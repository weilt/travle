package com.hx.order.service.hx;

import java.util.List;

import com.hwt.domain.entity.order.HwOrderUsuallyUser;
import com.hwt.domain.entity.order.vo.HwOrderUsuallyUserVo;

public interface OrderUsuallyUserService {
	/**
	 * 添加
	 * @param hwOrderUsuallyUser
	 */
	void insert(HwOrderUsuallyUser hwOrderUsuallyUser);

	/**
	 * 查询
	 * @param user_id
	 * @return
	 */
	List<HwOrderUsuallyUserVo> query(Long user_id);

	/**
	 * 查询详情
	 * @param user_id
	 * @param usually_id
	 * @return
	 */
	HwOrderUsuallyUserVo queryDetails(Long user_id, Long usually_id);

	/**
	 * 修改
	 * @param hwOrderUsuallyUserVo
	 */
	void update(HwOrderUsuallyUserVo hwOrderUsuallyUserVo);

	/**
	 * 删除
	 * @param user_id
	 * @param usually_id
	 */
	void delete(Long user_id, Long usually_id);

}
