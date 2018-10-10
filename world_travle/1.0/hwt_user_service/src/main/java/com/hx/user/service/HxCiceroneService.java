package com.hx.user.service;

import java.math.BigDecimal;
import java.util.List;

import com.hwt.domain.entity.cicerone.vo.HxCiceroneDetails;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoUserInfo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoVo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoVoInfo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneTypeVo;
import com.hwt.domain.entity.user.Vo.HxYearsVo;
import com.hwt.domain.entity.user.Vo.LoginVo;

/**
 * @Auther: Zhoudu
 * @Date: 2018/7/9 15:43
 * @Description: app导游申请
 */
public interface HxCiceroneService {


    /**
     * 申请导游
     * @param loginVo
     * @param name
     * @param identityNo
     * @param identityPositive
     * @param identityNegative
     * @param identityHold
     * @param areaCity
     * @param areaCityName
     * @return
     */
    Boolean apply(LoginVo loginVo, String name,
                  String identityNo,
                  String identityPositive,
                  String identityNegative,
                  String identityHold,
                  String areaCity,
                  String areaCityName)  throws Exception ;

    /**
     * 导游修改资料
     * @param loginVo
     * @param about
     * @param everyday_price
     * @param sex
     * @param phone
     * @param tag
     * @param ciceroneType
     * @return
     */
    Boolean modify (LoginVo loginVo,String autograph, BigDecimal everyday_price, Integer is_open, Integer sex, String phone, String tag,String workTime, String  ciceroneType);

    /**
     * 获取导游类型列表
     * @return
     */
    List<HxCiceroneTypeVo> getCiceroneType();


    /**
     * 获取导游列表
     * @param cityCode
     * @param sex
     * @param years
     * @param ciceroneType
     * @param orderBy
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<HxCiceroneInfoVo> searchCicerone(String cityCode, Integer sex, Integer years, Long ciceroneType, Integer orderBy, Integer pageIndex, Integer pageSize);

    /**
     * 获取年代
     * @return
     */
    List<HxYearsVo> getYears();
//    /**
//     * 根据用户id返回导游信息
//     * @param user_id
//     * @return
//     */
//    HxCiceroneInfoVo judge_is_cicerone(Long user_id);

	/**
	 * 获取导游资料详情
	 * @param user_id
	 * @return
	 */
	HxCiceroneInfoVoInfo cicerone_own_info(Long user_id);

	/**
	 * 根据导游id获取导游详情
	 * @param id
	 * @param User_id 
	 * @return
	 */
	HxCiceroneDetails cicerone_info(Long id, Long User_id);

	/**
	 * 修改工作时间
	 * @param workTime
	 * @param user_id
	 */
	void update_workTime(String workTime, Long user_id);

	/**
	 * 修改导游的故事
	 * @param about
	 * @param user_id
	 */
	void update_about(String about, Long user_id);

	
	/**
     * 修改导游的封面
     */
	void update_cover(String cover, Long user_id);

	/**
	 * 修改导游的故事和封面
	 * @param about
	 * @param cover
	 * @param user_id
	 */
	void update_about_cover(String about, String cover, Long user_id);

	/**
	 * 获取导游的im_id 等信息
	 * @param cicerone_user_id
	 * @return
	 */
	HxCiceroneInfoUserInfo cicerone_im_id(Long cicerone_user_id);

}
