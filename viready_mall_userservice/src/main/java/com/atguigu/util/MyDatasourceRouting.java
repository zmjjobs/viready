package com.atguigu.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @description 自定义数据源路由器，决定当前要使用数据源的KEY
 * @author 朱梦君
 * @datatime 2017年8月26日 上午12:24:45 
 * @version v1
 */
public class MyDatasourceRouting extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		
		return MyDataSourceSwitch.getKey();
	}
	
}
