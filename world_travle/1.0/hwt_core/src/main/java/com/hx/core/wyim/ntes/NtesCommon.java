package com.hx.core.wyim.ntes;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.store.SleepingLockWrapper;
import org.springframework.scheduling.annotation.Async;

import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.GsonUtil;
import com.hx.core.wyim.entity.FriendOperationNotice;
import com.hx.core.wyim.entity.system.SystemNotice;
import com.hx.core.wyim.utils.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RO on 2018/3/7.
 */
public class NtesCommon {

	public static final String APP_KEY = "74f268c4ce0ec424237840e32c3223f0";
	public static final String APP_SECRET = "c1abff9ae9ff";

	// 账号注册到网易云信请求地址
	private static final String NTES_Register = "https://api.netease.im/nimserver/user/create.action";

	// 网易云信更新用户账号和Token信息
	private static final String NTES_Update = "https://api.netease.im/nimserver/user/update.action";

	// 网易云信获取新Token
	private static final String NTES_NewToken = "https://api.netease.im/nimserver/user/refreshToken.action";

	// 禁用账号
	private static final String NTES_BlockUser = "https://api.netease.im/nimserver/user/block.action";

	// 启用账号
	private static final String NTES_UnblockUser = "https://api.netease.im/nimserver/user/unblock.action";

	private static final String NTES_UpdateUinfo = "https://api.netease.im/nimserver/user/updateUinfo.action";

	// 点对点通知
	private static final String NTES_SendAttachMsg = "https://api.netease.im/nimserver/msg/sendAttachMsg.action";
	
	
	private static final String NTES_nimserver = "https://api.netease.im/nimserver/user/unblock.action";
	
	//发送普通消息
	private static final String NTES_sendMsg = "https://api.netease.im/nimserver/msg/sendMsg.action";
	/**
	 * 注册用户到网易云信
	 * 
	 * @param accid
	 *            非空 网易云通信ID，最大长度32字符，必须保证一个 APP内唯一（只允许字母、数字、
	 *            半角下划线_、 @、半角点以及半角-组成，不区分大小写， 会统一小写处理，请注意以此接口返回结果中的accid为准）。
	 * @param name
	 *            网易云通信ID昵称，最大长度64字符，用来PUSH推送时显示的昵称
	 * @param props
	 *            json属性，第三方可选填，最大长度1024字符
	 * @param icon
	 *            网易云通信ID头像URL，第三方可选填，最大长度1024
	 * @param token
	 *            网易云通信ID可以指定登录token值，最大长度128字符， 并更新，如果未指定，会自动生成token，并在 创建成功后返回
	 * @param sign
	 *            用户签名，最大长度256字符
	 * @param email
	 *            用户email，最大长度64字符
	 * @param birth
	 *            用户生日，最大长度16字符
	 * @param mobile
	 *            用户mobile，最大长度32字符
	 * @param gender
	 *            用户性别，0表示未知，1表示男，2女表示女，其它会报参数错误
	 * @param ex
	 *            用户名片扩展字段，最大长度1024字符，用户可自行扩展，建议封装成JSON字符串
	 * @return
	 * @throws Exception
	 */
	public static String regiest(String accid, String name, String props, String icon, String token, String sign,
			String email, String birth, String mobile, Integer gender, String ex) throws Exception {
		Map<String, String> map = new HashMap();
		map.put("accid", accid);
		map.put("name", name);
		map.put("props", props);
		map.put("icon", icon);
		map.put("token", token);
		map.put("sign", sign);
		map.put("email", email);
		map.put("birth", birth);
		map.put("mobile", mobile);
		map.put("gender", gender + "");
		map.put("ex", ex);
		return HttpClientUtil.postHttp(NTES_Register, map);
	}
	
	
	
	public static String nimserver(String accid) throws Exception{
		Map<String, String> map = new HashMap();
		map.put("accid", accid);
		return HttpClientUtil.postHttp(NTES_nimserver, map);
		
	}

	/**
	 * 更新用户ID TOKEN信息
	 * 
	 * @param accid
	 *            非空 网易云通信ID，最大长度32字符，必须保证一个APP内唯一
	 * @param props
	 *            json属性，第三方可选填，最大长度1024字符
	 * @param token
	 *            网易云通信ID可以指定登录token值，最大长度128字符
	 * @return
	 */
	public static String updateImUser(String accid, String props, String token) throws Exception {
		Map<String, String> map = new HashMap();
		map.put("accid", accid);
		map.put("token", token);
		map.put("props", props);
		return HttpClientUtil.postHttp(NTES_Update, map);
	}

	/**
	 * 重新获取新TOKEN
	 * 
	 * @param accid
	 *            非空 网易云通信ID，最大长度32字符，必须保证一个APP内唯一
	 * @return { "code":200, "info":{"token":"xxx","accid":"xx" } }
	 */
	public static String getNewToken(String accid) throws Exception {
		Map<String, String> map = new HashMap();
		map.put("accid", accid);
		return HttpClientUtil.postHttp(NTES_NewToken, map);
	}

	/**
	 * 禁用IM账号
	 * 
	 * @param accid
	 *            非空 网易云通信ID，最大长度32字符，必须保证一个APP内唯一
	 * @param needkick
	 *            是否踢掉被禁用户
	 * @return
	 */
	public static String blockUser(String accid, boolean needkick) throws Exception {
		Map<String, String> map = new HashMap();
		map.put("accid", accid);
		map.put("needkick", needkick ? "true" : "false");
		return HttpClientUtil.postHttp(NTES_BlockUser, map);
	}

	/**
	 * 启用IM账号
	 * 
	 * @param accid
	 *            非空 网易云通信ID，最大长度32字符，必须保证一个APP内唯一
	 * @return
	 */
	public static String unblockUser(String accid) throws Exception {
		Map<String, String> map = new HashMap();
		map.put("accid", accid);
		return HttpClientUtil.postHttp(NTES_UnblockUser, map);
	}

	/**
	 * 点对点发送自定义系统通知
	 * 
	 * @param from
	 *            - Y 发送者accid，用户帐号，最大32字符，APP内唯一
	 * @param to
	 *            - Y msgtype==0是表示accid即用户id，msgtype==1表示tid即群id
	 * @param attach
	 *            - Y 自定义通知内容，第三方组装的字符串，建议是JSON串，最大长度4096字符
	 * @param pushcontent
	 *            - N iOS推送内容，第三方自己组装的推送内容,不超过150字符
	 * @param payload
	 *            - N iOS推送对应的payload,必须是JSON,不能超过2k字符
	 * @param sound
	 *            - N 如果有指定推送，此属性指定为客户端本地的声音文件名，长度不要超过30个字符，如果不指定，会使用默认声音
	 * @param save
	 *            - N 1表示只发在线，2表示会存离线，其他会报414错误。默认会存离线
	 * @param option
	 *            - N 发消息时特殊指定的行为选项,Json格式，可用于指定消息计数等特殊行为;option中字段不填时表示默认值。
	 * @return
	 */
	public static String sendAttachMsg(String from, String to, String attach, String pushcontent, String payload,
			String sound, Integer save, String option) throws Exception {
		Map<String, String> map = new HashMap();
		map.put("from", from);
		map.put("msgtype", "0");
		map.put("to", to);
		map.put("attach", attach);
		map.put("pushcontent", pushcontent);
		map.put("payload", payload);
		map.put("sound", sound);
		map.put("save", save + "");
		map.put("option", option);
		return HttpClientUtil.postHttp(NTES_SendAttachMsg, map);
	}

	public static void main(String[] args) throws Exception {
		//SystemNotice systemNotice = new SystemNotice(title, description, url, type, System.currentTimeMillis(), actualId, aboutBills);
		for (int i = 0; i < 7; i++) {
			
			NtesCommon.sendAttachMsg(HXSystemConfig.HX_OFFICIAL_IM_ID, "tgthfjwbttziflxgghjxqfozkjhmeqme",
					GsonUtil.toJson(new FriendOperationNotice("cc10691db22144938855491bcc580bf1", 1, null)), null, null,
					null, 2, null);
		}
		
		
	}

	/**
	 * 更新用户信息
	 * 
	 * @param accid
	 *            非空 网易云通信ID，最大长度32字符，必须保证一个 APP内唯一（只允许字母、数字、
	 *            半角下划线_、 @、半角点以及半角-组成，不区分大小写， 会统一小写处理，请注意以此接口返回结果中的accid为准）。
	 * @param name
	 *            网易云通信ID昵称，最大长度64字符，用来PUSH推送时显示的昵称
	 * @param icon
	 *            网易云通信ID头像URL，第三方可选填，最大长度1024
	 * @param sign
	 *            用户签名，最大长度256字符
	 * @param email
	 *            用户email，最大长度64字符
	 * @param birth
	 *            用户生日，最大长度16字符
	 * @param mobile
	 *            用户mobile，最大长度32字符
	 * @param gender
	 *            用户性别，0表示未知，1表示男，2女表示女，其它会报参数错误
	 * @param ex
	 *            用户名片扩展字段，最大长度1024字符，用户可自行扩展，建议封装成JSON字符串
	 * @return
	 * @throws Exception
	 */
	public static String updateUinfo(String accid, String name, String icon, String sign, String email, String birth,
			String mobile, Integer gender, String ex) throws Exception {

		Map<String, String> map = new HashMap();

		map.put("accid", accid);
		map.put("name", name);
		map.put("icon", icon);
		map.put("sign", sign);
		map.put("email", email);
		map.put("birth", birth);
		map.put("mobile", mobile);
		map.put("gender", gender + "");
		map.put("ex", ex);

		return HttpClientUtil.postHttp(NTES_UpdateUinfo, map);
	}
	
	/**
	 * 发送普通消息
	 * @param from				必须	发送者accid，用户帐号，最大32字符，必须保证一个APP内唯一 	
	 * @param ope				必须 	0：点对点个人消息，1：群消息（高级群），其他返回414
	 * @param to				必须	ope==0是表示accid即用户id，ope==1表示tid即群id
	 * @param type				必须 	0 表示文本消息,1 表示图片，2 表示语音，3 表示视频，4 表示地理位置信息，6 表示文件，100 自定义消息类型（特别注意，对于未对接易盾反垃圾功能的应用，该类型的消息不会提交反垃圾系统检测）
	 * @param body				必须	请参考下方消息示例说明中对应消息的body字段，最大长度5000字符，为一个JSON串
	 * @param antispam			否	对于对接了易盾反垃圾功能的应用，本消息是否需要指定经由易盾检测的内容（antispamCustom）。true或false, 默认false。只对消息类型为：100 自定义消息类型 的消息生效。
	 * @param antispamCustom	否	在antispam参数为true时生效。自定义的反垃圾检测内容, JSON格式，长度限制同body字段，不能超过5000字符，要求antispamCustom格式如下：{"type":1,"data":"custom content"}字段说明：1. type: 1：文本，2：图片。2. data: 文本内容or图片地址。  	
	 * @param option			否	发消息时特殊指定的行为选项,JSON格式，可用于指定消息的漫游，存云端历史，发送方多端同步，推送，消息抄送等特殊行为;option中字段不填时表示默认值 ，option示例:

									{"push":false,"roam":true,"history":false,"sendersync":true,"route":false,"badge":false,"needPushNick":true}
									
									字段说明：
									1. roam: 该消息是否需要漫游，默认true（需要app开通漫游消息功能）； 
									2. history: 该消息是否存云端历史，默认true；
									 3. sendersync: 该消息是否需要发送方多端同步，默认true；
									 4. push: 该消息是否需要APNS推送或安卓系统通知栏推送，默认true；
									 5. route: 该消息是否需要抄送第三方；默认true (需要app开通消息抄送功能);
									 6. badge:该消息是否需要计入到未读计数中，默认true;
									7. needPushNick: 推送文案是否需要带上昵称，不设置该参数时默认true;
									8. persistent: 是否需要存离线消息，不设置该参数时默认true。
	 * @param pushcontent		否 	ios推送内容，不超过150字符，option选项中允许推送（push=true），此字段可以指定推送内容
	 * @param payload			否	ios 推送对应的payload,必须是JSON,不能超过2k字符
	 * @param ext				否	开发者扩展字段，长度限制1024字符
	 * @param forcepushlist		否	发送群消息时的强推（@操作）用户列表，格式为JSONArray，如["accid1","accid2"]。若forcepushall为true，则forcepushlist为除发送者外的所有有效群成员
	 * @param forcepushcontent	否	发送群消息时，针对强推（@操作）列表forcepushlist中的用户，强制推送的内容
	 * @param forcepushall		否 	发送群消息时，强推（@操作）列表是否为群里除发送者外的所有有效成员，true或false，默认为false
	 * @param bid				否	可选，反垃圾业务ID，实现“单条消息配置对应反垃圾”，若不填则使用原来的反垃圾配置
	 * @param useYidun			否	可选，单条消息是否使用易盾反垃圾，可选值为0。 
									0：（在开通易盾的情况下）不使用易盾反垃圾而是使用通用反垃圾，包括自定义消息。
									
									若不填此字段，即在默认情况下，若应用开通了易盾反垃圾功能，则使用易盾反垃圾来进行垃圾消息的判断
	 * @param markRead			否	可选，群消息是否需要已读业务（仅对群消息有效），0:不需要，1:需要
	 * @return
	 * @throws Exception 
	 */
	public static String sendMsg(String from,String ope,String to,Integer type,String body,Boolean antispam,String antispamCustom,
			String option,String pushcontent,String payload,String ext,String forcepushlist,String forcepushcontent,
			String forcepushall,String bid,Integer useYidun,Integer markRead) throws Exception{
		Map<String, String> map = new HashMap<>();

		map.put("from", from);
		map.put("ope", ope);
		map.put("to", to);
		map.put("type", type+"");
		map.put("body", body);
//		map.put("antispam", antispam+"");
//		map.put("antispamCustom", antispamCustom);
//		map.put("option", option + "");
//		map.put("pushcontent", pushcontent);
//		map.put("payload", payload);
//		map.put("ext", ext);
//		map.put("forcepushlist", forcepushlist);
//		map.put("forcepushcontent", forcepushcontent);
//		map.put("forcepushall", forcepushall);
//		map.put("bid", bid);
//		map.put("useYidun", useYidun+"");
//		map.put("markRead", markRead+"");

		return HttpClientUtil.postHttp(NTES_sendMsg, map);
		
	}
	

}
