package com.painsolace.java.bean;

import java.util.HashMap;
import java.util.Map;


public class ResultBean {
    /**
     * 没有设置回复数据
     */
    private final static String NO_DATA = "not set data error!";
    private final static String SUCCESS = "1";
    private final static String ERROR = "0";

    /**
     * 返回实际内容
     */
    private Object content;

    /**
     * 错误内容
     */
    private Object error;

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Map<String,Object> toBack(){
        Map<String,Object> map = new HashMap<String, Object>();
        if(this.error != null){
            map.put("c", this.error);
            map.put("f", ERROR);
            return map;
        }

        if(this.getContent() != null){
            map.put("f", SUCCESS);
            map.put("c", this.getContent());
        }else{
            map.put("f", ERROR);
            map.put("c", NO_DATA);
        }
        return map;
    }
}
