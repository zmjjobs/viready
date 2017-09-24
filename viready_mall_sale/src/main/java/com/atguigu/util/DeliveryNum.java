package com.atguigu.util;

/**
 * @description 自动生成下一个物流单号
 * @author 朱梦君
 * @datatime 2017年8月27日 下午9:20:25
 * @version v1
 */
public class DeliveryNum {
	public static void main(String[] args) {
		String twoNum = getTwoNum("9999999998");
		System.out.println(twoNum);
	}

	/**
	 * //宅急送物流单 和 雅玛多物流单生成：加11，逢6 减6加10
	 * 
	 * @param deliverynum
	 *            物流单号
	 * @return 后一个物流单号
	 */
	public static String getOneNum(String deliverynum) {
		String deliveryvalue = "";
		int num = 0;
		try {
			num = Integer.parseInt(deliverynum);
		} catch (NumberFormatException e) {
		}
		int yushu = num % 10;
		if (yushu == 6) {
			num = num - 6 + 10;
		} else {
			num = num + 11;
		}
		int intnum = String.valueOf(num).trim().length();// num 字符长度
		int strnum = deliverynum.trim().length();// 输入的物流单号字符长度
		// 判断int类型数据是否小于原来值的长度
		// 如物流单号是已’0‘开头的，需要把零补上
		String value = "";
		if (intnum < strnum) {
			for (int j = 0; j < (strnum - intnum); j++) {
				value += "0";
			}
		}
		deliveryvalue = value + num;
		return deliveryvalue;
	}

	/**
	 * //中通、韵达、艾斯客、闵鑫 自动加 1
	 * 
	 * @param deliverynum
	 *            物流单号
	 * @return 后一个物流单号
	 */
	public static String getTwoNum(String deliverynum) {
		String deliveryvalue = "";
		int num = 0;
		try {
			num = Integer.parseInt(deliverynum);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		num++;
		int intnum = String.valueOf(num).trim().length();// num 字符长度
		int strnum = deliverynum.trim().length();// 输入的物流单号字符长度
		// 判断int类型数据是否小于原来值的长度
		// 如物流单号是已’0‘开头的，需要把零补上
		String value = "";
		if (intnum < strnum) {
			for (int j = 0; j < (strnum - intnum); j++) {
				value += "0";
			}
		}
		deliveryvalue = value + num;
		return deliveryvalue;
	}

	/**
	 * EMS物流单
	 * 
	 * @param deliverynum
	 *            EMS物流单号
	 * @return 后一个物流单号
	 */
	public static String getThreeNum(String deliverynum) {
		String strbegin = deliverynum.substring(0, 2);
		String strend = deliverynum.substring(deliverynum.length() - 2, deliverynum.length());
		String modnum = deliverynum.substring(2, deliverynum.length() - 3);
		// 订单号加一
		int intnum = Integer.parseInt(modnum) + 1;
		// 判断字符串长度
		int ontnum = String.valueOf(intnum).trim().length();// num 字符长度
		int twonum = modnum.trim().length();// 输入的物流单号字符长度
		// 判断int类型数据是否小于原来值的长度
		// 如物流单号是已’0‘开头的，需要把零补上
		String value = "";
		if (ontnum < twonum) {
			for (int j = 0; j < (twonum - ontnum); j++) {
				value += "0";
			}
		}
		// 保存新单号数字字符串
		modnum = value + intnum;

		// 求第九个数字位的验证字符
		int verifynum = 0;// 效验字符
		int sum = 8 * Integer.parseInt(modnum.substring(0, 1)) + 6 * Integer.parseInt(modnum.substring(1, 2))
				+ 4 * Integer.parseInt(modnum.substring(2, 3)) + 2 * Integer.parseInt(modnum.substring(3, 4))
				+ 3 * Integer.parseInt(modnum.substring(4, 5)) + 5 * Integer.parseInt(modnum.substring(5, 6))
				+ 9 * Integer.parseInt(modnum.substring(6, 7)) + 7 * Integer.parseInt(modnum.substring(7, 8));
		int modnumber = sum % 11;
		if (11 - modnumber == 10) {
			verifynum = 0;
		} else if (11 - modnumber == 11) {
			verifynum = 5;
		} else {
			verifynum = 11 - modnumber;
		}
		deliverynum = strbegin + modnum + String.valueOf(verifynum) + strend;
		return deliverynum;
	}

	
}
