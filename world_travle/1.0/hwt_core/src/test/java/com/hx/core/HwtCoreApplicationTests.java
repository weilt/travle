package com.hx.core;


import com.hx.core.es.entity.EsPage;
import com.hx.core.es.utils.ElasticsearchUtils;
import com.hx.core.pay.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.hx.core.pay.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.hx.core.pay.wxpay.config.WxPayConfig;
import com.hx.core.pay.wxpay.constant.WxPayConstants;
import com.hx.core.pay.wxpay.exception.WxPayException;
import com.hx.core.pay.wxpay.service.WxPayService;
import com.hx.core.pay.wxpay.service.impl.WxPayServiceImpl;
import javafx.application.Application;
import jodd.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.hx.core.redis.RedisCache.ip;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class HwtCoreApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
    public void t(){
        EsPage searchDataPage = ElasticsearchUtils.searchDataPage("hwt", "scenic_spot", 0, 10, 0l,new Date().getTime(), null, null, false, "city", "city=重庆");
        //EsPage searchDataPage = ElasticsearchUtils.searchDataPage("t1", "fulltext", 0, 10, 0l,new Date().getTime(), null, null, false, "content", "content=美国");
        List<Map<String,Object>> recordList = searchDataPage.getRecordList();
        for (Map<String, Object> map : recordList) {
            System.out.println("-------------------------");
            System.out.println(map);
        }
        System.err.println(searchDataPage.getRecordCount());
    }

    @Test
    public void wxpay() {
	    //微信配置
	    WxPayConfig wxPayConfig = new WxPayConfig();
	    wxPayConfig.setAppId("wx3be92d69f8754f1f");
	    wxPayConfig.setMchId("1509764901");
	    wxPayConfig.setMchKey("LIMINGXIANlimingxian123456789123");
	    wxPayConfig.setNotifyUrl("http://sevenplus.huaixinkeji.com/notifyCallback");

        WxPayUnifiedOrderRequest request = WxPayUnifiedOrderRequest.newBuilder()
                .body("旗驾服务业务办理")
                .totalFee(1)
//                .totalFee(totalFee)
                .spbillCreateIp(ip)
                .notifyUrl(wxPayConfig.getNotifyUrl())
                .tradeType(WxPayConstants.TradeType.JSAPI)
                .openid("oGpNc5fHX05sKPoU3GZAlyzUSWKM")
                .outTradeNo("1234546")
                .build();
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig);
        try {
            WxPayUnifiedOrderResult wxPayUnifiedOrderResult = wxPayService.unifiedOrder(request);
            System.out.println(StringUtil.toString(wxPayUnifiedOrderResult));
        } catch (WxPayException e) {
            e.printStackTrace();
        }
    }
}
