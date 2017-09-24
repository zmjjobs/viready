package com.atguigu.test;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.UserServiceInf;
import com.atguigu.util.MyWSFactory;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月25日 上午11:34:32 
 * @version v1
 */
public class TestCxfClient {
	public static void main(String[] args){
		/*JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setAddress("http://localhost:28080/viready_mall_userservice/user?wsdl");
		jaxWsProxyFactoryBean.setServiceClass(UserServiceInf.class);*/
		UserServiceInf us = MyWSFactory.getMyWSInf("http://localhost:28080/viready_mall_userservice/user?wsdl", UserServiceInf.class);
		T_MALL_USER_ACCOUNT user = new T_MALL_USER_ACCOUNT();
		user.setYh_mch("test");
		user.setYh_mm("123");
		T_MALL_USER_ACCOUNT login = us.login(user);
		System.out.println(login);
	}
}
