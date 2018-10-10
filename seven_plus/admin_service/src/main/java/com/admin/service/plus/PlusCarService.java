package com.admin.service.plus;

import com.domain.plus.vo.PlusCarVo;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/14 15:29
 * @Description:
 */
public interface PlusCarService {

    /**
     *
     * @param phone
     * @return
     */
    Integer countCarByPhone(String phone);

    /**
     *
     * @param phone
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<PlusCarVo> findCarByPhone(String phone, Integer pageNumber, Integer pageSize);

    PlusCarVo findCarById(Long id);
}
