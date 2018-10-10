package com.admin.service.plus;

import com.domain.plus.entity.PlusImg;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/10 14:10
 * @Description:
 */
public interface PlusImgService {

    /**
     *
     * @param types
     * @return
     */
    List<PlusImg> findByTypes(Integer... types);

    /**
     * 保存
     * @param plusImg
     * @return
     */
    Integer save(PlusImg plusImg);

    /**
     * 查询限制
     * @param type 类型
     * @param count 约束条件
     * @return
     */
    Boolean restrict(Integer type, Integer count);


    /**
     * 通过ID查询
     * @param id
     * @return
     */
    PlusImg findById(Long id);

    /**
     * 更新
     * @param plusImg
     * @return
     */
    Integer update(PlusImg plusImg);

    /**
     * 删除
     * @param plusImg
     * @return
     */
    Integer delete(PlusImg plusImg);
}
