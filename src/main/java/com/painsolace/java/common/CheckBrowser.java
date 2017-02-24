package com.painsolace.java.common;

import com.painsolace.java.constant.BrowserInfo;

import java.util.HashMap;


/**
 * 用于检测客户端浏览器类型的工具类
 * 
 */
public class CheckBrowser {
	public static HashMap<String,String> browserUnload = new HashMap<String,String>();
	
	static{
		browserUnload.put(BrowserInfo.BROWSER_360,BrowserInfo.ON_UNLOAD);
		browserUnload.put(BrowserInfo.BROWSER_MAXTHON,BrowserInfo.ON_UNLOAD);
		browserUnload.put(BrowserInfo.BROWSER_IE6,BrowserInfo.ON_UNLOAD);
		browserUnload.put(BrowserInfo.BROWSER_IE7,BrowserInfo.ON_UNLOAD);
		browserUnload.put(BrowserInfo.BROWSER_IE8,BrowserInfo.ON_UNLOAD);
		browserUnload.put(BrowserInfo.BROWSER_CHROME,BrowserInfo.BEFORE_UNLOAD);
		browserUnload.put(BrowserInfo.BROWSER_SAFARI,"");
		browserUnload.put(BrowserInfo.BROWSER_OPERA,"");
		browserUnload.put(BrowserInfo.BROWSER_FIREFOX,BrowserInfo.BEFORE_UNLOAD);
	}
	
	/**
	 * 用于获取浏览器名称
	 * 
	 * @param agent
	 * @return
	 */
	public static String getBroswerName(String agent) {
		String broswer = "无浏览器标识";
		if (agent != null) {
			agent = agent.toLowerCase();
			if (agent.indexOf(BrowserInfo.BROWSER_360_FLG) > -1) { // 360浏览器
				broswer = BrowserInfo.BROWSER_360;
			} else if (agent.indexOf(BrowserInfo.BROWSER_MAXTHON_FLG) > -1) { // Maxthon浏览器
				broswer = BrowserInfo.BROWSER_MAXTHON;
			} else if (agent.indexOf(BrowserInfo.BROWSER_IE6_FLG) > -1) { // IE6浏览器
				broswer = BrowserInfo.BROWSER_IE6;
			} else if (agent.indexOf(BrowserInfo.BROWSER_IE7_FLG) > -1) { // IE7浏览器
				broswer = BrowserInfo.BROWSER_IE7;
			} else if (agent.indexOf(BrowserInfo.BROWSER_IE8_FLG) > -1) { // IE8浏览器
				broswer = BrowserInfo.BROWSER_IE8;
			} else if (agent.indexOf(BrowserInfo.BROWSER_CHROME_FLG) > -1) { // Chrome浏览器
				broswer = BrowserInfo.BROWSER_CHROME;
			} else if (agent.indexOf(BrowserInfo.BROWSER_SAFARI_FLG) > -1) { // Safari浏览器
				broswer = BrowserInfo.BROWSER_SAFARI;
			} else if (agent.indexOf(BrowserInfo.BROWSER_OPERA_FLG) > -1) { // Opera浏览器
				broswer = BrowserInfo.BROWSER_OPERA;
			} else if (agent.indexOf(BrowserInfo.BROWSER_FIREFOX_FLG) > -1) { // Firefox浏览器
				broswer = BrowserInfo.BROWSER_FIREFOX;
			} else{
				broswer = "版本未识别{" + agent + "}";
			}
		}
		return broswer;
	}
	
	/**
	 * 获取浏览器使用的关闭时加载的函数名儿
	 * 
	 * @param agent
	 * @return
	 */
	public static String getBrowserUnload(String agent){
		return browserUnload.get(getBroswerName(agent));
	}

}
