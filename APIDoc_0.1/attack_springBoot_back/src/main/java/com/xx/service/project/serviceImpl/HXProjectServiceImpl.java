package com.xx.service.project.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xx.project.entity.HXProject;
import com.xx.project.mapper.HXProjectMapper;
import com.xx.service.project.service.HXProjectService;
@Service
public class HXProjectServiceImpl implements HXProjectService{
	@Autowired
	private HXProjectMapper hxProjectMapper;
	@Override
	@Transactional
	public int insert(HXProject project) {
		
		return hxProjectMapper.insert(project);
	}
	@Override
	@Transactional
	public int update(HXProject project) {
		
		return hxProjectMapper.update(project);
	}
	@Override
	@Transactional
	public int delete(int id, Integer status) {
		// TODO Auto-generated method stub
		return hxProjectMapper.delete(id,status);
	}

}
