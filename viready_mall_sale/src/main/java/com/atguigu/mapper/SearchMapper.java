package com.atguigu.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.DETAIL_T_MALL_SKU;
import com.atguigu.bean.OBJECT_T_MALL_SKU;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月19日 下午8:46:13 
 * @version v1
 */
public interface SearchMapper {
	/**
	 * 分类查询
	 * @param class_2_id
	 * @return
	 */
	List<OBJECT_T_MALL_SKU> select_sku_by_class_2(int class_2_id);
	
	/**
	 * 根据属性查询
	 * @param map
	 * @return
	 */
	

	List<OBJECT_T_MALL_SKU> select_sku_by_attr(Map<Object, Object> map);

	
	List<OBJECT_T_MALL_SKU> select_sku_by_spu(int spu_id);

	DETAIL_T_MALL_SKU select_sku_detail_by_id(int sku_id);
}
