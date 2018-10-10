package com.hx.admin.file.value;

/**
 * 
 * OSS 的 Bucket
 * 
 * @author lyj
 *
 */
public enum OSSBucketName {

	txhc_test("xlx-test","校联校app正式版本");


	public String value;
	public String describe;

	private OSSBucketName(String value,String describe) {
		this.value = value;
		this.describe = describe;
		
	}

	public String getValue() {
		return value;
	}


	public String getDescribe() {
		return describe;
	}
	
}
