package com.kelvem.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.kelvem.common.profile.ProfileContext;
import com.kelvem.common.utils.RequestUtil;
import com.kelvem.common.utils.WebUtil;
import com.kelvem.sample.system.model.UserVisitLogModel;
import com.kelvem.sample.system.service.UserVisitLogService;

public class UrlVisitLogFilter implements Filter {
	
	private static Log log = LogFactory.getLog(UrlVisitLogFilter.class);

	@Autowired UserVisitLogService userVisitLogService;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		ProfileContext.push("UrlVisitLogFilter");
		this.invoke(request, response);
		ProfileContext.pop();
		
		chain.doFilter(request, response);
		
	}
	
	public void invoke(HttpServletRequest request, HttpServletResponse response) {

		try {
			
//			log.info("===================  doFilter start ======================");
			//			browser, 
			//			user_ip, 
			//			encoding, 
			//			content_length, 
			//			method_type, 
			//			protocol, 
			//			session_id, 
			//			url, 
			//			server_ip, 
			//			server_port, 
			//			project_name, 
			//			path, 
			//			query_string, 
			//			user_principal
			
//			log.info(ObjectUtil.getObjectInfo(servletRequest));
//			log.info("UrlVisitLogFilter : " + request.getServletPath());
//			log.info("UrlVisitLogFilter : " + request.getContextPath());
//			log.info("UrlVisitLogFilter : " + request.getServerName());
//			log.info("UrlVisitLogFilter : " + request.getLocalPort());
//			log.info("UrlVisitLogFilter : " + request.getMethod());
//			log.info("UrlVisitLogFilter : " + request.getProtocol());
//			log.info("UrlVisitLogFilter : " + request.getContentLength());
//			log.info("UrlVisitLogFilter : " + request.getCookies());
//			log.info("UrlVisitLogFilter : " + request.getRequestedSessionId());
//			log.info("UrlVisitLogFilter : " + request.getServletPath());
//			log.info("UrlVisitLogFilter : " + request.getQueryString());
//			log.info("UrlVisitLogFilter : " + request.getCharacterEncoding());

			userVisitLogService = WebUtil.getBean(UserVisitLogService.class);
			

			UserVisitLogModel model = new UserVisitLogModel();
			
			model.setUrl(request.getServletPath());
			model.setServerIp(request.getServerName());
			model.setServerPort(request.getLocalPort() + "");
			model.setProjectName(request.getServerName());
			model.setPath(request.getServletPath());
			model.setQueryString(request.getQueryString());
			
			model.setBrowser(request.getHeader("User-Agent"));
			model.setContentLength(request.getContentLength());
			model.setEncoding(request.getCharacterEncoding());
			model.setMethodType(request.getMethod());
			model.setProtocol(request.getProtocol());
			model.setSessionId(request.getRequestedSessionId());
			
			model.setUserIp("###$$$");
			model.setUserPrincipal("###$$$");
			StringBuilder sb = new StringBuilder();
			for (Cookie cookie : RequestUtil.getCookies(request)) {
				// log.info(cookie.getName() + "#" + cookie.getValue() + "#" + cookie.getPath() + "#" + cookie.getDomain() + "#" + cookie.getComment() + "#" + cookie.getMaxAge() + "#" + cookie.getSecure());
				sb.append("{" + cookie.getName() + "=" + cookie.getValue() + "}");
			}
			model.setCookie(sb.toString());
			
			userVisitLogService.saveUserVisitLog(model);
			
//			log.info("===================  doFilter  end   ======================");
			
		} catch (BeansException e) {
			log.error(e.getMessage(), e);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	public void destroy() {
		
	}

	
}
