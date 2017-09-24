package com.atguigu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.bean.DETAIL_T_MALL_SKU;
import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.SearchServiceInf;
import com.atguigu.util.JedisPoolUtils;
import com.atguigu.util.MyHttpGetUtil;
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
public class SearchController {

	@Autowired
	private SearchServiceInf searchServiceInf;
	
	@RequestMapping("search_by_keywords")
	public String search_by_keywords(String keywords,ModelMap map){
		keywords = keywords.replaceAll(" ", "");
		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<>();
		//访问关键字检索服务，输入关键字
		try {
			String doGet = MyHttpGetUtil.doGet(MyProperty.getMyProperty("keywords_url", "solrServer.properties")+"search_by_keywords/"+keywords+".do");
			list_sku = MyJsonUtil.json_to_list(doGet, OBJECT_T_MALL_SKU.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("keywords", keywords);
		map.put("list_sku", list_sku);
		return "sale_keywords";
		
	}
	
	
	@RequestMapping("get_sku_detail_by_id")
	public String get_sku_detail_by_id(int spu_id,int sku_id,ModelMap map){
		//根据sku查询sku详情
		DETAIL_T_MALL_SKU detail_sku = searchServiceInf.get_sku_detail_by_id(sku_id);
		List<OBJECT_T_MALL_SKU> list_sku = searchServiceInf.get_sku_by_spu(spu_id);
		map.put("detail_sku", detail_sku);
		map.put("list_sku", list_sku);
		return "sale_search_detail";
		
	}
	
	
	@RequestMapping("get_sku_by_attr")
	public String get_sku_by_attr(String order, int class_2_id, MODEL_T_MALL_SKU_ATTR_VALUE list_av_bean, ModelMap map) {
		Jedis jedis = new JedisPoolUtils().getJedis();
		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<>();
		List<T_MALL_SKU_ATTR_VALUE> list_av =  list_av_bean.getList_av();
		int size = 0;
		String out = "out";
		String[] keys = null;
		if(list_av != null){
			size = list_av.size();
			
			//获取所有的KEY
			keys = new String[size];
			for (int i = 0; i < size; i++) {
				T_MALL_SKU_ATTR_VALUE av = list_av.get(i);
				keys[i] = "av_"+class_2_id +"_"+av.getShxm_id() + "_" + av.getShxzh_id();
			}
			
			for (int i = 0; i < size; i++) {
				out = out + "_" + keys;
			}
			Boolean exists = jedis.exists(out);
			if (!exists) {
				jedis.zinterstore(out, keys);
			}
			jedis.zinterstore(out, keys);
		}
		Set<String> zrange = jedis.zrange(out, 0, -1);
		
		if (zrange != null && zrange.size() > 0) {
			list_sku = MyRedisDataUtil.get_list_by_redis(zrange, OBJECT_T_MALL_SKU.class);
		} else {
			// mysql
			list_sku = searchServiceInf.get_sku_by_attr(order, class_2_id, list_av);
			for (int i = 0; i < size; i++) {
				if(keys[i] != null){
					MyRedisDataUtil.list_to_redis(keys[i] , list_sku);
				}
	 		}
		}
		for (int i = 0; i < list_sku.size(); i++) {
			System.out.println(list_sku.get(i).getSku_mch());
		}
		map.put("list_sku", list_sku);
		return "sale_search_inner";
	}
	
	
	
	/**
	 * 通过商品的分类来检索商品
	 * 根据class_2_id查询sku和attr,并将class_2_id、class_2_name、sku和attr放到ModelMap中 
	 * @param class_2_id
	 * @param class_2_name
	 * @param map
	 * @return
	 */
	@RequestMapping("class_2_sku_search")
	public String class_2_sku_search(int class_2_id,String class_2_name,ModelMap map){
		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<>();
		List<OBJECT_T_MALL_ATTR> list_attr = new ArrayList<>();;
		// 获得redis连接客户端
		Jedis jedis = JedisPoolUtils.getJedis();

		// 查询redis缓存数据
		Set<String> zrange = jedis.zrange("class_2_" + class_2_id, 0, -1);
		if (zrange != null && zrange.size() > 0) {
			list_sku = MyRedisDataUtil.get_list_by_redis(zrange, OBJECT_T_MALL_SKU.class);
		} else {
			// 从mysql中查询
			list_sku = searchServiceInf.get_sku_by_class_2(class_2_id);
			
			//将list_sku同步更新到redis中
			String key = "class_2_"+class_2_id;
			MyRedisDataUtil.list_to_redis(key , list_sku);
		}
		list_attr = searchServiceInf.get_attr_by_class_2(class_2_id);
		map.put("list_sku", list_sku);
		map.put("list_attr", list_attr);
		map.put("class_2_id", class_2_id);
		map.put("class_2_name", class_2_name);
		return "sale_search";
	}


	
	
	/**
	 * 通过商品的分类来检索商品
	 * 根据class_2_id查询sku和attr,并将class_2_id、class_2_name、sku和attr放到ModelMap中
	 * 这里使用的是restful风格，所以不用再次将class_2_id和class_2_name放入到map中了
	 * @param class_2_id
	 * @param class_2_name
	 * @param map
	 * @return
	 */
	@RequestMapping("class_2_sku_search_restful/{class_2_id}/{class_2_name}")
	public String class_2_sku_search_restful(@PathVariable("class_2_id")int class_2_id,@PathVariable("class_2_name")String class_2_name,ModelMap map){
		List<OBJECT_T_MALL_SKU> list_sku = searchServiceInf.get_sku_by_class_2(class_2_id);
		List<OBJECT_T_MALL_ATTR> list_attr = searchServiceInf.get_attr_by_class_2(class_2_id);
		map.put("list_sku", list_sku);
		map.put("list_attr", list_attr);
		//map.put("class_2_id", class_2_id);
		//map.put("class_2_name", class_2_name);
		return "sale_search";
	}
}
