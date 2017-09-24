package com.atguigu.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.bean.T_MALL_PRODUCT;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月16日 下午5:25:45 
 * @version v1
 */
public interface SkuMapper {

	/**
	 * 根据传参获取产品信息SPU
	 * @param hashMap
	 * @return
	 */
	List<T_MALL_PRODUCT> select_spu_by_tm(HashMap<String, Object> hashMap);

	/**
	 * 插入SKU信息
	 * @param hashMap
	 */
	void insert_sku(HashMap<String, Object> hashMap);
	/**
	 * 插入sku的属性和属性值关联表数据
	 * @param hashMap4skuav
	 */
	void insert_sku_av(HashMap<String, Object> hashMap4skuav);

}
