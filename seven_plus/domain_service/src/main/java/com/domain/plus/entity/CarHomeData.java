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
public class CarHomeData implements Serializable {

    private static final long serialVersionUID = 1534758949450L;


    /**
    * 主键
    * 
    * isNullAble:0
    */
    private Long id;

    /**
    * 品牌
    * isNullAble:1
    */
    private String brand;

    /**
    * 厂商
    * isNullAble:1
    */
    private String vendor;

    /**
    * 型号
    * isNullAble:1
    */
    private String model;

    /**
    * 子型号
    * isNullAble:1
    */
    private String subModel;

    /**
    * 价格（指导价）万
    * isNullAble:1
    */
    private String price;

    /**
    * 汽车之家ID(autohome_id)
    * isNullAble:1
    */
    private String autohomeId;

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

    public void setBrand(String brand){this.brand = brand;}

    public String getBrand(){return this.brand;}

    public void setVendor(String vendor){this.vendor = vendor;}

    public String getVendor(){return this.vendor;}

    public void setModel(String model){this.model = model;}

    public String getModel(){return this.model;}

    public void setSubModel(String subModel){this.subModel = subModel;}

    public String getSubModel(){return this.subModel;}

    public void setPrice(String price){this.price = price;}

    public String getPrice(){return this.price;}

    public void setAutohomeId(String autohomeId){this.autohomeId = autohomeId;}

    public String getAutohomeId(){return this.autohomeId;}

    public void setCreateTime(Long createTime){this.createTime = createTime;}

    public Long getCreateTime(){return this.createTime;}

    public void setUpdateTime(Long updateTime){this.updateTime = updateTime;}

    public Long getUpdateTime(){return this.updateTime;}
    @Override
    public String toString() {
        return "CarHomeData{" +
                "id='" + id + '\'' +
                "brand='" + brand + '\'' +
                "vendor='" + vendor + '\'' +
                "model='" + model + '\'' +
                "subModel='" + subModel + '\'' +
                "price='" + price + '\'' +
                "autohomeId='" + autohomeId + '\'' +
                "createTime='" + createTime + '\'' +
                "updateTime='" + updateTime + '\'' +
            '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private CarHomeData set;

        private ConditionBuilder where;

        public UpdateBuilder set(CarHomeData set){
            this.set = set;
            return this;
        }

        public CarHomeData getSet(){
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

    public static class QueryBuilder extends CarHomeData{
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

        private List<String> brandList;

        public List<String> getBrandList(){return this.brandList;}


        private List<String> fuzzyBrand;

        public List<String> getFuzzyBrand(){return this.fuzzyBrand;}

        private List<String> rightFuzzyBrand;

        public List<String> getRightFuzzyBrand(){return this.rightFuzzyBrand;}
        private List<String> vendorList;

        public List<String> getVendorList(){return this.vendorList;}


        private List<String> fuzzyVendor;

        public List<String> getFuzzyVendor(){return this.fuzzyVendor;}

        private List<String> rightFuzzyVendor;

        public List<String> getRightFuzzyVendor(){return this.rightFuzzyVendor;}
        private List<String> modelList;

        public List<String> getModelList(){return this.modelList;}


        private List<String> fuzzyModel;

        public List<String> getFuzzyModel(){return this.fuzzyModel;}

        private List<String> rightFuzzyModel;

        public List<String> getRightFuzzyModel(){return this.rightFuzzyModel;}
        private List<String> subModelList;

        public List<String> getSubModelList(){return this.subModelList;}


        private List<String> fuzzySubModel;

        public List<String> getFuzzySubModel(){return this.fuzzySubModel;}

        private List<String> rightFuzzySubModel;

        public List<String> getRightFuzzySubModel(){return this.rightFuzzySubModel;}
        private List<String> priceList;

        public List<String> getPriceList(){return this.priceList;}


        private List<String> fuzzyPrice;

        public List<String> getFuzzyPrice(){return this.fuzzyPrice;}

        private List<String> rightFuzzyPrice;

        public List<String> getRightFuzzyPrice(){return this.rightFuzzyPrice;}
        private List<String> autohomeIdList;

        public List<String> getAutohomeIdList(){return this.autohomeIdList;}


        private List<String> fuzzyAutohomeId;

        public List<String> getFuzzyAutohomeId(){return this.fuzzyAutohomeId;}

        private List<String> rightFuzzyAutohomeId;

        public List<String> getRightFuzzyAutohomeId(){return this.rightFuzzyAutohomeId;}
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

        public QueryBuilder fuzzyBrand (List<String> fuzzyBrand){
            this.fuzzyBrand = fuzzyBrand;
            return this;
        }

        public QueryBuilder fuzzyBrand (String ... fuzzyBrand){
            this.fuzzyBrand = solveNullList(fuzzyBrand);
            return this;
        }

        public QueryBuilder rightFuzzyBrand (List<String> rightFuzzyBrand){
            this.rightFuzzyBrand = rightFuzzyBrand;
            return this;
        }

        public QueryBuilder rightFuzzyBrand (String ... rightFuzzyBrand){
            this.rightFuzzyBrand = solveNullList(rightFuzzyBrand);
            return this;
        }

        public QueryBuilder brand(String brand){
            setBrand(brand);
            return this;
        }

        public QueryBuilder brandList(String ... brand){
            this.brandList = solveNullList(brand);
            return this;
        }

        public QueryBuilder brandList(List<String> brand){
            this.brandList = brand;
            return this;
        }

        public QueryBuilder fetchBrand(){
            setFetchFields("fetchFields","brand");
            return this;
        }

        public QueryBuilder excludeBrand(){
            setFetchFields("excludeFields","brand");
            return this;
        }

        public QueryBuilder fuzzyVendor (List<String> fuzzyVendor){
            this.fuzzyVendor = fuzzyVendor;
            return this;
        }

        public QueryBuilder fuzzyVendor (String ... fuzzyVendor){
            this.fuzzyVendor = solveNullList(fuzzyVendor);
            return this;
        }

        public QueryBuilder rightFuzzyVendor (List<String> rightFuzzyVendor){
            this.rightFuzzyVendor = rightFuzzyVendor;
            return this;
        }

        public QueryBuilder rightFuzzyVendor (String ... rightFuzzyVendor){
            this.rightFuzzyVendor = solveNullList(rightFuzzyVendor);
            return this;
        }

        public QueryBuilder vendor(String vendor){
            setVendor(vendor);
            return this;
        }

        public QueryBuilder vendorList(String ... vendor){
            this.vendorList = solveNullList(vendor);
            return this;
        }

        public QueryBuilder vendorList(List<String> vendor){
            this.vendorList = vendor;
            return this;
        }

        public QueryBuilder fetchVendor(){
            setFetchFields("fetchFields","vendor");
            return this;
        }

        public QueryBuilder excludeVendor(){
            setFetchFields("excludeFields","vendor");
            return this;
        }

        public QueryBuilder fuzzyModel (List<String> fuzzyModel){
            this.fuzzyModel = fuzzyModel;
            return this;
        }

        public QueryBuilder fuzzyModel (String ... fuzzyModel){
            this.fuzzyModel = solveNullList(fuzzyModel);
            return this;
        }

        public QueryBuilder rightFuzzyModel (List<String> rightFuzzyModel){
            this.rightFuzzyModel = rightFuzzyModel;
            return this;
        }

        public QueryBuilder rightFuzzyModel (String ... rightFuzzyModel){
            this.rightFuzzyModel = solveNullList(rightFuzzyModel);
            return this;
        }

        public QueryBuilder model(String model){
            setModel(model);
            return this;
        }

        public QueryBuilder modelList(String ... model){
            this.modelList = solveNullList(model);
            return this;
        }

        public QueryBuilder modelList(List<String> model){
            this.modelList = model;
            return this;
        }

        public QueryBuilder fetchModel(){
            setFetchFields("fetchFields","model");
            return this;
        }

        public QueryBuilder excludeModel(){
            setFetchFields("excludeFields","model");
            return this;
        }

        public QueryBuilder fuzzySubModel (List<String> fuzzySubModel){
            this.fuzzySubModel = fuzzySubModel;
            return this;
        }

        public QueryBuilder fuzzySubModel (String ... fuzzySubModel){
            this.fuzzySubModel = solveNullList(fuzzySubModel);
            return this;
        }

        public QueryBuilder rightFuzzySubModel (List<String> rightFuzzySubModel){
            this.rightFuzzySubModel = rightFuzzySubModel;
            return this;
        }

        public QueryBuilder rightFuzzySubModel (String ... rightFuzzySubModel){
            this.rightFuzzySubModel = solveNullList(rightFuzzySubModel);
            return this;
        }

        public QueryBuilder subModel(String subModel){
            setSubModel(subModel);
            return this;
        }

        public QueryBuilder subModelList(String ... subModel){
            this.subModelList = solveNullList(subModel);
            return this;
        }

        public QueryBuilder subModelList(List<String> subModel){
            this.subModelList = subModel;
            return this;
        }

        public QueryBuilder fetchSubModel(){
            setFetchFields("fetchFields","subModel");
            return this;
        }

        public QueryBuilder excludeSubModel(){
            setFetchFields("excludeFields","subModel");
            return this;
        }

        public QueryBuilder fuzzyPrice (List<String> fuzzyPrice){
            this.fuzzyPrice = fuzzyPrice;
            return this;
        }

        public QueryBuilder fuzzyPrice (String ... fuzzyPrice){
            this.fuzzyPrice = solveNullList(fuzzyPrice);
            return this;
        }

        public QueryBuilder rightFuzzyPrice (List<String> rightFuzzyPrice){
            this.rightFuzzyPrice = rightFuzzyPrice;
            return this;
        }

        public QueryBuilder rightFuzzyPrice (String ... rightFuzzyPrice){
            this.rightFuzzyPrice = solveNullList(rightFuzzyPrice);
            return this;
        }

        public QueryBuilder price(String price){
            setPrice(price);
            return this;
        }

        public QueryBuilder priceList(String ... price){
            this.priceList = solveNullList(price);
            return this;
        }

        public QueryBuilder priceList(List<String> price){
            this.priceList = price;
            return this;
        }

        public QueryBuilder fetchPrice(){
            setFetchFields("fetchFields","price");
            return this;
        }

        public QueryBuilder excludePrice(){
            setFetchFields("excludeFields","price");
            return this;
        }

        public QueryBuilder fuzzyAutohomeId (List<String> fuzzyAutohomeId){
            this.fuzzyAutohomeId = fuzzyAutohomeId;
            return this;
        }

        public QueryBuilder fuzzyAutohomeId (String ... fuzzyAutohomeId){
            this.fuzzyAutohomeId = solveNullList(fuzzyAutohomeId);
            return this;
        }

        public QueryBuilder rightFuzzyAutohomeId (List<String> rightFuzzyAutohomeId){
            this.rightFuzzyAutohomeId = rightFuzzyAutohomeId;
            return this;
        }

        public QueryBuilder rightFuzzyAutohomeId (String ... rightFuzzyAutohomeId){
            this.rightFuzzyAutohomeId = solveNullList(rightFuzzyAutohomeId);
            return this;
        }

        public QueryBuilder autohomeId(String autohomeId){
            setAutohomeId(autohomeId);
            return this;
        }

        public QueryBuilder autohomeIdList(String ... autohomeId){
            this.autohomeIdList = solveNullList(autohomeId);
            return this;
        }

        public QueryBuilder autohomeIdList(List<String> autohomeId){
            this.autohomeIdList = autohomeId;
            return this;
        }

        public QueryBuilder fetchAutohomeId(){
            setFetchFields("fetchFields","autohomeId");
            return this;
        }

        public QueryBuilder excludeAutohomeId(){
            setFetchFields("excludeFields","autohomeId");
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

        public CarHomeData build(){return this;}
    }


    public static class ConditionBuilder{
        private List<Long> idList;

        public List<Long> getIdList(){return this.idList;}

        private Long idSt;

        private Long idEd;

        public Long getIdSt(){return this.idSt;}

        public Long getIdEd(){return this.idEd;}

        private List<String> brandList;

        public List<String> getBrandList(){return this.brandList;}


        private List<String> fuzzyBrand;

        public List<String> getFuzzyBrand(){return this.fuzzyBrand;}

        private List<String> rightFuzzyBrand;

        public List<String> getRightFuzzyBrand(){return this.rightFuzzyBrand;}
        private List<String> vendorList;

        public List<String> getVendorList(){return this.vendorList;}


        private List<String> fuzzyVendor;

        public List<String> getFuzzyVendor(){return this.fuzzyVendor;}

        private List<String> rightFuzzyVendor;

        public List<String> getRightFuzzyVendor(){return this.rightFuzzyVendor;}
        private List<String> modelList;

        public List<String> getModelList(){return this.modelList;}


        private List<String> fuzzyModel;

        public List<String> getFuzzyModel(){return this.fuzzyModel;}

        private List<String> rightFuzzyModel;

        public List<String> getRightFuzzyModel(){return this.rightFuzzyModel;}
        private List<String> subModelList;

        public List<String> getSubModelList(){return this.subModelList;}


        private List<String> fuzzySubModel;

        public List<String> getFuzzySubModel(){return this.fuzzySubModel;}

        private List<String> rightFuzzySubModel;

        public List<String> getRightFuzzySubModel(){return this.rightFuzzySubModel;}
        private List<String> priceList;

        public List<String> getPriceList(){return this.priceList;}


        private List<String> fuzzyPrice;

        public List<String> getFuzzyPrice(){return this.fuzzyPrice;}

        private List<String> rightFuzzyPrice;

        public List<String> getRightFuzzyPrice(){return this.rightFuzzyPrice;}
        private List<String> autohomeIdList;

        public List<String> getAutohomeIdList(){return this.autohomeIdList;}


        private List<String> fuzzyAutohomeId;

        public List<String> getFuzzyAutohomeId(){return this.fuzzyAutohomeId;}

        private List<String> rightFuzzyAutohomeId;

        public List<String> getRightFuzzyAutohomeId(){return this.rightFuzzyAutohomeId;}
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

        public ConditionBuilder fuzzyBrand (List<String> fuzzyBrand){
            this.fuzzyBrand = fuzzyBrand;
            return this;
        }

        public ConditionBuilder fuzzyBrand (String ... fuzzyBrand){
            this.fuzzyBrand = solveNullList(fuzzyBrand);
            return this;
        }

        public ConditionBuilder rightFuzzyBrand (List<String> rightFuzzyBrand){
            this.rightFuzzyBrand = rightFuzzyBrand;
            return this;
        }

        public ConditionBuilder rightFuzzyBrand (String ... rightFuzzyBrand){
            this.rightFuzzyBrand = solveNullList(rightFuzzyBrand);
            return this;
        }

        public ConditionBuilder brandList(String ... brand){
            this.brandList = solveNullList(brand);
            return this;
        }

        public ConditionBuilder brandList(List<String> brand){
            this.brandList = brand;
            return this;
        }

        public ConditionBuilder fuzzyVendor (List<String> fuzzyVendor){
            this.fuzzyVendor = fuzzyVendor;
            return this;
        }

        public ConditionBuilder fuzzyVendor (String ... fuzzyVendor){
            this.fuzzyVendor = solveNullList(fuzzyVendor);
            return this;
        }

        public ConditionBuilder rightFuzzyVendor (List<String> rightFuzzyVendor){
            this.rightFuzzyVendor = rightFuzzyVendor;
            return this;
        }

        public ConditionBuilder rightFuzzyVendor (String ... rightFuzzyVendor){
            this.rightFuzzyVendor = solveNullList(rightFuzzyVendor);
            return this;
        }

        public ConditionBuilder vendorList(String ... vendor){
            this.vendorList = solveNullList(vendor);
            return this;
        }

        public ConditionBuilder vendorList(List<String> vendor){
            this.vendorList = vendor;
            return this;
        }

        public ConditionBuilder fuzzyModel (List<String> fuzzyModel){
            this.fuzzyModel = fuzzyModel;
            return this;
        }

        public ConditionBuilder fuzzyModel (String ... fuzzyModel){
            this.fuzzyModel = solveNullList(fuzzyModel);
            return this;
        }

        public ConditionBuilder rightFuzzyModel (List<String> rightFuzzyModel){
            this.rightFuzzyModel = rightFuzzyModel;
            return this;
        }

        public ConditionBuilder rightFuzzyModel (String ... rightFuzzyModel){
            this.rightFuzzyModel = solveNullList(rightFuzzyModel);
            return this;
        }

        public ConditionBuilder modelList(String ... model){
            this.modelList = solveNullList(model);
            return this;
        }

        public ConditionBuilder modelList(List<String> model){
            this.modelList = model;
            return this;
        }

        public ConditionBuilder fuzzySubModel (List<String> fuzzySubModel){
            this.fuzzySubModel = fuzzySubModel;
            return this;
        }

        public ConditionBuilder fuzzySubModel (String ... fuzzySubModel){
            this.fuzzySubModel = solveNullList(fuzzySubModel);
            return this;
        }

        public ConditionBuilder rightFuzzySubModel (List<String> rightFuzzySubModel){
            this.rightFuzzySubModel = rightFuzzySubModel;
            return this;
        }

        public ConditionBuilder rightFuzzySubModel (String ... rightFuzzySubModel){
            this.rightFuzzySubModel = solveNullList(rightFuzzySubModel);
            return this;
        }

        public ConditionBuilder subModelList(String ... subModel){
            this.subModelList = solveNullList(subModel);
            return this;
        }

        public ConditionBuilder subModelList(List<String> subModel){
            this.subModelList = subModel;
            return this;
        }

        public ConditionBuilder fuzzyPrice (List<String> fuzzyPrice){
            this.fuzzyPrice = fuzzyPrice;
            return this;
        }

        public ConditionBuilder fuzzyPrice (String ... fuzzyPrice){
            this.fuzzyPrice = solveNullList(fuzzyPrice);
            return this;
        }

        public ConditionBuilder rightFuzzyPrice (List<String> rightFuzzyPrice){
            this.rightFuzzyPrice = rightFuzzyPrice;
            return this;
        }

        public ConditionBuilder rightFuzzyPrice (String ... rightFuzzyPrice){
            this.rightFuzzyPrice = solveNullList(rightFuzzyPrice);
            return this;
        }

        public ConditionBuilder priceList(String ... price){
            this.priceList = solveNullList(price);
            return this;
        }

        public ConditionBuilder priceList(List<String> price){
            this.priceList = price;
            return this;
        }

        public ConditionBuilder fuzzyAutohomeId (List<String> fuzzyAutohomeId){
            this.fuzzyAutohomeId = fuzzyAutohomeId;
            return this;
        }

        public ConditionBuilder fuzzyAutohomeId (String ... fuzzyAutohomeId){
            this.fuzzyAutohomeId = solveNullList(fuzzyAutohomeId);
            return this;
        }

        public ConditionBuilder rightFuzzyAutohomeId (List<String> rightFuzzyAutohomeId){
            this.rightFuzzyAutohomeId = rightFuzzyAutohomeId;
            return this;
        }

        public ConditionBuilder rightFuzzyAutohomeId (String ... rightFuzzyAutohomeId){
            this.rightFuzzyAutohomeId = solveNullList(rightFuzzyAutohomeId);
            return this;
        }

        public ConditionBuilder autohomeIdList(String ... autohomeId){
            this.autohomeIdList = solveNullList(autohomeId);
            return this;
        }

        public ConditionBuilder autohomeIdList(List<String> autohomeId){
            this.autohomeIdList = autohomeId;
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

        private CarHomeData obj;

        public Builder(){
            this.obj = new CarHomeData();
        }

        public Builder id(Long id){
            this.obj.setId(id);
            return this;
        }
        public Builder brand(String brand){
            this.obj.setBrand(brand);
            return this;
        }
        public Builder vendor(String vendor){
            this.obj.setVendor(vendor);
            return this;
        }
        public Builder model(String model){
            this.obj.setModel(model);
            return this;
        }
        public Builder subModel(String subModel){
            this.obj.setSubModel(subModel);
            return this;
        }
        public Builder price(String price){
            this.obj.setPrice(price);
            return this;
        }
        public Builder autohomeId(String autohomeId){
            this.obj.setAutohomeId(autohomeId);
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
        public CarHomeData build(){return obj;}
    }

}
