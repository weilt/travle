package com.hx.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.user.HxUserConfig;
import com.hwt.domain.entity.user.HxUserInfo;
import com.hwt.domain.entity.user.Vo.HxUserConfigVo;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hwt.domain.entity.user.Vo.UserVo;
import com.hwt.domain.mapper.user.HxUserBlackListMapper;
import com.hwt.domain.mapper.user.HxUserConfigMapper;
import com.hwt.domain.mapper.user.HxUserInfoMapper;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hx.core.exception.BaseException;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.mongo.value.MongoQueryCondition.LinkKey;
import com.hx.core.redis.RedisCache;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.JsonUtils;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.wyim.entity.ImUser;
import com.hx.core.wyim.entity.emu.SMSType;
import com.hx.core.wyim.service.ImService;
import com.hx.user.service.AccountInfoService;
import com.hx.user.utils.BaseUtil;

@Service
public class AccountInfoServiceImpl implements AccountInfoService{
	
	@Autowired
	private HxUserMapper hxUserMapper;
	
	@Autowired
	private HxUserInfoMapper hxUserInfoMapper;
	
	@Autowired
	private ImService imService;
	
	@Autowired
	private HxUserConfigMapper hxUserConfigMapper;
	
	@Autowired
	private HxUserBlackListMapper userBlackListMapper;
	
	@Override
	public Object accountInfo(String token) {
		
		LoginVo loginVo =  BaseUtil.getLoginVo(token);
		if(ObjectHelper.isEmpty(loginVo)) {
			throw new BaseException("登录超时！");
		}
		UserVo userVo = hxUserMapper.selectByUserId(loginVo.getUser_id());
		if(ObjectHelper.isEmpty(userVo)){
			throw new BaseException("获取数据为空，请重新登录");
		}
		//去除 重要的数据信息
		userVo.setUser_id(null);
		userVo.setPassword(null);
		userVo.setPwd_salt(null);
		return userVo;
	}
	
	@Transactional
	@Override
	public void editAccount(String token, HxUserInfo hxUserInfo) throws Exception {
		if (StringUtils.isNoneBlank(hxUserInfo.getUser_autograph()) && hxUserInfo.getUser_autograph().length()>30){
			throw new RuntimeException("个性签名不能超过30个字");
		}
		
		if (StringUtils.isNoneBlank(hxUserInfo.getNickname())&&hxUserInfo.getNickname().length()>16){
			throw new RuntimeException("昵称不能超过16个字");
		}
		
		String username = hxUserInfo.getUsername();//真实姓名
		String nickname = hxUserInfo.getNickname(); //昵称
		String user_autograph = hxUserInfo.getUser_autograph(); //个性签名
		String user_icon = hxUserInfo.getUser_icon();//用户头像
		Integer user_emotion = hxUserInfo.getUser_emotion();//情感
		String friend_circle_cover = hxUserInfo.getFriend_circle_cover();//用户朋友圈封面
		Integer user_sex = hxUserInfo.getUser_sex(); //用户性别 0-其他 1-男 2-女
		Date user_birthday = hxUserInfo.getUser_birthday(); //用户生日
		String user_profession = hxUserInfo.getUser_profession(); //用户职业
		String user_area_state = hxUserInfo.getUser_area_state();//用户地区 - 国 标识
		String user_area_state_name = hxUserInfo.getUser_area_state_name();//用户地区 - 国 名称
		String user_area_province = hxUserInfo.getUser_area_province();//用户地区 - 省 标识
		String user_area_province_name = hxUserInfo.getUser_area_province_name();//用户地区 - 省名称
		String user_area_city = hxUserInfo.getUser_area_city();//用户地区 - 市 标识
		String user_area_city_name = hxUserInfo.getUser_area_city_name();//用户地区 - 市名称
		String user_area_district = hxUserInfo.getUser_area_district();//用户地区 - 区 标识
		String user_area_district_name = hxUserInfo.getUser_area_district_name();//用户地区 - 区 名称
		
		if(ObjectHelper.isEmpty(nickname) && ObjectHelper.isEmpty(user_autograph)&&ObjectHelper.isEmpty(username)&&ObjectHelper.isEmpty(user_icon)
				&& ObjectHelper.isEmpty(user_sex) && ObjectHelper.isEmpty(user_profession) && ObjectHelper.isEmpty(user_area_state)
				&& ObjectHelper.isEmpty(user_area_state_name)&& ObjectHelper.isEmpty(user_birthday)&& ObjectHelper.isEmpty(friend_circle_cover)&& ObjectHelper.isEmpty(user_emotion)){
			throw new BaseException("请输入要修改的用户信息");
		}
		LoginVo loginVo =  BaseUtil.getLoginVo(token);
		if(ObjectHelper.isEmpty(loginVo)) {
			throw new BaseException("登录超时！");
		}
		HxUserInfo hxUserInfoEdit = hxUserInfoMapper.selectByUserId(loginVo.getUser_id());
		
		hxUserInfo.setUser_id(loginVo.getUser_id());
		
		
		
		if(ObjectHelper.isNotEmpty(user_icon)){
			hxUserInfoEdit.setUser_icon(user_icon);
			loginVo.setUser_icon(user_icon);
			//修改是导游的情况
			
			MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
	        condition.add(new MongoQueryValue(MongoOperator.eq, "name_id", loginVo.getUser_id()));
	        condition.add(new MongoQueryValue(MongoOperator.eq, "type", 2));
	        Map<String, Object> findOne2 = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, condition, null);
	        if (findOne2!=null){
	        	Map<String, Object> updateMap = new HashMap<>();
		    	updateMap.put("user_icon", user_icon);
		    	
				Map<String, Object> filterMap = new HashMap<>();
				filterMap.put("name_id", loginVo.getUser_id());
				filterMap.put("type", 2);
				MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, filterMap , updateMap );
	        }
			
		
		}
		if(ObjectHelper.isNotEmpty(friend_circle_cover)){
			hxUserInfoEdit.setFriend_circle_cover(friend_circle_cover);
		}
		
		if(ObjectHelper.isNotEmpty(nickname)){
			hxUserInfoEdit.setNickname(nickname);
			loginVo.setNickname(nickname);
		}
		
		if(ObjectHelper.isNotEmpty(user_autograph)){
			hxUserInfoEdit.setUser_autograph(user_autograph);
		}
		
		if(user_autograph!=null && user_autograph.trim().equals("@null@")){
			hxUserInfoEdit.setUser_autograph(null);
		}
	 
		
		if(ObjectHelper.isNotEmpty(user_sex)){
			if(user_sex.intValue() > 2 || user_sex.intValue() < 0){
				throw new BaseException("性别参数不能为空");
			}
			hxUserInfoEdit.setUser_sex(user_sex);
		}
		if(ObjectHelper.isNotEmpty(user_emotion)){
			if(user_sex.intValue() > 4 || user_sex.intValue() < 0){
				throw new BaseException("用户情感参数不能为空");
			}
			hxUserInfoEdit.setUser_emotion(user_emotion);
		}
		
		if(ObjectHelper.isNotEmpty(user_profession)){
			hxUserInfoEdit.setUser_profession(user_profession);
		}
		
		if(ObjectHelper.isNotEmpty(user_birthday)){
			hxUserInfoEdit.setUser_birthday(user_birthday);
		}
		//修改国家地区
		if(ObjectHelper.isNotEmpty(user_area_state) && ObjectHelper.isNotEmpty(user_area_state_name)){
			//清空国家地区的数据
			hxUserInfoEdit.setUser_area_state(user_area_state);
			hxUserInfoEdit.setUser_area_state_name(user_area_state_name);
			hxUserInfoEdit.setUser_area_province(user_area_province);
			hxUserInfoEdit.setUser_area_province_name(user_area_province_name);
			hxUserInfoEdit.setUser_area_city(user_area_city);
			hxUserInfoEdit.setUser_area_city_name(user_area_city_name);
			hxUserInfoEdit.setUser_area_district(user_area_district);
			hxUserInfoEdit.setUser_area_district_name(user_area_district_name);;
		}
		hxUserInfoMapper.updateByPrimaryKey(hxUserInfoEdit);
		
		ImUser imUser = new ImUser(loginVo.getIm_id()+"");
		int count = 0;
		
		if (user_sex!=null){
			imUser.setGender(hxUserInfoEdit.getUser_sex());
			count++;
		}
		
		if (StringUtils.isNoneBlank(nickname)){
			imUser.setName(hxUserInfoEdit.getNickname());
			count++;
		}
//		if (StringUtils.isNoneBlank(user_icon)){
//			imUser.setIcon(hxUserInfoEdit.getUser_icon());
//			count++;
//		}
		
		if (count>0){
			imService.updateUinfo(imUser);
		}
	}
	
	@Transactional
	@Override
	public void updateAccount(String token, String account) {
		
		String pattern  ="^[a-zA-Z][a-zA-Z0-9_-]{2,15}$";
		Pattern r = Pattern.compile(pattern);
		Matcher matcher = r.matcher(account);
		boolean matches = matcher.matches();
		
		if (!matches){
			throw new RuntimeException("账号只能3—16个字母、数字、下划线和减号，必须以字母开头（不区分大小写），不支持设置中文");
		}
		int count = hxUserMapper.hxUseAccountCount(account);
		if (count>0){
			throw new RuntimeException("该账号已被使用，请重新输入");
		}
		
		LoginVo loginVo =  BaseUtil.getLoginVo(token);
		if(ObjectHelper.isEmpty(loginVo)) {
			throw new BaseException("登录超时！");
		}
		int num = hxUserMapper.updateAccount(loginVo.getUser_id(),account);
		if (num!=1){
			
		}
		hxUserInfoMapper.updateAccount(loginVo.getUser_id(),account);
		
	}
	
	@Transactional
	@Override
	public void updateAuthority(String token, HxUserConfig hxUserConfig) {
		LoginVo loginVo =  BaseUtil.getLoginVo(token);
		hxUserConfig.setUser_id(loginVo.getUser_id());
		if(ObjectHelper.isEmpty(loginVo)) {
			throw new RuntimeException("登录超时！");
		}
		hxUserConfigMapper.updateUserConfig(hxUserConfig);
	}

	@Override
	public HxUserConfigVo queryById(String token) {
		LoginVo loginVo =  BaseUtil.getLoginVo(token);
		if(ObjectHelper.isEmpty(loginVo)) {
			throw new RuntimeException("登录超时！");
		}
		return hxUserConfigMapper.queryByUserId(loginVo.getUser_id());
	}
	
	@Transactional
	@Override
	public void updatePassWord(String token, String newpassword, String oldpassword, int type) {
		LoginVo loginVo =  BaseUtil.getLoginVo(token);
		if(ObjectHelper.isEmpty(loginVo)) {
			throw new RuntimeException("登录超时！");
		}
		UserVo userVo = hxUserMapper.userVoAccountPhone(loginVo.getAccount_phone());
		String newspassword = ObjectHelper.passWord(newpassword, userVo.getPwd_salt());
		if(type==1) {
			String passWord = ObjectHelper.passWord(oldpassword, userVo.getPwd_salt());
			if(!passWord.equals(userVo.getPassword())) {
				throw new RuntimeException("原密码输入错误！");
			}
			hxUserMapper.updatePassWord(userVo.getAccount(), newspassword);
		}else if(type==2) {
			hxUserMapper.updatePassWord(userVo.getAccount(), newspassword);
		}
	}

	@Override
	public void updatePassWordToCode(String token, String smsVerify, String phone) {
		// 验证 - 验证码
		String redisVerify = RedisCache.get("HXJLFindPass_" + phone);

		if (ObjectHelper.isEmpty(redisVerify)) {
			throw new BaseException("验证码过期，请重新获取");
		}
		if (!smsVerify.equals(redisVerify)) {
			throw new BaseException("验证码错误");
		}
	}

	@Override
	public void updatePassWordByCode(String newpassword, String phone, String code) {
		UserVo userVo = hxUserMapper.userVoAccountPhone(phone);
		if(ObjectHelper.isEmpty(userVo)) {
			throw new BaseException("电话号码未注册");
		}
		String code1 = RedisCache.get(SMSType.findPass.smsTypePrefix + phone);
		if(!code1.equals(code)) {
			throw new BaseException("验证码错误");
		}
		String newspassword = ObjectHelper.passWord(newpassword, userVo.getPwd_salt());
		hxUserMapper.updatePassWord(userVo.getAccount(), newspassword);
		RedisCache.del(SMSType.findPass.smsTypePrefix + phone);
	}
	
}
