package com.hwt.domain.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 群
 * @author 
 */
public class HxUserTeam implements Serializable {
   
	
	/**
	 * 群id
	 */
	private String team_id;

    /**
     * 群名称
     */
    private String name;

    /**
     * 群主im_id
     */
    private String owner_id_im_id;

    /**
     * 群公告
     */
    private String announcement;

    /**
     * 群描述
     */
    private String intro;

    /**
     * 邀请发送的文字
     */
    private String msg;

    /**
     * 管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群
     */
    private Byte magree;

    /**
     * 群头像
     */
    private String icon;

    /**
     * 被邀请人同意方式，0-需要同意(默认),1-不需要同意
     */
    private Byte beinvitemode;

    /**
     * 谁可以邀请他人入群，0-管理员(默认),1-所有人
     */
    private Byte invitemode;

    /**
     * 谁可以修改群资料，0-管理员(默认),1-所有人
     */
    private Byte uptinfomode;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 状态    1-正常状态,2-冻结状态 ，3-永久冻结'
     */
    private Byte login_state;

    /**
     * 冻结开始时间
     */
    private Date login_state2_begin;

    /**
     * 冻结结束时间 - 冻结状态才去判断这个
     */
    private Date login_state2_end;

    /**
     * 是否可用 0 不可用 ；1 可用
     */
    private Byte is_available;

    private static final long serialVersionUID = 1L;

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner_id_im_id() {
        return owner_id_im_id;
    }

    public void setOwner_id_im_id(String owner_id_im_id) {
        this.owner_id_im_id = owner_id_im_id;
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

    public Byte getMagree() {
        return magree;
    }

    public void setMagree(Byte magree) {
        this.magree = magree;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Byte getBeinvitemode() {
        return beinvitemode;
    }

    public void setBeinvitemode(Byte beinvitemode) {
        this.beinvitemode = beinvitemode;
    }

    public Byte getInvitemode() {
        return invitemode;
    }

    public void setInvitemode(Byte invitemode) {
        this.invitemode = invitemode;
    }

    public Byte getUptinfomode() {
        return uptinfomode;
    }

    public void setUptinfomode(Byte uptinfomode) {
        this.uptinfomode = uptinfomode;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Byte getLogin_state() {
        return login_state;
    }

    public void setLogin_state(Byte login_state) {
        this.login_state = login_state;
    }

    public Date getLogin_state2_begin() {
        return login_state2_begin;
    }

    public void setLogin_state2_begin(Date login_state2_begin) {
        this.login_state2_begin = login_state2_begin;
    }

    public Date getLogin_state2_end() {
        return login_state2_end;
    }

    public void setLogin_state2_end(Date login_state2_end) {
        this.login_state2_end = login_state2_end;
    }

    public Byte getIs_available() {
        return is_available;
    }

    public void setIs_available(Byte is_available) {
        this.is_available = is_available;
    }
}