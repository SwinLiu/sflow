package com.lyplay.sflow.api.rest.controller;

import static com.lyplay.sflow.common.dto.RestResult.success;

import java.io.IOException;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyplay.sflow.api.auth.AuthPassport;
import com.lyplay.sflow.common.dto.RestResult;
import com.lyplay.sflow.common.util.CaptchaImageCode;
import com.lyplay.sflow.common.util.Constant;
import com.lyplay.sflow.common.util.RSAUtil;
import com.lyplay.sflow.common.util.ResponseUtil;

/**
 * 
 * Cecurity Rest API Functions
 * 
 * @author lyplay.com
 *
 */

@Controller("cecurityController")
public class CecurityController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@AuthPassport(validate = false)
	@RequestMapping(value = "/api/captcha/{size}", method = RequestMethod.GET, produces = "application/json")
	public void getCaptchaPic(@PathVariable("size") String size,
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
		session.setAttribute(Constant.CAPTCHA_CODE, captchaImageCode.getCode());
		ResponseUtil.setJpeg(response);
		captchaImageCode.write(response.getOutputStream());

	}
	
	@AuthPassport(validate = false)
	@RequestMapping(value = "/api/secret", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public RestResult getEncryptKey(HttpSession session) throws Exception {

		String rsaPublicKey = (String) session.getAttribute(Constant.RSA_PUBLIC_KEY);
		if(StringUtils.isEmpty(rsaPublicKey)){
			Map<String, String> map = RSAUtil.getKeyPair();
			rsaPublicKey = map.get(RSAUtil.PUBLIC_KEY);
			String rsaprivateKey = map.get(RSAUtil.PRIVATE_KEY);
			session.setAttribute(Constant.RSA_PUBLIC_KEY, rsaPublicKey);
			session.setAttribute(Constant.RSA_PRIVATE_KEY, rsaprivateKey);
		}
		return success(rsaPublicKey);
	}
	
	
}
