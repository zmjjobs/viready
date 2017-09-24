package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.mapper.TestMapper;
import com.atguigu.service.SpuServiceImp;
import com.atguigu.service.SpuServiceInf;
import com.atguigu.util.MyUploadFileUtil;
/**
 * @description SPU控制器
 * @author 朱梦君
 * @datatime 2017年8月14日 下午3:28:41 
 * @version v1
 */
@Controller
public class SpuController {
	
	@Autowired
	private SpuServiceImp spuServiceImp;
	
	@RequestMapping("goto_spu")
	public String goto_spu(String success,ModelMap map){
		map.put("success", success);
		return "manager_spu";
	}
	
	/**
	 * 保存SPU
	 * @param spu
	 * @param files
	 * @return
	 */
	@RequestMapping("save_spu")
	public ModelAndView save_spu(T_MALL_PRODUCT spu,@RequestParam("files") MultipartFile[] files){
		//图片上传到服务器
		List<String> list_image = MyUploadFileUtil.upload_image(files);
		
		//将spu信息和图片信息保存到数据库
		spuServiceImp.save_spu(spu,list_image);
		
		//用ModelAndView解决路径传参时候的中文不识别问题
		//当spu添加后，重定向到首页
		ModelAndView modelAndView = new ModelAndView("redirect:/index.do");
		modelAndView.addObject("success","恭喜");
		
		//对应manager_index.jsp中的url和title
		modelAndView.addObject("url","goto_spu.do");
		modelAndView.addObject("title","spu信息");
		return modelAndView;
	}
}
