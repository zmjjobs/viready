package com.atguigu.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_SKU;

import redis.clients.jedis.Jedis;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月31日 上午11:18:40 
 * @version v1
 */
public class MyRedisDataUtil {
	/**
	 * 根据redis缓存中的某个字符串集合转换成某个对象集合
	 * @param c 字符串集合对象
	 * @param t 对象类型
	 * @return list对象集合
	 */
	public static <T> List<T> get_list_by_redis(Collection<String> c, Class<T> t) {
		List<T> list = new ArrayList<>();
		Iterator<String> iterator = c.iterator();
		while(iterator.hasNext()){
			String next = iterator.next();
			T json_to_obj = MyJsonUtil.json_to_obj(next, t);
			list.add(json_to_obj);
		}
		return list;
	}
	
	
	
	/**
	 * 将数据库中查询的list更新到redis上
	 * @param key 
	 * @param list 数据库中查询的list
	 * @return 更新的条数
	 */
	public static <T> int list_to_redis(String key,List<T> list) {
		//获得redis连接客户端
		Jedis jedis = JedisPoolUtils.getJedis();
		
		//根据key删除之前的jedis缓存
		jedis.del(key);
		
		//调用redis的api将sku商品集合放入redis中
		int size = list.size();
		for (int s = 0; s < size; s++) {
			jedis.zadd(key, s,MyJsonUtil.obj_to_json(list.get(s)));
		}
		return size;
	}
}
