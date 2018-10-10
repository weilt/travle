package com.xx.service.project.service;

import com.xx.project.entity.HXProject;

public interface HXProjectService {

	/**
	 * 添加项目
	 * @param project 项目
	 * @return
	 */
	int insert(HXProject project);

	/**
	 * 修改
	 * @param project
	 * @return
	 */
	int update(HXProject project);

	/**
	 * 更改状态 
	 * @param id
	 * @param status 值
	 * @return
	 */
	int delete(int id, Integer status);

}
