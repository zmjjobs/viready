package com.atguigu.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.OBJECT_T_MALL_SKU;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月30日 下午1:15:44 
 * @version v1
 */
public interface SearchRedisMapper {

	List<OBJECT_T_MALL_SKU> select_sku_by_class_2(int class_2_id);


	List<Integer> select_value_by_attr_id(int shxm_id);


	List<OBJECT_T_MALL_SKU> select_sku_by_attr(Map<Object, Object> map);

}
