package com.hx.bureau.service.hx.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.bureau.HwTravelBureau;
import com.hwt.domain.entity.bureau.HxBureauModule;
import com.hwt.domain.entity.bureau.HxBureauUser;
import com.hwt.domain.mapper.bureau.HwTravelBureauMapper;
import com.hwt.domain.mapper.bureau.HxBureauModuleMapper;
import com.hwt.domain.mapper.bureau.HxBureauUserMapper;
import com.hx.bureau.service.cache.BureauUserCache;
import com.hx.bureau.service.cache.BureauUserCacheTools;
import com.hx.bureau.service.hx.BureauLoginService;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryCondition.LinkKey;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.mongo.value.Sort;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.ObjectHelper;

@Service
public class BureauLoginServiceImpl implements BureauLoginService {
	
	@Autowired
	private HwTravelBureauMapper hwTravelBureauMapper;
	
	@Autowired
	private HxBureauUserMapper hxBureauUserMapper;
	
	@Autowired
	private HxBureauModuleMapper hxBureauModuleMapper;
	
	
	@Transactional
	@Override
	public void enter(HwTravelBureau hwTravelBureau) {
		hwTravelBureau.setCreate_time(System.currentTimeMillis());
		hwTravelBureau.setReason(null);
		//判断是否入驻过
		HwTravelBureau hwTravelBureauOld = hwTravelBureauMapper.query_by_license_no(hwTravelBureau.getLicense_no());
		if (hwTravelBureauOld!=null){
			if (hwTravelBureauOld.getState()==0){
				throw new RuntimeException("审核中...请耐心等待");
			}else if (hwTravelBureauOld.getState()==1){
				
				throw new RuntimeException("该旅行社已通过，请不要重复申请");
			}else{
				hwTravelBureau.setState(0);
				hwTravelBureau.setBureau_id(hwTravelBureauOld.getBureau_id());
				hwTravelBureauMapper.updateByPrimaryKeySelective(hwTravelBureau);
				//其他业务
			}
		}else{
			//添加入驻
			
			hwTravelBureauMapper.insertSelective(hwTravelBureau);
			//其他业务
		}
	}


	@Override
	public void login(String bureau_user_account, String password, HttpServletRequest request) {
		
		HxBureauUser hxBureauUser = hxBureauUserMapper.query_bureau_user_account(bureau_user_account);
		if(hxBureauUser==null){
			throw new RuntimeException("账号密码错误");
		}else{
			if(hxBureauUser.getIs_delete()==2){
				throw new RuntimeException("该账号已被禁用，请联系相关人员");
			}
			if(!hxBureauUser.getPassword().equals(ObjectHelper.passWord(password, hxBureauUser.getPwd_salt()))){
				throw new RuntimeException("账号密码错误");
			}else{
				
				//获取上次登录时间
				
				MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
				condition.add(new MongoQueryValue(MongoOperator.eq, "type", 1));
				condition.add(new MongoQueryValue(MongoOperator.eq, "bureau_id", hxBureauUser.getBureau_id()));
				condition.add(new MongoQueryValue(MongoOperator.eq, "bureau_user_id", 1));
				Sort sort = new Sort("creat_time", Sort.DESC);
				List<Map<String,Object>> findByPage = MongoService.findByPage(HXSystemConfig.MONGO_DB_NAME_Logs, HXSystemConfig.MONGO_Collection_NAME_Bureau_Logs, null, condition, sort, 1, 1);
				Long last_login_time = null;
				if (!ObjectHelper.isEmpty(findByPage)){
					last_login_time = Long.parseLong(findByPage.get(0).get("creat_time").toString());
				}
				BureauUserCache bureauUserCache = new BureauUserCache(hxBureauUser.getBureau_user_id(), 
						hxBureauUser.getBureau_user_account(), hxBureauUser.getBureau_id(), hxBureauUser.getIs_legal(), hxBureauUser.getReal_name(),last_login_time);
				List<HxBureauModule> listModule = null;
				if (hxBureauUser.getIs_legal()==1){
					listModule = hxBureauModuleMapper.queryAllIsNotHide();
				}else{
					listModule = hxBureauModuleMapper.queryAllBy_user_id(hxBureauUser.getBureau_user_id());
				}
				
				BureauUserCacheTools.addSession(bureauUserCache, listModule, request);;
			}
		}
	}

}
