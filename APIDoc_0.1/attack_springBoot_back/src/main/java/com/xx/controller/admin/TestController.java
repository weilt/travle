package com.xx.controller.admin;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xx.admin.dao.AdminModuleDao;
import com.xx.admin.dao.AdminSystemConfigDao;
import com.xx.admin.dao.AdminUserDao;
import com.xx.admin.service.AdminSystemConfigService;
import com.xx.admin.testService.TestTaskService;
import com.xx.springBootUtil.excption.BaseException;
import com.xx.springBootUtil.redis.RedisCache;
import com.xx.springBootUtil.result.JsonResult;
import com.xx.springBootUtil.util.ObjectHelper;
import com.xx.springBootUtil.validate.Clear;
import com.xx.springBootUtil.validate.ValidateType;
import com.xx.springBootUtil.validate.ValidateWriteIn;

@RestController
public class TestController {

	@Autowired
	private TestTaskService testTaskService;
	@Resource
	private AdminSystemConfigDao adminSystemConfigDao;
	@Resource
	private AdminModuleDao adminModuleDao;
	@Autowired
	private AdminSystemConfigService adminSystemConfigService;
	/**
	 * 验证 参数是否为空 ValidateWriteIn
	 * @param test
	 * @return
	 */
	//@ValidateWriteInpost  - 这个是post请求才会验证
	@ValidateWriteIn(parameter = {"test"},type = {ValidateType.NULL},msg = {"test不能为空"})//验证参数
	@RequestMapping("test")
	public Object test(String test){
		
//		if(1==1)
//			throw new BaseException("断开连接");
		//这个是线程池测试
		testTaskService.test();
		//redis验证
		RedisCache.set("aaaa", new Date().getTime()+"");
		System.out.println(RedisCache.get("aaaa"));
		
		String key = adminSystemConfigDao.getValue("systemName");
		System.out.println("dao数据库验证："+key);
		String skey = adminSystemConfigService.getValue("systemName");
		System.out.println("service数据库验证："+skey);
		System.out.println("热启动");
		return new JsonResult("你哈皮吗");
	}
	
	
	//@ValidateWriteIn(parameter = {"test"},type = {ValidateType.NULL},msg = {"test不能为空"})//验证参数
	@RequestMapping("test2")
	public ModelAndView test2(String test){
		
//		ObjectHelper
		//这个是线程池测试
		testTaskService.test();
		//redis验证
		RedisCache.set("aaaa", new Date().getTime()+"");
		System.out.println(RedisCache.get("aaaa"));
		
		String key = adminSystemConfigDao.getValue("systemName");
		System.out.println("dao数据库验证："+key);
		String skey = adminSystemConfigService.getValue("systemName");
		System.out.println("service数据库验证："+skey);
		System.out.println("热启动");
		return new ModelAndView("test/test").addObject("key", key);
	}
	
	@RequestMapping("test3")
	public Object test3(){
		Map<String, Object> map = new HashMap<>();
		int ii = adminModuleDao.queryListMapCount(map);
		System.out.println("测试获取xml中的值queryListMapCount：" + ii);
		
		System.out.println("测试："+adminSystemConfigDao.findById(1).toString());
		
		return "成功";
	}

}
