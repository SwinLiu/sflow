package com.lyplay.sflow.api.rest.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lyplay.sflow.common.util.CaptchaImageCode;
import com.lyplay.sflow.common.util.Constant;

/**
 * 
 * Captcha Rest API Functions
 * 
 * @author lyplay.com
 *
 */

@Controller("captchaController")
public class CaptchaController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

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
