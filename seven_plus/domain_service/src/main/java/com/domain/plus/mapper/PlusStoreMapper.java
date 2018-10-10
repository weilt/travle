package com.domain.plus.mapper;

import java.util.List;

import com.domain.plus.vo.CountAndDistrictVo;
import com.domain.plus.vo.StoreVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import com.domain.plus.entity.PlusStore;
import org.apache.ibatis.annotations.Param;

/**
*  @author zhoudu
*/
@Mapper
public interface PlusStoreMapper {

    int insertPlusStore(PlusStore object);

    int updatePlusStore(PlusStore object);

    int update(PlusStore.UpdateBuilder object);

    List<PlusStore> queryPlusStore(PlusStore object);

    PlusStore queryPlusStoreLimit1(PlusStore object);

    Integer countStore(@Param("name") String name, @Param("address")String address, @Param("count")Integer count);

    List<PlusStore> queryStore(@Param("name")String name, @Param("address")String address, @Param("count")Integer count,
                               @Param("index")Integer index, @Param("last")Integer last);

    PlusStore queryStoreById(@Param("id")Long id);

    List<StoreVo> queryStoreByType(@Param("storeType")Integer storeType,@Param("index")Integer index, @Param("last")Integer last);

    List<StoreVo> queryStoreByTypeAndName(@Param("storeType")Integer storeType,@Param("storeName") String storeName,@Param("index")Integer index, @Param("last")Integer last);


    List<PlusStore> queryStoreByNameAndType(@Param("storeType")Integer storeType,@Param("storeName") String storeName,@Param("index")Integer index, @Param("last")Integer last);

    Integer countStoreByNameAndType(@Param("storeType")Integer storeType,@Param("storeName") String storeName);

    /**
     * 分组获取网点条数和区名称
     * @return
     */
    List<CountAndDistrictVo> queryStoreCountAndDistrict();


    /**
     * 网点总条数
     * @return
     */
    Integer queryStoreCount();

    /**
     * 获取网点区域分组
     * @param orderType
     * @param beginTime
     * @param endTime
     * @return
     */
    List<CountAndDistrictVo> queryStoreCountAndDistrictByTypeAndTime(@Param("state")Integer state, @Param("orderType")Integer orderType,
                                                                     @Param("beginTime")Long beginTime, @Param("endTime")Long endTime);
}