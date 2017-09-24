package com.atguigu.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月24日 下午9:56:00 
 * @version v1
 */
public class TestBigDecimal {

	@Test
	public void test() {
		//初始化时，只用字符串的初始化，这样不会失去精度
		BigDecimal bd1 = new BigDecimal("0.494");
		BigDecimal bd2 = new BigDecimal("4");
		
		//比较
		int compareTo = bd1.compareTo(bd2);
		System.out.println(compareTo);
		
		//加法
		BigDecimal add = bd1.add(bd2);
		System.out.println(add);
		
		//减法
		BigDecimal subtract = bd1.subtract(bd2);
		System.out.println(subtract);
		
		//乘法
		BigDecimal multiply = bd1.multiply(bd2);
		System.out.println(multiply);
		
		//除法(能除尽)
		//BigDecimal divide = bd1.divide(bd2);
		//System.out.println(divide);
		
		//除法(不能除尽)
		BigDecimal divide = bd1.divide(bd2,3,BigDecimal.ROUND_HALF_UP);
		System.out.println(divide);
		
		
	}

}
