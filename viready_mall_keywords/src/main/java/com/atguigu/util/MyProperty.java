package com.atguigu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 *	根据文件内容中的KEY获取VALUE
 * @author Administrator
 *
 */
public class MyProperty {
	
	/**
	 * 在文件中根据key找到对应的Value
	 * @param key
	 * @param proFile properties文件
	 * @return
	 */
	public static String getMyProperty(String key,String proFile){
		
		//以Properties接收，它的存储是字符串=字符串
		Properties properties = new Properties();
		
		//将存放路径的文件通过类加载器转换为流的形式
		InputStream resourceAsStream = MyProperty.class.getClassLoader().getResourceAsStream(proFile);
		
		try {
			properties.load(resourceAsStream);
			return properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
