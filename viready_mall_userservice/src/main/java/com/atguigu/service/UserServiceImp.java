package com.atguigu.service;


import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.UserMapper;
import com.atguigu.util.MyDataSourceSwitch;
import com.google.gson.Gson;

/**
 * @description 用户业务实现类 
 * @author 朱梦君
 * @datatime 2017年8月22日 下午9:25:20 
 * @version v1
 */
public class UserServiceImp implements UserServiceInf {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {
		MyDataSourceSwitch.setKey("1");
		return userMapper.select_user(user);
	}
	
	@Override
	@GET
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@Path("loginTest")
	public String loginTest(@BeanParam T_MALL_USER_ACCOUNT user) {
		MyDataSourceSwitch.setKey("2");
		return new Gson().toJson((userMapper.select_user(user)));
	}

	
}
