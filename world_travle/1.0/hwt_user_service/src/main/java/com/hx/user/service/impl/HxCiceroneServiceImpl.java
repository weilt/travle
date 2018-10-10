package com.hx.user.service.impl;

import com.hwt.domain.entity.cicerone.HxCiceroneInfo;
import com.hwt.domain.entity.cicerone.HxCiceroneRelevance;
import com.hwt.domain.entity.cicerone.HxCiceroneType;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneDetails;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoBaseVo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoUserInfo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoVo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoVoInfo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneTypeVo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneWorkTimeVo;
import com.hwt.domain.entity.es.ESQuery;
import com.hwt.domain.entity.mg.cicerone.MgCiceroneInfo;
import com.hwt.domain.entity.user.Vo.HxYearsVo;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hwt.domain.entity.user.collect.HwUserCollect;
import com.hwt.domain.mapper.cicerone.HxCiceroneInfoMapper;
import com.hwt.domain.mapper.cicerone.HxCiceroneRelevanceMapper;
import com.hwt.domain.mapper.cicerone.HxCiceroneTypeMapper;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hwt.domain.mapper.user.collect.HwUserCollectMapper;
import com.hx.core.consts.HxSystemConst;
import com.hx.core.exception.BaseException;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.mongo.value.MongoQueryCondition.LinkKey;
import com.hx.core.redis.RedisCache;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.*;
import com.hx.user.service.HxCiceroneService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;


/**
 * @Auther: Zhoudu
 * @Date: 2018/7/9 16:27
 * @Description:
 */
@Service
public class HxCiceroneServiceImpl implements HxCiceroneService {


    @Autowired
    private HxCiceroneInfoMapper mapper;


    @Autowired
    private HxCiceroneTypeMapper typeMapper;

    @Autowired
    private HxCiceroneRelevanceMapper relevanceMapper;
    
    @Autowired
    private HxUserMapper hxUserMapper;
    
    @Autowired
    private HwUserCollectMapper hwUserCollectMapper;


    @Transactional
    @Override
    public Boolean apply(LoginVo loginVo, String name, String identityNo, String identityPositive,
                         String identityNegative, String identityHold, String areaCity, String areaCityName) throws Exception {
        Long birthday = IDCardUtil.getBirthDay(identityNo);
        if (null == birthday){
            throw new BaseException("身份证号码错误，请重新填写！");
        }
        long currentTimeMillis = System.currentTimeMillis();
        Long userId = loginVo.getUser_id();
		//存在
        HxCiceroneInfo hxCiceroneInfo = mapper.findByUserId(userId );
        if (ObjectHelper.isNotEmpty(hxCiceroneInfo)){
            //审核通过
            if (hxCiceroneInfo.getStatus() == HxSystemConst.CiceroneStatus.STATUS_PASS.getCode()){
                throw new BaseException("审核已通过，请不要重复申请！");
            }
            hxCiceroneInfo.setReal_name(name);
            hxCiceroneInfo.setIdentity_no(identityNo);
            hxCiceroneInfo.setIdentity_positive(identityPositive);
            hxCiceroneInfo.setIdentity_negative(identityNegative);
            hxCiceroneInfo.setIdentity_hold(identityHold);
            hxCiceroneInfo.setService_area_city(areaCity);
            hxCiceroneInfo.setService_area_city_name(areaCityName);
            hxCiceroneInfo.setBirthday(birthday);
            hxCiceroneInfo.setCreate_time(currentTimeMillis);
            hxCiceroneInfo.setStatus(HxSystemConst.CiceroneStatus.STATUS_WAIT.getCode());

            mapper.updateSelective(hxCiceroneInfo);
        }else {
            hxCiceroneInfo = new HxCiceroneInfo();
            hxCiceroneInfo.setUser_id(userId);
            hxCiceroneInfo.setReal_name(name);
            hxCiceroneInfo.setIdentity_no(identityNo);
            hxCiceroneInfo.setIdentity_positive(identityPositive);
            hxCiceroneInfo.setIdentity_negative(identityNegative);
            hxCiceroneInfo.setIdentity_hold(identityHold);
            hxCiceroneInfo.setService_area_city(areaCity);
            hxCiceroneInfo.setService_area_city_name(areaCityName);
            hxCiceroneInfo.setCreate_time(currentTimeMillis);
            hxCiceroneInfo.setBirthday(birthday);
            mapper.insertSelective(hxCiceroneInfo);
        }
        
        //判断mong是否存在
        MongoQueryValue queryValue = new MongoQueryValue(MongoOperator.eq, "user_id", loginVo.getUser_id());
		Map<String, Object> findOne = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_CiceroneInfo, queryValue, null);
        if (findOne==null){
        	MgCiceroneInfo mgCiceroneInfo = new MgCiceroneInfo();
            mgCiceroneInfo.setUser_id(loginVo.getUser_id());
            MongoService.insert(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_CiceroneInfo, mgCiceroneInfo);
        }
		
        
        //判断ESmong是否存在
        MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
        condition.add(new MongoQueryValue(MongoOperator.eq, "name_id", loginVo.getUser_id()));
        condition.add(new MongoQueryValue(MongoOperator.eq, "type", 2));
        Map<String, Object> findOne2 = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, condition, null);
        if (findOne2==null){
        	 ESQuery esQuery = new ESQuery();
             esQuery.setType(2);
             esQuery.setUser_icon(loginVo.getUser_icon());
             esQuery.setName_id(userId);
             esQuery.setName(name);
             esQuery.setCreate_time(currentTimeMillis);
             esQuery.setArea_code(areaCity);
             esQuery.setArea_code_name(areaCityName);
             esQuery.setIs_hide(1);
             MongoService.insertIncId(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, esQuery, "id");
        }else {
        	
        	
        	Map<String, Object> updateMap = new HashMap<>();
        	updateMap.put("user_icon", loginVo.getUser_icon());
        	updateMap.put("name", name);
        	updateMap.put("create_time", currentTimeMillis);
        	updateMap.put("area_code", areaCity);
        	updateMap.put("area_code_name", areaCityName);
        	
			Map<String, Object> filterMap = new HashMap<>();
			filterMap.put("id", findOne2.get("id"));
			MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, filterMap , updateMap );
        }
       
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public Boolean modify(LoginVo loginVo,String autograph, BigDecimal everyday_price, Integer is_open, Integer sex, String phone, String tag,String workTime, String ciceroneType) {
        HxCiceroneInfo hxCicer = mapper.findByUserId(loginVo.getUser_id());
        //Optional<HxCiceroneInfo> optional = Optional.of(hxCicer);
        if (hxCicer==null){
        	 throw new BaseException("请先填写导游申请，在申请通过后再添加您的信息！");
        }
        if ( hxCicer.getStatus() != HxSystemConst.CiceroneStatus.STATUS_PASS.getCode()){
            throw new BaseException("正在审核中，请通过后再添加您的信息！");
        }
        hxCicer.setAutograph(autograph);
        hxCicer.setEveryday_price(everyday_price);
//        hxCicer.setCover(cover);
        hxCicer.setSex(sex);
        hxCicer.setPhone(phone);
        hxCicer.setTag(tag);
        hxCicer.setIs_open(is_open);
       // hxCicer.setWork_time(workTime);
//        hxCicer.setCicerone_type(ciceronType);
//        hxCicer.setStatus(HxSystemConst.CiceroneStatus.STATUS_WAIT.getCode());
        //删除以前的类型
        relevanceMapper.deleteByCicId(loginVo.getUser_id());
        List<HxCiceroneRelevance> ciceroneRelevances = new ArrayList<>();

        if (ObjectHelper.isNotEmpty(ciceroneType)){
            String[] ciceroneTypes = ciceroneType.split(",");
            if (ciceroneTypes.length > 0){
                Arrays.asList(ciceroneTypes).stream().forEach(
                        l -> {
                            HxCiceroneRelevance hxCiceroneRelevance = new HxCiceroneRelevance();
                            hxCiceroneRelevance.setCic_id(loginVo.getUser_id());
                            hxCiceroneRelevance.setType_id(Long.parseLong(l));
                            hxCiceroneRelevance.setCreate_time(System.currentTimeMillis());
                            ciceroneRelevances.add(hxCiceroneRelevance);
                        }
                );
                relevanceMapper.insertCollection(ciceroneRelevances);
            }
        }
        mapper.updateByPrimaryKeySelective(hxCicer);
        
        //修改ESmong
        MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
        condition.add(new MongoQueryValue(MongoOperator.eq, "name_id", loginVo.getUser_id()));
        condition.add(new MongoQueryValue(MongoOperator.eq, "type", 2));
        Map<String, Object> findOne2 = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, condition, null);
    	
        Map<String, Object> updateMap = new HashMap<>();
    	updateMap.put("autograph", autograph);
    	updateMap.put("is_hide", is_open);
    	updateMap.put("price", everyday_price.toString());
    	
		Map<String, Object> filterMap = new HashMap<>();
		filterMap.put("id", findOne2.get("id"));
		MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, filterMap , updateMap );
        
        return Boolean.TRUE;
    }

    @Override
    public List<HxCiceroneTypeVo> getCiceroneType() {
        List<HxCiceroneTypeVo> result = typeMapper.findAll();
        return result;
    }


    @Override
    public List<HxCiceroneInfoVo> searchCicerone(String cityCode, Integer sex, Integer years, Long ciceroneType, Integer orderBy, Integer pageIndex, Integer pageSize) {
        Map<String,Object> param = new HashMap<>();
        param.put("cityCode",cityCode);
        
        param.put("sex",sex);
//        if (null != years && years > 0){
//            Long beginTime = DateUtils.beginYear(years);
//            Long endTime = DateUtils.endYear(years);
//            param.put("startYear",beginTime);
//            param.put("endYear",endTime);
//        }
        if (null != years && years > 0){
            param.put("tag",String.valueOf(years/10).substring(0,1) + "0");
        }
        param.put("ciceroneType",ciceroneType);
        if (null != orderBy && orderBy > 0){
            param.put("orderBy",Enums.OrderBy.getColumn(orderBy));
        }
        param.put("pageIndex",(pageIndex <= 1 ? 0 : pageIndex  - 1) * pageSize);
        param.put("pageSize",pageSize);
        List<HxCiceroneInfoVo> list = mapper.queryByList(param);
        if (null == list || list.isEmpty()){
            return Collections.EMPTY_LIST;
        }
       
        return list;
    }

    /**
     * 判断是否存在
     * @param userId
     * @return
     */
    private boolean exists(final Long userId){
        return mapper.countByUserId(userId) > HxSystemConst.CiceroneStatus.STATUS_WAIT.getCode();
    }

    /**
     * 获取年份
     * @return
     */
    @Override
    public List<HxYearsVo> getYears(){
        String years = RedisCache.get(HxSystemConst.HX_YEAS_KEY);
        List<HxYearsVo> yearsVoList = new ArrayList<>();
        if (StringUtils.isEmpty(years)){
            Map<Integer,String> map = DateUtils.getBeforeYears(4,3);
            List<HxYearsVo> finalYearsVoList = yearsVoList;
            map.keySet().stream().forEach(l -> {
                HxYearsVo vo = new HxYearsVo();
                vo.setYear(l);
                vo.setYearValue(map.get(l));
                finalYearsVoList.add(vo);
            });
            String yearsValue = JsonUtils.Bean2Json(yearsVoList);
            RedisCache.set(HxSystemConst.HX_YEAS_KEY,yearsValue,HxSystemConst.EFFECTIVE_SECONDS);
            return finalYearsVoList;
        }
        yearsVoList = JsonUtils.json2List(years,HxYearsVo.class);

        return yearsVoList;
    }

//    @Override
//    public HxCiceroneInfoVo judge_is_cicerone(Long user_id) {
//
//        HxCiceroneInfoVo hxCiceroneInfoVo = mapper.queryHxCiceroneInfoVoByUserId(user_id);
//        return hxCiceroneInfoVo;
//    }

	@Override
	public HxCiceroneInfoVoInfo cicerone_own_info(Long user_id) {
		HxCiceroneInfoVoInfo hxCiceroneInfoVoInfo = mapper.queryHxCiceroneHxCiceroneInfoVoInfoByUserId(user_id);
		if(hxCiceroneInfoVoInfo!=null){
			List<HxCiceroneTypeVo> hxCiceroneTypeVos = typeMapper.query_by_cicerone_id(user_id);
			if (hxCiceroneTypeVos!=null&&hxCiceroneTypeVos.size()>0){
				hxCiceroneInfoVoInfo.setCiceroneTypeVos(hxCiceroneTypeVos);
			}
		}else{
			
				return null;
			
		}
		MongoQueryValue queryValue = new MongoQueryValue(MongoOperator.eq, "user_id", user_id);
		Map<String, Object> findOne = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_CiceroneInfo, queryValue, null);
		hxCiceroneInfoVoInfo.setAbout(findOne.get("about")==null?null:findOne.get("about").toString());
		hxCiceroneInfoVoInfo.setWork_time(findOne.get("work_time")==null?null:findOne.get("work_time").toString());
		
		return hxCiceroneInfoVoInfo;
	}

	@Override
	public HxCiceroneDetails cicerone_info(Long id, Long User_id) {
		HxCiceroneDetails hxCiceroneDetails = new HxCiceroneDetails();
		HxCiceroneInfoVoInfo hxCiceroneInfoVoInfo = mapper.queryHxCiceroneHxCiceroneInfoVoInfoById(id);
		if(hxCiceroneInfoVoInfo!=null){
			List<HxCiceroneTypeVo> hxCiceroneTypeVos = typeMapper.query_by_cicerone_id(id);
			if (hxCiceroneTypeVos!=null&&hxCiceroneTypeVos.size()>0){
				hxCiceroneInfoVoInfo.setCiceroneTypeVos(hxCiceroneTypeVos);
			}
		}else{
			
				return null;
			
		}
		
		MongoQueryValue queryValue = new MongoQueryValue(MongoOperator.eq, "user_id", id);
		Map<String, Object> findOne = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_CiceroneInfo, queryValue, null);
		hxCiceroneInfoVoInfo.setAbout(findOne.get("about")==null?null:findOne.get("about").toString());
		hxCiceroneInfoVoInfo.setWork_time(findOne.get("work_time")==null?null:findOne.get("work_time").toString());
		
		hxCiceroneDetails.setHxCiceroneInfoVoInfo(hxCiceroneInfoVoInfo);
		hxCiceroneDetails.setIs_collection(0);
		if (!ObjectHelper.isEmpty(User_id)){
			HwUserCollect queryIsCollect = hwUserCollectMapper.queryIsCollect(User_id, id, 2);
			if (queryIsCollect!=null){
				hxCiceroneDetails.setIs_collection(1);
				hxCiceroneDetails.setCollect_id(queryIsCollect.getCollect_id());
			}else {
				hxCiceroneDetails.setIs_collection(0);
			}
		}
		
		return hxCiceroneDetails;
	}

	@Override
	public void update_workTime(String workTime, Long user_id) {
		if (ObjectHelper.isEmpty(workTime)){
			return;
		}
		String[] workTimes = workTime.split(",");
		
		Map<String, Object> WorkTimes = new HashMap<>();
		
		MongoQueryValue queryValue = new MongoQueryValue(MongoOperator.eq, "user_id", user_id);
		Map<String, Object> findOne = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_CiceroneInfo, queryValue , null);
		Object work_time = findOne.get("work_time");
		
		
		//将被下单的时间提出来
		if (work_time!=null){
			Map<String, Object> work_timeMap = JsonUtils.json2Map(work_time.toString());
			Set<String> keySet = work_timeMap.keySet();
			for (String string : keySet) {
				HxCiceroneWorkTimeVo json2Bean = JsonUtils.json2Bean(work_timeMap.get(string).toString(), HxCiceroneWorkTimeVo.class);
				if (json2Bean.getStatus()==1){
					WorkTimes.put(string, JsonUtils.Bean2Json(json2Bean));
				}
			}
		}
		//添加新增是时间
		for (int i = 0; i < workTimes.length; i++) {
			String string = workTimes[i];
			if (ObjectHelper.isEmpty(WorkTimes.get(string))){
				HxCiceroneWorkTimeVo hxCiceroneWorkTimeVo = new HxCiceroneWorkTimeVo();
				hxCiceroneWorkTimeVo.setStatus(0);
				hxCiceroneWorkTimeVo.setWork_time(string);
				WorkTimes.put(string, JsonUtils.Bean2Json(hxCiceroneWorkTimeVo));
			}
		}
		
		//修改
		Map<String, Object> updateMap = new HashMap<>();
    	updateMap.put("work_time", JsonUtils.Bean2Json(WorkTimes));
		Map<String, Object> filterMap = new HashMap<>();
		filterMap.put("user_id", user_id);
		MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_CiceroneInfo, filterMap , updateMap );
		
	}

	@Override
	public void update_about(String about, Long user_id) {
		Map<String, Object> updateMap = new HashMap<>();
    	updateMap.put("about", about);
    	
		Map<String, Object> filterMap = new HashMap<>();
		filterMap.put("user_id", user_id);
		MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_CiceroneInfo, filterMap , updateMap );
	}

	@Override
	@Transactional
	public void update_cover(String cover, Long user_id) {
		mapper.update_cover(cover,user_id);
		
		Map<String, Object> updateMap = new HashMap<>();
    	updateMap.put("image", cover);
    	
		Map<String, Object> filterMap = new HashMap<>();
		filterMap.put("name_id", user_id);
		filterMap.put("type", 2);
		MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, filterMap , updateMap );
	}

	@Override
	@Transactional
	public void update_about_cover(String about, String cover, Long user_id) {
		//MySQL里面数据修改
		mapper.update_cover(cover,user_id);
		
		
		//mong里面数据修改
		Map<String, Object> updateMap = new HashMap<>();
    	updateMap.put("image", cover);
		Map<String, Object> filterMap = new HashMap<>();
		filterMap.put("name_id", user_id);
		filterMap.put("type", 2);
		MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, filterMap , updateMap );
		
		
		Map<String, Object> updateMap1 = new HashMap<>();
		updateMap1.put("about", about);
    	
		Map<String, Object> filterMap1 = new HashMap<>();
		filterMap1.put("user_id", user_id);
		MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_CiceroneInfo, filterMap1 , updateMap1 );
	}

	@Override
	public HxCiceroneInfoUserInfo cicerone_im_id(Long cicerone_user_id) {
		HxCiceroneInfoUserInfo hxCiceroneInfoUserInfo = new HxCiceroneInfoUserInfo();
		 String im_id = hxUserMapper.queryImIdByUserId(cicerone_user_id);
		 if (StringUtils.isBlank(im_id)){
			 throw new RuntimeException("该用户不存在");
		 }
		 hxCiceroneInfoUserInfo.setIm_id(im_id);
		return hxCiceroneInfoUserInfo;
	}

}
