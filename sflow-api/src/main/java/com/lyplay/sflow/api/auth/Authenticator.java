package com.lyplay.sflow.api.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyplay.sflow.api.exception.AuthenticateException;
import com.lyplay.sflow.common.UserSession;

/**
 * 
 * @author lyplay
 *
 */

public interface Authenticator {

	// 认证成功返回UserSession，认证失败抛出异常，无认证信息返回null:
	UserSession authenticate(HttpServletRequest request, HttpServletResponse response) throws AuthenticateException;
    
}
