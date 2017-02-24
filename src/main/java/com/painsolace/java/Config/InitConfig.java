package com.painsolace.java.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by painsolace on 2017/1/23.
 */
public class InitConfig {

    private static final Logger log = LoggerFactory.getLogger(InitConfig.class);

    @PostConstruct
    public void init() {
        try {
            Map<String, Object> map = readProperties();
            for (Map.Entry<String, Object> item : map.entrySet()) {
                Configuration.putConfig(item.getKey(), item.getValue());
                //log.info(item.getKey() + " : " + item.getValue());
            }
            //SystemDataBase.init();//基础数据初始化
            log.info("Init config file Finished···");
        } catch (Exception e) {
            log.error("Wrong config file，" + e);
            e.printStackTrace();
            if (e.getStackTrace() != null)
                if (e.getStackTrace()[0] != null)
                    log.error("Exception at:" + e.getStackTrace()[0].getClassName() + ":" + e.getStackTrace()[0].getLineNumber());

        }
    }


    @SuppressWarnings("unchecked")
    public static Map readProperties() throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            InputStream is =  InitConfig.class.getResourceAsStream("/config.properties");
            Properties props = new Properties();
            props.load(is);
            Enumeration en = props.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                map.put(key.trim(), props.getProperty(key));
            }
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
