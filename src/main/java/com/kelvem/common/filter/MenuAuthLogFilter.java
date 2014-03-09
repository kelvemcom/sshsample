package com.kelvem.common.filter;

import java.io.IOException;

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
import org.springframework.beans.factory.annotation.Autowired;

import com.kelvem.common.utils.RequestUtil;
import com.kelvem.common.utils.WebUtil;
import com.kelvem.sample.system.model.SysRoleModel;
import com.kelvem.sample.system.service.SysAuthorityService;
import com.kelvem.sample.system.service.SysMenuService;

public class MenuAuthLogFilter implements Filter {
	
	private static Log log = LogFactory.getLog(MenuAuthLogFilter.class);

	@Autowired SysAuthorityService sysAuthorityService;
	@Autowired SysMenuService sysMenuService;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		this.doFilter(request, response);
		
		chain.doFilter(request, response);
	}
	
	public void doFilter(HttpServletRequest request, HttpServletResponse response) {
		
		try {
//			log.info("===================  doFilter start ======================");
			
			String url = RequestUtil.getUrl(request);
			if (sysAuthorityService.getAuthControlledFlag(url) == false) {
				return;
			}
			log.debug("MenuAuthLogFilter : " + url);
			
			HttpSession session = request.getSession(true);
			
			/**
			 * 记录菜单与auth的关系
			 */
			// 从菜单访问或地址栏的url为菜单地址
			SysRoleModel urlRole = sysMenuService.getSysMenu(url);
			if (urlRole != null) {
				// 把当前菜单记录到session中
				session.setAttribute("currMenuUrl", urlRole);
				// 记录菜单与权限的关系
				sysAuthorityService.recordMenuWithAuthMapping(urlRole, url);
				return;
			}
			
			// 从地址栏直接访问,且url是不菜单地址
			String referer = RequestUtil.getReferer(request);
			if (referer == null) {
				// 把当前菜单记录在session中清除
				session.setAttribute("currMenuUrl", null);
				return;
			}
			
			SysRoleModel currSysMenu = (SysRoleModel)session.getAttribute("currMenuUrl");
			if (currSysMenu == null) {
				// 未点击菜单，则什么都不做
			} else {
				// 已经点击过菜单, 则记录菜单与权限的关系
				sysAuthorityService.recordMenuWithAuthMapping(currSysMenu, url);
			}
			
//			log.info("===================  doFilter  end   ======================");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		sysAuthorityService = WebUtil.getBean(SysAuthorityService.class);
		sysMenuService = WebUtil.getBean(SysMenuService.class);
	}
	
	public void destroy() {
		
	}

//select 
//	r.sys_role_id, r.sys_role_name, r.menu_url, 
//	a.sys_authority_id, a.sys_authority_name, a.sys_authority_type,a.sys_authority_url 
//from sys_authority a, sys_role r, sys_role_authority_mapping ra
//where a.sys_authority_id =ra.sys_authority_id
//and r.sys_role_id = ra.sys_role_id
//;

}
