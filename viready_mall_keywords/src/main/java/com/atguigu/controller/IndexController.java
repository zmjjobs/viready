package com.atguigu.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.ResponseParser;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.util.JedisPoolUtils;
import com.atguigu.util.MyHttpSolrServer;
import com.atguigu.util.MyProperty;
import com.atguigu.util.MyRedisDataUtil;

import redis.clients.jedis.Jedis;


@Controller
public class IndexController {
	
	
	@RequestMapping("index")
	public String index(){
		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<OBJECT_T_MALL_SKU>();
		Jedis jedis = JedisPoolUtils.getJedis();
		Set<String> zrange = jedis.zrange("class_2_"+28, 0, -1);
		list_sku = MyRedisDataUtil.get_list_by_redis(zrange, OBJECT_T_MALL_SKU.class);
		
		HttpSolrServer solrServer = MyHttpSolrServer.getSolrServer(MyProperty.getMyProperty("solr_sku","solr.properties"));
		
		try {
			//删除所有查询
			solrServer.deleteByQuery("*:*");
			//添加新的信息
			solrServer.addBeans(list_sku);
			//提交信息
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}
}
