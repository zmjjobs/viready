package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月28日 上午11:01:50 
 * @version v1
 */
public interface OrderMapper {
	int insert_order(OBJECT_T_MALL_ORDER order);

	int insert_flow(OBJECT_T_MALL_FLOW flow);

	void insert_order_infos(List<T_MALL_ORDER_INFO> order_infos);

	void delete_shoppingCarts(List<Integer> list_cart_id);

	void update_order(OBJECT_T_MALL_ORDER order);

	void update_flow(OBJECT_T_MALL_FLOW flow);

	void update_kc(T_MALL_ORDER_INFO t_MALL_ORDER_INFO);

	int select_kc(T_MALL_ORDER_INFO order_info);
}
