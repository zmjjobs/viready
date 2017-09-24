package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.AddressMapper;

public class AddressServiceImp implements AddressServiceInf {

	@Autowired
	AddressMapper addressMapper;

	@Override
	public void add_address(T_MALL_ADDRESS address) {
		addressMapper.insert_address(address);
	}

	@Override
	public List<T_MALL_ADDRESS> get_addresses_by_user_id(T_MALL_USER_ACCOUNT user) {
		return addressMapper.select_addresses_by_user_id(user);
	}

	@Override
	public T_MALL_ADDRESS get_addresses_by_id(int address_id) {
		return addressMapper.select_addresses_by_id(address_id);
	}
}
