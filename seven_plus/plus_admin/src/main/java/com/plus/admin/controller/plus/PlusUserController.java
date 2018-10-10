package com.plus.admin.controller.plus;

import com.admin.service.plus.PlusUserService;
import com.common.consts.Consts;
import com.common.pakag.PageInfo;
import com.common.util.JsonUtil;
import com.common.util.ObjectHelper;
import com.domain.plus.entity.ExtractCash;
import com.domain.plus.entity.PlusAddress;
import com.domain.plus.entity.PlusUser;
import com.domain.plus.vo.BrokerageVo;
import com.domain.plus.vo.PlusCarVo;
import com.domain.plus.vo.PlusUserVo;
import com.google.common.collect.Lists;
import com.plus.admin.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/8 18:31
 * @Description:
 */
@RestController
public class PlusUserController extends BaseController {


    @Autowired
    private PlusUserService plusUserService;

    @Autowired
    private PageInfo pageInfo;

    /**
     * 用户查询
     * @param phone 电话
     * @param washCount 洗车次数
     * @param nickCount 划痕次数
     * @return
     */
    @RequestMapping("/admin/user/list")
    public ModelAndView userQuery (String phone,Integer washCount, Integer nickCount){
        map.clear();
        int count = plusUserService.countUser(phone,washCount,nickCount);
        pageInfo.format(count,super.request);
        List<PlusUserVo> list = plusUserService.queryUser(phone,washCount,nickCount,pageInfo.getPageNumber(),pageInfo.getPageSize());
        if (seePhone()){
            map.put("phoneSee",1);
        }
        map.put("phone",phone);
        map.put("washCount",washCount);
        map.put("nickCount",nickCount);
        map.put("list",list);
        map.put("pageInfo",pageInfo);
        return new ModelAndView("plus/user/userList").addAllObjects(map);
    }


    /**
     *  添加用户
     * @param phone
     * @return
     */
    @RequestMapping("/admin/user/insert")
    public ModelAndView userInsert(String phone){

        //判断请求是否GET请求
        if (ObjectHelper.getOrPost(super.request)){
            return new ModelAndView("plus/user/userInsert");
        }else {
            PlusUser user = new PlusUser();
            user.setCreateTime(System.currentTimeMillis());
            user.setPhone(phone);
            plusUserService.save(user);
            super.responseJson(JsonUtil.jsonForward("添加成功", "/admin/user/list"));
        }
        return null;
    }


    /**
     * 修改佣金（提现）
     * @param id 会员id
     * @param money  提现金额（提正）
     * @return
     */
    @RequestMapping("/admin/user/setBrokerage")
    public ModelAndView setBrokerage(Long id,Integer money){
        PlusUser plusUser = plusUserService.findById(id);
        //判断请求是否GET请求
        if (ObjectHelper.getOrPost(super.request)){
            map.clear();
            //获取抽佣记录
            List<BrokerageVo> brokerageList = plusUserService.findBrokerageByUserId(plusUser.getId());
            //洗车抽佣
            AtomicReference<Long> washBrokerage = new AtomicReference<>(0L);
            //划痕抽佣
            AtomicReference<Long> nikeBrokerage = new AtomicReference<>(0L);
            //洗车抽佣记录
            List<BrokerageVo> washBrokerageList = Lists.newArrayList();
            //划痕抽佣记录
            List<BrokerageVo> nikeBrokerageList = Lists.newArrayList();
            brokerageList.stream().forEach(l -> {
                if (l.getOrderType() == Consts.PlusOrderType.TYPE_1.getCode()){
                    washBrokerage.updateAndGet(v -> v + l.getBrokerage());
                    washBrokerageList.add(l);
                }else {
                    nikeBrokerage.updateAndGet(v -> v + l.getBrokerage());
                    nikeBrokerageList.add(l);
                }
            });
            List<ExtractCash> extractCashes = plusUserService.findExtractCash(plusUser.getId());
            if (seePhone()){
                map.put("phoneSee",1);
            }
            map.put("washBrokerage",washBrokerage.get());
            map.put("nikeBrokerage",nikeBrokerage.get());
            map.put("washBrokerageList",washBrokerageList);
            map.put("nikeBrokerageList",nikeBrokerageList);
            map.put("extractCashes",extractCashes);
            map.put("entity",plusUser);
            return new ModelAndView("plus/user/brokerage").addAllObjects(map);
        }else {
            if (plusUser.getBrokerage() < money * 100) {
                super.responseJson(JsonUtil.jsonError("佣金金额不足！"));
            } else {
                //extract cash
                plusUserService.setBrokerage(plusUser, money);
                super.responseJson(JsonUtil.jsonRefresh("操作成功！"));
            }
        }
        return null;
    }


    /**
     * 用户详情
     * @param id
     * @return
     */
    @GetMapping("/admin/user/info")
    public ModelAndView userInfo(Long id){
        map.clear();
        //用户信息
        PlusUser plusUser = plusUserService.findById(id);
        //地址信息
        List<PlusAddress> addressList = plusUserService.findAddrByUserId(plusUser.getId());
        //获取抽佣记录
        List<BrokerageVo> brokerageList = plusUserService.findBrokerageByUserId(plusUser.getId());
        //洗车抽佣
        AtomicReference<Long> washBrokerage = new AtomicReference<>(0L);
        //划痕抽佣
        AtomicReference<Long> nikeBrokerage = new AtomicReference<>(0L);
        //洗车抽佣记录
        List<BrokerageVo> washBrokerageList = Lists.newArrayList();
        //划痕抽佣记录
        List<BrokerageVo> nikeBrokerageList = Lists.newArrayList();
        brokerageList.stream().forEach(l -> {
            if (l.getOrderType() == Consts.PlusOrderType.TYPE_1.getCode()){
                washBrokerage.updateAndGet(v -> v + l.getBrokerage());
                washBrokerageList.add(l);
            }else {
                nikeBrokerage.updateAndGet(v -> v + l.getBrokerage());
                nikeBrokerageList.add(l);
            }
        });
        //车辆信息
        List<PlusCarVo> carVoList = plusUserService.findCarByUserId(plusUser.getId());
        if (seePhone()){
            map.put("phoneSee",1);
        }
        map.put("washBrokerage",washBrokerage.get());
        map.put("nikeBrokerage",nikeBrokerage.get());
        map.put("washBrokerageList",washBrokerageList);
        map.put("nikeBrokerageList",nikeBrokerageList);
        map.put("user",plusUser);
        map.put("addressList",addressList);
        map.put("cars",carVoList);
        return new ModelAndView("plus/user/userInfo").addAllObjects(map);
    }

}
