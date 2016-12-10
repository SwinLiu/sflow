package com.lyplay.sflow.common.util;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

public class PasswdUtil {
	
	
	public static String getPasswd(HttpSession session, String passwd) throws Exception{
		return getPasswd(session, null, passwd);
	}

	public static String getPasswd(HttpSession session, String loginAccount, String passwd) throws Exception{
		
		String rsaPrivateKey = (String) session.getAttribute(Constant.RSA_PRIVATE_KEY);
		
		String data = RSAUtil.decryptByPrivateKey(passwd, rsaPrivateKey);
		String[] passwdGroup = data.split(Constant.SPLIT_STR);
		String realPasswd = StringUtils.EMPTY;
		if(StringUtils.isNotEmpty(loginAccount)){
			if(StringUtils.equals(passwdGroup[0], loginAccount)){
				realPasswd = passwdGroup[1];
			}
		} else {
			realPasswd = passwdGroup[0];
		}
		
		return realPasswd;
	}
	
	public static boolean checkCaptchaCode(HttpSession session, String captchaCode){
		String sessionCaptchaCode = (String) session.getAttribute(Constant.CAPTCHA_CODE);
		//return !StringUtils.equals(sessionCaptchaCode, captchaCode);
		// for test
		return false;
	}
	
}
