package com.hx.system.service.hx.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwt.domain.entity.version.vo.HxVersionVo;
import com.hwt.domain.mapper.version.HxVersionMapper;
import com.hx.core.redis.RedisCache;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.JsonUtils;
import com.hx.system.service.hx.VersionService;

@Service
public class VersionServiceImpl implements VersionService{
	
	@Autowired
	private HxVersionMapper hxVersionMapper;

	@Override
	public List<HxVersionVo> verificationVersion() {
		List<HxVersionVo> hxVersionVos = new ArrayList<>();
		HxVersionVo android = getVersion(1);
		hxVersionVos.add(android);
		HxVersionVo ios = getVersion(2);
		hxVersionVos.add(ios);
		return hxVersionVos;
	}
	
	/**
	 * 获取版本
	 * @param android_or_ios 1-安卓 2-ios
	 * @return
	 */
	private HxVersionVo getVersion(int android_or_ios) {
		String string = null;
		if (android_or_ios==1){
			string = RedisCache.get(HXSystemConfig.HX_VSERSION_ANDROID, RedisCache.db15);
			if (StringUtils.isNoneBlank(string)){
				HxVersionVo hxVersionVo = JsonUtils.json2Bean(string, HxVersionVo.class);
				return hxVersionVo;
			}else{
				HxVersionVo hxVersionVo = hxVersionMapper.query_by_android_or_ios_new(android_or_ios);
				RedisCache.setpar(HXSystemConfig.HX_VSERSION_ANDROID, JsonUtils.Bean2Json(hxVersionVo), RedisCache.db15);
				return hxVersionVo;
			}
			
		}else{
			string = RedisCache.get(HXSystemConfig.HX_VSERSION_IOS, RedisCache.db15);
			if (StringUtils.isNoneBlank(string)){
				HxVersionVo hxVersionVo = JsonUtils.json2Bean(string, HxVersionVo.class);
				return hxVersionVo;
			}else{
				HxVersionVo hxVersionVo = hxVersionMapper.query_by_android_or_ios_new(android_or_ios);
				RedisCache.setpar(HXSystemConfig.HX_VSERSION_IOS, JsonUtils.Bean2Json(hxVersionVo), RedisCache.db15);
				return hxVersionVo;
			}
		}
	}
	
	
}
