package com.atguigu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description 自定义的日期转换工具
 * @author 朱梦君
 * @datatime 2017年8月26日 下午10:42:25 
 * @version v1
 */
public class MyDateUtil {
	private static SimpleDateFormat sdf_normal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdf_unsigned5 = new SimpleDateFormat("yyyyMMddHHmm");
	
	/**
	 * 获取物流配送时间或预定时间
	 * @param days（7>=days>=-7）
	 * @return
	 */
	public static Date getDate4Flow(int days){
		if(days > 7){
			days = 7;
		}
		if(days < -7){
			days = -7;
		}
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, days);
		return c.getTime();
	}
	
	/**
	 * 将当前时间从年到分钟无符号的输出字符串，用于密码的一部分
	 * @return
	 */
	public static String getDateStr4Password(){
		return sdf_unsigned5.format(new Date());
	}
	
	/**
	 * 将日期对象转换为字符串(yyyy-MM-dd HH:mm:ss)
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		return sdf_normal.format(date);
	}
	
	/**
	 * 将字符串转换为日期对象(yyyy-MM-dd HH:mm:ss)
	 * @param date
	 * @return
	 */
	public static Date stringToDate(String date){
		try {
			return sdf_normal.parse(date) ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null ;
	}
}
