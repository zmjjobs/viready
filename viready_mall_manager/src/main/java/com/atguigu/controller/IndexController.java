package com.atguigu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
	
	/**
	 * 去往后台管理的首页，将url和title传过去
	 * @param url
	 * @param title
	 * @param map
	 * @return
	 */
	@RequestMapping("index")
	public String index(String url,String title,ModelMap map){
		map.put("url", url);
		map.put("title", title);
		return "manager_index";
	}
	
}
