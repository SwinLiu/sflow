package com.lyplay.sflow.mvc.config;

import java.awt.Color;
import java.awt.image.BufferedImageOp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("patchcaController")
@RequestMapping("/patchca.do")
public class PatchcaController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
	private static Random random = new Random();

	static {
		cs.setColorFactory(new ColorFactory() {
			@Override
			public Color getColor(int x) {
				int[] c = new int[3];
				int i = random.nextInt(c.length);
				for (int fi = 0; fi < c.length; fi++) {
					if (fi == i) {
						c[fi] = random.nextInt(71);
					} else {
						c[fi] = random.nextInt(256);
					}
				}
				return new Color(c[0], c[1], c[2]);
			}
		});

		RandomWordFactory wf = new RandomWordFactory();
		ResourceBundle rb = ResourceBundle.getBundle("system");
		wf.setCharacters(rb.getString("captcha.characters"));
		wf.setMaxLength(Integer.parseInt(rb.getString("captcha.length")));
		wf.setMinLength(Integer.parseInt(rb.getString("captcha.length")));
		cs.setWordFactory(wf);
		cs.setWidth(Integer.parseInt(rb.getString("captcha.width")));
		cs.setHeight(Integer.parseInt(rb.getString("captcha.height")));

		List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();
		filters.add(new SoftenImageOp());
		ConfigurableFilterFactory cf = new ConfigurableFilterFactory();
		cf.setFilters(filters);
		cs.setFilterFactory(cf);
	}

	@RequestMapping(params = "method=getpatchca")
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession();
		}
		setResponseHeaders(response);
		String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
		session.setAttribute("captchaToken", token);
		System.out.println("SessionID=" + session.getId() + "，captcha=" + token);
	}

	protected void setResponseHeaders(HttpServletResponse response) {
		response.setContentType("image/png");
		response.setHeader("Cache-Control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		long time = System.currentTimeMillis();
		response.setDateHeader("Last-Modified", time);
		response.setDateHeader("Date", time);
		response.setDateHeader("Expires", time);
	}
}
