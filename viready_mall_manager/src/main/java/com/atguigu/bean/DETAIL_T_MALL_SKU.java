package com.atguigu.bean;

import java.util.List;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月21日 下午11:43:18 
 * @version v1
 */
public class DETAIL_T_MALL_SKU extends T_MALL_SKU{
	/**
	 * 商品介绍的信息，用spu表示
	 */
	private T_MALL_PRODUCT spu;
	
	/**
	 * 商品的商标信息
	 */
	private T_MALL_TRADE_MARK tm;
	
	/**
	 * 图片集合信息
	 */
	private List<T_MALL_PRODUCT_IMAGE> list_image;
	
	/**
	 * 属性名名称和属性值名称组合的列表
	 */
	private List<T_MALL_SKU_AV_NAME> list_av_name;

	public T_MALL_PRODUCT getSpu() {
		return spu;
	}

	public void setSpu(T_MALL_PRODUCT spu) {
		this.spu = spu;
	}

	public T_MALL_TRADE_MARK getTm() {
		return tm;
	}

	public void setTm(T_MALL_TRADE_MARK tm) {
		this.tm = tm;
	}

	public List<T_MALL_PRODUCT_IMAGE> getList_image() {
		return list_image;
	}

	public void setList_image(List<T_MALL_PRODUCT_IMAGE> list_image) {
		this.list_image = list_image;
	}

	public List<T_MALL_SKU_AV_NAME> getList_av_name() {
		return list_av_name;
	}

	public void setList_av_name(List<T_MALL_SKU_AV_NAME> list_av_name) {
		this.list_av_name = list_av_name;
	}
	
	
	
}
