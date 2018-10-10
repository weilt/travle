package com.hx.admin.service.admin.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.admin.AdminModule;
import com.hwt.domain.entity.admin.AdminRole;
import com.hwt.domain.entity.admin.AdminUser;
import com.hwt.domain.entity.admin.vo.AdminModuleVo;
import com.hwt.domain.entity.admin.vo.AdminUserVo;
import com.hwt.domain.mapper.admin.AdminModuleMapper;
import com.hwt.domain.mapper.admin.AdminRoleMapper;
import com.hwt.domain.mapper.admin.AdminSystemconfigMapper;
import com.hwt.domain.mapper.admin.AdminUserMapper;
import com.hx.admin.service.admin.AdminUserService;
import com.hx.admin.service.cache.AdminUserCache;
import com.hx.admin.service.cache.AdminUserCacheTools;
import com.hx.admin.service.utils.AdminConfigKey;
import com.hx.admin.service.utils.EncryptionUtil;
import com.hx.core.page.asyn.PageResult;
import com.hx.core.utils.ObjectHelper;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserMapper adminUserMapper;

	@Autowired
	private AdminSystemconfigMapper adminSystemconfigMapper;

	@Autowired
	private AdminRoleMapper adminRoleMapper;

	@Autowired
	private AdminModuleMapper adminModuleMapper;

	@Override
	public PageResult<AdminUserVo> queryByMap(Map<String, Object> map) {

		int count = adminUserMapper.queryCountByMap(map);

		List<AdminUserVo> dataList = adminUserMapper.queryListByMap(map);

		PageResult<AdminUserVo> pageResult = new PageResult<AdminUserVo>();
		pageResult.setCount(count);
		pageResult.setDataList(dataList);
		return pageResult;
	}

	@Transactional
	@Override
	public int insert(AdminUser adminUser) {
		AdminUser adminUser1 = adminUserMapper.queryByUser_account(adminUser.getUser_account());
		if (adminUser1 != null) {
			throw new RuntimeException("该用户名已被使用，请重新输入");
		}
		String password = adminSystemconfigMapper.queryValueByConfigKey(AdminConfigKey.initPassword);
		adminUser.setPassword(EncryptionUtil.userPasswordEncrypt(password));
		adminUser.setCreate_time(new Date());
		return adminUserMapper.insertSelective(adminUser);
	}

	@Override
	public AdminUser queryById(Long user_id) {
		return adminUserMapper.selectByPrimaryKey(user_id);
	}

	@Transactional
	@Override
	public int update(AdminUser adminUser) {
		return adminUserMapper.updateByPrimaryKeySelective(adminUser);
	}

	@Transactional
	@Override
	public int deleteById(Long user_id, Integer type) {
		return adminUserMapper.deleteById(user_id, type);
	}

	@Override
	public String resetPassword(Long id) {
		// 获取密码
		String resetPassword = adminSystemconfigMapper.queryValueByConfigKey(AdminConfigKey.resetPassword);
		adminUserMapper.resetPassword(id, EncryptionUtil.userPasswordEncrypt(resetPassword));
		return resetPassword;
	}

	@Override
	public void login(String user_account, String password, HttpServletRequest request) {
		AdminUser adminUser = adminUserMapper.queryByUser_account(user_account);
		if (adminUser == null) {
			throw new RuntimeException("用户名密码错误");
		} else {
			if (!adminUser.getPassword().equals(EncryptionUtil.userPasswordEncrypt(password))) {
				throw new RuntimeException("用户名密码错误");
			} else {

				if (adminUser.getIsenable() == 2) {
					throw new RuntimeException("账号已被禁用，有问题请联系管理员");
				}
				if (adminUser.getRole_id() <= 0) {
					throw new RuntimeException("该用户还没分配角色,有问题请联系管理员");
				}
				AdminUserCache adminUserCache = new AdminUserCache(adminUser.getUser_id(), adminUser.getUser_account(),
						adminUser.getReal_name(), adminUser.getRole_id());
				// 查询角色信息
				AdminRole role = adminRoleMapper.selectByPrimaryKey(adminUser.getRole_id());
				if (ObjectHelper.isEmpty(role)) {
					throw new RuntimeException("角色信息错误,请联系管理员");
				}

				// 查询权限
				List<AdminModule> listModule = null;
				if (adminUser.getRole_id() == AdminConfigKey.IS_SUPER_ADMIN) {
					listModule = adminModuleMapper.queryAllIsNotHide();
				} else {
					listModule = adminModuleMapper.queryAllIsNotHideByRoleId(adminUser.getRole_id());
				}
				
				// 存入session
				AdminUserCacheTools.addSession(adminUserCache, listModule, request);
			}
		}

	}

}
