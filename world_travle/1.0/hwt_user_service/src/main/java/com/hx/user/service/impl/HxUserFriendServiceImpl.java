package com.hx.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.user.HxUserApplyFriend;
import com.hwt.domain.entity.user.HxUserBlackList;
import com.hwt.domain.entity.user.HxUserFriend;
import com.hwt.domain.entity.user.HxUserInfo;
import com.hwt.domain.entity.user.Vo.CommunicationListFriendVo;
import com.hwt.domain.entity.user.Vo.FriendInfoVo;
import com.hwt.domain.entity.user.Vo.FriendUserVo;
import com.hwt.domain.entity.user.Vo.InitializationFriendVo;
import com.hwt.domain.mapper.user.HxUserApplyFriendMapper;
import com.hwt.domain.mapper.user.HxUserBlackListMapper;
import com.hwt.domain.mapper.user.HxUserFriendMapper;
import com.hwt.domain.mapper.user.HxUserInfoMapper;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hx.core.utils.GsonUtil;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.wyim.entity.FriendOperationNotice;
import com.hx.core.wyim.service.ImService;
import com.hx.user.service.HxUserFriendService;

@Service
public class HxUserFriendServiceImpl implements HxUserFriendService{
	
	@Autowired
	private HxUserFriendMapper hxUserFriendMapper;
	@Autowired
	private HxUserApplyFriendMapper hxUserApplyFriendMapper;
	
	@Autowired
	private ImService imService;
	
	@Autowired
	private HxUserInfoMapper hxUserInfoMapper;
	
	@Autowired
	private HxUserMapper hxUserMapper;
	
	@Autowired
	private HxUserBlackListMapper hxUserBlackListMapper;

	@Override
	public List<FriendUserVo> getFriends(Long userId) {
		List<FriendUserVo> friends = hxUserFriendMapper.queryFriends(userId);
		return friends;
	}

	@Override
	public FriendInfoVo getFriendInfo(String im_id, Long userId) {
		
		
		
		//FriendInfoVo friendInfoVo = hxUserFriendMapper.queryFriendInfoByImId(im_id, userId);
		//if (friendInfoVo == null){
			FriendInfoVo friendInfoVo = hxUserInfoMapper.queryByUserIm_id(im_id);
	//	}
		
		getFriendInfoVo(userId, friendInfoVo);
		
		
		return friendInfoVo;
	}
	
	@Transactional
	@Override
	public void deleteFriend(Long userId, Long friendId , String im_id)  throws Exception{
		
		 HxUserFriend hxUserFriend = hxUserFriendMapper.queryByByUserIdAndFriendId(friendId, userId);
		 if (hxUserFriend==null){
			 throw new RuntimeException("好友关系已不存在");
		 }else{
			 //删除好友关系
			 if(hxUserFriend.getFriend_state()==3){
				 hxUserFriendMapper.deleteByUserIdAndFriendId(userId,friendId);
			 }else{
				 hxUserFriendMapper.recoveryByByUserIdAndFriendId(userId, friendId, 3);
				 hxUserFriendMapper.updateFriendToUser( friendId, userId, 3);
			 }
		 }
		 //删除黑名单关系
		 hxUserBlackListMapper.deleteByUserIdAndFriendId(userId, friendId);
		 
		//更新好友申请状态
		//hxUserApplyFriendMapper.updateByUserIdAndFriendId(friendId,userId,1);
		
		imService.singleNotice(im_id+"", getFriendImId(friendId) +"", GsonUtil.toJson(new FriendOperationNotice(im_id+"", 3, null)), 2);
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
	public void updateFriendRemark(Long userId, Long friendId, String friendRemark) {
		hxUserFriendMapper.updateFriendRemark(userId,friendId,friendRemark);
		hxUserBlackListMapper.updateFriendRemark(userId,friendId,friendRemark);
	}

	@Override
	public FriendInfoVo searchAccountByField(String field, Long userId) {
		FriendInfoVo friendInfoVo;
		if (ObjectHelper.isPhoneLegal(field)){
			friendInfoVo = hxUserInfoMapper.queryByAccountPhone(field);
		}else{
			friendInfoVo = hxUserInfoMapper.queryByAccount(field);
		}
		getFriendInfoVo(userId, friendInfoVo);
		return friendInfoVo;
	}
	
	
	/**
	 * 对friendInfoVo进行处理
	 * @param userId
	 * @param friendInfoVo
	 */
	private void getFriendInfoVo(Long userId, FriendInfoVo friendInfoVo) {
		if (friendInfoVo!=null){
			//查询是否在好友列表
			HxUserFriend hxUserFriend = hxUserFriendMapper.queryByByUserIdAndFriendId(userId, friendInfoVo.getFriend_id());
			if (hxUserFriend!=null){
				friendInfoVo.setFriend_remark(hxUserFriend.getFriend_remark());
				friendInfoVo.setFriend_state(hxUserFriend.getFriend_state());
				friendInfoVo.setFriend_see_me_space(hxUserFriend.getFriend_see_me_space());
				friendInfoVo.setMe_see_friend_space(hxUserFriend.getMe_see_friend_space());
				//是否拉黑对方
//				if(hxUserFriend.getFriend_state()==2){
//					friendInfoVo.setBlack_state(1);
//				}
//				//是否被拉黑
//				if (hxUserFriend.getFriend_to_me_state()==2){
//					friendInfoVo.setBei_black_state(1);
//				}
//				//是否删除对方
//				if(hxUserFriend.getFriend_state()==3){
//					friendInfoVo.setDelete_state(1);
//				}
//				//是否被删除
//				if (hxUserFriend.getFriend_to_me_state()==3){
//					friendInfoVo.setBei_delete_state(1);
//				}
				
				if (hxUserFriend.getFriend_state()==1){
					friendInfoVo.setApply_type(1);
				}
				if (hxUserFriend.getFriend_state()==2){
					friendInfoVo.setApply_type(2);
				}
				
				if (hxUserFriend.getFriend_to_me_state()==2){
					friendInfoVo.setFriend_apply_type(2);
				}
				if (hxUserFriend.getFriend_to_me_state()==3){
					friendInfoVo.setFriend_apply_type(3);
				}
				
				return ;
			}
			
			
			//查询是否被申请
			HxUserApplyFriend hxUserApplyFriend2 = hxUserApplyFriendMapper.queryByByUserIdAndFriendId( friendInfoVo.getFriend_id(),userId);
			if (hxUserApplyFriend2!=null){
				friendInfoVo.setFriend_apply_type(4);
			}
			
			//查询是否在黑名单里面
			HxUserBlackList hxUserBlackList = hxUserBlackListMapper.queryByUserIdAndFriendId(userId, friendInfoVo.getFriend_id());
			if (hxUserBlackList!=null){
				friendInfoVo.setApply_type(2);
			}
			//查询是否在对面黑名单里面
			HxUserBlackList hxUserBlackList2 = hxUserBlackListMapper.queryByUserIdAndFriendId( friendInfoVo.getFriend_id(),userId);
			if (hxUserBlackList2!=null){
				friendInfoVo.setFriend_apply_type(2);
			}
		}else{
			
			throw new RuntimeException("不存在该用户");
			
		}
	}

	@Override
	public FriendInfoVo searchAccountByFriendId(Long friend_id, Long user_id) {
		FriendInfoVo friendInfoVo;
		
		friendInfoVo = hxUserInfoMapper.queryByUserId(friend_id);
		
		getFriendInfoVo(user_id, friendInfoVo);
		
		return friendInfoVo;
	}

	@Override
	public InitializationFriendVo initializationFriendToMe(Long user_id) {
		
		//查询把我拉黑的im——id
		List<String> blackList = hxUserBlackListMapper.queryBeiBlackImIdByUserId(user_id);
		
		//查询把我删除的im——id
		List<String> deleteList = hxUserFriendMapper.queryBeiDeleteImIdByUserId(user_id);
		return new InitializationFriendVo(blackList,deleteList);
	}

	@Override
	public List<CommunicationListFriendVo> findHxUserByPhone(String[] phones) {
		if (phones!=null&&phones.length>0){
			List<CommunicationListFriendVo> communicationListFriendVos = hxUserInfoMapper.findHxUserByPhone(phones);
			
			return communicationListFriendVos;
		}
		return null;
	}

}
