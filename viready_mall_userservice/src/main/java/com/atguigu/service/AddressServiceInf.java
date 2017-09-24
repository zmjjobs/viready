package com.atguigu.service;

import java.util.List;

import javax.jws.WebService;

import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

@WebService
public interface AddressServiceInf {

	void add_address(T_MALL_ADDRESS address);

	T_MALL_ADDRESS get_addresses_by_id(int address_id);

	List<T_MALL_ADDRESS> get_addresses_by_user_id(T_MALL_USER_ACCOUNT user);

}
