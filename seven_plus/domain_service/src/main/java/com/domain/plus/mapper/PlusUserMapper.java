package com.domain.plus.mapper;

import java.util.List;

import com.domain.plus.vo.PlusUserVo;
import org.apache.ibatis.annotations.Mapper;
import com.domain.plus.entity.PlusUser;
import org.apache.ibatis.annotations.Param;

/**
*  @author zhoudu
*/
@Mapper
public interface PlusUserMapper {

    int insertPlusUser(PlusUser object);

    int updatePlusUser(PlusUser object);

    int update(PlusUser.UpdateBuilder object);

    PlusUser queryById(Long id);

    List<PlusUser> queryPlusUser(PlusUser object);

    PlusUser queryPlusUserLimit1(PlusUser object);

    /**
     * 分页查询
     * @param phone
     * @param washCount
     * @param nickCount
     * @param index
     * @param last
     * @return
     */
    List<PlusUserVo> queryUserVoLimit(@Param("phone") String phone, @Param("washCount") Integer washCount,
                                      @Param("nickCount") Integer nickCount, @Param("index") Integer index, @Param("last") Integer last);

    /**
     * 总条数
     * @param phone
     * @param washCount
     * @param nickCount
     * @return
     */
    Integer countUser(@Param("phone") String phone, @Param("washCount") Integer washCount,
                      @Param("nickCount") Integer nickCount);

    /**
     * 通过openId 获取用户信息
     * @param openId
     * @return
     */
    PlusUser queryUserByOpenId(@Param("openId") String openId );


    /**
     * 通过电话查询用户
     * @param phone
     * @return
     */
    PlusUser queryUserByPhone(@Param("phone")String phone);

    /**
     * 通过id删除
     * @param id
     * @return
     */
    Integer deleteById(@Param("id")Long id);

}

