package com.hx.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.user.HxUserBlackList;
import com.hwt.domain.entity.user.HxUserFriend;
import com.hwt.domain.entity.user.Vo.BlackFriendUserVo;
import com.hwt.domain.mapper.user.HxUserBlackListMapper;
import com.hwt.domain.mapper.user.HxUserFriendMapper;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hx.core.utils.GsonUtil;
import com.hx.core.wyim.entity.FriendOperationNotice;
import com.hx.core.wyim.service.ImService;
import com.hx.user.service.HxUserBlackService;

@Service
public class HxUserBlackServiceImpl implements HxUserBlackService{
	
	@Autowired
	private HxUserBlackListMapper hxUserBlackListMapper;
	@Autowired
	private HxUserFriendMapper hxUserFriendMapper;
	
	@Autowired
	private ImService imService;
	
	@Autowired
	private HxUserMapper hxUserMapper;
	
	@Override
	public List<BlackFriendUserVo> getBlackfriends(Long userId) {
		List<BlackFriendUserVo> BlackFriends = hxUserBlackListMapper.queryListByUserId(userId);
		return BlackFriends;
	}
	@Transactional
	@Override
	public void defriend(Long userId, Long friendId, String remark , String im_id) throws Exception{
		
		HxUserFriend hxUserFriend = hxUserFriendMapper.queryByByUserIdAndFriendId(userId, friendId);
		//添加到黑名单
		HxUserBlackList hxUserBlackList = new HxUserBlackList();
		//更新好友关系
		if (hxUserFriend!=null){
			if(hxUserFriend.getFriend_state()==1){
				hxUserFriend.setFriend_state((byte) 2);
				hxUserFriendMapper.updateByPrimaryKey(hxUserFriend);
				hxUserFriendMapper.updateFriendToUser(friendId,userId,2);
				hxUserBlackList.setFriend_remark(hxUserFriend.getFriend_remark());
			}
		}
		
		hxUserBlackList.setBlacklist_id(friendId);
		hxUserBlackList.setUser_id(userId);
		hxUserBlackList.setCreate_time(new Date());
		
		hxUserBlackListMapper.insert(hxUserBlackList );
		
		//发消息
		imService.singleNotice(im_id+"", getFriendImId(friendId)+"", GsonUtil.toJson(new FriendOperationNotice(im_id+"", 2, null)), 2);
	}
	
	/**
	 * 
	 * 通过id获取imid
	 * @param userId
	 * @return
	 */
	private String	getFriendImId(Long userId){
		return hxUserMapper.queryImIdByUserId(userId);
		
	}
	
	@Transactional
	@Override
	public void removeDefriend(Long userId, Long friendId, String im_id) throws Exception {
		HxUserFriend hxUserFriend = hxUserFriendMapper.queryByByUserIdAndFriendId(userId, friendId);
		
		//更新好友关系
		if (hxUserFriend!=null){
			if(hxUserFriend.getFriend_state()==2){
				hxUserFriend.setFriend_state((byte) 1);
				hxUserFriendMapper.updateByPrimaryKey(hxUserFriend);
				hxUserFriendMapper.updateFriendToUser(friendId,userId,1);
			}
		}
		
		//移除黑名单
		hxUserBlackListMapper.deleteByUserIdAndFriendId(userId,friendId);
		
		//发消息
		imService.singleNotice(im_id+"", getFriendImId(friendId)+"", GsonUtil.toJson(new FriendOperationNotice(im_id+"", 6, null)), 2);
	}
}
