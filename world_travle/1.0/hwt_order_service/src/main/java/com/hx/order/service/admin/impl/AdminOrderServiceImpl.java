package com.hx.order.service.admin.impl;

import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.entity.order.vo.*;
import com.hwt.domain.mapper.order.*;
import com.hwt.domain.mapper.user.wallet.HxUserWalletBillMapper;
import com.hwt.domain.mapper.user.wallet.HxUserWalletMapper;
import com.hx.core.utils.ObjectHelper;
import com.hx.order.service.admin.AdminOrderService;
import com.hx.order.service.admin.vo.AdminPageOrder;
import com.hx.order.service.bureau.vo.PageResultOrder;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
@Service
public class AdminOrderServiceImpl implements AdminOrderService {
    @Autowired
    private HxOrderMapper hxOrderMapper;

//    @Autowired
//    private HxOrderMapper hxOrderMapper;

    /**
     * 查询线路订单
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public AdminPageOrder selectQuery(Map<String, Object> map) throws Exception {


        //待确认
        Long status1 = 0l;

        //待开始
        Long status2 = 0l;

        //已完成
        Long status3 = 0l;

        //全部订单
        Long status0 = status1+status2+status3;
        //已完成
        //Long status4 = 0l;

        //退款订单
        //Long status5 = 0l;

        List<HwOrder> hwOrders = hxOrderMapper.selectQuery();
        if (!ObjectHelper.isEmpty(hwOrders)){
            status0 = (long) hwOrders.size();
            for (HwOrder hwOrder : hwOrders) {
                if (hwOrder.getStatus()==1){
                    status1++;
                }
                if (hwOrder.getStatus()==2){//&&System.currentTimeMillis()<hwOrder.getStart_time()
                    status2++;
                }
                if(hwOrder.getStatus()==3){//&&System.currentTimeMillis()>=hwOrder.getStart_time()
                    status3++;
                }
//                if (hwOrder.getStatus()==3){
//                    status4++;
//                }
//                if (hwOrder.getStatus()==4||hwOrder.getStatus()==5){
//                    status5++;
//                }
            }
        }

        String filde = (String) map.get("filde");
        if (StringUtils.isNoneBlank(filde)){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            map.put("filde",format.parse(filde).getTime());
        }
        //满足条件的总条数
        Long count = hxOrderMapper.selectQueryCountByMap(map);

        //满足条件的数据
        List<Map<String, Object>> list = hxOrderMapper.selectQueryByMap(map);

        AdminPageOrder adminPageOrder = new AdminPageOrder();
        adminPageOrder.setCount(count);
        adminPageOrder.setDataList(list);
        adminPageOrder.setStatus0(status0);
        adminPageOrder.setStatus1(status1);
        adminPageOrder.setStatus2(status2);
        adminPageOrder.setStatus3(status3);
        // adminPageOrder.setStatus4(status4);
        // adminPageOrder.setStatus5(status5);

        return adminPageOrder;
    }

    /**
     * 查询导游订单
     * @param map
     * @return
     * @throws ParseException
     * @throws Exception
     */
    @Override
    public AdminPageOrder selectGuideQuery(Map<String, Object> map) throws ParseException, Exception {

        //待确认
        Long status1 = 0l;

        //待开始
        Long status2 = 0l;

        //已完成
        Long status3 = 0l;

        //全部订单
        Long status0 = status1+status2+status3;
        List<HwOrder> hwOrders = hxOrderMapper.selectGuideQuery();
        if (!ObjectHelper.isEmpty(hwOrders)){
            status0 = (long) hwOrders.size();
            for (HwOrder hwOrder : hwOrders) {
                if (hwOrder.getStatus()==1){
                    status1++;
                }
                if (hwOrder.getStatus()==2){//&&System.currentTimeMillis()<hwOrder.getStart_time()
                    status2++;
                }
                if(hwOrder.getStatus()==3){//&&System.currentTimeMillis()>=hwOrder.getStart_time()
                    status3++;
                }
            }
        }
        String filde = (String) map.get("filde");
        if (StringUtils.isNoneBlank(filde)){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            map.put("filde",format.parse(filde).getTime());
        }
        //满足条件的总条数
        Long count = hxOrderMapper.selectGuideQueryCountByMap(map);

        //满足条件的数据
        List<Map<String, Object>> list = hxOrderMapper.selectGuideQueryByMap(map);

        AdminPageOrder adminPageOrder = new AdminPageOrder();
        adminPageOrder.setCount(count);
        adminPageOrder.setDataList(list);
        adminPageOrder.setStatus0(status0);
        adminPageOrder.setStatus1(status1);
        adminPageOrder.setStatus2(status2);
        adminPageOrder.setStatus3(status3);

        return adminPageOrder;
    }

    @Override
    public OrderDetailsVo selectQureyDetails(Long order_id) {
        OrderDetailsVo orderDetailsVo = new OrderDetailsVo();
        //基本信息
        HwOrderVo hwOrderVo = hxOrderMapper.selectByorder_id(order_id);
        if(hwOrderVo == null){
            throw new RuntimeException("该订单不存在");
        }
        orderDetailsVo.setHwOrderVo(hwOrderVo);
        //商品信息
        HxOrderInfoVo hxOrderInfoVo = hxOrderMapper.selectQueryByOrderInfo(order_id);
        orderDetailsVo.setHxOrderInfoVo(hxOrderInfoVo);
        //if (hwOrderVo.getApply_sales_time()!=null||hwOrderVo.getRefundable_time()!=null){
        //说明存在退款情况
        //HwOrderRefundVo hwOrderRefundVo = hxOrderMapper.selectQueryByOrderRefund(order_id);
        // orderDetailsVo.setHwOrderRefundVo(hwOrderRefundVo);
        //}
        //用户信息 保险信息
        List<HwOrderUserVo> hwOrderUserVos =  hxOrderMapper.selectQueryListByOrderUser(order_id);
        orderDetailsVo.setHwOrderUserVos(hwOrderUserVos);
        return orderDetailsVo;
    }

//    @Override
//    public List<OrderEntity> selectAll(@Param("page")Integer page) {
//        List<OrderEntity> list = hxOrderMapper.selectAll(page);
//        return list;
//    }
//
//    @Override
//    public List<OrderEntity> selectPaid(@Param("status")Integer status,
//                                        @Param("page")Integer page) {
//        List<OrderEntity> list = hxOrderMapper.selectPaid(status,page);
//        for (OrderEntity o:list){
//            System.out.println(o.getOrder_id()+"A");
//            System.out.println(o.getOrder_num()+"B");
//            System.out.println(o.getDec()+"C");
//            System.out.println(o.getPaymen_type()+"D");
//            System.out.println(o.getOrderSource()+"E");
//            System.out.println(o.getStatus()+"F");
//            System.out.println(o.getPayment()+"G");
//            System.out.println(o.getPayment_time()+"H");
//            System.out.println(o.getTotal()+"Y");
//        }
//        return list;
//    }
}
