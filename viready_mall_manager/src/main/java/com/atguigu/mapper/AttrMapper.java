package com.atguigu.mapper;
import java.util.HashMap;
import java.util.List;
/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月15日 上午12:30:33 
 * @version v1
 */

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_CLASS_1;
public interface AttrMapper {
	List<T_MALL_CLASS_1> select_class_1();
	
	/**
	 * 通过二级分类ID查询所有属性集合以及每个属性对应的属性值集合
	 * @param class_2_id
	 * @return
	 */
	List<OBJECT_T_MALL_ATTR> select_attr_by_class_2(int class_2_id);
	
	void insert_attr(HashMap<String, Object> hashMap4Attr);
	
	void insert_values(HashMap<String, Object> hashMap4Values);
}
