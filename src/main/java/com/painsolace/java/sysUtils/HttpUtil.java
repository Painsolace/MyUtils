package com.painsolace.java.sysUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 * http请求
 *
 */
public class HttpUtil {

    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url) {
    	InputStream in = null;
        String sb = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Content-type", "text/html");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Connection", "Keep-Alive");
            // 建立实际的连接
            connection.connect();
            in = connection.getInputStream();
            int length = in.available();
            if (length > 0) {
            	byte[] bs = new byte[length];
            	in.read(bs);
            	sb=new String(bs ,"UTF-8");
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return sb;
    }
    
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
    	//System.out.println("post");
    	DataOutputStream  out = null;
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer("");
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection  conn = (HttpURLConnection )realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Content-type", "text/html");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(true);
            conn.setUseCaches(false);
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new DataOutputStream(conn.getOutputStream());
            // 发送请求参数
            if (param != null) {
            	out.writeBytes(param);
			}
            out.flush();
            out.close(); 
            
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
      
            while ((line = in.readLine()) != null) {
            	line = new String(line.getBytes(), "utf-8");
                sb.append(line);
            }
            System.out.println(sb);
            in.close();
            conn.disconnect();
            
            
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return sb.toString();
    }
    
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param obj
     *            请求参数，请求参数应该JSON的形式。
     * @return 所代表远程资源的响应结果
     */
	public static String postJSON(String url,String obj) {
		String sb = null;  
		try {
            //创建连接
            URL urls = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            
            // 设置通用的请求属性
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            if (obj != null) {
            	out.writeBytes(obj);
			}
            out.flush();
            out.close();
         
            //读取响应
            InputStream in = connection.getInputStream();
            int length = in.available();
            if (length > 0) {
            	byte[] bs = new byte[length];
            	in.read(bs);
            	sb = new String(bs ,"UTF-8");
			}
            
            // 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
		 return sb.toString();
	}
	 
	 
	/**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param reqUrl
     *            发送请求的 URL
     * @param parameters
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
	public static String doPost(String reqUrl, Map parameters, String recvEncoding) {
	    HttpURLConnection conn = null;
	    String responseContent = null;
	    try {
	      StringBuffer params = new StringBuffer();
	      for (Iterator iter = parameters.entrySet().iterator(); iter.hasNext();) {
	        Entry element = (Entry) iter.next();
	        params.append(element.getKey().toString());
	        params.append("=");
	        params.append(URLEncoder.encode(element.getValue().toString(), recvEncoding));
	        params.append("&");
	      }

	      if (params.length() > 0) {
	        params = params.deleteCharAt(params.length() - 1);
	      }
	      URL url = new URL(reqUrl);
	      HttpURLConnection url_con = (HttpURLConnection) url.openConnection();
	      url_con.setRequestMethod("POST");
	      url_con.setConnectTimeout(5000);//（单位：毫秒）jdk
	      // 1.5换成这个,连接超时
	      url_con.setReadTimeout(5000);//（单位：毫秒）jdk 1.5换成这个,读操作超时
	      url_con.setDoOutput(true);
	      byte[] b = params.toString().getBytes();
	      url_con.getOutputStream().write(b, 0, b.length);
	      url_con.getOutputStream().flush();
	      url_con.getOutputStream().close();

	      InputStream in = url_con.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(in, recvEncoding));
	      String tempLine = rd.readLine();
	      StringBuffer tempStr = new StringBuffer();
	      String crlf = System.getProperty("line.separator");
	      while (tempLine != null) {
	        tempStr.append(tempLine);
	        tempStr.append(crlf);
	        tempLine = rd.readLine();
	      }
	      responseContent = tempStr.toString();
	      rd.close();
	      in.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      if (conn != null) {
	        conn.disconnect();
	      }
	    }
	    return responseContent;
	  }
	
	
	  /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param jsonStr
     *            请求参数，请求参数应该JSON的形式。
     * @return 所代表远程资源的响应结果
     */
	public static String postShopJSON(String url,String jsonStr,boolean isDebug) {
		String sb = null;  
		try {
            //创建连接
            URL urls = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            
            // 设置通用的请求属性
            connection.setRequestProperty("content-encoding", "application/json");
            connection.setRequestProperty("Content-type", "application/json");
            if(isDebug){
            	connection.setRequestProperty("Cookie", "debug=1");
            }
            

            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        	out.write(jsonStr.toString().getBytes("UTF-8"));
            out.flush();
            out.close();
            //读取响应
            InputStream in = connection.getInputStream();
            int length = in.available();
            if (length > 0) {
            	byte[] bs = new byte[length];
            	in.read(bs);
            	sb = new String(bs ,"UTF-8");
			}
            if(isDebug){
            	showCookie(connection);
            }
            // 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
		 return sb.toString();
	}
	
	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * post方式请求服务器(https协议)
	 * 
	 * @param url
	 *            请求地址
	 * @param content
	 *            参数
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static String post(String url, String content)
			throws NoSuchAlgorithmException, KeyManagementException,
			IOException {
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
				new java.security.SecureRandom());

		URL console = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
		conn.setSSLSocketFactory(sc.getSocketFactory());
		conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
		conn.setDoOutput(true);
		conn.connect();
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.write(content.getBytes("UTF-8"));
		// 刷新、关闭
		out.flush();
		out.close();
		InputStream is = conn.getInputStream();
		if (is != null) {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			is.close();
			return new String(outStream.toByteArray());
		}
		return null;
	} 
	
	private static void showCookie(HttpURLConnection conn){
		Map<String,List<String>> map=conn.getHeaderFields();
		Set<String> set=map.keySet();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			if (key.equals("Set-Cookie")) {
				System.out.println("key=" + key+",开始获取cookie");
				List<String> list = map.get(key);
				StringBuilder builder = new StringBuilder();
				for (String str : list) {
					builder.append(str).toString();
				}
				builder.toString();
				System.out.println("返回cookie="+builder.toString());
			}
		}
	}
}