package com.xx.service.project.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xx.project.entity.InterfaceDocument;
import com.xx.project.mapper.InterfaceDocumentMapper;
import com.xx.service.project.service.InterfaceDocumentService;
@Service
public class InterfaceDocumentSeriviceImpl implements InterfaceDocumentService{
	
	@Autowired
	private InterfaceDocumentMapper interfaceDocumentMapper;

	@Override
	public int insert(InterfaceDocument interfaceDocument) {
		
		return interfaceDocumentMapper.insert(interfaceDocument);
	}

	@Override
	public int update(InterfaceDocument interfaceDocument) {
		// TODO Auto-generated method stub
		return interfaceDocumentMapper.update(interfaceDocument);
	}

	@Override
	public int delete(int id, Integer status) {
		// TODO Auto-generated method stub
		return interfaceDocumentMapper.delete(id,status);
	}
}
