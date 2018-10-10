package com.common.vo;

import com.common.redis.RedisCache;
import com.common.util.ObjectHelper;
import com.google.gson.Gson;

/**
 * 获取 - 用户登录信息 - 验证登录状态
 * @author JIAO_LIU_BABA
 */
public class LoginRedisVo {
	
	/**
	 * 获取登录信息 - 验证
	 * @param token
	 * @return
	 */
	public static LoginVo find(String token) {
		LoginVo loginVo = null;
		if(ObjectHelper.isNotEmpty(token)) {
			String[] str = token.split("_");
			if(str.length == 2) {
				String redisStr = RedisCache.get(str[0].trim());
				if(ObjectHelper.isNotEmpty(redisStr)) {
					loginVo = new Gson().fromJson(redisStr, LoginVo.class);
					if(ObjectHelper.isNotEmpty(loginVo)) {
						if(!(loginVo.getVerToken().trim()).equals(str[1].trim())) {
							loginVo = null;
						}
					}
				}
			}
		}
		return loginVo;
	}
	
	
	
}
