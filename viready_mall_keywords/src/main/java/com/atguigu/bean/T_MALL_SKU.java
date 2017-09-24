package com.atguigu.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class T_MALL_SKU {

	@Field("id")
	private int id;
	@Field("shp_id")
	private int shp_id;
	@Field("kc")
	private int kc;
	@Field("jg")
	private double jg;
	@Field("chjshj")
	private Date chjshj;
	@Field("sku_mch")
	private String sku_mch;
	@Field("kcdzh")
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

	public double getJg() {
		return jg;
	}

	public void setJg(double jg) {
		this.jg = jg;
	}

}
