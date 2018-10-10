package com.hx.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.user.HxUserTeam;
import com.hwt.domain.entity.user.HxUserTeamUser;
import com.hwt.domain.entity.user.Vo.HxUserTeamVo;
import com.hwt.domain.mapper.user.HxUserTeamMapper;
import com.hwt.domain.mapper.user.HxUserTeamUserMapper;
import com.hx.user.service.HxUserTeamService;

@Service
public class HxUserTeamServiceImpl implements HxUserTeamService{
	@Autowired
	private HxUserTeamMapper hxUserTeamMapper;
	
	@Autowired
	private HxUserTeamUserMapper hxUserTeamUserMapper;
	
	
	@Override
	@Transactional
	public void insert_team(HxUserTeam hxUserTeam, String[] im_ids) {
		//添加群
		hxUserTeamMapper.insertSelective(hxUserTeam);
		List<HxUserTeamUser> list = new ArrayList<>();
		
		for (int i = 0; i < im_ids.length; i++) {
			HxUserTeamUser hxUserTeamUser = new HxUserTeamUser();
			hxUserTeamUser.setCreate_time(new Date());
			hxUserTeamUser.setTeam_id(hxUserTeam.getTeam_id());
			hxUserTeamUser.setUser_id_im_id(im_ids[i]);
			if (hxUserTeam.getOwner_id_im_id().equals(im_ids[i])){
				hxUserTeamUser.setType((byte)1);
			}else{
				hxUserTeamUser.setType((byte)3);
			}
			hxUserTeamUser.setState((byte)1);
			hxUserTeamUser.setDisplay_team_nick((byte)0);
			hxUserTeamUser.setIs_shield((byte)0);
			hxUserTeamUser.setTeam_nick(null);
			list.add(hxUserTeamUser);
		}
		//添加群成员		
		hxUserTeamUserMapper.insertList(list);
	}


	@Override
	@Transactional
	public void update_team(HxUserTeam hxUserTeam) {
		hxUserTeamMapper.updateByPrimaryKeySelective(hxUserTeam);
		
	}


	@Override
	public List<HxUserTeamVo> query_HxUserTeamVo_by_team_ids(String[] team_ids) {
		if(team_ids!=null&&team_ids.length>0){
			return hxUserTeamMapper.query_HxUserTeamVo_by_team_ids(team_ids);
		}
		
		return null;
	}


	@Override
	@Transactional
	public void team_delete(String team_id) {
		hxUserTeamMapper.deleteByPrimaryKey(team_id);
		hxUserTeamUserMapper.deleteByTeam_id(team_id);
		
	}


	@Override
	@Transactional
	public void team_add_users(String team_id, String[] im_ids) {
		
		if (im_ids!=null && im_ids.length>0){
			List<HxUserTeamUser> list = new ArrayList<>();
			for (int i = 0; i < im_ids.length; i++) {
				HxUserTeamUser hxUserTeamUser = new HxUserTeamUser();
				hxUserTeamUser.setCreate_time(new Date());
				hxUserTeamUser.setTeam_id(team_id);
				hxUserTeamUser.setUser_id_im_id(im_ids[i]);
				hxUserTeamUser.setType((byte)3);
				hxUserTeamUser.setState((byte)1);
				hxUserTeamUser.setDisplay_team_nick((byte)0);
				hxUserTeamUser.setIs_shield((byte)0);
				hxUserTeamUser.setTeam_nick(null);
				list.add(hxUserTeamUser);
			}
			//添加群成员		
			hxUserTeamUserMapper.insertList(list);
		}else{
			throw new RuntimeException("请选择用户");
		}
	}


	@Override
	@Transactional
	public void team_delete_users(String team_id, String[] user_id_im_ids) {
		if (user_id_im_ids!=null&&user_id_im_ids.length>0){
			
			hxUserTeamUserMapper.team_delete_users(team_id,user_id_im_ids);
		}else{
			throw new RuntimeException("请选择用户");
		}
		
	}


	@Override
	@Transactional
	public void team_update_user(HxUserTeamUser hxUserTeamUser) {
		HxUserTeamUser hxUserTeamUserEdit = hxUserTeamUserMapper.queryByUser_im_idAndTeamId(hxUserTeamUser.getUser_id_im_id(),hxUserTeamUser.getTeam_id());
		hxUserTeamUserMapper.updateByPrimaryKeySelective(hxUserTeamUser);
		
	}

}
