package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_SHOPPINGCART;
import com.atguigu.mapper.CartMapper;

/**
 * @description 购物车业务实现类 
 * @author 朱梦君
 * @datatime 2017年8月22日 下午9:25:20 
 * @version v1
 */
@Service
public class CartServiceImp implements CartServiceInf {
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public void add_cart(T_MALL_SHOPPINGCART cart) {
		cartMapper.insert_cart(cart);
	}
	
	@Override
	public void update_cart(T_MALL_SHOPPINGCART shoppingcart) {
		cartMapper.update_cart(shoppingcart);
		
	}
	
	@Override
	public List<T_MALL_SHOPPINGCART> get_list_cart_by_user_id(int yh_id) {
		return cartMapper.select_list_cart_by_user_id(yh_id);
	}
	
	
	@Override
	public boolean is_new_cart(List<T_MALL_SHOPPINGCART> list_cart, T_MALL_SHOPPINGCART cart) {
		int size = list_cart.size();
		for (int i = 0; i < size; i++) {
			if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void update_tjshl_and_hj(T_MALL_SHOPPINGCART cart, List<T_MALL_SHOPPINGCART> list_cart) {
		int size = list_cart.size();
		for (int i = 0; i < size; i++) {
			T_MALL_SHOPPINGCART shoppingcart = list_cart.get(i);
			//如果查到其中一个的sku_id与上传购物车的sku_id相同，则更新数量和合计
			if(shoppingcart.getSku_id() == cart.getSku_id()){
				shoppingcart.setTjshl(shoppingcart.getTjshl()+cart.getTjshl());
				shoppingcart.setHj(shoppingcart.getHj().add(cart.getHj()));
				update_cart(shoppingcart);
				break;
			}
		}
	}

	@Override
	public void change_cart_sku_id(String shfxzh, int tjshl, int sku_id) {
		cartMapper.update_cart_by_sku_id(shfxzh,tjshl,sku_id);
	}
	@Override
	public void delete_carts_by_sku_id(String sku_id) {
		cartMapper.delete_carts_by_sku_id(sku_id);
		
	}
}
