//package com.hwt.domain.mapper.admin;
//
//import java.util.Date;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.hwt.domain.entity.admin.AdminModule;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class AdminModuleMapperTest {
//
//	@Autowired
//	private AdminModuleMapper adminModuleMapper;
//	
//	@Test
//	public void testSelectOneById() {
//		System.out.println(adminModuleMapper.selectOneById(1));
//	}
//
//	@Test
//	public void testSelectAll() {
//		System.out.println(adminModuleMapper.selectAll());
//	}
//
//	@Test
//	public void testSave() {
//		AdminModule adminModule = new AdminModule();
//		adminModule.setCreateTime(new Date());
//		adminModule.setDescription("你好");
//		adminModule.setIsHide(1);
//		adminModule.setModuleImage("/1.jpg");
//		adminModule.setName("仨");
//		adminModule.setSort(1);
//		adminModule.setUrl("/djs/ds");
//		adminModuleMapper.save(adminModule);
//	}
//
//	@Test
//	public void testUpdateById() {
//		AdminModule adminModule = new AdminModule();
//		adminModule.setId(103);
//		adminModule.setCreateTime(new Date());
//		adminModule.setDescription("你好ya");
//		adminModule.setIsHide(1);
//		adminModule.setModuleImage("/1.jpg");
//		adminModule.setName("仨");
//		adminModule.setSort(1);
//		adminModule.setUrl("/djs/ds");
//		adminModuleMapper.updateById(adminModule);
//	}
//
//	@Test
//	public void testRemoveById() {
//		adminModuleMapper.removeById(102);
//	}
//
//}
