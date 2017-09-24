package com.atguigu.util;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 *@author zhumengjun
 *@version 
 *@datetime 2017年7月8日下午8:29:02  
 */
public class MyHttpGetUtil {
	
	public static String doGet(String url) throws IOException {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// 创建http GET请求
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			//执行请求
			response = httpClient.execute(httpGet);
			//判断返回的状态码是否为200
			if(response.getStatusLine().getStatusCode() == 200){
				return EntityUtils.toString(response.getEntity(),"UTF-8");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(response != null){
				response.close();
			}
			httpClient.close();
		}
		return null;
	}
    
}
