package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月14日 下午7:30:12 
 * @version v1
 */
@Controller
public class TestViewController {
	/**
	 * 直接返回字符串路径
	 * 问题：不识别带中文的传参
	 * @return
	 */
	@RequestMapping("str")
	public String add1(){
		String success = "Start恭喜String成功End";
		return "redirect:/test/"+success+".do";
	}
	
	
	@RequestMapping("mv")
	public ModelAndView add2(){
		ModelAndView modelAndView = new ModelAndView("redirect:/test/${success}.do");
		modelAndView.addObject("success","Start恭喜String成功End");
		return modelAndView;
	}
	@RequestMapping("rv")
	public RedirectView add3(){
		RedirectView redirectView = new RedirectView("/test/${success}.do", true, false,true);
		redirectView.addStaticAttribute("success", "Start恭喜String成功End");
		return redirectView;
	}
	@RequestMapping("test/{success}")
	public String goto_add_attr(@PathVariable("success") String success,ModelMap map){
		map.put("success", success);
		return "testView";
	}
}
