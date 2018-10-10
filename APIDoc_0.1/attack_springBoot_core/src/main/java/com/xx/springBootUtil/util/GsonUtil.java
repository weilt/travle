package com.xx.springBootUtil.util;
import java.io.Reader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

/**
 * GsonUtil
 * @author JIAO_LIU_BABA
 */
public class GsonUtil {


	public static String toJson(Object obj, Type type) {
		Gson gson = new Gson();
		return gson.toJson(obj, type);
	}
	
	public static String toJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	public static Object fromJson(String str, Type type) {
		Gson gson = new Gson();
		return gson.fromJson(str, type);
	}

	public static Object fromJson(Reader reader, Type type) {
		Gson gson = new Gson();
		return gson.fromJson(reader, type);
	}
}
