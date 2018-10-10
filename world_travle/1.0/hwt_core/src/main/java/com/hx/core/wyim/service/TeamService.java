package com.hx.core.wyim.service;

import com.hx.core.wyim.entity.TeamUser;

/**
 * 群业务接口
 * @author Administrator
 *
 */
public interface TeamService {
	/**
	 * 创建群
	 * @param teamUser
	 * @return
	 * @throws Exception
	 */
	public String create(TeamUser teamUser) throws Exception;
	
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
	public String add(String tid, String owner, String members, int magree, String msg, String attach) throws Exception;
	
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
	public String kick(String tid, String owner, String member, String attach) throws Exception;
	
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
	public String remove(String tid, String owner) throws Exception;
	
	/**
	 * 编辑群资料
	 * @param teamUser
	 * @return
	 * @throws Exception
	 */
	public String update(TeamUser teamUser) throws Exception;
	
	/**
	 * 群信息与成员列表查询
	 * @param tids		是	群id列表，如["3083","3084"]
	 * @param ope		是	1表示带上群成员列表，0表示不带群成员列表，只返回群信息
	 * @return
	 * @throws Exception
	 */
	public String query(String tids, String ope) throws Exception;
	
	
	/**
	 * 查询指定群的详细信息（群信息+成员详细信息）
	 * @param tid		是	群id，群唯一标识，创建群时会返回
	 * @return
	 * @throws Exception
	 */
	public String queryDetail(String tid) throws Exception;
	
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
	public String getMarkReadInfo(String tid,String msgid, String fromAccid, Boolean snapshot) throws Exception;
	
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
	public String changeOwner(String tid,String owner, String newowner, int leave) throws Exception;
	
	
	/**
	 * 任命管理员
	 * @param tid		是	网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
	 * @param owner		是	群主用户帐号，最大长度32字符
	 * @param members	是	["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，长度最大1024字符（一次添加最多10个管理员）
	 * @return
	 * @throws Exception
	 */
	public String addManager(String tid,String owner, String members) throws Exception;
	
	/**
	 * 移除管理员
	 * @param tid		是	网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
	 * @param owner		是	群主用户帐号，最大长度32字符
	 * @param members	是	["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，长度最大1024字符（一次解除最多10个管理员）
	 * @return
	 * @throws Exception
	 */
	public String removeManager(String tid,String owner, String members) throws Exception;

	/**
	 * 获取某用户所加入的群信息
	 * @param accid			是	要查询用户的accid
	 * @return
	 * @throws Exception
	 */
	public String joinTeams(String accid) throws Exception;
	
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
	public String updateTeamNick(String tid, String owner, String accid, String nick, String custom) throws Exception;
	
	/**
	 * 修改消息提醒开关
	 * @param tid			是	网易云通信服务器产生，群唯一标识，创建群时会返回
	 * @param accid			是	要操作的群成员accid
	 * @param ope			是	1：关闭消息提醒，2：打开消息提醒，其他值无效
	 * @return
	 * @throws Exception
	 */
	public String muteTeam(String tid, String accid, int ope) throws Exception;
	
	
	/**
	 * 禁言群成员
	 * @param tid		是	网易云通信服务器产生，群唯一标识，创建群时会返回
	 * @param owner		是	群主accid
	 * @param accid		是	禁言对象的accid
	 * @param mute		是	1-禁言，0-解禁
	 * @return
	 * @throws Exception
	 */
	public String muteTlist(String tid,String owner ,String accid, int mute) throws Exception;
	
	
	/**
	 * 主动退群
	 * @param tid		是	网易云通信服务器产生，群唯一标识，创建群时会返回
	 * @param accid		是	退群的accid
	 * @return
	 * @throws Exception
	 */
	public String leave(String tid ,String accid) throws Exception ;
	
	/**
	 * 将群组整体禁言
	 * @param tid		是	网易云通信服务器产生，群唯一标识，创建群时会返回
	 * @param owner		是	群主的accid
	 * @param mute		否	true:禁言，false:解禁(mute和muteType至少提供一个，都提供时按mute处理)
	 * @param muteType	否	禁言类型 0:解除禁言，1:禁言普通成员 3:禁言整个群(包括群主)
	 * @return
	 * @throws Exception
	 */
	public String muteTlistAll(String tid ,String owner ,String mute , int muteType) throws Exception;
	
	/**
	 * 
	 * 获取群组禁言的成员列表
	 * @param tid		是	网易云通信服务器产生，群唯一标识，创建群时会返回
	 * @param owner		是	群主的accid
	 * @return
	 * @throws Exception
	 */
	public String listTeamMute(String tid ,String owner ) throws Exception;
}
