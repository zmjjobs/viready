package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.UserServiceImp;
import com.atguigu.util.Constant;

/**
 * @description 购物车控制器
 * @author 朱梦君
 * @datatime 2017年8月22日 下午7:28:55 
 * @version v1
 */
@Controller
public class UserController {
	
	@Autowired
	private UserServiceImp userServiceImp;
	
	
	
}
