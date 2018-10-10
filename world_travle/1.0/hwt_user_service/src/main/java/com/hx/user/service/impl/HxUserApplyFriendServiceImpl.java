package com.hx.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.user.HxUserApplyFriend;
import com.hwt.domain.entity.user.HxUserBlackList;
import com.hwt.domain.entity.user.HxUserFriend;
import com.hwt.domain.entity.user.HxUserInfo;
import com.hwt.domain.entity.user.Vo.FriendApplyVo;
import com.hwt.domain.mapper.user.HxUserApplyFriendMapper;
import com.hwt.domain.mapper.user.HxUserBlackListMapper;
import com.hwt.domain.mapper.user.HxUserFriendMapper;
import com.hwt.domain.mapper.user.HxUserInfoMapper;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hx.core.utils.GsonUtil;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.wyim.entity.FriendOperationNotice;
import com.hx.core.wyim.service.ImService;
import com.hx.user.service.HxUserApplyFriendService;

@Service
public class HxUserApplyFriendServiceImpl implements HxUserApplyFriendService{
	
	
	@Autowired
	private ImService imService;
	@Autowired
	private HxUserFriendMapper hxUserFriendMapper;
	@Autowired
	private HxUserApplyFriendMapper hxUserApplyFriendMapper;
	@Autowired
	private HxUserBlackListMapper hxUserBlackListMapper;
	@Autowired
	private HxUserMapper hxUserMapper;
	
	@Autowired
	private HxUserInfoMapper hxUserInfoMapper;
	
	@Override
	public List<FriendApplyVo> getApplyUsers(Long userId) {
		
		return hxUserApplyFriendMapper.queryListByType(userId);
	}

	
	@Transactional
	@Override
	public void apply(Long userId, Long friendId, String remark, String im_id, String friendRemark,Byte friend_see_me_space) throws Exception {
		HxUserBlackList hxUserBlackList = hxUserBlackListMapper.queryByUserIdAndFriendId(friendId,userId);
		
		if(ObjectHelper.isNotEmpty(hxUserBlackList)){
			throw new RuntimeException("对方拒绝接受你的消息");
		}
		HxUserFriend hxUserFriend = hxUserFriendMapper.queryByByUserIdAndFriendId(userId,friendId);
		if(hxUserFriend!=null){
			
			if(hxUserFriend.getFriend_state()==1){
				throw new RuntimeException("好友关系，不能重复申请！");
			}else if (hxUserFriend.getFriend_state()==3){
				//好友是删除状态————————恢复好友关系
				if(StringUtils.isNoneBlank(friendRemark)){
					hxUserFriendMapper.recoveryByByUserIdAndFriendIdTofriendRemark(userId,friendId,1,friendRemark);
				}
				hxUserFriendMapper.recoveryByByUserIdAndFriendId(userId,friendId,1);
				
				hxUserFriendMapper.updateFriendToUser(friendId,userId, 1);
				//发送消息
				imService.singleNotice(im_id+"", getFriendImId(friendId)+"",
						GsonUtil.toJson(new FriendOperationNotice(im_id+"", 4, null)), 2);
			}else{
				//黑名单情况
				throw new RuntimeException("异常操作");
			}
		}else{
			HxUserApplyFriend hxUserApplyFriend = hxUserApplyFriendMapper.queryByByUserIdAndFriendId(userId,friendId);
			
			HxUserInfo userInfo = hxUserInfoMapper.selectByUserId(userId);
			
			Map<String, Object> map= new HashMap<>();
			
			map.put("user_icon", userInfo.getUser_icon());
			map.put("nickname", userInfo.getNickname());
			map.put("user_id", userInfo.getUser_id());
			if(hxUserApplyFriend!=null){
				
			}else{
				hxUserApplyFriendMapper.insert(createHxUserApplyFriend(userId, friendId, 1, remark, friendRemark,friend_see_me_space));
			}
			
			//发送消息
			imService.singleNotice(im_id+"", getFriendImId(friendId)+"",
					GsonUtil.toJson(new FriendOperationNotice(im_id+"", 1, GsonUtil.toJson(map))), 2);
		}
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
	
	/**
	 * 创建好友申请关系
	 * @param userId
	 * @param friendId
	 * @param applyState
	 * @param remark
	 * @param friendRemark
	 * @param friend_see_me_space 
	 * @return
	 */
	private HxUserApplyFriend createHxUserApplyFriend(Long userId, Long friendId,Integer applyState,
			String remark, String friendRemark, Byte friend_see_me_space) {
		HxUserApplyFriend apply = new HxUserApplyFriend();
		apply.setApply_friend_id(friendId);
		apply.setApply_state(applyState);
		apply.setApply_user_id(userId);
		apply.setFriend_remark(friendRemark);
		apply.setRemarks(remark);
		apply.setFriend_see_me_space(friend_see_me_space);
		return apply;
	}

	@Transactional
	@Override
	public void agree(Long userId, Long friendId, String friendRemark, int friendSeeMeSpace, String remark, String im_id) throws Exception {
		HxUserFriend hxUserFriend = hxUserFriendMapper.queryByByUserIdAndFriendId(userId,friendId);
		if (hxUserFriend!=null){
			throw new RuntimeException("已是好友");
		}
		//添加好友信息
		HxUserFriend hxUserFriend2 = creatHxUserFriend(userId, friendId, friendRemark, friendSeeMeSpace, remark);
		//好友添加好友信息
		HxUserApplyFriend hxUserApplyFriend = hxUserApplyFriendMapper.queryByByUserIdAndFriendId(friendId, userId);
		hxUserFriend2.setFriend_friend_see_me_space(hxUserApplyFriend.getFriend_see_me_space());
		
		HxUserFriend hxUserFriend3 = creatHxUserFriend(friendId, userId, hxUserApplyFriend.getFriend_remark(), 1, hxUserApplyFriend.getRemarks());
		hxUserFriend3.setFriend_see_me_space(hxUserApplyFriend.getFriend_see_me_space());
		hxUserFriend3.setFriend_friend_see_me_space((byte)friendSeeMeSpace);
		//判断是否是黑名单
		HxUserBlackList hxUserBlackList = hxUserBlackListMapper.queryByUserIdAndFriendId(userId,friendId);
		if(hxUserBlackList!=null){
			hxUserFriend2.setFriend_state((byte) 2);
			hxUserFriend3.setFriend_to_me_state((byte)2);
		}
		
		
		
		//判断是否是黑名单
		HxUserBlackList hxUserBlackList1 = hxUserBlackListMapper.queryByUserIdAndFriendId(friendId,userId);
		if(hxUserBlackList1!=null){
			hxUserFriend3.setFriend_state((byte) 2);
			hxUserFriend2.setFriend_to_me_state((byte)2);
		}
		hxUserFriendMapper.insertSelective(hxUserFriend2);
		hxUserFriendMapper.insertSelective(hxUserFriend3);
		hxUserApplyFriendMapper.deleteByUserIdAndFriendId(friendId, userId);
		imService.singleNotice(im_id+"", getFriendImId(friendId)+"",
				GsonUtil.toJson(new FriendOperationNotice(im_id+"", 5,null)), 2);
	}


	/**
	 * 创建好友关系
	 * @param userId
	 * @param friendId
	 * @param friendRemark
	 * @param friendSeeMeSpace
	 * @param remark
	 * @return
	 */
	private HxUserFriend creatHxUserFriend(Long userId, Long friendId, String friendRemark, int friendSeeMeSpace,
			String remark) {
		HxUserFriend hxUserFriend2 = new HxUserFriend();
		hxUserFriend2.setFriend_id(friendId);
		hxUserFriend2.setUser_id(userId);
		hxUserFriend2.setFriend_remark(friendRemark);
		hxUserFriend2.setFriend_see_me_space((byte) friendSeeMeSpace);
		hxUserFriend2.setCreate_time(new Date());
		hxUserFriend2.setDescription(remark);
		return hxUserFriend2;
	}

	
	@Transactional
	@Override
	public void applyRefuse(Long user_id, Long friend_id) {
		hxUserApplyFriendMapper.deleteByUserIdAndFriendId(friend_id, user_id);
		
	}
}
