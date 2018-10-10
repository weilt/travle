package com.plus.admin.controller.plus;

import com.admin.service.plus.PlusStoreService;
import com.common.consts.Consts;
import com.common.excption.BaseException;
import com.common.pakag.PageInfo;
import com.common.util.JsonUtil;
import com.common.util.ObjectHelper;
import com.domain.plus.entity.PlusStore;
import com.plus.admin.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/13 17:55
 * @Description: 网点
 */
@RestController
public class PlusStoreController extends BaseController {


    @Autowired
    private PlusStoreService storeService;

    @Autowired
    private PageInfo pageInfo;

    /**
     * 网点列表
     * @param name
     * @param address
     * @param count
     * @return
     */
    @GetMapping("/admin/store/list")
    public ModelAndView storeList(String name,String address,Integer count){
        map.clear();
        map.put("name",name);
        map.put("address",address);
        map.put("count",count);
        int rowcount = storeService.countStore(name,address,count);
        pageInfo.format(rowcount,super.request);
        List<PlusStore> list = storeService.findStore(name,address,count,pageInfo.getPageNumber(),pageInfo.getPageSize());
        map.put("list",list);
        map.put("pageInfo",pageInfo);
        return new ModelAndView("plus/store/storeList").addAllObjects(map);
    }

    /**
     * 添加网点
     * @param storeName 网点名称
     * @param storeAddress 地址
     * @param storePhone 电话
     * @param type 类型
     * @param storeImg 图片
     * @return
     */
    @RequestMapping("/admin/store/add")
    public ModelAndView storeAdd(String storeName,String storeAddress,String storePhone,Integer type, String storeImg){
        if (ObjectHelper.getOrPost(request)){
            return new ModelAndView("plus/store/storeInsert");
        } else {
            PlusStore store = new PlusStore();
            store.setCreateTime(System.currentTimeMillis());
            store.setStoreName(storeName);
            store.setStorePhone(storePhone);
            store.setStoreAddress(storeAddress);
            store.setStoreType(type);
            store.setStoreImg(storeImg);
            try {
                storeService.storeAdd(store);
            }catch (BaseException e){
                super.responseJson(JsonUtil.jsonError("地址输入错误获取经纬度失败，请重新输入"));
                return null;
            }
            super.responseJson(JsonUtil.jsonForward("添加成功！","/admin/store/list"));
        }
        return null;
    }

    /**
     * 修改
     * @param id
     * @param storeName
     * @param storeAddress
     * @param storePhone
     * @param storeImg
     * @return
     */
    @RequestMapping("/admin/store/storeUpdate")
    public ModelAndView storeUpdate(Long id, String storeName,String storeAddress,String storePhone,String storeImg,Integer type){
        PlusStore plusStore = storeService.findById(id);
        if (ObjectHelper.getOrPost(request)){
            map.clear();
            map.put("entity",plusStore);
            return new ModelAndView("plus/store/storeupdate").addAllObjects(map);
        }else {
            plusStore.setStoreType(type);
            plusStore.setStoreName(storeName);
            plusStore.setStoreAddress(storeAddress);
            plusStore.setStorePhone(storePhone);
            plusStore.setStoreImg(storeImg);
            plusStore.setUpdateTime(System.currentTimeMillis());
            storeService.storeUpdate(plusStore);
            responseJson(JsonUtil.jsonForward("修改成功！","/admin/store/list"));
        }
        return null;
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/admin/store/storeInfo")
    public ModelAndView storeInfo(Long id){
        map.clear();
        PlusStore plusStore = storeService.findById(id);
        map.put("entity",plusStore);
        return new ModelAndView("plus/store/storeinfo").addAllObjects(map);
    }

    @GetMapping("/admin/store/getStoreByName")
    public ModelAndView getStoreByName(Integer storeType,String storeName){
        map.clear();
        map.put("storeType",storeType);
        map.put("storeName",storeName);
        if (null == storeType) {
            storeType = Consts.StoreType.nike_type.ordinal();
        }
        int rowcount = storeService.countStoreByName(storeType,storeName);
        pageInfo.format(rowcount,super.request);
        List<PlusStore> list = storeService.getStoreByName(storeType,storeName,pageInfo.getPageNumber(),pageInfo.getPageSize());
        map.put("list",list);
        map.put("pageInfo",pageInfo);
        return new ModelAndView("plus/store/storeByNameList").addAllObjects(map);
    }
}
