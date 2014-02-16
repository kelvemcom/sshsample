package com.kelvem.common.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * <p> BaseAction </p>
 * 
 * @ClassName BaseAction
 * @version 1.0
 * 
 */
public class ActionBase extends ActionSupport implements SessionAware, RequestAware, ParameterAware {
	// implements SpringControlEnable
	
	private static Log log = LogFactory.getLog(ActionBase.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8041176574322258181L;
	
	public static final String LOGIN = "login";
	public static final String INDEX = "index";
	public static final String LIST = "list";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	public static final String ADD = "add";
	public static final String DETAIL = "detail";
	public static final String MODIFY = "modify";
	public static final String OK = "ok";
	public static final String REDIRECT_OK = "generalSuccess";
	public static final String JSON = "json";
		
	protected Map<String, Object> request = null;
	protected Map<String, Object> session = null;
	protected Map<String, String[]> parameters = null;
	
	public ActionBase(){
		
//		setBreadcrumb();
//		this.logRequest();
	}

	/**
	 * <p>得到web请求的request</p>
	 * 
	 * @param request
	 * @return void
	 * @see
	 */
	public void setRequest(Map<String, Object> request) {
		this.request = request;	
	}
	
	/**
	 * <p>得到web请求的session</p>
	 * 
	 * @param session
	 * @return void
	 * @see
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	/**
	 * <p>得到web请求的parameters</p>
	 * 
	 * @param parameters
	 * @return void
	 * @see
	 */
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * <p>获取ServletContext</p>
	 * 
	 * @return ServletContext
	 * @see
	 */
	protected ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	/**
	 * <p>获取HttpServletRequest</p>
	 * 
	 * @return HttpServletRequest
	 * @see
	 */
	protected HttpServletRequest getRequest() {
		try {
			return ServletActionContext.getRequest();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * <p>获取HttpServletResponse</p>
	 * 
	 * @return HttpServletResponse
	 * @see
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * <p>往HttpServletRequest里存入对象</p>
	 * 
	 * @param key
	 * @param value
	 * @return void
	 * @see
	 */
	protected void putRequest(String key, Object value) {
		request.put(key, value);
	}

	/**
	 * <p>从HttpServletRequest里获取对象</p>
	 * 
	 * @param key
	 * @return Object
	 * @see
	 */
	protected Object getRequest(String key) {
		return request.get(key);
	}

	/**
	 * <p>往HttpSession里存入对象</p>
	 * 
	 * @param key
	 * @param value
	 * @return void
	 * @see
	 */
	protected void putSession(String key, Object value) {
		if (session != null) {
			session.put(key, value);
		}
	}

	/**
	 * <p>从HttpSession里获取对象</p>
	 * 
	 * @param key
	 * @return Object
	 * @see
	 */
	protected Object getSession(String key) {
		if (session != null) {
			return session.get(key);
		} else {
			return null;
		}
	}
	
	/**
	 * <p>从Action上下文中获取参数</p>
	 * 
	 * @param key
	 * @return String[]
	 * @see
	 */
	protected String[] getParameters(String key) {
		return parameters.get(key);
	}

	/**
	 * <p>从Action上下文中获取参数</p>
	 * 
	 * @param key
	 * @return String
	 */
	protected String getParameter(String key) {
		return parameters.get(key)[0];
	}

	/**
	 * <p>往Action上下文中存入参数</p>
	 * 
	 * @param key
	 * @param value
	 * @return void
	 * @see
	 */
	protected void putParameter(String key, String value) {
		if (parameters.get(key) == null) {
			String[] param = new String[] { value };
			parameters.put(key, param);
		} else {
			String[] param = (String[]) parameters.get(key);
			param[0] = value;
		}
	}
	
	/**
	 * <p>得到web应用的绝对路径</p>
	 * 
	 * @return String 路径信息
	 * @see
	 */
	protected String getWebAbsolutePath() {
		String realPath = getServletContext().getRealPath("/");
		if (realPath != null) {
			if (!realPath.endsWith("/"))
				return realPath + "/";
		}
		return realPath;
	}
	
	/**
	 * <p>获取全局页面大小</p>
	 * 
	 * @param key
	 * @return int
	 */
	protected int getGlobalPageSize(String key) {
		String size = this.getText(key);
		return size == null ? 15 : Integer.parseInt(size);
	}

	
	// ---------------------------------------------
	//       MSG
	// ---------------------------------------------
	
	/**
	 * <p>返回JS弹出窗口</p>
	 * 
	 * @param msg  提示文本
	 * @throws IOException
	 * @return String
	 */
	private String showMsg = "";
//	public void showMsg(String msg) throws IOException {
//		StringBuffer sb = new StringBuffer();
//		sb.append("<script type='text/javascript'>");
//		sb.append("	$(document).ready(function(){");
//		sb.append("		var msg = '" + msg + "';");
//		sb.append("		if ($('#msgShow').html() == undefined) {");
//		sb.append("			alert(msg);");
//		sb.append("		} else {");
//		sb.append("   			$('#msgShow').text(msg);");
//		sb.append("			$('#msgDiv').modal({keyboard : false,show : true});");
//		sb.append("		}");
//		sb.append("	});");
//		sb.append("</script>");
//
//		this.getResponse().setCharacterEncoding("utf-8");
//		this.getResponse().getWriter().println(sb);
//		this.getResponse().getWriter().println("<br><br><br><br><br><br><br><br><br>");
//		this.getResponse().flushBuffer();
//	}

	public void showMsg(String msg) throws IOException {
		log.info("setShowMsg#" + msg);
		this.setShowMsg(msg);
	}

	public String getShowMsg() {
		return this.showMsg;
	}

	public void setShowMsg(String showMsg) {
		this.showMsg = showMsg;
	}
	
	// ---------------------------------------------
	//    文件
	// ---------------------------------------------

	/**
	 * <p>文件下载</p>
	 * @param filePathAndName：要下载的文件在服务器端的路径和文件名
	 * @param contentType：文件类型，默认为二进制流
	 * @return void
	 * @see
	 */
	protected void downloadFile(String filePathAndName, String contentType) throws Exception {
		File file = new File(filePathAndName.toString());
		if (file.exists()) {
			this.getResponse().reset(); 
			if (contentType == null || contentType.trim().equals("")) {
				contentType = "application/octet-stream";
			}
			this.getResponse().setContentType(contentType + ";charset=UTF-8");
			this.getResponse().addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(file.getName(), "UTF-8"));
			//客户端不缓存
			this.getResponse().addHeader("Pragma", "no-cache");
			this.getResponse().addHeader("Cache-Control", "no-cache");
			
		    BufferedInputStream bis = null;
		    BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(filePathAndName.toString()));
			    bos = new BufferedOutputStream(this.getResponse().getOutputStream());
				byte[] buff = new byte[2048];
				int bytesRead;
				while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
					bos.write(buff, 0, bytesRead);
				}
				bos.flush();
			} catch (Exception e) {
				throw e;
			} finally {
				if (file != null) {
					file.delete();
	        	}
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			}
		}
	}


	
	// ---------------------------------------------
	//    JSON
	// ---------------------------------------------
	private Object jsonData;

	public Object getJsonData() {
		return jsonData;
	}

	public void setJsonData(Object jsonData) {
		this.jsonData = jsonData;
	}
	
	
	// ---------------------------------------------
	//    PageResult
	// ---------------------------------------------

	/**
	 * 页码-分页使用
	 */
	private int pageNo;
	/**
	 * 每页大小-分页使用
	 */
	private int pageSize = 10;
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 总数
	 */
	private long iTotalRecords = 100;
	/**
	 * 一页大小
	 */
	private long iTotalDisplayRecords = 100;
	
	public long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	
	
	// ---------------------------------------------
	//    log Request
	// ---------------------------------------------

	public void logRequest() {
		
		log.info("QueryString = " + this.getRequest().getQueryString());
		log.info("RequestURI = " + this.getRequest().getRequestURI());
		
		this.logRequestAttributes();

		this.logRequestParameters();
		
		this.logRequestHeaders();

	}

	@SuppressWarnings("unchecked")
	public void logRequestAttributes() {
		
		log.info("【AttributeNames】");
		Enumeration<String> e = this.getRequest().getAttributeNames();
		for (String key = ""; e.hasMoreElements();) {
			key = e.nextElement();
			log.info(key + " : " + this.getRequest().getAttribute(key));
		}
	}

	@SuppressWarnings("unchecked")
	public void logRequestParameters() {

		log.info("【ParameterNames】");
		Enumeration<String> ep = this.getRequest().getParameterNames();
		for (String key = ""; ep.hasMoreElements();) {
			key = ep.nextElement();
			log.info(key + " : " + this.getRequest().getParameter(key));
		}
	}

	@SuppressWarnings("unchecked")
	public void logRequestHeaders() {

		log.info("【HeaderNames】");
		Enumeration<String> eh = this.getRequest().getHeaderNames();
		for (String key = ""; eh.hasMoreElements();) {
			key = eh.nextElement();
			log.info(key + " : " + this.getRequest().getHeader(key));
		}
	}
	
	
	// ---------------------------------------------
	//    menu auth
	// ---------------------------------------------
	
	public boolean isMenu(String uri){
		return uri.toLowerCase().contains("list");
	}
	
	
	// ---------------------------------------------
	//    breadcrumb
	// ---------------------------------------------

	private String breadcrumb;
	
	@SuppressWarnings("unchecked")
	public String getBreadcrumb(){

		List<String[]> listBreadcrumb = null;
		
		HttpServletRequest req = this.getRequest();
		if (req == null) {
			return "";
		}
		
		String referer = req.getHeader("referer");
		this.putSession("referer", referer);
		
		String url = req.getRequestURL().toString();
		
		if (this.session.get("list_breadcrumb") != null) {
			listBreadcrumb = (List<String[]>)this.session.get("list_breadcrumb");
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
		
		this.session.put("list_breadcrumb", listBuf);
		
//		breadcrumb = 
//"<ol class='breadcrumb'>" +
//"  <li><a href='#'>Home</a></li>" +
//"  <li><a href='#'>Library</a></li>" +
//"  <li class='active'>Data</li>" +
//"</ol>";
		
		breadcrumb = "<ol class='breadcrumb'  style='background-color: #dddddd;'>";
		for (String[] vals : listBreadcrumb) {
			breadcrumb += "<li><a href='" + vals[1] + "'>" + vals[0] + "</a></li>";
		}
		breadcrumb += "</ol>";
		
		log.debug("breadcrumb = " + breadcrumb);
		return breadcrumb;
	}
}
