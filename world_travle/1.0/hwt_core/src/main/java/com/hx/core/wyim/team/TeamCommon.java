package com.hx.core.wyim.team;

import java.util.HashMap;
import java.util.Map;

import com.hx.core.wyim.utils.HttpClientUtil;

/**
 * 网易群操作
 * 
 * @author Administrator
 *
 */
public class TeamCommon {

	// 创建群
	private static final String TEAM_Create = "https://api.netease.im/nimserver/team/create.action";

	// 拉人入伙
	private static final String TEAM_Add = "https://api.netease.im/nimserver/team/add.action";

	// 踢人出去
	private static final String TEAM_Kick = "https://api.netease.im/nimserver/team/kick.action";

	// 解散群
	private static final String TEAM_Remove = "https://api.netease.im/nimserver/team/remove.action";

	// 编辑群资料
	private static final String TEAM_Update = "https://api.netease.im/nimserver/team/update.action";
	
	//群信息与成员列表查询
	private static final String TEAM_Query = "https://api.netease.im/nimserver/team/query.action";
	
	//获取群组详细信息
	private static final String TEAM_QueryDetail = "https://api.netease.im/nimserver/team/queryDetail.action";
	
	//获取群组已读消息的已读详情信息
	private static final String TEAM_GetMarkReadInfo = "https://api.netease.im/nimserver/team/getMarkReadInfo.action";
	
	//移交群主
	private static final String TEAM_ChangeOwner = "https://api.netease.im/nimserver/team/changeOwner.action";
	
	//任命管理员
	private static final String TEAM_AddManager = "https://api.netease.im/nimserver/team/addManager.action";
	
	//移除管理员
	private static final String TEAM_RemoveManager = "https://api.netease.im/nimserver/team/removeManager.action";
	
	//获取某用户所加入的群信息
	private static final String TEAM_JoinTeams = "https://api.netease.im/nimserver/team/joinTeams.action";
	
	//修改群昵称
	private static final String TEAM_UpdateTeamNick = "https://api.netease.im/nimserver/team/updateTeamNick.action";
	
	//修改消息提醒开关
	private static final String TEAM_MuteTeam = "https://api.netease.im/nimserver/team/muteTeam.action";
	
	//禁言群成员
	private static final String TEAM_MuteTlist = "https://api.netease.im/nimserver/team/muteTlist.action";

	//禁言群成员
	private static final String TEAM_Leave = "https://api.netease.im/nimserver/team/leave.action";
	
	//将群组整体禁言
	private static final String TEAM_MuteTlistAll = "https://api.netease.im/nimserver/team/muteTlistAll.action";
	
	//获取群组禁言列表
	private static final String TEAM_ListTeamMute = "https://api.netease.im/nimserver/team/listTeamMute.action";

	/**
	 * 描述 创建高级群，以邀请的方式发送给用户； custom 字段是给第三方的扩展字段，第三方可以基于此字段扩展高级群的功能，构建自己需要的群；
	 * 建群成功会返回tid，需要保存，以便于加人与踢人等后续操作； 每个用户可创建的群数量有限制，限制值由 IM
	 * 套餐的群组配置决定，可登录管理后台查看。</br>
	 * </br>
	 * </br>
	 * 
	 * 
	 * 群创建
	 * 
	 * @param tname
	 *            是 群名称，最大长度64字符
	 * @param owner
	 *            是 群主用户帐号，最大长度32字符
	 * @param members
	 *            是 ["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，一次最多拉200个成员
	 * @param announcement
	 *            否 群公告，最大长度1024字符
	 * @param intro
	 *            否 群描述，最大长度512字符
	 * @param msg
	 *            是 邀请发送的文字，最大长度150字符
	 * @param magree
	 *            是 管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
	 * @param joinmode
	 *            是 群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414
	 * @param custom
	 *            否 自定义高级群扩展属性，第三方可以跟据此属性自定义扩展自己的群属性。（建议为json）,最大长度1024字符
	 * @param icon
	 *            否 群头像，最大长度1024字符
	 * @param beinvitemode
	 *            否 被邀请人同意方式，0-需要同意(默认),1-不需要同意。其它返回414
	 * @param invitemode
	 *            否 谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414
	 * @param uptinfomode
	 *            否 谁可以修改群资料，0-管理员(默认),1-所有人。其它返回414
	 * @param upcustommode
	 *            否 谁可以更新群自定义属性，0-管理员(默认),1-所有人。其它返回414
	 * @return
	 * @throws Exception
	 */
	public static String create(String tname, String owner, String members, String announcement, String intro,
			String msg, int magree, int joinmode, String custom, String icon, int beinvitemode, int invitemode,
			int uptinfomode, int upcustommode) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		map.put("tname", tname);
		map.put("owner", owner);
		map.put("members", members);
		map.put("announcement", announcement);
		map.put("intro", intro);
		map.put("msg", msg);
		map.put("magree", magree + "");
		map.put("joinmode", joinmode + "");
		map.put("custom", custom);
		map.put("icon", icon + "");
		map.put("beinvitemode", beinvitemode + "");
		map.put("invitemode", invitemode + "");
		map.put("uptinfomode", uptinfomode + "");
		map.put("upcustommode", upcustommode + "");

		return HttpClientUtil.postHttp(TEAM_Create, map);

	}

	/**
	 * 描述 ---1.可以批量邀请，邀请时需指定群主；2.当群成员达到上限时，再邀请某人入群返回失败；3.当群成员达到上限时，被邀请人“接受邀请"
	 * 的操作也将返回失败 </br>
	 * </br>
	 * </br>
	 * 
	 * 拉人入伙
	 * 
	 * @param tid
	 *            是 网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
	 * @param owner
	 *            是 群主用户帐号，最大长度32字符
	 * @param members
	 *            是 ["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，一次最多拉200个成员
	 * @param magree
	 *            是 管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
	 * @param msg
	 *            是 邀请发送的文字，最大长度150字符
	 * @param attach
	 *            否 自定义扩展字段，最大长度512
	 * @return
	 * @throws Exception
	 */
	public static String add(String tid, String owner, String members, int magree, String msg, String attach)
			throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("owner", owner);
		map.put("members", members);
		map.put("magree", magree + "");
		map.put("msg", msg);
		map.put("attach", attach + "");

		return HttpClientUtil.postHttp(TEAM_Add, map);
	}

	/**
	 * 描述 高级群踢人出群，需要提供群主accid以及要踢除人的accid。 </br>
	 * </br>
	 * </br>
	 * 
	 * 踢人出群
	 * 
	 * @param tid
	 *            是 网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
	 * @param owner
	 *            是 群主的accid，用户帐号，最大长度32字符
	 * @param member
	 *            是 被移除人的accid，用户账号，最大长度字符
	 * @param attach
	 *            否 自定义扩展字段，最大长度512
	 * @return
	 * @throws Exception
	 */
	public static String kick(String tid, String owner, String member, String attach) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("owner", owner);
		map.put("member", member);
		map.put("attach", attach + "");

		return HttpClientUtil.postHttp(TEAM_Kick, map);
	}

	/**
	 * 描述 删除整个群，会解散该群，需要提供群主accid，谨慎操作！参数说明 </br>
	 * </br>
	 * </br>
	 * 删除整个群
	 * 
	 * @param tid
	 *            是 网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
	 * @param owner
	 *            是 群主用户帐号，最大长度32字符
	 * @return
	 * @throws Exception
	 */
	public static String remove(String tid, String owner) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("owner", owner);

		return HttpClientUtil.postHttp(TEAM_Remove, map);
	}

	/**
	 * 描述 
	 * 		高级群基本信息修改  </br>
	 * </br>
	 * </br>
	 * 
	 * 编辑群资料
	 * @param tid 			是	网易云通信服务器产生，群唯一标识，创建群时会返回
	 * @param tname			否	群名称，最大长度64字符
	 * @param owner			是	群主用户帐号，最大长度32字符
	 * @param announcement	否	群公告，最大长度1024字符
	 * @param intro			否	群描述，最大长度512字符
	 * @param joinmode		否	群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414
	 * @param custom		否	自定义高级群扩展属性，第三方可以跟据此属性自定义扩展自己的群属性。（建议为json）,最大长度1024字符
	 * @param icon			否	群头像，最大长度1024字符
	 * @param beinvitemode	否	被邀请人同意方式，0-需要同意(默认),1-不需要同意。其它返回414
	 * @param invitemode	否	谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414
	 * @param uptinfomode	否	谁可以修改群资料，0-管理员(默认),1-所有人。其它返回414
	 * @param upcustommode	否	谁可以更新群自定义属性，0-管理员(默认),1-所有人。其它返回414
	 * @return
	 * @throws Exception
	 */
	public static String update(String tid, String tname, String owner, String announcement, String intro, int joinmode,
			String custom, String icon , int beinvitemode ,int invitemode, int uptinfomode, int upcustommode) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("tname", tname);
		map.put("owner", owner);
		map.put("announcement", announcement);
		map.put("intro", intro);
		map.put("joinmode", joinmode +"");
		map.put("custom", custom);
		map.put("icon", icon);
		map.put("beinvitemode", beinvitemode+"");
		map.put("invitemode", invitemode+"");
		map.put("uptinfomode", uptinfomode+"");
		map.put("upcustommode", upcustommode+"");

		return HttpClientUtil.postHttp(TEAM_Update, map);
	}
	
	/**
	 * 描述
	 * 		</br>接口描述
	 * 		</br>高级群信息与成员列表查询，一次最多查询30个群相关的信息，跟据ope参数来控制是否带上群成员列表；
	 * 		</br>查询群成员会稍微慢一些，所以如果不需要群成员列表可以只查群信息；
	 * 		</br>此接口受频率控制，某个应用一分钟最多查询30次，超过会返回416，并且被屏蔽一段时间；
	 *		</br> 群成员的群列表信息中增加管理员成员admins的返回。</br></br></br>
	 * 群信息与成员列表查询
	 * @param tids		是	群id列表，如["3083","3084"]
	 * @param ope		是	1表示带上群成员列表，0表示不带群成员列表，只返回群信息
	 * @return
	 * @throws Exception
	 */
	public static String query(String tids, String ope) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tids", tids);
		map.put("ope", ope);
		
		return HttpClientUtil.postHttp(TEAM_Query, map);
	}
	
	
	/**
	 * 查询指定群的详细信息（群信息+成员详细信息）
	 * @param tid		是	群id，群唯一标识，创建群时会返回
	 * @return
	 * @throws Exception
	 */
	public static String queryDetail(String tid) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		
		return HttpClientUtil.postHttp(TEAM_QueryDetail, map);
	}
	
	/**
	 * 
	 * 获取群组已读消息的已读详情信息
	 * @param tid  			是	群id，群唯一标识，创建群时会返回
	 * @param msgid			是	发送群已读业务消息时服务器返回的消息ID
	 * @param fromAccid		是	消息发送者账号
	 * @param snapshot		否	是否返回已读、未读成员的accid列表，默认为false
	 * @return
	 * @throws Exception
	 */
	public static String getMarkReadInfo(String tid,String msgid, String fromAccid, Boolean snapshot) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("msgid", msgid);
		map.put("fromAccid", fromAccid);
		map.put("snapshot", snapshot+"");
		
		return HttpClientUtil.postHttp(TEAM_GetMarkReadInfo, map);
	}
	
	/**
	 * 
	 * 移交群主
	 * @param tid			是	网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
	 * @param owner			是	群主用户帐号，最大长度32字符
	 * @param newowner		是	新群主帐号，最大长度32字符
	 * @param leave			是	1:群主解除群主后离开群，2：群主解除群主后成为普通成员。其它414
	 * @return
	 * @throws Exception
	 */
	public static String changeOwner(String tid,String owner, String newowner, int leave) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("owner", owner);
		map.put("newowner", newowner);
		map.put("leave", leave+"");
		
		return HttpClientUtil.postHttp(TEAM_ChangeOwner, map);
	}
	
	
	/**
	 * 任命管理员
	 * @param tid		是	网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
	 * @param owner		是	群主用户帐号，最大长度32字符
	 * @param members	是	["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，长度最大1024字符（一次添加最多10个管理员）
	 * @return
	 * @throws Exception
	 */
	public static String addManager(String tid,String owner, String members) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("owner", owner);
		map.put("members", members);
		
		return HttpClientUtil.postHttp(TEAM_AddManager, map);
	}
	/**
	 * 移除管理员
	 * @param tid		是	网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
	 * @param owner		是	群主用户帐号，最大长度32字符
	 * @param members	是	["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，长度最大1024字符（一次解除最多10个管理员）
	 * @return
	 * @throws Exception
	 */
	public static String removeManager(String tid,String owner, String members) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("owner", owner);
		map.put("members", members);
		
		return HttpClientUtil.postHttp(TEAM_RemoveManager, map);
	}
	
	/**
	 * 获取某用户所加入的群信息
	 * @param accid			是	要查询用户的accid
	 * @return
	 * @throws Exception
	 */
	public static String joinTeams(String accid) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("accid", accid);
		
		return HttpClientUtil.postHttp(TEAM_JoinTeams, map);
	}
	
	/**
	 * 
	 * 修改指定账号在群内的昵称
	 * @param tid			是	群唯一标识，创建群时网易云通信服务器产生并返回
	 * @param owner			是	群主 accid
	 * @param accid			是	要修改群昵称的群成员 accid
	 * @param nick			是	accid 对应的群昵称，最大长度32字符
	 * @param custom		否	自定义扩展字段，最大长度1024字节
	 * @return
	 * @throws Exception
	 */
	public static String updateTeamNick(String tid, String owner, String accid, String nick, String custom) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("owner", owner);
		map.put("accid", accid);
		map.put("nick", nick);
		map.put("custom", custom);
		
		return HttpClientUtil.postHttp(TEAM_UpdateTeamNick, map);
	}
	
	/**
	 * 修改消息提醒开关
	 * @param tid			是	网易云通信服务器产生，群唯一标识，创建群时会返回
	 * @param accid			是	要操作的群成员accid
	 * @param ope			是	1：关闭消息提醒，2：打开消息提醒，其他值无效
	 * @return
	 * @throws Exception
	 */
	public static String muteTeam(String tid, String accid, int ope) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("accid", accid);
		map.put("ope", ope+"");
		
		return HttpClientUtil.postHttp(TEAM_MuteTeam, map);
	}
	
	/**
	 * 禁言群成员
	 * @param tid		是	网易云通信服务器产生，群唯一标识，创建群时会返回
	 * @param owner		是	群主accid
	 * @param accid		是	禁言对象的accid
	 * @param mute		是	1-禁言，0-解禁
	 * @return
	 * @throws Exception
	 */
	public static String muteTlist(String tid,String owner ,String accid, int mute) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("owner", owner);
		map.put("accid", accid);
		map.put("mute", mute+"");
		
		return HttpClientUtil.postHttp(TEAM_MuteTlist, map);
	}
	
	
	/**
	 * 主动退群
	 * @param tid		是	网易云通信服务器产生，群唯一标识，创建群时会返回
	 * @param accid		是	退群的accid
	 * @return
	 * @throws Exception
	 */
	public static String leave(String tid ,String accid) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("accid", accid);
		
		return HttpClientUtil.postHttp(TEAM_Leave, map);
	}
	
	/**
	 * 将群组整体禁言
	 * @param tid		是	网易云通信服务器产生，群唯一标识，创建群时会返回
	 * @param owner		是	群主的accid
	 * @param mute		否	true:禁言，false:解禁(mute和muteType至少提供一个，都提供时按mute处理)
	 * @param muteType	否	禁言类型 0:解除禁言，1:禁言普通成员 3:禁言整个群(包括群主)
	 * @return
	 * @throws Exception
	 */
	public static String muteTlistAll(String tid ,String owner ,String mute , int muteType) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("owner", owner);
		map.put("mute", mute);
		map.put("muteType", muteType+"");
		
		return HttpClientUtil.postHttp(TEAM_MuteTlistAll, map);
	}
	
	/**
	 * 
	 * 获取群组禁言的成员列表
	 * @param tid		是	网易云通信服务器产生，群唯一标识，创建群时会返回
	 * @param owner		是	群主的accid
	 * @return
	 * @throws Exception
	 */
	public static String listTeamMute(String tid ,String owner ) throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tid", tid);
		map.put("owner", owner);
		
		return HttpClientUtil.postHttp(TEAM_ListTeamMute, map);
	}
	
	
}
