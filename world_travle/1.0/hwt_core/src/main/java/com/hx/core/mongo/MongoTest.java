package com.hx.core.mongo;

//
//import com.mongodb.client.MongoCollection;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring-config.xml")
public class MongoTest {

    public void xx() {
    }

//
//	@org.junit.Test
//	public void Test() {
//	分页查询
//        List<Map<String,Object>> list = mongoService.findByPage("bbbang","orc",MongoQueryProjections.getInstance(),
//                new MongoQueryCondition(MongoQueryCondition.LinkKey.and,new MongoQueryValue("aa",1))
//                ,new Sort("time2",Sort.DESC),1,2);
//        Long o = mongoService.findPageCount("bbbang","orc",new MongoQueryCondition(MongoQueryCondition.LinkKey.and,new MongoQueryValue("aa",1)));
//        System.out.println(JsonUtils.Bean2Json(list));
//        System.out.println(o);

    /**
     * 删除
     */
    //mongoService.delete("bbbang","orc","aa",2,MongoOperator.gte);

    /**
     * 聚合
     * 分组查询
     */
//        MongoAggregate ma = MongoAggregate.getInstance();
//                // 分组字段
//                MongoAggregateValue  mav = new MongoAggregateValue("_id",new MongoAggregateValue("cc","$aa"));
//                // 获取条数
//                mav.put("count",new MongoAggregateValue(AggregateOperator.sum.code,1));
//                ma.put(AggregateOperator.group,mav);
//                List<Map<String,Object>> list = mongoService.findGroup("bbbang","orc",ma,null,null );
//        System.out.print(JsonUtils.Bean2Json(list));
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block :1487844164429
//			e.printStackTrace();
//		}
//	}
}
