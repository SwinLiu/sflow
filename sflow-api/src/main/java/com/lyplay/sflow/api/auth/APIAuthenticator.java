package com.lyplay.sflow.api.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyplay.sflow.api.exception.AuthenticateException;
import com.lyplay.sflow.common.UserSession;

public class APIAuthenticator implements Authenticator {

	@Override
	public UserSession authenticate(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticateException {
        String token = getHeaderFromRequest(request, "X-API-Token");
        if (token == null) {
            return null;
        }
        return authenticateUserByAPIToken(token);
	}

	private UserSession authenticateUserByAPIToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getHeaderFromRequest(HttpServletRequest request,
			String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
