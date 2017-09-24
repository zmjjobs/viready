package com.atguigu.mapper;



import com.atguigu.bean.T_MALL_USER_ACCOUNT;

/**
 * @description 购物车相关数据访问接口
 * @author 朱梦君
 * @datatime 2017年8月22日 下午7:29:55 
 * @version v1
 */
public interface UserMapper {

	T_MALL_USER_ACCOUNT select_user(T_MALL_USER_ACCOUNT user);
	void insert_user(T_MALL_USER_ACCOUNT user);

}

