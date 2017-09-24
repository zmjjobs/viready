package com.atguigu.util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.wss4j.common.ext.WSPasswordCallback;
/**
 * @description 自定义的用于函数回调的服务端工具
 * @author 朱梦君
 * @datatime 2017年8月26日 下午5:47:32 
 * @version v1
 */
public class MyCallBackService implements CallbackHandler{

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback wsc = (WSPasswordCallback) callbacks[0];
		String pwd = MyProperty.getMyProperty(wsc.getIdentifier(), "wsPassword.properties");
		String md5pwd = MD5Util.md5(pwd + Constant.CIPHER_FOR_PASSWORD + MyDateUtil.getDateStr4Password());
		wsc.setPassword(md5pwd);
		
	}

}
