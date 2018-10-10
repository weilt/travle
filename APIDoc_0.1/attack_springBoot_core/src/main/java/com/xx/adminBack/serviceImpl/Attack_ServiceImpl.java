package com.xx.adminBack.serviceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.adminBack.service.Attack_Service;
import com.xx.adminBack.thread.Attack_ThreadAsk;
import com.xx.springBootUtil.excption.BaseException;
import com.xx.springBootUtil.util.ObjectHelper;
import com.xx.springBootUtil.validate.URLAvailability;

@Service
public class Attack_ServiceImpl implements Attack_Service {

	public static final Logger log = LoggerFactory.getLogger(Attack_ServiceImpl.class);
	@Autowired
	private Attack_ThreadAsk ask;
	
	public void attack_ADD(String getOrPost, String http_s, String url,Integer number,Integer threadNumber) {
		if(!getOrPost.equals("GET") && !getOrPost.equals("POST")){
			throw new BaseException("请选择POST或者GET请求");
		}
		if(ObjectHelper.isEmpty(number)){
			number = 100; //默认攻击100次
		}
		if(ObjectHelper.isEmpty(threadNumber)){
			threadNumber = 5; //默认线程次数5次
		}
		if(ObjectHelper.isNotEmpty(http_s) &&
				!("http://").equals(http_s) && !("https://").equals(http_s)){
			throw new BaseException("http请求请选择http://或者https://");
		}
		if(ObjectHelper.isEmpty(http_s)){
			if(!url.contains("http")){
				throw new BaseException("url地址开头为http://或者https://");
			}
		}else{
			if(url.contains("https://") && http_s.equals("http://")){
				url = url.replace("https://", http_s);
			}else if(url.contains("http://") && http_s.equals("https://")){
				url = url.replace("http://", http_s);
			}
		}
		if(!URLAvailability.exists(url)){
			throw new BaseException("请输入有效的url地址");
		}
		
		for(int i = 0;i < threadNumber; i++){
			ask.Attack_ADD(getOrPost,url, number);
		}
		log.info("线程创建完成 - 总数：" + threadNumber);
	}

}
