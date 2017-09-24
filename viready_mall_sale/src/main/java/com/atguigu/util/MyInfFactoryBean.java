package com.atguigu.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.FactoryBean;

/**
 * @description 自定义的工厂bean
 * @author 朱梦君
 * @datatime 2017年8月25日 下午8:50:32 
 * @version v1
 */
public class MyInfFactoryBean<T> implements FactoryBean<T> {
	private Class<T> t;
	private String url;
	
	public Class<T> getT() {
		return t;
	}

	public void setT(Class<T> t) {
		this.t = t;
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public T getObject() throws Exception {
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setAddress(url);
		jaxWsProxyFactoryBean.setServiceClass(t);
		if(t.getSimpleName().equals("UserServiceInf")){
			Map<String,Object> map = new HashMap<>();
			map.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
			map.put(WSHandlerConstants.USER, "user");
			map.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
			map.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyCallBackClient.class.getName());
			//客户端安全拦截器
			WSS4JOutInterceptor wss4jOutInterceptor = new WSS4JOutInterceptor(map);
			
			jaxWsProxyFactoryBean.getOutInterceptors().add(wss4jOutInterceptor);
		}
		return (T) jaxWsProxyFactoryBean.create();
	}

	@Override
	public Class<?> getObjectType() {
		return this.t;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
