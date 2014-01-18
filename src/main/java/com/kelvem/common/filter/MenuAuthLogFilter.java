package com.kelvem.common.filter;

import java.io.IOException;
import java.util.HashSet;
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
import org.springframework.beans.factory.annotation.Autowired;

import com.kelvem.common.utils.AuthUtil;
import com.kelvem.common.utils.RequestUtil;
import com.kelvem.common.utils.WebUtil;
import com.kelvem.sample.system.model.SysAuthorityModel;
import com.kelvem.sample.system.model.SysRoleModel;
import com.kelvem.sample.system.service.SysAuthorityService;
import com.kelvem.sample.system.service.SysMenuService;
import com.kelvem.sample.system.service.SysRoleService;

public class MenuAuthLogFilter implements Filter {
	
	private static Log log = LogFactory.getLog(MenuAuthLogFilter.class);

	@Autowired SysAuthorityService sysAuthorityService;
	@Autowired SysRoleService sysRoleService;
	@Autowired SysMenuService sysMenuService;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		try {
			
//			log.info("===================  doFilter start ======================");
			sysAuthorityService = WebUtil.getBean(SysAuthorityService.class);
			sysRoleService = WebUtil.getBean(SysRoleService.class);
			sysMenuService = WebUtil.getBean(SysMenuService.class);
			
			String url = RequestUtil.getUrl(request);
			log.debug("MenuAuthLogFilter : " + url);

			HttpSession session = request.getSession(true);
			SysRoleModel sysMenu = sysMenuService.getSysMenu(url);
			SysRoleModel currSysMenu = (SysRoleModel)session.getAttribute("currMenuUrl");
			
			String referer = RequestUtil.getReferer(request);
			if (sysMenu != null) {
				// 从菜单访问, 把当前菜单记录到session中
				session.setAttribute("currMenuUrl", sysMenu);
				// 记录菜单与权限的关系
				recordMenuWithAuthMapping(sysMenu, url);
			} else if (referer == null) {
				// 从地址栏直接访问
				session.setAttribute("currMenuUrl", null);
			} else if (currSysMenu == null) {
				// 未点击菜单，则什么都不做
			} else {
				// 已经点击过菜单, 则记录菜单与权限的关系
				recordMenuWithAuthMapping(currSysMenu, url);
			}
			
//			log.info("===================  doFilter  end   ======================");
		} catch (BeansException e) {
			log.error(e.getMessage(), e);
		}
		
		chain.doFilter(request, response);

	}
	
	private void recordMenuWithAuthMapping(SysRoleModel currSysMenu, String url){

		List<SysAuthorityModel> list = sysAuthorityService.querySysAuthority(null, null, url);

		SysAuthorityModel sysAuthority = null;
		
		if (list == null || list.size() <= 0) {
			sysAuthority = AuthUtil.createSysAuthorityModel(url);
			sysAuthorityService.saveSysAuthority(sysAuthority);
		} else {
			sysAuthority = list.get(0);
		}
		
		// Save relationship
		Set<SysAuthorityModel> sysAuthoritySet = currSysMenu.getSysAuthoritySet();

		for (SysAuthorityModel sysAuthorityModel : sysAuthoritySet) {
			if (sysAuthorityModel.getSysAuthorityUrl().equals(sysAuthority.getSysAuthorityUrl())) {
				// 已经有菜单和权限的关系，则直接结束
				return;
			}
		}

		HashSet<SysAuthorityModel> buf = new HashSet<SysAuthorityModel>();
		buf.addAll(sysAuthoritySet);
		buf.add(sysAuthority);
		
		for (SysAuthorityModel sysAuthorityModel : buf) {
			log.debug("sysAuthorityModel id=" + sysAuthorityModel.getSysAuthorityId() + ",  " + sysAuthorityModel.getSysAuthorityUrl());
		}
		
		currSysMenu.setSysAuthoritySet(buf);
		sysRoleService.updateSysRole(currSysMenu);
		log.info("记录菜单与权限的关系 SysRole(" + currSysMenu.getMenuUrl() + ")与SysAuthority(" + url + ")");
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	public void destroy() {
		
	}

	
}
