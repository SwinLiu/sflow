package com.lyplay.sflow.api.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyplay.sflow.api.exception.AuthenticateException;
import com.lyplay.sflow.common.UserSession;

public class BasicAuthenticator implements Authenticator {

	@Override
	public UserSession authenticate(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticateException {
		String auth = getHeaderFromRequest(request, "Authorization");
        if (auth == null) {
            return null;
        }
        String username = parseUsernameFromAuthorizationHeader(auth);
        String password = parsePasswordFromAuthorizationHeader(auth);
        return authenticateUserByPassword(username, password);
	}

	private UserSession authenticateUserByPassword(String username,
			String password) {
		// TODO Auto-generated method stub
		return null;
	}

	private String parsePasswordFromAuthorizationHeader(String auth) {
		// TODO Auto-generated method stub
		return null;
	}

	private String parseUsernameFromAuthorizationHeader(String auth) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getHeaderFromRequest(HttpServletRequest request,
			String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
