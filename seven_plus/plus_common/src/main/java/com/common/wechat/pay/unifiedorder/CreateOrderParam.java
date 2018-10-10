package com.common.wechat.pay.unifiedorder;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/6 17:14
 * @Description: 统一下单传入参数
 */
public class CreateOrderParam implements Serializable {

    /**
     * 小程序ID	appid	是	String(32)	wxd678efh567hg6787	微信分配的小程序ID
     * 商户号	mch_id	是	String(32)	1230000109	微信支付分配的商户号
     * 设备号	device_info	否	String(32)	013467007045764	自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     * 随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，长度要求在32位以内。推荐随机数生成算法
     * 签名	sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	通过签名算法计算得出的签名值，详见签名生成算法
     * 签名类型	sign_type	否	String(32)	MD5	签名类型，默认为MD5，支持HMAC-SHA256和MD5。
     * 商品描述	body	是	String(128)	腾讯充值中心-QQ会员充值
     * 商品简单描述，该字段请按照规范传递，具体请见参数规定
     *
     * 商品详情	detail	否	String(6000)	 	商品详细描述，对于使用单品优惠的商户，改字段必须按照规范上传，详见“单品优惠参数说明”
     * 附加数据	attach	否	String(127)	深圳分店	附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     * 商户订单号	out_trade_no	是	String(32)	20150806125346	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
     * 标价币种	fee_type	否	String(16)	CNY	符合ISO 4217标准的三位字母代码，默认人民币：CNY，详细列表请参见货币类型
     * 标价金额	total_fee	是	Int	88	订单总金额，单位为分，详见支付金额
     * 终端IP	spbill_create_ip	是	String(16)	123.12.12.123	APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
     * 交易起始时间	time_start	否	String(14)	20091225091010	订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     * 交易结束时间	time_expire	否	String(14)	20091227091010
     * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。其他详见时间规则
     *
     * 建议：最短失效时间间隔大于1分钟
     *
     * 订单优惠标记	goods_tag	否	String(32)	WXG	订单优惠标记，使用代金券或立减优惠功能时需要的参数，说明详见代金券或立减优惠
     * 通知地址	notify_url	是	String(256)	http://www.weixin.qq.com/wxpay/pay.php	异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     * 交易类型	trade_type	是	String(16)	JSAPI	小程序取值如下：JSAPI，详细说明见参数规定
     * 商品ID	product_id	否	String(32)	12235413214070356458058	trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
     * 指定支付方式	limit_pay	否	String(32)	no_credit	上传此参数no_credit--可限制用户不能使用信用卡支付
     * 用户标识	openid	否	String(128)	oUpF8uMuAJO_M2pxb1Q9zNjWeS6o	trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。openid如何获取，可参考【获取openid】。
     */

    private String appid;

    private String mch_id;

    private String device_info;

    private String nonce_str;

    private String sign;

    private String sign_type;

    private String body;

    private String detail;

    private String attach;

    private String out_trade_no;

    private String fee_type;

    private String total_fee;

    private String spbill_create_ip;

    private String time_start;

    private String time_expire;

    private String goods_tag;

    private String notify_url;

    private String trade_type;

    private String product_id;

    private String limit_pay;

    private String openid;

    public String getAppid() {
        return appid;
    }

    public CreateOrderParam setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getMch_id() {
        return mch_id;
    }

    public CreateOrderParam setMch_id(String mch_id) {
        this.mch_id = mch_id;
        return this;
    }

    public String getDevice_info() {
        return device_info;
    }

    public CreateOrderParam setDevice_info(String device_info) {
        this.device_info = device_info;
        return this;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public CreateOrderParam setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public CreateOrderParam setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String getSign_type() {
        return sign_type;
    }

    public CreateOrderParam setSign_type(String sign_type) {
        this.sign_type = sign_type;
        return this;
    }

    public String getBody() {
        return body;
    }

    public CreateOrderParam setBody(String body) {
        this.body = body;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public CreateOrderParam setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public String getAttach() {
        return attach;
    }

    public CreateOrderParam setAttach(String attach) {
        this.attach = attach;
        return this;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public CreateOrderParam setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
        return this;
    }

    public String getFee_type() {
        return fee_type;
    }

    public CreateOrderParam setFee_type(String fee_type) {
        this.fee_type = fee_type;
        return this;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public CreateOrderParam setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
        return this;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public CreateOrderParam setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
        return this;
    }

    public String getTime_start() {
        return time_start;
    }

    public CreateOrderParam setTime_start(String time_start) {
        this.time_start = time_start;
        return this;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public CreateOrderParam setTime_expire(String time_expire) {
        this.time_expire = time_expire;
        return this;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public CreateOrderParam setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
        return this;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public CreateOrderParam setNotify_url(String notify_url) {
        this.notify_url = notify_url;
        return this;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public CreateOrderParam setTrade_type(String trade_type) {
        this.trade_type = trade_type;
        return this;
    }

    public String getProduct_id() {
        return product_id;
    }

    public CreateOrderParam setProduct_id(String product_id) {
        this.product_id = product_id;
        return this;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public CreateOrderParam setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
        return this;
    }

    public String getOpenid() {
        return openid;
    }

    public CreateOrderParam setOpenid(String openid) {
        this.openid = openid;
        return this;
    }
}
