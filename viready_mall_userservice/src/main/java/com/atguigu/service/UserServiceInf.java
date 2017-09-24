package com.atguigu.service;

import java.util.List;

import javax.jws.WebService;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月22日 下午9:24:58 
 * @version v1
 */
@WebService
public interface UserServiceInf {

	T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user);

	String loginTest(T_MALL_USER_ACCOUNT user);
}
