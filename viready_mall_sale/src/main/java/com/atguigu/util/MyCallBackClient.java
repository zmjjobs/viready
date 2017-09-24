package com.atguigu.util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

/**
 * @description 
 * @author 朱梦君
 * @datatime 2017年8月26日 下午5:43:54 
 * @version v1
 */
public class MyCallBackClient implements CallbackHandler{

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback wsc = (WSPasswordCallback) callbacks[0];
		wsc.setIdentifier("cxf");
		String md5pwd = MD5Util.md5("wss4j" + Constant.CIPHER_FOR_PASSWORD + MyDateUtil.getDateStr4Password());
		System.err.println(md5pwd);
		wsc.setPassword(md5pwd);
	}

}
