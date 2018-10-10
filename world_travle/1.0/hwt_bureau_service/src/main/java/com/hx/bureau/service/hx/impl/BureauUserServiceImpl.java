package com.hx.bureau.service.hx.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.bureau.HxBureauModule;
import com.hwt.domain.entity.bureau.HxBureauModuleUser;
import com.hwt.domain.entity.bureau.HxBureauUser;
import com.hwt.domain.mapper.admin.AdminSystemconfigMapper;
import com.hwt.domain.mapper.bureau.HxBureauModuleMapper;
import com.hwt.domain.mapper.bureau.HxBureauModuleUserMapper;
import com.hwt.domain.mapper.bureau.HxBureauUserMapper;
import com.hx.bureau.service.hx.BureauUserService;
import com.hx.bureau.service.utils.BureauConfigKey;
import com.hx.core.exception.BaseException;
import com.hx.core.page.asyn.PageResult;
import com.hx.core.utils.MessageUtil;
import com.hx.core.utils.ObjectHelper;

@Service
public class BureauUserServiceImpl implements BureauUserService{
	@Autowired
	private HxBureauUserMapper hxBureauUserMapper;
	
	@Autowired
	private AdminSystemconfigMapper adminSystemconfigMapper;
	
	@Autowired
	private HxBureauModuleMapper hxBureauModuleMapper;
	
	@Autowired
	private HxBureauModuleUserMapper hxBureauModuleUserMapper;
	
	@Override
	public PageResult<Map<String, Object>> queryByMap(Map<String, Object> map) {
		PageResult<Map<String,Object>> pageResult = new PageResult<Map<String, Object>>();
		
		int count = hxBureauUserMapper.queryCountByMap(map);

		List<Map<String, Object>> dataList = hxBureauUserMapper.queryListByMap(map);
		pageResult.setCount(count);
		pageResult.setDataList(dataList);
		return pageResult;
	}

	@Override
	@Transactional
	public void insert(HxBureauUser bureauUser) {
		String bureau_user_account = bureauUser.getBureau_user_account();
		HxBureauUser bureauUser2 = hxBureauUserMapper.query_bureau_user_account(bureau_user_account);
		if (bureauUser2!=null){
			throw new RuntimeException("该账号已被使用，请重新输入");
		}
		String key = MessageUtil.MD5(bureau_user_account, 16);
		String passWord = ObjectHelper.passWord(bureauUser.getPassword(), key);
		
		bureauUser.setPassword(passWord);
		bureauUser.setPwd_salt(key);
		
		bureauUser.setCreate_time(System.currentTimeMillis());
		hxBureauUserMapper.insertSelective(bureauUser);
		
	}

	@Override
	public HxBureauUser queryByBureau_user_id(Long bureau_user_id) {
		
		return hxBureauUserMapper.queryByBureau_user_id(bureau_user_id);
	}

	@Override
	@Transactional
	public void update(HxBureauUser bureauUser) {
		hxBureauUserMapper.updateByPrimaryKeySelective(bureauUser);
	}

	@Override
	@Transactional
	public int deleteById(Long id, Integer type) {
		
		return hxBureauUserMapper.deleteById(id,type);
	}

	@Override
	@Transactional
	public String resetPassword(Long id) {
		String resetPassword = adminSystemconfigMapper.queryValueByConfigKey(BureauConfigKey.bureauResetPassword);
		if (ObjectHelper.isEmpty(resetPassword)){
			resetPassword="666666";
		}
		HxBureauUser hxBureauUser = hxBureauUserMapper.selectByPrimaryKey(id);
		
		hxBureauUser.setPassword(ObjectHelper.passWord(resetPassword, hxBureauUser.getPwd_salt()));
		hxBureauUserMapper.updateByPrimaryKeySelective(hxBureauUser);
		return resetPassword;
	}

	@Override
	public List<HxBureauModule> queryAll() {
		
		return hxBureauModuleMapper.queryAllIsNotHide();
	}

	@Override
	public List<HxBureauModule> queryAllBybureau_user_id(Long id) {
		
		return hxBureauModuleMapper.queryAllBy_user_id(id);
	}

	@Override
	public void adpower(Long id, Long[] rightList) {
		//删除权限
		hxBureauModuleUserMapper.deleteByUserId(id);
		
		
		if (!ObjectHelper.isEmpty(rightList)){
			List<HxBureauModuleUser> list = new ArrayList<>();
			for (int i = 0; i < rightList.length; i++) {
				HxBureauModuleUser hxBureauModuleUser = new HxBureauModuleUser();
				hxBureauModuleUser.setBureau_module_id(rightList[i]);
				hxBureauModuleUser.setBureau_user_id(id);
				list.add(hxBureauModuleUser);
			}
			hxBureauModuleUserMapper.savaUserModule(list);
		}
		
	}

	@Override
	public void user_editPassWord(String oldPassword, String password, Long bureau_user_id) {
		HxBureauUser hxBureauUser = hxBureauUserMapper.selectByPrimaryKey(bureau_user_id);
		
		
		if(!hxBureauUser.getPassword().equals(ObjectHelper.passWord(oldPassword, hxBureauUser.getPwd_salt()))){
			throw new BaseException("旧密码错误");
		}
		password = ObjectHelper.passWord(password, hxBureauUser.getPwd_salt());
		
		hxBureauUser.setPassword(password);
		hxBureauUserMapper.updateByPrimaryKeySelective(hxBureauUser);
	}

	
}
