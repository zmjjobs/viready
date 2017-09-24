package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月25日 下午11:44:38 
 * @version v1
 */
public interface AddressMapper {

	void insert_address(T_MALL_ADDRESS address);

	List<T_MALL_ADDRESS> select_addresses_by_user_id(T_MALL_USER_ACCOUNT user);

	T_MALL_ADDRESS select_addresses_by_id(int address_id);

}
