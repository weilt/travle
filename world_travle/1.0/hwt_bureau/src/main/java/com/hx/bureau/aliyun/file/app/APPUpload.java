package com.hx.bureau.aliyun.file.app;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class APPUpload {
	private static String EXTERNAL_ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
	private static String BUCKET_NAME = "gwstatic";
	/**
	 * app端上传图片，返回token
	 * @return
	 */
	public static AliyunAPPSTS getToken() {
		
		String endpoint = "sts.aliyuncs.com";
		
		String accessKeyId = "LTAIXglzybm9VOTx";
		String accessKeySecret = "ZggdltvvQd4tpydRAh9TPaZia2uGBK";
		
		String roleArn = "acs:ram::1633913135313498:role/useross"; //指定角色的全局资源描述符
		String roleSessionName = "useross"; //用户自定义参数。此参数用来区分不同的Token，可用于用户级别的访问审计。
		Long durationSeconds = (long) (60*60);//指定的过期时间，单位为秒。过期时间范围：900 ~ 3600，默认值为3600。
		//授权策略Policy，Policy长度限制为1024字节；您可以通过此参数限制生成的Token的权限，不指定则返回的Token将拥有指定角色的所有权限。
		String policy = null;
		
		try {
			// Init ACS Client
			DefaultProfile.addEndpoint("", "", "Sts", endpoint);
			IClientProfile profile = DefaultProfile.getProfile("cn-beijing", accessKeyId, accessKeySecret);
			DefaultAcsClient client = new DefaultAcsClient(profile);
			
			final AssumeRoleRequest request = new AssumeRoleRequest();
			request.setMethod(MethodType.POST);
			request.setRoleArn(roleArn);
			request.setRoleSessionName(roleSessionName);
			request.setPolicy(policy); // Optional
			request.setDurationSeconds(durationSeconds);
			
			
			final AssumeRoleResponse response = client.getAcsResponse(request);
			System.out.println("Expiration: " + response.getCredentials().getExpiration());
			System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
			System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
			System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
			System.out.println("RequestId: " + response.getRequestId());
			return new AliyunAPPSTS(response.getCredentials().getAccessKeyId(), response.getCredentials().getAccessKeySecret(),
					response.getCredentials().getSecurityToken(), response.getRequestId(),
					response.getCredentials().getExpiration(), EXTERNAL_ENDPOINT,BUCKET_NAME);
		} catch (ClientException e) {
			System.out.println("Failed：");
			System.out.println("Error code: " + e.getErrCode());
			System.out.println("Error message: " + e.getErrMsg());
			System.out.println("RequestId: " + e.getRequestId());
		}
		return null;
	}
	
}
