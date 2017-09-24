package com.atguigu.util;

import java.util.Date;

/**
 * 自定义常量类
 * @author zhumengjun
 *
 */
public class Constant {
	
	
	
	/**
	 * 代表登录的用户
	 */
	public final static String LOGIN_USER = "login_user";
	
	/**
	 * 新建用户的默认初始密码
	 */
	public static final String USER_DEFAULT_PASSWORD = "123456";
	
	/**
	 * 暗号，协议密码串，用于加密密码的一部分
	 */
	public static final String CIPHER_FOR_PASSWORD = "zmj8888ZMJ";
	
	/**
	 * 存放在db中的购物车集合对应的KEY
	 */
	public static final String LIST_CART_AT_DB = "list_cart_at_db";
	
	/**
	 * 存放在session中的购物车集合对应的KEY
	 */
	public static final String LIST_CART_AT_SESSION = "list_cart_at_session";
	
	/**
	 * 存放在cookie中的购物车集合对应的KEY
	 */
	public static final String LIST_CART_AT_COOKIE = "list_cart_at_cookie";

	/**
	 * 配送方式
	 */
	public static final String PSFSH = "熊猫快递";

	
	/**
	 * Flow表--目前地点_0
	 */
	public static final String MQDD_0 = "商品未出库";

	
	
	
}
