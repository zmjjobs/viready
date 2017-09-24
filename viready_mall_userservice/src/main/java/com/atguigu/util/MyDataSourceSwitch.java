package com.atguigu.util;
/**
 * 自定义数据源交换器，通过ThreadLocal传递
 * @author 朱梦君
 *
 */
public class MyDataSourceSwitch {

	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

	public static String getKey() {
		return threadLocal.get();
	}

	public static void setKey(String key) {
		threadLocal.set(key);
	}

}
