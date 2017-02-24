package com.painsolace.java.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by painsolace on 2017/1/23.
 */
public class Configuration {
    private static Map<String, Object> MAP_CONFIG = new HashMap<String, Object>();

    public static String  test1 = "";

    public static String  test2 = "";

    public static Object getConfig(String key) {
        if (key == null)
            return null;
        return MAP_CONFIG.get(key);
    }

    public static void putConfig(String key, Object obj) {
        // 保护默认值不被null冲掉
        if (obj == null)
            return;
        if (key != null)
            MAP_CONFIG.put(key, obj);
    }


}
