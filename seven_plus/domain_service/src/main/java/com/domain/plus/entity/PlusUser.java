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
public class PlusUser implements Serializable {

    private static final long serialVersionUID = 1533693431505L;


    /**
    * 主键
    *  id自增
    * isNullAble:0
    */
    private Long id;

    /**
    * 微信用户唯一标识
    * isNullAble:1
    */
    private String openid;

    /**
    * 微信sessionKey
    * isNullAble:1
    */
    private String sessionKey;

    /**
     * 推荐人ID
     */
    private Long parentId;

    /**
    * 电话
    * isNullAble:1
    */
    private String phone;

    /**
    * 头像
    * isNullAble:1
    */
    private String image;

    /**
    * VIP类型 默认0 无 1：黄金会员 2：铂金会员
    * isNullAble:1,defaultVal:0
    */
    private Integer vipType;

    /**
     * 佣金
     */
    private Long brokerage;

    /**
     * 城市
     */
    private String city;

    /**
    * 创建时间
    * isNullAble:1
    */
    private Long createTime;

    /**
    * 修改时间
    * isNullAble:1
    */
    private Long updateTime;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(Long brokerage) {
        this.brokerage = brokerage;
    }

    public void setId(Long id){this.id = id;}

    public Long getId(){return this.id;}

    public void setOpenid(String openid){this.openid = openid;}

    public String getOpenid(){return this.openid;}

    public void setSessionKey(String sessionKey){this.sessionKey = sessionKey;}

    public String getSessionKey(){return this.sessionKey;}

    public void setPhone(String phone){this.phone = phone;}

    public String getPhone(){return this.phone;}

    public void setImage(String image){this.image = image;}

    public String getImage(){return this.image;}

    public void setVipType(Integer vipType){this.vipType = vipType;}

    public Integer getVipType(){return this.vipType;}

    public void setCreateTime(Long createTime){this.createTime = createTime;}

    public Long getCreateTime(){return this.createTime;}

    public void setUpdateTime(Long updateTime){this.updateTime = updateTime;}

    public Long getUpdateTime(){return this.updateTime;}
    @Override
    public String toString() {
        return "PlusUser{" +
                "id='" + id + '\'' +
                "openid='" + openid + '\'' +
                "sessionKey='" + sessionKey + '\'' +
                "phone='" + phone + '\'' +
                "image='" + image + '\'' +
                "vipType='" + vipType + '\'' +
                "createTime='" + createTime + '\'' +
                "updateTime='" + updateTime + '\'' +
            '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private PlusUser set;

        private ConditionBuilder where;

        public UpdateBuilder set(PlusUser set){
            this.set = set;
            return this;
        }

        public PlusUser getSet(){
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

    public static class QueryBuilder extends PlusUser{
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

        private List<String> openidList;

        public List<String> getOpenidList(){return this.openidList;}


        private List<String> fuzzyOpenid;

        public List<String> getFuzzyOpenid(){return this.fuzzyOpenid;}

        private List<String> rightFuzzyOpenid;

        public List<String> getRightFuzzyOpenid(){return this.rightFuzzyOpenid;}
        private List<String> sessionKeyList;

        public List<String> getSessionKeyList(){return this.sessionKeyList;}


        private List<String> fuzzySessionKey;

        public List<String> getFuzzySessionKey(){return this.fuzzySessionKey;}

        private List<String> rightFuzzySessionKey;

        public List<String> getRightFuzzySessionKey(){return this.rightFuzzySessionKey;}
        private List<String> phoneList;

        public List<String> getPhoneList(){return this.phoneList;}


        private List<String> fuzzyPhone;

        public List<String> getFuzzyPhone(){return this.fuzzyPhone;}

        private List<String> rightFuzzyPhone;

        public List<String> getRightFuzzyPhone(){return this.rightFuzzyPhone;}
        private List<String> imageList;

        public List<String> getImageList(){return this.imageList;}


        private List<String> fuzzyImage;

        public List<String> getFuzzyImage(){return this.fuzzyImage;}

        private List<String> rightFuzzyImage;

        public List<String> getRightFuzzyImage(){return this.rightFuzzyImage;}
        private List<Integer> vipTypeList;

        public List<Integer> getVipTypeList(){return this.vipTypeList;}

        private Integer vipTypeSt;

        private Integer vipTypeEd;

        public Integer getVipTypeSt(){return this.vipTypeSt;}

        public Integer getVipTypeEd(){return this.vipTypeEd;}

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

        public QueryBuilder fuzzyOpenid (List<String> fuzzyOpenid){
            this.fuzzyOpenid = fuzzyOpenid;
            return this;
        }

        public QueryBuilder fuzzyOpenid (String ... fuzzyOpenid){
            this.fuzzyOpenid = solveNullList(fuzzyOpenid);
            return this;
        }

        public QueryBuilder rightFuzzyOpenid (List<String> rightFuzzyOpenid){
            this.rightFuzzyOpenid = rightFuzzyOpenid;
            return this;
        }

        public QueryBuilder rightFuzzyOpenid (String ... rightFuzzyOpenid){
            this.rightFuzzyOpenid = solveNullList(rightFuzzyOpenid);
            return this;
        }

        public QueryBuilder openid(String openid){
            setOpenid(openid);
            return this;
        }

        public QueryBuilder openidList(String ... openid){
            this.openidList = solveNullList(openid);
            return this;
        }

        public QueryBuilder openidList(List<String> openid){
            this.openidList = openid;
            return this;
        }

        public QueryBuilder fetchOpenid(){
            setFetchFields("fetchFields","openid");
            return this;
        }

        public QueryBuilder excludeOpenid(){
            setFetchFields("excludeFields","openid");
            return this;
        }

        public QueryBuilder fuzzySessionKey (List<String> fuzzySessionKey){
            this.fuzzySessionKey = fuzzySessionKey;
            return this;
        }

        public QueryBuilder fuzzySessionKey (String ... fuzzySessionKey){
            this.fuzzySessionKey = solveNullList(fuzzySessionKey);
            return this;
        }

        public QueryBuilder rightFuzzySessionKey (List<String> rightFuzzySessionKey){
            this.rightFuzzySessionKey = rightFuzzySessionKey;
            return this;
        }

        public QueryBuilder rightFuzzySessionKey (String ... rightFuzzySessionKey){
            this.rightFuzzySessionKey = solveNullList(rightFuzzySessionKey);
            return this;
        }

        public QueryBuilder sessionKey(String sessionKey){
            setSessionKey(sessionKey);
            return this;
        }

        public QueryBuilder sessionKeyList(String ... sessionKey){
            this.sessionKeyList = solveNullList(sessionKey);
            return this;
        }

        public QueryBuilder sessionKeyList(List<String> sessionKey){
            this.sessionKeyList = sessionKey;
            return this;
        }

        public QueryBuilder fetchSessionKey(){
            setFetchFields("fetchFields","sessionKey");
            return this;
        }

        public QueryBuilder excludeSessionKey(){
            setFetchFields("excludeFields","sessionKey");
            return this;
        }

        public QueryBuilder fuzzyPhone (List<String> fuzzyPhone){
            this.fuzzyPhone = fuzzyPhone;
            return this;
        }

        public QueryBuilder fuzzyPhone (String ... fuzzyPhone){
            this.fuzzyPhone = solveNullList(fuzzyPhone);
            return this;
        }

        public QueryBuilder rightFuzzyPhone (List<String> rightFuzzyPhone){
            this.rightFuzzyPhone = rightFuzzyPhone;
            return this;
        }

        public QueryBuilder rightFuzzyPhone (String ... rightFuzzyPhone){
            this.rightFuzzyPhone = solveNullList(rightFuzzyPhone);
            return this;
        }

        public QueryBuilder phone(String phone){
            setPhone(phone);
            return this;
        }

        public QueryBuilder phoneList(String ... phone){
            this.phoneList = solveNullList(phone);
            return this;
        }

        public QueryBuilder phoneList(List<String> phone){
            this.phoneList = phone;
            return this;
        }

        public QueryBuilder fetchPhone(){
            setFetchFields("fetchFields","phone");
            return this;
        }

        public QueryBuilder excludePhone(){
            setFetchFields("excludeFields","phone");
            return this;
        }

        public QueryBuilder fuzzyImage (List<String> fuzzyImage){
            this.fuzzyImage = fuzzyImage;
            return this;
        }

        public QueryBuilder fuzzyImage (String ... fuzzyImage){
            this.fuzzyImage = solveNullList(fuzzyImage);
            return this;
        }

        public QueryBuilder rightFuzzyImage (List<String> rightFuzzyImage){
            this.rightFuzzyImage = rightFuzzyImage;
            return this;
        }

        public QueryBuilder rightFuzzyImage (String ... rightFuzzyImage){
            this.rightFuzzyImage = solveNullList(rightFuzzyImage);
            return this;
        }

        public QueryBuilder image(String image){
            setImage(image);
            return this;
        }

        public QueryBuilder imageList(String ... image){
            this.imageList = solveNullList(image);
            return this;
        }

        public QueryBuilder imageList(List<String> image){
            this.imageList = image;
            return this;
        }

        public QueryBuilder fetchImage(){
            setFetchFields("fetchFields","image");
            return this;
        }

        public QueryBuilder excludeImage(){
            setFetchFields("excludeFields","image");
            return this;
        }

        public QueryBuilder vipTypeBetWeen(Integer vipTypeSt,Integer vipTypeEd){
            this.vipTypeSt = vipTypeSt;
            this.vipTypeEd = vipTypeEd;
            return this;
        }

        public QueryBuilder vipTypeGreaterEqThan(Integer vipTypeSt){
            this.vipTypeSt = vipTypeSt;
            return this;
        }
        public QueryBuilder vipTypeLessEqThan(Integer vipTypeEd){
            this.vipTypeEd = vipTypeEd;
            return this;
        }


        public QueryBuilder vipType(Integer vipType){
            setVipType(vipType);
            return this;
        }

        public QueryBuilder vipTypeList(Integer ... vipType){
            this.vipTypeList = solveNullList(vipType);
            return this;
        }

        public QueryBuilder vipTypeList(List<Integer> vipType){
            this.vipTypeList = vipType;
            return this;
        }

        public QueryBuilder fetchVipType(){
            setFetchFields("fetchFields","vipType");
            return this;
        }

        public QueryBuilder excludeVipType(){
            setFetchFields("excludeFields","vipType");
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

        public PlusUser build(){return this;}
    }


    public static class ConditionBuilder{
        private List<Long> idList;

        public List<Long> getIdList(){return this.idList;}

        private Long idSt;

        private Long idEd;

        public Long getIdSt(){return this.idSt;}

        public Long getIdEd(){return this.idEd;}

        private List<String> openidList;

        public List<String> getOpenidList(){return this.openidList;}


        private List<String> fuzzyOpenid;

        public List<String> getFuzzyOpenid(){return this.fuzzyOpenid;}

        private List<String> rightFuzzyOpenid;

        public List<String> getRightFuzzyOpenid(){return this.rightFuzzyOpenid;}
        private List<String> sessionKeyList;

        public List<String> getSessionKeyList(){return this.sessionKeyList;}


        private List<String> fuzzySessionKey;

        public List<String> getFuzzySessionKey(){return this.fuzzySessionKey;}

        private List<String> rightFuzzySessionKey;

        public List<String> getRightFuzzySessionKey(){return this.rightFuzzySessionKey;}
        private List<String> phoneList;

        public List<String> getPhoneList(){return this.phoneList;}


        private List<String> fuzzyPhone;

        public List<String> getFuzzyPhone(){return this.fuzzyPhone;}

        private List<String> rightFuzzyPhone;

        public List<String> getRightFuzzyPhone(){return this.rightFuzzyPhone;}
        private List<String> imageList;

        public List<String> getImageList(){return this.imageList;}


        private List<String> fuzzyImage;

        public List<String> getFuzzyImage(){return this.fuzzyImage;}

        private List<String> rightFuzzyImage;

        public List<String> getRightFuzzyImage(){return this.rightFuzzyImage;}
        private List<Integer> vipTypeList;

        public List<Integer> getVipTypeList(){return this.vipTypeList;}

        private Integer vipTypeSt;

        private Integer vipTypeEd;

        public Integer getVipTypeSt(){return this.vipTypeSt;}

        public Integer getVipTypeEd(){return this.vipTypeEd;}

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

        public ConditionBuilder fuzzyOpenid (List<String> fuzzyOpenid){
            this.fuzzyOpenid = fuzzyOpenid;
            return this;
        }

        public ConditionBuilder fuzzyOpenid (String ... fuzzyOpenid){
            this.fuzzyOpenid = solveNullList(fuzzyOpenid);
            return this;
        }

        public ConditionBuilder rightFuzzyOpenid (List<String> rightFuzzyOpenid){
            this.rightFuzzyOpenid = rightFuzzyOpenid;
            return this;
        }

        public ConditionBuilder rightFuzzyOpenid (String ... rightFuzzyOpenid){
            this.rightFuzzyOpenid = solveNullList(rightFuzzyOpenid);
            return this;
        }

        public ConditionBuilder openidList(String ... openid){
            this.openidList = solveNullList(openid);
            return this;
        }

        public ConditionBuilder openidList(List<String> openid){
            this.openidList = openid;
            return this;
        }

        public ConditionBuilder fuzzySessionKey (List<String> fuzzySessionKey){
            this.fuzzySessionKey = fuzzySessionKey;
            return this;
        }

        public ConditionBuilder fuzzySessionKey (String ... fuzzySessionKey){
            this.fuzzySessionKey = solveNullList(fuzzySessionKey);
            return this;
        }

        public ConditionBuilder rightFuzzySessionKey (List<String> rightFuzzySessionKey){
            this.rightFuzzySessionKey = rightFuzzySessionKey;
            return this;
        }

        public ConditionBuilder rightFuzzySessionKey (String ... rightFuzzySessionKey){
            this.rightFuzzySessionKey = solveNullList(rightFuzzySessionKey);
            return this;
        }

        public ConditionBuilder sessionKeyList(String ... sessionKey){
            this.sessionKeyList = solveNullList(sessionKey);
            return this;
        }

        public ConditionBuilder sessionKeyList(List<String> sessionKey){
            this.sessionKeyList = sessionKey;
            return this;
        }

        public ConditionBuilder fuzzyPhone (List<String> fuzzyPhone){
            this.fuzzyPhone = fuzzyPhone;
            return this;
        }

        public ConditionBuilder fuzzyPhone (String ... fuzzyPhone){
            this.fuzzyPhone = solveNullList(fuzzyPhone);
            return this;
        }

        public ConditionBuilder rightFuzzyPhone (List<String> rightFuzzyPhone){
            this.rightFuzzyPhone = rightFuzzyPhone;
            return this;
        }

        public ConditionBuilder rightFuzzyPhone (String ... rightFuzzyPhone){
            this.rightFuzzyPhone = solveNullList(rightFuzzyPhone);
            return this;
        }

        public ConditionBuilder phoneList(String ... phone){
            this.phoneList = solveNullList(phone);
            return this;
        }

        public ConditionBuilder phoneList(List<String> phone){
            this.phoneList = phone;
            return this;
        }

        public ConditionBuilder fuzzyImage (List<String> fuzzyImage){
            this.fuzzyImage = fuzzyImage;
            return this;
        }

        public ConditionBuilder fuzzyImage (String ... fuzzyImage){
            this.fuzzyImage = solveNullList(fuzzyImage);
            return this;
        }

        public ConditionBuilder rightFuzzyImage (List<String> rightFuzzyImage){
            this.rightFuzzyImage = rightFuzzyImage;
            return this;
        }

        public ConditionBuilder rightFuzzyImage (String ... rightFuzzyImage){
            this.rightFuzzyImage = solveNullList(rightFuzzyImage);
            return this;
        }

        public ConditionBuilder imageList(String ... image){
            this.imageList = solveNullList(image);
            return this;
        }

        public ConditionBuilder imageList(List<String> image){
            this.imageList = image;
            return this;
        }

        public ConditionBuilder vipTypeBetWeen(Integer vipTypeSt,Integer vipTypeEd){
            this.vipTypeSt = vipTypeSt;
            this.vipTypeEd = vipTypeEd;
            return this;
        }

        public ConditionBuilder vipTypeGreaterEqThan(Integer vipTypeSt){
            this.vipTypeSt = vipTypeSt;
            return this;
        }
        public ConditionBuilder vipTypeLessEqThan(Integer vipTypeEd){
            this.vipTypeEd = vipTypeEd;
            return this;
        }


        public ConditionBuilder vipTypeList(Integer ... vipType){
            this.vipTypeList = solveNullList(vipType);
            return this;
        }

        public ConditionBuilder vipTypeList(List<Integer> vipType){
            this.vipTypeList = vipType;
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

        private PlusUser obj;

        public Builder(){
            this.obj = new PlusUser();
        }

        public Builder id(Long id){
            this.obj.setId(id);
            return this;
        }
        public Builder openid(String openid){
            this.obj.setOpenid(openid);
            return this;
        }
        public Builder sessionKey(String sessionKey){
            this.obj.setSessionKey(sessionKey);
            return this;
        }
        public Builder phone(String phone){
            this.obj.setPhone(phone);
            return this;
        }
        public Builder image(String image){
            this.obj.setImage(image);
            return this;
        }
        public Builder vipType(Integer vipType){
            this.obj.setVipType(vipType);
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
        public PlusUser build(){return obj;}
    }

}
