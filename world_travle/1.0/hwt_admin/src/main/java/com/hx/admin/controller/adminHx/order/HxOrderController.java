package com.hx.admin.controller.adminHx.order;

import com.hwt.domain.entity.order.vo.OrderDetailsVo;
import com.hx.admin.base.ResultEntity;
import com.hx.admin.validate.ValidateParam;
import com.hx.order.service.admin.AdminOrderService;
import com.hx.order.service.admin.vo.AdminPageOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@RestController
@Api(value = "API - OrderController", description = "订单管理")
public class HxOrderController {
    @Autowired
    private AdminOrderService adminOrderService;

//    @Autowired
//    private BureauOrderService bureauOrderService;

    /**
     * 跳转到订单管理界面
     *
     * @return
     */
    @RequestMapping("admin/order/list")
    public ModelAndView list() {
        return new ModelAndView("adminHx/order/order-list");
    }
    /**
     * 跳转到导游订单管理界面
     *
     * @return
     */
    @RequestMapping("admin/order/guideList")
    public ModelAndView guideList() {
        return new ModelAndView("adminHx/order/order-guideList");
    }

    /**
     * 查询所有订单
     * @return 状态  订单包括 1-待确认 2-待完成 3-已完成  7-失败',
     * 不包括 0-代付款 4-退款 5-退款完成 6-拒绝
     */
//    @RequestMapping("admin/order/selectAll")
//    public ResultEntity selectAll(@RequestParam(defaultValue = "0")Integer page){
//        List<OrderEntity> list = adminOrderService.selectAll(page);
//        if (list.size() == 0){
//            throw new RuntimeException();
//        }
//        return new ResultEntity(list);
//    }

    /**
     * 查询已支付的订单
     *  订单包括 1-待确认 2-待完成 3-已完成  7-失败',
     */
//    @RequestMapping("admin/order/paid")
//    public ResultEntity paid(@RequestParam(defaultValue = "1")Integer status,
//                             @RequestParam(defaultValue = "0")Integer page){
//        List<OrderEntity> list = adminOrderService.selectPaid(status,page);
//        if (list.size() == 0){
//            throw new RuntimeException();
//        }
//        return new ResultEntity(list);
//    }

    /**
     * 查询线路订单
     *
     * @param pageSize  分页大小
     * @param startNum  当前哪一条
     * @param orderBy   排序
     * @param status    0-全部   1-待确认  2-待开始  3-进行中  4-已完成 5-退款订单
     * @param order_num 订单编号
     * @param filde     下单时间(字符串)
     * @return
     */
    @RequestMapping("admin/order/query")
    public ResultEntity query(@RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "0") Integer startNum,
                              @RequestParam(defaultValue = "create_time DESC") String orderBy,
                              @RequestParam(defaultValue = "0", name = "paramMap[status]") Integer status,
                              @RequestParam(name = "paramMap[order_num]", required = false) String order_num,
                              @RequestParam(name = "paramMap[filde]", required = false) String filde) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageSize", pageSize);
        map.put("startNum", startNum);
        map.put("orderBy", orderBy);
        map.put("order_num", order_num);
        map.put("filde", filde);
        map.put("status", status);
        AdminPageOrder adminPageOrder = adminOrderService.selectQuery(map);
        return new ResultEntity(adminPageOrder);
    }

    /**
     * 查询导游订单
     *
     * @param pageSize  分页大小
     * @param startNum  当前哪一条
     * @param orderBy   排序
     * @param status    0-全部   1-待确认  2-待开始  3-进行中  4-已完成 5-退款订单
     * @param order_num 订单编号
     * @param filde     下单时间(字符串)
     * @return
     */
    @RequestMapping("admin/order/guideQuery")
    public ResultEntity guideQuery(@RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "0") Integer startNum,
                              @RequestParam(defaultValue = "create_time DESC") String orderBy,
                              @RequestParam(defaultValue = "0", name = "paramMap[status]") Integer status,
                              @RequestParam(name = "paramMap[order_num]", required = false) String order_num,
                              @RequestParam(name = "paramMap[filde]", required = false) String filde) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageSize", pageSize);
        map.put("startNum", startNum);
        map.put("orderBy", orderBy);
        map.put("order_num", order_num);
        map.put("filde", filde);
        map.put("status", status);
        AdminPageOrder adminPageOrder = adminOrderService.selectGuideQuery(map);
        return new ResultEntity(adminPageOrder);
    }
    /**
     * 订单详情
     */
    @ApiOperation(value = "订单详情", notes = "订单详情", response = OrderDetailsVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "order_id", value = "订单id", dataType = "string", required = true, paramType = "query"),
    })
    @ValidateParam(field = {"order_id"}, message = {"order_id不能为空"})
    @RequestMapping("admin/order/details")
    public ResultEntity details(Long order_id) {
        OrderDetailsVo orderDetailsVo = adminOrderService.selectQureyDetails(order_id);
        return new ResultEntity(orderDetailsVo);

    }


}
