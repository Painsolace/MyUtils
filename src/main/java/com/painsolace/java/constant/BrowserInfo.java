package com.painsolace.java.constant;

/**
 * 用于保存各种浏览器user-agent标识值的常量类
 * 
 */
public class BrowserInfo {
	/**
	 * Google浏览器的user-agent标识关键字
	 * Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/533.4 (KHTML, like Gecko) Chrome/5.0.375.9 Safari/533.4
	 * <li>注: Google浏览器的标识中会带有Apple Safari的标识，应在判断Safari浏览器前优先判断此浏览器</li>
	 */
	public static final String BROWSER_CHROME_FLG = "chrome";
	
	/**
	 * Apple Safari浏览器的user-agent标识关键字
	 * Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN) AppleWebKit/531.9 (KHTML, like Gecko) Version/4.0.3 Safari/531.9.1
	 */
	public static final String BROWSER_SAFARI_FLG = "safari";
	
	/**
	 * Firefox浏览器的user-agent标识关键字
	 * Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.4) Gecko/20100413 Firefox/3.6.4 (.NET CLR 3.5.30729)
	 */
	public static final String BROWSER_FIREFOX_FLG = "firefox";
	
	/**
	 * Opera浏览器的user-agent标识关键字
	 * Opera/9.80 (Windows NT 5.1; U; zh-cn) Presto/2.5.24 Version/10.52
	 */
	public static final String BROWSER_OPERA_FLG = "opera";
	
	/**
	 * 360浏览器的user-agent标识关键字
	 * Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; 360SE)
	 * <li>注: 360浏览器由于采用了IE的内核因此标识中会带有含Microsoft IE的标识，应在判断IE类浏览器前优先判断此浏览器</li>
	 */
	public static final String BROWSER_360_FLG = "360se";
	
	/**
	 * Maxthon浏览器的user-agent标识关键字
	 * Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)
	 * <li>注: Maxthon浏览器由于采用了IE的内核因此标识中会带有含Microsoft IE的标识，应在判断IE类浏览器前优先判断此浏览器</li>
	 */
	public static final String BROWSER_MAXTHON_FLG = "maxthon";
	
	/**
	 * IE6浏览器的user-agent标识关键字
	 * Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)
	 *
	 */
	public static final String BROWSER_IE6_FLG = "msie 6";
	
	/**
	 * IE7浏览器的user-agent标识关键字
	 * <li>注: IE7浏览器的标识中会带有含Googe Chrome的chromeframe标识，应在判断Chrome浏览器前优先判断此浏览器</li>
	 * Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; chromeframe; .NET CLR 2.0.50727; InfoPath.2)
	 */
	public static final String BROWSER_IE7_FLG = "msie 7";
	
	/**
	 * IE8浏览器的user-agent标识关键字
	 * Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; chromeframe; .NET CLR 2.0.50727; InfoPath.2)
	 *<li>注: IE8浏览器的标识中会带有含Googe Chrome的chromeframe标识，应在判断Chrome浏览器前优先判断此浏览器</li>
	 */
	public static final String BROWSER_IE8_FLG = "msie 8";
	
	/**
	 * Google浏览器名称
	 */
	public static final String BROWSER_CHROME = "Chrome";
	
	/**
	 * Apple Safari浏览器名称
	 */
	public static final String BROWSER_SAFARI = "Safari";
	
	/**
	 * Firefox浏览器名称
	 */
	public static final String BROWSER_FIREFOX = "Firefox";
	
	/**
	 * Opera浏览器名称
	 */
	public static final String BROWSER_OPERA = "Opera";
	
	/**
	 * 360浏览器名称
	 */
	public static final String BROWSER_360 = "360";
	
	/**
	 * Maxthon浏览器名称
	 */
	public static final String BROWSER_MAXTHON = "Maxthon";
	
	/**
	 * IE6浏览器名称
	 *
	 */
	public static final String BROWSER_IE6 = "IE6";
	
	/**
	 * IE7浏览器名称
	 */
	public static final String BROWSER_IE7 = "IE7";
	
	/**
	 * IE8浏览器名称
	 */
	public static final String BROWSER_IE8 = "IE8";
	
	/**
	 * Firefox、Chrom浏览器窗口关闭调用函数名称
	 */
	public static final String BEFORE_UNLOAD = "onbeforeunload";
	
	/**
	 * IE系(IE6、IE7、IE8、360、Maxthon)浏览器窗口关闭调用函数名称
	 */
	public static final String ON_UNLOAD = "onunload";
}