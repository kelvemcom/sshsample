/**============================================================
   * 版权： kelvem 版权所有 (c) 2012 - 2013
 * 文件：com.kelvem.common.interceptor.SampleInterceptor.java
 * 所含类: SampleInterceptor
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-21       kelvem       创建文件，实现基本功能
 * ============================================================*/

package com.kelvem.common.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * <p>SampleInterceptor</p>
 *
 * <p>Sample拦截器</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2013</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName SampleInterceptor
 * @author kelvem
 * @version 1.0
 */

public class SampleInterceptor extends AbstractInterceptor {
	
	private static final Logger log = Logger.getLogger(SampleInterceptor.class);

	private static final long serialVersionUID = 7252503572910147463L;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			ServletActionContext.getRequest().setAttribute("user_name", "kelvem");
		} catch (Exception e) {
			log.error("SampleInterceptor处理失败" + ServletActionContext.getRequest().getRequestURI(), e);
		}
		return invocation.invoke();
	}
	
	
	// ---------------------------------------------
	//    breadcrumb
	// ---------------------------------------------

	@SuppressWarnings("unchecked")
	public void addSysAuthority(ActionInvocation invocation){

		String breadcrumb = "";
		List<String[]> listBreadcrumb = null;
		
		HttpServletRequest req = ServletActionContext.getRequest();
		if (req == null) {
			return;
		}
		HttpSession session = req.getSession(true);
		
		String referer = req.getHeader("referer");
		session.setAttribute("referer", referer);
		
		System.out.println("uri : " + req.getRequestURI());
		String url = req.getRequestURL().toString();
		
		if (session.getAttribute("list_breadcrumb") != null) {
			listBreadcrumb = (List<String[]>)session.getAttribute("list_breadcrumb");
		}
		if (listBreadcrumb == null) {
			 listBreadcrumb = new ArrayList<String[]>();
		}
		if (this.isMenu(url)) {
			listBreadcrumb.clear();
		}
		
		String[] val = new String[2];
		val[0] = "AAA";
		if (url.contains("Add")) {
			val[0] = "add";
		} else if (url.contains("List")) {
			val[0] = "list";
		} else if (url.contains("Update")) {
			val[0] = "update";
		} else if (url.contains("Detail")) {
			val[0] = "detail";
		}
		
		val[1] = url;

		listBreadcrumb.add(val);
		
		boolean isContain = false;
		List<String[]> listBuf = new ArrayList<String[]>();
		for (String[] vals : listBreadcrumb) {
			if (!isContain) {
				listBuf.add(vals);
			}
			if (url.equalsIgnoreCase(vals[1])) {
				isContain = true;
			}
		}
		
		session.setAttribute("list_breadcrumb", listBuf);
		
//		breadcrumb = 
//"<ol class='breadcrumb'>" +
//"  <li><a href='#'>Home</a></li>" +
//"  <li><a href='#'>Library</a></li>" +
//"  <li class='active'>Data</li>" +
//"</ol>";
		
		breadcrumb = "<ol class='breadcrumb'>";
		for (String[] vals : listBreadcrumb) {
			breadcrumb += "<li><a href='" + vals[1] + "'>" + vals[0] + "</a></li>";
		}
		breadcrumb += "</ol>";
		
		System.out.println("###########" + breadcrumb);
//		req.setAttribute("breadcrumb", breadcrumb);

	}
	
	public boolean isMenu(String uri){
		return uri.toLowerCase().contains("list");
	}
	
	
}
