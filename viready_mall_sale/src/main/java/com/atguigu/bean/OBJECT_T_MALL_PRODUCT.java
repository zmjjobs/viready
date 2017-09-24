package com.atguigu.bean;
/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月17日 下午5:06:25 
 * @version v1
 */
public class OBJECT_T_MALL_PRODUCT extends T_MALL_PRODUCT {
	/**
	 * spu_id与t_mall_product表中的id是一一对应的，
	 * t_mall_product表中不存在此字段，
	 * 这里是为了jsp页面在传参时写成spu_id封装在此对象中
	 * 
	 */
	private int spu_id;

	public int getSpu_id() {
		return spu_id;
	}

	public void setSpu_id(int spu_id) {
		this.spu_id = spu_id;
	}
	
}
