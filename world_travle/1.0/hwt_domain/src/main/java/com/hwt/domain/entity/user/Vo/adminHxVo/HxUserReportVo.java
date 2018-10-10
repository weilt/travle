package com.hwt.domain.entity.user.Vo.adminHxVo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 淮信用户报表
 * @author Administrator
 *
 */
public class HxUserReportVo implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
	     * 主键ID
	     */
	    private Long user_id;
	    
	    /**
	     * 用户登录账号
	     */
	    private String account;
	    
	    /**
	     * 手机登录号码
	     */
	    private String account_phone;
	    
	    /**
	     * 登录状态 1-正常状态,2-冻结状态 ，3-永久冻结
	     */
	    private Integer login_state;
	    
	    /**
	     * 注册时间
	     */
	    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:dd:ss")
	   	@DateTimeFormat( pattern = "yyyy-MM-dd HH:dd:ss")
	    private Date create_time;

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

		public String getAccount_phone() {
			return account_phone;
		}

		public void setAccount_phone(String account_phone) {
			this.account_phone = account_phone;
		}

		public Integer getLogin_state() {
			return login_state;
		}

		public void setLogin_state(Integer login_state) {
			this.login_state = login_state;
		}

		public Date getCreate_time() {
			return create_time;
		}

		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}
}
	  
