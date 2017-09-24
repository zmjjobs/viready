package com.atguigu.util;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;

/**
 * @description
 * @author 朱梦君
 * @datatime 2017年9月3日 下午3:19:14
 * @version v1
 */
public class MyHttpSolrServer {
	
	/**
	 * 定义solr的server
	 * @param baseUrl
	 * @return
	 */
	public static HttpSolrServer getSolrServer(String baseUrl){
		//页面访问的地址     http://localhost:8983/solr/#/sku
		HttpSolrServer solrServer = new HttpSolrServer(baseUrl);
		
		//设置响应解析器     
		solrServer.setParser(new XMLResponseParser());
		
		//设置最大尝试解析的次数，推荐设置为1
		solrServer.setMaxRetries(1);
		
		// 建立连接的最长时间
		solrServer.setConnectionTimeout(3000);
		
		return solrServer;
	}
}
