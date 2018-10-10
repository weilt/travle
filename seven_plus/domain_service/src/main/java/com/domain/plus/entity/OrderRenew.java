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
public class OrderRenew implements Serializable {

    private static final long serialVersionUID = 1533693410882L;


    /**
    * 主键
    * id自增
    * isNullAble:0
    */
    private Long id;

    /**
    * 订单Id
    * isNullAble:1
    */
    private Long orderId;

    /**
    * 默认0 新开通 1续费
    * isNullAble:1,defaultVal:0
    */
    private Integer renewType;

    /**
     * 默认 0：支付中，1：支付成功，2：支付失败
     */
    private Integer renewState;

    /**
     * 订单号
     */
    private String orderNo;

    /**
    * 续费或者新增费用（分）
    * isNullAble:1
    */
    private Long renewMoney;

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

    public Integer getRenewState() {
        return renewState;
    }

    public void setRenewState(Integer renewState) {
        this.renewState = renewState;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setId(Long id){this.id = id;}

    public Long getId(){return this.id;}

    public void setOrderId(Long orderId){this.orderId = orderId;}

    public Long getOrderId(){return this.orderId;}

    public void setRenewType(Integer renewType){this.renewType = renewType;}

    public Integer getRenewType(){return this.renewType;}

    public void setRenewMoney(Long renewMoney){this.renewMoney = renewMoney;}

    public Long getRenewMoney(){return this.renewMoney;}

    public void setCreateTime(Long createTime){this.createTime = createTime;}

    public Long getCreateTime(){return this.createTime;}

    public void setUpdateTime(Long updateTime){this.updateTime = updateTime;}

    public Long getUpdateTime(){return this.updateTime;}
    @Override
    public String toString() {
        return "OrderRenew{" +
                "id='" + id + '\'' +
                "orderId='" + orderId + '\'' +
                "renewType='" + renewType + '\'' +
                "renewMoney='" + renewMoney + '\'' +
                "createTime='" + createTime + '\'' +
                "updateTime='" + updateTime + '\'' +
            '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private OrderRenew set;

        private ConditionBuilder where;

        public UpdateBuilder set(OrderRenew set){
            this.set = set;
            return this;
        }

        public OrderRenew getSet(){
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

    public static class QueryBuilder extends OrderRenew{
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

        private List<Long> orderIdList;

        public List<Long> getOrderIdList(){return this.orderIdList;}

        private Long orderIdSt;

        private Long orderIdEd;

        public Long getOrderIdSt(){return this.orderIdSt;}

        public Long getOrderIdEd(){return this.orderIdEd;}

        private List<Integer> renewTypeList;

        public List<Integer> getRenewTypeList(){return this.renewTypeList;}

        private Integer renewTypeSt;

        private Integer renewTypeEd;

        public Integer getRenewTypeSt(){return this.renewTypeSt;}

        public Integer getRenewTypeEd(){return this.renewTypeEd;}

        private List<Long> renewMoneyList;

        public List<Long> getRenewMoneyList(){return this.renewMoneyList;}

        private Long renewMoneySt;

        private Long renewMoneyEd;

        public Long getRenewMoneySt(){return this.renewMoneySt;}

        public Long getRenewMoneyEd(){return this.renewMoneyEd;}

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

        public QueryBuilder orderIdBetWeen(Long orderIdSt,Long orderIdEd){
            this.orderIdSt = orderIdSt;
            this.orderIdEd = orderIdEd;
            return this;
        }

        public QueryBuilder orderIdGreaterEqThan(Long orderIdSt){
            this.orderIdSt = orderIdSt;
            return this;
        }
        public QueryBuilder orderIdLessEqThan(Long orderIdEd){
            this.orderIdEd = orderIdEd;
            return this;
        }


        public QueryBuilder orderId(Long orderId){
            setOrderId(orderId);
            return this;
        }

        public QueryBuilder orderIdList(Long ... orderId){
            this.orderIdList = solveNullList(orderId);
            return this;
        }

        public QueryBuilder orderIdList(List<Long> orderId){
            this.orderIdList = orderId;
            return this;
        }

        public QueryBuilder fetchOrderId(){
            setFetchFields("fetchFields","orderId");
            return this;
        }

        public QueryBuilder excludeOrderId(){
            setFetchFields("excludeFields","orderId");
            return this;
        }

        public QueryBuilder renewTypeBetWeen(Integer renewTypeSt,Integer renewTypeEd){
            this.renewTypeSt = renewTypeSt;
            this.renewTypeEd = renewTypeEd;
            return this;
        }

        public QueryBuilder renewTypeGreaterEqThan(Integer renewTypeSt){
            this.renewTypeSt = renewTypeSt;
            return this;
        }
        public QueryBuilder renewTypeLessEqThan(Integer renewTypeEd){
            this.renewTypeEd = renewTypeEd;
            return this;
        }


        public QueryBuilder renewType(Integer renewType){
            setRenewType(renewType);
            return this;
        }

        public QueryBuilder renewTypeList(Integer ... renewType){
            this.renewTypeList = solveNullList(renewType);
            return this;
        }

        public QueryBuilder renewTypeList(List<Integer> renewType){
            this.renewTypeList = renewType;
            return this;
        }

        public QueryBuilder fetchRenewType(){
            setFetchFields("fetchFields","renewType");
            return this;
        }

        public QueryBuilder excludeRenewType(){
            setFetchFields("excludeFields","renewType");
            return this;
        }

        public QueryBuilder renewMoneyBetWeen(Long renewMoneySt,Long renewMoneyEd){
            this.renewMoneySt = renewMoneySt;
            this.renewMoneyEd = renewMoneyEd;
            return this;
        }

        public QueryBuilder renewMoneyGreaterEqThan(Long renewMoneySt){
            this.renewMoneySt = renewMoneySt;
            return this;
        }
        public QueryBuilder renewMoneyLessEqThan(Long renewMoneyEd){
            this.renewMoneyEd = renewMoneyEd;
            return this;
        }


        public QueryBuilder renewMoney(Long renewMoney){
            setRenewMoney(renewMoney);
            return this;
        }

        public QueryBuilder renewMoneyList(Long ... renewMoney){
            this.renewMoneyList = solveNullList(renewMoney);
            return this;
        }

        public QueryBuilder renewMoneyList(List<Long> renewMoney){
            this.renewMoneyList = renewMoney;
            return this;
        }

        public QueryBuilder fetchRenewMoney(){
            setFetchFields("fetchFields","renewMoney");
            return this;
        }

        public QueryBuilder excludeRenewMoney(){
            setFetchFields("excludeFields","renewMoney");
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

        public OrderRenew build(){return this;}
    }


    public static class ConditionBuilder{
        private List<Long> idList;

        public List<Long> getIdList(){return this.idList;}

        private Long idSt;

        private Long idEd;

        public Long getIdSt(){return this.idSt;}

        public Long getIdEd(){return this.idEd;}

        private List<Long> orderIdList;

        public List<Long> getOrderIdList(){return this.orderIdList;}

        private Long orderIdSt;

        private Long orderIdEd;

        public Long getOrderIdSt(){return this.orderIdSt;}

        public Long getOrderIdEd(){return this.orderIdEd;}

        private List<Integer> renewTypeList;

        public List<Integer> getRenewTypeList(){return this.renewTypeList;}

        private Integer renewTypeSt;

        private Integer renewTypeEd;

        public Integer getRenewTypeSt(){return this.renewTypeSt;}

        public Integer getRenewTypeEd(){return this.renewTypeEd;}

        private List<Long> renewMoneyList;

        public List<Long> getRenewMoneyList(){return this.renewMoneyList;}

        private Long renewMoneySt;

        private Long renewMoneyEd;

        public Long getRenewMoneySt(){return this.renewMoneySt;}

        public Long getRenewMoneyEd(){return this.renewMoneyEd;}

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

        public ConditionBuilder orderIdBetWeen(Long orderIdSt,Long orderIdEd){
            this.orderIdSt = orderIdSt;
            this.orderIdEd = orderIdEd;
            return this;
        }

        public ConditionBuilder orderIdGreaterEqThan(Long orderIdSt){
            this.orderIdSt = orderIdSt;
            return this;
        }
        public ConditionBuilder orderIdLessEqThan(Long orderIdEd){
            this.orderIdEd = orderIdEd;
            return this;
        }


        public ConditionBuilder orderIdList(Long ... orderId){
            this.orderIdList = solveNullList(orderId);
            return this;
        }

        public ConditionBuilder orderIdList(List<Long> orderId){
            this.orderIdList = orderId;
            return this;
        }

        public ConditionBuilder renewTypeBetWeen(Integer renewTypeSt,Integer renewTypeEd){
            this.renewTypeSt = renewTypeSt;
            this.renewTypeEd = renewTypeEd;
            return this;
        }

        public ConditionBuilder renewTypeGreaterEqThan(Integer renewTypeSt){
            this.renewTypeSt = renewTypeSt;
            return this;
        }
        public ConditionBuilder renewTypeLessEqThan(Integer renewTypeEd){
            this.renewTypeEd = renewTypeEd;
            return this;
        }


        public ConditionBuilder renewTypeList(Integer ... renewType){
            this.renewTypeList = solveNullList(renewType);
            return this;
        }

        public ConditionBuilder renewTypeList(List<Integer> renewType){
            this.renewTypeList = renewType;
            return this;
        }

        public ConditionBuilder renewMoneyBetWeen(Long renewMoneySt,Long renewMoneyEd){
            this.renewMoneySt = renewMoneySt;
            this.renewMoneyEd = renewMoneyEd;
            return this;
        }

        public ConditionBuilder renewMoneyGreaterEqThan(Long renewMoneySt){
            this.renewMoneySt = renewMoneySt;
            return this;
        }
        public ConditionBuilder renewMoneyLessEqThan(Long renewMoneyEd){
            this.renewMoneyEd = renewMoneyEd;
            return this;
        }


        public ConditionBuilder renewMoneyList(Long ... renewMoney){
            this.renewMoneyList = solveNullList(renewMoney);
            return this;
        }

        public ConditionBuilder renewMoneyList(List<Long> renewMoney){
            this.renewMoneyList = renewMoney;
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

        private OrderRenew obj;

        public Builder(){
            this.obj = new OrderRenew();
        }

        public Builder id(Long id){
            this.obj.setId(id);
            return this;
        }
        public Builder orderId(Long orderId){
            this.obj.setOrderId(orderId);
            return this;
        }
        public Builder renewType(Integer renewType){
            this.obj.setRenewType(renewType);
            return this;
        }
        public Builder renewMoney(Long renewMoney){
            this.obj.setRenewMoney(renewMoney);
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
        public OrderRenew build(){return obj;}
    }

}
