package com.hx.bureau.controller.bureau;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.user.wallet.HxUserWalletBill;
import com.hx.bureau.base.ResultEntity;
import com.hx.bureau.service.hx.BureauWalletService;
import com.hx.bureau.util.BaseController;
import com.hx.core.page.asyn.PageResult;
import com.hx.core.utils.ObjectHelper;

import io.swagger.annotations.Api;

@Api(value = "API - WalletController", description = "钱包管理")
@RestController
public class WalletController extends BaseController{
	
	@Autowired
	private BureauWalletService bureauWalletService;
	
	/**
	 * 我的账户
	 * @return
	 */
	@GetMapping("bureau/wallet/my")
	public ModelAndView my(){
		Map<String, Object> map = bureauWalletService.query_my(sessionUser().getBureau_id());
		return new ModelAndView("bureau/wallet/my").addObject("map", map);
	}
	
	
	/**
	 * 提现
	 * @param forward  提现金额
	 * @return
	 * @throws Exception 
	 */
	@PostMapping("bureau/wallet/put_forward")
	public ResultEntity put_forward(Long forward) throws Exception{
		if (forward>100){
			if (forward%100!=0){
				
				throw new RuntimeException("提现金额必须大于100，且是100的倍数！");
			}
		}else {
			throw new RuntimeException("提现金额必须大于100，且是100的倍数！");
		}
		
		bureauWalletService.put_forward(forward,sessionUser().getBureau_id());
		return new ResultEntity("200","操作成功");
		
	}
	/**
	 * 资金明细
	 */
	@GetMapping("bureau/wallet/bill-list")
	public ModelAndView bill_list(){
		
		return new ModelAndView("bureau/wallet/bill-list");
	}
	

	/**
	 * 资金明细查询
	 * @param bill_type    0-全部  1-收入 2-提现 3-支出
	 * @param startTime		开始时间
	 * @param endTime		结束时间	
	 * @param pageSize		
	 * @param startNum
	 * @param orderBy		排序
	 * @return
	 * @throws ParseException 
	 */
	@PostMapping("bureau/wallet/bill-query")
	public ResultEntity bill_query(@RequestParam(defaultValue = "0",name="paramMap[bill_type]")Integer bill_type ,
			@RequestParam(name="paramMap[start_time]", required = false)String start_time, 
			@RequestParam(name="paramMap[end_time]", required = false)String end_time,
			@RequestParam(defaultValue = "10")Integer pageSize, @RequestParam(defaultValue = "0")Integer startNum,String orderBy) throws Exception{
		Long startTime = null;
		Long endTime = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (!ObjectHelper.isEmpty(start_time)){
			
			startTime = simpleDateFormat.parse(start_time).getTime();
		}
		if (!ObjectHelper.isEmpty(end_time)){
			endTime = simpleDateFormat.parse(end_time).getTime();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("bill_type", bill_type);
		map.put("startTime", startTime);
	
		map.put("endTime", endTime);
		map.put("bureau_id", sessionUser().getBureau_id());
		map.put("orderBy", orderBy);
		PageResult<Map<String, Object>> pageResult = bureauWalletService.bill_query(map);
		return new ResultEntity(pageResult);
		
	}
	
	/**
	 * 查看账单详情
	 * @param bill_id    账单id
	 */
	@PostMapping("bureau/wallet/bill-info")
	public ResultEntity bill_info(Long bill_id){
		
		HxUserWalletBill hxUserWalletBill = bureauWalletService.bill_info(bill_id,sessionUser().getBureau_id());
		return new ResultEntity(hxUserWalletBill);
	}
}
