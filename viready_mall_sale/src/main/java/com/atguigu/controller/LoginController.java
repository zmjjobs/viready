package com.atguigu.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
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
import com.atguigu.mapper.CartMapper;
import com.atguigu.mapper.TestMapper;
import com.atguigu.service.CartServiceInf;
import com.atguigu.service.UserServiceInf;
import com.atguigu.util.Constant;
import com.atguigu.util.MyJsonUtil;
import com.atguigu.util.MyWSFactory;

/**
 * @description 登录控制器
 * @author 朱梦君
 * @datatime 2017年8月22日 下午7:30:55 
 * @version v1
 */
@Controller
public class LoginController {
	@Autowired
	private CartServiceInf cartServiceInf;
	
	@Autowired
	private TestMapper testMapper;
	
	@Autowired
	private UserServiceInf userServiceInf;
	
	@RequestMapping("goto_login")
	public String goto_login(){
		return "sale_login";
	}
	
	/**
	 * 注销账户，并回到首页
	 * @param session
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/index.do";
	}
	
	@RequestMapping("login")
	public String login(String user_type,@CookieValue(value=Constant.LIST_CART_AT_COOKIE,required=false)String list_cart_at_cookie_param,HttpServletResponse response,HttpSession session,T_MALL_USER_ACCOUNT user){
		T_MALL_USER_ACCOUNT login_user = null;
		if("user".equals(user_type)){
			login_user = userServiceInf.login(user);
		}else if("testuser".equals(user_type)){
			login_user = userServiceInf.loginTest(user);
		}
		if(login_user == null){
			return "sale_login";
		}
		session.setAttribute(Constant.LOGIN_USER, login_user);
		merge_cart(response,session,list_cart_at_cookie_param);
		//向浏览器客户端写入一个Cookie
		Cookie cookie = new Cookie("yh_mch", login_user.getYh_mch());
		cookie.setMaxAge(60*60);
		//cookie.setPath(uri);
		
		String encode = "";
		try {
			encode = URLEncoder.encode("周瑞福", "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cookie cookie2 = new Cookie("test", encode);
		cookie2.setMaxAge(60*60);
		response.addCookie(cookie);
		response.addCookie(cookie2);
		return "redirect:/index.do";
	}

	/**
	 * 用户登录后，合并cookie中的购物车信息，将其添加或更新到数据库，并同步到session
	 * @param response
	 * @param session
	 * @param list_cart_at_cookie_param
	 */
	private void merge_cart(HttpServletResponse response, HttpSession session, String list_cart_at_cookie_param) {
		T_MALL_USER_ACCOUNT login_user = (T_MALL_USER_ACCOUNT)(session.getAttribute(Constant.LOGIN_USER));
		List<T_MALL_SHOPPINGCART> list_cart_at_db = cartServiceInf.get_list_cart_by_user_id(login_user.getId());
		if(list_cart_at_db == null){//如果数据库中没有购物车数据
			
			if(StringUtils.isBlank(list_cart_at_cookie_param)){//如果cookie中也没有购物车信息
				
			}else{//如果cookie中有购物车信息，将信息添加到db中
				List<T_MALL_SHOPPINGCART> list_cart_at_cookie = MyJsonUtil.json_to_list(list_cart_at_cookie_param, T_MALL_SHOPPINGCART.class);
				int size = list_cart_at_cookie.size();
				for (int i = 0; i < size; i++) {
					list_cart_at_cookie.get(i).setYh_id(login_user.getId());
					cartServiceInf.add_cart(list_cart_at_cookie.get(i));
				}
			}
			
		}else{//如果数据库中有购物车数据
			if(StringUtils.isBlank(list_cart_at_cookie_param)){//如果cookie中没有购物车信息
				
			}else{//如果cookie中有购物车信息，将信息添加或更新到db中
				List<T_MALL_SHOPPINGCART> list_cart_at_cookie = MyJsonUtil.json_to_list(list_cart_at_cookie_param, T_MALL_SHOPPINGCART.class);
				int size_cookie = list_cart_at_cookie.size();
				
				for (int i = 0; i < size_cookie; i++) {
					T_MALL_SHOPPINGCART cart_at_cookie = list_cart_at_cookie.get(i);
					boolean flag = cartServiceInf.is_new_cart(list_cart_at_db, cart_at_cookie);
					if(flag){
						list_cart_at_cookie.get(i).setYh_id(login_user.getId());
						cartServiceInf.add_cart(cart_at_cookie);
					}else{
						cartServiceInf.update_tjshl_and_hj(cart_at_cookie,list_cart_at_db);
					}
				}
			}
		}
		
		session.setAttribute(Constant.LIST_CART_AT_SESSION, cartServiceInf.get_list_cart_by_user_id(login_user.getId()));
		Cookie cookie3 = new Cookie(Constant.LIST_CART_AT_COOKIE, "");
		cookie3.setMaxAge(60 * 60);
		response.addCookie(cookie3);
	}
	
}
