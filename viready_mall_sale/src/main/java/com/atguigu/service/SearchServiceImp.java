package com.atguigu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.DETAIL_T_MALL_SKU;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.AttrMapper;
import com.atguigu.mapper.SearchMapper;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月19日 下午8:45:09 
 * @version v1
 */
@Service
public class SearchServiceImp implements SearchServiceInf {
	
	@Autowired
	private SearchMapper searchMapper;
	
	@Autowired
	private AttrMapper attrMapper;
	
	@Override
	public List<OBJECT_T_MALL_SKU> 	get_sku_by_class_2(int class_2_id){
		return searchMapper.select_sku_by_class_2(class_2_id);
	}
	
	@Override
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2(int class_2_id){
		return attrMapper.select_attr_by_class_2(class_2_id);
	}
	

	@Override
	public DETAIL_T_MALL_SKU get_sku_detail_by_id(int sku_id) {
		return searchMapper.select_sku_detail_by_id(sku_id);
	}

	@Override
	public List<OBJECT_T_MALL_SKU> get_sku_by_spu(int spu_id) {
		return searchMapper.select_sku_by_spu(spu_id);
	}

	@Override
	public List<OBJECT_T_MALL_SKU> get_sku_by_attr(String order, int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_av) {
		StringBuffer sql = new StringBuffer("");
		if(list_av != null && list_av.size() > 0 ){
			sql.append(" and sku.id in ( select sku_0.sku_id from ");

			//动态sql区域
			int size = list_av.size();
			for (int i = 0; i < size; i++) {
				T_MALL_SKU_ATTR_VALUE av = list_av.get(i);
				sql.append(" ( select sku_id from t_mall_sku_attr_value where shxzh_id = "+av.getShxzh_id()+" and shxm_id = "+av.getShxm_id()+" ) sku_"+i+" ");
				
				//如果i小于（size减一），则拼接 ，
				if(i<(size-1)){
					sql.append(" , ");
				}
			}
			
			//如果size大于1，则有至少两个动态表，需要where来继续加条件判断
			if(size > 1){
				sql.append(" where ");
				
				for (int i = 0; i < size; i++) {
					
					//如果i小于（size减一），则拼接and关联语句
					if(i<(size - 1)){
						sql.append(" sku_"+i+".sku_id=sku_"+(i+1)+".sku_id ");
						if(i<(size -2)){
							sql.append(" and ");
						}
					}
				}
			}
			
			
			sql.append(" ) ");
		}
		
		Map<Object, Object> map=new HashMap<>();
		map.put("class_2_id", class_2_id);
		map.put("sql", sql);
		if (StringUtils.isNotBlank(order)) {
			map.put("order", order);
		}
		List<OBJECT_T_MALL_SKU> list_sku = searchMapper.select_sku_by_attr(map);

		return list_sku;
	}
	
}
