package com.hwt.domain.mapper.bureau;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.bureau.HwTravelBureau;
import com.hwt.domain.entity.bureau.vo.HxBureauBankdAccount;

@Mapper
public interface HwTravelBureauMapper {

    int deleteByPrimaryKey(Long id);

    int insert(HwTravelBureau record);

    int insertSelective(HwTravelBureau record);

    HwTravelBureau selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HwTravelBureau record);

    int updateByPrimaryKey(HwTravelBureau record);

	/**
	 * 通过工商营业执照注册号码 查询
	 * @param license_no
	 * @return
	 */
    @Select("select * from hw_travel_bureau where license_no = #{license_no}  ")
	HwTravelBureau query_by_license_no(@Param("license_no")String license_no);

    /**
     * 根据条件返回总条数
     * @param map
     * @return
     */
	long queryCountByMapToAdmin(Map<String, Object> map);

	/**
     * 根据条件返回数据
     * @param map
     * @return
     */
	List<Map<String, Object>> queryByMapToAdmin(Map<String, Object> map);

	/**
	 * 查询所有返回给后台
	 * @return
	 */
	 @Select("select * from hw_travel_bureau ")
	List<HwTravelBureau> queryAll();

	/**
	 * 通过法人的电话号码查询
	 * @param legal_person_phome
	 * @return
	 */
	 @Select("select * from hw_travel_bureau where legal_person_phome = #{legal_person_phome}  ")
	 HwTravelBureau query_by_legal_person_phome(@Param("legal_person_phome")String legal_person_phome);

	/**
	 * 根据旅行社id 查询账户银行信息
	 * @param bureau_id
	 * @return
	 */
	 @Select("select bureau_bank_account, bank_name, legal_person from hw_travel_bureau where bureau_id = #{bureau_id}  ")
	HxBureauBankdAccount query_bankdAccount(@Param("bureau_id")Long bureau_id);

	/**
	 * 查询旅行社联系人电话
	 * @param bureau_id
	 * @return
	 */
	 @Select("select contacts_phome from hw_travel_bureau where  bureau_id = #{bureau_id}   ")
	String getBureauPhone(@Param("bureau_id")Long bureau_id);

}