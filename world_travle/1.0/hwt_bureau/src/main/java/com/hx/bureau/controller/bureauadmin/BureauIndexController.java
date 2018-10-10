package com.hx.bureau.controller.bureauadmin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hx.bureau.base.ResultEntity;
import com.hx.bureau.service.cache.BureauUserCache;
import com.hx.bureau.service.hx.BureauIndexService;
import com.hx.bureau.util.BaseController;

@RestController
public class BureauIndexController extends BaseController{
	@Autowired
	private BureauIndexService indexService;
	
	/**
	 * 首页数据
	 * @return
	 */
	@RequestMapping("bureau/index")
	public ModelAndView index(){
		
		Map<String, Object> map = indexService.query_index(sessionUser().getBureau_id());
		
		//获取上次登录时间
		BureauUserCache sessionUser = sessionUser();
		Long last_login_time = sessionUser.getLast_login_time();
		if (last_login_time!=null){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = simpleDateFormat.format(new Date(last_login_time));
			map.put("last_login_time", format);
		}
		
		return new ModelAndView("bureau/index/index").addObject("map", map);
	}
	
    /**
     * 统计订单
     * @param choose_type   1-今日  2-本周  3-本月
     * @return
     */
//    @RequestMapping("/queryOrderStatistics")
//    public ResultEntity orderStatistics(@RequestParam(defaultValue="1")Integer choose_type)  throws Exception {
//    
//    	Map<String, Object> map = indexService.orderStatistics(sessionUser().getBureau_id(),choose_type);
//    	return new ResultEntity(map);
//    }
    
    /**
     * 近一周订单统计
     * @param choose_type
     * @return
     * @throws Exception
     */
  @RequestMapping("/queryLatelyOrderStatistics")
  public ResultEntity queryLatelyOrderStatistics()  throws Exception {
  
  	Map<String, Object> map = indexService.queryLatelyOrderStatistics(sessionUser().getBureau_id());
  	return new ResultEntity(map);
  }
}
