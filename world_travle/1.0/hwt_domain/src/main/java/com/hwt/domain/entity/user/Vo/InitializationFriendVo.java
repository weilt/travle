package com.hwt.domain.entity.user.Vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用于初始化返回好友对我的 删除和拉黑 了 的im_id
 * @author Administrator
 *
 */
@ApiModel(value="用于初始化返回好友对我的 删除和拉黑 了 的im_id")
public class InitializationFriendVo implements Serializable{
	
	

	public InitializationFriendVo(List<String> blackList, List<String> deleteList) {
		super();
		this.blackList = blackList;
		this.deleteList = deleteList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 把我拉黑人的im_id
	 */
	@ApiModelProperty(value="把我拉黑人的im_id ---数组")
	List<String> blackList;
	
	/**
	 * 把我删除人的im_id
	 */
	@ApiModelProperty(value="把我删除人的im_id ---数组")
	List<String> deleteList;

	public List<String> getBlackList() {
		return blackList;
	}

	public void setBlackList(List<String> blackList) {
		this.blackList = blackList;
	}

	public List<String> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<String> deleteList) {
		this.deleteList = deleteList;
	}
	

}
