package com.hwt.domain.mapper.cicerone;

import com.hwt.domain.entity.cicerone.HxCiceroneRelevance;

import java.util.List;

public interface HxCiceroneRelevanceMapper {


    int insert(HxCiceroneRelevance record);

    int insertSelective(HxCiceroneRelevance record);

    int updateByPrimaryKeySelective(HxCiceroneRelevance record);

    int insertCollection(List<HxCiceroneRelevance> list);

    int updateByPrimaryKey(HxCiceroneRelevance record);

    int deleteByCicId(Long cicId);
}