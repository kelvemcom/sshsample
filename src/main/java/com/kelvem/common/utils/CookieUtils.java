package com.kelvem.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * cookie的增加、删除、查询
 */
public class CookieUtils {

	// 添加一个cookie
	public Cookie addCookie(String key, String value) {
		Cookie cookie = new Cookie(key, value);
		System.out.println("添加cookie");
		cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周
		return cookie;
	}

	// 得到cookie
	public Cookie[] getCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		System.out.println("cookies: " + cookies);
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				System.out.println("cookie: " + cookie.getName());
			}
		}
		return cookies;
	}

	// 删除cookie
	public Cookie delCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName())) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					return cookie;
				}
			}
		}
		return null;
	}
}
