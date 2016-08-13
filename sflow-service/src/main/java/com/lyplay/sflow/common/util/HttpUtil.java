package com.lyplay.sflow.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class HttpUtil {

	/**
	 * Get Client IP Address
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}

		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}

		return request.getRemoteAddr();
	}

	/**
	 * Get Web Application Base Path/URL
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getBasePath(HttpServletRequest request) {
		return StringUtils.join(request.getScheme(), "://", request.getServerName(), ":", request.getServerPort(), request.getContextPath(), "/");
	}

	/**
	 * Get Full URL
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	public static String getFullURL(HttpServletRequest request, String url) {
		url = url.startsWith("/") ? url.substring(1) : url;
		return StringUtils.join(getBasePath(request), url);
	}

	

	
}
