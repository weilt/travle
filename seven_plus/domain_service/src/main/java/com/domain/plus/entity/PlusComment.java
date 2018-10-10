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
public class PlusComment implements Serializable {

    private static final long serialVersionUID = 1533693421850L;


    /**
    * 主键
    * 
    * isNullAble:0
    */
    private Long id;

    /**
    * 用户Id
    * isNullAble:1
    */
    private Long userId;

    /**
    * 网店Id
    * isNullAble:1
    */
    private Long storeId;

    /**
     * 订单使用
     */
    private Long recordId;

    /**
    * 类型 默认0：好评 1：差评 2： TODO
    * isNullAble:1,defaultVal:0
    */
    private Integer type;

    /**
    * 评论内容
    * isNullAble:1
    */
    private String comment;

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


    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public void setId(Long id){this.id = id;}

    public Long getId(){return this.id;}

    public void setUserId(Long userId){this.userId = userId;}

    public Long getUserId(){return this.userId;}

    public void setStoreId(Long storeId){this.storeId = storeId;}

    public Long getStoreId(){return this.storeId;}

    public void setType(Integer type){this.type = type;}

    public Integer getType(){return this.type;}

    public void setComment(String comment){this.comment = comment;}

    public String getComment(){return this.comment;}

    public void setCreateTime(Long createTime){this.createTime = createTime;}

    public Long getCreateTime(){return this.createTime;}

    public void setUpdateTime(Long updateTime){this.updateTime = updateTime;}

    public Long getUpdateTime(){return this.updateTime;}
    @Override
    public String toString() {
        return "PlusComment{" +
                "id='" + id + '\'' +
                "userId='" + userId + '\'' +
                "storeId='" + storeId + '\'' +
                "type='" + type + '\'' +
                "comment='" + comment + '\'' +
                "createTime='" + createTime + '\'' +
                "updateTime='" + updateTime + '\'' +
            '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private PlusComment set;

        private ConditionBuilder where;

        public UpdateBuilder set(PlusComment set){
            this.set = set;
            return this;
        }

        public PlusComment getSet(){
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

    public static class QueryBuilder extends PlusComment{
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

        private List<Long> storeIdList;

        public List<Long> getStoreIdList(){return this.storeIdList;}

        private Long storeIdSt;

        private Long storeIdEd;

        public Long getStoreIdSt(){return this.storeIdSt;}

        public Long getStoreIdEd(){return this.storeIdEd;}

        private List<Integer> typeList;

        public List<Integer> getTypeList(){return this.typeList;}

        private Integer typeSt;

        private Integer typeEd;

        public Integer getTypeSt(){return this.typeSt;}

        public Integer getTypeEd(){return this.typeEd;}

        private List<String> commentList;

        public List<String> getCommentList(){return this.commentList;}


        private List<String> fuzzyComment;

        public List<String> getFuzzyComment(){return this.fuzzyComment;}

        private List<String> rightFuzzyComment;

        public List<String> getRightFuzzyComment(){return this.rightFuzzyComment;}
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

        public QueryBuilder typeBetWeen(Integer typeSt,Integer typeEd){
            this.typeSt = typeSt;
            this.typeEd = typeEd;
            return this;
        }

        public QueryBuilder typeGreaterEqThan(Integer typeSt){
            this.typeSt = typeSt;
            return this;
        }
        public QueryBuilder typeLessEqThan(Integer typeEd){
            this.typeEd = typeEd;
            return this;
        }


        public QueryBuilder type(Integer type){
            setType(type);
            return this;
        }

        public QueryBuilder typeList(Integer ... type){
            this.typeList = solveNullList(type);
            return this;
        }

        public QueryBuilder typeList(List<Integer> type){
            this.typeList = type;
            return this;
        }

        public QueryBuilder fetchType(){
            setFetchFields("fetchFields","type");
            return this;
        }

        public QueryBuilder excludeType(){
            setFetchFields("excludeFields","type");
            return this;
        }

        public QueryBuilder fuzzyComment (List<String> fuzzyComment){
            this.fuzzyComment = fuzzyComment;
            return this;
        }

        public QueryBuilder fuzzyComment (String ... fuzzyComment){
            this.fuzzyComment = solveNullList(fuzzyComment);
            return this;
        }

        public QueryBuilder rightFuzzyComment (List<String> rightFuzzyComment){
            this.rightFuzzyComment = rightFuzzyComment;
            return this;
        }

        public QueryBuilder rightFuzzyComment (String ... rightFuzzyComment){
            this.rightFuzzyComment = solveNullList(rightFuzzyComment);
            return this;
        }

        public QueryBuilder comment(String comment){
            setComment(comment);
            return this;
        }

        public QueryBuilder commentList(String ... comment){
            this.commentList = solveNullList(comment);
            return this;
        }

        public QueryBuilder commentList(List<String> comment){
            this.commentList = comment;
            return this;
        }

        public QueryBuilder fetchComment(){
            setFetchFields("fetchFields","comment");
            return this;
        }

        public QueryBuilder excludeComment(){
            setFetchFields("excludeFields","comment");
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

        public PlusComment build(){return this;}
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

        private List<Long> storeIdList;

        public List<Long> getStoreIdList(){return this.storeIdList;}

        private Long storeIdSt;

        private Long storeIdEd;

        public Long getStoreIdSt(){return this.storeIdSt;}

        public Long getStoreIdEd(){return this.storeIdEd;}

        private List<Integer> typeList;

        public List<Integer> getTypeList(){return this.typeList;}

        private Integer typeSt;

        private Integer typeEd;

        public Integer getTypeSt(){return this.typeSt;}

        public Integer getTypeEd(){return this.typeEd;}

        private List<String> commentList;

        public List<String> getCommentList(){return this.commentList;}


        private List<String> fuzzyComment;

        public List<String> getFuzzyComment(){return this.fuzzyComment;}

        private List<String> rightFuzzyComment;

        public List<String> getRightFuzzyComment(){return this.rightFuzzyComment;}
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

        public ConditionBuilder typeBetWeen(Integer typeSt,Integer typeEd){
            this.typeSt = typeSt;
            this.typeEd = typeEd;
            return this;
        }

        public ConditionBuilder typeGreaterEqThan(Integer typeSt){
            this.typeSt = typeSt;
            return this;
        }
        public ConditionBuilder typeLessEqThan(Integer typeEd){
            this.typeEd = typeEd;
            return this;
        }


        public ConditionBuilder typeList(Integer ... type){
            this.typeList = solveNullList(type);
            return this;
        }

        public ConditionBuilder typeList(List<Integer> type){
            this.typeList = type;
            return this;
        }

        public ConditionBuilder fuzzyComment (List<String> fuzzyComment){
            this.fuzzyComment = fuzzyComment;
            return this;
        }

        public ConditionBuilder fuzzyComment (String ... fuzzyComment){
            this.fuzzyComment = solveNullList(fuzzyComment);
            return this;
        }

        public ConditionBuilder rightFuzzyComment (List<String> rightFuzzyComment){
            this.rightFuzzyComment = rightFuzzyComment;
            return this;
        }

        public ConditionBuilder rightFuzzyComment (String ... rightFuzzyComment){
            this.rightFuzzyComment = solveNullList(rightFuzzyComment);
            return this;
        }

        public ConditionBuilder commentList(String ... comment){
            this.commentList = solveNullList(comment);
            return this;
        }

        public ConditionBuilder commentList(List<String> comment){
            this.commentList = comment;
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

        private PlusComment obj;

        public Builder(){
            this.obj = new PlusComment();
        }

        public Builder id(Long id){
            this.obj.setId(id);
            return this;
        }
        public Builder userId(Long userId){
            this.obj.setUserId(userId);
            return this;
        }
        public Builder storeId(Long storeId){
            this.obj.setStoreId(storeId);
            return this;
        }
        public Builder type(Integer type){
            this.obj.setType(type);
            return this;
        }
        public Builder comment(String comment){
            this.obj.setComment(comment);
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
        public PlusComment build(){return obj;}
    }

}
