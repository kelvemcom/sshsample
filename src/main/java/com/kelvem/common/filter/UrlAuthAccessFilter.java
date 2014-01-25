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

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.kelvem.common.utils.RequestUtil;
import com.kelvem.common.utils.WebUtil;
import com.kelvem.sample.system.cache.MenuCache;
import com.kelvem.sample.system.model.MenuTree;
import com.kelvem.sample.system.model.SysRoleModel;

public class UrlAuthAccessFilter implements Filter {
	
	private static Log log = LogFactory.getLog(UrlAuthAccessFilter.class);

	@Autowired MenuCache menuCache;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		try {
//			log.info("===================  doFilter start ======================");
			
			menuCache = WebUtil.getBean(MenuCache.class);
			HttpSession session = request.getSession(true);
			
			MenuTree menuTree = menuCache.getMenuTree();
			session.setAttribute("json_menu_tree", menuTree.toJson());
			
			if (session.getAttribute("json_curr_menu") == null) {
				session.setAttribute("json_curr_menu", new JSONObject());
			} else {
				SysRoleModel currMenu = menuCache.getCurrMenu(RequestUtil.getUrl(request));
				if (currMenu != null) {
					JSONObject jsonCurrMenu = new JSONObject();
					jsonCurrMenu.element("menu_level1", currMenu.getMenuLevel1());
					jsonCurrMenu.element("menu_level2", currMenu.getMenuLevel2());
					jsonCurrMenu.element("menu_level3", currMenu.getMenuLevel3());
					jsonCurrMenu.element("menu_level4", currMenu.getMenuLevel4());
					session.setAttribute("json_curr_menu", jsonCurrMenu);
				}
			}
			
//			log.info("===================  doFilter  end   ======================");
		} catch (BeansException e) {
			log.error(e.getMessage(), e);
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	public void destroy() {
		
	}

	
}
