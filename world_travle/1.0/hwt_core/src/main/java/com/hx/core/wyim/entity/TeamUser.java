package com.hx.core.wyim.entity;

import java.io.Serializable;

/**
 * 网易群对象
 * @author Administrator
 *
 */
public class TeamUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 网易云通信服务器产生，群唯一标识，创建群时会返回
	 */
	private String tid;
	
	/**
	 * 群名称，最大长度64字符
	 */
	private String tname;
	/**
	 * 群主用户帐号，最大长度32字符
	 */
	private String owner;
	/**
	 * ["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，一次最多拉200个成员
	 */
	private String members;
	/**
	 * 群公告，最大长度1024字符
	 */
	private String announcement;
	
	/**
	 * 群描述，最大长度512字符
	 */
	private String intro;
	
	/**
	 * 邀请发送的文字，最大长度150字符
	 */
	private String msg;
	
	/**
	 * 管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
	 */
	private int magree;
	
	/**
	 * 群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414
	 */
	private int joinmode;
	
	/**
	 * 自定义高级群扩展属性，第三方可以跟据此属性自定义扩展自己的群属性。（建议为json）,最大长度1024字符
	 */
	private String custom;
	
	/**
	 * 群头像，最大长度1024字符
	 */
	private String icon;
	
	/**
	 * 被邀请人同意方式，0-需要同意(默认),1-不需要同意。其它返回414
	 */
	private int beinvitemode;
	
	/**
	 * 谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414
	 */
	private int invitemode;
	
	/**
	 * 谁可以修改群资料，0-管理员(默认),1-所有人。其它返回414
	 */
	private int uptinfomode;
	
	/**
	 * 谁可以更新群自定义属性，0-管理员(默认),1-所有人。其它返回414
	 */
	private int upcustommode;
	
	
	
	/**
	 * 创建时 ---构造
	 * @param tname
	 * @param owner
	 * @param members
	 * @param announcement
	 * @param intro
	 * @param msg
	 * @param magree
	 * @param joinmode
	 * @param custom
	 * @param icon
	 * @param beinvitemode
	 * @param invitemode
	 * @param uptinfomode
	 * @param upcustommode
	 */
	public TeamUser(String tname, String owner, String members, String announcement, String intro, String msg,
			int magree, int joinmode, String custom, String icon, int beinvitemode, int invitemode,
			int uptinfomode, int upcustommode) {
		super();
		this.tname = tname;
		this.owner = owner;
		this.members = members;
		this.announcement = announcement;
		this.intro = intro;
		this.msg = msg;
		this.magree = magree;
		this.joinmode = joinmode;
		this.custom = custom;
		this.icon = icon;
		this.beinvitemode = beinvitemode;
		this.invitemode = invitemode;
		this.uptinfomode = uptinfomode;
		this.upcustommode = upcustommode;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getMagree() {
		return magree;
	}

	public void setMagree(int magree) {
		this.magree = magree;
	}

	public int getJoinmode() {
		return joinmode;
	}

	public void setJoinmode(int joinmode) {
		this.joinmode = joinmode;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getBeinvitemode() {
		return beinvitemode;
	}

	public void setBeinvitemode(int beinvitemode) {
		this.beinvitemode = beinvitemode;
	}

	public int getInvitemode() {
		return invitemode;
	}

	public void setInvitemode(int invitemode) {
		this.invitemode = invitemode;
	}

	public int getUptinfomode() {
		return uptinfomode;
	}

	public void setUptinfomode(int uptinfomode) {
		this.uptinfomode = uptinfomode;
	}

	public int getUpcustommode() {
		return upcustommode;
	}

	public void setUpcustommode(int upcustommode) {
		this.upcustommode = upcustommode;
	}
}
