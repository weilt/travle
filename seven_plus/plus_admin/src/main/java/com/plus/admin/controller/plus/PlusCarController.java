package com.plus.admin.controller.plus;

import com.admin.service.plus.PlusCarService;
import com.common.pakag.PageInfo;
import com.domain.plus.vo.PlusCarVo;
import com.plus.admin.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/14 14:36
 * @Description: 车辆管理
 */
@RestController
public class PlusCarController extends BaseController {


    @Autowired
    private PageInfo pageInfo;

    @Autowired
    private PlusCarService plusCarService;

    /**
     * 查询
     * @param phone
     * @return
     */
    @GetMapping("/admin/car/list")
    public ModelAndView carList(String phone){
        map.clear();
        map.put("phone",phone);
        int count = plusCarService.countCarByPhone(phone);
        pageInfo.format(count,request);
        List<PlusCarVo> list = plusCarService.findCarByPhone(phone,pageInfo.getPageNumber(),pageInfo.getPageSize());
        if (seePhone()){
            map.put("phoneSee",1);
        }
        map.put("list",list);
        map.put("pageInfo",pageInfo);
        return new ModelAndView("plus/car/carList").addAllObjects(map);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/admin/car/info")
    public ModelAndView carInfo(Long id){
        map.clear();
        PlusCarVo plusCarVo = plusCarService.findCarById(id);
        if (seePhone()){
            map.put("phoneSee",1);
        }
        map.put("entity",plusCarVo);
        return new ModelAndView("plus/car/carInfo").addAllObjects(map);
    }
}
