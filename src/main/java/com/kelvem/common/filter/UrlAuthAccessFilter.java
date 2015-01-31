package com.kelvem.common.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.kelvem.common.profile.ProfileContext;
import com.kelvem.common.utils.RequestUtil;
import com.kelvem.common.utils.SessionUtils;
import com.kelvem.common.utils.WebUtil;
import com.kelvem.sample.system.cache.MenuCache;
import com.kelvem.sample.system.model.MenuTree;
import com.kelvem.sample.system.model.SysAuthorityModel;
import com.kelvem.sample.system.model.SysRoleModel;
import com.kelvem.sample.system.model.SysUserModel;

public class UrlAuthAccessFilter implements Filter {
	
	private static Log log = LogFactory.getLog(UrlAuthAccessFilter.class);

	@Autowired MenuCache menuCache;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		ProfileContext.push("UrlAuthAccessFilter");
		boolean access = this.checkAccess(request, response);
		ProfileContext.pop();
		
//		if (access == false) {
//			String contextPath = RequestUtil.getContextPath(request);
//			response.sendRedirect(contextPath + "/403.jsp");
//		} else {
			chain.doFilter(request, response);
//		}
		
	}
	
	public boolean checkAccess(HttpServletRequest request, HttpServletResponse response) {
		
//		log.info("===================  doFilter start ======================");
		SysUserModel sysUser = SessionUtils.getLoginUser(request);
		MenuTree menuTree = menuCache.getMenuTree(sysUser.getSysRoleSet());
		SessionUtils.setMenuTree(request, menuTree);
		
		String url = RequestUtil.getUrl(request);
			
		SysRoleModel currMenu = menuCache.getCurrMenu(url);
		if (currMenu != null) {
			JSONObject jsonCurrMenu = new JSONObject();
			jsonCurrMenu.put("menu_level1", currMenu.getMenuLevel1());
			jsonCurrMenu.put("menu_level2", currMenu.getMenuLevel2());
			jsonCurrMenu.put("menu_level3", currMenu.getMenuLevel3());
			jsonCurrMenu.put("menu_level4", currMenu.getMenuLevel4());
			jsonCurrMenu.put("url", url);
			SessionUtils.setCurrMenu(request, jsonCurrMenu);
		} else {
			//SessionUtils.setCurrMenu(request, new JSONObject());
		}

		boolean access = false;
		if (url.startsWith("/403.jsp")) {
			access = true;
		} else if (url.trim().endsWith(".jsp") 
				|| url.trim().endsWith(".html") 
				|| url.trim().endsWith(".htm") 
				|| url.trim().endsWith(".do") 
				|| url.trim().endsWith(".action")) {
			
			if (SessionUtils.getAuthList(request) == null) {
				throw new RuntimeException("Confirm UserLoginAccessFilter. session.auth_list can't be null");
			}
			List<SysAuthorityModel> authList = (List<SysAuthorityModel>)SessionUtils.getAuthList(request);
			for (SysAuthorityModel auth : authList) {
				if (url.startsWith(auth.getSysAuthorityUrl())) {
					access = true;
					break;
				}
			}
		} else {
			access = true;
		}
		
		return access;
		
//		log.info("===================  doFilter  end   ======================");
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {

		log.info("com.kelvem.common.filter.UrlAuthAccessFilter init");

		try {
			menuCache = WebUtil.getBean(MenuCache.class);
		} catch (BeansException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	public void destroy() {
		log.info("com.kelvem.common.filter.UrlAuthAccessFilter destroy");
	}

	
}
