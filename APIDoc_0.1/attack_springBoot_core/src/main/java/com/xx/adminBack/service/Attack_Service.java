package com.xx.adminBack.service;

public interface Attack_Service {

	/**
	 * 攻击的数据信息
	 * @param getOrPost
	 * @param http_s
	 * @param url
	 * @param number - 次数
	 * @param threadNumber - 线程个数
	 */
	void attack_ADD(String getOrPost,String http_s,String url,Integer number,Integer threadNumber);
}
