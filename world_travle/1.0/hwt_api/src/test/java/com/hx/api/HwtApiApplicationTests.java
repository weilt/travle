package com.hx.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hwt.domain.entity.area.vo.CityVo;
import com.hwt.domain.entity.area.vo.ProvinceCityVo;
import com.hwt.domain.entity.user.Vo.FriendCircleVo;
import com.hwt.domain.mapper.area.AmapAreaCodeMapper;
import com.hx.core.es.entity.EsPage;
import com.hx.core.es.entity.SearchRequestParam;
import com.hx.core.es.utils.ElasticsearchUtils;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.mongo.value.Sort;
import com.hx.core.redis.RedisCache;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.JsonUtils;
import com.hx.scenic.scenicVo.ScenicSpotService;
import com.hx.core.mongo.value.MongoQueryCondition.LinkKey;
import com.hx.user.service.FriendCircleService;

import redis.clients.jedis.GeoRadiusResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HwtApiApplicationTests {
	
	@Autowired
	private FriendCircleService friendCircleService ;
	@Autowired
	private ScenicSpotService scenicSpotService ;
	@Autowired
	private AmapAreaCodeMapper amapAreaCodeMapper;
	
	@Test
	public void aaaaa() throws Exception{
		try {
			ElasticsearchUtils.createIndex(HXSystemConfig.ES_Collection_NAME_hwt);
			
			boolean createMapping = scenicSpotService.createMapping();
			System.out.println(createMapping);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
