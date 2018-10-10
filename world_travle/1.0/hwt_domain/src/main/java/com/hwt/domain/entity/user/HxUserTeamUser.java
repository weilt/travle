package com.hwt.domain.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 群成员
 * @author 
 */
public class HxUserTeamUser implements Serializable {
	
	/**
	 * 群id
	 */
	private String team_id;

	/**
	 * 用户im_id
	 */
	private String user_id_im_id;
    /**
     * 状态      1-正常状态,2-禁言状态 ，3-永久禁言
     */
    private Byte state;

    /**
     * 禁言开始时间
     */
    private Date state2_begin;

    /**
     * 禁言结束时间 - 禁言状态才去判断这个
     */
    private Date login_state2_end;

    /**
     * 身份  1-群主 2-管理员 3-普通成员
     */
    private Byte type;

    /**
     * 加入时间
     */
    private Date create_time;
    
    /**
     * 群昵称
     */
    private String team_nick;

    /**
     * 消息面打扰 0 否 1 是
     */
    private Byte is_shield;

    /**
     * 显示群昵称 0 否 1 是
     */
    private Byte display_team_nick;

    private static final long serialVersionUID = 1L;
    
    public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getUser_id_im_id() {
		return user_id_im_id;
	}

	public void setUser_id_im_id(String user_id_im_id) {
		this.user_id_im_id = user_id_im_id;
	}

	public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getState2_begin() {
        return state2_begin;
    }

    public void setState2_begin(Date state2_begin) {
        this.state2_begin = state2_begin;
    }

    public Date getLogin_state2_end() {
        return login_state2_end;
    }

    public void setLogin_state2_end(Date login_state2_end) {
        this.login_state2_end = login_state2_end;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

	public String getTeam_nick() {
		return team_nick;
	}

	public void setTeam_nick(String team_nick) {
		this.team_nick = team_nick;
	}

	public Byte getIs_shield() {
		return is_shield;
	}

	public void setIs_shield(Byte is_shield) {
		this.is_shield = is_shield;
	}

	public Byte getDisplay_team_nick() {
		return display_team_nick;
	}

	public void setDisplay_team_nick(Byte display_team_nick) {
		this.display_team_nick = display_team_nick;
	}
    
    
}