package com.hx.system.service.adminHx.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwt.domain.entity.user.Vo.adminHxVo.HxUserReportVo;
import com.hwt.domain.entity.version.HxVersion;
import com.hwt.domain.mapper.version.HxVersionMapper;
import com.hx.core.page.asyn.PageResult;
import com.hx.core.redis.RedisCache;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.JsonUtils;
import com.hx.core.utils.ObjectHelper;
import com.hx.system.service.adminHx.service.AdminHxVersionService;

@Service
public class AdminHxVersionServiceImpl implements AdminHxVersionService{
	
	@Autowired
	private HxVersionMapper hxVersionMapper;

	@Override
	public PageResult<HxVersion> queryByMap(Map<String, Object> map) {
		long count = hxVersionMapper.queryHxVersionCountByMap(map);
		
		List<HxVersion> list = hxVersionMapper.queryHxVersionByMap(map);
		
		PageResult<HxVersion> pageResult = new PageResult<HxVersion>();
		
		pageResult.setCount(count);
		pageResult.setDataList(list);
		return pageResult;
	}

	@Override
	public void insert(HxVersion hxVersion) {
		hxVersion.setCreate_time(new Date());
		Long version_number = hxVersionMapper.get_max_version_number(hxVersion.getAndroid_or_ios());
		if (version_number!=null&&version_number >= hxVersion.getVersion_number()){
			throw new RuntimeException("新添加的版本号必须必之前的大，之前的最大" + version_number);
		}
		hxVersionMapper.insertVersion(hxVersion);
		//将版本号存入内存
		if(hxVersion.getAndroid_or_ios()==1){
			
			RedisCache.setpar(HXSystemConfig.HX_VSERSION_ANDROID, JsonUtils.Bean2Json(hxVersion), RedisCache.db15);
			
		}else{
			
			RedisCache.setpar(HXSystemConfig.HX_VSERSION_IOS, JsonUtils.Bean2Json(hxVersion), RedisCache.db15);
		}
		
		
	}

	@Override
	public HxVersion queryById(Long id) {
		HxVersion version = hxVersionMapper.queryById(id);
		
		return version;
	}

	@Override
	public void update(HxVersion hxVersion) {
		hxVersion.setIs_not_minimum(null);
		hxVersion.setMinimun(null);
		hxVersion.setVersion_number(null);
		hxVersionMapper.updateVersion(hxVersion);
		
	}

}
