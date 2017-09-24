package com.atguigu.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
	
	/**
	 * 服务器进行Cookie操作的方法
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("index_2")
	public String index_2(HttpServletRequest request,ModelMap map){
		//从浏览器获取Cookie，放入首页jsp页面的域中
		Cookie[] cookies = request.getCookies();
		String yh_mch = "";
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if(cookie.getName().equals("yh_mch")){
				yh_mch = cookie.getValue();
				break;
			}
		}
		map.put("yh_mch", yh_mch);
		return "sale_index";
	}
	
	@RequestMapping("index")
	public String index(){
		return "sale_index";
	}
}
