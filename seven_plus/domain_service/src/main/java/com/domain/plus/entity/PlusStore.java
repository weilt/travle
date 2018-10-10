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
public class PlusStore implements Serializable {

    private static final long serialVersionUID = 1533693429161L;


    /**
    * 主键
    * ID自增
    * isNullAble:0
    */
    private Long id;

    /**
    * 网点名称
    * isNullAble:1
    */
    private String storeName;

    /**
    * 默认0：天天洗车 1：划痕无忧
    * isNullAble:1,defaultVal:0
    */
    private Integer storeType;

    /**
     * 网点电话
     */
    private String storePhone;

    /**
     * 网点所在去
     */
    private String storeDistrict;
    /**
    * 网点图片
    * isNullAble:1
    */
    private String storeImg;

    /**
    * 经度
    * isNullAble:1
    */
    private String storeLat;

    /**
    * 维度
    * isNullAble:1
    */
    private String storeLon;

    /**
    * 网点地址
    * isNullAble:1
    */
    private String storeAddress;

    /**
    * 订单数
    * isNullAble:1
    */
    private Long orderCount;

    /**
    * 
    * isNullAble:1
    */
    private Long createTime;

    /**
    * 
    * isNullAble:1
    */
    private Long updateTime;

    public String getStoreDistrict() {
        return storeDistrict;
    }

    public void setStoreDistrict(String storeDistrict) {
        this.storeDistrict = storeDistrict;
    }

    public void setId(Long id){this.id = id;}

    public Long getId(){return this.id;}

    public void setStoreName(String storeName){this.storeName = storeName;}

    public String getStoreName(){return this.storeName;}

    public void setStoreType(Integer storeType){this.storeType = storeType;}

    public Integer getStoreType(){return this.storeType;}

    public void setStoreImg(String storeImg){this.storeImg = storeImg;}

    public String getStoreImg(){return this.storeImg;}

    public void setStoreLat(String storeLat){this.storeLat = storeLat;}

    public String getStoreLat(){return this.storeLat;}

    public void setStoreLon(String storeLon){this.storeLon = storeLon;}

    public String getStoreLon(){return this.storeLon;}

    public void setStoreAddress(String storeAddress){this.storeAddress = storeAddress;}

    public String getStoreAddress(){return this.storeAddress;}

    public void setOrderCount(Long orderCount){this.orderCount = orderCount;}

    public Long getOrderCount(){return this.orderCount;}

    public void setCreateTime(Long createTime){this.createTime = createTime;}

    public Long getCreateTime(){return this.createTime;}

    public void setUpdateTime(Long updateTime){this.updateTime = updateTime;}

    public Long getUpdateTime(){return this.updateTime;}


    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    @Override
    public String toString() {
        return "PlusStore{" +
                "id='" + id + '\'' +
                "storeName='" + storeName + '\'' +
                "storeType='" + storeType + '\'' +
                "storeImg='" + storeImg + '\'' +
                "storeLat='" + storeLat + '\'' +
                "storeLon='" + storeLon + '\'' +
                "storeAddress='" + storeAddress + '\'' +
                "orderCount='" + orderCount + '\'' +
                "createTime='" + createTime + '\'' +
                "updateTime='" + updateTime + '\'' +
            '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private PlusStore set;

        private ConditionBuilder where;

        public UpdateBuilder set(PlusStore set){
            this.set = set;
            return this;
        }

        public PlusStore getSet(){
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

    public static class QueryBuilder extends PlusStore{
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

        private List<String> storeNameList;

        public List<String> getStoreNameList(){return this.storeNameList;}


        private List<String> fuzzyStoreName;

        public List<String> getFuzzyStoreName(){return this.fuzzyStoreName;}

        private List<String> rightFuzzyStoreName;

        public List<String> getRightFuzzyStoreName(){return this.rightFuzzyStoreName;}
        private List<Integer> storeTypeList;

        public List<Integer> getStoreTypeList(){return this.storeTypeList;}

        private Integer storeTypeSt;

        private Integer storeTypeEd;

        public Integer getStoreTypeSt(){return this.storeTypeSt;}

        public Integer getStoreTypeEd(){return this.storeTypeEd;}

        private List<String> storeImgList;

        public List<String> getStoreImgList(){return this.storeImgList;}


        private List<String> fuzzyStoreImg;

        public List<String> getFuzzyStoreImg(){return this.fuzzyStoreImg;}

        private List<String> rightFuzzyStoreImg;

        public List<String> getRightFuzzyStoreImg(){return this.rightFuzzyStoreImg;}
        private List<String> storeLatList;

        public List<String> getStoreLatList(){return this.storeLatList;}


        private List<String> fuzzyStoreLat;

        public List<String> getFuzzyStoreLat(){return this.fuzzyStoreLat;}

        private List<String> rightFuzzyStoreLat;

        public List<String> getRightFuzzyStoreLat(){return this.rightFuzzyStoreLat;}
        private List<String> storeLonList;

        public List<String> getStoreLonList(){return this.storeLonList;}


        private List<String> fuzzyStoreLon;

        public List<String> getFuzzyStoreLon(){return this.fuzzyStoreLon;}

        private List<String> rightFuzzyStoreLon;

        public List<String> getRightFuzzyStoreLon(){return this.rightFuzzyStoreLon;}
        private List<String> storeAddressList;

        public List<String> getStoreAddressList(){return this.storeAddressList;}


        private List<String> fuzzyStoreAddress;

        public List<String> getFuzzyStoreAddress(){return this.fuzzyStoreAddress;}

        private List<String> rightFuzzyStoreAddress;

        public List<String> getRightFuzzyStoreAddress(){return this.rightFuzzyStoreAddress;}
        private List<Long> orderCountList;

        public List<Long> getOrderCountList(){return this.orderCountList;}

        private Long orderCountSt;

        private Long orderCountEd;

        public Long getOrderCountSt(){return this.orderCountSt;}

        public Long getOrderCountEd(){return this.orderCountEd;}

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

        public QueryBuilder fuzzyStoreName (List<String> fuzzyStoreName){
            this.fuzzyStoreName = fuzzyStoreName;
            return this;
        }

        public QueryBuilder fuzzyStoreName (String ... fuzzyStoreName){
            this.fuzzyStoreName = solveNullList(fuzzyStoreName);
            return this;
        }

        public QueryBuilder rightFuzzyStoreName (List<String> rightFuzzyStoreName){
            this.rightFuzzyStoreName = rightFuzzyStoreName;
            return this;
        }

        public QueryBuilder rightFuzzyStoreName (String ... rightFuzzyStoreName){
            this.rightFuzzyStoreName = solveNullList(rightFuzzyStoreName);
            return this;
        }

        public QueryBuilder storeName(String storeName){
            setStoreName(storeName);
            return this;
        }

        public QueryBuilder storeNameList(String ... storeName){
            this.storeNameList = solveNullList(storeName);
            return this;
        }

        public QueryBuilder storeNameList(List<String> storeName){
            this.storeNameList = storeName;
            return this;
        }

        public QueryBuilder fetchStoreName(){
            setFetchFields("fetchFields","storeName");
            return this;
        }

        public QueryBuilder excludeStoreName(){
            setFetchFields("excludeFields","storeName");
            return this;
        }

        public QueryBuilder storeTypeBetWeen(Integer storeTypeSt,Integer storeTypeEd){
            this.storeTypeSt = storeTypeSt;
            this.storeTypeEd = storeTypeEd;
            return this;
        }

        public QueryBuilder storeTypeGreaterEqThan(Integer storeTypeSt){
            this.storeTypeSt = storeTypeSt;
            return this;
        }
        public QueryBuilder storeTypeLessEqThan(Integer storeTypeEd){
            this.storeTypeEd = storeTypeEd;
            return this;
        }


        public QueryBuilder storeType(Integer storeType){
            setStoreType(storeType);
            return this;
        }

        public QueryBuilder storeTypeList(Integer ... storeType){
            this.storeTypeList = solveNullList(storeType);
            return this;
        }

        public QueryBuilder storeTypeList(List<Integer> storeType){
            this.storeTypeList = storeType;
            return this;
        }

        public QueryBuilder fetchStoreType(){
            setFetchFields("fetchFields","storeType");
            return this;
        }

        public QueryBuilder excludeStoreType(){
            setFetchFields("excludeFields","storeType");
            return this;
        }

        public QueryBuilder fuzzyStoreImg (List<String> fuzzyStoreImg){
            this.fuzzyStoreImg = fuzzyStoreImg;
            return this;
        }

        public QueryBuilder fuzzyStoreImg (String ... fuzzyStoreImg){
            this.fuzzyStoreImg = solveNullList(fuzzyStoreImg);
            return this;
        }

        public QueryBuilder rightFuzzyStoreImg (List<String> rightFuzzyStoreImg){
            this.rightFuzzyStoreImg = rightFuzzyStoreImg;
            return this;
        }

        public QueryBuilder rightFuzzyStoreImg (String ... rightFuzzyStoreImg){
            this.rightFuzzyStoreImg = solveNullList(rightFuzzyStoreImg);
            return this;
        }

        public QueryBuilder storeImg(String storeImg){
            setStoreImg(storeImg);
            return this;
        }

        public QueryBuilder storeImgList(String ... storeImg){
            this.storeImgList = solveNullList(storeImg);
            return this;
        }

        public QueryBuilder storeImgList(List<String> storeImg){
            this.storeImgList = storeImg;
            return this;
        }

        public QueryBuilder fetchStoreImg(){
            setFetchFields("fetchFields","storeImg");
            return this;
        }

        public QueryBuilder excludeStoreImg(){
            setFetchFields("excludeFields","storeImg");
            return this;
        }

        public QueryBuilder fuzzyStoreLat (List<String> fuzzyStoreLat){
            this.fuzzyStoreLat = fuzzyStoreLat;
            return this;
        }

        public QueryBuilder fuzzyStoreLat (String ... fuzzyStoreLat){
            this.fuzzyStoreLat = solveNullList(fuzzyStoreLat);
            return this;
        }

        public QueryBuilder rightFuzzyStoreLat (List<String> rightFuzzyStoreLat){
            this.rightFuzzyStoreLat = rightFuzzyStoreLat;
            return this;
        }

        public QueryBuilder rightFuzzyStoreLat (String ... rightFuzzyStoreLat){
            this.rightFuzzyStoreLat = solveNullList(rightFuzzyStoreLat);
            return this;
        }

        public QueryBuilder storeLat(String storeLat){
            setStoreLat(storeLat);
            return this;
        }

        public QueryBuilder storeLatList(String ... storeLat){
            this.storeLatList = solveNullList(storeLat);
            return this;
        }

        public QueryBuilder storeLatList(List<String> storeLat){
            this.storeLatList = storeLat;
            return this;
        }

        public QueryBuilder fetchStoreLat(){
            setFetchFields("fetchFields","storeLat");
            return this;
        }

        public QueryBuilder excludeStoreLat(){
            setFetchFields("excludeFields","storeLat");
            return this;
        }

        public QueryBuilder fuzzyStoreLon (List<String> fuzzyStoreLon){
            this.fuzzyStoreLon = fuzzyStoreLon;
            return this;
        }

        public QueryBuilder fuzzyStoreLon (String ... fuzzyStoreLon){
            this.fuzzyStoreLon = solveNullList(fuzzyStoreLon);
            return this;
        }

        public QueryBuilder rightFuzzyStoreLon (List<String> rightFuzzyStoreLon){
            this.rightFuzzyStoreLon = rightFuzzyStoreLon;
            return this;
        }

        public QueryBuilder rightFuzzyStoreLon (String ... rightFuzzyStoreLon){
            this.rightFuzzyStoreLon = solveNullList(rightFuzzyStoreLon);
            return this;
        }

        public QueryBuilder storeLon(String storeLon){
            setStoreLon(storeLon);
            return this;
        }

        public QueryBuilder storeLonList(String ... storeLon){
            this.storeLonList = solveNullList(storeLon);
            return this;
        }

        public QueryBuilder storeLonList(List<String> storeLon){
            this.storeLonList = storeLon;
            return this;
        }

        public QueryBuilder fetchStoreLon(){
            setFetchFields("fetchFields","storeLon");
            return this;
        }

        public QueryBuilder excludeStoreLon(){
            setFetchFields("excludeFields","storeLon");
            return this;
        }

        public QueryBuilder fuzzyStoreAddress (List<String> fuzzyStoreAddress){
            this.fuzzyStoreAddress = fuzzyStoreAddress;
            return this;
        }

        public QueryBuilder fuzzyStoreAddress (String ... fuzzyStoreAddress){
            this.fuzzyStoreAddress = solveNullList(fuzzyStoreAddress);
            return this;
        }

        public QueryBuilder rightFuzzyStoreAddress (List<String> rightFuzzyStoreAddress){
            this.rightFuzzyStoreAddress = rightFuzzyStoreAddress;
            return this;
        }

        public QueryBuilder rightFuzzyStoreAddress (String ... rightFuzzyStoreAddress){
            this.rightFuzzyStoreAddress = solveNullList(rightFuzzyStoreAddress);
            return this;
        }

        public QueryBuilder storeAddress(String storeAddress){
            setStoreAddress(storeAddress);
            return this;
        }

        public QueryBuilder storeAddressList(String ... storeAddress){
            this.storeAddressList = solveNullList(storeAddress);
            return this;
        }

        public QueryBuilder storeAddressList(List<String> storeAddress){
            this.storeAddressList = storeAddress;
            return this;
        }

        public QueryBuilder fetchStoreAddress(){
            setFetchFields("fetchFields","storeAddress");
            return this;
        }

        public QueryBuilder excludeStoreAddress(){
            setFetchFields("excludeFields","storeAddress");
            return this;
        }

        public QueryBuilder orderCountBetWeen(Long orderCountSt,Long orderCountEd){
            this.orderCountSt = orderCountSt;
            this.orderCountEd = orderCountEd;
            return this;
        }

        public QueryBuilder orderCountGreaterEqThan(Long orderCountSt){
            this.orderCountSt = orderCountSt;
            return this;
        }
        public QueryBuilder orderCountLessEqThan(Long orderCountEd){
            this.orderCountEd = orderCountEd;
            return this;
        }


        public QueryBuilder orderCount(Long orderCount){
            setOrderCount(orderCount);
            return this;
        }

        public QueryBuilder orderCountList(Long ... orderCount){
            this.orderCountList = solveNullList(orderCount);
            return this;
        }

        public QueryBuilder orderCountList(List<Long> orderCount){
            this.orderCountList = orderCount;
            return this;
        }

        public QueryBuilder fetchOrderCount(){
            setFetchFields("fetchFields","orderCount");
            return this;
        }

        public QueryBuilder excludeOrderCount(){
            setFetchFields("excludeFields","orderCount");
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

        public PlusStore build(){return this;}
    }


    public static class ConditionBuilder{
        private List<Long> idList;

        public List<Long> getIdList(){return this.idList;}

        private Long idSt;

        private Long idEd;

        public Long getIdSt(){return this.idSt;}

        public Long getIdEd(){return this.idEd;}

        private List<String> storeNameList;

        public List<String> getStoreNameList(){return this.storeNameList;}


        private List<String> fuzzyStoreName;

        public List<String> getFuzzyStoreName(){return this.fuzzyStoreName;}

        private List<String> rightFuzzyStoreName;

        public List<String> getRightFuzzyStoreName(){return this.rightFuzzyStoreName;}
        private List<Integer> storeTypeList;

        public List<Integer> getStoreTypeList(){return this.storeTypeList;}

        private Integer storeTypeSt;

        private Integer storeTypeEd;

        public Integer getStoreTypeSt(){return this.storeTypeSt;}

        public Integer getStoreTypeEd(){return this.storeTypeEd;}

        private List<String> storeImgList;

        public List<String> getStoreImgList(){return this.storeImgList;}


        private List<String> fuzzyStoreImg;

        public List<String> getFuzzyStoreImg(){return this.fuzzyStoreImg;}

        private List<String> rightFuzzyStoreImg;

        public List<String> getRightFuzzyStoreImg(){return this.rightFuzzyStoreImg;}
        private List<String> storeLatList;

        public List<String> getStoreLatList(){return this.storeLatList;}


        private List<String> fuzzyStoreLat;

        public List<String> getFuzzyStoreLat(){return this.fuzzyStoreLat;}

        private List<String> rightFuzzyStoreLat;

        public List<String> getRightFuzzyStoreLat(){return this.rightFuzzyStoreLat;}
        private List<String> storeLonList;

        public List<String> getStoreLonList(){return this.storeLonList;}


        private List<String> fuzzyStoreLon;

        public List<String> getFuzzyStoreLon(){return this.fuzzyStoreLon;}

        private List<String> rightFuzzyStoreLon;

        public List<String> getRightFuzzyStoreLon(){return this.rightFuzzyStoreLon;}
        private List<String> storeAddressList;

        public List<String> getStoreAddressList(){return this.storeAddressList;}


        private List<String> fuzzyStoreAddress;

        public List<String> getFuzzyStoreAddress(){return this.fuzzyStoreAddress;}

        private List<String> rightFuzzyStoreAddress;

        public List<String> getRightFuzzyStoreAddress(){return this.rightFuzzyStoreAddress;}
        private List<Long> orderCountList;

        public List<Long> getOrderCountList(){return this.orderCountList;}

        private Long orderCountSt;

        private Long orderCountEd;

        public Long getOrderCountSt(){return this.orderCountSt;}

        public Long getOrderCountEd(){return this.orderCountEd;}

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

        public ConditionBuilder fuzzyStoreName (List<String> fuzzyStoreName){
            this.fuzzyStoreName = fuzzyStoreName;
            return this;
        }

        public ConditionBuilder fuzzyStoreName (String ... fuzzyStoreName){
            this.fuzzyStoreName = solveNullList(fuzzyStoreName);
            return this;
        }

        public ConditionBuilder rightFuzzyStoreName (List<String> rightFuzzyStoreName){
            this.rightFuzzyStoreName = rightFuzzyStoreName;
            return this;
        }

        public ConditionBuilder rightFuzzyStoreName (String ... rightFuzzyStoreName){
            this.rightFuzzyStoreName = solveNullList(rightFuzzyStoreName);
            return this;
        }

        public ConditionBuilder storeNameList(String ... storeName){
            this.storeNameList = solveNullList(storeName);
            return this;
        }

        public ConditionBuilder storeNameList(List<String> storeName){
            this.storeNameList = storeName;
            return this;
        }

        public ConditionBuilder storeTypeBetWeen(Integer storeTypeSt,Integer storeTypeEd){
            this.storeTypeSt = storeTypeSt;
            this.storeTypeEd = storeTypeEd;
            return this;
        }

        public ConditionBuilder storeTypeGreaterEqThan(Integer storeTypeSt){
            this.storeTypeSt = storeTypeSt;
            return this;
        }
        public ConditionBuilder storeTypeLessEqThan(Integer storeTypeEd){
            this.storeTypeEd = storeTypeEd;
            return this;
        }


        public ConditionBuilder storeTypeList(Integer ... storeType){
            this.storeTypeList = solveNullList(storeType);
            return this;
        }

        public ConditionBuilder storeTypeList(List<Integer> storeType){
            this.storeTypeList = storeType;
            return this;
        }

        public ConditionBuilder fuzzyStoreImg (List<String> fuzzyStoreImg){
            this.fuzzyStoreImg = fuzzyStoreImg;
            return this;
        }

        public ConditionBuilder fuzzyStoreImg (String ... fuzzyStoreImg){
            this.fuzzyStoreImg = solveNullList(fuzzyStoreImg);
            return this;
        }

        public ConditionBuilder rightFuzzyStoreImg (List<String> rightFuzzyStoreImg){
            this.rightFuzzyStoreImg = rightFuzzyStoreImg;
            return this;
        }

        public ConditionBuilder rightFuzzyStoreImg (String ... rightFuzzyStoreImg){
            this.rightFuzzyStoreImg = solveNullList(rightFuzzyStoreImg);
            return this;
        }

        public ConditionBuilder storeImgList(String ... storeImg){
            this.storeImgList = solveNullList(storeImg);
            return this;
        }

        public ConditionBuilder storeImgList(List<String> storeImg){
            this.storeImgList = storeImg;
            return this;
        }

        public ConditionBuilder fuzzyStoreLat (List<String> fuzzyStoreLat){
            this.fuzzyStoreLat = fuzzyStoreLat;
            return this;
        }

        public ConditionBuilder fuzzyStoreLat (String ... fuzzyStoreLat){
            this.fuzzyStoreLat = solveNullList(fuzzyStoreLat);
            return this;
        }

        public ConditionBuilder rightFuzzyStoreLat (List<String> rightFuzzyStoreLat){
            this.rightFuzzyStoreLat = rightFuzzyStoreLat;
            return this;
        }

        public ConditionBuilder rightFuzzyStoreLat (String ... rightFuzzyStoreLat){
            this.rightFuzzyStoreLat = solveNullList(rightFuzzyStoreLat);
            return this;
        }

        public ConditionBuilder storeLatList(String ... storeLat){
            this.storeLatList = solveNullList(storeLat);
            return this;
        }

        public ConditionBuilder storeLatList(List<String> storeLat){
            this.storeLatList = storeLat;
            return this;
        }

        public ConditionBuilder fuzzyStoreLon (List<String> fuzzyStoreLon){
            this.fuzzyStoreLon = fuzzyStoreLon;
            return this;
        }

        public ConditionBuilder fuzzyStoreLon (String ... fuzzyStoreLon){
            this.fuzzyStoreLon = solveNullList(fuzzyStoreLon);
            return this;
        }

        public ConditionBuilder rightFuzzyStoreLon (List<String> rightFuzzyStoreLon){
            this.rightFuzzyStoreLon = rightFuzzyStoreLon;
            return this;
        }

        public ConditionBuilder rightFuzzyStoreLon (String ... rightFuzzyStoreLon){
            this.rightFuzzyStoreLon = solveNullList(rightFuzzyStoreLon);
            return this;
        }

        public ConditionBuilder storeLonList(String ... storeLon){
            this.storeLonList = solveNullList(storeLon);
            return this;
        }

        public ConditionBuilder storeLonList(List<String> storeLon){
            this.storeLonList = storeLon;
            return this;
        }

        public ConditionBuilder fuzzyStoreAddress (List<String> fuzzyStoreAddress){
            this.fuzzyStoreAddress = fuzzyStoreAddress;
            return this;
        }

        public ConditionBuilder fuzzyStoreAddress (String ... fuzzyStoreAddress){
            this.fuzzyStoreAddress = solveNullList(fuzzyStoreAddress);
            return this;
        }

        public ConditionBuilder rightFuzzyStoreAddress (List<String> rightFuzzyStoreAddress){
            this.rightFuzzyStoreAddress = rightFuzzyStoreAddress;
            return this;
        }

        public ConditionBuilder rightFuzzyStoreAddress (String ... rightFuzzyStoreAddress){
            this.rightFuzzyStoreAddress = solveNullList(rightFuzzyStoreAddress);
            return this;
        }

        public ConditionBuilder storeAddressList(String ... storeAddress){
            this.storeAddressList = solveNullList(storeAddress);
            return this;
        }

        public ConditionBuilder storeAddressList(List<String> storeAddress){
            this.storeAddressList = storeAddress;
            return this;
        }

        public ConditionBuilder orderCountBetWeen(Long orderCountSt,Long orderCountEd){
            this.orderCountSt = orderCountSt;
            this.orderCountEd = orderCountEd;
            return this;
        }

        public ConditionBuilder orderCountGreaterEqThan(Long orderCountSt){
            this.orderCountSt = orderCountSt;
            return this;
        }
        public ConditionBuilder orderCountLessEqThan(Long orderCountEd){
            this.orderCountEd = orderCountEd;
            return this;
        }


        public ConditionBuilder orderCountList(Long ... orderCount){
            this.orderCountList = solveNullList(orderCount);
            return this;
        }

        public ConditionBuilder orderCountList(List<Long> orderCount){
            this.orderCountList = orderCount;
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

        private PlusStore obj;

        public Builder(){
            this.obj = new PlusStore();
        }

        public Builder id(Long id){
            this.obj.setId(id);
            return this;
        }
        public Builder storeName(String storeName){
            this.obj.setStoreName(storeName);
            return this;
        }
        public Builder storeType(Integer storeType){
            this.obj.setStoreType(storeType);
            return this;
        }
        public Builder storeImg(String storeImg){
            this.obj.setStoreImg(storeImg);
            return this;
        }
        public Builder storeLat(String storeLat){
            this.obj.setStoreLat(storeLat);
            return this;
        }
        public Builder storeLon(String storeLon){
            this.obj.setStoreLon(storeLon);
            return this;
        }
        public Builder storeAddress(String storeAddress){
            this.obj.setStoreAddress(storeAddress);
            return this;
        }
        public Builder orderCount(Long orderCount){
            this.obj.setOrderCount(orderCount);
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
        public PlusStore build(){return obj;}
    }

}
