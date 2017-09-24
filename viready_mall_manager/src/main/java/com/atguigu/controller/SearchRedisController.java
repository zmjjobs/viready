package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Attr;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.service.AttrServiceImp;
import com.atguigu.service.SearchRedisServiceImp;
import com.atguigu.util.JedisPoolUtils;
import com.atguigu.util.MyJsonUtil;
import com.atguigu.util.MyRedisDataUtil;

import redis.clients.jedis.Jedis;

/**
 * @description 查询redis缓存的控制器
 * @author 朱梦君
 * @datatime 2017年8月29日 下午11:34:48 
 * @version v1
 */
@Controller
public class SearchRedisController {
	
	@Autowired
	private SearchRedisServiceImp searchRedisServiceImp;
	
	@RequestMapping("goto_search_redis")
	public String goto_search_redis() {
		return "manager_search_redis";
	}
	
	@RequestMapping("refresh_class_2")
	@ResponseBody
	public int refresh_class_2(int class_2_id){
		//查询二级分类对应的商品sku集合
		List<OBJECT_T_MALL_SKU> list_sku = searchRedisServiceImp.get_sku_by_class_2(class_2_id);
		
		//将list_sku更新到redis中
		String key = "class_2_"+class_2_id;
		return MyRedisDataUtil.list_to_redis(key , list_sku);
	}

	
	
	/**
	 * 
	 * @param class_2_id
	 * @param attr_array 通过@RequestParam接收的来自页面的数组
	 * @return 更新的条数
	 */
	@RequestMapping("refresh_attr")
	@ResponseBody
	public long refresh_attr(int class_2_id,@RequestParam("attr_array[]") int[] attr_array){
		long sum = 0;
		
		//循环分类属性ID的数组
		int length = attr_array.length;
		for (int i = 0; i < length; i++) {
			int attr_id = attr_array[i];
			//根据分类属性的ID查询分类属性值的集合
			List<Integer> list_value_id = searchRedisServiceImp.get_value_by_attr_id(attr_id);
			
			//循环分类属性值的集合，查询对应的sku集合
			int size = list_value_id.size();
			
			for (int j = 0; j < size; j++) {
				int shxzh_id = list_value_id.get(j);
				String key = "av_"+class_2_id+"_"+attr_id+"_"+shxzh_id;
				List<T_MALL_SKU_ATTR_VALUE> list_av = new ArrayList<>();
				T_MALL_SKU_ATTR_VALUE av = new T_MALL_SKU_ATTR_VALUE();
				av.setShxm_id(attr_id);
				av.setShxzh_id(shxzh_id);
				list_av.add(av);
				List<OBJECT_T_MALL_SKU> list_sku = searchRedisServiceImp.get_sku_by_attr("",class_2_id,list_av);
				sum += list_sku.size();
				//循环sku集合，插入redis中
				MyRedisDataUtil.list_to_redis(key, list_sku);
			}
		}
		return sum;
		
	}
}
