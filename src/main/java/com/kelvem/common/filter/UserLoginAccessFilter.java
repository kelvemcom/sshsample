package com.kelvem.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;

import com.kelvem.common.profile.ProfileContext;
import com.kelvem.common.utils.SessionUtils;
import com.kelvem.common.utils.WebUtil;
import com.kelvem.sample.system.model.SysAuthorityModel;
import com.kelvem.sample.system.model.SysRoleModel;
import com.kelvem.sample.system.model.SysUserModel;
import com.kelvem.sample.system.service.SysRoleService;
import com.kelvem.sample.system.service.SysUserService;

public class UserLoginAccessFilter implements Filter {
	
	private static Log log = LogFactory.getLog(UserLoginAccessFilter.class);

	private SysRoleService sysRoleService;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		ProfileContext.push("UserLoginAccessFilter");
		this.invoke(request, response);
		ProfileContext.pop();
		
		chain.doFilter(request, response);
		
	}
	
	public void invoke(HttpServletRequest request, HttpServletResponse response) {

		try {
			sysRoleService = WebUtil.getBean(SysRoleService.class);
		} catch (BeansException e) {
			log.error(e.getMessage(), e);
		}
		
//		log.info("===================  doFilter start ======================");
		
		SysUserModel loginUser = SessionUtils.getLoginUser(request);
		if (loginUser == null) {
			SysUserService sysUserService = WebUtil.getBean(SysUserService.class);
			loginUser = sysUserService.getSysUserByName("kelvem");
			SessionUtils.setLoginUser(request, loginUser);
		}
		
		if (SessionUtils.getAuthList(request) == null) {
			Set<SysRoleModel> roleSet = loginUser.getSysRoleSet();
			List<SysRoleModel> allRoleSet = sysRoleService.queryAllSysRole();
			
			List<SysAuthorityModel> authList = new ArrayList<SysAuthorityModel>();
			for (SysRoleModel role : allRoleSet) {
				if (roleSet.contains(role)) {
					authList.addAll(role.getSysAuthoritySet());
				}
			}
			SessionUtils.setAuthList(request, authList);
		}
		
//		log.info("===================  doFilter  end   ======================");
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	public void destroy() {
		
	}

	
}
