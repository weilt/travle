package com.domain.plus.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
*
*  @author zhoudu
*/
public class PlusOrder implements Serializable {

    private static final long serialVersionUID = 1533693426778L;


    /**
    * 主键
    * id自增
    * isNullAble:0
    */
    private Long id;

    /**
    * 会员Id
    * isNullAble:1
    */
    private Long userId;

    /**
    * 车辆信息
    * isNullAble:1
    */
    private Long carId;

    /**
    * 默认0  1：天天洗车 2：划痕无忧
    * isNullAble:1,defaultVal:0
    */
    private Integer orderType;

    /**
    * 总次数
    * isNullAble:1
    */
    private Integer totalCount;

    /**
    * 使用次数
    * isNullAble:1,defaultVal:0
    */
    private Integer useCount;

    /**
    * 到期时间
    * isNullAble:1
    */
    private Long expireTime;

    /**
     * 默认0 正常 1：过期
     */
    private Integer isExpire;

    /**
    * 创建时间
    * isNullAble:1
    */
    private Long createTime;

    /**
    * 更新时间
    * isNullAble:1
    */
    private Long updateTime;


    public Integer getIsExpire() {
        return isExpire;
    }

    public void setIsExpire(Integer isExpire) {
        this.isExpire = isExpire;
    }

    public void setId(Long id){this.id = id;}

    public Long getId(){return this.id;}

    public void setUserId(Long userId){this.userId = userId;}

    public Long getUserId(){return this.userId;}

    public void setCarId(Long carId){this.carId = carId;}

    public Long getCarId(){return this.carId;}

    public void setOrderType(Integer orderType){this.orderType = orderType;}

    public Integer getOrderType(){return this.orderType;}

    public void setTotalCount(Integer totalCount){this.totalCount = totalCount;}

    public Integer getTotalCount(){return this.totalCount;}

    public void setUseCount(Integer useCount){this.useCount = useCount;}

    public Integer getUseCount(){return this.useCount;}

    public void setExpireTime(Long expireTime){this.expireTime = expireTime;}

    public Long getExpireTime(){return this.expireTime;}

    public void setCreateTime(Long createTime){this.createTime = createTime;}

    public Long getCreateTime(){return this.createTime;}

    public void setUpdateTime(Long updateTime){this.updateTime = updateTime;}

    public Long getUpdateTime(){return this.updateTime;}
    @Override
    public String toString() {
        return "PlusOrder{" +
                "id='" + id + '\'' +
                "userId='" + userId + '\'' +
                "carId='" + carId + '\'' +
                "orderType='" + orderType + '\'' +
                "totalCount='" + totalCount + '\'' +
                "useCount='" + useCount + '\'' +
                "expireTime='" + expireTime + '\'' +
                "createTime='" + createTime + '\'' +
                "updateTime='" + updateTime + '\'' +
            '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private PlusOrder set;

        private ConditionBuilder where;

        public UpdateBuilder set(PlusOrder set){
            this.set = set;
            return this;
        }

        public PlusOrder getSet(){
            return this.set;
        }

        public UpdateBuilder where(ConditionBuilder where){
            this.where = where;
            return this;
        }

        public ConditionBuilder getWhere(){
            return this.where;
        }

        public UpdateBuilder build(){
            return this;
        }
    }

    public static class QueryBuilder extends PlusOrder{
        /**
        * 需要返回的列
        */
        private Map<String,Object> fetchFields;

        public Map<String,Object> getFetchFields(){return this.fetchFields;}

        private List<Long> idList;

        public List<Long> getIdList(){return this.idList;}

        private Long idSt;

        private Long idEd;

        public Long getIdSt(){return this.idSt;}

        public Long getIdEd(){return this.idEd;}

        private List<Long> userIdList;

        public List<Long> getUserIdList(){return this.userIdList;}

        private Long userIdSt;

        private Long userIdEd;

        public Long getUserIdSt(){return this.userIdSt;}

        public Long getUserIdEd(){return this.userIdEd;}

        private List<Long> carIdList;

        public List<Long> getCarIdList(){return this.carIdList;}

        private Long carIdSt;

        private Long carIdEd;

        public Long getCarIdSt(){return this.carIdSt;}

        public Long getCarIdEd(){return this.carIdEd;}

        private List<Integer> orderTypeList;

        public List<Integer> getOrderTypeList(){return this.orderTypeList;}

        private Integer orderTypeSt;

        private Integer orderTypeEd;

        public Integer getOrderTypeSt(){return this.orderTypeSt;}

        public Integer getOrderTypeEd(){return this.orderTypeEd;}

        private List<Integer> totalCountList;

        public List<Integer> getTotalCountList(){return this.totalCountList;}

        private Integer totalCountSt;

        private Integer totalCountEd;

        public Integer getTotalCountSt(){return this.totalCountSt;}

        public Integer getTotalCountEd(){return this.totalCountEd;}

        private List<Integer> useCountList;

        public List<Integer> getUseCountList(){return this.useCountList;}

        private Integer useCountSt;

        private Integer useCountEd;

        public Integer getUseCountSt(){return this.useCountSt;}

        public Integer getUseCountEd(){return this.useCountEd;}

        private List<Long> expireTimeList;

        public List<Long> getExpireTimeList(){return this.expireTimeList;}

        private Long expireTimeSt;

        private Long expireTimeEd;

        public Long getExpireTimeSt(){return this.expireTimeSt;}

        public Long getExpireTimeEd(){return this.expireTimeEd;}

        private List<Long> createTimeList;

        public List<Long> getCreateTimeList(){return this.createTimeList;}

        private Long createTimeSt;

        private Long createTimeEd;

        public Long getCreateTimeSt(){return this.createTimeSt;}

        public Long getCreateTimeEd(){return this.createTimeEd;}

        private List<Long> updateTimeList;

        public List<Long> getUpdateTimeList(){return this.updateTimeList;}

        private Long updateTimeSt;

        private Long updateTimeEd;

        public Long getUpdateTimeSt(){return this.updateTimeSt;}

        public Long getUpdateTimeEd(){return this.updateTimeEd;}

        private QueryBuilder (){
            this.fetchFields = new HashMap<>();
        }

        public QueryBuilder idBetWeen(Long idSt,Long idEd){
            this.idSt = idSt;
            this.idEd = idEd;
            return this;
        }

        public QueryBuilder idGreaterEqThan(Long idSt){
            this.idSt = idSt;
            return this;
        }
        public QueryBuilder idLessEqThan(Long idEd){
            this.idEd = idEd;
            return this;
        }


        public QueryBuilder id(Long id){
            setId(id);
            return this;
        }

        public QueryBuilder idList(Long ... id){
            this.idList = solveNullList(id);
            return this;
        }

        public QueryBuilder idList(List<Long> id){
            this.idList = id;
            return this;
        }

        public QueryBuilder fetchId(){
            setFetchFields("fetchFields","id");
            return this;
        }

        public QueryBuilder excludeId(){
            setFetchFields("excludeFields","id");
            return this;
        }

        public QueryBuilder userIdBetWeen(Long userIdSt,Long userIdEd){
            this.userIdSt = userIdSt;
            this.userIdEd = userIdEd;
            return this;
        }

        public QueryBuilder userIdGreaterEqThan(Long userIdSt){
            this.userIdSt = userIdSt;
            return this;
        }
        public QueryBuilder userIdLessEqThan(Long userIdEd){
            this.userIdEd = userIdEd;
            return this;
        }


        public QueryBuilder userId(Long userId){
            setUserId(userId);
            return this;
        }

        public QueryBuilder userIdList(Long ... userId){
            this.userIdList = solveNullList(userId);
            return this;
        }

        public QueryBuilder userIdList(List<Long> userId){
            this.userIdList = userId;
            return this;
        }

        public QueryBuilder fetchUserId(){
            setFetchFields("fetchFields","userId");
            return this;
        }

        public QueryBuilder excludeUserId(){
            setFetchFields("excludeFields","userId");
            return this;
        }

        public QueryBuilder carIdBetWeen(Long carIdSt,Long carIdEd){
            this.carIdSt = carIdSt;
            this.carIdEd = carIdEd;
            return this;
        }

        public QueryBuilder carIdGreaterEqThan(Long carIdSt){
            this.carIdSt = carIdSt;
            return this;
        }
        public QueryBuilder carIdLessEqThan(Long carIdEd){
            this.carIdEd = carIdEd;
            return this;
        }


        public QueryBuilder carId(Long carId){
            setCarId(carId);
            return this;
        }

        public QueryBuilder carIdList(Long ... carId){
            this.carIdList = solveNullList(carId);
            return this;
        }

        public QueryBuilder carIdList(List<Long> carId){
            this.carIdList = carId;
            return this;
        }

        public QueryBuilder fetchCarId(){
            setFetchFields("fetchFields","carId");
            return this;
        }

        public QueryBuilder excludeCarId(){
            setFetchFields("excludeFields","carId");
            return this;
        }

        public QueryBuilder orderTypeBetWeen(Integer orderTypeSt,Integer orderTypeEd){
            this.orderTypeSt = orderTypeSt;
            this.orderTypeEd = orderTypeEd;
            return this;
        }

        public QueryBuilder orderTypeGreaterEqThan(Integer orderTypeSt){
            this.orderTypeSt = orderTypeSt;
            return this;
        }
        public QueryBuilder orderTypeLessEqThan(Integer orderTypeEd){
            this.orderTypeEd = orderTypeEd;
            return this;
        }


        public QueryBuilder orderType(Integer orderType){
            setOrderType(orderType);
            return this;
        }

        public QueryBuilder orderTypeList(Integer ... orderType){
            this.orderTypeList = solveNullList(orderType);
            return this;
        }

        public QueryBuilder orderTypeList(List<Integer> orderType){
            this.orderTypeList = orderType;
            return this;
        }

        public QueryBuilder fetchOrderType(){
            setFetchFields("fetchFields","orderType");
            return this;
        }

        public QueryBuilder excludeOrderType(){
            setFetchFields("excludeFields","orderType");
            return this;
        }

        public QueryBuilder totalCountBetWeen(Integer totalCountSt,Integer totalCountEd){
            this.totalCountSt = totalCountSt;
            this.totalCountEd = totalCountEd;
            return this;
        }

        public QueryBuilder totalCountGreaterEqThan(Integer totalCountSt){
            this.totalCountSt = totalCountSt;
            return this;
        }
        public QueryBuilder totalCountLessEqThan(Integer totalCountEd){
            this.totalCountEd = totalCountEd;
            return this;
        }


        public QueryBuilder totalCount(Integer totalCount){
            setTotalCount(totalCount);
            return this;
        }

        public QueryBuilder totalCountList(Integer ... totalCount){
            this.totalCountList = solveNullList(totalCount);
            return this;
        }

        public QueryBuilder totalCountList(List<Integer> totalCount){
            this.totalCountList = totalCount;
            return this;
        }

        public QueryBuilder fetchTotalCount(){
            setFetchFields("fetchFields","totalCount");
            return this;
        }

        public QueryBuilder excludeTotalCount(){
            setFetchFields("excludeFields","totalCount");
            return this;
        }

        public QueryBuilder useCountBetWeen(Integer useCountSt,Integer useCountEd){
            this.useCountSt = useCountSt;
            this.useCountEd = useCountEd;
            return this;
        }

        public QueryBuilder useCountGreaterEqThan(Integer useCountSt){
            this.useCountSt = useCountSt;
            return this;
        }
        public QueryBuilder useCountLessEqThan(Integer useCountEd){
            this.useCountEd = useCountEd;
            return this;
        }


        public QueryBuilder useCount(Integer useCount){
            setUseCount(useCount);
            return this;
        }

        public QueryBuilder useCountList(Integer ... useCount){
            this.useCountList = solveNullList(useCount);
            return this;
        }

        public QueryBuilder useCountList(List<Integer> useCount){
            this.useCountList = useCount;
            return this;
        }

        public QueryBuilder fetchUseCount(){
            setFetchFields("fetchFields","useCount");
            return this;
        }

        public QueryBuilder excludeUseCount(){
            setFetchFields("excludeFields","useCount");
            return this;
        }

        public QueryBuilder expireTimeBetWeen(Long expireTimeSt,Long expireTimeEd){
            this.expireTimeSt = expireTimeSt;
            this.expireTimeEd = expireTimeEd;
            return this;
        }

        public QueryBuilder expireTimeGreaterEqThan(Long expireTimeSt){
            this.expireTimeSt = expireTimeSt;
            return this;
        }
        public QueryBuilder expireTimeLessEqThan(Long expireTimeEd){
            this.expireTimeEd = expireTimeEd;
            return this;
        }


        public QueryBuilder expireTime(Long expireTime){
            setExpireTime(expireTime);
            return this;
        }

        public QueryBuilder expireTimeList(Long ... expireTime){
            this.expireTimeList = solveNullList(expireTime);
            return this;
        }

        public QueryBuilder expireTimeList(List<Long> expireTime){
            this.expireTimeList = expireTime;
            return this;
        }

        public QueryBuilder fetchExpireTime(){
            setFetchFields("fetchFields","expireTime");
            return this;
        }

        public QueryBuilder excludeExpireTime(){
            setFetchFields("excludeFields","expireTime");
            return this;
        }

        public QueryBuilder createTimeBetWeen(Long createTimeSt,Long createTimeEd){
            this.createTimeSt = createTimeSt;
            this.createTimeEd = createTimeEd;
            return this;
        }

        public QueryBuilder createTimeGreaterEqThan(Long createTimeSt){
            this.createTimeSt = createTimeSt;
            return this;
        }
        public QueryBuilder createTimeLessEqThan(Long createTimeEd){
            this.createTimeEd = createTimeEd;
            return this;
        }


        public QueryBuilder createTime(Long createTime){
            setCreateTime(createTime);
            return this;
        }

        public QueryBuilder createTimeList(Long ... createTime){
            this.createTimeList = solveNullList(createTime);
            return this;
        }

        public QueryBuilder createTimeList(List<Long> createTime){
            this.createTimeList = createTime;
            return this;
        }

        public QueryBuilder fetchCreateTime(){
            setFetchFields("fetchFields","createTime");
            return this;
        }

        public QueryBuilder excludeCreateTime(){
            setFetchFields("excludeFields","createTime");
            return this;
        }

        public QueryBuilder updateTimeBetWeen(Long updateTimeSt,Long updateTimeEd){
            this.updateTimeSt = updateTimeSt;
            this.updateTimeEd = updateTimeEd;
            return this;
        }

        public QueryBuilder updateTimeGreaterEqThan(Long updateTimeSt){
            this.updateTimeSt = updateTimeSt;
            return this;
        }
        public QueryBuilder updateTimeLessEqThan(Long updateTimeEd){
            this.updateTimeEd = updateTimeEd;
            return this;
        }


        public QueryBuilder updateTime(Long updateTime){
            setUpdateTime(updateTime);
            return this;
        }

        public QueryBuilder updateTimeList(Long ... updateTime){
            this.updateTimeList = solveNullList(updateTime);
            return this;
        }

        public QueryBuilder updateTimeList(List<Long> updateTime){
            this.updateTimeList = updateTime;
            return this;
        }

        public QueryBuilder fetchUpdateTime(){
            setFetchFields("fetchFields","updateTime");
            return this;
        }

        public QueryBuilder excludeUpdateTime(){
            setFetchFields("excludeFields","updateTime");
            return this;
        }
        private <T>List<T> solveNullList(T ... objs){
            if (objs != null){
            List<T> list = new ArrayList<>();
                for (T item : objs){
                    if (item != null){
                        list.add(item);
                    }
                }
                return list;
            }
            return null;
        }

        public QueryBuilder fetchAll(){
            this.fetchFields.put("AllFields",true);
            return this;
        }

        public QueryBuilder addField(String ... fields){
            List<String> list = new ArrayList<>();
            if (fields != null){
                for (String field : fields){
                    list.add(field);
                }
            }
            this.fetchFields.put("otherFields",list);
            return this;
        }
        @SuppressWarnings("unchecked")
        private void setFetchFields(String key,String val){
            Map<String,Boolean> fields= (Map<String, Boolean>) this.fetchFields.get(key);
            if (fields == null){
                fields = new HashMap<>();
            }
            fields.put(val,true);
            this.fetchFields.put(key,fields);
        }

        public PlusOrder build(){return this;}
    }


    public static class ConditionBuilder{
        private List<Long> idList;

        public List<Long> getIdList(){return this.idList;}

        private Long idSt;

        private Long idEd;

        public Long getIdSt(){return this.idSt;}

        public Long getIdEd(){return this.idEd;}

        private List<Long> userIdList;

        public List<Long> getUserIdList(){return this.userIdList;}

        private Long userIdSt;

        private Long userIdEd;

        public Long getUserIdSt(){return this.userIdSt;}

        public Long getUserIdEd(){return this.userIdEd;}

        private List<Long> carIdList;

        public List<Long> getCarIdList(){return this.carIdList;}

        private Long carIdSt;

        private Long carIdEd;

        public Long getCarIdSt(){return this.carIdSt;}

        public Long getCarIdEd(){return this.carIdEd;}

        private List<Integer> orderTypeList;

        public List<Integer> getOrderTypeList(){return this.orderTypeList;}

        private Integer orderTypeSt;

        private Integer orderTypeEd;

        public Integer getOrderTypeSt(){return this.orderTypeSt;}

        public Integer getOrderTypeEd(){return this.orderTypeEd;}

        private List<Integer> totalCountList;

        public List<Integer> getTotalCountList(){return this.totalCountList;}

        private Integer totalCountSt;

        private Integer totalCountEd;

        public Integer getTotalCountSt(){return this.totalCountSt;}

        public Integer getTotalCountEd(){return this.totalCountEd;}

        private List<Integer> useCountList;

        public List<Integer> getUseCountList(){return this.useCountList;}

        private Integer useCountSt;

        private Integer useCountEd;

        public Integer getUseCountSt(){return this.useCountSt;}

        public Integer getUseCountEd(){return this.useCountEd;}

        private List<Long> expireTimeList;

        public List<Long> getExpireTimeList(){return this.expireTimeList;}

        private Long expireTimeSt;

        private Long expireTimeEd;

        public Long getExpireTimeSt(){return this.expireTimeSt;}

        public Long getExpireTimeEd(){return this.expireTimeEd;}

        private List<Long> createTimeList;

        public List<Long> getCreateTimeList(){return this.createTimeList;}

        private Long createTimeSt;

        private Long createTimeEd;

        public Long getCreateTimeSt(){return this.createTimeSt;}

        public Long getCreateTimeEd(){return this.createTimeEd;}

        private List<Long> updateTimeList;

        public List<Long> getUpdateTimeList(){return this.updateTimeList;}

        private Long updateTimeSt;

        private Long updateTimeEd;

        public Long getUpdateTimeSt(){return this.updateTimeSt;}

        public Long getUpdateTimeEd(){return this.updateTimeEd;}


        public ConditionBuilder idBetWeen(Long idSt,Long idEd){
            this.idSt = idSt;
            this.idEd = idEd;
            return this;
        }

        public ConditionBuilder idGreaterEqThan(Long idSt){
            this.idSt = idSt;
            return this;
        }
        public ConditionBuilder idLessEqThan(Long idEd){
            this.idEd = idEd;
            return this;
        }


        public ConditionBuilder idList(Long ... id){
            this.idList = solveNullList(id);
            return this;
        }

        public ConditionBuilder idList(List<Long> id){
            this.idList = id;
            return this;
        }

        public ConditionBuilder userIdBetWeen(Long userIdSt,Long userIdEd){
            this.userIdSt = userIdSt;
            this.userIdEd = userIdEd;
            return this;
        }

        public ConditionBuilder userIdGreaterEqThan(Long userIdSt){
            this.userIdSt = userIdSt;
            return this;
        }
        public ConditionBuilder userIdLessEqThan(Long userIdEd){
            this.userIdEd = userIdEd;
            return this;
        }


        public ConditionBuilder userIdList(Long ... userId){
            this.userIdList = solveNullList(userId);
            return this;
        }

        public ConditionBuilder userIdList(List<Long> userId){
            this.userIdList = userId;
            return this;
        }

        public ConditionBuilder carIdBetWeen(Long carIdSt,Long carIdEd){
            this.carIdSt = carIdSt;
            this.carIdEd = carIdEd;
            return this;
        }

        public ConditionBuilder carIdGreaterEqThan(Long carIdSt){
            this.carIdSt = carIdSt;
            return this;
        }
        public ConditionBuilder carIdLessEqThan(Long carIdEd){
            this.carIdEd = carIdEd;
            return this;
        }


        public ConditionBuilder carIdList(Long ... carId){
            this.carIdList = solveNullList(carId);
            return this;
        }

        public ConditionBuilder carIdList(List<Long> carId){
            this.carIdList = carId;
            return this;
        }

        public ConditionBuilder orderTypeBetWeen(Integer orderTypeSt,Integer orderTypeEd){
            this.orderTypeSt = orderTypeSt;
            this.orderTypeEd = orderTypeEd;
            return this;
        }

        public ConditionBuilder orderTypeGreaterEqThan(Integer orderTypeSt){
            this.orderTypeSt = orderTypeSt;
            return this;
        }
        public ConditionBuilder orderTypeLessEqThan(Integer orderTypeEd){
            this.orderTypeEd = orderTypeEd;
            return this;
        }


        public ConditionBuilder orderTypeList(Integer ... orderType){
            this.orderTypeList = solveNullList(orderType);
            return this;
        }

        public ConditionBuilder orderTypeList(List<Integer> orderType){
            this.orderTypeList = orderType;
            return this;
        }

        public ConditionBuilder totalCountBetWeen(Integer totalCountSt,Integer totalCountEd){
            this.totalCountSt = totalCountSt;
            this.totalCountEd = totalCountEd;
            return this;
        }

        public ConditionBuilder totalCountGreaterEqThan(Integer totalCountSt){
            this.totalCountSt = totalCountSt;
            return this;
        }
        public ConditionBuilder totalCountLessEqThan(Integer totalCountEd){
            this.totalCountEd = totalCountEd;
            return this;
        }


        public ConditionBuilder totalCountList(Integer ... totalCount){
            this.totalCountList = solveNullList(totalCount);
            return this;
        }

        public ConditionBuilder totalCountList(List<Integer> totalCount){
            this.totalCountList = totalCount;
            return this;
        }

        public ConditionBuilder useCountBetWeen(Integer useCountSt,Integer useCountEd){
            this.useCountSt = useCountSt;
            this.useCountEd = useCountEd;
            return this;
        }

        public ConditionBuilder useCountGreaterEqThan(Integer useCountSt){
            this.useCountSt = useCountSt;
            return this;
        }
        public ConditionBuilder useCountLessEqThan(Integer useCountEd){
            this.useCountEd = useCountEd;
            return this;
        }


        public ConditionBuilder useCountList(Integer ... useCount){
            this.useCountList = solveNullList(useCount);
            return this;
        }

        public ConditionBuilder useCountList(List<Integer> useCount){
            this.useCountList = useCount;
            return this;
        }

        public ConditionBuilder expireTimeBetWeen(Long expireTimeSt,Long expireTimeEd){
            this.expireTimeSt = expireTimeSt;
            this.expireTimeEd = expireTimeEd;
            return this;
        }

        public ConditionBuilder expireTimeGreaterEqThan(Long expireTimeSt){
            this.expireTimeSt = expireTimeSt;
            return this;
        }
        public ConditionBuilder expireTimeLessEqThan(Long expireTimeEd){
            this.expireTimeEd = expireTimeEd;
            return this;
        }


        public ConditionBuilder expireTimeList(Long ... expireTime){
            this.expireTimeList = solveNullList(expireTime);
            return this;
        }

        public ConditionBuilder expireTimeList(List<Long> expireTime){
            this.expireTimeList = expireTime;
            return this;
        }

        public ConditionBuilder createTimeBetWeen(Long createTimeSt,Long createTimeEd){
            this.createTimeSt = createTimeSt;
            this.createTimeEd = createTimeEd;
            return this;
        }

        public ConditionBuilder createTimeGreaterEqThan(Long createTimeSt){
            this.createTimeSt = createTimeSt;
            return this;
        }
        public ConditionBuilder createTimeLessEqThan(Long createTimeEd){
            this.createTimeEd = createTimeEd;
            return this;
        }


        public ConditionBuilder createTimeList(Long ... createTime){
            this.createTimeList = solveNullList(createTime);
            return this;
        }

        public ConditionBuilder createTimeList(List<Long> createTime){
            this.createTimeList = createTime;
            return this;
        }

        public ConditionBuilder updateTimeBetWeen(Long updateTimeSt,Long updateTimeEd){
            this.updateTimeSt = updateTimeSt;
            this.updateTimeEd = updateTimeEd;
            return this;
        }

        public ConditionBuilder updateTimeGreaterEqThan(Long updateTimeSt){
            this.updateTimeSt = updateTimeSt;
            return this;
        }
        public ConditionBuilder updateTimeLessEqThan(Long updateTimeEd){
            this.updateTimeEd = updateTimeEd;
            return this;
        }


        public ConditionBuilder updateTimeList(Long ... updateTime){
            this.updateTimeList = solveNullList(updateTime);
            return this;
        }

        public ConditionBuilder updateTimeList(List<Long> updateTime){
            this.updateTimeList = updateTime;
            return this;
        }

        private <T>List<T> solveNullList(T ... objs){
            if (objs != null){
            List<T> list = new ArrayList<>();
                for (T item : objs){
                    if (item != null){
                        list.add(item);
                    }
                }
                return list;
            }
            return null;
        }

        public ConditionBuilder build(){return this;}
    }

    public static class Builder {

        private PlusOrder obj;

        public Builder(){
            this.obj = new PlusOrder();
        }

        public Builder id(Long id){
            this.obj.setId(id);
            return this;
        }
        public Builder userId(Long userId){
            this.obj.setUserId(userId);
            return this;
        }
        public Builder carId(Long carId){
            this.obj.setCarId(carId);
            return this;
        }
        public Builder orderType(Integer orderType){
            this.obj.setOrderType(orderType);
            return this;
        }
        public Builder totalCount(Integer totalCount){
            this.obj.setTotalCount(totalCount);
            return this;
        }
        public Builder useCount(Integer useCount){
            this.obj.setUseCount(useCount);
            return this;
        }
        public Builder expireTime(Long expireTime){
            this.obj.setExpireTime(expireTime);
            return this;
        }
        public Builder createTime(Long createTime){
            this.obj.setCreateTime(createTime);
            return this;
        }
        public Builder updateTime(Long updateTime){
            this.obj.setUpdateTime(updateTime);
            return this;
        }
        public PlusOrder build(){return obj;}
    }

}
