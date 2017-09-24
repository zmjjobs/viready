package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月16日 下午5:22:39 
 * @version v1
 */
public interface SkuServiceInf {

	List<T_MALL_PRODUCT> get_spu_by_tm(int pp_id, int class_1_id, int class_2_id);

	/**
	 * 保存sku信息以及sku的属性和属性值
	 * @param spu
	 * @param list_av
	 * @param sku
	 */
	void save_sku(OBJECT_T_MALL_PRODUCT spu, MODEL_T_MALL_SKU_ATTR_VALUE list_av, T_MALL_SKU sku);

}
