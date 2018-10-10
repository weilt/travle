package com.plus.admin.controller.admin;

import com.admin.service.admin.AdminUserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.consts.Consts;
import com.common.excption.BaseAdminException;
import com.common.excption.BaseException;
import com.common.pakag.PageInfo;
import com.common.result.JsonResult;
import com.common.util.*;
import com.common.validate.Clear;
import com.common.validate.ValidateType;
import com.common.validate.ValidateWriteInPost;
import com.domain.admin.mapper.AdminAccesslogMapper;
import com.domain.admin.mapper.AdminRoleMapper;
import com.domain.admin.mapper.AdminSystemConfigMapper;
import com.domain.admin.mapper.AdminUserMapper;
import com.domain.admin.entity.AdminUser;
import com.domain.plus.mapper.OrderRenewMapper;
import com.domain.plus.mapper.PlusStoreMapper;
import com.domain.plus.vo.CountAndDistrictRateVo;
import com.domain.plus.vo.CountAndDistrictVo;
import com.plus.admin.controller.base.BaseController;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AdminUserController extends BaseController {

	@Autowired
	private PageInfo pageInfo;
	@Autowired
	private AdminUserMapper adminUserMapper;
	@Autowired
	private AdminRoleMapper adminRoleMapper;
	@Autowired
	private AdminAccesslogMapper adminAccesslogMapper;
	@Autowired
	private AdminSystemConfigMapper adminSystemConfigMapper;
	@Autowired
	private AdminUserService adminUserService;

	@Autowired
	private PlusStoreMapper storeMapper;

	@Autowired
	private OrderRenewMapper renewMapper;
	
	
	/**
	 * 进入到后台页面信息中
	 * @return
	 */
	@Clear
	@RequestMapping("admin/user/main")
	public ModelAndView main(){
		return new ModelAndView("admin/login/main");
	}
	/**
	 * 获取左边的数据信息
	 * @return
	 */
	@Clear
	@RequestMapping("admin/user/left")
	public ModelAndView main_left(){
		return new ModelAndView("admin/login/left");
	}

	/**
	 * 获取后台网站首页信息
	 * @return
	 */
	@Clear
	@RequestMapping("admin/user/index")
	public ModelAndView main_index(){
		//查询统计数据
		map.clear();
		//网点总条数
		Integer storeCount = storeMapper.queryStoreCount();
		Double totalMoney =  renewMapper.queryTotalMoney()/100.0;
		//网点覆盖
		List<CountAndDistrictVo> districts = storeMapper.queryStoreCountAndDistrict();
		//今日洗车
		List<CountAndDistrictVo> dayWashDistricts = storeMapper.queryStoreCountAndDistrictByTypeAndTime(1, Consts.PlusOrderType.TYPE_1.getCode(),
				DateUtil.getDayBeginDateTime(System.currentTimeMillis()),DateUtil.getDayEndDateTime(System.currentTimeMillis()));
		//今日划痕
		List<CountAndDistrictVo> dayNikeDistricts = storeMapper.queryStoreCountAndDistrictByTypeAndTime(1, Consts.PlusOrderType.TYPE_2.getCode(),
				DateUtil.getDayBeginDateTime(System.currentTimeMillis()),DateUtil.getDayEndDateTime(System.currentTimeMillis()));
		//当月洗车
		List<CountAndDistrictVo> monthWashDistricts = storeMapper.queryStoreCountAndDistrictByTypeAndTime(1, Consts.PlusOrderType.TYPE_1.getCode(),
				DateUtil.getMonthBeginDateTime(System.currentTimeMillis()),DateUtil.getMonthEndDateTime(System.currentTimeMillis()));
		List<CountAndDistrictRateVo> washCountAndDistrictRateVos = new ArrayList<>(dayNikeDistricts.size());
		if (null != monthWashDistricts && !monthWashDistricts.isEmpty()){
			Double totalMonthWash = monthWashDistricts.stream().mapToDouble(CountAndDistrictVo::getValue).sum();
			monthWashDistricts.stream().forEach(l -> {
				CountAndDistrictRateVo vo = new CountAndDistrictRateVo();
				vo.setName(l.getName());
				vo.setValue(l.getValue());
				vo.setRate(PriceUtil.div(l.getValue(),totalMonthWash,4) * 100);
				washCountAndDistrictRateVos.add(vo);
			});
		}
		//当月划痕
		List<CountAndDistrictVo> monthNikeDistricts = storeMapper.queryStoreCountAndDistrictByTypeAndTime(1, Consts.PlusOrderType.TYPE_2.getCode(),
				DateUtil.getMonthBeginDateTime(System.currentTimeMillis()),DateUtil.getMonthEndDateTime(System.currentTimeMillis()));
		List<CountAndDistrictRateVo> nikeCountAndDistrictRateVos = new ArrayList<>(monthNikeDistricts.size());
		if (null != monthNikeDistricts && !monthNikeDistricts.isEmpty()){
			Double totalMonthWash = monthNikeDistricts.stream().mapToDouble(CountAndDistrictVo::getValue).sum();
			monthNikeDistricts.stream().forEach(l -> {
				CountAndDistrictRateVo rateVo = new CountAndDistrictRateVo();
				rateVo.setName(l.getName());
				rateVo.setValue(l.getValue());
				rateVo.setRate( PriceUtil.div(l.getValue(),totalMonthWash,4) * 100);
				nikeCountAndDistrictRateVos.add(rateVo);
			});
		}

		map.put("storeCount", storeCount);
		map.put("totalMoney",totalMoney);
		map.put("districts",JSON.toJSONString(districts));
		map.put("dayWashDistricts",JSON.toJSONString(dayWashDistricts));
		map.put("dayNikeDistricts",JSON.toJSONString(dayNikeDistricts));
		map.put("monthWashDistricts",JSON.toJSONString(washCountAndDistrictRateVos));
		map.put("monthNikeDistricts",JSON.toJSONString(nikeCountAndDistrictRateVos));
		return new ModelAndView("admin/login/index").addAllObjects(map);
	}
	
	/**
	 * 修改密码
	 * @param oldPassword 旧密码
	 * @param password - 新密码
	 * @param conPassword - 确认密码
	 * @return
	 */
	@ValidateWriteInPost(parameter={"oldPassword","password","conPassword"},type = {ValidateType.NULL,ValidateType.NULL,ValidateType.NULL},
			msg = {"旧密码不能为空","新密码不能为空","确认密码不能为空"})
	@RequestMapping("admin/user/passWord")
	public ModelAndView user_editPassWord(String oldPassword,String password,String conPassword){
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminUser/passWord");
		}else{
			if(!password.equals(conPassword)){
				throw new BaseAdminException("确认密码错误");
			}
			if(oldPassword.equals(password)){
				throw new BaseAdminException("旧密码和新密码相同");
			}
			AdminUser adminUser = adminUserMapper.findById(sessionAdminUser().getId());
			if(!adminUser.getPassWord().equals(MessageUtil.encodeMD5(oldPassword))){
				throw new BaseAdminException("旧密码错误");
			}
			password = MessageUtil.encodeMD5(password);
			adminUserMapper.editPassWord(password, adminUser.getId());
			responseJson(JsonUtil.jsonSuccess("修改成功"));
			return null;
		}
	}
	
	/**
	 * 修改个人信息
	 * @return
	 */
	@RequestMapping("admin/adminUser/personalData")
	public ModelAndView user_editInfo(String realName,String sex,String jobPosition,String address,
			String telephone,String aboutMe,String wchat){
		AdminUser adminUser = sessionAdminUser();
		adminUser = adminUserMapper.findById(adminUser.getId());
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminUser/personalData").addObject("adminUser", adminUser);
		}else{
			adminUser.setRealName(realName);
			adminUser.setSex((byte)Integer.parseInt(sex));
			adminUser.setJobPosition(jobPosition);
			adminUser.setAddress(address);
			adminUser.setTelephone(telephone);
			adminUser.setAboutMe(aboutMe);
			adminUser.setWchat(wchat);
			adminUserMapper.update(adminUser);
			responseJson(JsonUtil.jsonSuccess("修改成功！"));
		}
		return null;
	}
	

	/**
	 * 查询 用户信息
	 * @param userName - 用户名
	 * @param realName - 真实姓名
	 * @param sex -性别
	 * @param roleId -角色ID
	 * @param telephone 电话
	 * @param isDelete 是否删除
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("admin/adminUser/query")
	public ModelAndView user_query(String userName,String realName,String sex,String roleId,
			String telephone,String isDelete,ModelAndView modelAndView){
		map.clear();
		map.put("userName", userName);
		map.put("realName", realName);
		map.put("sex", ObjectHelper.isEmpty(sex)? -1:Integer.parseInt(sex));
		map.put("roleId", ObjectHelper.isEmpty(roleId)? -1:Integer.parseInt(roleId));
		map.put("telephone", telephone);
		map.put("isDelete", ObjectHelper.isEmpty(isDelete)? -1:Integer.parseInt(isDelete));
		
		int count = adminUserMapper.queryListCount(map);
		pageInfo.format(count, request); //默认15页 
		map.put("index", pageInfo.getPageSize()*(pageInfo.getPageNumber()-1));
		map.put("last", pageInfo.getPageSize());
		List<Map> list = adminUserMapper.queryList(map);
		
		modelAndView.addObject("roleList", adminRoleMapper.queryAllList());
		modelAndView.addObject("map", map);
		modelAndView.addObject("list", list);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.setViewName("admin/adminUser/userList");
		return modelAndView;
	}
	
	
	/**
	 * 添加用户信息
	 * @param adminUser
	 * @return
	 */
	@ValidateWriteInPost(parameter={"userName","roleId"},msg={"用户名不能为空","请选择角色"})
	@RequestMapping("admin/adminUser/insert")
	public ModelAndView user_insert(AdminUser adminUser){
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminUser/userInsert")
					.addObject("roleList", adminRoleMapper.queryAllList());
		}else{
			adminUserService.AdminUserInsert(adminUser,sessionAdminUser().getId().intValue());
			adminAccesslogMapper.insert(UUIDHelper.createUUId(),"USER添加用户："+adminUser.getUserName(),
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
			responseJson(JsonUtil.jsonForward("添加成功", "/admin/adminUser/query"));
		}
		return null;
	}
	
	/**
	 * 修改用户信息
	 * @param adminUser
	 * @return
	 */
	@RequestMapping("admin/adminUser/update")
	public ModelAndView user_update(AdminUser adminUser){
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminUser/userUpdate")
					.addObject("roleList", adminRoleMapper.queryAllList())
					.addObject("obj", adminUserMapper.findById(adminUser.getId()));
		} else {
			adminUserService.AdminUserUpdate(adminUser);
			adminAccesslogMapper.insert(UUIDHelper.createUUId(),"USER修改用户信息："+adminUser.getUserName(),
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
			responseJson(JsonUtil.jsonForward("修改成功", "/admin/adminUser/query"));
		}
		return null;
	}
	
	/**
	 * 重置用户密码
	 * @return
	 */
	@ValidateWriteInPost(parameter={"userId"},msg={"参数错误"})
	@RequestMapping("admin/adminUser/adreset")
	public JsonResult user_adreset(String userId){
		String resetPassword = adminSystemConfigMapper.getValue("resetPassword");// 系统初始化密码
		if (resetPassword == null || "".equals(resetPassword)) 
			resetPassword = "666666";
		adminUserMapper.editPassWord(ObjectHelper.md5Hex(resetPassword), Long.valueOf(userId));
		
		adminAccesslogMapper.insert(UUIDHelper.createUUId(),"重置用户密码userId："+userId,
				ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
		
		return JsonUtil.jsonSuccess("重置成功，密码是："+resetPassword);
	}
	
	/**
	 * 禁用恢复用户
	 * @return
	 */
	@ValidateWriteInPost(parameter={"userId"},msg={"参数错误"})
	@RequestMapping("admin/adminUser/delete")
	public JsonResult user_delete(String userId){
		Long id = Long.valueOf(userId);
		AdminUser user = adminUserMapper.findById(id);
		if(ObjectHelper.isNotEmpty(user)){
			int isDelete = 1;//1-正常 2-禁用
			if(user.getIsDelete() == 1){
				isDelete = 2;
			}
			adminUserMapper.editIsDelete(isDelete,id);
			adminAccesslogMapper.insert(UUIDHelper.createUUId(),"禁用启用用户userId："+userId,
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
		}
		return JsonUtil.jsonRefresh("操作成功");
	}
	
}
