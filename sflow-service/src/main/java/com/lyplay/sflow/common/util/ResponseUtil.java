package com.lyplay.sflow.common.util;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	
	
	public static void setJpeg(HttpServletResponse response){
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg"); 
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	}
	
}
