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
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;

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

		try {
			sysRoleService = WebUtil.getBean(SysRoleService.class);
		} catch (BeansException e) {
			log.error(e.getMessage(), e);
		}
		
//		log.info("===================  doFilter start ======================");
		
		HttpSession session = request.getSession(true);
		
		Object loginUser = session.getAttribute("login_user");
		if (loginUser == null) {
			SysUserService sysUserService = WebUtil.getBean(SysUserService.class);
			loginUser = sysUserService.getSysUserByName("kelvem");
			session.setAttribute("login_user", loginUser);
		}
		
		if (session.getAttribute("auth_list") == null) {
			Set<SysRoleModel> roleSet = ((SysUserModel)loginUser).getSysRoleSet();
			List<SysRoleModel> allRoleSet = sysRoleService.queryAllSysRole();
			
			List<SysAuthorityModel> authList = new ArrayList<SysAuthorityModel>();
			for (SysRoleModel role : allRoleSet) {
				if (roleSet.contains(role)) {
					authList.addAll(role.getSysAuthoritySet());
				}
			}
			session.setAttribute("auth_list", authList);
		}
		
//		log.info("===================  doFilter  end   ======================");
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	public void destroy() {
		
	}

	
}
