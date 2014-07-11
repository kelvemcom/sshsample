package com.kelvem.common.profile;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kelvem.common.utils.RequestUtil;

public class ProfileFilter implements Filter {
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		ProfileContext.push(RequestUtil.getUrl(request));
		chain.doFilter(request, response);
		ProfileContext.pop();
	}

}