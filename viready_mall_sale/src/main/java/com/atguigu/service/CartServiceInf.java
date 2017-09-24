package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.T_MALL_SHOPPINGCART;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月22日 下午9:24:58 
 * @version v1
 */
public interface CartServiceInf {

	void add_cart(T_MALL_SHOPPINGCART cart);

	void update_cart(T_MALL_SHOPPINGCART shoppingcart);

	List<T_MALL_SHOPPINGCART> get_list_cart_by_user_id(int yh_id);

	/**
	 * 判断是否是新的购物车对象，也就是检查sku_id是否相同
	 * @param list_cart
	 * @param cart
	 * @return
	 */
	boolean is_new_cart(List<T_MALL_SHOPPINGCART> list_cart, T_MALL_SHOPPINGCART cart);

	
	/**
	 * 情况一：用户登录后，当要插入的购物车对象已经存在在session中时，对对应的购物车对象在session中进行添加数量和合计的更新
	 * 情况二：用户登录后，当cookie中有数据，并且此购物车对象与db中的某个对象一样时，对对应的购物车对象在db中进行添加数量和合计的更新
	 * @param cart 要添加的购物车对象
	 * @param list_cart
	 */
	void update_tjshl_and_hj(T_MALL_SHOPPINGCART cart, List<T_MALL_SHOPPINGCART> list_cart);


	void change_cart_sku_id(String shfxzh, int tjshl, int sku_id);

	void delete_carts_by_sku_id(String sku_id);

}
