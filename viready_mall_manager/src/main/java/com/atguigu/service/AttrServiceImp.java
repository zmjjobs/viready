package com.atguigu.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.mapper.AttrMapper;
import com.atguigu.mapper.SpuMapper;
@Service
public class AttrServiceImp implements AttrServiceInf {
	
	@Autowired
	private AttrMapper attrMapper;

	@Override
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2(int class_2_id) {
		
		return attrMapper.select_attr_by_class_2(class_2_id);
	}

	/**
	 * 保存属性以及它所关联的属性值集合
	 */
	@Override
	public void save_attr(List<OBJECT_T_MALL_ATTR> list_attr, int class_2_id) {
		if(list_attr != null){
			for (int i = 0; i < list_attr.size(); i++) {
				OBJECT_T_MALL_ATTR attr_obj = list_attr.get(i);
				//循环插入一个attr
				HashMap<String, Object> hashMap4attr = new HashMap<String,Object>();
				hashMap4attr.put("class_2_id", class_2_id);
				hashMap4attr.put("attr_obj", attr_obj);
				attrMapper.insert_attr(hashMap4attr);
				//同时循环插入对应attr的属性值集合
				HashMap<String, Object> hashMap4Vaules = new HashMap<String,Object>();
				hashMap4Vaules.put("list_value", attr_obj.getList_value());
				hashMap4Vaules.put("attr_id", attr_obj.getId());
				attrMapper.insert_values(hashMap4Vaules);
			}
			
		}
	}

	

}
