package com.mulodo.fiveneed.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpServletRequest Utils
 *
 * @author thomc
 *
 */
public class ServletUtils {

	/*-----------------------------------------------
	* Public
	*-----------------------------------------------*/
	/**
	 * Create Uri
	 *
	 * @param req HttpServletRequest
	 * @return baseUrl
	 */
	public static String createBaseUrl(HttpServletRequest req) {
		StringBuffer sb = new StringBuffer();
		sb.append(req.getScheme());
		sb.append("://");
		sb.append(req.getServerName());
		sb.append(":");
		sb.append(req.getServerPort());
		sb.append("/");

		String[] split = req.getRequestURI().split("/");
		if (split.length > 1) {
			// context path
			sb.append(split[1]);
		}

		return sb.toString();
	}
}
