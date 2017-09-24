package com.atguigu.bean;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 购物车
 * @author Administrator
 *
 */
public class T_MALL_SHOPPINGCART {
	private int id;
	private String sku_mch;
	private BigDecimal sku_jg;
	private int tjshl;
	private BigDecimal hj;
	private int yh_id;
	private int shp_id;
	private Date chjshj;
	private int sku_id;
	private String shp_tp;
	private String shfxzh = "1";

	private String kcdzh;

	
	
	public T_MALL_SHOPPINGCART() {
	}

	
	public T_MALL_SHOPPINGCART(String sku_mch, BigDecimal sku_jg) {
		this.sku_mch = sku_mch;
		this.sku_jg = sku_jg;
	}


	public T_MALL_SHOPPINGCART(int id, String sku_mch, BigDecimal sku_jg, int tjshl, BigDecimal hj, int yh_id,
			int shp_id, Date chjshj, int sku_id, String shp_tp, String shfxzh, String kcdzh) {
		super();
		this.id = id;
		this.sku_mch = sku_mch;
		this.sku_jg = sku_jg;
		this.tjshl = tjshl;
		this.hj = hj;
		this.yh_id = yh_id;
		this.shp_id = shp_id;
		this.chjshj = chjshj;
		this.sku_id = sku_id;
		this.shp_tp = shp_tp;
		this.shfxzh = shfxzh;
		this.kcdzh = kcdzh;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSku_mch() {
		return sku_mch;
	}


	public void setSku_mch(String sku_mch) {
		this.sku_mch = sku_mch;
	}


	public BigDecimal getSku_jg() {
		return sku_jg;
	}


	public void setSku_jg(BigDecimal sku_jg) {
		this.sku_jg = sku_jg;
	}


	public int getTjshl() {
		return tjshl;
	}


	public void setTjshl(int tjshl) {
		this.tjshl = tjshl;
	}


	public BigDecimal getHj() {
		return this.sku_jg.multiply(new BigDecimal(this.tjshl+""));
	}


	public void setHj(BigDecimal hj) {
		this.hj = hj;
	}


	public int getYh_id() {
		return yh_id;
	}


	public void setYh_id(int yh_id) {
		this.yh_id = yh_id;
	}


	public int getShp_id() {
		return shp_id;
	}


	public void setShp_id(int shp_id) {
		this.shp_id = shp_id;
	}


	public Date getChjshj() {
		return chjshj;
	}


	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}


	public int getSku_id() {
		return sku_id;
	}


	public void setSku_id(int sku_id) {
		this.sku_id = sku_id;
	}


	public String getShp_tp() {
		return shp_tp;
	}


	public void setShp_tp(String shp_tp) {
		this.shp_tp = shp_tp;
	}


	public String getShfxzh() {
		return shfxzh;
	}


	public void setShfxzh(String shfxzh) {
		this.shfxzh = shfxzh;
	}


	public String getKcdzh() {
		return kcdzh;
	}


	public void setKcdzh(String kcdzh) {
		this.kcdzh = kcdzh;
	}


	@Override
	public String toString() {
		return "T_MALL_SHOPPINGCART [id=" + id + ", sku_mch=" + sku_mch + ", sku_jg=" + sku_jg + ", tjshl=" + tjshl
				+ ", hj=" + hj + ", yh_id=" + yh_id + ", shp_id=" + shp_id + ", chjshj=" + chjshj + ", sku_id=" + sku_id
				+ ", shp_tp=" + shp_tp + ", shfxzh=" + shfxzh + ", kcdzh=" + kcdzh + "]";
	}


	

}
