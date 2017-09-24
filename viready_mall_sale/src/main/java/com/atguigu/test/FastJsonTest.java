package com.atguigu.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.atguigu.bean.T_MALL_SHOPPINGCART;

public class FastJsonTest {
	
	
	// 一个购物车对象
		T_MALL_SHOPPINGCART t_MALL_SHOPPINGCAR = new T_MALL_SHOPPINGCART();
		List<T_MALL_SHOPPINGCART> list = new ArrayList<T_MALL_SHOPPINGCART>();
		@Before
		public void setUp(){
			
			// 一个购物车对象
			t_MALL_SHOPPINGCAR.setSku_mch("商品");
			
			t_MALL_SHOPPINGCAR.setSku_jg(new BigDecimal(888));
			t_MALL_SHOPPINGCAR.setTjshl(10);
			
			//一个购物车 集合对象
			for (int i = 0; i < 5; i++) {
				T_MALL_SHOPPINGCART t_MALL_SHOPPINGCAR1 = new T_MALL_SHOPPINGCART();
				t_MALL_SHOPPINGCAR1.setSku_mch("商品" + i);
				t_MALL_SHOPPINGCAR1.setSku_jg(new BigDecimal(i * 1000));
				t_MALL_SHOPPINGCAR1.setTjshl(i);
				list.add(t_MALL_SHOPPINGCAR1);
			}
			
			
		}

		/**
		 * bean对象转json互转
		 */
		@Test
		public void testObject2JsonString() {
			String object2Json = object2Json(list);
			System.out.println(object2Json);
			/*
			//对象转Json: JSON.toJSONString(Object object)
			String jsonString = JSON.toJSONString(t_MALL_SHOPPINGCAR);
			//System.out.println(jsonString);
			
			//Json转对象: JSON.parseObject(String text,Class<T> Class);
			T_MALL_SHOPPINGCART t_MALL_SHOPPINGCAR2 = JSON.parseObject(jsonString,T_MALL_SHOPPINGCART.class);
			System.out.println(t_MALL_SHOPPINGCAR2);*/
			
			
		}
		
		
		public String object2Json(Object object){
			String jsonString = JSON.toJSONString(object);
			return jsonString;
		}
		/**
		 * List对象转json互转
		 */
		@Test
		public void testList2JsonString() {
			
			//Json转集合: JSON.parseArray(String text,Class<T> Class);
			String json_arr = JSON.toJSONString(list);
			//System.out.println(json_arr);
			
			List<T_MALL_SHOPPINGCART> list2 = JSON.parseArray(json_arr,T_MALL_SHOPPINGCART.class);
			for (T_MALL_SHOPPINGCART t_MALL_SHOPPINGCAR : list2) {
				System.out.println(t_MALL_SHOPPINGCAR);
			}
		}
		
}
