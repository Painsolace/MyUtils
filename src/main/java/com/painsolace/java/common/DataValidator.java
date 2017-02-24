package com.painsolace.java.common;


import java.util.List;
import java.util.Map;

public class DataValidator {

	/**
	 * 判断字符串是否为空 return
	 */
	public static boolean isNull(String str) {
		if (str == null || str.equals("") || str.length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNull(Integer str) {
		if (str == null || str == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断list是否为空
	 */
	public static boolean isNull(List<?> list) {
		if (list != null && list.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 判断map是否为空
	 */
	public static boolean isNull(Map<?, ?> map) {
		if (map != null && map.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}
}
