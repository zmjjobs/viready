package com.atguigu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.OrderMapper;
import com.atguigu.util.Constant;
import com.atguigu.util.MyDateUtil;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月28日 上午10:59:33 
 * @version v1
 */
@Service
public class OrderServiceImp implements OrderServiceInf {

	
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public void save_order(OBJECT_T_MALL_ORDER order, T_MALL_USER_ACCOUNT login_user, T_MALL_ADDRESS address) {
		List<Integer> list_cart_id = new ArrayList<>();
		int user_id = login_user.getId();
		String dzh_mch = address.getDzh_mch();
		//保存订单，自动生成订单ID
		order.setDzh_id(address.getId());
		order.setShhr(address.getShjr());
		order.setDzh_mch(dzh_mch);
		int order_id = orderMapper.insert_order(order);
		
		/*  根据订单号，生成物流信息包裹，生成物流ID，不生成物流单号   */
		//物流集合
		List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
		int size_list_flow = list_flow.size();
		for (int i = 0; i < size_list_flow; i++) {
			OBJECT_T_MALL_FLOW flow = list_flow.get(i);
			flow.setPsfsh(Constant.PSFSH);
			flow.setYh_id(user_id);
			flow.setDd_id(order_id);
			flow.setMqdd(Constant.MQDD_0);
			flow.setMdd(dzh_mch);
			int flow_id = orderMapper.insert_flow(flow);
			
			//根据物流ID，批量插入订单信息表
			List<T_MALL_ORDER_INFO> order_infos = flow.getList_order_info();
			int size_order_infos = order_infos.size();
			for (int j = 0; j < size_order_infos; j++) {
				T_MALL_ORDER_INFO order_info = order_infos.get(j);
				order_info.setFlow_id(flow_id);
				order_info.setDd_id(order_id);
				
				//收集购物车ID用于删除
				list_cart_id.add(order_info.getGwch_id());
			}
			orderMapper.insert_order_infos(order_infos);
		}
		
		//删除已经提交订单的购物车信息
		orderMapper.delete_shoppingCarts(list_cart_id);
	}

	@Override
	public void order_pay(OBJECT_T_MALL_ORDER order) {
		//修改订单状态
		//预计送达时间是三天之后
		order.setYjsdshj(MyDateUtil.getDate4Flow(3));
		order.setJdh(2);
		orderMapper.update_order(order);
		
		//修改物流信息(这里暂时写死，应该是调用物流接口)
		List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
		int size = list_flow.size();
		for (int i = 0; i < size; i++) {
			OBJECT_T_MALL_FLOW flow = list_flow.get(i);
			flow.setPsfsh("熊猫快递，无限速度！");
			flow.setPsshj(MyDateUtil.getDate4Flow(1));
			flow.setYwy("(๑ŐдŐ)b");
			flow.setLxfsh("1231233333");
			orderMapper.update_flow(flow);
			
			//根据sku_id修改库存和销量信息
			List<T_MALL_ORDER_INFO> list_order_info = flow.getList_order_info();
			int size_list_order_info = list_order_info.size();
			for (int j = 0; j < size_list_order_info; j++) {
				T_MALL_ORDER_INFO order_info = list_order_info.get(i);
				//确认库存是否足够
				boolean flag = if_can_buy(order_info);
				if(flag){
					orderMapper.update_kc(order_info);
				}
			}
			
		}
		
	}

	private boolean if_can_buy(T_MALL_ORDER_INFO order_info) {
		//根据sku_id查询库存
		int kc = orderMapper.select_kc(order_info);
		if(kc >= order_info.getSku_shl()){
			return true;
		}
		return false;
	}

}
