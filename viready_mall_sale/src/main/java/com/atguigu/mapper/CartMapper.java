package com.atguigu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.T_MALL_SHOPPINGCART;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

/**
 * @description 购物车相关数据访问接口
 * @author 朱梦君
 * @datatime 2017年8月22日 下午7:29:55 
 * @version v1
 */
public interface CartMapper {

	T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user);

	void insert_cart(T_MALL_SHOPPINGCART cart);

	void update_cart(T_MALL_SHOPPINGCART shoppingcart);

	List<T_MALL_SHOPPINGCART> select_list_cart_by_user_id(int yh_id);
	
	void update_cart_by_sku_id(@Param("shfxzh")String shfxzh, @Param("tjshl")int tjshl, @Param("sku_id")int sku_id);

	void delete_carts_by_sku_id(String sku_id);

}

