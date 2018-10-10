package com.xx.service.project.service;

import com.xx.project.entity.InterfaceDocument;

public interface InterfaceDocumentService {

	/**
	 * 添加接口文档
	 * @param interfaceDocument
	 * @return
	 */
	int insert(InterfaceDocument interfaceDocument);

	/**
	 * 修改
	 * @param interfaceDocument
	 * @return
	 */
	int update(InterfaceDocument interfaceDocument);

	/**
	 * 删除/恢复
	 * @param a
	 * @param status
	 * @return
	 */
	int delete(int a, Integer status);

}
