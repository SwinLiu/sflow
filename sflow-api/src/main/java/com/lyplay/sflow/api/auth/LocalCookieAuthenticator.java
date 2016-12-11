package com.lyplay.sflow.api.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyplay.sflow.api.exception.AuthenticateException;
import com.lyplay.sflow.common.UserSession;

public class LocalCookieAuthenticator implements Authenticator {

	@Override
	public UserSession authenticate(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticateException {
		String cookie = getCookieFromRequest(request, "cookieName");
        if (cookie == null) {
            return null;
        }
        return getUserByCookie(cookie);
	}

	private UserSession getUserByCookie(String cookie) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getCookieFromRequest(HttpServletRequest request,
			String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
