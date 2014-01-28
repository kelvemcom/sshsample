package com.kelvem.common.filter;

import java.io.IOException;
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

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.kelvem.common.utils.RequestUtil;
import com.kelvem.common.utils.WebUtil;
import com.kelvem.sample.system.cache.MenuCache;
import com.kelvem.sample.system.model.MenuTree;
import com.kelvem.sample.system.model.SysAuthorityModel;
import com.kelvem.sample.system.model.SysRoleModel;

public class UrlAuthAccessFilter implements Filter {
	
	private static Log log = LogFactory.getLog(UrlAuthAccessFilter.class);

	@Autowired MenuCache menuCache;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		try {
			menuCache = WebUtil.getBean(MenuCache.class);
		} catch (BeansException e) {
			log.error(e.getMessage(), e);
		}
		
//		log.info("===================  doFilter start ======================");
		HttpSession session = request.getSession(true);
		
		MenuTree menuTree = menuCache.getMenuTree();
		session.setAttribute("json_menu_tree", menuTree.toJson());
		
		String url = RequestUtil.getUrl(request);
		if (session.getAttribute("json_curr_menu") == null) {
			session.setAttribute("json_curr_menu", new JSONObject());
		} else {
			SysRoleModel currMenu = menuCache.getCurrMenu(url);
			if (currMenu != null) {
				JSONObject jsonCurrMenu = new JSONObject();
				jsonCurrMenu.element("menu_level1", currMenu.getMenuLevel1());
				jsonCurrMenu.element("menu_level2", currMenu.getMenuLevel2());
				jsonCurrMenu.element("menu_level3", currMenu.getMenuLevel3());
				jsonCurrMenu.element("menu_level4", currMenu.getMenuLevel4());
				session.setAttribute("json_curr_menu", jsonCurrMenu);
			}
		}
		
		if (session.getAttribute("auth_list") == null) {
			throw new RuntimeException("Confirm UserLoginAccessFilter. session.auth_list can't be null");
		}

		boolean access = false;
		List<SysAuthorityModel> authList = (List<SysAuthorityModel>)session.getAttribute("auth_list");
		for (SysAuthorityModel auth : authList) {
			if (url.startsWith(auth.getSysAuthorityUrl())) {
				access = true;
				break;
			}
		}
		
		if (access == false && !url.startsWith("/403.jsp")) {
			String contextPath = RequestUtil.getContextPath(request);
			response.sendRedirect(contextPath + "/403.jsp");
		} else {
			chain.doFilter(request, response);
		}
		
//		log.info("===================  doFilter  end   ======================");
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	public void destroy() {
		
	}

	
}
