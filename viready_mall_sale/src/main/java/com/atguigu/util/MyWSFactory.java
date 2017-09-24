package com.atguigu.util;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.UserServiceInf;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月25日 下午1:12:36 
 * @version v1
 */
public class MyWSFactory {
	@SuppressWarnings("unchecked")
	public static <T> T getMyWSInf(String url,Class<T> t){
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setAddress(url);
		jaxWsProxyFactoryBean.setServiceClass(t);
		T create =  (T) jaxWsProxyFactoryBean.create();
		return create;
	}
}
