package com.hwt.domain.entity.user.Vo;

import java.util.Map;

/**
 * 请求网易 返回的token 结构
 * @author JIAO_LIU_BABA
 */

public class WangYiToken {

	private int code;
	
	private Map<String, String> info;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Map<String, String> getInfo() {
		return info;
	}

	public void setInfo(Map<String, String> info) {
		this.info = info;
	}
	
	
}
