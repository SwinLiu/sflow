package com.lyplay.sflow.api.Interceptor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lyplay.sflow.api.auth.APIAuthenticator;
import com.lyplay.sflow.api.auth.AuthPassport;
import com.lyplay.sflow.api.exception.AuthenticateException;
import com.lyplay.sflow.common.UserSession;
import com.lyplay.sflow.common.UserSessionContext;

public class LoginInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/** 
     * Handler执行完成之后调用这个方法 
     */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		UserSessionContext.removeUserSession();
	}

	/** 
     * Handler执行之后，ModelAndView返回之前调用这个方法 
     */ 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView map) throws Exception {
	}

	/** 
     * Handler执行之前调用这个方法 
     */ 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		boolean returnFlag = true;
		if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
			
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
            
            //没有声明则需要验证权限,或者声明不验证权限
           if(authPassport == null || authPassport.validate() == true){

        	   UserSession userSession = tryGetAuthenticatedUser(request, response);
        	   if(userSession != null){
        		   UserSessionContext.setUserSession(userSession);
        	   }else{
        		   returnFlag = false;
        	   }
           }
        }
		
		if(!returnFlag){
			response.setStatus(401);
		}
            
		return returnFlag;   
	}
	
	private UserSession tryGetAuthenticatedUser(HttpServletRequest request,
			HttpServletResponse response) {
		APIAuthenticator apiAuthenticator = new APIAuthenticator();
		try {
			return apiAuthenticator.authenticate(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
}
