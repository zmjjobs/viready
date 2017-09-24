package com.atguigu.bean;

import java.util.List;

/**
 * @description 一个订单对应多个物流信息
 * @author 朱梦君
 * @datatime 2017年8月27日 下午10:58:09 
 * @version v1
 */
public class OBJECT_T_MALL_ORDER extends T_MALL_ORDER {
		private List<OBJECT_T_MALL_FLOW> list_flow;

		public List<OBJECT_T_MALL_FLOW> getList_flow() {
			return list_flow;
		}

		public void setList_flow(List<OBJECT_T_MALL_FLOW> list_flow) {
			this.list_flow = list_flow;
		}
		
		
}
