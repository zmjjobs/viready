package com.atguigu.bean;

import java.util.List;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月27日 下午9:54:48 
 * @version v1
 */
public class OBJECT_T_MALL_FLOW extends T_MALL_FLOW {
	private List<T_MALL_ORDER_INFO> list_order_info;

	public List<T_MALL_ORDER_INFO> getList_order_info() {
		return list_order_info;
	}

	public void setList_order_info(List<T_MALL_ORDER_INFO> list_order_info) {
		this.list_order_info = list_order_info;
	}
	
	
}
