package com.lyplay.sflow.api.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyplay.sflow.api.auth.Authenticator;
import com.lyplay.sflow.common.UserSession;
import com.lyplay.sflow.common.UserSessionContext;

public class GlobalFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	Authenticator[] authenticators = initAuthenticators();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 链式认证获得User:
        UserSession userSession = tryGetAuthenticatedUser(request, response);
        // 把User绑定到UserSessionContext中:
        try (UserSessionContext ctx = new UserSessionContext(userSession)) {
            chain.doFilter(request, response);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
		}
	}

	private UserSession tryGetAuthenticatedUser(ServletRequest request,
			ServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private Authenticator[] initAuthenticators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
