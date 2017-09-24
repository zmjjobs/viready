package com.atguigu.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.atguigu.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_SHOPPINGCART;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.AddressServiceInf;
import com.atguigu.service.CartServiceInf;
import com.atguigu.service.OrderServiceInf;
import com.atguigu.util.Constant;

/**
 * 
 * 
 * @author Administrator
 *
 */
@Controller
@SessionAttributes("order")
public class OrderController {
	
	@Autowired
	private OrderServiceInf orderServiceInf;
	
	@Autowired
	private CartServiceInf cartServiceInf;
	
	@Autowired
	private AddressServiceInf addressServiceInf;
	
	
	@RequestMapping("sale_pay_success")
	public String sale_pay_success(){
		//跳转到支付服务，这里待定未完成
		return "sale_pay_success";
		
	}
	
	@RequestMapping("order_pay")
	public String order_pay(){
		//跳转到支付服务，这里待定未完成
		return "redirect:/sale_pay_success.htm";
		
	}
	
	/**
	 * 跳转到收银台页面
	 * @return
	 */
	@RequestMapping("sale_pay")
	public String sale_pay(@ModelAttribute("order") OBJECT_T_MALL_ORDER order){
		orderServiceInf.order_pay(order);
		return "sale_pay";
	}
	
	/**
	 * 提交订单，重定向到支付接口，订单信息保存到数据库，将订单洗洗脑持久化
	 * @param address_id 页面选择地址时，会传入此地址ID
	 * @param order 通过@SessionAttributes来获取
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("save_order")
	public String save_order(int address_id,@ModelAttribute("order") OBJECT_T_MALL_ORDER order,HttpSession session,ModelMap map){
		T_MALL_ADDRESS address = addressServiceInf.get_addresses_by_id(address_id);
		T_MALL_USER_ACCOUNT login_user = (T_MALL_USER_ACCOUNT) session.getAttribute(Constant.LOGIN_USER);
		
		//调用提交订单的业务层，将订单信息持久化
		orderServiceInf.save_order(order,login_user,address);
		
		//清理session中的订单对象
		session.setAttribute(Constant.LIST_CART_AT_SESSION, cartServiceInf.get_list_cart_by_user_id(login_user.getId()));
		return "redirect:/sale_pay.do";
		
	}
	
	/**
	 * 去往订单确认页面
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("goto_order")
	public String goto_order(HttpSession session,ModelMap map){
		List<T_MALL_SHOPPINGCART> list_cart_at_session = new ArrayList<>();
		list_cart_at_session = (List<T_MALL_SHOPPINGCART>)session.getAttribute(Constant.LIST_CART_AT_SESSION);
		T_MALL_USER_ACCOUNT login_user = (T_MALL_USER_ACCOUNT) session.getAttribute(Constant.LOGIN_USER);
		int user_id = login_user.getId();
		List<T_MALL_ADDRESS> list_address = addressServiceInf.get_addresses_by_user_id(login_user);
		//总金额
		BigDecimal zje = new BigDecimal("0");
		
		//用于存放拆分好的每个物流详情
		List<OBJECT_T_MALL_FLOW> list_flow = new ArrayList<>();
		
		/*            根据库存地址进行拆单开始                 */
		//先获得购物车中有多少个商品地址(去重)
		Set<String> set_kcdzh = new HashSet<>();  
		int size = list_cart_at_session.size();
		for (int i = 0; i < size; i++) {
			set_kcdzh.add(list_cart_at_session.get(i).getKcdzh());
		}
		
		//循环库存地址，按照库存地址生成物流单号
		Iterator<String> iterator = set_kcdzh.iterator();
		while(iterator.hasNext()){
			//一个库存地址
			String kcdzh = iterator.next();
			
			//一个物流（对应一个库存地址）
			OBJECT_T_MALL_FLOW flow = new OBJECT_T_MALL_FLOW();
			
			//存放订单信息的包裹
			List<T_MALL_ORDER_INFO> list_order_info = new ArrayList<>();
			
			//开始分拣商品
			for (int i = 0; i < size; i++) {
				T_MALL_SHOPPINGCART shoppingcart = list_cart_at_session.get(i);
				//如果此商品的库存地址是这个划分地址并且已经选中，则将其写入订单信息，然后放入此包裹中
				if(shoppingcart.getKcdzh().equals(kcdzh) && shoppingcart.getShfxzh().equals("1")){
					zje = zje.add(new BigDecimal(shoppingcart.getHj()+""));
					
					T_MALL_ORDER_INFO order_info = new T_MALL_ORDER_INFO();
					order_info.setGwch_id(shoppingcart.getId());
					order_info.setShp_tp(shoppingcart.getShp_tp());
					order_info.setSku_id(shoppingcart.getSku_id());
					order_info.setSku_jg(shoppingcart.getSku_jg());
					order_info.setSku_kcdzh(kcdzh);
					order_info.setSku_mch(shoppingcart.getSku_mch());
					order_info.setSku_shl(shoppingcart.getTjshl());
					list_order_info.add(order_info);
				}
			}
			
			//将包裹分给某个物流
			flow.setList_order_info(list_order_info);
			flow.setPsfsh(Constant.PSFSH);
			flow.setYh_id(user_id);
			flow.setMqdd(kcdzh + Constant.MQDD_0);
			
			//集中个人物流详情
			list_flow.add(flow);
		}
		/*            根据库存地址进行拆单结束                */
		
		//这个对象用于将拆分好的各个物流记录在一个个人订单上
		OBJECT_T_MALL_ORDER order = new OBJECT_T_MALL_ORDER();
		order.setList_flow(list_flow);
		order.setYh_id(user_id);
		order.setZje(zje);
		order.setJdh(1);
		
		//这里使用order与SessionAttributes中的order一致，
		//这样就可以保证当request中有值时，自动将在session中赋值 
		//在session中放一个占位order，用于提交订单后的确认订单和支付
		map.put("order", order);
		map.put("list_address", list_address);
		return "sale_order";
	}
}
