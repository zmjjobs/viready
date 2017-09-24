package com.atguigu.service;

import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月28日 上午10:58:59 
 * @version v1
 */
public interface OrderServiceInf {

	void save_order(OBJECT_T_MALL_ORDER order, T_MALL_USER_ACCOUNT login_user, T_MALL_ADDRESS address);

	void order_pay(OBJECT_T_MALL_ORDER order);

}
