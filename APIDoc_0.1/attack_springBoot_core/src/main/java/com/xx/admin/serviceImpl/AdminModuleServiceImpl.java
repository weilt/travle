package com.xx.admin.serviceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xx.admin.dao.AdminModuleDao;
import com.xx.admin.entity.AdminModule;
import com.xx.admin.service.AdminModuleService;
import com.xx.springBootUtil.util.ObjectHelper;

@Service
public class AdminModuleServiceImpl implements AdminModuleService{

	@Resource
	private AdminModuleDao adminModuleDao;
	
	public String getRightPathIdToTop(AdminModule module) {
		String result = null;
		if (module != null) {
			if (ObjectHelper.isNotEmpty(module.getParentId()) && module.getParentId() > 0) {
				AdminModule parentModule = adminModuleDao.findById(module.getParentId());
				result = getRightPathIdToTop(parentModule) + "->"
						+ module.getId() + ":" + module.getName();
			} else {
				result = module.getId() + ":" + module.getName();
			}
		}
		return result;
	}

	public List<Map> getRightPathIdToTop(Map<String, Object> map) {
		List<Map> list = adminModuleDao.queryListMap(map);
		if(list.size() > 0){
			Iterator<Map> listI = list.iterator();
			while (listI.hasNext()) {
				Map m = listI.next();
				if(ObjectHelper.isNotEmpty(m.get("parentId"))){
					int parentId = Integer.parseInt(m.get("parentId").toString());
					if(parentId > 0){
						String parentName = adminModuleDao.findById_name(parentId);
						m.put("parentName", parentName);
					}
				}
			}
		}
		return list;
	}

	@Transactional
	public void deleteModuleParentId(int parentId) {
		List<AdminModule> list = adminModuleDao.queryList_parentId(parentId);
		if(list.size() > 0){
			Iterator<AdminModule> it = list.iterator();
			while (it.hasNext()) {
				AdminModule adminModule = (AdminModule) it.next();
				deleteModuleParentId(adminModule.getId());
			}
			adminModuleDao.delete_parentId(parentId);
		}
	}

}
