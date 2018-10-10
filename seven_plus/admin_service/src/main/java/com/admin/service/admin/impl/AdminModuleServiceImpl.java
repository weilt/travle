package com.admin.service.admin.impl;

import com.admin.service.admin.AdminModuleService;
import com.common.util.ObjectHelper;
import com.domain.admin.mapper.AdminModuleMapper;
import com.domain.admin.entity.AdminModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class AdminModuleServiceImpl implements AdminModuleService {

	@Autowired
	private AdminModuleMapper adminModuleMapper;
	
	public String getRightPathIdToTop(AdminModule module) {
		String result = null;
		if (module != null) {
			if (ObjectHelper.isNotEmpty(module.getParentId()) && module.getParentId() > 0) {
				AdminModule parentModule = adminModuleMapper.findById(module.getParentId());
				result = getRightPathIdToTop(parentModule) + "->"
						+ module.getId() + ":" + module.getName();
			} else {
				result = module.getId() + ":" + module.getName();
			}
		}
		return result;
	}

	public List<Map> getRightPathIdToTop(Map<String, Object> map) {
		List<Map> list = adminModuleMapper.queryListMap(map);
		if(list.size() > 0){
			Iterator<Map> listI = list.iterator();
			while (listI.hasNext()) {
				Map m = listI.next();
				if(ObjectHelper.isNotEmpty(m.get("parentId"))){
					String p = m.get("parentId").toString();
					int parentId = Integer.parseInt(p.indexOf(".") > 0 ? p.substring(0,p.indexOf(".")) : p);
					if(parentId > 0){
						String parentName = adminModuleMapper.findById_name(parentId);
						m.put("parentName", parentName);
					}
				}
			}
		}
		return list;
	}

	@Transactional
	public void deleteModuleParentId(int parentId) {
		List<AdminModule> list = adminModuleMapper.queryList_parentId(parentId);
		if(list.size() > 0){
			Iterator<AdminModule> it = list.iterator();
			while (it.hasNext()) {
				AdminModule adminModule = it.next();
				deleteModuleParentId(adminModule.getId());
			}
			adminModuleMapper.delete_parentId(parentId);
		}
	}

}
