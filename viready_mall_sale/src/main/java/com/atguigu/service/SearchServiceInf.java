package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.DETAIL_T_MALL_SKU;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月19日 下午8:44:12 
 * @version v1
 */
public interface SearchServiceInf {

	List<OBJECT_T_MALL_SKU> get_sku_by_class_2(int class_2_id);

	List<OBJECT_T_MALL_ATTR> get_attr_by_class_2(int class_2_id);

	

	DETAIL_T_MALL_SKU get_sku_detail_by_id(int sku_id);

	List<OBJECT_T_MALL_SKU> get_sku_by_spu(int spu_id);

	List<OBJECT_T_MALL_SKU> get_sku_by_attr(String order, int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_av);

}
