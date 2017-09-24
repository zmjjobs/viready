package com.atguigu.bean;

import java.util.List;

/**
 * @description 当传参为List<T_MALL_SKU_ATTR_VALUE>时，将其封装在此类中作为此类的属性
 * @author 朱梦君
 * @datatime 2017年8月17日 下午3:15:25 
 * @version v1
 */
public class MODEL_T_MALL_SKU_ATTR_VALUE {
	List<T_MALL_SKU_ATTR_VALUE> list_av;

	public List<T_MALL_SKU_ATTR_VALUE> getList_av() {
		return list_av;
	}

	public void setList_av(List<T_MALL_SKU_ATTR_VALUE> list_av) {
		this.list_av = list_av;
	}
	
	
}
