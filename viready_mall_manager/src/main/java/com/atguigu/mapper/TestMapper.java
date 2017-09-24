package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.bean.T_MALL_CLASS_2;
import com.atguigu.bean.T_MALL_TRADE_MARK;
public interface TestMapper {
    List<T_MALL_CLASS_1> select_class_1();

	List<T_MALL_CLASS_2> select_class_2_by_flbh1(Integer flbh1);

	List<T_MALL_TRADE_MARK> select_trade_mark();

	List<T_MALL_TRADE_MARK> select_trade_mark_by_flbh1(int id);
}
