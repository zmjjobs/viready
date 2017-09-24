package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月30日 下午12:17:24 
 * @version v1
 */
public interface SearchRedisServiceInf {

	List<OBJECT_T_MALL_SKU> get_sku_by_class_2(int class_2_id);

	/**
	 * 根据分类属性的ID，查询分类属性值的集合
	 * @param attr_id 分类属性的ID
	 * @return 分类属性值ID的集合
	 */
	List<Integer> get_value_by_attr_id(int attr_id);
	
	List<OBJECT_T_MALL_SKU> get_sku_by_attr(String order, int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_av);

}
