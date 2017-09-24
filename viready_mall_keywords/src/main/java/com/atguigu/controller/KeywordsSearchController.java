package com.atguigu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.util.JedisPoolUtils;
import com.atguigu.util.MyHttpSolrServer;
import com.atguigu.util.MyJsonUtil;
import com.atguigu.util.MyProperty;
import com.atguigu.util.MyRedisDataUtil;

import redis.clients.jedis.Jedis;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月19日 下午9:13:14 
 * @version v1
 */
@Controller
public class KeywordsSearchController {

	@RequestMapping("search_by_keywords/{keywords}")
	@ResponseBody
	public List<OBJECT_T_MALL_SKU> search_by_keywords(@PathVariable String keywords){
		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<>();
		HttpSolrServer solrServer = MyHttpSolrServer.getSolrServer(MyProperty.getMyProperty("solr_sku", "solr.properties"));
		
		//新建查询对象，并设置查询语句
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("sku_mch:"+keywords);
		try {
			//根据查询语句查询，返回一个查询响应对象,并得到里面的beans
			QueryResponse response = solrServer.query(solrQuery);
			list_sku = response.getBeans(OBJECT_T_MALL_SKU.class);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return list_sku;
		
		
	}
	
}
