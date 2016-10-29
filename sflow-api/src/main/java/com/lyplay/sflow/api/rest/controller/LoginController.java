package com.lyplay.sflow.api.rest.controller;

import static com.lyplay.sflow.common.dto.RestResult.fail;
import static com.lyplay.sflow.common.dto.RestResult.success;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.common.util.CaptchaImageCode;
import com.lyplay.sflow.common.util.Constant;
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
	public RestResult login(
			@RequestParam(value = "loginAccount", required = true) String loginAccount,
			@RequestParam(value = "passwd", required = true) String passwd,
			HttpServletRequest request) {

		String pwd = null;
		try {
			pwd = SHAUtil.shaEncode(passwd);
		} catch (Exception e) {
			logger.error(" Encdoe password happened issue. ");
			logger.error(e.getMessage(), e);
			return fail("password have issue.");
		}

		UserAccount userAccount = userAccountService.login(loginAccount, pwd);
		if (userAccount != null) {
			request.getSession().setAttribute("userAccount", userAccount);
			return success();
		} else {
			return fail();
		}

	}

	@RequestMapping(value = "/api/captcha/{size}", method = RequestMethod.GET, produces = "application/json")
	public void save(@PathVariable("size") String size,
			HttpServletResponse response, HttpSession session)
			throws IOException {

		int[] params = { CaptchaImageCode.DEFAULT_WIDTH,
				CaptchaImageCode.DEFAULT_HEIGHT,
				CaptchaImageCode.DEFAULT_CODECOUNT,
				CaptchaImageCode.DEFAULT_LINECOUNT };

		if (StringUtils.isNotEmpty(size)) {
			String[] sizeGrps = size.split("x");
			if (ArrayUtils.isNotEmpty(sizeGrps)) {
				for (int i = 0; i < sizeGrps.length; i++) {
					try {
						params[i] = Integer.valueOf(sizeGrps[i]);
					} catch (NumberFormatException e) {
						logger.error(" captcha size format NumberFormatException. size : {} .", size);
						logger.error(e.getMessage(), e);
					}
				}
			}
		}

		CaptchaImageCode captchaImageCode = new CaptchaImageCode(params[0], params[1],
				params[2], params[3]);
		
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg"); 
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		session.setAttribute(Constant.CAPTCHA_CODE, captchaImageCode.getCode());
		captchaImageCode.write(response.getOutputStream());
		
		

	}
}
