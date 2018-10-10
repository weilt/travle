package com.common.aliyun.file.app;



public class AliyunAPPSTS {
	
	private String accessKeyId;
	
	private String accessKeySecret;
	
	private String securityToken;
	
	private String requestId;
	
	private String expiration;
	
	private String EXTERNAL_ENDPOINT;
	
	private String BUCKET_NAME;
	
	public AliyunAPPSTS(String accessKeyId, String accessKeySecret, String securityToken, String requestId,
			String expiration, String eXTERNAL_ENDPOINT,String BUCKET_NAME) {
		super();
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.securityToken = securityToken;
		this.requestId = requestId;
		this.expiration = expiration;
		this.EXTERNAL_ENDPOINT = eXTERNAL_ENDPOINT;
		this.BUCKET_NAME = BUCKET_NAME;
	}
	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getAccessKeySecret() {
		return accessKeySecret;
	}
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	public String getSecurityToken() {
		return securityToken;
	}
	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public String getEXTERNAL_ENDPOINT() {
		return EXTERNAL_ENDPOINT;
	}
	public void setEXTERNAL_ENDPOINT(String eXTERNAL_ENDPOINT) {
		EXTERNAL_ENDPOINT = eXTERNAL_ENDPOINT;
	}
	public String getBUCKET_NAME() {
		return BUCKET_NAME;
	}
	public void setBUCKET_NAME(String bUCKET_NAME) {
		BUCKET_NAME = bUCKET_NAME;
	}
	
	
}
