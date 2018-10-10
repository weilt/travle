package com.hwt.domain.entity.user.Vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户所有信息
 * @author Administrator
 *
 */
@ApiModel(value="用户所有信息")
public class UserVo extends UserInfoVo implements Serializable{
	
	/**
     * 主键ID
     */
	@ApiModelProperty(value="用户id",name="user_id")
    private Long user_id;

    /**
     * 用户登录账号
     */
	@ApiModelProperty(value="用户登录账号")
    private String account;

    /**
     * 账号是否被修改 0-否 1-是  
     */
	@ApiModelProperty(value="账号是否被修改 0-否 1-是  ")
    private Byte account_is_edit;
	
	/**
	 * 登录用手机号
	 */
	@ApiModelProperty(value="登录用手机号")
	private String account_phone;

    /**
     * 登录密码 + 密码盐 加密
     */
    @ApiModelProperty(value="登录密码 + 密码盐 加密")
    private String password;

    /**
     * 密码盐 6位随机数
     */
    @ApiModelProperty(value="密码盐 6位随机数")
    private String pwd_salt;

    /**
     * 网易云信id
     */
    @ApiModelProperty(value=" 网易云信id")
    private String im_id;

    /**
     * 第三方通信token
     */
    @ApiModelProperty(value="第三方通信token")
    private String im_token;

    /**
     * 通信token更新时间
     */
    @ApiModelProperty(value="通信token更新时间")
    private Date im_token_time;

    /**
     * 登录状态 1-正常状态,2-冻结状态 ，3-永久冻结
     */
    @ApiModelProperty(value="* 登录状态 1-正常状态,2-冻结状态 ，3-永久冻结")
    private Integer login_state;

    /**
     * 登录状态冻结开始时间 - 
     */
    @ApiModelProperty(value="登录状态冻结开始时间 - ")
    private Date login_state2_begin;

    /**
     * 登录状态冻结结束时间 - 冻结状态才去判断这个
     */
    @ApiModelProperty(value="登录状态冻结结束时间 - 冻结状态才去判断这个")
    private Date login_state2_end;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date create_time;

    /**
     * 最近登录时间
     */
    @ApiModelProperty(value="最近登录时间")
    private Date recently_login_time;

    /**
     * 最近登录IP
     */
    @ApiModelProperty(value="最近登录IP")
    private String recently_login_ip;

    /**
     * 登录状态 0-离线 1-在线
     */
    @ApiModelProperty(value="登录状态 0-离线 1-在线")
    private Integer login_type;

    /**
     * 是否可用 0 不可用 ；1 可用
     */
    @ApiModelProperty(value="是否可用 0 不可用 ；1 可用")
    private Boolean is_available;

    private static final long serialVersionUID = 1L;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Byte getAccount_is_edit() {
		return account_is_edit;
	}

	public void setAccount_is_edit(Byte account_is_edit) {
		this.account_is_edit = account_is_edit;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPwd_salt() {
		return pwd_salt;
	}

	public void setPwd_salt(String pwd_salt) {
		this.pwd_salt = pwd_salt;
	}

	public String getIm_id() {
		return im_id;
	}

	public void setIm_id(String im_id) {
		this.im_id = im_id;
	}

	public String getIm_token() {
		return im_token;
	}

	public void setIm_token(String im_token) {
		this.im_token = im_token;
	}

	public Date getIm_token_time() {
		return im_token_time;
	}

	public void setIm_token_time(Date im_token_time) {
		this.im_token_time = im_token_time;
	}

	public Integer getLogin_state() {
		return login_state;
	}

	public void setLogin_state(Integer login_state) {
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

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getRecently_login_time() {
		return recently_login_time;
	}

	public void setRecently_login_time(Date recently_login_time) {
		this.recently_login_time = recently_login_time;
	}

	public String getRecently_login_ip() {
		return recently_login_ip;
	}

	public void setRecently_login_ip(String recently_login_ip) {
		this.recently_login_ip = recently_login_ip;
	}

	public Integer getLogin_type() {
		return login_type;
	}

	public void setLogin_type(Integer login_type) {
		this.login_type = login_type;
	}

	public Boolean getIs_available() {
		return is_available;
	}

	public void setIs_available(Boolean is_available) {
		this.is_available = is_available;
	}

	public String getAccount_phone() {
		return account_phone;
	}

	public void setAccount_phone(String account_phone) {
		this.account_phone = account_phone;
	}
    
    
}
