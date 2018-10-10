package com.hx.core.pay.alipay;

/**
 * 支付宝支付配置  用于继承（方便配置到配置文件中）
 */


public abstract class AliPayConstants {
	 // --------- **支付宝支付填充信息 **-------------
	
	 //在支付宝支付开发平台登记的
	 public final static String app_id = "2018091761423178";
	 //私钥
     public final static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC7DmmgAZxD4sATbR8biDsIbsIGPAPC532vhnSqjjyosMLwHP9QbLbQMAuLYtgyR3B6Y/+V11HOogOWvpEPbKd5HuYQ+xnh4RDHVEbJeTNmRI4rABoZVZSB7wLW5e9M/xSpau6e4uhyocQ4nKe6pWriIeDf7Yba0W8DUtqNSAMZ/hlikQ7zhS2r3ENidQfzvr6PzhxAT0cH0W4GvkwBpOA/IVfjRE34WKyz5/R/sOykJxNRmPzk7qyUeMdPgtEOOyUJZfVfdnmyf5X4WofAIsm4/D4NO8otgdmrG41AHn5dgW4IbVGisWlULOjNqnmdMjJKcghrxy5OVGlU1u/bGm1pAgMBAAECggEAFPyr+yjTGIbDWCqhF9y0CuEqYbqwDVkdr+jdBBnLUn3qkTgfqS1ZYYhSc0QWz6VWSFUZ4vzyj8V7s5kT+3AMZjmCRJt3rvEqicK54nb3vlyoushbJUaF0AI6BlRsAe6Iqe12clsxC1N9CYECDJ3snQyCB33F6J0g4HWWUc2nk23OWHqio5jK9833iIf+DJSDh+XS+lFsHpJiWPaq1j+VJTggpCjTFRdj3yXIjAWEElqdDKGD9bkG8OG6I7t3tGPfy2n8mITrKJ1d4ud5n9GQjd3IB9UTAf1f1P9Jh7bYswEQblaRI7BwFOWjSCeYuCxz/5AXwPoyJ/5uRGU4N7wvZQKBgQD9NpEbD44Bw2fqvJ68uauWwJwtCHSVlLzEdsCbBo/NEzZJuZXWVZRU7d8pD8g8QsbmHsB6m2WiJ5CqkACOVW0fZPNGdyesafVnUg0Tl9m/5pZqi2vn0+QzC08kOSlM2lPVSBMTh/FFwE/l2jAWG4oVBMFFvTUXNhBVsSKnV7Tm2wKBgQC9HXKP21nau1Xn09hc0tyGmSYRDdpCmLLv+4lGRgalwK0ffbXf6W0qY9TKZkNGuBzUM3zRjenx0FoZJWSv1t2LHaYs40+u+aEJ6NuNmPFBRK+jnTVXvxchDuQryUk5Ys7HQEzkPHeFd73zHf4IBM0v5ejEmCat1xrzRFO3yTAmCwKBgQDWWDurMZafTX+JCJRC49UprvBFerPd72N/15qhYIOYMJwy+oOpRRZkclwHEVIKGOjv4bZD04nMIzvl9jJSSmQuQDFjQ/h0jdlr1dw16sQsGxDXLiBwxUCWKQuceTrJ0MNId1OWo+wMhIfd5b3w6O0Rb83/r/PiKyTWtTTFfYwoIwKBgCtyKG+EJRBrGUE5Geu5RZBmfqhMVmgWTIV1dAQA8mYB7cPeTXozVjD23lvhQnNsuLLHYVWDFMvI4eoE/SyoYB0lopeNYTEBiE2OgWH2UPZ9oRwlcavG6rldgm3MwFW+rWJ5EzEb8xlmeOzxZvrQn5Lxbpg7nCcz060vdUwznCwhAoGBAIjdwIzfOPkM7/9xl6RHXIE3tIls0XIdriRhZVMHPJ/mcmHO5G1jEliXVsC16sVOmg7I9qpbddwL3B8HlFkoHSEUfCN4mcD4hLxVDNGL9U9zUZlfybJwrpuih0ir/m/8I05SDaWhYbx+KIdTU1TInVEg97FZhifO+iPfgLZZa5c2";
     //公钥
     public final static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4uVTec43DY/KFMkbhc1u/zwLOO9pEXf+OlHvReKcAy58Of91jMkTbeezJsJQu6rhdQPHWBBn9945JaEmh0f2fDnbFGwwWx/wMp3Yhtl/UnmqJnigaQGlDPPVqnj/dvtYTtBawL4DpFwmQavKCfRKUHDbBivgUu30UQzVGKfadpPD2KdrKpEP3azCUEuQUYKcgWR1CUXN8TR+Kf3Tpz7bOSZ6Hhq0HYFOC42KWdlDNDlTaFPRBLzoyg9fKSzVhVKKs5aRlip/2oYYRm/Gz273zVvJJeCTa/sIrxt1HiuNnyq/CueGXuqAmmT0/X5JZghbZfAnXVt43/iO6QVwVkiI+wIDAQAB";
     //异步通知地址
     public final static String notify_url = " http://travel.cqtsp.cn/aliback";
     
     /*
      * 支付宝网关  
      * https://openapi.alipaydev.com/gateway.do   - 沙箱环境  
      * https://openapi.alipay.com/gateway.do   - 正式环境
      */
     public final static String url = "https://openapi.alipay.com/gateway.do";

     
     
}
