package com.hx.api.controller.user;

import com.hwt.domain.entity.cicerone.vo.HxCiceroneDetails;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoBaseVo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoUserInfo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoVo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoVoInfo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneTypeVo;
import com.hwt.domain.entity.user.Vo.HxYearsVo;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.core.utils.Enums;
import com.hx.core.utils.ObjectHelper;
import com.hx.user.service.HxCiceroneService;
import com.hx.user.utils.BaseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Zhoudu
 * @Date: 2018/7/9 15:34
 * @Description: 导游申请接口
 */
@Api(value = "API - HxCiceroneController", description = "导游接口")
@RestController
public class HxCiceroneController {


    @Autowired
    private HxCiceroneService hxCiceroneService;


    /**
     * 提交导游申请
     * @param token				用户验证TOKEN
     * @param name				姓名
     * @param identityNo		身份证号码
     * @param identityPositive	身份证正面
     * @param identityNegative	身份证反面
     * @param identityHold		手持证件照
     * @param areaCity			服务区域编码
     * @param areaCityName		服务区域名称
     * @return
     */
    @ApiOperation(value = "提交导游申请", notes = "提交导游申请", response = ResultEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "name", value = "姓名", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "identityNo", value = "身份证号码", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "identityPositive", value = "身份证正面", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "identityNegative", value = "身份证反面", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "identityHold", value = "手持证件照", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "areaCity", value = "服务区域编码", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "areaCityName", value = "服务区域名称", dataType = "string",paramType = "query"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功!")
    })
    @ValidateParam(field={"token"},checkedType = {ValidateParam.CheckedType.TOKEN})
    @PostMapping("cicerone/apply")
    public ResultEntity apply(String token, @RequestParam("name") String name,
                              @RequestParam("identityNo")String identityNo,
                              @RequestParam("identityPositive")String identityPositive,
                              @RequestParam("identityNegative")String identityNegative,
                              @RequestParam("identityHold")String identityHold,
                              @RequestParam("areaCity")String areaCity,
                              @RequestParam("areaCityName")String areaCityName)  throws Exception {
        LoginVo loginVo = BaseUtil.getLoginVo(token);
        hxCiceroneService.apply(loginVo,name,identityNo,identityPositive,identityNegative,identityHold,areaCity,areaCityName);
        return  new ResultEntity();
    }


    /**
     * 修改导游资料
     * @param token			用户验证TOKEN
     * @param about			关于
     * @param cover			封面
     * @param sex			性别 1男  2女
     * @param phone			电话
     * @param tag			标签
     * @param ciceroneType	导游类型（1,2,3）
     * @return
     */
    @ApiOperation(value = "修改导游资料", notes = "修改导游资料", response = ResultEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "autograph", value = "个性签名", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "is_open", value = "是否公开  0-公开   1- 不公开", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "sex", value = "性别 1男  2女", dataType = "integer",paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "电话", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "tag", value = "标签", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "everyday_price", value = "每日价格", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "ciceroneType", value = "导游类型（1,2,3）", dataType = "string",paramType = "query"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功!")
    })
    @ValidateParam(field={"token","phone"},checkedType = {ValidateParam.CheckedType.TOKEN,ValidateParam.CheckedType.MOBIL})
    @PostMapping("cicerone/modify")
    public ResultEntity modify(String token,
                               @RequestParam("autograph") String autograph,
                               @RequestParam("is_open") Integer is_open,
                               @RequestParam("sex")Integer sex,
                               @RequestParam("phone")String phone,
                               @RequestParam("tag")String tag,
                               @RequestParam("everyday_price")BigDecimal everyday_price,
                               @RequestParam("ciceroneType")String ciceroneType){
        LoginVo loginVo = BaseUtil.getLoginVo(token);
        hxCiceroneService.modify(loginVo,autograph,everyday_price,is_open,sex,phone,tag,null,ciceroneType);
        return  new ResultEntity();
    }


    /**
     * 获取导游类型
     *
     * @return
     */
    @ApiOperation(value = "获取导游类型", notes = "获取导游类型", response = ResultEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功!",response = HxCiceroneTypeVo.class)
    })
    @GetMapping("cicerone/type")
    public ResultEntity getCiceroneType(){
        List<HxCiceroneTypeVo > hxCiceroneTypeVo = hxCiceroneService.getCiceroneType();
        return new ResultEntity(hxCiceroneTypeVo);
    }


    
    /**
     * 获取年代
     * @return
     */
    @ApiOperation(value = "获取年代", notes = "获取年代", response = ResultEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功!",response = ResultEntity.class)
    })
    @GetMapping("cicerone/years")
    public ResultEntity getYears(){
        List<HxYearsVo> result = hxCiceroneService.getYears();
        return new ResultEntity(result);
    }


    /**
     * 查询导游
     * @param cityCode		城市编码
     * @param sex			性别 1男  2女
     * @param years			年代（1970，1980，1990）
     * @param ciceroneType	导游类型(接口获取类型)
     * @param orderBy		排序（1服务次数由高到低；2评分由高到低）
     * @param pageIndex		开始页
     * @param pageSize		展示条数
     * @return
     */
    @ApiOperation(value = "查询导游", notes = "查询导游", response = HxCiceroneInfoVo.class)
    @ApiImplicitParams({

            @ApiImplicitParam(name = "cityCode", value = "城市编码", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "sex", value = "性别 1男  2女", defaultValue = "0",dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "years", value = "年代（1970，1980，1990）", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "ciceroneType", value = "导游类型(接口获取类型)",defaultValue = "0", dataType = "long",paramType = "query"),
            @ApiImplicitParam(name = "orderBy", value = "排序（1服务次数由高到低；2评分由高到低）",defaultValue = "0", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "开始页", dataType = "int",required = true,defaultValue = "0",paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "展示条数", dataType = "int",required = true,defaultValue = "10",paramType = "query")

    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功!")
    })
    @PostMapping("cicerone/search")
    public ResultEntity searchCicerone(@RequestParam(value = "cityCode", required = false) String cityCode,
                                       @RequestParam(value = "sex",defaultValue="0", required = false) Integer sex,
                                       @RequestParam(value = "years", required = false) Integer years,
                                       @RequestParam(value = "ciceroneType",defaultValue="0", required = false)Long ciceroneType,
                                       @RequestParam(value = "orderBy", required = false) Integer orderBy,
                                       @RequestParam(value = "pageIndex",defaultValue="1")Integer pageIndex,
                                       @RequestParam(value = "pageSize",defaultValue="10")Integer pageSize){
        List<HxCiceroneInfoVo> result = hxCiceroneService.searchCicerone(cityCode,sex,years,ciceroneType,orderBy,pageIndex,pageSize);
        return new ResultEntity(result);
    }


//    /**
//     * 判断是否是导游
//     * @param token  用户验证TOKEN
//     * @return
//     */
//    @ApiOperation(value = "判断是否是导游", notes = "判断是否是导游", response = HxCiceroneInfoVo.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",paramType = "query"),
//    })
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "成功!")
//    })
//    @PostMapping("cicerone/judge_is_cicerone")
//    @ValidateParam(field={"token"},checkedType = {ValidateParam.CheckedType.TOKEN})
//    public ResultEntity judge_is_cicerone(String token){
//    	LoginVo loginVo = BaseUtil.getLoginVo(token);
//    	HxCiceroneInfoVo hxCiceroneInfoVo = hxCiceroneService.judge_is_cicerone(loginVo.getUser_id());
//		return  new ResultEntity(hxCiceroneInfoVo);
//
//    }
    
    /**
     * 获取自己导游资料详情
     */
    @ApiOperation(value = "获取自己导游资料详情", notes = "获取自己导游资料详情", response = HxCiceroneInfoVoInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",paramType = "query"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功!")
    })
    @PostMapping("cicerone/cicerone_own_info")
    @ValidateParam(field={"token"},checkedType = {ValidateParam.CheckedType.TOKEN})
    public ResultEntity cicerone_own_info(String token){
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	HxCiceroneInfoVoInfo hxCiceroneInfoVoInfo = hxCiceroneService.cicerone_own_info(loginVo.getUser_id());
		return  new ResultEntity(hxCiceroneInfoVoInfo);

    }
    
    /**
     * 获取导游资料详情
     */
    @ApiOperation(value = "获取导游资料详情", notes = "获取导游资料详情", response = HxCiceroneDetails.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",paramType = "query"),
    	@ApiImplicitParam(name = "user_id", value = "导游的用户id", dataType = "string",paramType = "query"),
    })
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "成功!")
    })
    @PostMapping("cicerone/cicerone_info")
    @ValidateParam(field={"token","user_id"},checkedType = {ValidateParam.CheckedType.TOKEN})
    public ResultEntity cicerone_info(String token,Long user_id){
    	Long User_id = null;
    	if (!ObjectHelper.isEmpty(token)){
    		LoginVo loginVo = BaseUtil.getLoginVo(token);
        	
        	if (!ObjectHelper.isEmpty(loginVo)){
        		User_id = loginVo.getUser_id();
        	}
    	}
    	HxCiceroneDetails ciceroneDetails = hxCiceroneService.cicerone_info(user_id,User_id);
    	return  new ResultEntity(ciceroneDetails);
    	
    }
    
    /**
     * 修改导游接单日期
     */
    @ApiOperation(value = "修改导游接单日期", notes = "修改导游接单日期", response = ResultEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "workTime", value = "工作时间（0101,0102,0103,0129）", dataType = "string",paramType = "query"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功!")
    })
    @ValidateParam(field={"token"},checkedType = {ValidateParam.CheckedType.TOKEN})
    @PostMapping("cicerone/update_workTime")
    public ResultEntity update_workTime(String workTime,String token){
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	hxCiceroneService.update_workTime(workTime,loginVo.getUser_id());
    	return  new ResultEntity();
    }
    
    /**
     * 修改导游的故事
     *//*
    @ApiOperation(value = "修改导游的故事", notes = "修改导游的故事", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",paramType = "query"),
    	@ApiImplicitParam(name = "about", value = "关于自己", dataType = "string",paramType = "query"),
    })
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "成功!")
    })
    @ValidateParam(field={"token"},checkedType = {ValidateParam.CheckedType.TOKEN})
    @PostMapping("cicerone/update_about")
    public ResultEntity update_about(String about,String token){
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	hxCiceroneService.update_about(about,loginVo.getUser_id());
    	return  new ResultEntity();
    }
    *//**
     * 修改导游的封面
     *//*
    @ApiOperation(value = "修改导游的封面", notes = "修改导游的封面", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",paramType = "query"),
    	@ApiImplicitParam(name = "cover", value = "封面", dataType = "string",paramType = "query"),
    })
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "成功!")
    })
    @ValidateParam(field={"token"},checkedType = {ValidateParam.CheckedType.TOKEN})
    @PostMapping("cicerone/update_cover")
    public ResultEntity update_cover(String cover,String token){
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	hxCiceroneService.update_cover(cover,loginVo.getUser_id());
    	return  new ResultEntity();
    }*/
    
    
    /**
     * 修改导游的故事和封面
     */
    @ApiOperation(value = " 修改导游的故事和封面", notes = " 修改导游的故事和封面", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",paramType = "query"),
    	@ApiImplicitParam(name = "about", value = "关于自己", dataType = "string",paramType = "query"),
    	@ApiImplicitParam(name = "cover", value = "封面", dataType = "string",paramType = "query"),
    })
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "成功!")
    })
    @ValidateParam(field={"token"},checkedType = {ValidateParam.CheckedType.TOKEN})
    @PostMapping("cicerone/update_about_cover")
    public ResultEntity update_about(String about,String cover,String token){
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	hxCiceroneService.update_about_cover(about,cover,loginVo.getUser_id());
    	return  new ResultEntity();
    }
    
    
	  /**
	  * 获取导游的im_id
	  */
	 @ApiOperation(value = "获取导游的im_id" , notes = "获取导游的im_id", response = HxCiceroneInfoUserInfo.class)
	 @ApiImplicitParams({
	 		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
	         @ApiImplicitParam(name = "cicerone_user_id", value = "导游的用户id", dataType = "string",required = true,paramType = "query"),
	 })
	 @ValidateParam(field={"token","cicerone_user_id"}, checkedType = CheckedType.TOKEN)
		@PostMapping("/cicerone/cicerone_im_id")
	 public ResultEntity cicerone_im_id(Long cicerone_user_id){
	 	HxCiceroneInfoUserInfo hxCiceroneInfoUserInfo = hxCiceroneService.cicerone_im_id(cicerone_user_id);
	 	return new ResultEntity(hxCiceroneInfoUserInfo);
	 }
}
