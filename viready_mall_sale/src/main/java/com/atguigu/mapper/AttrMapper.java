package com.atguigu.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_CLASS_1;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月20日 上午12:41:53 
 * @version v1
 */
public interface AttrMapper {
	List<OBJECT_T_MALL_ATTR> select_attr_by_class_2(int class_2_id);
	void insert_attr(HashMap<String, Object> hashMap1);
	void insert_values(HashMap<String, Object> hashMap2);
}
