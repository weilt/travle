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
public class OrderRecord implements Serializable {

    private static final long serialVersionUID = 1533693407787L;


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
    * 网店id
    * isNullAble:1
    */
    private Long storeId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 使用记录id
     */
    private Long recordId;

    /**
    * 上传图片
    * isNullAble:1
    */
    private String imgUrl;

    /**
    * 订单类型 默认0：天天洗车 1：划痕无忧
    * isNullAble:1,defaultVal:0
    */
    private Integer orderType;

    /**
    * 状态 默认0 未审核 1：审核通过 2：审核失败
    * isNullAble:1,defaultVal:0
    */
    private Integer state;

    /**
    * 备注
    * isNullAble:1
    */
    private String remarks;

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


    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public void setId(Long id){this.id = id;}

    public Long getId(){return this.id;}

    public void setOrderId(Long orderId){this.orderId = orderId;}

    public Long getOrderId(){return this.orderId;}

    public void setStoreId(Long storeId){this.storeId = storeId;}

    public Long getStoreId(){return this.storeId;}

    public void setImgUrl(String imgUrl){this.imgUrl = imgUrl;}

    public String getImgUrl(){return this.imgUrl;}

    public void setOrderType(Integer orderType){this.orderType = orderType;}

    public Integer getOrderType(){return this.orderType;}

    public void setState(Integer state){this.state = state;}

    public Integer getState(){return this.state;}

    public void setRemarks(String remarks){this.remarks = remarks;}

    public String getRemarks(){return this.remarks;}

    public void setCreateTime(Long createTime){this.createTime = createTime;}

    public Long getCreateTime(){return this.createTime;}

    public void setUpdateTime(Long updateTime){this.updateTime = updateTime;}

    public Long getUpdateTime(){return this.updateTime;}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderRecord{" +
                "id='" + id + '\'' +
                "orderId='" + orderId + '\'' +
                "storeId='" + storeId + '\'' +
                "imgUrl='" + imgUrl + '\'' +
                "orderType='" + orderType + '\'' +
                "state='" + state + '\'' +
                "remarks='" + remarks + '\'' +
                "createTime='" + createTime + '\'' +
                "updateTime='" + updateTime + '\'' +
            '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private OrderRecord set;

        private ConditionBuilder where;

        public UpdateBuilder set(OrderRecord set){
            this.set = set;
            return this;
        }

        public OrderRecord getSet(){
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

    public static class QueryBuilder extends OrderRecord{
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

        private List<Long> storeIdList;

        public List<Long> getStoreIdList(){return this.storeIdList;}

        private Long storeIdSt;

        private Long storeIdEd;

        public Long getStoreIdSt(){return this.storeIdSt;}

        public Long getStoreIdEd(){return this.storeIdEd;}

        private List<String> imgUrlList;

        public List<String> getImgUrlList(){return this.imgUrlList;}


        private List<String> fuzzyImgUrl;

        public List<String> getFuzzyImgUrl(){return this.fuzzyImgUrl;}

        private List<String> rightFuzzyImgUrl;

        public List<String> getRightFuzzyImgUrl(){return this.rightFuzzyImgUrl;}
        private List<Integer> orderTypeList;

        public List<Integer> getOrderTypeList(){return this.orderTypeList;}

        private Integer orderTypeSt;

        private Integer orderTypeEd;

        public Integer getOrderTypeSt(){return this.orderTypeSt;}

        public Integer getOrderTypeEd(){return this.orderTypeEd;}

        private List<Integer> stateList;

        public List<Integer> getStateList(){return this.stateList;}

        private Integer stateSt;

        private Integer stateEd;

        public Integer getStateSt(){return this.stateSt;}

        public Integer getStateEd(){return this.stateEd;}

        private List<String> remarksList;

        public List<String> getRemarksList(){return this.remarksList;}


        private List<String> fuzzyRemarks;

        public List<String> getFuzzyRemarks(){return this.fuzzyRemarks;}

        private List<String> rightFuzzyRemarks;

        public List<String> getRightFuzzyRemarks(){return this.rightFuzzyRemarks;}
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

        public QueryBuilder storeIdBetWeen(Long storeIdSt,Long storeIdEd){
            this.storeIdSt = storeIdSt;
            this.storeIdEd = storeIdEd;
            return this;
        }

        public QueryBuilder storeIdGreaterEqThan(Long storeIdSt){
            this.storeIdSt = storeIdSt;
            return this;
        }
        public QueryBuilder storeIdLessEqThan(Long storeIdEd){
            this.storeIdEd = storeIdEd;
            return this;
        }


        public QueryBuilder storeId(Long storeId){
            setStoreId(storeId);
            return this;
        }

        public QueryBuilder storeIdList(Long ... storeId){
            this.storeIdList = solveNullList(storeId);
            return this;
        }

        public QueryBuilder storeIdList(List<Long> storeId){
            this.storeIdList = storeId;
            return this;
        }

        public QueryBuilder fetchStoreId(){
            setFetchFields("fetchFields","storeId");
            return this;
        }

        public QueryBuilder excludeStoreId(){
            setFetchFields("excludeFields","storeId");
            return this;
        }

        public QueryBuilder fuzzyImgUrl (List<String> fuzzyImgUrl){
            this.fuzzyImgUrl = fuzzyImgUrl;
            return this;
        }

        public QueryBuilder fuzzyImgUrl (String ... fuzzyImgUrl){
            this.fuzzyImgUrl = solveNullList(fuzzyImgUrl);
            return this;
        }

        public QueryBuilder rightFuzzyImgUrl (List<String> rightFuzzyImgUrl){
            this.rightFuzzyImgUrl = rightFuzzyImgUrl;
            return this;
        }

        public QueryBuilder rightFuzzyImgUrl (String ... rightFuzzyImgUrl){
            this.rightFuzzyImgUrl = solveNullList(rightFuzzyImgUrl);
            return this;
        }

        public QueryBuilder imgUrl(String imgUrl){
            setImgUrl(imgUrl);
            return this;
        }

        public QueryBuilder imgUrlList(String ... imgUrl){
            this.imgUrlList = solveNullList(imgUrl);
            return this;
        }

        public QueryBuilder imgUrlList(List<String> imgUrl){
            this.imgUrlList = imgUrl;
            return this;
        }

        public QueryBuilder fetchImgUrl(){
            setFetchFields("fetchFields","imgUrl");
            return this;
        }

        public QueryBuilder excludeImgUrl(){
            setFetchFields("excludeFields","imgUrl");
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

        public QueryBuilder stateBetWeen(Integer stateSt,Integer stateEd){
            this.stateSt = stateSt;
            this.stateEd = stateEd;
            return this;
        }

        public QueryBuilder stateGreaterEqThan(Integer stateSt){
            this.stateSt = stateSt;
            return this;
        }
        public QueryBuilder stateLessEqThan(Integer stateEd){
            this.stateEd = stateEd;
            return this;
        }


        public QueryBuilder state(Integer state){
            setState(state);
            return this;
        }

        public QueryBuilder stateList(Integer ... state){
            this.stateList = solveNullList(state);
            return this;
        }

        public QueryBuilder stateList(List<Integer> state){
            this.stateList = state;
            return this;
        }

        public QueryBuilder fetchState(){
            setFetchFields("fetchFields","state");
            return this;
        }

        public QueryBuilder excludeState(){
            setFetchFields("excludeFields","state");
            return this;
        }

        public QueryBuilder fuzzyRemarks (List<String> fuzzyRemarks){
            this.fuzzyRemarks = fuzzyRemarks;
            return this;
        }

        public QueryBuilder fuzzyRemarks (String ... fuzzyRemarks){
            this.fuzzyRemarks = solveNullList(fuzzyRemarks);
            return this;
        }

        public QueryBuilder rightFuzzyRemarks (List<String> rightFuzzyRemarks){
            this.rightFuzzyRemarks = rightFuzzyRemarks;
            return this;
        }

        public QueryBuilder rightFuzzyRemarks (String ... rightFuzzyRemarks){
            this.rightFuzzyRemarks = solveNullList(rightFuzzyRemarks);
            return this;
        }

        public QueryBuilder remarks(String remarks){
            setRemarks(remarks);
            return this;
        }

        public QueryBuilder remarksList(String ... remarks){
            this.remarksList = solveNullList(remarks);
            return this;
        }

        public QueryBuilder remarksList(List<String> remarks){
            this.remarksList = remarks;
            return this;
        }

        public QueryBuilder fetchRemarks(){
            setFetchFields("fetchFields","remarks");
            return this;
        }

        public QueryBuilder excludeRemarks(){
            setFetchFields("excludeFields","remarks");
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

        public OrderRecord build(){return this;}
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

        private List<Long> storeIdList;

        public List<Long> getStoreIdList(){return this.storeIdList;}

        private Long storeIdSt;

        private Long storeIdEd;

        public Long getStoreIdSt(){return this.storeIdSt;}

        public Long getStoreIdEd(){return this.storeIdEd;}

        private List<String> imgUrlList;

        public List<String> getImgUrlList(){return this.imgUrlList;}


        private List<String> fuzzyImgUrl;

        public List<String> getFuzzyImgUrl(){return this.fuzzyImgUrl;}

        private List<String> rightFuzzyImgUrl;

        public List<String> getRightFuzzyImgUrl(){return this.rightFuzzyImgUrl;}
        private List<Integer> orderTypeList;

        public List<Integer> getOrderTypeList(){return this.orderTypeList;}

        private Integer orderTypeSt;

        private Integer orderTypeEd;

        public Integer getOrderTypeSt(){return this.orderTypeSt;}

        public Integer getOrderTypeEd(){return this.orderTypeEd;}

        private List<Integer> stateList;

        public List<Integer> getStateList(){return this.stateList;}

        private Integer stateSt;

        private Integer stateEd;

        public Integer getStateSt(){return this.stateSt;}

        public Integer getStateEd(){return this.stateEd;}

        private List<String> remarksList;

        public List<String> getRemarksList(){return this.remarksList;}


        private List<String> fuzzyRemarks;

        public List<String> getFuzzyRemarks(){return this.fuzzyRemarks;}

        private List<String> rightFuzzyRemarks;

        public List<String> getRightFuzzyRemarks(){return this.rightFuzzyRemarks;}
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

        public ConditionBuilder storeIdBetWeen(Long storeIdSt,Long storeIdEd){
            this.storeIdSt = storeIdSt;
            this.storeIdEd = storeIdEd;
            return this;
        }

        public ConditionBuilder storeIdGreaterEqThan(Long storeIdSt){
            this.storeIdSt = storeIdSt;
            return this;
        }
        public ConditionBuilder storeIdLessEqThan(Long storeIdEd){
            this.storeIdEd = storeIdEd;
            return this;
        }


        public ConditionBuilder storeIdList(Long ... storeId){
            this.storeIdList = solveNullList(storeId);
            return this;
        }

        public ConditionBuilder storeIdList(List<Long> storeId){
            this.storeIdList = storeId;
            return this;
        }

        public ConditionBuilder fuzzyImgUrl (List<String> fuzzyImgUrl){
            this.fuzzyImgUrl = fuzzyImgUrl;
            return this;
        }

        public ConditionBuilder fuzzyImgUrl (String ... fuzzyImgUrl){
            this.fuzzyImgUrl = solveNullList(fuzzyImgUrl);
            return this;
        }

        public ConditionBuilder rightFuzzyImgUrl (List<String> rightFuzzyImgUrl){
            this.rightFuzzyImgUrl = rightFuzzyImgUrl;
            return this;
        }

        public ConditionBuilder rightFuzzyImgUrl (String ... rightFuzzyImgUrl){
            this.rightFuzzyImgUrl = solveNullList(rightFuzzyImgUrl);
            return this;
        }

        public ConditionBuilder imgUrlList(String ... imgUrl){
            this.imgUrlList = solveNullList(imgUrl);
            return this;
        }

        public ConditionBuilder imgUrlList(List<String> imgUrl){
            this.imgUrlList = imgUrl;
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

        public ConditionBuilder stateBetWeen(Integer stateSt,Integer stateEd){
            this.stateSt = stateSt;
            this.stateEd = stateEd;
            return this;
        }

        public ConditionBuilder stateGreaterEqThan(Integer stateSt){
            this.stateSt = stateSt;
            return this;
        }
        public ConditionBuilder stateLessEqThan(Integer stateEd){
            this.stateEd = stateEd;
            return this;
        }


        public ConditionBuilder stateList(Integer ... state){
            this.stateList = solveNullList(state);
            return this;
        }

        public ConditionBuilder stateList(List<Integer> state){
            this.stateList = state;
            return this;
        }

        public ConditionBuilder fuzzyRemarks (List<String> fuzzyRemarks){
            this.fuzzyRemarks = fuzzyRemarks;
            return this;
        }

        public ConditionBuilder fuzzyRemarks (String ... fuzzyRemarks){
            this.fuzzyRemarks = solveNullList(fuzzyRemarks);
            return this;
        }

        public ConditionBuilder rightFuzzyRemarks (List<String> rightFuzzyRemarks){
            this.rightFuzzyRemarks = rightFuzzyRemarks;
            return this;
        }

        public ConditionBuilder rightFuzzyRemarks (String ... rightFuzzyRemarks){
            this.rightFuzzyRemarks = solveNullList(rightFuzzyRemarks);
            return this;
        }

        public ConditionBuilder remarksList(String ... remarks){
            this.remarksList = solveNullList(remarks);
            return this;
        }

        public ConditionBuilder remarksList(List<String> remarks){
            this.remarksList = remarks;
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

        private OrderRecord obj;

        public Builder(){
            this.obj = new OrderRecord();
        }

        public Builder id(Long id){
            this.obj.setId(id);
            return this;
        }
        public Builder orderId(Long orderId){
            this.obj.setOrderId(orderId);
            return this;
        }
        public Builder storeId(Long storeId){
            this.obj.setStoreId(storeId);
            return this;
        }
        public Builder imgUrl(String imgUrl){
            this.obj.setImgUrl(imgUrl);
            return this;
        }
        public Builder orderType(Integer orderType){
            this.obj.setOrderType(orderType);
            return this;
        }
        public Builder state(Integer state){
            this.obj.setState(state);
            return this;
        }
        public Builder remarks(String remarks){
            this.obj.setRemarks(remarks);
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
        public OrderRecord build(){return obj;}
    }

}
