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
public class PlusImg implements Serializable {

    private static final long serialVersionUID = 1533693424310L;


    /**
    * 主键
    * id自增
    * isNullAble:0
    */
    private Long id;

    /**
    * 图片名称
    * isNullAble:1
    */
    private String imgName;

    /**
    * 图片地址
    * isNullAble:1
    */
    private String imgUrl;

    /**
    * 图片类型 默认0：广告位 1：划痕无忧宣传图 2：天天洗车宣传图 3：二维码  4：车辆正面示意图  5:左前面示意图
     * 6:右前面示意图  7:左侧面示意图  8:右侧面示意图  9:正后面示意图  10:划痕示意图  11:天天洗车示意图
    * isNullAble:1,defaultVal:0
    */
    private Integer imgType;

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


    public void setId(Long id){this.id = id;}

    public Long getId(){return this.id;}

    public void setImgName(String imgName){this.imgName = imgName;}

    public String getImgName(){return this.imgName;}

    public void setImgUrl(String imgUrl){this.imgUrl = imgUrl;}

    public String getImgUrl(){return this.imgUrl;}

    public void setImgType(Integer imgType){this.imgType = imgType;}

    public Integer getImgType(){return this.imgType;}

    public void setCreateTime(Long createTime){this.createTime = createTime;}

    public Long getCreateTime(){return this.createTime;}

    public void setUpdateTime(Long updateTime){this.updateTime = updateTime;}

    public Long getUpdateTime(){return this.updateTime;}
    @Override
    public String toString() {
        return "PlusImg{" +
                "id='" + id + '\'' +
                "imgName='" + imgName + '\'' +
                "imgUrl='" + imgUrl + '\'' +
                "imgType='" + imgType + '\'' +
                "createTime='" + createTime + '\'' +
                "updateTime='" + updateTime + '\'' +
            '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private PlusImg set;

        private ConditionBuilder where;

        public UpdateBuilder set(PlusImg set){
            this.set = set;
            return this;
        }

        public PlusImg getSet(){
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

    public static class QueryBuilder extends PlusImg{
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

        private List<String> imgNameList;

        public List<String> getImgNameList(){return this.imgNameList;}


        private List<String> fuzzyImgName;

        public List<String> getFuzzyImgName(){return this.fuzzyImgName;}

        private List<String> rightFuzzyImgName;

        public List<String> getRightFuzzyImgName(){return this.rightFuzzyImgName;}
        private List<String> imgUrlList;

        public List<String> getImgUrlList(){return this.imgUrlList;}


        private List<String> fuzzyImgUrl;

        public List<String> getFuzzyImgUrl(){return this.fuzzyImgUrl;}

        private List<String> rightFuzzyImgUrl;

        public List<String> getRightFuzzyImgUrl(){return this.rightFuzzyImgUrl;}
        private List<Integer> imgTypeList;

        public List<Integer> getImgTypeList(){return this.imgTypeList;}

        private Integer imgTypeSt;

        private Integer imgTypeEd;

        public Integer getImgTypeSt(){return this.imgTypeSt;}

        public Integer getImgTypeEd(){return this.imgTypeEd;}

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

        public QueryBuilder fuzzyImgName (List<String> fuzzyImgName){
            this.fuzzyImgName = fuzzyImgName;
            return this;
        }

        public QueryBuilder fuzzyImgName (String ... fuzzyImgName){
            this.fuzzyImgName = solveNullList(fuzzyImgName);
            return this;
        }

        public QueryBuilder rightFuzzyImgName (List<String> rightFuzzyImgName){
            this.rightFuzzyImgName = rightFuzzyImgName;
            return this;
        }

        public QueryBuilder rightFuzzyImgName (String ... rightFuzzyImgName){
            this.rightFuzzyImgName = solveNullList(rightFuzzyImgName);
            return this;
        }

        public QueryBuilder imgName(String imgName){
            setImgName(imgName);
            return this;
        }

        public QueryBuilder imgNameList(String ... imgName){
            this.imgNameList = solveNullList(imgName);
            return this;
        }

        public QueryBuilder imgNameList(List<String> imgName){
            this.imgNameList = imgName;
            return this;
        }

        public QueryBuilder fetchImgName(){
            setFetchFields("fetchFields","imgName");
            return this;
        }

        public QueryBuilder excludeImgName(){
            setFetchFields("excludeFields","imgName");
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

        public QueryBuilder imgTypeBetWeen(Integer imgTypeSt,Integer imgTypeEd){
            this.imgTypeSt = imgTypeSt;
            this.imgTypeEd = imgTypeEd;
            return this;
        }

        public QueryBuilder imgTypeGreaterEqThan(Integer imgTypeSt){
            this.imgTypeSt = imgTypeSt;
            return this;
        }
        public QueryBuilder imgTypeLessEqThan(Integer imgTypeEd){
            this.imgTypeEd = imgTypeEd;
            return this;
        }


        public QueryBuilder imgType(Integer imgType){
            setImgType(imgType);
            return this;
        }

        public QueryBuilder imgTypeList(Integer ... imgType){
            this.imgTypeList = solveNullList(imgType);
            return this;
        }

        public QueryBuilder imgTypeList(List<Integer> imgType){
            this.imgTypeList = imgType;
            return this;
        }

        public QueryBuilder fetchImgType(){
            setFetchFields("fetchFields","imgType");
            return this;
        }

        public QueryBuilder excludeImgType(){
            setFetchFields("excludeFields","imgType");
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

        public PlusImg build(){return this;}
    }


    public static class ConditionBuilder{
        private List<Long> idList;

        public List<Long> getIdList(){return this.idList;}

        private Long idSt;

        private Long idEd;

        public Long getIdSt(){return this.idSt;}

        public Long getIdEd(){return this.idEd;}

        private List<String> imgNameList;

        public List<String> getImgNameList(){return this.imgNameList;}


        private List<String> fuzzyImgName;

        public List<String> getFuzzyImgName(){return this.fuzzyImgName;}

        private List<String> rightFuzzyImgName;

        public List<String> getRightFuzzyImgName(){return this.rightFuzzyImgName;}
        private List<String> imgUrlList;

        public List<String> getImgUrlList(){return this.imgUrlList;}


        private List<String> fuzzyImgUrl;

        public List<String> getFuzzyImgUrl(){return this.fuzzyImgUrl;}

        private List<String> rightFuzzyImgUrl;

        public List<String> getRightFuzzyImgUrl(){return this.rightFuzzyImgUrl;}
        private List<Integer> imgTypeList;

        public List<Integer> getImgTypeList(){return this.imgTypeList;}

        private Integer imgTypeSt;

        private Integer imgTypeEd;

        public Integer getImgTypeSt(){return this.imgTypeSt;}

        public Integer getImgTypeEd(){return this.imgTypeEd;}

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

        public ConditionBuilder fuzzyImgName (List<String> fuzzyImgName){
            this.fuzzyImgName = fuzzyImgName;
            return this;
        }

        public ConditionBuilder fuzzyImgName (String ... fuzzyImgName){
            this.fuzzyImgName = solveNullList(fuzzyImgName);
            return this;
        }

        public ConditionBuilder rightFuzzyImgName (List<String> rightFuzzyImgName){
            this.rightFuzzyImgName = rightFuzzyImgName;
            return this;
        }

        public ConditionBuilder rightFuzzyImgName (String ... rightFuzzyImgName){
            this.rightFuzzyImgName = solveNullList(rightFuzzyImgName);
            return this;
        }

        public ConditionBuilder imgNameList(String ... imgName){
            this.imgNameList = solveNullList(imgName);
            return this;
        }

        public ConditionBuilder imgNameList(List<String> imgName){
            this.imgNameList = imgName;
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

        public ConditionBuilder imgTypeBetWeen(Integer imgTypeSt,Integer imgTypeEd){
            this.imgTypeSt = imgTypeSt;
            this.imgTypeEd = imgTypeEd;
            return this;
        }

        public ConditionBuilder imgTypeGreaterEqThan(Integer imgTypeSt){
            this.imgTypeSt = imgTypeSt;
            return this;
        }
        public ConditionBuilder imgTypeLessEqThan(Integer imgTypeEd){
            this.imgTypeEd = imgTypeEd;
            return this;
        }


        public ConditionBuilder imgTypeList(Integer ... imgType){
            this.imgTypeList = solveNullList(imgType);
            return this;
        }

        public ConditionBuilder imgTypeList(List<Integer> imgType){
            this.imgTypeList = imgType;
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

        private PlusImg obj;

        public Builder(){
            this.obj = new PlusImg();
        }

        public Builder id(Long id){
            this.obj.setId(id);
            return this;
        }
        public Builder imgName(String imgName){
            this.obj.setImgName(imgName);
            return this;
        }
        public Builder imgUrl(String imgUrl){
            this.obj.setImgUrl(imgUrl);
            return this;
        }
        public Builder imgType(Integer imgType){
            this.obj.setImgType(imgType);
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
        public PlusImg build(){return obj;}
    }

}
