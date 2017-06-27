package com.painsolace.java;

import com.painsolace.java.md5Utils.MD5Util;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class ExportPointsUtil {
	
	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	public static String createSign(SortedMap<String, String> packageParams,
			String export_points_key) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"export_points_key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("export_points_key=" + export_points_key);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8")
				.toUpperCase();
		return sign;

	}

	// 输出XML
	public static String parseXML(SortedMap<String, String> packageParams) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"partnerKey".equals(k)) {
				sb.append("<" + k + ">" + v + "</" + k + ">\n");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
	
	/**
	 * description: 解析微信通知xml
	 * 
	 * @param xml
	 * @return
	 * @author ex_yangxiaoyi
	 * @see
	 */
	public static SortedMap<String, String> parseXmlToList2(String xml) {
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		try {
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc = (Document) sb.build(source);
			Element root = doc.getRootElement();// 指向根节点
			List<Element> es = root.getChildren();
			if (es != null && es.size() != 0) {
				for (Element element : es) {
					packageParams.put(element.getName(), element.getValue());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return packageParams;
	}
	
	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1D) {
			random += 0.1D;
		}
		for (int i = 0; i < length; i++) {
			num *= 10;
		}
		return (int) (random * num);
	}
}
