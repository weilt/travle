package com.common.util;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/6 15:47
 * @Description:
 */
public class XMLUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(XMLUtil.class);

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     * @param strXml
     * @return
     */
    public static Map doXMLParse(String strXml){
        if(null == strXml || "".equals(strXml)) {
            return null;
        }
        Map m = new HashMap();
        InputStream in = null;
        try{
            in = new ByteArrayInputStream(strXml.getBytes());
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(in);
            Element root = doc.getRootElement();
            List list = root.getChildren();
            Iterator it = list.iterator();
            while(it.hasNext()) {
                Element e = (Element) it.next();
                String k = e.getName();
                String v = "";
                List children = e.getChildren();
                if(children.isEmpty()) {
                    v = e.getTextNormalize();
                } else {
                    v = getChildrenText(children);
                }
                m.put(k, v);
            }
        }catch (Exception e){
            LOGGER.error("解析失败:",e.getMessage());
        }finally {

            //关闭流
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return m;
    }

    /**
     * 获取子结点的xml
     * @param children
     * @return String
     */
    private static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if(!children.isEmpty()) {
            Iterator it = children.iterator();
            while(it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if(!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }
        return sb.toString();
    }


    /**
     *  生成XMl 之支持一级
     * @param o 生成xml的对象
     * @return string
     */
    public static String createXML(Object o) {
        StringBuilder sb = new StringBuilder();
        Class clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        sb.append("<xml>");
        for (Field f : fields){
            String name = f.getName();
            String methodName = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
            sb.append(invoke(o,"get"+methodName,name));
        }
        return sb.append("</xml>").toString();
    }

    /**
     * 拼装xml
     * @param o
     * @param methodName
     * @param fieldName
     * @return
     */
    private static String invoke (Object o,String methodName,String fieldName){
        String result = "";
        try {
            Method method = o.getClass().getMethod(methodName);
            Object value =  method.invoke(o);
            if (null != value){
                result = "<"+fieldName+">";
                result += String.valueOf(value);
                result += "</"+fieldName+">";
            }
        } catch (NoSuchMethodException e) {
            LOGGER.error("没有找到该方法:",e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error("参数错误:",e.getMessage());
        } catch (InvocationTargetException e) {
            LOGGER.error("执行方法错误:",e.getMessage());
        }
        return result;
    }

//    public static void main(String[] args) {
//        CreateOrderParam createOrderParam = new CreateOrderParam();
//        createOrderParam.setAppid("123").setGoods_tag("1212324234");
//        System.out.println(createXML(createOrderParam));
//    }

}
