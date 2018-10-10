package com.hx.user.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.hwt.domain.entity.user.HxUser;
import com.hwt.domain.entity.user.HxUserConfig;
import com.hwt.domain.entity.user.HxUserInfo;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hwt.domain.entity.user.Vo.UserVo;
import com.hwt.domain.entity.user.Vo.WangYiToken;
import com.hwt.domain.mapper.user.HxUserConfigMapper;
import com.hwt.domain.mapper.user.HxUserInfoMapper;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hx.core.exception.BaseException;
import com.hx.core.redis.RedisCache;
import com.hx.core.utils.DateUtils;
import com.hx.core.utils.GsonUtil;
import com.hx.core.utils.IDUtils;
import com.hx.core.utils.MessageUtil;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.utils.PropertiesUtils;
import com.hx.core.utils.UUIDHelper;
import com.hx.core.utils.WebUtils;
import com.hx.core.wyim.entity.ImUser;
import com.hx.core.wyim.service.ImService;
import com.hx.core.wyim.service.impl.NtesImServiceImpl;
import com.hx.user.logs.entity.parent.LoginLog;
import com.hx.user.logs.entity.parent.Logs;
import com.hx.user.logs.service.LogseeService;
import com.hx.user.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	/**
	 * 默认头像地址
	 */
	private static final String placeholder_user_icon = "https://gwstatic.oss-cn-beijing.aliyuncs.com/placeholder_avatar.png";
	/**
	 * 朋友圈封面地址
	 */
	private static final String placeholder_friend_circle_cover = "https://gwstatic.oss-cn-beijing.aliyuncs.com/placeholder_friend_circle_cover.png";

	@Autowired
	private HxUserMapper hxUserMapper;

	@Autowired
	private HxUserInfoMapper hxUserInfoMapper;

	@Autowired
	private ImService imService;
	
	@Autowired
	private LogseeService logsService; 
	
	@Autowired
	private HxUserConfigMapper hxUserConfigMapper;

	@Override
	public LoginVo validateToken(String token) {
		String cacheUser = RedisCache.get(token);
		if (StringUtils.isNotBlank(cacheUser)) {
			return GsonUtil.fromJson(cacheUser, LoginVo.class);
		}
		return null;
	}

	@Transactional
	@Override
	public Object imLogin(String phone, String smsVerify, HttpServletRequest request) throws ParseException {
		// 验证 - 验证码
		String redisVerify = RedisCache.get("HXJLLogin_" + phone);

		if (ObjectHelper.isEmpty(redisVerify)) {
			throw new BaseException("验证码过期，请重新获取");
		}

		if (!smsVerify.equals(redisVerify)) {
			throw new BaseException("验证码错误");
		}

		UserVo userVo = hxUserMapper.userVoAccountPhone(phone);
		if (ObjectHelper.isEmpty(userVo)) {
			throw new BaseException("账号不存在");
		}
		Object o = userVoImLogin(userVo, request);
		
		addLoginLog(userVo.getUser_id(), ObjectHelper.getIpAddress(request), 1, 1, null, null, null);
		RedisCache.del("HXJLLogin_" + phone);
		return o;
	}

	@Transactional
	@Override
	public Object imLogin_accPass(String acc, String pwd, HttpServletRequest request) throws ParseException {
		// 验证acc是手机号码还是普通的账号
		boolean b = ObjectHelper.isPhoneLegal(acc);
		UserVo userVo = null;
		int loginState;
		if (b) {
			loginState = 2;
			userVo = hxUserMapper.userVoAccountPhone(acc);
		} else {
			loginState = 3;
			userVo = hxUserMapper.userVoAccount(acc);
		}
		if (ObjectHelper.isEmpty(userVo)) {
			throw new BaseException("账号不存在");
		}
		// 验证密码是否正确
		String passJiaMi = ObjectHelper.passWord(pwd, userVo.getPwd_salt());
		if (!passJiaMi.equals(userVo.getPassword())) {
			throw new BaseException("密码错误");
		}
		Object o = userVoImLogin(userVo, request);
		
		
		
		addLoginLog(userVo.getUser_id(), ObjectHelper.getIpAddress(request), 1, loginState, null, null, null);
		return o;
	}
	
	
	/**
	 * 添加登录日志
	 * @param user_id 用户id
	 * @param clientIpAndName ip
	 * @param state  1-登录  2-登出
	 * @param loginState  登录方式  1-手机加验证码  2-手机加密码 3-账号加密码
	 * @param longitude		 经度
	 * @param latitude		维度
	 * @param remarks		备注
	 */
	@Async
	private void addLoginLog(Long user_id,String clientIpAndName,int state,int loginState,String longitude,String latitude,String remarks){
		try {
			LoginLog loginLog = new LoginLog();
			loginLog.setClientIpAndName(clientIpAndName);
			loginLog.setUser_id(user_id);
			loginLog.setCreateTime(new Date());
			loginLog.setLatitude(latitude);
			loginLog.setLongitude(latitude);
			loginLog.setLoginState(loginState);
			loginLog.setRemarks(remarks);
			loginLog.setState(state);
			loginLog.setTimeStamp(new Date().getTime());
			logsService.add(loginLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 登录管理统一的数据信息
	 * 
	 * @param userVo
	 * @return
	 * @throws ParseException
	 */
	public Object userVoImLogin(UserVo userVo, HttpServletRequest request) throws ParseException {
		// 验证冻结状态
		if (userVo.getLogin_state().intValue() > 1) {
			if (userVo.getLogin_state().intValue() == 2) {
				// 验证账号被封和解除时间
				Date loginState2Begin = userVo.getLogin_state2_begin();
				Date loginState2End = userVo.getLogin_state2_end();
				if (ObjectHelper.isNotEmpty(loginState2Begin) && ObjectHelper.isNotEmpty(loginState2End)) {
					long lon = new Date().getTime();
					if (loginState2End.getTime() > loginState2Begin.getTime() && lon > loginState2Begin.getTime()
							&& lon < loginState2End.getTime()) {
						throw new BaseException("账号被封，请联系管理员");
					} else {
						userVo.setLogin_state(1);
					}
				} else {
					// 没时间限制，视为永久冻结状态
					throw new BaseException("账号被封，请联系管理员");
				}
			} else {
				// 永久冻结状态
				throw new BaseException("账号被封，请联系管理员");
			}
		}
		// 验证token是否存在 - 不存在则添加token信息
		Date date = userVo.getIm_token_time(); // 获取token最新的时间信息
		int dayInt = 1;
		if (ObjectHelper.isNotEmpty(date)) {
			// 5天获取新token
			dayInt = DateUtils.daysBetween(date, new Date());
		}
		String refreshToken = null; // 验证新的Token还是之前的Token
		Gson gson = new Gson();
		if (ObjectHelper.isEmpty(userVo.getIm_token()) || dayInt > 5) {
			if (imService == null)
				imService = new NtesImServiceImpl();
			try {
				// { "code":200, "info":{"token":"xxx","accid":"xx" } }
				String tokenRe = imService.getNewToken(userVo.getIm_id() + "");
				if (ObjectHelper.isEmpty(tokenRe)) {
					throw new BaseException("获取通讯TOKEN失败");
				}

				WangYiToken wangYiToken = gson.fromJson(tokenRe, WangYiToken.class);
				if (wangYiToken.getCode() != 200) {
					throw new BaseException("获取通讯TOKEN失败");
				}

				Map<String, String> mapToken = wangYiToken.getInfo();

				if (ObjectHelper.isEmpty(mapToken) || mapToken.size() == 0
						|| ObjectHelper.isEmpty(mapToken.get("token")) || ObjectHelper.isEmpty(mapToken.get("accid"))) {
					throw new BaseException("获取通讯TOKEN失败");
				}
				if (!mapToken.get("accid").toString().equals(userVo.getIm_id() + "")) {
					throw new BaseException("获取通讯TOKEN失败");
				}
				refreshToken = mapToken.get("token").toString();
				userVo.setIm_token(refreshToken);
				userVo.setIm_token_time(new Date());
				if (ObjectHelper.isEmpty(refreshToken)) {
					throw new BaseException("获取通讯TOKEN失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("获取通讯TOKEN失败");
			}
		}
		
		String theLoginId = "HXJL" + userVo.getAccount_phone() +"_HXJL" + UUIDHelper.createUUId(); // 登录标识
		
		//返回参数
		LoginVo loginVo2 = new LoginVo(userVo.getUser_id(), userVo.getAccount(), userVo.getAccount_phone(),
				userVo.getIm_id(), userVo.getIm_token(), userVo.getReferrer_account_id() + "",
				userVo.getRecently_login_time(), userVo.getRecently_login_ip(), theLoginId,userVo.getUser_icon(),userVo.getFriend_circle_cover());
		
		loginVo2.setNickname(userVo.getNickname());
		// 更新登录时间
		userVo.setRecently_login_time(new Date());
		userVo.setRecently_login_ip(WebUtils.getIpAddress(request));
		userVo.setLogin_type(1);

		
		LoginVo loginVo = new LoginVo(userVo.getUser_id(), userVo.getAccount(), userVo.getAccount_phone(),
				userVo.getIm_id(), userVo.getIm_token(), userVo.getReferrer_account_id() + "",
				userVo.getRecently_login_time(), userVo.getRecently_login_ip(), theLoginId,userVo.getUser_icon(),userVo.getFriend_circle_cover());
		RedisCache.delRegex("HXJL" + userVo.getAccount_phone() +"_*");
		boolean b = RedisCache.set(theLoginId.trim(), gson.toJson(loginVo), 86400*7);
		if (b) {

			HxUser hxUser = new HxUser();
			hxUser.setUser_id(userVo.getUser_id());
			hxUser.setIm_token(userVo.getIm_token());
			hxUser.setIm_token_time(userVo.getIm_token_time());
			hxUser.setLogin_state(userVo.getLogin_state());
			hxUser.setRecently_login_ip(userVo.getRecently_login_ip());
			hxUser.setRecently_login_time(new Date());
			hxUser.setLogin_type(userVo.getLogin_type());

			// 更新数据信息
			hxUserMapper.updateByPrimaryKeySelective(hxUser);

			// 添加登录日志

			/*// 返回登录成功的数据
			Map<String, Object> map = new HashMap<>();
			map.put("token", theLoginId);
			map.put("user_id", userVo.getUser_id());
			map.put("account", userVo.getAccount());
			map.put("account_phone", userVo.getAccount_phone());
			map.put("im_id", userVo.getIm_id());
			map.put("im_token", userVo.getIm_token());
			map.put("nickname", userVo.getNickname());
			map.put("userType", userVo.getUserType());
			map.put("user_icon", userVo.getUser_icon());
			map.put("recently_login_time", userVo.getRecently_login_time());*/
			
			
			return loginVo2;
		} else {
			throw new BaseException("登录失败，请重新登录");
		}
	}

	@Transactional
	@Override
	public Object imRegister(String phone, String userNickname, String pass, Long referrerAccountId) {

		// 验证手机
		if (!ObjectHelper.isPhoneLegal(phone)) {
			throw new BaseException("号码格式错误");
		}
		if (userNickname.length() > 16) {
			throw new BaseException("昵称不能超过16个字符");
		}
		if (pass.length() < 6) {
			throw new BaseException("密码不能少于6位字符");
		}

		// 验证是否注册重复

		HxUser hxUser = hxUserMapper.hxUserAccountPhone(phone);
		if (ObjectHelper.isNotEmpty(hxUser)) {
			throw new BaseException("手机号已注册，请直接登录");
		}

		HxUser hxUser2 = new HxUser();

		Gson gson = new Gson();

		String account = null;
		for (int i = 0; i < 100000; i++) {
			account = "hx" + UUIDHelper.getStringRandom(6).toLowerCase();
			if (hxUserMapper.hxUseAccountCount(account) == 0) {
				break; // 跳出循环
			} else {
				account = null;
				i--;
			}
		}
		if (ObjectHelper.isEmpty(account)) {
			throw new BaseException("账号注册失败");
		}
		hxUser2.setAccount(account);
		hxUser2.setAccount_phone(phone);

		String key = MessageUtil.MD5(account, 16);
		String passWord = ObjectHelper.passWord(pass, key);
		hxUser2.setPwd_salt(key);
		hxUser2.setPassword(passWord);
		hxUser2.setCreate_time(new Date());
		hxUser2.setAccount_is_edit((byte) 0);
		String imId = UUIDHelper.code(32).toLowerCase();

		// 生成头像地址
//		String userIcon;
//		String EXTERNAL_ENDPOINT = PropertiesUtils.getValue("EXTERNAL_ENDPOINT", "aliyun.properties").trim();
//
//		String IMG_OSS_BUCKET_NAME = PropertiesUtils.getValue("IMG_OSS_BUCKET_NAME", "aliyun.properties").trim();
//
//		String avatar = PropertiesUtils.getValue("avatar", "aliyun.properties").trim();
//		// 头像地址全路径
//		userIcon = EXTERNAL_ENDPOINT.replace("http://", "https://" + IMG_OSS_BUCKET_NAME + ".") + "/" + avatar + "/"
//				+ imId + ".jpg";

		// 向网易注册信息
		if (imService == null)
			imService = new NtesImServiceImpl();

		ImUser imUser = new ImUser(imId, userNickname);
		try {
			String reWangyi = imService.register(imUser);

			if (ObjectHelper.isEmpty(reWangyi)) {
				throw new BaseException("通讯注册失败");
			}

			WangYiToken wangYiToken = gson.fromJson(reWangyi, WangYiToken.class);
			if (wangYiToken.getCode() != 200) {
				throw new BaseException("通讯注册失败");
			}
			Map<String, String> map = wangYiToken.getInfo();
			if (ObjectHelper.isEmpty(map) || map.size() == 0 || ObjectHelper.isEmpty(map.get("token"))
					|| ObjectHelper.isEmpty(map.get("accid"))) {
				throw new BaseException("通讯注册失败");
			}
			// a88c5b8e24574ec1943ddd4232bdbe7d
			// 3d7f589d459c4a72bb341c92cbb6d5f1
			if (!map.get("accid").equals(imId)) {
				throw new BaseException("通讯注册失败");
			}
			hxUser2.setIm_token(map.get("token"));
			hxUser2.setIm_token_time(new Date());
			hxUser2.setIm_id(imId);
			// 添加数据
			hxUserMapper.insertReturnUserId(hxUser2);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(e.getMessage());
		}
		// 用户信息
		HxUserInfo hxUserInfo = new HxUserInfo();
		hxUserInfo.setNickname(userNickname);
		hxUserInfo.setPhone(phone);
		hxUserInfo.setUser_id(hxUser2.getUser_id());
		hxUserInfo.setUser_icon(placeholder_user_icon);
		//hxUserInfo.setFriend_circle_cover(placeholder_friend_circle_cover);
		hxUserInfo.setReferrer_account_id(referrerAccountId + "");
		hxUserInfo.setAccount(account);
		hxUserInfo.setIm_id(imId);
		hxUserInfoMapper.insertSelective(hxUserInfo);
		//hxUserInfoMapper.insert(hxUserInfo);
		
		//用户配置
		HxUserConfig hxUserConfig = new HxUserConfig();
		hxUserConfig.setUser_id(hxUser2.getUser_id());
		hxUserConfigMapper.insertSelective(hxUserConfig);

		Map<String, Object> map = new HashMap<>();
		map.put("account", account);
		map.put("accountPhone", phone);
		return map;
	}

}
