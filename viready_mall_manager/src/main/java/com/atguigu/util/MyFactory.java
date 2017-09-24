package com.atguigu.util;
import java.io.IOException;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * 获取SqlSessionFactory(单例)
 */
public class MyFactory {
	private static SqlSessionFactory sqlSessionFactory; 
	public static SqlSessionFactory getMyFactory(){
		if(sqlSessionFactory == null){
			String resource = "mybatis-config.xml";
			InputStream inputStream = null;
			try {
				inputStream = Resources.getResourceAsStream(resource);
			} catch (IOException e) {
				e.printStackTrace();
			}
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}
		return sqlSessionFactory;
	}
}
