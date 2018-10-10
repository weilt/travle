package com.plus.admin.controller.plus;

import com.admin.service.plus.PlusOrderService;
import com.common.consts.Consts;
import com.common.pakag.PageInfo;
import com.common.util.JsonUtil;
import com.domain.plus.entity.OrderRecord;
import com.domain.plus.vo.OrderRecordVo;
import com.domain.plus.vo.OrderVo;
import com.plus.admin.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/13 11:34
 * @Description:
 */
@RestController
public class PlusOrderController extends BaseController {

    @Autowired
    private PageInfo pageInfo;


    @Autowired
    private PlusOrderService orderService;


    /**
     * 订单列表
     * @param phone
     * @param count
     * @param beginTime
     * @param endTime
     * @return
     */
    @GetMapping("/admin/order/list")
    public ModelAndView orderList(String phone,Integer count,String beginTime,String endTime){
        map.clear();
        map.put("phone",phone);
        map.put("count",count);
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        int rowCount = orderService.countOrder(phone,count,beginTime,endTime);
        pageInfo.format(rowCount,request);
        List<OrderVo> list = orderService.findOrder(phone,count,beginTime,endTime,pageInfo.getPageNumber(),pageInfo.getPageSize());
        if (seePhone()){
            map.put("phoneSee",1);
        }
        map.put("list",list);
        map.put("pageInfo",pageInfo);
        return  new ModelAndView("plus/order/orderList").addAllObjects(map);
    }


    /**
     * 订单详情
     * @param id
     * @return
     */
    @GetMapping("/admin/order/info")
    public ModelAndView orderInfo(Long id) {
        map.clear();
        map = orderService.findById(id,null);
        if (seePhone()){
            map.put("phoneSee",1);
        }
        return new ModelAndView("plus/order/orderInfov").addAllObjects(map);
    }


    /**
     *  天天洗车列表
     * @param phone
     * @param storeName
     * @param storeAddress
     * @return
     */
    @GetMapping("/admin/order/washList")
    public ModelAndView washOrderList(String phone,String storeName, String storeAddress) {
        map.clear();
        map.put("phone",phone);
        map.put("storeName",storeName);
        map.put("storeAddress",storeAddress);
        int rowCount = orderService.countWashOrder(phone,storeName,storeAddress);
        pageInfo.format(rowCount,request);
        List<OrderRecordVo> list = orderService.findWashOrder(phone,storeName,storeAddress,pageInfo.getPageNumber(),pageInfo.getPageSize());
        if (seePhone()){
            map.put("phoneSee",1);
        }
        map.put("list",list);
        map.put("pageInfo",pageInfo);
        return  new ModelAndView("plus/order/washList").addAllObjects(map);
    }


    /**
     *  划痕列表
     * @param phone 电话
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param state 审核状态
     * @return
     */
    @GetMapping("/admin/order/scratch")
    public ModelAndView scratchOrderList(String phone,String beginTime,String endTime,Integer state) {
        map.clear();
        map.put("phone",phone);
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        map.put("state",state);
        int count = orderService.countScratchOrder(phone,beginTime,endTime,state);
        pageInfo.format(count,request);
        List<OrderRecordVo> list = orderService.findScratchOrder(phone,beginTime,endTime,state,pageInfo.getPageNumber(),pageInfo.getPageSize());
        if (seePhone()){
            map.put("phoneSee",1);
        }
        map.put("list",list);
        map.put("pageInfo",pageInfo);
        return  new ModelAndView("plus/order/scratchList").addAllObjects(map);
    }

    /**
     * 订单详情
     * @param id id
     * @return
     */
    @GetMapping("/admin/order/recordOrderInfo")
    public ModelAndView scratchOrderInfo(Long id) {
        //订单使用记录
        OrderRecord record = orderService.findRecordById(id);
        Map<String,Object> data = orderService.findById(record.getOrderId(),record.getStoreId());
        data.put("record",record);
        if (seePhone()){
            data.put("phoneSee",1);
        }
        if (Consts.PlusOrderType.TYPE_1.getCode() == record.getOrderType()){
            //天天洗车
            return new ModelAndView("plus/order/washInfo").addAllObjects(data);
        }else {
            return new ModelAndView("plus/order/scratchInfo").addAllObjects(data);
        }
    }

    /**
     *  划痕审核
     * @return
     */
    @PostMapping("/admin/order/checkScratch")
    public ModelAndView checkScratch(Long id,Long storeId,Integer state) {
        OrderRecord record = orderService.findRecordById(id);
        record.setState(state);
        if (Consts.RecordState.AUDIT_FAILURE.ordinal() != state) {
            record.setStoreId(storeId);
        }
        record.setUpdateTime(System.currentTimeMillis());
        orderService.checkScratch(record);
        super.responseJson(JsonUtil.jsonForward("审核成功", "/admin/order/scratch"));
        return null;
    }
}
