package com.painsolace.java.common;


import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * json工具类
 * 
 */
public class JsonUtils {

	public static Object jsonToBean(String json, Class className) {
		JSONObject jb = JSONObject.fromObject(json);
		Object obj = JSONObject.toBean(jb, className);
		return obj;
	}

	public static JSONObject beanToJson(Object obj) {
		return JSONObject.fromObject(obj);
	}

	public static List<JSONObject> listToJson(List list) {
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		for (Object obj : list) {
			jsonList.add(JSONObject.fromObject(obj));
		}
		return jsonList;
	}

	public static JSONObject StringToJson(String str) {
		return JSONObject.fromObject(str);
	}

	public static String jsonToXml(JSONObject json) {

		XMLSerializer xmlSerializer = new XMLSerializer();

		String xml = xmlSerializer.write(json);

		return xml;
	}

	public static JSONObject HashMapToJson(Map hashmap) {
		return JSONObject.fromObject(hashmap);
	}
	

	/**
	 * map转换JSON
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String mapToJsonString(Map map){
		return JSONObject.fromObject(map).toString();
	}
	
	/**
	 * 获取json中String
	 * @param jsonObject
	 * @param key
	 * @return
	 * @throws JSONException
	 */
	public static String getString(JSONObject jsonObject,String key) throws JSONException {
		try{
			return jsonObject.get(key).toString();
		}catch(Exception e){
			throw new JSONException("尊敬的开发者 :参数[" + key + "]为啥没有给后台？", e);
		}
	}
	/**
	 * 获取json中数组
	 * @param jsonObject
	 * @param key
	 * @return
	 * @throws JSONException
	 */
	public static Object[] getArray(JSONObject jsonObject,String key) throws JSONException{
		try{
			return jsonObject.getJSONArray(key).toArray();
		}catch(Exception e){
			throw new JSONException("尊敬的开发者 :参数[" + key + "] 是不是没给后台或者给的不是数组？", e);
		}
	}
}
