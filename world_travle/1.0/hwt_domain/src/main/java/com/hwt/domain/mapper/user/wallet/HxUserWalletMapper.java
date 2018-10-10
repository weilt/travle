package com.hwt.domain.mapper.user.wallet;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hwt.domain.entity.user.wallet.HxUserWallet;
import com.hwt.domain.entity.user.wallet.Vo.HxUserWalletUserVo;

@Mapper
public interface HxUserWalletMapper {


    int insert(HxUserWallet record);

    int insertSelective(HxUserWallet record);


    int updateByPrimaryKeySelective(HxUserWallet record);

    int updateByPrimaryKey(HxUserWallet record);

	/**
	 * 根据用户查询钱包
	 * @param user_id
	 * @return
	 */
    @Select("select name_id as user_id ,balance, is_can, is_not_can from hx_user_wallet where name_id = #{user_id} and name_type = 1")
	HxUserWalletUserVo query(@Param("user_id")Long user_id);

	/**
	 * 通过用户id查询
	 * @param user_id
	 * @return
	 */
    @Select("select * from hx_user_wallet  where name_id = #{user_id} and name_type = 1 ")
	HxUserWallet selectByUserId(@Param("user_id")Long user_id);

	/**
	 * 增加钱
	 * @param user_id
	 * @param amount
	 */
    @Update("update hx_user_wallet set balance = (balance + #{amount}),is_can = (is_can + #{amount}) where name_id = #{user_id} and name_type = 1 ")
	void addBalanceByUserId(@Param("user_id")Long user_id, @Param("amount")BigDecimal amount);

	/**
	 * 减去钱
	 * @param user_id
	 * @param amount
	 * @return 
	 */
    @Update("update hx_user_wallet set balance = (balance - #{amount}),is_can = (is_can - #{amount}) where name_id = #{user_id} and name_type = 1 and is_can >= #{amount}")
	int safeBalanceByUserId(@Param("user_id")Long user_id, @Param("amount")BigDecimal amount);

	/**
	 * 查询旅行社钱包
	 * @param bureau_id
	 */
    @Select("select * from hx_user_wallet  where name_id = #{bureau_id} and name_type = 2 ")
    HxUserWallet queryBureau(@Param("bureau_id")Long bureau_id);

    /**
     * 减去钱
     * @param bureau_id   
     * @param amount
     * @return
     */
    @Update("update hx_user_wallet set balance = (balance - #{amount}),is_can = (is_can - #{amount}) where name_id = #{bureau_id} and name_type = 2 and is_can >= #{amount}")
	int safeBalanceByBureauId(@Param("bureau_id")Long bureau_id, @Param("amount")BigDecimal amount);

	/**
	 * 收入总和
	 * @param bureau_id
	 * @return
	 */
    @Select("select count(operation_amount) from hx_user_wallet  where name_id = #{bureau_id} and name_type = 2 and bill_type = 3 and is_get = 1")
	BigDecimal soleBureauTatol(@Param("bureau_id")Long bureau_id);

    /**
     * 订单完成加钱
     * @param cicerone_id
     * @param name_type   1-用户 2-旅行社
     * @param payment
     */
    @Update("update hx_user_wallet set balance = (balance + #{amount}),is_not_can = (is_not_can + #{amount}) where name_id = #{name_id} and name_type = #{name_type} ")
	void addBalanceByNameId(@Param("name_id")Long name_id,@Param("name_type") int name_type, @Param("amount")BigDecimal payment);

    /**
     * 订单完成7天 将钱转入可用金额
     * @param name_id
     * @param name_type
     * @param payment
     */
    @Update("update hx_user_wallet set is_can = (is_can + #{amount}),is_not_can = (is_not_can - #{amount}) where name_id = #{name_id} and name_type = #{name_type} and"
    		+ " is_not_can >=#{amount}")
	int is_not_can_to_can(@Param("name_id")Long name_id,@Param("name_type") int name_type, @Param("amount")BigDecimal payment);
	
}