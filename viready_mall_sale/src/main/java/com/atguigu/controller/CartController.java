package com.atguigu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.T_MALL_SHOPPINGCART;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.CartServiceInf;
import com.atguigu.util.Constant;
import com.atguigu.util.MyJsonUtil;

/**
 * @description 购物车控制器
 * @author 朱梦君
 * @datatime 2017年8月22日 下午7:28:55 
 * @version v1
 */
@Controller
public class CartController {
	
	@Autowired
	private CartServiceInf cartServiceInf;
	
	/**
	 * 点击购物车按钮，跳转到购物车页
	 * @return
	 */
	@RequestMapping("goto_cart_list")
	public String goto_cart_list(ModelMap map,HttpSession session,@CookieValue(value=Constant.LIST_CART_AT_COOKIE,required=false)String list_cart_at_cookie_param){
		T_MALL_USER_ACCOUNT login_user = (T_MALL_USER_ACCOUNT) session.getAttribute(Constant.LOGIN_USER);
		List<T_MALL_SHOPPINGCART> list_cart = new ArrayList<>();
		if(login_user == null){
			list_cart = MyJsonUtil.json_to_list(list_cart_at_cookie_param, T_MALL_SHOPPINGCART.class);
		}else{
			list_cart = (List<T_MALL_SHOPPINGCART>) session.getAttribute(Constant.LIST_CART_AT_SESSION);
		}
		int size = list_cart.size();
		//求所有的hj之和，也就是总计
		BigDecimal sum = new BigDecimal("0");
		for (int i = 0; i < size; i++) {
			sum = sum.add(list_cart.get(i).getHj());
		}
		map.put("sum", sum);
		map.put("list_cart", list_cart);
		return "sale_cart_list";
	}
	
	/**
	 * 跳转到购物车列表页（自己跳转自己局部刷新）
	 * @return
	 */
	@RequestMapping("change_cart")
	public String change_cart(ModelMap map,String shfxzh,int tjshl,int sku_id){
		cartServiceInf.change_cart_sku_id(shfxzh,tjshl,sku_id);
		map.put("test", "cart_list");
		return "redirect:/sale_cart_list_inner.do";
	}
	
	@RequestMapping("delete_carts_by_sku_id")
	public String delete_carts_by_sku_id(String sku_id){
		cartServiceInf.delete_carts_by_sku_id(sku_id);
		return "redirect:/goto_cart_list.do";
	}
	
	@RequestMapping("get_miniCart")
	public String get_miniCart(ModelMap map,HttpSession session,@CookieValue(value=Constant.LIST_CART_AT_COOKIE,required=false)String list_cart_at_cookie_param){
		T_MALL_USER_ACCOUNT login_user = (T_MALL_USER_ACCOUNT) session.getAttribute(Constant.LOGIN_USER);
		List<T_MALL_SHOPPINGCART> list_cart = new ArrayList<>();
		if(login_user == null && list_cart_at_cookie_param != null){
			list_cart = MyJsonUtil.json_to_list(list_cart_at_cookie_param, T_MALL_SHOPPINGCART.class);
		}else{
			list_cart = (List<T_MALL_SHOPPINGCART>) session.getAttribute(Constant.LIST_CART_AT_SESSION);
		}
		int size = 0;
		int shpzshl = 0;//商品数量总和
		//求所有的hj之和，也就是总计
		BigDecimal sum = new BigDecimal("0");
		if(list_cart != null){
			size = list_cart.size();
			for (int i = 0; i < size; i++) {
				T_MALL_SHOPPINGCART shoppingcart = list_cart.get(i);
				sum = sum.add(shoppingcart.getHj());
				shpzshl += shoppingcart.getTjshl(); 
			}
		}
		map.put("sum", sum);
		map.put("shpzshl", shpzshl);
		map.put("list_cart", list_cart);
		return "sale_miniCart_cart_list_inner";
	}
	
	/**
	 * 添加购物车成功要跳转的页面
	 * @return
	 */
	@RequestMapping("cart_success")
	public String cart_success(){
		return "sale_cart_success";
	}
	
	/**
	 * 添加购物车对象
	 * @param list_cart_at_cookie_param 在调用此方法前，已经存在在cookie中的json串
	 * @param cart 要添加的购物车对象
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("add_cart")
	public String add_cart(@CookieValue(value=Constant.LIST_CART_AT_COOKIE,required=false)String list_cart_at_cookie_param,T_MALL_SHOPPINGCART cart,HttpSession session,HttpServletRequest request,HttpServletResponse response){
		
		T_MALL_USER_ACCOUNT login_user = (T_MALL_USER_ACCOUNT) session.getAttribute(Constant.LOGIN_USER);
		
		List<T_MALL_SHOPPINGCART> list_cart = new ArrayList<T_MALL_SHOPPINGCART>();
		if(login_user == null){//用户未登录，操作浏览器cookie
			
			//如果cookie无数据，添加cookie的值
			if(StringUtils.isBlank(list_cart_at_cookie_param)){
				list_cart.add(cart);
				
			}else{
				//cookie有数据
				list_cart = MyJsonUtil.json_to_list(list_cart_at_cookie_param, T_MALL_SHOPPINGCART.class);
				boolean flag = cartServiceInf.is_new_cart(list_cart, cart);
				if(flag){//新数据
					list_cart.add(cart);
				}else{//老数据
					for (int i = 0; i < list_cart.size(); i++) {
						//如果查到其中一个的sku_id与上传购物车的sku_id相同，则更新数量和合计
						T_MALL_SHOPPINGCART shoppingcart = list_cart.get(i);
						if(shoppingcart.getSku_id() == cart.getSku_id()){
							shoppingcart.setTjshl(shoppingcart.getTjshl()+cart.getTjshl());
							shoppingcart.setHj(shoppingcart.getHj().add(cart.getHj()));
							break;
						}
					}
				}
			}
			Cookie cookie = new Cookie(Constant.LIST_CART_AT_COOKIE, MyJsonUtil.list_to_json(list_cart));
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
			
		}else{//用户已登录，操作数据库db
			list_cart = (List<T_MALL_SHOPPINGCART>) session.getAttribute(Constant.LIST_CART_AT_SESSION);
			cart.setYh_id(login_user.getId());
			
			//如果db和session中没有购物车数据
			if(list_cart == null || list_cart.size() == 0){
				list_cart = new ArrayList<>();
				cartServiceInf.add_cart(cart);
				list_cart.add(cart);
				
				//向session中插入一条购物车数据
				session.setAttribute(Constant.LIST_CART_AT_SESSION, list_cart);
			}else{
				boolean flag = cartServiceInf.is_new_cart(list_cart, cart);
				
				if(flag){//如果true,直接更新session中的购物车数据
					cartServiceInf.add_cart(cart);
					list_cart.add(cart);
					
				}else{//如果false，则更新添加数量和购物车合计
					cartServiceInf.update_tjshl_and_hj(cart, list_cart);
				}
			}
		}
		return "redirect:/cart_success.do";
		
	}

	
	/*private void update_tjshl_and_hj(T_MALL_SHOPPINGCART cart, List<T_MALL_SHOPPINGCART> list_cart_at_session) {
		int size = list_cart_at_session.size();
		for (int i = 0; i < size; i++) {
			T_MALL_SHOPPINGCART shoppingcart = list_cart_at_session.get(i);
			//如果查到其中一个的sku_id与上传购物车的sku_id相同，则更新数量和合计
			if(shoppingcart.getSku_id() == cart.getSku_id()){
				shoppingcart.setTjshl(shoppingcart.getTjshl()+cart.getTjshl());
				shoppingcart.setHj(shoppingcart.getHj().add(cart.getHj()));
				cartServiceInf.update_cart(shoppingcart);
				break;
			}
		}
	}*/

}
