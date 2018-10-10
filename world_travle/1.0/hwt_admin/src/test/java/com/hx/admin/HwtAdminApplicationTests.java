package com.hx.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hx.core.mongo.service.MongoService;
import com.hx.core.utils.JsonUtils;
import com.hx.core.utils.ObjectHelper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HwtAdminApplication.class)
public class HwtAdminApplicationTests {
	
	/**
	 * 将分表导入主表
	 * @param args
	 */
	public static void main(String[] args) {
		List<Map<String,Object>> findAll = MongoService.findAll("hwt7","scenic_spot", null, null);
		System.out.println(findAll.size());
		for (Map<String, Object> map : findAll) {
			map.remove("spotId");
			map.remove("_id");
			
			try {
				String bean2Json = JsonUtils.Bean2Json(new HashMap<>(map));
				ScenicSpot json2Bean = JsonUtils.json2Bean(bean2Json, ScenicSpot.class);
				//ScenicSpot map2Obj = ObjectHelper.map2Obj(new HashMap<>(map), ScenicSpot.class);
				
				MongoService.insertIncId("hwt9","scenic_spot", json2Bean, "spotId");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
