package com.common.wechat.pay;

import com.common.util.MD5Util;
import com.common.util.XMLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Map.Entry;

public class WeixinPaySign {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeixinPaySign.class);
    /**
     * 签名算法
     * @param map 要参与签名的数据对象
     * @return 签名
     */
    public static String getSign(Map<String,Object> map, String key){
        ArrayList<String> list = new ArrayList<>();
        for(Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()!="" && entry.getValue() != null){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        result = MD5Util.MD5Encode(result).toUpperCase();
        return result;
    }

    /**
     * 从API返回的XML数据里面重新计算一次签名
     * @param responseString API返回的XML数据
     * @throws Exception
     */
    public static String getSignFromResponseString(String responseString,String key) {
        Map<String,Object> map = XMLUtil.doXMLParse(responseString);
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign","");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        return WeixinPaySign.getSign(map,key);
    }

    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
     */
    @SuppressWarnings("unchecked")
	public static boolean checkIsSignValidFromResponseString(String responseString,String key){

    	 Map<String,Object> map = XMLUtil.doXMLParse(responseString);

        String signFromAPIResponse = map.get("sign").toString();
        if(signFromAPIResponse=="" || signFromAPIResponse == null){
            LOGGER.info("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
            return false;
        }
//        logger.info("服务器回包里面的签名是:" + signFromAPIResponse);
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign","");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        String signForAPIResponse = WeixinPaySign.getSign(map,key);

        if(!signForAPIResponse.equals(signFromAPIResponse)){
            //签名验不过，表示这个API返回的数据有可能已经被篡改了
        	LOGGER.info("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
            return false;
        }
        LOGGER.info("恭喜，API返回的数据签名验证通过!!!");
        return true;
    }

    /**
     * sign签名
     * @param characterEncoding - 签名格式
     * @param parameters - 值
     * @param key - 支付KEY值
     * @return
     */
	public static String createSign(String characterEncoding,SortedMap<Object, Object> parameters,String key) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<Object, Object>> es = parameters.entrySet();
		Iterator<Entry<Object, Object>> it = es.iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = (Entry<Object, Object>) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			/** 如果参数为key或者sign，则不参与加密签名 */
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		/** 支付密钥必须参与加密，放在字符串最后面 */
		sb.append("key=" + key);
		/** 记得最后一定要转换为大写 */
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding)
				.toUpperCase();
		return sign;
	}

    /**
     * 生成15为随机号
     * @return
     */
	public static String createOutTradeNo(){
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0
        // 15 代表长度为15
        // d 代表参数为正数型
        return String.format("%015d", hashCodeV);
    }


}
