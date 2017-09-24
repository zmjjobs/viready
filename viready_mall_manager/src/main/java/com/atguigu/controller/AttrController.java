package com.atguigu.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.MODEL_OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.service.AttrServiceImp;
import com.atguigu.service.SkuServiceImp;

/**
 * @description 属性控制器
 * @author 朱梦君
 * @datatime 2017年8月14日 下午11:31:09 
 * @version v1
 */
@Controller
public class AttrController {
	
	@Autowired
	private AttrServiceImp attrServiceImp;
	
	@RequestMapping("goto_attr")
	public String goto_spu(){
		return "manager_attr";
	}
	
	
	/**
	 * 根据二级分类的ID查询分类属性的集合（包含将二级分类id和二级分类名称放到域中）
	 * @param class_2_id
	 * @param class_2_name
	 * @param map
	 * @return
	 */
	@RequestMapping("get_attr_by_class_2")
	public String get_attr_by_class_2(int class_2_id, String class_2_name,ModelMap map){
		List<OBJECT_T_MALL_ATTR> list_attr = attrServiceImp.get_attr_by_class_2(class_2_id);
		map.put("list_attr", list_attr);
		map.put("class_2_id", class_2_id);
		map.put("class_2_name", class_2_name);
		return "manager_attr_list_inner";
	}
	
	
	/**
	 * manager_attr.jsp中的datagrid组件中需要将list_attr以json传入
	 * @param class_2_id
	 * @param class_2_name
	 * @param map
	 * @return
	 */
	@RequestMapping("get_attr_by_class_2_json")
	@ResponseBody
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_json(int class_2_id, String class_2_name){
		List<OBJECT_T_MALL_ATTR> list_attr = attrServiceImp.get_attr_by_class_2(class_2_id);
		return list_attr;
	}
	
	/**
	 * 去往添加分类属性的页面
	 * @param success
	 * @param class_2_id
	 * @param class_2_name
	 * @param map
	 * @return
	 */
	@RequestMapping("goto_add_attr")
	public String goto_add_attr(String success,int class_2_id, String class_2_name,ModelMap map){
		map.put("class_2_id", class_2_id);
		map.put("class_2_name", class_2_name);
		map.put("success", success);
		return "manager_attr_add";
	}
	
	/**
	 * 添加分类属性集合
	 * @param class_2_id
	 * @param class_2_name
	 * @param list_attr
	 * @return
	 */
	@RequestMapping("save_attr")
	public ModelAndView save_attr(MODEL_OBJECT_T_MALL_ATTR list_attr, int class_2_id, String class_2_name){
		
		attrServiceImp.save_attr(list_attr.getList_attr(), class_2_id);
		ModelAndView modelAndView = new ModelAndView("redirect:/goto_add_attr.do");
		modelAndView.addObject("success","添加成功！");
		modelAndView.addObject("class_2_id", class_2_id);
		modelAndView.addObject("class_2_name", class_2_name);
		return modelAndView;
	}
}
