package com.kelvem.common.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class ActionBase0713 extends ActionSupport implements SessionAware, RequestAware, ParameterAware {
	// implements SpringControlEnable
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8041176574322258181L;
	
	public static final String LOGIN = "login";
	public static final String VIEW = "list";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	public static final String ADD = "add";
	public static final String DETAIL = "detail";
	public static final String modify = "modify";
	public static final String OK = "ok";
	public static final String REDIRECT_OK = "generalSuccess";
	public static final String JSON = "json";
		
	protected Map<String, Object> request = null;
	protected Map<String, Object> session = null;
	protected Map<String, String[]> parameters = null;
	
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
		return ServletActionContext.getRequest();
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
	protected void requestPut(String key, Object value) {
		request.put(key, value);
	}

	/**
	 * <p>从HttpServletRequest里获取对象</p>
	 * 
	 * @param key
	 * @return Object
	 * @see
	 */
	protected Object requestGet(String key) {
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
	protected void sessionPut(String key, Object value) {		
		session.put(key, value);
	}

	/**
	 * <p>从HttpSession里获取对象</p>
	 * 
	 * @param key
	 * @return Object
	 * @see
	 */
	protected Object sessionGet(String key) {
		return session.get(key);
	}
	
	/**
	 * <p>从Action上下文中获取参数</p>
	 * 
	 * @param key
	 * @return String[]
	 * @see
	 */
	protected String[] parametersGets(String key) {
		return parameters.get(key);
	}

	/**
	 * <p>从Action上下文中获取参数</p>
	 * 
	 * @param key
	 * @return String
	 */
	protected String parametersGet(String key) {
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
	protected void parametersPut(String key, String value) {
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
		System.out.println("setShowMsg#" + msg);
		this.setShowMsg(msg);
	}

	public String getShowMsg() {
		System.out.println("getShowMsg#" + showMsg);
		return this.showMsg;
	}

	public void setShowMsg(String showMsg) {
		this.showMsg = showMsg;
	}

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
	
//	private String jsonData;
//
//	public String getJsonData() {
//		return jsonData;
//	}
//
//	public void setJsonData(String jsonData) {
//		this.jsonData = jsonData;
//	}

}
