package com.painsolace.java.sysUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

	public static StringBuffer transfer(String URL, String jsonObj)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		// 创建连接
		java.net.URL url = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		connection.setConnectTimeout(3000);
		connection.connect();
		// POST请求
		DataOutputStream out = new DataOutputStream(
				connection.getOutputStream());
		if (jsonObj != null) {
			out.writeBytes(jsonObj);
		}
		out.flush();
		out.close();
		// 读取响应
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String lines;
		while ((lines = reader.readLine()) != null) {
			lines = new String(lines.getBytes(), "utf-8");
			sb.append(lines);
		}
		reader.close();
		// 断开连接
		connection.disconnect();
		return sb;

	}
}
