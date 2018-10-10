package com.domain.plus.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
*
*  @author zhoudu
*/
@ApiModel(value="车辆信息")
public class PlusCar implements Serializable {

    private static final long serialVersionUID = 1533693419274L;


    /**
     * 主键
     * id自增
     * isNullAble:0
     */
    @ApiModelProperty(value="车辆信息id")
    private Long id;

    /**
     * 会员ID
     * isNullAble:1
     */
    @ApiModelProperty(value="userId")
    private Long userId;

    /**
     * 订单Id
     * isNullAble:1
     */
    @ApiModelProperty(value="orderId")
    private Long orderId;

    /**
     * 车辆正面图
     * isNullAble:1
     */
    @ApiModelProperty(value="车辆正面图")
    private String faceUrl;

    /**
     * 左前面图片

     * isNullAble:1
     */
    @ApiModelProperty(value="左前面图片")
    private String leftAnteriorUrl;

    /**
     * 右前面图
     * isNullAble:1
     */
    @ApiModelProperty(value="右前面图")
    private String rightAnteriorUrl;

    /**
     * 左侧图片
     * isNullAble:1
     */
    @ApiModelProperty(value="左侧图片")
    private String leftUrl;

    /**
     * 右侧图片
     * isNullAble:1
     */
    @ApiModelProperty(value="右侧图片")
    private String rightUrl;

    /**
     * 正后图片
     * isNullAble:1
     */
    @ApiModelProperty(value="正后图片")
    private String backUrl;

    /**
     * 车牌号
     * isNullAble:1
     */
    @ApiModelProperty(value="车牌号")
    private String carNo;

    /**
     * 车辆品牌
     * isNullAble:1
     */
    @ApiModelProperty(value="车辆品牌")
    private String carBrand;

    /**
     * 车辆性质 默认0 非运营 1：运营
     * isNullAble:1,defaultVal:0
     */
    @ApiModelProperty(value="车辆性质 默认0 非运营 1：运营")
    private Integer carNature;

    /**
     * 驾龄 默认0：三年以下 1：三年到五年以下 2：五年以上
     * isNullAble:1,defaultVal:0
     */
    @ApiModelProperty(value="驾龄 默认0：三年以下 1：三年到五年以下 2：五年以上")
    private Integer drivingAge;

    /**
     * 车辆类型 默认0：五座以下 1：五座到七座
     * isNullAble:1,defaultVal:0
     */
    @ApiModelProperty(value="车辆类型 默认0：五座以下 1：五座到七座")
    private Integer carType;

    /**
     * 估价
     */
    private String evaluation;

    /**
     * 创建时间
     * isNullAble:1,defaultVal:0
     */
    @ApiModelProperty(value="创建时间")
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

    public void setOrderId(Long orderId){this.orderId = orderId;}

    public Long getOrderId(){return this.orderId;}

    public void setFaceUrl(String faceUrl){this.faceUrl = faceUrl;}

    public String getFaceUrl(){return this.faceUrl;}

    public void setLeftAnteriorUrl(String leftAnteriorUrl){this.leftAnteriorUrl = leftAnteriorUrl;}

    public String getLeftAnteriorUrl(){return this.leftAnteriorUrl;}

    public void setRightAnteriorUrl(String rightAnteriorUrl){this.rightAnteriorUrl = rightAnteriorUrl;}

    public String getRightAnteriorUrl(){return this.rightAnteriorUrl;}

    public void setLeftUrl(String leftUrl){this.leftUrl = leftUrl;}

    public String getLeftUrl(){return this.leftUrl;}

    public void setRightUrl(String rightUrl){this.rightUrl = rightUrl;}

    public String getRightUrl(){return this.rightUrl;}

    public void setBackUrl(String backUrl){this.backUrl = backUrl;}

    public String getBackUrl(){return this.backUrl;}

    public void setCarNo(String carNo){this.carNo = carNo;}

    public String getCarNo(){return this.carNo;}

    public void setCarBrand(String carBrand){this.carBrand = carBrand;}

    public String getCarBrand(){return this.carBrand;}

    public void setCarNature(Integer carNature){this.carNature = carNature;}

    public Integer getCarNature(){return this.carNature;}

    public void setDrivingAge(Integer drivingAge){this.drivingAge = drivingAge;}

    public Integer getDrivingAge(){return this.drivingAge;}

    public void setCarType(Integer carType){this.carType = carType;}

    public Integer getCarType(){return this.carType;}

    public void setCreateTime(Long createTime){this.createTime = createTime;}

    public Long getCreateTime(){return this.createTime;}

    public void setUpdateTime(Long updateTime){this.updateTime = updateTime;}

    public Long getUpdateTime(){return this.updateTime;}

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        return "PlusCar{" +
                "id='" + id + '\'' +
                "userId='" + userId + '\'' +
                "orderId='" + orderId + '\'' +
                "faceUrl='" + faceUrl + '\'' +
                "leftAnteriorUrl='" + leftAnteriorUrl + '\'' +
                "rightAnteriorUrl='" + rightAnteriorUrl + '\'' +
                "leftUrl='" + leftUrl + '\'' +
                "rightUrl='" + rightUrl + '\'' +
                "backUrl='" + backUrl + '\'' +
                "carNo='" + carNo + '\'' +
                "carBrand='" + carBrand + '\'' +
                "carNature='" + carNature + '\'' +
                "drivingAge='" + drivingAge + '\'' +
                "carType='" + carType + '\'' +
                "createTime='" + createTime + '\'' +
                "updateTime='" + updateTime + '\'' +
                '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private PlusCar set;

        private ConditionBuilder where;

        public UpdateBuilder set(PlusCar set){
            this.set = set;
            return this;
        }

        public PlusCar getSet(){
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

    public static class QueryBuilder extends PlusCar{
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

        private List<Long> orderIdList;

        public List<Long> getOrderIdList(){return this.orderIdList;}

        private Long orderIdSt;

        private Long orderIdEd;

        public Long getOrderIdSt(){return this.orderIdSt;}

        public Long getOrderIdEd(){return this.orderIdEd;}

        private List<String> faceUrlList;

        public List<String> getFaceUrlList(){return this.faceUrlList;}


        private List<String> fuzzyFaceUrl;

        public List<String> getFuzzyFaceUrl(){return this.fuzzyFaceUrl;}

        private List<String> rightFuzzyFaceUrl;

        public List<String> getRightFuzzyFaceUrl(){return this.rightFuzzyFaceUrl;}
        private List<String> leftAnteriorUrlList;

        public List<String> getLeftAnteriorUrlList(){return this.leftAnteriorUrlList;}


        private List<String> fuzzyLeftAnteriorUrl;

        public List<String> getFuzzyLeftAnteriorUrl(){return this.fuzzyLeftAnteriorUrl;}

        private List<String> rightFuzzyLeftAnteriorUrl;

        public List<String> getRightFuzzyLeftAnteriorUrl(){return this.rightFuzzyLeftAnteriorUrl;}
        private List<String> rightAnteriorUrlList;

        public List<String> getRightAnteriorUrlList(){return this.rightAnteriorUrlList;}


        private List<String> fuzzyRightAnteriorUrl;

        public List<String> getFuzzyRightAnteriorUrl(){return this.fuzzyRightAnteriorUrl;}

        private List<String> rightFuzzyRightAnteriorUrl;

        public List<String> getRightFuzzyRightAnteriorUrl(){return this.rightFuzzyRightAnteriorUrl;}
        private List<String> leftUrlList;

        public List<String> getLeftUrlList(){return this.leftUrlList;}


        private List<String> fuzzyLeftUrl;

        public List<String> getFuzzyLeftUrl(){return this.fuzzyLeftUrl;}

        private List<String> rightFuzzyLeftUrl;

        public List<String> getRightFuzzyLeftUrl(){return this.rightFuzzyLeftUrl;}
        private List<String> rightUrlList;

        public List<String> getRightUrlList(){return this.rightUrlList;}


        private List<String> fuzzyRightUrl;

        public List<String> getFuzzyRightUrl(){return this.fuzzyRightUrl;}

        private List<String> rightFuzzyRightUrl;

        public List<String> getRightFuzzyRightUrl(){return this.rightFuzzyRightUrl;}
        private List<String> backUrlList;

        public List<String> getBackUrlList(){return this.backUrlList;}


        private List<String> fuzzyBackUrl;

        public List<String> getFuzzyBackUrl(){return this.fuzzyBackUrl;}

        private List<String> rightFuzzyBackUrl;

        public List<String> getRightFuzzyBackUrl(){return this.rightFuzzyBackUrl;}
        private List<String> carNoList;

        public List<String> getCarNoList(){return this.carNoList;}


        private List<String> fuzzyCarNo;

        public List<String> getFuzzyCarNo(){return this.fuzzyCarNo;}

        private List<String> rightFuzzyCarNo;

        public List<String> getRightFuzzyCarNo(){return this.rightFuzzyCarNo;}
        private List<String> carBrandList;

        public List<String> getCarBrandList(){return this.carBrandList;}


        private List<String> fuzzyCarBrand;

        public List<String> getFuzzyCarBrand(){return this.fuzzyCarBrand;}

        private List<String> rightFuzzyCarBrand;

        public List<String> getRightFuzzyCarBrand(){return this.rightFuzzyCarBrand;}
        private List<Integer> carNatureList;

        public List<Integer> getCarNatureList(){return this.carNatureList;}

        private Integer carNatureSt;

        private Integer carNatureEd;

        public Integer getCarNatureSt(){return this.carNatureSt;}

        public Integer getCarNatureEd(){return this.carNatureEd;}

        private List<Integer> drivingAgeList;

        public List<Integer> getDrivingAgeList(){return this.drivingAgeList;}

        private Integer drivingAgeSt;

        private Integer drivingAgeEd;

        public Integer getDrivingAgeSt(){return this.drivingAgeSt;}

        public Integer getDrivingAgeEd(){return this.drivingAgeEd;}

        private List<Integer> carTypeList;

        public List<Integer> getCarTypeList(){return this.carTypeList;}

        private Integer carTypeSt;

        private Integer carTypeEd;

        public Integer getCarTypeSt(){return this.carTypeSt;}

        public Integer getCarTypeEd(){return this.carTypeEd;}

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

        public QueryBuilder fuzzyFaceUrl (List<String> fuzzyFaceUrl){
            this.fuzzyFaceUrl = fuzzyFaceUrl;
            return this;
        }

        public QueryBuilder fuzzyFaceUrl (String ... fuzzyFaceUrl){
            this.fuzzyFaceUrl = solveNullList(fuzzyFaceUrl);
            return this;
        }

        public QueryBuilder rightFuzzyFaceUrl (List<String> rightFuzzyFaceUrl){
            this.rightFuzzyFaceUrl = rightFuzzyFaceUrl;
            return this;
        }

        public QueryBuilder rightFuzzyFaceUrl (String ... rightFuzzyFaceUrl){
            this.rightFuzzyFaceUrl = solveNullList(rightFuzzyFaceUrl);
            return this;
        }

        public QueryBuilder faceUrl(String faceUrl){
            setFaceUrl(faceUrl);
            return this;
        }

        public QueryBuilder faceUrlList(String ... faceUrl){
            this.faceUrlList = solveNullList(faceUrl);
            return this;
        }

        public QueryBuilder faceUrlList(List<String> faceUrl){
            this.faceUrlList = faceUrl;
            return this;
        }

        public QueryBuilder fetchFaceUrl(){
            setFetchFields("fetchFields","faceUrl");
            return this;
        }

        public QueryBuilder excludeFaceUrl(){
            setFetchFields("excludeFields","faceUrl");
            return this;
        }

        public QueryBuilder fuzzyLeftAnteriorUrl (List<String> fuzzyLeftAnteriorUrl){
            this.fuzzyLeftAnteriorUrl = fuzzyLeftAnteriorUrl;
            return this;
        }

        public QueryBuilder fuzzyLeftAnteriorUrl (String ... fuzzyLeftAnteriorUrl){
            this.fuzzyLeftAnteriorUrl = solveNullList(fuzzyLeftAnteriorUrl);
            return this;
        }

        public QueryBuilder rightFuzzyLeftAnteriorUrl (List<String> rightFuzzyLeftAnteriorUrl){
            this.rightFuzzyLeftAnteriorUrl = rightFuzzyLeftAnteriorUrl;
            return this;
        }

        public QueryBuilder rightFuzzyLeftAnteriorUrl (String ... rightFuzzyLeftAnteriorUrl){
            this.rightFuzzyLeftAnteriorUrl = solveNullList(rightFuzzyLeftAnteriorUrl);
            return this;
        }

        public QueryBuilder leftAnteriorUrl(String leftAnteriorUrl){
            setLeftAnteriorUrl(leftAnteriorUrl);
            return this;
        }

        public QueryBuilder leftAnteriorUrlList(String ... leftAnteriorUrl){
            this.leftAnteriorUrlList = solveNullList(leftAnteriorUrl);
            return this;
        }

        public QueryBuilder leftAnteriorUrlList(List<String> leftAnteriorUrl){
            this.leftAnteriorUrlList = leftAnteriorUrl;
            return this;
        }

        public QueryBuilder fetchLeftAnteriorUrl(){
            setFetchFields("fetchFields","leftAnteriorUrl");
            return this;
        }

        public QueryBuilder excludeLeftAnteriorUrl(){
            setFetchFields("excludeFields","leftAnteriorUrl");
            return this;
        }

        public QueryBuilder fuzzyRightAnteriorUrl (List<String> fuzzyRightAnteriorUrl){
            this.fuzzyRightAnteriorUrl = fuzzyRightAnteriorUrl;
            return this;
        }

        public QueryBuilder fuzzyRightAnteriorUrl (String ... fuzzyRightAnteriorUrl){
            this.fuzzyRightAnteriorUrl = solveNullList(fuzzyRightAnteriorUrl);
            return this;
        }

        public QueryBuilder rightFuzzyRightAnteriorUrl (List<String> rightFuzzyRightAnteriorUrl){
            this.rightFuzzyRightAnteriorUrl = rightFuzzyRightAnteriorUrl;
            return this;
        }

        public QueryBuilder rightFuzzyRightAnteriorUrl (String ... rightFuzzyRightAnteriorUrl){
            this.rightFuzzyRightAnteriorUrl = solveNullList(rightFuzzyRightAnteriorUrl);
            return this;
        }

        public QueryBuilder rightAnteriorUrl(String rightAnteriorUrl){
            setRightAnteriorUrl(rightAnteriorUrl);
            return this;
        }

        public QueryBuilder rightAnteriorUrlList(String ... rightAnteriorUrl){
            this.rightAnteriorUrlList = solveNullList(rightAnteriorUrl);
            return this;
        }

        public QueryBuilder rightAnteriorUrlList(List<String> rightAnteriorUrl){
            this.rightAnteriorUrlList = rightAnteriorUrl;
            return this;
        }

        public QueryBuilder fetchRightAnteriorUrl(){
            setFetchFields("fetchFields","rightAnteriorUrl");
            return this;
        }

        public QueryBuilder excludeRightAnteriorUrl(){
            setFetchFields("excludeFields","rightAnteriorUrl");
            return this;
        }

        public QueryBuilder fuzzyLeftUrl (List<String> fuzzyLeftUrl){
            this.fuzzyLeftUrl = fuzzyLeftUrl;
            return this;
        }

        public QueryBuilder fuzzyLeftUrl (String ... fuzzyLeftUrl){
            this.fuzzyLeftUrl = solveNullList(fuzzyLeftUrl);
            return this;
        }

        public QueryBuilder rightFuzzyLeftUrl (List<String> rightFuzzyLeftUrl){
            this.rightFuzzyLeftUrl = rightFuzzyLeftUrl;
            return this;
        }

        public QueryBuilder rightFuzzyLeftUrl (String ... rightFuzzyLeftUrl){
            this.rightFuzzyLeftUrl = solveNullList(rightFuzzyLeftUrl);
            return this;
        }

        public QueryBuilder leftUrl(String leftUrl){
            setLeftUrl(leftUrl);
            return this;
        }

        public QueryBuilder leftUrlList(String ... leftUrl){
            this.leftUrlList = solveNullList(leftUrl);
            return this;
        }

        public QueryBuilder leftUrlList(List<String> leftUrl){
            this.leftUrlList = leftUrl;
            return this;
        }

        public QueryBuilder fetchLeftUrl(){
            setFetchFields("fetchFields","leftUrl");
            return this;
        }

        public QueryBuilder excludeLeftUrl(){
            setFetchFields("excludeFields","leftUrl");
            return this;
        }

        public QueryBuilder fuzzyRightUrl (List<String> fuzzyRightUrl){
            this.fuzzyRightUrl = fuzzyRightUrl;
            return this;
        }

        public QueryBuilder fuzzyRightUrl (String ... fuzzyRightUrl){
            this.fuzzyRightUrl = solveNullList(fuzzyRightUrl);
            return this;
        }

        public QueryBuilder rightFuzzyRightUrl (List<String> rightFuzzyRightUrl){
            this.rightFuzzyRightUrl = rightFuzzyRightUrl;
            return this;
        }

        public QueryBuilder rightFuzzyRightUrl (String ... rightFuzzyRightUrl){
            this.rightFuzzyRightUrl = solveNullList(rightFuzzyRightUrl);
            return this;
        }

        public QueryBuilder rightUrl(String rightUrl){
            setRightUrl(rightUrl);
            return this;
        }

        public QueryBuilder rightUrlList(String ... rightUrl){
            this.rightUrlList = solveNullList(rightUrl);
            return this;
        }

        public QueryBuilder rightUrlList(List<String> rightUrl){
            this.rightUrlList = rightUrl;
            return this;
        }

        public QueryBuilder fetchRightUrl(){
            setFetchFields("fetchFields","rightUrl");
            return this;
        }

        public QueryBuilder excludeRightUrl(){
            setFetchFields("excludeFields","rightUrl");
            return this;
        }

        public QueryBuilder fuzzyBackUrl (List<String> fuzzyBackUrl){
            this.fuzzyBackUrl = fuzzyBackUrl;
            return this;
        }

        public QueryBuilder fuzzyBackUrl (String ... fuzzyBackUrl){
            this.fuzzyBackUrl = solveNullList(fuzzyBackUrl);
            return this;
        }

        public QueryBuilder rightFuzzyBackUrl (List<String> rightFuzzyBackUrl){
            this.rightFuzzyBackUrl = rightFuzzyBackUrl;
            return this;
        }

        public QueryBuilder rightFuzzyBackUrl (String ... rightFuzzyBackUrl){
            this.rightFuzzyBackUrl = solveNullList(rightFuzzyBackUrl);
            return this;
        }

        public QueryBuilder backUrl(String backUrl){
            setBackUrl(backUrl);
            return this;
        }

        public QueryBuilder backUrlList(String ... backUrl){
            this.backUrlList = solveNullList(backUrl);
            return this;
        }

        public QueryBuilder backUrlList(List<String> backUrl){
            this.backUrlList = backUrl;
            return this;
        }

        public QueryBuilder fetchBackUrl(){
            setFetchFields("fetchFields","backUrl");
            return this;
        }

        public QueryBuilder excludeBackUrl(){
            setFetchFields("excludeFields","backUrl");
            return this;
        }

        public QueryBuilder fuzzyCarNo (List<String> fuzzyCarNo){
            this.fuzzyCarNo = fuzzyCarNo;
            return this;
        }

        public QueryBuilder fuzzyCarNo (String ... fuzzyCarNo){
            this.fuzzyCarNo = solveNullList(fuzzyCarNo);
            return this;
        }

        public QueryBuilder rightFuzzyCarNo (List<String> rightFuzzyCarNo){
            this.rightFuzzyCarNo = rightFuzzyCarNo;
            return this;
        }

        public QueryBuilder rightFuzzyCarNo (String ... rightFuzzyCarNo){
            this.rightFuzzyCarNo = solveNullList(rightFuzzyCarNo);
            return this;
        }

        public QueryBuilder carNo(String carNo){
            setCarNo(carNo);
            return this;
        }

        public QueryBuilder carNoList(String ... carNo){
            this.carNoList = solveNullList(carNo);
            return this;
        }

        public QueryBuilder carNoList(List<String> carNo){
            this.carNoList = carNo;
            return this;
        }

        public QueryBuilder fetchCarNo(){
            setFetchFields("fetchFields","carNo");
            return this;
        }

        public QueryBuilder excludeCarNo(){
            setFetchFields("excludeFields","carNo");
            return this;
        }

        public QueryBuilder fuzzyCarBrand (List<String> fuzzyCarBrand){
            this.fuzzyCarBrand = fuzzyCarBrand;
            return this;
        }

        public QueryBuilder fuzzyCarBrand (String ... fuzzyCarBrand){
            this.fuzzyCarBrand = solveNullList(fuzzyCarBrand);
            return this;
        }

        public QueryBuilder rightFuzzyCarBrand (List<String> rightFuzzyCarBrand){
            this.rightFuzzyCarBrand = rightFuzzyCarBrand;
            return this;
        }

        public QueryBuilder rightFuzzyCarBrand (String ... rightFuzzyCarBrand){
            this.rightFuzzyCarBrand = solveNullList(rightFuzzyCarBrand);
            return this;
        }

        public QueryBuilder carBrand(String carBrand){
            setCarBrand(carBrand);
            return this;
        }

        public QueryBuilder carBrandList(String ... carBrand){
            this.carBrandList = solveNullList(carBrand);
            return this;
        }

        public QueryBuilder carBrandList(List<String> carBrand){
            this.carBrandList = carBrand;
            return this;
        }

        public QueryBuilder fetchCarBrand(){
            setFetchFields("fetchFields","carBrand");
            return this;
        }

        public QueryBuilder excludeCarBrand(){
            setFetchFields("excludeFields","carBrand");
            return this;
        }

        public QueryBuilder carNatureBetWeen(Integer carNatureSt,Integer carNatureEd){
            this.carNatureSt = carNatureSt;
            this.carNatureEd = carNatureEd;
            return this;
        }

        public QueryBuilder carNatureGreaterEqThan(Integer carNatureSt){
            this.carNatureSt = carNatureSt;
            return this;
        }
        public QueryBuilder carNatureLessEqThan(Integer carNatureEd){
            this.carNatureEd = carNatureEd;
            return this;
        }


        public QueryBuilder carNature(Integer carNature){
            setCarNature(carNature);
            return this;
        }

        public QueryBuilder carNatureList(Integer ... carNature){
            this.carNatureList = solveNullList(carNature);
            return this;
        }

        public QueryBuilder carNatureList(List<Integer> carNature){
            this.carNatureList = carNature;
            return this;
        }

        public QueryBuilder fetchCarNature(){
            setFetchFields("fetchFields","carNature");
            return this;
        }

        public QueryBuilder excludeCarNature(){
            setFetchFields("excludeFields","carNature");
            return this;
        }

        public QueryBuilder drivingAgeBetWeen(Integer drivingAgeSt,Integer drivingAgeEd){
            this.drivingAgeSt = drivingAgeSt;
            this.drivingAgeEd = drivingAgeEd;
            return this;
        }

        public QueryBuilder drivingAgeGreaterEqThan(Integer drivingAgeSt){
            this.drivingAgeSt = drivingAgeSt;
            return this;
        }
        public QueryBuilder drivingAgeLessEqThan(Integer drivingAgeEd){
            this.drivingAgeEd = drivingAgeEd;
            return this;
        }


        public QueryBuilder drivingAge(Integer drivingAge){
            setDrivingAge(drivingAge);
            return this;
        }

        public QueryBuilder drivingAgeList(Integer ... drivingAge){
            this.drivingAgeList = solveNullList(drivingAge);
            return this;
        }

        public QueryBuilder drivingAgeList(List<Integer> drivingAge){
            this.drivingAgeList = drivingAge;
            return this;
        }

        public QueryBuilder fetchDrivingAge(){
            setFetchFields("fetchFields","drivingAge");
            return this;
        }

        public QueryBuilder excludeDrivingAge(){
            setFetchFields("excludeFields","drivingAge");
            return this;
        }

        public QueryBuilder carTypeBetWeen(Integer carTypeSt,Integer carTypeEd){
            this.carTypeSt = carTypeSt;
            this.carTypeEd = carTypeEd;
            return this;
        }

        public QueryBuilder carTypeGreaterEqThan(Integer carTypeSt){
            this.carTypeSt = carTypeSt;
            return this;
        }
        public QueryBuilder carTypeLessEqThan(Integer carTypeEd){
            this.carTypeEd = carTypeEd;
            return this;
        }


        public QueryBuilder carType(Integer carType){
            setCarType(carType);
            return this;
        }

        public QueryBuilder carTypeList(Integer ... carType){
            this.carTypeList = solveNullList(carType);
            return this;
        }

        public QueryBuilder carTypeList(List<Integer> carType){
            this.carTypeList = carType;
            return this;
        }

        public QueryBuilder fetchCarType(){
            setFetchFields("fetchFields","carType");
            return this;
        }

        public QueryBuilder excludeCarType(){
            setFetchFields("excludeFields","carType");
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

        public PlusCar build(){return this;}
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

        private List<Long> orderIdList;

        public List<Long> getOrderIdList(){return this.orderIdList;}

        private Long orderIdSt;

        private Long orderIdEd;

        public Long getOrderIdSt(){return this.orderIdSt;}

        public Long getOrderIdEd(){return this.orderIdEd;}

        private List<String> faceUrlList;

        public List<String> getFaceUrlList(){return this.faceUrlList;}


        private List<String> fuzzyFaceUrl;

        public List<String> getFuzzyFaceUrl(){return this.fuzzyFaceUrl;}

        private List<String> rightFuzzyFaceUrl;

        public List<String> getRightFuzzyFaceUrl(){return this.rightFuzzyFaceUrl;}
        private List<String> leftAnteriorUrlList;

        public List<String> getLeftAnteriorUrlList(){return this.leftAnteriorUrlList;}


        private List<String> fuzzyLeftAnteriorUrl;

        public List<String> getFuzzyLeftAnteriorUrl(){return this.fuzzyLeftAnteriorUrl;}

        private List<String> rightFuzzyLeftAnteriorUrl;

        public List<String> getRightFuzzyLeftAnteriorUrl(){return this.rightFuzzyLeftAnteriorUrl;}
        private List<String> rightAnteriorUrlList;

        public List<String> getRightAnteriorUrlList(){return this.rightAnteriorUrlList;}


        private List<String> fuzzyRightAnteriorUrl;

        public List<String> getFuzzyRightAnteriorUrl(){return this.fuzzyRightAnteriorUrl;}

        private List<String> rightFuzzyRightAnteriorUrl;

        public List<String> getRightFuzzyRightAnteriorUrl(){return this.rightFuzzyRightAnteriorUrl;}
        private List<String> leftUrlList;

        public List<String> getLeftUrlList(){return this.leftUrlList;}


        private List<String> fuzzyLeftUrl;

        public List<String> getFuzzyLeftUrl(){return this.fuzzyLeftUrl;}

        private List<String> rightFuzzyLeftUrl;

        public List<String> getRightFuzzyLeftUrl(){return this.rightFuzzyLeftUrl;}
        private List<String> rightUrlList;

        public List<String> getRightUrlList(){return this.rightUrlList;}


        private List<String> fuzzyRightUrl;

        public List<String> getFuzzyRightUrl(){return this.fuzzyRightUrl;}

        private List<String> rightFuzzyRightUrl;

        public List<String> getRightFuzzyRightUrl(){return this.rightFuzzyRightUrl;}
        private List<String> backUrlList;

        public List<String> getBackUrlList(){return this.backUrlList;}


        private List<String> fuzzyBackUrl;

        public List<String> getFuzzyBackUrl(){return this.fuzzyBackUrl;}

        private List<String> rightFuzzyBackUrl;

        public List<String> getRightFuzzyBackUrl(){return this.rightFuzzyBackUrl;}
        private List<String> carNoList;

        public List<String> getCarNoList(){return this.carNoList;}


        private List<String> fuzzyCarNo;

        public List<String> getFuzzyCarNo(){return this.fuzzyCarNo;}

        private List<String> rightFuzzyCarNo;

        public List<String> getRightFuzzyCarNo(){return this.rightFuzzyCarNo;}
        private List<String> carBrandList;

        public List<String> getCarBrandList(){return this.carBrandList;}


        private List<String> fuzzyCarBrand;

        public List<String> getFuzzyCarBrand(){return this.fuzzyCarBrand;}

        private List<String> rightFuzzyCarBrand;

        public List<String> getRightFuzzyCarBrand(){return this.rightFuzzyCarBrand;}
        private List<Integer> carNatureList;

        public List<Integer> getCarNatureList(){return this.carNatureList;}

        private Integer carNatureSt;

        private Integer carNatureEd;

        public Integer getCarNatureSt(){return this.carNatureSt;}

        public Integer getCarNatureEd(){return this.carNatureEd;}

        private List<Integer> drivingAgeList;

        public List<Integer> getDrivingAgeList(){return this.drivingAgeList;}

        private Integer drivingAgeSt;

        private Integer drivingAgeEd;

        public Integer getDrivingAgeSt(){return this.drivingAgeSt;}

        public Integer getDrivingAgeEd(){return this.drivingAgeEd;}

        private List<Integer> carTypeList;

        public List<Integer> getCarTypeList(){return this.carTypeList;}

        private Integer carTypeSt;

        private Integer carTypeEd;

        public Integer getCarTypeSt(){return this.carTypeSt;}

        public Integer getCarTypeEd(){return this.carTypeEd;}

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

        public ConditionBuilder fuzzyFaceUrl (List<String> fuzzyFaceUrl){
            this.fuzzyFaceUrl = fuzzyFaceUrl;
            return this;
        }

        public ConditionBuilder fuzzyFaceUrl (String ... fuzzyFaceUrl){
            this.fuzzyFaceUrl = solveNullList(fuzzyFaceUrl);
            return this;
        }

        public ConditionBuilder rightFuzzyFaceUrl (List<String> rightFuzzyFaceUrl){
            this.rightFuzzyFaceUrl = rightFuzzyFaceUrl;
            return this;
        }

        public ConditionBuilder rightFuzzyFaceUrl (String ... rightFuzzyFaceUrl){
            this.rightFuzzyFaceUrl = solveNullList(rightFuzzyFaceUrl);
            return this;
        }

        public ConditionBuilder faceUrlList(String ... faceUrl){
            this.faceUrlList = solveNullList(faceUrl);
            return this;
        }

        public ConditionBuilder faceUrlList(List<String> faceUrl){
            this.faceUrlList = faceUrl;
            return this;
        }

        public ConditionBuilder fuzzyLeftAnteriorUrl (List<String> fuzzyLeftAnteriorUrl){
            this.fuzzyLeftAnteriorUrl = fuzzyLeftAnteriorUrl;
            return this;
        }

        public ConditionBuilder fuzzyLeftAnteriorUrl (String ... fuzzyLeftAnteriorUrl){
            this.fuzzyLeftAnteriorUrl = solveNullList(fuzzyLeftAnteriorUrl);
            return this;
        }

        public ConditionBuilder rightFuzzyLeftAnteriorUrl (List<String> rightFuzzyLeftAnteriorUrl){
            this.rightFuzzyLeftAnteriorUrl = rightFuzzyLeftAnteriorUrl;
            return this;
        }

        public ConditionBuilder rightFuzzyLeftAnteriorUrl (String ... rightFuzzyLeftAnteriorUrl){
            this.rightFuzzyLeftAnteriorUrl = solveNullList(rightFuzzyLeftAnteriorUrl);
            return this;
        }

        public ConditionBuilder leftAnteriorUrlList(String ... leftAnteriorUrl){
            this.leftAnteriorUrlList = solveNullList(leftAnteriorUrl);
            return this;
        }

        public ConditionBuilder leftAnteriorUrlList(List<String> leftAnteriorUrl){
            this.leftAnteriorUrlList = leftAnteriorUrl;
            return this;
        }

        public ConditionBuilder fuzzyRightAnteriorUrl (List<String> fuzzyRightAnteriorUrl){
            this.fuzzyRightAnteriorUrl = fuzzyRightAnteriorUrl;
            return this;
        }

        public ConditionBuilder fuzzyRightAnteriorUrl (String ... fuzzyRightAnteriorUrl){
            this.fuzzyRightAnteriorUrl = solveNullList(fuzzyRightAnteriorUrl);
            return this;
        }

        public ConditionBuilder rightFuzzyRightAnteriorUrl (List<String> rightFuzzyRightAnteriorUrl){
            this.rightFuzzyRightAnteriorUrl = rightFuzzyRightAnteriorUrl;
            return this;
        }

        public ConditionBuilder rightFuzzyRightAnteriorUrl (String ... rightFuzzyRightAnteriorUrl){
            this.rightFuzzyRightAnteriorUrl = solveNullList(rightFuzzyRightAnteriorUrl);
            return this;
        }

        public ConditionBuilder rightAnteriorUrlList(String ... rightAnteriorUrl){
            this.rightAnteriorUrlList = solveNullList(rightAnteriorUrl);
            return this;
        }

        public ConditionBuilder rightAnteriorUrlList(List<String> rightAnteriorUrl){
            this.rightAnteriorUrlList = rightAnteriorUrl;
            return this;
        }

        public ConditionBuilder fuzzyLeftUrl (List<String> fuzzyLeftUrl){
            this.fuzzyLeftUrl = fuzzyLeftUrl;
            return this;
        }

        public ConditionBuilder fuzzyLeftUrl (String ... fuzzyLeftUrl){
            this.fuzzyLeftUrl = solveNullList(fuzzyLeftUrl);
            return this;
        }

        public ConditionBuilder rightFuzzyLeftUrl (List<String> rightFuzzyLeftUrl){
            this.rightFuzzyLeftUrl = rightFuzzyLeftUrl;
            return this;
        }

        public ConditionBuilder rightFuzzyLeftUrl (String ... rightFuzzyLeftUrl){
            this.rightFuzzyLeftUrl = solveNullList(rightFuzzyLeftUrl);
            return this;
        }

        public ConditionBuilder leftUrlList(String ... leftUrl){
            this.leftUrlList = solveNullList(leftUrl);
            return this;
        }

        public ConditionBuilder leftUrlList(List<String> leftUrl){
            this.leftUrlList = leftUrl;
            return this;
        }

        public ConditionBuilder fuzzyRightUrl (List<String> fuzzyRightUrl){
            this.fuzzyRightUrl = fuzzyRightUrl;
            return this;
        }

        public ConditionBuilder fuzzyRightUrl (String ... fuzzyRightUrl){
            this.fuzzyRightUrl = solveNullList(fuzzyRightUrl);
            return this;
        }

        public ConditionBuilder rightFuzzyRightUrl (List<String> rightFuzzyRightUrl){
            this.rightFuzzyRightUrl = rightFuzzyRightUrl;
            return this;
        }

        public ConditionBuilder rightFuzzyRightUrl (String ... rightFuzzyRightUrl){
            this.rightFuzzyRightUrl = solveNullList(rightFuzzyRightUrl);
            return this;
        }

        public ConditionBuilder rightUrlList(String ... rightUrl){
            this.rightUrlList = solveNullList(rightUrl);
            return this;
        }

        public ConditionBuilder rightUrlList(List<String> rightUrl){
            this.rightUrlList = rightUrl;
            return this;
        }

        public ConditionBuilder fuzzyBackUrl (List<String> fuzzyBackUrl){
            this.fuzzyBackUrl = fuzzyBackUrl;
            return this;
        }

        public ConditionBuilder fuzzyBackUrl (String ... fuzzyBackUrl){
            this.fuzzyBackUrl = solveNullList(fuzzyBackUrl);
            return this;
        }

        public ConditionBuilder rightFuzzyBackUrl (List<String> rightFuzzyBackUrl){
            this.rightFuzzyBackUrl = rightFuzzyBackUrl;
            return this;
        }

        public ConditionBuilder rightFuzzyBackUrl (String ... rightFuzzyBackUrl){
            this.rightFuzzyBackUrl = solveNullList(rightFuzzyBackUrl);
            return this;
        }

        public ConditionBuilder backUrlList(String ... backUrl){
            this.backUrlList = solveNullList(backUrl);
            return this;
        }

        public ConditionBuilder backUrlList(List<String> backUrl){
            this.backUrlList = backUrl;
            return this;
        }

        public ConditionBuilder fuzzyCarNo (List<String> fuzzyCarNo){
            this.fuzzyCarNo = fuzzyCarNo;
            return this;
        }

        public ConditionBuilder fuzzyCarNo (String ... fuzzyCarNo){
            this.fuzzyCarNo = solveNullList(fuzzyCarNo);
            return this;
        }

        public ConditionBuilder rightFuzzyCarNo (List<String> rightFuzzyCarNo){
            this.rightFuzzyCarNo = rightFuzzyCarNo;
            return this;
        }

        public ConditionBuilder rightFuzzyCarNo (String ... rightFuzzyCarNo){
            this.rightFuzzyCarNo = solveNullList(rightFuzzyCarNo);
            return this;
        }

        public ConditionBuilder carNoList(String ... carNo){
            this.carNoList = solveNullList(carNo);
            return this;
        }

        public ConditionBuilder carNoList(List<String> carNo){
            this.carNoList = carNo;
            return this;
        }

        public ConditionBuilder fuzzyCarBrand (List<String> fuzzyCarBrand){
            this.fuzzyCarBrand = fuzzyCarBrand;
            return this;
        }

        public ConditionBuilder fuzzyCarBrand (String ... fuzzyCarBrand){
            this.fuzzyCarBrand = solveNullList(fuzzyCarBrand);
            return this;
        }

        public ConditionBuilder rightFuzzyCarBrand (List<String> rightFuzzyCarBrand){
            this.rightFuzzyCarBrand = rightFuzzyCarBrand;
            return this;
        }

        public ConditionBuilder rightFuzzyCarBrand (String ... rightFuzzyCarBrand){
            this.rightFuzzyCarBrand = solveNullList(rightFuzzyCarBrand);
            return this;
        }

        public ConditionBuilder carBrandList(String ... carBrand){
            this.carBrandList = solveNullList(carBrand);
            return this;
        }

        public ConditionBuilder carBrandList(List<String> carBrand){
            this.carBrandList = carBrand;
            return this;
        }

        public ConditionBuilder carNatureBetWeen(Integer carNatureSt,Integer carNatureEd){
            this.carNatureSt = carNatureSt;
            this.carNatureEd = carNatureEd;
            return this;
        }

        public ConditionBuilder carNatureGreaterEqThan(Integer carNatureSt){
            this.carNatureSt = carNatureSt;
            return this;
        }
        public ConditionBuilder carNatureLessEqThan(Integer carNatureEd){
            this.carNatureEd = carNatureEd;
            return this;
        }


        public ConditionBuilder carNatureList(Integer ... carNature){
            this.carNatureList = solveNullList(carNature);
            return this;
        }

        public ConditionBuilder carNatureList(List<Integer> carNature){
            this.carNatureList = carNature;
            return this;
        }

        public ConditionBuilder drivingAgeBetWeen(Integer drivingAgeSt,Integer drivingAgeEd){
            this.drivingAgeSt = drivingAgeSt;
            this.drivingAgeEd = drivingAgeEd;
            return this;
        }

        public ConditionBuilder drivingAgeGreaterEqThan(Integer drivingAgeSt){
            this.drivingAgeSt = drivingAgeSt;
            return this;
        }
        public ConditionBuilder drivingAgeLessEqThan(Integer drivingAgeEd){
            this.drivingAgeEd = drivingAgeEd;
            return this;
        }


        public ConditionBuilder drivingAgeList(Integer ... drivingAge){
            this.drivingAgeList = solveNullList(drivingAge);
            return this;
        }

        public ConditionBuilder drivingAgeList(List<Integer> drivingAge){
            this.drivingAgeList = drivingAge;
            return this;
        }

        public ConditionBuilder carTypeBetWeen(Integer carTypeSt,Integer carTypeEd){
            this.carTypeSt = carTypeSt;
            this.carTypeEd = carTypeEd;
            return this;
        }

        public ConditionBuilder carTypeGreaterEqThan(Integer carTypeSt){
            this.carTypeSt = carTypeSt;
            return this;
        }
        public ConditionBuilder carTypeLessEqThan(Integer carTypeEd){
            this.carTypeEd = carTypeEd;
            return this;
        }


        public ConditionBuilder carTypeList(Integer ... carType){
            this.carTypeList = solveNullList(carType);
            return this;
        }

        public ConditionBuilder carTypeList(List<Integer> carType){
            this.carTypeList = carType;
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

        private PlusCar obj;

        public Builder(){
            this.obj = new PlusCar();
        }

        public Builder id(Long id){
            this.obj.setId(id);
            return this;
        }
        public Builder userId(Long userId){
            this.obj.setUserId(userId);
            return this;
        }
        public Builder orderId(Long orderId){
            this.obj.setOrderId(orderId);
            return this;
        }
        public Builder faceUrl(String faceUrl){
            this.obj.setFaceUrl(faceUrl);
            return this;
        }
        public Builder leftAnteriorUrl(String leftAnteriorUrl){
            this.obj.setLeftAnteriorUrl(leftAnteriorUrl);
            return this;
        }
        public Builder rightAnteriorUrl(String rightAnteriorUrl){
            this.obj.setRightAnteriorUrl(rightAnteriorUrl);
            return this;
        }
        public Builder leftUrl(String leftUrl){
            this.obj.setLeftUrl(leftUrl);
            return this;
        }
        public Builder rightUrl(String rightUrl){
            this.obj.setRightUrl(rightUrl);
            return this;
        }
        public Builder backUrl(String backUrl){
            this.obj.setBackUrl(backUrl);
            return this;
        }
        public Builder carNo(String carNo){
            this.obj.setCarNo(carNo);
            return this;
        }
        public Builder carBrand(String carBrand){
            this.obj.setCarBrand(carBrand);
            return this;
        }
        public Builder carNature(Integer carNature){
            this.obj.setCarNature(carNature);
            return this;
        }
        public Builder drivingAge(Integer drivingAge){
            this.obj.setDrivingAge(drivingAge);
            return this;
        }
        public Builder carType(Integer carType){
            this.obj.setCarType(carType);
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
        public PlusCar build(){return obj;}
    }
}
