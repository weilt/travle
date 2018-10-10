package com.xx.admin.serviceImpl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xx.admin.dao.AdminRoleDao;
import com.xx.admin.dao.AdminSystemConfigDao;
import com.xx.admin.dao.AdminUserDao;
import com.xx.admin.entity.AdminUser;
import com.xx.admin.service.AdminUserService;
import com.xx.springBootUtil.excption.BaseException;
import com.xx.springBootUtil.util.ObjectHelper;
import com.xx.util.result.JsonUtil;

@Service
public class AdminUserServiceImpl implements AdminUserService{

	@Resource
	private AdminSystemConfigDao adminSystemConfigDao;
	@Resource
	private AdminRoleDao adminRoleDao;
	@Resource
	private AdminUserDao adminUserDao;
	
	
	@Transactional
	public void AdminUserInsert(AdminUser adminUser,Integer userId) {
		if(adminUserDao.findByUserNameCount(adminUser.getUserName()) > 0){
			throw new BaseException("用户名已存在，请重新填写");
		}
		String initPassword = adminSystemConfigDao.getValue("initPassword");// 系统初始化密码
		if (ObjectHelper.isEmpty(initPassword)) {
			initPassword = "123456";
		}
		adminUser.setPassWord(DigestUtils.md5Hex(initPassword));
		adminUser.setUserId(userId);
		adminUser.setCreateTime(new Date());
		adminUser.setIsDelete((byte)1);
		adminRoleDao.findUpdateNumber(adminUser.getRoleId(), 1);
		adminUserDao.insert(adminUser);
	}
	
	@Transactional
	public void AdminUserUpdate(AdminUser adminUser) throws Exception {
		AdminUser user = adminUserDao.findById(adminUser.getId());
		if (!user.getRoleId().equals(adminUser.getRoleId())) {// 判断角色信息有没有改变 如果改变修改对应角色数量
			//-1
			adminRoleDao.findUpdateNumber(user.getRoleId(), -1);
			//+1
			adminRoleDao.findUpdateNumber(adminUser.getRoleId(), 1);
		}
		user.setRoleId(adminUser.getRoleId());
		user.setRealName(adminUser.getRealName());
		user.setSex(adminUser.getSex());
		user.setTelephone(adminUser.getTelephone());
		user.setAddress(adminUser.getAddress());
		adminUserDao.update(user);
	}

	@Override
	public int adminUserLogin(Long id) {
		int num =  adminUserDao.adminUserLogin(id);
		return num;
	}
	
}
