package com.atguigu.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.mapper.SkuMapper;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月16日 下午5:21:55 
 * @version v1
 */
@Service
public class SkuServiceImp implements SkuServiceInf{

	@Autowired
	private SkuMapper skuMapper;
	
	@Override
	public List<T_MALL_PRODUCT> get_spu_by_tm(int pp_id, int class_1_id, int class_2_id) {
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("pp_id", pp_id);
		hashMap.put("class_1_id", class_1_id);
		hashMap.put("class_2_id", class_2_id);
		List<T_MALL_PRODUCT> list_spu = skuMapper.select_spu_by_tm(hashMap);
		return list_spu;
	}

	
	@Override
	public void save_sku(OBJECT_T_MALL_PRODUCT spu, MODEL_T_MALL_SKU_ATTR_VALUE list_av, T_MALL_SKU sku) {
		HashMap<String, Object> hashMap4sku = new HashMap<String,Object>();
		hashMap4sku.put("spu", spu);
		hashMap4sku.put("sku", sku);
		skuMapper.insert_sku(hashMap4sku);
		HashMap<String, Object> hashMap4skuav = new HashMap<String,Object>();
		hashMap4skuav.put("list_av", list_av.getList_av());
		hashMap4skuav.put("sku_id", sku.getId());
		hashMap4skuav.put("spu_id", spu.getId());
		skuMapper.insert_sku_av(hashMap4skuav);
	}


}
