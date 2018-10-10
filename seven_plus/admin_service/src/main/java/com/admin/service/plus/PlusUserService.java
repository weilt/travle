package com.admin.service.plus;

import com.domain.plus.entity.ExtractCash;
import com.domain.plus.entity.PlusAddress;
import com.domain.plus.entity.PlusBrokerage;
import com.domain.plus.entity.PlusUser;
import com.domain.plus.vo.BrokerageVo;
import com.domain.plus.vo.PlusCarVo;
import com.domain.plus.vo.PlusUserVo;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/8 18:47
 * @Description:
 */
public interface PlusUserService {


    /**
     * 总条数
     * @param phone
     * @param washCount
     * @param nickCount
     * @return
     */
    Integer countUser(String phone,Integer washCount, Integer nickCount);


    /**
     * 分页查询
     * @param phone
     * @param washCount
     * @param nickCount
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<PlusUserVo> queryUser(String phone,Integer washCount, Integer nickCount,Integer pageNo, Integer pageSize);


    /**
     * 后台添加用户
     * @param user
     * @return
     */
    Integer save(PlusUser user);


    /**
     * 用户信息
     * @param id
     * @return
     */
    PlusUser findById(Long id);


    /**
     * 地址信息
     * @param id
     * @return
     */
    List<PlusAddress> findAddrByUserId(Long id);

    /**
     * 车辆信息
     * @param id
     * @return
     */
    List<PlusCarVo> findCarByUserId(Long id);


    /**
     * 获取佣金记录
     * @param id
     * @return
     */
    List<BrokerageVo> findBrokerageByUserId(Long id);

    /**
     * 修改佣金
     * @param plusUser
     * @param money
     * @return
     */
    Boolean setBrokerage(PlusUser plusUser, Integer money);


    /**
     * 获取提现记录
     * @param id userId
     * @return
     */
    List<ExtractCash> findExtractCash(Long id);
}
