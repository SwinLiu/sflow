package com.lyplay.sflow.api.auth;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyplay.sflow.api.exception.AuthenticateException;
import com.lyplay.sflow.common.UserSession;
import com.lyplay.sflow.common.util.Constant;
import com.lyplay.sflow.common.util.JsonUtil;
import com.lyplay.sflow.common.util.TokenUtil;

public class APIAuthenticator implements Authenticator {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String API_HEADER_TOKEN = "X-API-Token";
	
	@Override
	public UserSession authenticate(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticateException {
        String token = getHeaderFromRequest(request, API_HEADER_TOKEN);
        if (token == null) {
            return null;
        }
        return authenticateUserByAPIToken(token);
	}

	private UserSession authenticateUserByAPIToken(String token) {
		
		Claims claims = TokenUtil.parseJWT(token);
		if(claims != null){
			try {
				String userSessionJson = (String) claims.get(Constant.USER_SESSION);
				return JsonUtil.json2Bean(userSessionJson, UserSession.class);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return null;
			}
		}else{
			return null;
		}
		
	}

	private String getHeaderFromRequest(HttpServletRequest request,
			String string) {
		return request.getHeader(API_HEADER_TOKEN);
	}

}
