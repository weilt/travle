package com.domain.plus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.domain.plus.entity.PlusImg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

/**
*  @author zhoudu
*/
@Mapper
public interface PlusImgMapper {

    int insertPlusImg(PlusImg object);

    int updatePlusImg(PlusImg object);

    int update(PlusImg.UpdateBuilder object);

    List<PlusImg> queryPlusImg(PlusImg object);

    PlusImg queryPlusImgLimit1(PlusImg object);


    PlusImg findById(@Param("id") Long id);


    /**
     * 类型查找返回一个
     * @param type
     * @return
     */
    PlusImg queryImgByTypeLimit1(@Param("type") Integer type );

    /**
     * 类型查找返回集合
     * @param type
     * @return
     */
    List<PlusImg> queryImgByType(@Param("type") Integer type );

    /**
     * 通过类型查询
     * @param types
     * @return
     */
    List<PlusImg> queryImgByTypes(@Param("types") Integer ... types);


    /**
     * 根据类型查询条数
     * @param type
     * @return
     */
    Integer countType(@Param("type") Integer type);


    Integer deleteImg(PlusImg object);



}