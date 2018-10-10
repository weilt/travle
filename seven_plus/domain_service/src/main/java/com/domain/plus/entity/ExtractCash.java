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
public class ExtractCash implements Serializable {

    private static final long serialVersionUID = 1535512751274L;


    /**
    * 主键
    * 
    * isNullAble:0,defaultVal:0
    */
    private Long id;

    /**
    * 用户Id
    * isNullAble:1
    */
    private Long userId;

    /**
    * 提现金额
    * isNullAble:1
    */
    private Long money;

    /**
    * 创建时间
    * isNullAble:1
    */
    private Long createTime;


    public void setId(Long id){this.id = id;}

    public Long getId(){return this.id;}

    public void setUserId(Long userId){this.userId = userId;}

    public Long getUserId(){return this.userId;}

    public void setMoney(Long money){this.money = money;}

    public Long getMoney(){return this.money;}

    public void setCreateTime(Long createTime){this.createTime = createTime;}

    public Long getCreateTime(){return this.createTime;}
    @Override
    public String toString() {
        return "ExtractCash{" +
                "id='" + id + '\'' +
                "userId='" + userId + '\'' +
                "money='" + money + '\'' +
                "createTime='" + createTime + '\'' +
            '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private ExtractCash set;

        private ConditionBuilder where;

        public UpdateBuilder set(ExtractCash set){
            this.set = set;
            return this;
        }

        public ExtractCash getSet(){
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

    public static class QueryBuilder extends ExtractCash{
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

        private List<Long> moneyList;

        public List<Long> getMoneyList(){return this.moneyList;}

        private Long moneySt;

        private Long moneyEd;

        public Long getMoneySt(){return this.moneySt;}

        public Long getMoneyEd(){return this.moneyEd;}

        private List<Long> createTimeList;

        public List<Long> getCreateTimeList(){return this.createTimeList;}

        private Long createTimeSt;

        private Long createTimeEd;

        public Long getCreateTimeSt(){return this.createTimeSt;}

        public Long getCreateTimeEd(){return this.createTimeEd;}

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

        public QueryBuilder moneyBetWeen(Long moneySt,Long moneyEd){
            this.moneySt = moneySt;
            this.moneyEd = moneyEd;
            return this;
        }

        public QueryBuilder moneyGreaterEqThan(Long moneySt){
            this.moneySt = moneySt;
            return this;
        }
        public QueryBuilder moneyLessEqThan(Long moneyEd){
            this.moneyEd = moneyEd;
            return this;
        }


        public QueryBuilder money(Long money){
            setMoney(money);
            return this;
        }

        public QueryBuilder moneyList(Long ... money){
            this.moneyList = solveNullList(money);
            return this;
        }

        public QueryBuilder moneyList(List<Long> money){
            this.moneyList = money;
            return this;
        }

        public QueryBuilder fetchMoney(){
            setFetchFields("fetchFields","money");
            return this;
        }

        public QueryBuilder excludeMoney(){
            setFetchFields("excludeFields","money");
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

        public ExtractCash build(){return this;}
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

        private List<Long> moneyList;

        public List<Long> getMoneyList(){return this.moneyList;}

        private Long moneySt;

        private Long moneyEd;

        public Long getMoneySt(){return this.moneySt;}

        public Long getMoneyEd(){return this.moneyEd;}

        private List<Long> createTimeList;

        public List<Long> getCreateTimeList(){return this.createTimeList;}

        private Long createTimeSt;

        private Long createTimeEd;

        public Long getCreateTimeSt(){return this.createTimeSt;}

        public Long getCreateTimeEd(){return this.createTimeEd;}


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

        public ConditionBuilder moneyBetWeen(Long moneySt,Long moneyEd){
            this.moneySt = moneySt;
            this.moneyEd = moneyEd;
            return this;
        }

        public ConditionBuilder moneyGreaterEqThan(Long moneySt){
            this.moneySt = moneySt;
            return this;
        }
        public ConditionBuilder moneyLessEqThan(Long moneyEd){
            this.moneyEd = moneyEd;
            return this;
        }


        public ConditionBuilder moneyList(Long ... money){
            this.moneyList = solveNullList(money);
            return this;
        }

        public ConditionBuilder moneyList(List<Long> money){
            this.moneyList = money;
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

        private ExtractCash obj;

        public Builder(){
            this.obj = new ExtractCash();
        }

        public Builder id(Long id){
            this.obj.setId(id);
            return this;
        }
        public Builder userId(Long userId){
            this.obj.setUserId(userId);
            return this;
        }
        public Builder money(Long money){
            this.obj.setMoney(money);
            return this;
        }
        public Builder createTime(Long createTime){
            this.obj.setCreateTime(createTime);
            return this;
        }
        public ExtractCash build(){return obj;}
    }

}
