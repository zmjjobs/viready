package com.atguigu.bean;

import java.util.List;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月15日 下午5:29:07 
 * @version v1
 */
public class MODEL_OBJECT_T_MALL_ATTR {
	//用于传参时，将此定义为对象的属性，使用OGNL
	private List<OBJECT_T_MALL_ATTR> list_attr;

	public List<OBJECT_T_MALL_ATTR> getList_attr() {
		return list_attr;
	}

	public void setList_attr(List<OBJECT_T_MALL_ATTR> list_attr) {
		this.list_attr = list_attr;
	}
	 
}
