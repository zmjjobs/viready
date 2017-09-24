package com.atguigu.bean;

import java.math.BigDecimal;
import java.util.Date;

public class T_MALL_SKU {

	private int id;
	private int shp_id;
	
	/**
	 * 库存数量
	 */
	private int kc;
	
	/**
	 * 库存价格
	 */
	private BigDecimal jg;
	
	/**
	 * 创建时间
	 */
	private Date chjshj;
	/**
	 * 库存名称
	 */
	private String sku_mch;
	/**
	 * 库存地址
	 */
	private String kcdzh;



	public String getKcdzh() {
		return kcdzh;
	}

	public void setKcdzh(String kcdzh) {
		this.kcdzh = kcdzh;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShp_id() {
		return shp_id;
	}

	public void setShp_id(int shp_id) {
		this.shp_id = shp_id;
	}

	public int getKc() {
		return kc;
	}

	public void setKc(int kc) {
		this.kc = kc;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}

	public String getSku_mch() {
		return sku_mch;
	}

	public void setSku_mch(String sku_mch) {
		this.sku_mch = sku_mch;
	}

	public BigDecimal getJg() {
		return jg;
	}

	public void setJg(BigDecimal jg) {
		this.jg = jg;
	}

}
