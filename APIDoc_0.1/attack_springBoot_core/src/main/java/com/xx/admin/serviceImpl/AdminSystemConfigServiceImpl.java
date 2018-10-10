package com.xx.admin.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xx.admin.dao.AdminSystemConfigDao;
import com.xx.admin.service.AdminSystemConfigService;

@Service
public class AdminSystemConfigServiceImpl implements AdminSystemConfigService {

	@Resource
	private AdminSystemConfigDao adminSystemConfigDao;
	
	@Override
	public String getValue(String configKey) {
		return adminSystemConfigDao.getValue(configKey);
	}
	
}
