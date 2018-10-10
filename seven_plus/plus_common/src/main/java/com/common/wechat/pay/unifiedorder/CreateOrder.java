package com.common.wechat.pay.unifiedorder;

import com.common.excption.BaseException;
import com.common.util.HttpClientUtil;
import com.common.util.XMLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/6 16:20
 * @Description: 统一下单
 */
public class CreateOrder {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateOrder.class);

    /**
     * 微信统一下单地址
     */
    private static final String CREATE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 关闭订单地址
     */
    private static final String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";

    private static final String PREPAY_ID = "prepay_id";


    /**
     * 获取下单
     * @param xmlParam
     * @return
     */
    public static String createOrder(String xmlParam){
        String result = HttpClientUtil.httpost(CREATE_ORDER_URL,xmlParam);
        if(-1 != result.indexOf("FAIL")){
            LOGGER.error("下单失败：",result);
            throw new BaseException("下单失败："+ result);
        }
        Map map = XMLUtil.doXMLParse(result);

        return map.get(PREPAY_ID).toString();
    }



}
