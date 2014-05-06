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
		
		ProfileItem item = new ProfileItem();
		item.setContent(RequestUtil.getUrl(request));
		
		ProfileContext.push(item);
		chain.doFilter(request, response);
		ProfileContext.pop();
		
		/**
		if (ProfileContext.get() == null) {
//			long t = item.getEndDate().getTime() - item.getStartDate().getTime();
//			System.out.println(item.getContent() + " [" + t + "ms] level=" + item.getLevel() + ", " + RequestUtil.getReferer(request));	
		}
		
		if (root == null || root.getLevel() != 0) {
//			System.err.println("============" + root);
		}
		**/
		ProfileItem root = ProfileContext.get();
		if (root != null) {
//			System.out.println(root.toString());
			System.out.println(item.toString());
		}
	}

}