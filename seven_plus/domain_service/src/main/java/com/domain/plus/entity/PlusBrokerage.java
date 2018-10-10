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
public class PlusBrokerage implements Serializable {

    private static final long serialVersionUID = 1535441519482L;


    /**
    * 主键
    * id自增
    * isNullAble:0,defaultVal:0
    */
    private Long id;

    /**
    * 一级会员id
    * isNullAble:1
    */
    private Long userId;

    /**
    * 二级会员Id
    * isNullAble:1
    */
    private Long secondId;

    /**
    * 佣金（分）
    * isNullAble:1
    */
    private Long brokerage;

    /**
    * 默认0  1：天天洗车 2：划痕无忧
    * isNullAble:1,defaultVal:0
    */
    private Integer orderType;

    /**
    * 创建时间
    * isNullAble:1,defaultVal:0
    */
    private Long createTime;

    /**
    * 更新时间
    * isNullAble:1,defaultVal:0
    */
    private Long updateTime;


    public void setId(Long id){this.id = id;}

    public Long getId(){return this.id;}

    public void setUserId(Long userId){this.userId = userId;}

    public Long getUserId(){return this.userId;}

    public void setSecondId(Long secondId){this.secondId = secondId;}

    public Long getSecondId(){return this.secondId;}

    public void setBrokerage(Long brokerage){this.brokerage = brokerage;}

    public Long getBrokerage(){return this.brokerage;}

    public void setOrderType(Integer orderType){this.orderType = orderType;}

    public Integer getOrderType(){return this.orderType;}

    public void setCreateTime(Long createTime){this.createTime = createTime;}

    public Long getCreateTime(){return this.createTime;}

    public void setUpdateTime(Long updateTime){this.updateTime = updateTime;}

    public Long getUpdateTime(){return this.updateTime;}
    @Override
    public String toString() {
        return "PlusBrokerage{" +
                "id='" + id + '\'' +
                "userId='" + userId + '\'' +
                "secondId='" + secondId + '\'' +
                "brokerage='" + brokerage + '\'' +
                "orderType='" + orderType + '\'' +
                "createTime='" + createTime + '\'' +
                "updateTime='" + updateTime + '\'' +
            '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private PlusBrokerage set;

        private ConditionBuilder where;

        public UpdateBuilder set(PlusBrokerage set){
            this.set = set;
            return this;
        }

        public PlusBrokerage getSet(){
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

    public static class QueryBuilder extends PlusBrokerage{
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

        private List<Long> secondIdList;

        public List<Long> getSecondIdList(){return this.secondIdList;}

        private Long secondIdSt;

        private Long secondIdEd;

        public Long getSecondIdSt(){return this.secondIdSt;}

        public Long getSecondIdEd(){return this.secondIdEd;}

        private List<Long> brokerageList;

        public List<Long> getBrokerageList(){return this.brokerageList;}

        private Long brokerageSt;

        private Long brokerageEd;

        public Long getBrokerageSt(){return this.brokerageSt;}

        public Long getBrokerageEd(){return this.brokerageEd;}

        private List<Integer> orderTypeList;

        public List<Integer> getOrderTypeList(){return this.orderTypeList;}

        private Integer orderTypeSt;

        private Integer orderTypeEd;

        public Integer getOrderTypeSt(){return this.orderTypeSt;}

        public Integer getOrderTypeEd(){return this.orderTypeEd;}

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

        public QueryBuilder secondIdBetWeen(Long secondIdSt,Long secondIdEd){
            this.secondIdSt = secondIdSt;
            this.secondIdEd = secondIdEd;
            return this;
        }

        public QueryBuilder secondIdGreaterEqThan(Long secondIdSt){
            this.secondIdSt = secondIdSt;
            return this;
        }
        public QueryBuilder secondIdLessEqThan(Long secondIdEd){
            this.secondIdEd = secondIdEd;
            return this;
        }


        public QueryBuilder secondId(Long secondId){
            setSecondId(secondId);
            return this;
        }

        public QueryBuilder secondIdList(Long ... secondId){
            this.secondIdList = solveNullList(secondId);
            return this;
        }

        public QueryBuilder secondIdList(List<Long> secondId){
            this.secondIdList = secondId;
            return this;
        }

        public QueryBuilder fetchSecondId(){
            setFetchFields("fetchFields","secondId");
            return this;
        }

        public QueryBuilder excludeSecondId(){
            setFetchFields("excludeFields","secondId");
            return this;
        }

        public QueryBuilder brokerageBetWeen(Long brokerageSt,Long brokerageEd){
            this.brokerageSt = brokerageSt;
            this.brokerageEd = brokerageEd;
            return this;
        }

        public QueryBuilder brokerageGreaterEqThan(Long brokerageSt){
            this.brokerageSt = brokerageSt;
            return this;
        }
        public QueryBuilder brokerageLessEqThan(Long brokerageEd){
            this.brokerageEd = brokerageEd;
            return this;
        }


        public QueryBuilder brokerage(Long brokerage){
            setBrokerage(brokerage);
            return this;
        }

        public QueryBuilder brokerageList(Long ... brokerage){
            this.brokerageList = solveNullList(brokerage);
            return this;
        }

        public QueryBuilder brokerageList(List<Long> brokerage){
            this.brokerageList = brokerage;
            return this;
        }

        public QueryBuilder fetchBrokerage(){
            setFetchFields("fetchFields","brokerage");
            return this;
        }

        public QueryBuilder excludeBrokerage(){
            setFetchFields("excludeFields","brokerage");
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

        public PlusBrokerage build(){return this;}
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

        private List<Long> secondIdList;

        public List<Long> getSecondIdList(){return this.secondIdList;}

        private Long secondIdSt;

        private Long secondIdEd;

        public Long getSecondIdSt(){return this.secondIdSt;}

        public Long getSecondIdEd(){return this.secondIdEd;}

        private List<Long> brokerageList;

        public List<Long> getBrokerageList(){return this.brokerageList;}

        private Long brokerageSt;

        private Long brokerageEd;

        public Long getBrokerageSt(){return this.brokerageSt;}

        public Long getBrokerageEd(){return this.brokerageEd;}

        private List<Integer> orderTypeList;

        public List<Integer> getOrderTypeList(){return this.orderTypeList;}

        private Integer orderTypeSt;

        private Integer orderTypeEd;

        public Integer getOrderTypeSt(){return this.orderTypeSt;}

        public Integer getOrderTypeEd(){return this.orderTypeEd;}

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

        public ConditionBuilder secondIdBetWeen(Long secondIdSt,Long secondIdEd){
            this.secondIdSt = secondIdSt;
            this.secondIdEd = secondIdEd;
            return this;
        }

        public ConditionBuilder secondIdGreaterEqThan(Long secondIdSt){
            this.secondIdSt = secondIdSt;
            return this;
        }
        public ConditionBuilder secondIdLessEqThan(Long secondIdEd){
            this.secondIdEd = secondIdEd;
            return this;
        }


        public ConditionBuilder secondIdList(Long ... secondId){
            this.secondIdList = solveNullList(secondId);
            return this;
        }

        public ConditionBuilder secondIdList(List<Long> secondId){
            this.secondIdList = secondId;
            return this;
        }

        public ConditionBuilder brokerageBetWeen(Long brokerageSt,Long brokerageEd){
            this.brokerageSt = brokerageSt;
            this.brokerageEd = brokerageEd;
            return this;
        }

        public ConditionBuilder brokerageGreaterEqThan(Long brokerageSt){
            this.brokerageSt = brokerageSt;
            return this;
        }
        public ConditionBuilder brokerageLessEqThan(Long brokerageEd){
            this.brokerageEd = brokerageEd;
            return this;
        }


        public ConditionBuilder brokerageList(Long ... brokerage){
            this.brokerageList = solveNullList(brokerage);
            return this;
        }

        public ConditionBuilder brokerageList(List<Long> brokerage){
            this.brokerageList = brokerage;
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

        private PlusBrokerage obj;

        public Builder(){
            this.obj = new PlusBrokerage();
        }

        public Builder id(Long id){
            this.obj.setId(id);
            return this;
        }
        public Builder userId(Long userId){
            this.obj.setUserId(userId);
            return this;
        }
        public Builder secondId(Long secondId){
            this.obj.setSecondId(secondId);
            return this;
        }
        public Builder brokerage(Long brokerage){
            this.obj.setBrokerage(brokerage);
            return this;
        }
        public Builder orderType(Integer orderType){
            this.obj.setOrderType(orderType);
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
        public PlusBrokerage build(){return obj;}
    }

}
