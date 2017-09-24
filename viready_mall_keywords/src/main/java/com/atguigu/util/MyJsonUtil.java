package com.atguigu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

import net.sf.json.JSONArray;

/**
 * @description 有关json的转换工具
 * @author 朱梦君
 * @datatime 2017年8月22日 下午10:02:14
 * @version v1
 */
public class MyJsonUtil {

	/**
	 * json串转换成List
	 * @param json
	 * @param t
	 * @return
	 */
	public static <T> List<T> json_to_list(String json, Class<T> t) {
		List<T> list_array = new ArrayList<>();
		if(StringUtils.isNotBlank(json)){
			String decode = "";
			try {
				decode = URLDecoder.decode(json, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			list_array = (List<T>) JSONArray.toCollection(JSONArray.fromObject(decode), t);
		}
		return list_array;
	}

	/**
	 * list转换成json
	 * @param list
	 * @return
	 */
	public static <T> String list_to_json(List<T> list) {

		Gson gson = new Gson();
		String json = gson.toJson(list);

		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 对象转换为json
	 * @param t
	 * @return json字符串
	 */
	public static <T> String obj_to_json(T t) {
		String json = new Gson().toJson(t);
		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * json转换成对象
	 * @param json
	 * @param t
	 * @return
	 */
	public static <T> T json_to_obj(String json,Class<T> t){
		try {
			json = URLDecoder.decode(json,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new Gson().fromJson(json, t);
	}

}