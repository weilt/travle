package com.hwt.domain.mapper.user.wallet;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.user.wallet.HxUserWalletBill;
import com.hwt.domain.entity.user.wallet.Vo.HxUserWalletBillVo;

@Mapper
public interface HxUserWalletBillMapper {

    int deleteByPrimaryKey(Long bill_id);

    int insert(HxUserWalletBill record);

    int insertSelective(HxUserWalletBill record);


    HxUserWalletBill selectByPrimaryKey(Long bill_id);

    int updateByPrimaryKeySelective(HxUserWalletBill record);

    int updateByPrimaryKey(HxUserWalletBill record);

	/**
	 * 查询账单
	 * @param map
	 * @return
	 */
	List<HxUserWalletBillVo> query_bill_app(Map<String, Object> map);

	/**
	 * 旅行社账单查询
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> query_bill_bureau(Map<String, Object> map);

	/**
	 * 查询旅行社账条数
	 * @param map
	 * @return
	 */
	long query_bill_bureau_count(Map<String, Object> map);

	/**
	 * 通过交易单号查询
	 * @return
	 */
	@Select("select * from hx_user_wallet_bill where trans_num = #{trans_num}")
	HxUserWalletBill selectByTrans_num(@Param("trans_num")String trans_num);
}