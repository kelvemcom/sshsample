/**
 * 
 */
package com.kelvem.common.utils;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;



/**
 * @author kelvem
 *
 */
public class RequestUtil {

	private RequestUtil(){
		// do nothing
	}

	public static String getContextPath(HttpServletRequest request){
		if (request == null || request.getContextPath() == null) {
			return "";
		}
		return request.getContextPath();
	}
	
	public static String getReferer(HttpServletRequest request){
		if (request == null) {
			return null;
		}
		String referer = request.getHeader("referer");
		if (referer == null || referer.length() <= 0) {
			return null;
		}
		int index = referer.indexOf(getContextPath(request) + "/");
		referer = referer.substring(index + getContextPath(request).length());
		return referer;
	}

	public static String getBrowser(HttpServletRequest request){
		if (request == null) {
			return null;
		}
		return request.getHeader("User-Agent");
	}

	public static String getUrl(HttpServletRequest request){
		if (request == null) {
			return null;
		}
		return request.getServletPath();
	}

	public static String getServerName(HttpServletRequest request){
		if (request == null) {
			return null;
		}
		return request.getServerName();
	}

	public static Integer getLocalPort(HttpServletRequest request){
		if (request == null) {
			return null;
		}
		return request.getLocalPort();
	}

	public static String getQueryString(HttpServletRequest request){
		if (request == null) {
			return null;
		}
		return request.getQueryString();
	}

	public static Integer getContentLength(HttpServletRequest request){
		if (request == null) {
			return null;
		}
		return request.getContentLength();
	}

	public static String getCharacterEncoding(HttpServletRequest request){
		if (request == null) {
			return null;
		}
		return request.getCharacterEncoding();
	}

	public static String getMethod(HttpServletRequest request){
		if (request == null) {
			return null;
		}
		return request.getMethod();
	}

	public static String getProtocol(HttpServletRequest request){
		if (request == null) {
			return null;
		}
		return request.getProtocol();
	}

	public static String getRequestedSessionId(HttpServletRequest request){
		if (request == null) {
			return null;
		}
		return request.getRequestedSessionId();
	}

	public static Principal getUserPrincipal(HttpServletRequest request){
		if (request == null) {
			return null;
		}
		return request.getUserPrincipal();
	}

	public static Cookie[] getCookies(HttpServletRequest request){
		if (request == null || request.getCookies() == null) {
			return new Cookie[0];
		}
		return request.getCookies();
	}
	
}
