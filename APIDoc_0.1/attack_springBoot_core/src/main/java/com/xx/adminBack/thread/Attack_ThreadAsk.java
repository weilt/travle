package com.xx.adminBack.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.xx.util.util.RequestNOUtil;

@Service
public class Attack_ThreadAsk {
	public static final Logger log = LoggerFactory.getLogger(Attack_ThreadAsk.class);
	/**
	 * 攻击网站信息
	 * @param getOrPost - GET OR POST
	 * @param url - 请求地址
	 * @param number - 攻击次数
	 */
	@Async
	public void Attack_ADD(String getOrPost,String url,Integer number){
		if("GET".equals(getOrPost)){
			for(int i=0;i < number;i++){
				RequestNOUtil.doGetStr(url);
			}
		}else if("POST".equals(getOrPost)){
			for(int i=0;i < number;i++){
				RequestNOUtil.doPostStr(url);
			}
		}
		log.info("单线程请求数据完成 - 总数：" + number);
	}
}
