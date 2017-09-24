package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;

@Controller
public class LoginController {

	@RequestMapping("login")
	@ResponseBody
	public String login(String yh_mch, String yh_mm, MODEL_T_MALL_SKU_ATTR_VALUE list_av) {
		System.out.println("fdsfsf");
		return "success";
	}

}
