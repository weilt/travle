package com.hwt.domain.mapper.user.wallet;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.user.wallet.HxUserWalletBankCard;
import com.hwt.domain.entity.user.wallet.Vo.HxUserWalletBankCardUserVo;

@Mapper
public interface HxUserWalletBankCardMapper {

    int deleteByPrimaryKey(Long bank_card_id);

    int insert(HxUserWalletBankCard record);

    int insertSelective(HxUserWalletBankCard record);


    HxUserWalletBankCard selectByPrimaryKey(Long bank_card_id);



    int updateByPrimaryKeySelective(HxUserWalletBankCard record);

    int updateByPrimaryKey(HxUserWalletBankCard record);

	/**
	 * 根据用户id查询所有
	 * @param user_id
	 * @return
	 */
    @Select("select * from hx_user_wallet_bank_card where name_id = #{user_id} and name_type = 1")
	List<HxUserWalletBankCard> selectByUserId(@Param("user_id")Long user_id);

    
    /**
	 * 根据用户id查询所有
	 * @param user_id
	 * @return
	 */
    @Select("select * from hx_user_wallet_bank_card where name_id = #{user_id} and name_type = 1")
	List<HxUserWalletBankCardUserVo> query_bankByUserId(@Param("user_id")Long user_id);

	/**
	 * 通过卡号查询
	 * @param card_num
	 * @return
	 */
    @Select("select * from hx_user_wallet_bank_card where card_num = #{card_num}")
	HxUserWalletBankCard selectByCard_num(@Param("card_num")String card_num);

	/**
	 * 添加 返回主键
	 * @param hxUserWalletBankCard2
	 */
	void insertBackId(HxUserWalletBankCard hxUserWalletBankCard2);

	
}