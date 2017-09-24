package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.service.AttrServiceImp;
import com.atguigu.service.SkuServiceImp;

/**
 * @description sku控制器
 * @author 朱梦君
 * @datatime 2017年8月16日 下午5:27:40 
 * @version v1
 */
@Controller
public class SkuController {

	@Autowired
	private SkuServiceImp skuServiceImp;
	
	@Autowired
	private AttrServiceImp attrServiceImp;
	
	
	/**
	 * 保存SKU
	 * @param list_av 所有属性，以及每个属性对应的属性值和属性值名称
	 * @param spu
	 * @return
	 */
	@RequestMapping("save_sku")
	public String save_sku(MODEL_T_MALL_SKU_ATTR_VALUE list_av,OBJECT_T_MALL_PRODUCT spu,T_MALL_SKU sku){
		
		skuServiceImp.save_sku(spu,list_av,sku);
		return "redirect:/goto_sku.do";
		
	}
	
	@RequestMapping("goto_sku")
	public String goto_sku(){
		return "manager_sku";
				
	}
	
	/**
	 * 根据class_2可以求的属性对象集合,再根据属性名ID找到attr_list集合
	 * @return
	 */
	@RequestMapping("get_sku_attr_list_by_class_2")
	public String get_sku_attr_list_by_class_2(int class_2_id,ModelMap map){
		//根据class_2获取属性对象集合
		List<OBJECT_T_MALL_ATTR> list_attr = attrServiceImp.get_attr_by_class_2(class_2_id);
		map.put("list_attr", list_attr);
		return "manager_sku_attr_inner";
	}
	
	
	/**
	 * 
	 * @param pp_id 品牌ID
	 * @param class_1_id
	 * @param class_2_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get_spu_by_tm")
	public List<T_MALL_PRODUCT> get_spu_by_tm(int pp_id,int class_1_id,int class_2_id){
		List<T_MALL_PRODUCT> list_spu = skuServiceImp.get_spu_by_tm(pp_id,class_1_id,class_2_id);
		return list_spu;
	}
}
