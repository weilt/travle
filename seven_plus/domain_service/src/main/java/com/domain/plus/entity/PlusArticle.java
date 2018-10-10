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
public class PlusArticle implements Serializable {

    private static final long serialVersionUID = 1533693416338L;


    /**
    * 主键
    * 主键自增
    * isNullAble:0
    */
    private Long id;

    /**
    * 文章标题
    * isNullAble:1
    */
    private String articleTitel;

    /**
    * 文章分类 默认0 1： 划痕无忧  2：天天洗车 3：会员协议 4：车友群介绍 5:道路抢险 6：代办车贷
    * isNullAble:1,defaultVal:0
    */
    private Integer articleType;

    /**
    * 文章内容
    * isNullAble:1
    */
    private String articleContent;

    /**
    * 创建者ID
    * isNullAble:1,defaultVal:0
    */
    private Long userId;

    /**
    * 联系电话
    * isNullAble:1
    */
    private String contactsPhome;

    /**
    * 联系微信
    * isNullAble:1
    */
    private String contactsWechat;

    /**
    * 创建时间
    * isNullAble:1,defaultVal:0
    */
    private Long createTime;

    /**
    * 
    * isNullAble:1,defaultVal:0
    */
    private Long updateTime;


    public void setId(Long id){this.id = id;}

    public Long getId(){return this.id;}

    public void setArticleTitel(String articleTitel){this.articleTitel = articleTitel;}

    public String getArticleTitel(){return this.articleTitel;}

    public void setArticleType(Integer articleType){this.articleType = articleType;}

    public Integer getArticleType(){return this.articleType;}

    public void setArticleContent(String articleContent){this.articleContent = articleContent;}

    public String getArticleContent(){return this.articleContent;}

    public void setUserId(Long userId){this.userId = userId;}

    public Long getUserId(){return this.userId;}

    public void setContactsPhome(String contactsPhome){this.contactsPhome = contactsPhome;}

    public String getContactsPhome(){return this.contactsPhome;}

    public void setContactsWechat(String contactsWechat){this.contactsWechat = contactsWechat;}

    public String getContactsWechat(){return this.contactsWechat;}

    public void setCreateTime(Long createTime){this.createTime = createTime;}

    public Long getCreateTime(){return this.createTime;}

    public void setUpdateTime(Long updateTime){this.updateTime = updateTime;}

    public Long getUpdateTime(){return this.updateTime;}
    @Override
    public String toString() {
        return "PlusArticle{" +
                "id='" + id + '\'' +
                "articleTitel='" + articleTitel + '\'' +
                "articleType='" + articleType + '\'' +
                "articleContent='" + articleContent + '\'' +
                "userId='" + userId + '\'' +
                "contactsPhome='" + contactsPhome + '\'' +
                "contactsWechat='" + contactsWechat + '\'' +
                "createTime='" + createTime + '\'' +
                "updateTime='" + updateTime + '\'' +
            '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private PlusArticle set;

        private ConditionBuilder where;

        public UpdateBuilder set(PlusArticle set){
            this.set = set;
            return this;
        }

        public PlusArticle getSet(){
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

    public static class QueryBuilder extends PlusArticle{
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

        private List<String> articleTitelList;

        public List<String> getArticleTitelList(){return this.articleTitelList;}


        private List<String> fuzzyArticleTitel;

        public List<String> getFuzzyArticleTitel(){return this.fuzzyArticleTitel;}

        private List<String> rightFuzzyArticleTitel;

        public List<String> getRightFuzzyArticleTitel(){return this.rightFuzzyArticleTitel;}
        private List<Integer> articleTypeList;

        public List<Integer> getArticleTypeList(){return this.articleTypeList;}

        private Integer articleTypeSt;

        private Integer articleTypeEd;

        public Integer getArticleTypeSt(){return this.articleTypeSt;}

        public Integer getArticleTypeEd(){return this.articleTypeEd;}

        private List<String> articleContentList;

        public List<String> getArticleContentList(){return this.articleContentList;}


        private List<String> fuzzyArticleContent;

        public List<String> getFuzzyArticleContent(){return this.fuzzyArticleContent;}

        private List<String> rightFuzzyArticleContent;

        public List<String> getRightFuzzyArticleContent(){return this.rightFuzzyArticleContent;}
        private List<Long> userIdList;

        public List<Long> getUserIdList(){return this.userIdList;}

        private Long userIdSt;

        private Long userIdEd;

        public Long getUserIdSt(){return this.userIdSt;}

        public Long getUserIdEd(){return this.userIdEd;}

        private List<String> contactsPhomeList;

        public List<String> getContactsPhomeList(){return this.contactsPhomeList;}


        private List<String> fuzzyContactsPhome;

        public List<String> getFuzzyContactsPhome(){return this.fuzzyContactsPhome;}

        private List<String> rightFuzzyContactsPhome;

        public List<String> getRightFuzzyContactsPhome(){return this.rightFuzzyContactsPhome;}
        private List<String> contactsWechatList;

        public List<String> getContactsWechatList(){return this.contactsWechatList;}


        private List<String> fuzzyContactsWechat;

        public List<String> getFuzzyContactsWechat(){return this.fuzzyContactsWechat;}

        private List<String> rightFuzzyContactsWechat;

        public List<String> getRightFuzzyContactsWechat(){return this.rightFuzzyContactsWechat;}
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

        public QueryBuilder fuzzyArticleTitel (List<String> fuzzyArticleTitel){
            this.fuzzyArticleTitel = fuzzyArticleTitel;
            return this;
        }

        public QueryBuilder fuzzyArticleTitel (String ... fuzzyArticleTitel){
            this.fuzzyArticleTitel = solveNullList(fuzzyArticleTitel);
            return this;
        }

        public QueryBuilder rightFuzzyArticleTitel (List<String> rightFuzzyArticleTitel){
            this.rightFuzzyArticleTitel = rightFuzzyArticleTitel;
            return this;
        }

        public QueryBuilder rightFuzzyArticleTitel (String ... rightFuzzyArticleTitel){
            this.rightFuzzyArticleTitel = solveNullList(rightFuzzyArticleTitel);
            return this;
        }

        public QueryBuilder articleTitel(String articleTitel){
            setArticleTitel(articleTitel);
            return this;
        }

        public QueryBuilder articleTitelList(String ... articleTitel){
            this.articleTitelList = solveNullList(articleTitel);
            return this;
        }

        public QueryBuilder articleTitelList(List<String> articleTitel){
            this.articleTitelList = articleTitel;
            return this;
        }

        public QueryBuilder fetchArticleTitel(){
            setFetchFields("fetchFields","articleTitel");
            return this;
        }

        public QueryBuilder excludeArticleTitel(){
            setFetchFields("excludeFields","articleTitel");
            return this;
        }

        public QueryBuilder articleTypeBetWeen(Integer articleTypeSt,Integer articleTypeEd){
            this.articleTypeSt = articleTypeSt;
            this.articleTypeEd = articleTypeEd;
            return this;
        }

        public QueryBuilder articleTypeGreaterEqThan(Integer articleTypeSt){
            this.articleTypeSt = articleTypeSt;
            return this;
        }
        public QueryBuilder articleTypeLessEqThan(Integer articleTypeEd){
            this.articleTypeEd = articleTypeEd;
            return this;
        }


        public QueryBuilder articleType(Integer articleType){
            setArticleType(articleType);
            return this;
        }

        public QueryBuilder articleTypeList(Integer ... articleType){
            this.articleTypeList = solveNullList(articleType);
            return this;
        }

        public QueryBuilder articleTypeList(List<Integer> articleType){
            this.articleTypeList = articleType;
            return this;
        }

        public QueryBuilder fetchArticleType(){
            setFetchFields("fetchFields","articleType");
            return this;
        }

        public QueryBuilder excludeArticleType(){
            setFetchFields("excludeFields","articleType");
            return this;
        }

        public QueryBuilder fuzzyArticleContent (List<String> fuzzyArticleContent){
            this.fuzzyArticleContent = fuzzyArticleContent;
            return this;
        }

        public QueryBuilder fuzzyArticleContent (String ... fuzzyArticleContent){
            this.fuzzyArticleContent = solveNullList(fuzzyArticleContent);
            return this;
        }

        public QueryBuilder rightFuzzyArticleContent (List<String> rightFuzzyArticleContent){
            this.rightFuzzyArticleContent = rightFuzzyArticleContent;
            return this;
        }

        public QueryBuilder rightFuzzyArticleContent (String ... rightFuzzyArticleContent){
            this.rightFuzzyArticleContent = solveNullList(rightFuzzyArticleContent);
            return this;
        }

        public QueryBuilder articleContent(String articleContent){
            setArticleContent(articleContent);
            return this;
        }

        public QueryBuilder articleContentList(String ... articleContent){
            this.articleContentList = solveNullList(articleContent);
            return this;
        }

        public QueryBuilder articleContentList(List<String> articleContent){
            this.articleContentList = articleContent;
            return this;
        }

        public QueryBuilder fetchArticleContent(){
            setFetchFields("fetchFields","articleContent");
            return this;
        }

        public QueryBuilder excludeArticleContent(){
            setFetchFields("excludeFields","articleContent");
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

        public QueryBuilder fuzzyContactsPhome (List<String> fuzzyContactsPhome){
            this.fuzzyContactsPhome = fuzzyContactsPhome;
            return this;
        }

        public QueryBuilder fuzzyContactsPhome (String ... fuzzyContactsPhome){
            this.fuzzyContactsPhome = solveNullList(fuzzyContactsPhome);
            return this;
        }

        public QueryBuilder rightFuzzyContactsPhome (List<String> rightFuzzyContactsPhome){
            this.rightFuzzyContactsPhome = rightFuzzyContactsPhome;
            return this;
        }

        public QueryBuilder rightFuzzyContactsPhome (String ... rightFuzzyContactsPhome){
            this.rightFuzzyContactsPhome = solveNullList(rightFuzzyContactsPhome);
            return this;
        }

        public QueryBuilder contactsPhome(String contactsPhome){
            setContactsPhome(contactsPhome);
            return this;
        }

        public QueryBuilder contactsPhomeList(String ... contactsPhome){
            this.contactsPhomeList = solveNullList(contactsPhome);
            return this;
        }

        public QueryBuilder contactsPhomeList(List<String> contactsPhome){
            this.contactsPhomeList = contactsPhome;
            return this;
        }

        public QueryBuilder fetchContactsPhome(){
            setFetchFields("fetchFields","contactsPhome");
            return this;
        }

        public QueryBuilder excludeContactsPhome(){
            setFetchFields("excludeFields","contactsPhome");
            return this;
        }

        public QueryBuilder fuzzyContactsWechat (List<String> fuzzyContactsWechat){
            this.fuzzyContactsWechat = fuzzyContactsWechat;
            return this;
        }

        public QueryBuilder fuzzyContactsWechat (String ... fuzzyContactsWechat){
            this.fuzzyContactsWechat = solveNullList(fuzzyContactsWechat);
            return this;
        }

        public QueryBuilder rightFuzzyContactsWechat (List<String> rightFuzzyContactsWechat){
            this.rightFuzzyContactsWechat = rightFuzzyContactsWechat;
            return this;
        }

        public QueryBuilder rightFuzzyContactsWechat (String ... rightFuzzyContactsWechat){
            this.rightFuzzyContactsWechat = solveNullList(rightFuzzyContactsWechat);
            return this;
        }

        public QueryBuilder contactsWechat(String contactsWechat){
            setContactsWechat(contactsWechat);
            return this;
        }

        public QueryBuilder contactsWechatList(String ... contactsWechat){
            this.contactsWechatList = solveNullList(contactsWechat);
            return this;
        }

        public QueryBuilder contactsWechatList(List<String> contactsWechat){
            this.contactsWechatList = contactsWechat;
            return this;
        }

        public QueryBuilder fetchContactsWechat(){
            setFetchFields("fetchFields","contactsWechat");
            return this;
        }

        public QueryBuilder excludeContactsWechat(){
            setFetchFields("excludeFields","contactsWechat");
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

        public PlusArticle build(){return this;}
    }


    public static class ConditionBuilder{
        private List<Long> idList;

        public List<Long> getIdList(){return this.idList;}

        private Long idSt;

        private Long idEd;

        public Long getIdSt(){return this.idSt;}

        public Long getIdEd(){return this.idEd;}

        private List<String> articleTitelList;

        public List<String> getArticleTitelList(){return this.articleTitelList;}


        private List<String> fuzzyArticleTitel;

        public List<String> getFuzzyArticleTitel(){return this.fuzzyArticleTitel;}

        private List<String> rightFuzzyArticleTitel;

        public List<String> getRightFuzzyArticleTitel(){return this.rightFuzzyArticleTitel;}
        private List<Integer> articleTypeList;

        public List<Integer> getArticleTypeList(){return this.articleTypeList;}

        private Integer articleTypeSt;

        private Integer articleTypeEd;

        public Integer getArticleTypeSt(){return this.articleTypeSt;}

        public Integer getArticleTypeEd(){return this.articleTypeEd;}

        private List<String> articleContentList;

        public List<String> getArticleContentList(){return this.articleContentList;}


        private List<String> fuzzyArticleContent;

        public List<String> getFuzzyArticleContent(){return this.fuzzyArticleContent;}

        private List<String> rightFuzzyArticleContent;

        public List<String> getRightFuzzyArticleContent(){return this.rightFuzzyArticleContent;}
        private List<Long> userIdList;

        public List<Long> getUserIdList(){return this.userIdList;}

        private Long userIdSt;

        private Long userIdEd;

        public Long getUserIdSt(){return this.userIdSt;}

        public Long getUserIdEd(){return this.userIdEd;}

        private List<String> contactsPhomeList;

        public List<String> getContactsPhomeList(){return this.contactsPhomeList;}


        private List<String> fuzzyContactsPhome;

        public List<String> getFuzzyContactsPhome(){return this.fuzzyContactsPhome;}

        private List<String> rightFuzzyContactsPhome;

        public List<String> getRightFuzzyContactsPhome(){return this.rightFuzzyContactsPhome;}
        private List<String> contactsWechatList;

        public List<String> getContactsWechatList(){return this.contactsWechatList;}


        private List<String> fuzzyContactsWechat;

        public List<String> getFuzzyContactsWechat(){return this.fuzzyContactsWechat;}

        private List<String> rightFuzzyContactsWechat;

        public List<String> getRightFuzzyContactsWechat(){return this.rightFuzzyContactsWechat;}
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

        public ConditionBuilder fuzzyArticleTitel (List<String> fuzzyArticleTitel){
            this.fuzzyArticleTitel = fuzzyArticleTitel;
            return this;
        }

        public ConditionBuilder fuzzyArticleTitel (String ... fuzzyArticleTitel){
            this.fuzzyArticleTitel = solveNullList(fuzzyArticleTitel);
            return this;
        }

        public ConditionBuilder rightFuzzyArticleTitel (List<String> rightFuzzyArticleTitel){
            this.rightFuzzyArticleTitel = rightFuzzyArticleTitel;
            return this;
        }

        public ConditionBuilder rightFuzzyArticleTitel (String ... rightFuzzyArticleTitel){
            this.rightFuzzyArticleTitel = solveNullList(rightFuzzyArticleTitel);
            return this;
        }

        public ConditionBuilder articleTitelList(String ... articleTitel){
            this.articleTitelList = solveNullList(articleTitel);
            return this;
        }

        public ConditionBuilder articleTitelList(List<String> articleTitel){
            this.articleTitelList = articleTitel;
            return this;
        }

        public ConditionBuilder articleTypeBetWeen(Integer articleTypeSt,Integer articleTypeEd){
            this.articleTypeSt = articleTypeSt;
            this.articleTypeEd = articleTypeEd;
            return this;
        }

        public ConditionBuilder articleTypeGreaterEqThan(Integer articleTypeSt){
            this.articleTypeSt = articleTypeSt;
            return this;
        }
        public ConditionBuilder articleTypeLessEqThan(Integer articleTypeEd){
            this.articleTypeEd = articleTypeEd;
            return this;
        }


        public ConditionBuilder articleTypeList(Integer ... articleType){
            this.articleTypeList = solveNullList(articleType);
            return this;
        }

        public ConditionBuilder articleTypeList(List<Integer> articleType){
            this.articleTypeList = articleType;
            return this;
        }

        public ConditionBuilder fuzzyArticleContent (List<String> fuzzyArticleContent){
            this.fuzzyArticleContent = fuzzyArticleContent;
            return this;
        }

        public ConditionBuilder fuzzyArticleContent (String ... fuzzyArticleContent){
            this.fuzzyArticleContent = solveNullList(fuzzyArticleContent);
            return this;
        }

        public ConditionBuilder rightFuzzyArticleContent (List<String> rightFuzzyArticleContent){
            this.rightFuzzyArticleContent = rightFuzzyArticleContent;
            return this;
        }

        public ConditionBuilder rightFuzzyArticleContent (String ... rightFuzzyArticleContent){
            this.rightFuzzyArticleContent = solveNullList(rightFuzzyArticleContent);
            return this;
        }

        public ConditionBuilder articleContentList(String ... articleContent){
            this.articleContentList = solveNullList(articleContent);
            return this;
        }

        public ConditionBuilder articleContentList(List<String> articleContent){
            this.articleContentList = articleContent;
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

        public ConditionBuilder fuzzyContactsPhome (List<String> fuzzyContactsPhome){
            this.fuzzyContactsPhome = fuzzyContactsPhome;
            return this;
        }

        public ConditionBuilder fuzzyContactsPhome (String ... fuzzyContactsPhome){
            this.fuzzyContactsPhome = solveNullList(fuzzyContactsPhome);
            return this;
        }

        public ConditionBuilder rightFuzzyContactsPhome (List<String> rightFuzzyContactsPhome){
            this.rightFuzzyContactsPhome = rightFuzzyContactsPhome;
            return this;
        }

        public ConditionBuilder rightFuzzyContactsPhome (String ... rightFuzzyContactsPhome){
            this.rightFuzzyContactsPhome = solveNullList(rightFuzzyContactsPhome);
            return this;
        }

        public ConditionBuilder contactsPhomeList(String ... contactsPhome){
            this.contactsPhomeList = solveNullList(contactsPhome);
            return this;
        }

        public ConditionBuilder contactsPhomeList(List<String> contactsPhome){
            this.contactsPhomeList = contactsPhome;
            return this;
        }

        public ConditionBuilder fuzzyContactsWechat (List<String> fuzzyContactsWechat){
            this.fuzzyContactsWechat = fuzzyContactsWechat;
            return this;
        }

        public ConditionBuilder fuzzyContactsWechat (String ... fuzzyContactsWechat){
            this.fuzzyContactsWechat = solveNullList(fuzzyContactsWechat);
            return this;
        }

        public ConditionBuilder rightFuzzyContactsWechat (List<String> rightFuzzyContactsWechat){
            this.rightFuzzyContactsWechat = rightFuzzyContactsWechat;
            return this;
        }

        public ConditionBuilder rightFuzzyContactsWechat (String ... rightFuzzyContactsWechat){
            this.rightFuzzyContactsWechat = solveNullList(rightFuzzyContactsWechat);
            return this;
        }

        public ConditionBuilder contactsWechatList(String ... contactsWechat){
            this.contactsWechatList = solveNullList(contactsWechat);
            return this;
        }

        public ConditionBuilder contactsWechatList(List<String> contactsWechat){
            this.contactsWechatList = contactsWechat;
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

        private PlusArticle obj;

        public Builder(){
            this.obj = new PlusArticle();
        }

        public Builder id(Long id){
            this.obj.setId(id);
            return this;
        }
        public Builder articleTitel(String articleTitel){
            this.obj.setArticleTitel(articleTitel);
            return this;
        }
        public Builder articleType(Integer articleType){
            this.obj.setArticleType(articleType);
            return this;
        }
        public Builder articleContent(String articleContent){
            this.obj.setArticleContent(articleContent);
            return this;
        }
        public Builder userId(Long userId){
            this.obj.setUserId(userId);
            return this;
        }
        public Builder contactsPhome(String contactsPhome){
            this.obj.setContactsPhome(contactsPhome);
            return this;
        }
        public Builder contactsWechat(String contactsWechat){
            this.obj.setContactsWechat(contactsWechat);
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
        public PlusArticle build(){return obj;}
    }

}
