package com.lyplay.sflow.api.rest.controller;

import static com.lyplay.sflow.common.dto.RestResult.fail;
import static com.lyplay.sflow.common.dto.RestResult.success;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.common.enums.ErrorCode;
import com.lyplay.sflow.common.util.Constant;
import com.lyplay.sflow.dto.RegisterUser;
import com.lyplay.sflow.service.impl.UserAccountServiceImpl;

/**
 * 
 * Register Rest API Functions
 * 
 * @author lyplay.com
 *
 */

@Controller("registerController")
public class RegisterController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserAccountServiceImpl userAccountService;

	@RequestMapping(value = "/api/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public RestResult register(RegisterUser registerUser, HttpSession session) {
		
		String sessionCaptchaCode = (String) session.getAttribute(Constant.CAPTCHA_CODE);
		if(!StringUtils.equals(sessionCaptchaCode, registerUser.getCaptchaCode())){
			return fail(ErrorCode.CAPTCHA_ERROR); 
		}
		
		return success();

	}

	
}
