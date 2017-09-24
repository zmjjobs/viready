package com.atguigu.test;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class Phone_Call {
	public static void main(String[] args)throws Exception
	{

	HttpClient client = new HttpClient();
	PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
	post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
	NameValuePair[] data ={ 
			new NameValuePair("Uid", "zhumengjun"), // 注册的用户名
			new NameValuePair("Key", "ed0e570e29e1ac9cdeb0"), // 注册成功后，登录网站后得到的密钥
			new NameValuePair("smsMob", "13146862669"), // 目的手机号码
			new NameValuePair("smsText","验证码："+System.currentTimeMillis())};
	post.setRequestBody(data);

	client.executeMethod(post);
	Header[] headers = post.getResponseHeaders();
	int statusCode = post.getStatusCode();
	System.out.println("statusCode:"+statusCode);
	for(Header h : headers)
	{
	System.out.println(h.toString());
	}
	String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
	System.out.println(result); //打印返回消息状态
	post.releaseConnection();

	}
}

