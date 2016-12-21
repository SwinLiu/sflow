package com.lyplay.sflow.api.rest.controller;

import static com.lyplay.sflow.common.dto.RestResult.fail;
import static com.lyplay.sflow.common.dto.RestResult.success;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyplay.sflow.api.auth.AuthPassport;
import com.lyplay.sflow.common.UserSession;
import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.common.enums.AccountStatusEnum;
import com.lyplay.sflow.common.enums.ErrorCode;
import com.lyplay.sflow.common.util.Constant;
import com.lyplay.sflow.common.util.JsonUtil;
import com.lyplay.sflow.common.util.PasswdUtil;
import com.lyplay.sflow.common.util.TokenUtil;
import com.lyplay.sflow.po.UserAccount;
import com.lyplay.sflow.service.IUserAccountService;

/**
 * 
 * Login Rest API Functions
 * 
 * @author lyplay.com
 *
 */

@Controller("loginController")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserAccountService userAccountService;

	@AuthPassport(validate = false)
	@RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RestResult login(
			@RequestParam(value = "loginAccount", required = true) String loginAccount,
			@RequestParam(value = "passwd", required = true) String passwd,
			@RequestParam(value = "captchaCode") String captchaCode,
			HttpSession session) throws Exception {

		if(PasswdUtil.checkCaptchaCode(session, captchaCode)){
			return fail(ErrorCode.CAPTCHA_ERROR); 
		}
		
		String pwd = PasswdUtil.getPasswd(session, loginAccount, passwd);
		if(StringUtils.isEmpty(pwd)){
			return fail(ErrorCode.LOGIN_ERROR); // userAccount or Password have issue.
		}
		UserAccount userAccount = new UserAccount();
		userAccount.setId("T001");
		userAccount.setUserName("Swin.Liu");
		userAccount.setEmail("test@test.com");
		userAccount.setPhone("123123123");
		userAccount.setStatus(AccountStatusEnum.ACTIVE.getStatus());
		
		UserSession sserSession = new UserSession();
		sserSession.setUserAccount(userAccount);
		
		session.setAttribute(Constant.USER_SESSION, sserSession);
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		Map<String, Object> claims = new HashMap<String, Object>(1);
		claims.put(Constant.USER_SESSION, JsonUtil.bean2Json(sserSession));
		
		result.put(Constant.USER_TOKEN, TokenUtil.getJWTString(claims, null));
		result.put(Constant.USER_SESSION, sserSession);
		
		return success(result);
//		
//		UserAccount userAccount = userAccountService.login(loginAccount, pwd);
//		if (userAccount != null) {
//			session.setAttribute("userAccount", userAccount);
//			return success();
//		} else {
//			return fail(ErrorCode.LOGIN_ERROR); // userAccount or Password have issue.
//		}

	}
	
	// TODO add filter for login check
	@RequestMapping(value = "/api/auth", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RestResult auth() throws Exception {
		return success();
	}
	
	// TODO add filter for login check
	@RequestMapping(value = "/api/logout", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RestResult logout(HttpSession session) throws Exception {
		//清除Session  
        session.invalidate();
        
        return success();
	}
	
}
