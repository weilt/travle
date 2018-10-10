package com.hx.user.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.mg.user.FriendCircle;
import com.hwt.domain.entity.mg.user.FriendCircleComment;
import com.hwt.domain.entity.mg.user.FriendCircleIdAdministration;
import com.hwt.domain.entity.user.HxUserConfig;
import com.hwt.domain.entity.user.HxUserFriend;
import com.hwt.domain.entity.user.Vo.FriendCircleCommentVo;
import com.hwt.domain.entity.user.Vo.FriendCircleJurisdictionVo;
import com.hwt.domain.entity.user.Vo.FriendCircleVo;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hwt.domain.mapper.user.HxUserConfigMapper;
import com.hwt.domain.mapper.user.HxUserFriendMapper;
import com.hwt.domain.mapper.user.HxUserInfoMapper;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryCondition.LinkKey;
import com.hx.core.utils.GsonUtil;
import com.hx.core.utils.JsonUtils;
import com.hx.core.wyim.entity.FriendOperationNotice;
import com.hx.core.wyim.service.ImService;
import com.hx.core.mongo.value.MongoQueryProjections;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.mongo.value.Sort;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.user.service.FriendCircleService;

@Service
public class FriendCircleServiceImpl implements FriendCircleService {
	
	@Autowired
	private HxUserFriendMapper hxUserFriendMapper;
	
	@Autowired
	private HxUserConfigMapper hxUserConfigMapper;
	
	@Autowired
	private ImService imService;
	
	/**
	 * 获取最大id
	 * @return
	 */
	private Long queryMaxId(){
		Long incId = MongoService.getIncId(HXSystemConfig.MONGO_DB_NAME_friendCircle,HXSystemConfig.MONGO_Collection_NAME_Friend_Circle_Id_Administration , "friendCircleIdAdministrationId");
		if (incId==null||incId<=1){
			return 10000l;
		}
		return incId;
	}
	private void insertFriendCircleIdAdministration(FriendCircleIdAdministration friendCircleIdAdministration) throws Exception{
		
		MongoService.insert(HXSystemConfig.MONGO_DB_NAME_friendCircle,HXSystemConfig.MONGO_Collection_NAME_Friend_Circle_Id_Administration, friendCircleIdAdministration);
	}
	
	@Override
	public void friendCircle_insert(FriendCircle friendCircle, LoginVo loginVo) throws Exception {
		Long friendCircleIdAdministrationId = queryMaxId();
		friendCircle.setFriend_circle_id(friendCircleIdAdministrationId);
		MongoService.insert(HXSystemConfig.MONGO_DB_NAME_friendCircle,HXSystemConfig.MONGO_Collection_NAME_friendCircle, friendCircle);
		insertFriendCircleIdAdministration(new FriendCircleIdAdministration(friendCircleIdAdministrationId, new Date().getTime(), "朋友圈添加"));
	
		String can_see_user_id = friendCircle.getCan_see_user_id();
		List<String> im_ids = null;
		if ("1".equals(can_see_user_id)){
			im_ids = hxUserFriendMapper.queryFriend_im_id_for_friendCircle_all(loginVo.getUser_id());
			if(im_ids!=null&&im_ids.size()>0){
				for (String im_id : im_ids) {
					//发送消息
					imService.singleNotice(loginVo.getIm_id()+"", im_id+"",
							GsonUtil.toJson(new FriendOperationNotice(loginVo.getIm_id()+"",null, 9, GsonUtil.toJson(friendCircle))), 2);
				}
				
			}
		}else if(can_see_user_id.contains("y")) {
			String[] split = can_see_user_id.split("y");
			Long[] ids = StringToLong(split);
			if (ids!=null){
				im_ids = hxUserFriendMapper.queryFriend_im_id_for_friendCircle_Y(loginVo.getUser_id(),ids);
				if(im_ids!=null&&im_ids.size()>0){
					for (String im_id : im_ids) {
						//发送消息
						imService.singleNotice(loginVo.getIm_id()+"", im_id+"",
								GsonUtil.toJson(new FriendOperationNotice(loginVo.getIm_id()+"",null, 9, GsonUtil.toJson(friendCircle))), 2);
					}
					
				}
			}
		}else if(can_see_user_id.contains("n")){
			String[] split = can_see_user_id.split("n");
			Long[] ids = StringToLong(split);
			if (ids!=null){
				im_ids = hxUserFriendMapper.queryFriend_im_id_for_friendCircle_N(loginVo.getUser_id(),ids);
				if(im_ids!=null&&im_ids.size()>0){
					for (String im_id : im_ids) {
						//发送消息
						imService.singleNotice(loginVo.getIm_id()+"", im_id+"",
								GsonUtil.toJson(new FriendOperationNotice(loginVo.getIm_id()+"",null, 9, GsonUtil.toJson(friendCircle))), 2);
					}
					
				}
			}
		}
	
	}
	/**
	 * string[] 转 Long[]
	 * @param user_id
	 * @param friend_circle_id
	 */
	private Long[] StringToLong(String[] split){
		if (split!=null && split.length>0){
			Long[] ids = new Long[split.length];
			for (int i = 0; i < split.length; i++) {
				ids[i] = Long.parseLong(split[i]);
			}
			return ids;
		}
		return null;
		
	}
	@Override
	public void friendCircle_delete(Long user_id, Long friend_circle_id) {
		//删除朋友圈
		Map<String, Object> map = new HashMap<>();
		map.put("friend_circle_id", friend_circle_id);
		
		//删除评论
		MongoService.delete(HXSystemConfig.MONGO_DB_NAME_friendCircle, HXSystemConfig.MONGO_Collection_NAME_friendCircle_comment, map);
		
		map.put("user_id", user_id);
		
		//删除朋友圈
		MongoService.delete(HXSystemConfig.MONGO_DB_NAME_friendCircle, HXSystemConfig.MONGO_Collection_NAME_friendCircle, map);
		
		
		
	}

	@Override
	public List<FriendCircleVo> friendCircle_query(Long user_id, Long last_friend_circle_id, Integer pageSize) {
		
		//自己的
		MongoQueryCondition condition1 = new MongoQueryCondition(LinkKey.and);
		condition1.add(new MongoQueryValue(MongoOperator.eq, "user_id",user_id));
		if (last_friend_circle_id!=null&&last_friend_circle_id>0){
			condition1.add(new MongoQueryValue(MongoOperator.lt, "friend_circle_id",last_friend_circle_id));
		}
		
		
		List<FriendCircleJurisdictionVo> friendCircleJurisdictionVos = hxUserFriendMapper.query1to1FriendByUse_id(user_id);
		MongoQueryCondition[] conditions = null;
		
		
		if (friendCircleJurisdictionVos!=null&&friendCircleJurisdictionVos.size()>=0){
			conditions = new MongoQueryCondition[friendCircleJurisdictionVos.size()];
			
			for (int i=0; i< friendCircleJurisdictionVos.size(); i++) {
				
				FriendCircleJurisdictionVo friendCircleJurisdictionVo = friendCircleJurisdictionVos.get(i);
				
				//好友不让你
				MongoQueryCondition condition8 = new MongoQueryCondition(LinkKey.and);
				condition8.add(new MongoQueryValue(MongoOperator.notregex, "can_see_user_id","n"+user_id+"n"));
				condition8.add(new MongoQueryValue(MongoOperator.ne, "can_see_user_id","0"));
				
				MongoQueryCondition condition3 = new MongoQueryCondition(LinkKey.or);
				//好友的所有人可看
				condition3.add(new MongoQueryValue(MongoOperator.eq, "can_see_user_id","1"));
				//好友的只让你看的
				condition3.add(new MongoQueryValue(MongoOperator.regex, "can_see_user_id","y"+user_id+"y"));
				condition3.add(new MongoQueryValue(MongoOperator.regex, "can_see_user_id","n"));
				MongoQueryCondition condition9 = new MongoQueryCondition(LinkKey.and,condition3,condition8);
				
				
				//condition3.add(new MongoQueryValue(MongoOperator.ne, "can_see_user_id","0"));
				
				MongoQueryCondition condition7 = new MongoQueryCondition(LinkKey.and);
				condition7.add(new MongoQueryValue(MongoOperator.eq, "user_id",friendCircleJurisdictionVo.getFriend_id()));
				
				
				MongoQueryCondition condition2 = new MongoQueryCondition(LinkKey.and,condition7,condition9);
				
			//	MongoQueryCondition condition2 = new MongoQueryCondition(LinkKey.and,new MongoQueryCondition(LinkKey.or,new MongoQueryValue(MongoOperator.eq,"can_see_user_id",1),new MongoQueryValue(MongoOperator.eq,"can_see_user_id","y"+user_id+"y")),new MongoQueryCondition(LinkKey.or,new MongoQueryValue(MongoOperator.eq,"user_id",friendCircleJurisdictionVo.getFriend_id())));
				
				MongoQueryCondition condition5 = new MongoQueryCondition(LinkKey.and);
				
				if (last_friend_circle_id!=null&&last_friend_circle_id>0){
					condition5.add(new MongoQueryValue(MongoOperator.lt, "friend_circle_id",last_friend_circle_id));
				}
				if (friendCircleJurisdictionVo.getFriend_circle_friend_see_day()==0){
					//0所有都能看
					
				}else if(friendCircleJurisdictionVo.getFriend_circle_friend_see_day()==1){
					//1 只能看3天内
					Long time = new Date().getTime() - 3*24*60*60*1000;
					condition5.add(new MongoQueryValue(MongoOperator.gt, "create_time",time));
				}else{
					//半年内
					Long time = new Date().getTime() - 133*24*60*60*1000;
					
					condition5.add(new MongoQueryValue(MongoOperator.gt, "create_time",time));
				}
				MongoQueryCondition condition4 = new MongoQueryCondition(LinkKey.and,condition2,condition5);
				conditions[i] = condition4;
			}
		}
		if (conditions!=null&&conditions.length>0){
			MongoQueryCondition condition8 = new MongoQueryCondition(LinkKey.or,conditions);
			MongoQueryCondition condition = new MongoQueryCondition(LinkKey.or,condition8,condition1);
			
			
			List<FriendCircleVo> friendCircleVos = MGByConditionFriendCircleVo(condition, 1, pageSize);
			
			return friendCircleVos;
		}else{
			
			List<FriendCircleVo> friendCircleVos = MGByConditionFriendCircleVo(condition1, 1, pageSize);
			
			return friendCircleVos;
		}
		
		
		//方案一    
		/*//自己的
		MongoQueryCondition condition1 = new MongoQueryCondition(LinkKey.and);
		condition1.add(new MongoQueryValue(MongoOperator.eq, "user_id",user_id));
		
		
		//好友关系正常的
		MongoQueryCondition condition2 = new MongoQueryCondition(LinkKey.and);
		List<Long> ids = hxUserFriendMapper.query1to1Friend_idByUse_id(user_id);
		condition2.add(new MongoQueryValue(MongoOperator.in, "user_id",ids));
		
		//好友能让你看的
		MongoQueryCondition condition3 = new MongoQueryCondition(LinkKey.or);
		//好友的所有人可看
		condition3.add(new MongoQueryValue(MongoOperator.eq, "can_see_user_id",1));
		//好友的只让你看的
		condition3.add(new MongoQueryValue(MongoOperator.regex, "can_see_user_id","y"+user_id+"y"));
		//好友不让你
		condition3.add(new MongoQueryValue(MongoOperator.notregex, "can_see_user_id","n"+user_id+"n"));
		
		//关系组合
		MongoQueryCondition condition4 = new MongoQueryCondition(LinkKey.and,condition2,condition3);
		MongoQueryCondition condition = new MongoQueryCondition(LinkKey.or,condition4,condition1);
		
		List<FriendCircleVo> friendCircleVos = MGByConditionFriendCircleVo(condition, pageIndex, pageSize);
		
		return friendCircleVos;*/
	}

	/**
	 * 通过条件查询朋友圈信息
	 * @param condition		条件
	 * @param pageIndex		当前页
	 * @param pageSize		页面条数
	 * @return
	 */
	private List<FriendCircleVo> MGByConditionFriendCircleVo(MongoQueryCondition condition, Integer pageIndex, Integer pageSize) {
		MongoQueryProjections projection = new MongoQueryProjections();
		//返回对象
		List<FriendCircleVo> friendCircleVos = new ArrayList<FriendCircleVo>();
		//获取数据
		List<Map<String, Object>> findByPage = MongoService.findByPage(HXSystemConfig.MONGO_DB_NAME_friendCircle, HXSystemConfig.MONGO_Collection_NAME_friendCircle, projection , condition, new Sort("create_time", Sort.DESC), pageIndex, pageSize);
		//获取朋友圈id集用来查询评论
		List<Long> friendCircleVoIDS = new ArrayList<Long>();
		//封装数据
		if (findByPage!=null&&findByPage.size()>0){
			
			for (Map<String, Object> map : findByPage) {
				FriendCircleVo friendCircleVo = mapToFriendCircleVo(map);
				
				friendCircleVos.add(friendCircleVo);
				friendCircleVoIDS.add(friendCircleVo.getFriend_circle_id());
			}
		}
		//获取评论
		if (friendCircleVos!=null&&friendCircleVos.size()>0){
			
			MGByConditionFriendCircleCommentVo(friendCircleVoIDS,friendCircleVos);
		}
		return friendCircleVos;
	}

	/**
	 * map转成朋友圈对象
	 * @param map
	 * @return
	 */
	private FriendCircleVo mapToFriendCircleVo(Map<String, Object> map) {
		
		FriendCircleVo friendCircleVo = new FriendCircleVo();
		friendCircleVo.setCan_see_user_id(map.get("can_see_user_id")==null?null:map.get("can_see_user_id").toString());
		friendCircleVo.setContent(map.get("content")==null?null:map.get("content").toString());
		friendCircleVo.setDescription(map.get("description")==null?null:map.get("description").toString());
		friendCircleVo.setFriend_circle_id(map.get("friend_circle_id")==null?null:Long.parseLong(map.get("friend_circle_id").toString()));
		friendCircleVo.setLatitude(map.get("latitude")==null?null:map.get("latitude").toString());
		friendCircleVo.setLongitude(map.get("longitude")==null?null:map.get("longitude").toString());
		friendCircleVo.setRemind_user_id(map.get("remind_user_id")==null?null:map.get("remind_user_id").toString());
		friendCircleVo.setType(map.get("type")==null?null:Integer.parseInt(map.get("type").toString()));
		friendCircleVo.setUser_id(map.get("user_id")==null?null:Long.parseLong(map.get("user_id").toString()));
		friendCircleVo.setCreate_time(map.get("create_time")==null?null:(Long) map.get("create_time"));
		friendCircleVo.setPublish_address(map.get("publish_address")==null?null:map.get("publish_address").toString());
		return friendCircleVo;
	}

	/**
	 * 获取评论
	 * @param friendCircleVoIDS  朋友圈id
	 * @param friendCircleVos
	 */
	private void MGByConditionFriendCircleCommentVo(List<Long> friendCircleVoIDS,
			List<FriendCircleVo> friendCircleVos) {
		MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
		condition.add(new MongoQueryValue(MongoOperator.in, "friend_circle_id",friendCircleVoIDS));
		
		
		//排序
		Sort[] sorts = {new Sort("create_time", 1),new Sort("friend_circle_id", -1)};
		//获取数据
//		List<Map<String, Object>> findByPage = MongoService.findALLSorts(MONGO_DB_NAME_friendCircle, 
//				MONGO_Collection_NAME_friendCircle_comment, sorts , null);
		List<Map<String, Object>> findByPage = MongoService.findCustomSorts(HXSystemConfig.MONGO_DB_NAME_friendCircle, HXSystemConfig.MONGO_Collection_NAME_friendCircle_comment, condition, sorts, null);
		for (Map<String, Object> map : findByPage) {
		}
		if (findByPage!=null&&findByPage.size()>0){
			
			for (int i = 0; i < friendCircleVos.size(); i++) {
				FriendCircleVo friendCircleVo = friendCircleVos.get(i);
				for (int j = 0; findByPage!=null && findByPage.size()>0 && j < findByPage.size(); j++) {
					Map<String, Object> map = findByPage.get(j);
					if (!(friendCircleVo.getFriend_circle_id()+"").equals(map.get("friend_circle_id").toString())){
						break;
					}
					
					FriendCircleCommentVo friendCircleCommentVo = new FriendCircleCommentVo();
					friendCircleCommentVo.setComment_id(map.get("comment_id")==null?null:Long.parseLong(map.get("comment_id").toString()));
					friendCircleCommentVo.setContent(map.get("content")==null?null: map.get("content").toString());
					friendCircleCommentVo.setCreate_time(map.get("create_time")==null?null:Long.parseLong( map.get("create_time").toString()));
					friendCircleCommentVo.setFriend_circle_id(map.get("friend_circle_id")==null?null:Long.parseLong( map.get("friend_circle_id").toString()));
					friendCircleCommentVo.setParent_id(map.get("parent_id")==null?null:Long.parseLong( map.get("parent_id").toString()));
					friendCircleCommentVo.setParent_user_id(map.get("parent_user_id")==null?null:Long.parseLong( map.get("parent_user_id").toString()));
					friendCircleCommentVo.setRelation(map.get("relation")==null?null: map.get("relation").toString());
					friendCircleCommentVo.setType(map.get("type")==null?null:Integer.parseInt( map.get("type").toString()));
					friendCircleCommentVo.setUser_id(map.get("user_id")==null?null:Long.parseLong( map.get("user_id").toString()));
					
					friendCircleVo.getFriendCircleCommentVos().add(friendCircleCommentVo);
					
					findByPage.remove(j);
					j--;
					
				}
			}
		}
	}

	
	@Override
	public List<FriendCircleVo> friendCircle_query_friend(Long user_id, Long last_friend_circle_id, Integer pageSize,
			Long friend_id) {
		
		FriendCircleJurisdictionVo friendCircleJurisdictionVo = hxUserFriendMapper.query1to1FriendByUse_idAndFriend_id(user_id,friend_id);
		if (friendCircleJurisdictionVo!=null){
			if (friendCircleJurisdictionVo.getFriend_let_me_see_space()!=1){
				throw new RuntimeException("对方不让你看他的朋友圈");
			}
			if(friendCircleJurisdictionVo.getFriend_state()!=1){
				throw new RuntimeException("好友关系不正常");
			}
			if(friendCircleJurisdictionVo.getFriend_to_me_state()!=1){
				throw new RuntimeException("好友关系不正常");
			}
			if (friendCircleJurisdictionVo.getMe_see_friend_space()!=1){
				throw new RuntimeException("你自己设置了不看他的朋友圈");
			}
		}
		
		
		List<FriendCircleVo> friendCircleVos = null;
		
		
		if (friendCircleJurisdictionVo==null){
			//陌生人
			
			//查询好友配置
			HxUserConfig hxUserConfig = hxUserConfigMapper.selectByPrimaryKey(friend_id);
			if (hxUserConfig!=null){
				if (hxUserConfig.getFriend_circle_stranger_see_10()==1){
					//说明非好友能看10条
					MongoQueryCondition condition3 = new MongoQueryCondition(LinkKey.and);
					condition3.add(new MongoQueryValue(MongoOperator.eq, "user_id",friend_id));
					//好友的所有人可看
					condition3.add(new MongoQueryValue(MongoOperator.eq, "can_see_user_id","1"));
					if (last_friend_circle_id!=null&&last_friend_circle_id>0){
						condition3.add(new MongoQueryValue(MongoOperator.lt, "friend_circle_id",last_friend_circle_id));
					}
					
					friendCircleVos = MGByConditionFriendCircleVo(condition3, 1, 10);
				}else{
					return null;
				}
			}else{
				//说明不存在该用户
				throw new RuntimeException("不存在该用户");
			}
		}else{
			
			//好友不让你
			MongoQueryCondition condition8 = new MongoQueryCondition(LinkKey.and);
			condition8.add(new MongoQueryValue(MongoOperator.notregex, "can_see_user_id","n"+user_id+"n"));
			condition8.add(new MongoQueryValue(MongoOperator.ne, "can_see_user_id","0"));
			
			MongoQueryCondition condition3 = new MongoQueryCondition(LinkKey.or);
			//好友的所有人可看
			condition3.add(new MongoQueryValue(MongoOperator.eq, "can_see_user_id","1"));
			//好友的只让你看的
			condition3.add(new MongoQueryValue(MongoOperator.regex, "can_see_user_id","y"+user_id+"y"));
			condition3.add(new MongoQueryValue(MongoOperator.regex, "can_see_user_id","n"));
			MongoQueryCondition condition9 = new MongoQueryCondition(LinkKey.and,condition3,condition8);
				
				MongoQueryCondition condition7 = new MongoQueryCondition(LinkKey.and);
				condition7.add(new MongoQueryValue(MongoOperator.eq, "user_id",friendCircleJurisdictionVo.getFriend_id()));
				
				MongoQueryCondition condition2 = new MongoQueryCondition(LinkKey.and,condition7,condition9);
				
				MongoQueryCondition condition5 = new MongoQueryCondition(LinkKey.and);
				if (last_friend_circle_id!=null&&last_friend_circle_id>0){
					condition5.add(new MongoQueryValue(MongoOperator.lt, "friend_circle_id",last_friend_circle_id));
				}
				if (friendCircleJurisdictionVo.getFriend_circle_friend_see_day()==0){
					//0所有都能看
					
				}else if(friendCircleJurisdictionVo.getFriend_circle_friend_see_day()==1){
					//1 只能看3天内
					Long time = new Date().getTime() - 3*24*60*60*1000;
					condition5.add(new MongoQueryValue(MongoOperator.gt, "create_time",time));
				}else{
					//半年内
					Long time = new Date().getTime() - 133*24*60*60*1000;
					
					condition5.add(new MongoQueryValue(MongoOperator.gt, "create_time",time));
				}
				MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and,condition2,condition5);
				
				
				friendCircleVos = MGByConditionFriendCircleVo(condition, 1, pageSize);
			
			
		}
		
		return friendCircleVos;
		/*HxUserFriend hxUserFriend = hxUserFriendMapper.queryByByUserIdAndFriendId(user_id, friend_id);
		if (hxUserFriend.getFriend_state()!=1||hxUserFriend.getFriend_to_me_state()!=1){
			throw new RuntimeException("无法访问");
		}
		
		//好友的
		MongoQueryCondition condition1 = new MongoQueryCondition(LinkKey.and);
		condition1.add(new MongoQueryValue(MongoOperator.eq, "user_id",friend_id));
		
		//好友能让你看的
		MongoQueryCondition condition2 = new MongoQueryCondition(LinkKey.or);
		//好友的所有人可看
		condition2.add(new MongoQueryValue(MongoOperator.eq, "can_see_user_id",1));
		//好友的只让你看的
		condition2.add(new MongoQueryValue(MongoOperator.regex, "can_see_user_id","y"+user_id+"y"));
		//好友不让你
		condition2.add(new MongoQueryValue(MongoOperator.notregex, "can_see_user_id","n"+user_id+"n"));
	
		//条件组合
		MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and,condition2,condition1);
		//MongoQueryCondition condition = new MongoQueryCondition(LinkKey.or,condition1,condition4);
		
		List<FriendCircleVo> friendCircleVos = MGByConditionFriendCircleVo(condition, pageIndex, pageSize);
		return friendCircleVos;*/
	}

	@Override
	public List<FriendCircleVo> friendCircle_query_own(Long user_id, Long last_friend_circle_id, Integer pageSize) {
		//自己的
		MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
		condition.add(new MongoQueryValue(MongoOperator.eq, "user_id",user_id));
		if (last_friend_circle_id!=null&&last_friend_circle_id>0){
			condition.add(new MongoQueryValue(MongoOperator.lt, "friend_circle_id",last_friend_circle_id));
		}
		
		List<FriendCircleVo> friendCircleVos = MGByConditionFriendCircleVo(condition, 1, pageSize);
		return friendCircleVos;
	}

	@Override
	public FriendCircleComment friendCircle_comment(FriendCircleComment friendCircleComment) throws Exception {
		
		Map<String, Object> findOne2 = MongoService.findOne(HXSystemConfig.MONGO_DB_NAME_friendCircle,HXSystemConfig.MONGO_Collection_NAME_friendCircle, new MongoQueryValue(MongoOperator.eq, "friend_circle_id", friendCircleComment.getFriend_circle_id()), null);
		if (findOne2==null){
			throw new RuntimeException("该朋友圈已删除或被删除");
		}
		//判断是否为点赞
		if (friendCircleComment.getType()==2){
			
			
			List<MongoQueryValue> queryValue = new ArrayList<>();
			queryValue.add(new MongoQueryValue(MongoOperator.eq, "type", 2));
			queryValue.add(new MongoQueryValue(MongoOperator.eq, "friend_circle_id", friendCircleComment.getFriend_circle_id()));
			queryValue.add(new MongoQueryValue(MongoOperator.eq, "user_id", friendCircleComment.getUser_id()));
			Map<String, Object> findOne = MongoService.findOne(HXSystemConfig.MONGO_DB_NAME_friendCircle,HXSystemConfig.MONGO_Collection_NAME_friendCircle_comment, queryValue, null);
			//存在说明点过赞
			if (findOne!=null){
				throw new RuntimeException("已点过赞了");
			}
		}
		Long friendCircleIdAdministrationId = queryMaxId();
		friendCircleComment.setComment_id(friendCircleIdAdministrationId);
		MongoService.insert(HXSystemConfig.MONGO_DB_NAME_friendCircle,HXSystemConfig.MONGO_Collection_NAME_friendCircle_comment, friendCircleComment);
		insertFriendCircleIdAdministration(new FriendCircleIdAdministration(friendCircleIdAdministrationId, new Date().getTime(), "朋友圈评论"));
		return friendCircleComment;
		
	}

	@Override
	public void friendCircle_comment_delete(Long user_id, Long comment_id, Long friend_circle_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("friend_circle_id", friend_circle_id);
		map.put("comment_id", comment_id);
		MongoService.delete(HXSystemConfig.MONGO_DB_NAME_friendCircle, HXSystemConfig.MONGO_Collection_NAME_friendCircle_comment, map);
		
	}
	
	@Transactional
	@Override
	public int let_friend_see_friendCircle(Long user_id, Integer friend_see_me_space, Long friend_id) {
		hxUserFriendMapper.friend_let_friend_see_friendCircle(friend_id,friend_see_me_space,user_id);
		return hxUserFriendMapper.let_friend_see_friendCircle(user_id,friend_see_me_space,friend_id);
		
	}
	
	@Transactional
	@Override
	public int see_friend_friendCircle(Long user_id, Integer me_see_friend_space, Long friend_id) {
		hxUserFriendMapper.friend_see_friend_friendCircle(friend_id,me_see_friend_space,user_id);
		return hxUserFriendMapper.see_friend_friendCircle(user_id,me_see_friend_space,friend_id);
	}

	@Override
	public List<FriendCircleVo> friendCircle_userInfo(Long user_id, Long friend_id) {
		List<FriendCircleVo> friendCircleVos = null;
		if (user_id==friend_id){
			//说明是自己
			MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
			condition.add(new MongoQueryValue(MongoOperator.eq, "user_id",friend_id));
			condition.add(new MongoQueryValue(MongoOperator.eq, "type",2));
			friendCircleVos = MGByConditionFriendCircleVo(condition , 1, 3);
			return friendCircleVos;
		}
		
		FriendCircleJurisdictionVo friendCircleJurisdictionVo = hxUserFriendMapper.query1to1FriendByUse_idAndFriend_id(user_id,friend_id);
		if (friendCircleJurisdictionVo!=null){
			if (friendCircleJurisdictionVo.getFriend_let_me_see_space()!=1){
				throw new RuntimeException("对方不让你看他的朋友圈");
			}
			if(friendCircleJurisdictionVo.getFriend_state()!=1){
				throw new RuntimeException("好友关系不正常");
			}
			if(friendCircleJurisdictionVo.getFriend_to_me_state()!=1){
				throw new RuntimeException("好友关系不正常");
			}
			if (friendCircleJurisdictionVo.getMe_see_friend_space()!=1){
				throw new RuntimeException("你自己设置了不看他的朋友圈");
			}
		}
		
		
		
		
		
		if (friendCircleJurisdictionVo==null){
			//陌生人
			
			//查询好友配置
			HxUserConfig hxUserConfig = hxUserConfigMapper.selectByPrimaryKey(friend_id);
			if (hxUserConfig!=null){
				if (hxUserConfig.getFriend_circle_stranger_see_10()==1){
					//说明非好友能看10条
					MongoQueryCondition condition3 = new MongoQueryCondition(LinkKey.and);
					condition3.add(new MongoQueryValue(MongoOperator.eq, "user_id",friend_id));
					//好友的所有人可看
					condition3.add(new MongoQueryValue(MongoOperator.eq, "can_see_user_id","1"));
					condition3.add(new MongoQueryValue(MongoOperator.eq, "type",2));
					friendCircleVos = MGByConditionFriendCircleVo(condition3, 1, 3);
				}else{
					return null;
				}
			}else{
				//说明不存在该用户
				throw new RuntimeException("不存在该用户");
			}
		}else{
			
			
			//好友不让你
			MongoQueryCondition condition8 = new MongoQueryCondition(LinkKey.and);
			condition8.add(new MongoQueryValue(MongoOperator.notregex, "can_see_user_id","n"+user_id+"n"));
			condition8.add(new MongoQueryValue(MongoOperator.ne, "can_see_user_id","0"));
			
			MongoQueryCondition condition3 = new MongoQueryCondition(LinkKey.or);
			//好友的所有人可看
			condition3.add(new MongoQueryValue(MongoOperator.eq, "can_see_user_id","1"));
			//好友的只让你看的
			condition3.add(new MongoQueryValue(MongoOperator.regex, "can_see_user_id","y"+user_id+"y"));
			condition3.add(new MongoQueryValue(MongoOperator.regex, "can_see_user_id","n"));
			MongoQueryCondition condition9 = new MongoQueryCondition(LinkKey.and,condition3,condition8);
				
				MongoQueryCondition condition7 = new MongoQueryCondition(LinkKey.and);
				condition7.add(new MongoQueryValue(MongoOperator.eq, "user_id",friendCircleJurisdictionVo.getFriend_id()));
				condition7.add(new MongoQueryValue(MongoOperator.eq, "type",2));
				
				MongoQueryCondition condition2 = new MongoQueryCondition(LinkKey.and,condition7,condition9);
				
			
				MongoQueryCondition condition5 = new MongoQueryCondition(LinkKey.and);
				if (friendCircleJurisdictionVo.getFriend_circle_friend_see_day()==0){
					//0所有都能看
					
				}else if(friendCircleJurisdictionVo.getFriend_circle_friend_see_day()==1){
					//1 只能看3天内
					Long time = new Date().getTime() - 3*24*60*60*1000;
					condition5.add(new MongoQueryValue(MongoOperator.gt, "create_time",time));
				}else{
					//半年内
					Long time = new Date().getTime() - 133*24*60*60*1000;
					
					condition5.add(new MongoQueryValue(MongoOperator.gt, "create_time",time));
				}
				MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and,condition2,condition5);
				
				friendCircleVos = MGByConditionFriendCircleVo(condition, 1, 3);
			
			
		}
		
		return friendCircleVos;
	}

	@Override
	public FriendCircleVo query_friendCircle_info(Long friend_circle_id) {
		
		MongoQueryValue queryValue = new MongoQueryValue(MongoOperator.eq, "friend_circle_id", friend_circle_id);
		Map<String, Object> findOne = MongoService.findOne(HXSystemConfig.MONGO_DB_NAME_friendCircle, HXSystemConfig.MONGO_Collection_NAME_friendCircle, queryValue , null);
		if (findOne==null){
			return null;
		}
		
		FriendCircleVo mapToFriendCircleVo = mapToFriendCircleVo(findOne);
		
		List<FriendCircleVo> list = new ArrayList<>();
		list.add(mapToFriendCircleVo);
		
		List<Long> friendCircleVoIDS = new ArrayList<>();
		friendCircleVoIDS.add(mapToFriendCircleVo.getFriend_circle_id());
		
		MGByConditionFriendCircleCommentVo(friendCircleVoIDS, list);
		
		return list.get(0);
	}
}
