package com.xx.controller.adminBack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xx.adminBack.service.Attack_Service;
import com.xx.controller.util.BaseController;
import com.xx.springBootUtil.util.ObjectHelper;
import com.xx.springBootUtil.validate.ValidateWriteInPost;
import com.xx.util.result.JsonUtil;
import com.xx.util.util.RequestNOUtil;

/**
 * 攻击数据信息
 * @author JIAO_LIU_BABA
 */
@RestController
public class Attack_Controller extends BaseController{

	@Autowired
	private Attack_Service attack_Service;
	
	/**
	 * 直接攻击数据信息
	 * @param getOrPost GET POST请求
	 * @param http_s http://  https://
	 * @param url 地址
	 * @param number 次数
	 * @param threadNumber 线程次数
	 * @return
	 */
	@ValidateWriteInPost(parameter={"getOrPost","url"})
	@RequestMapping("adminBack/attack/addAttack")
	public ModelAndView Attack_ADD(String getOrPost,String http_s,String url,Integer number,Integer threadNumber){
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("adminBack/attack/addAttack");
		}else{
			attack_Service.attack_ADD(getOrPost, http_s, url, number, threadNumber);
			responseJson(JsonUtil.jsonSuccess("请求成功，攻击请求有时间间隔，请稍等再查看攻击对象"));
		}
		return null;
	}
}
