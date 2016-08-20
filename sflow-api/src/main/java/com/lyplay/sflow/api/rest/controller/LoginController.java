package com.lyplay.sflow.api.rest.controller;

import static com.lyplay.sflow.common.dto.RestResult.fail;
import static com.lyplay.sflow.common.dto.RestResult.success;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.common.util.SHAUtil;
import com.lyplay.sflow.po.UserAccount;
import com.lyplay.sflow.service.impl.UserAccountServiceImpl;

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
	private UserAccountServiceImpl userAccountService;
	
	
	@RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RestResult save(@RequestParam(value = "loginAccount", required = true) String loginAccount,
			@RequestParam(value = "passwd", required = true) String passwd,
			HttpServletRequest request){
		
		String pwd = null;
		try {
			pwd = SHAUtil.shaEncode(passwd);
		} catch (Exception e) {
			logger.error(" Encdoe password happened issue. ");
			logger.error(e.getMessage(),e);
			return fail("password have issue.");
		}
		
		UserAccount userAccount = userAccountService.login(loginAccount, pwd);
		if(userAccount != null){
			request.getSession().setAttribute("userAccount", userAccount);
			return success();
		}else{
			return fail();
		}
		
	}
}
