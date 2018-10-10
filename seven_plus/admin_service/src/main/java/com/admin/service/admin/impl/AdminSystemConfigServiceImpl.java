package com.admin.service.admin.impl;

import com.admin.service.admin.AdminSystemConfigService;
import com.domain.admin.entity.AdminSystemconfig;
import com.domain.admin.mapper.AdminSystemConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSystemConfigServiceImpl implements AdminSystemConfigService {

	@Autowired
	private AdminSystemConfigMapper adminSystemConfigMapper;
	
	@Override
	public String getValue(String configKey) {
		return adminSystemConfigMapper.getValue(configKey);
	}


}
