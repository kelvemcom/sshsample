/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2010 - 2012
 * 文件：com.kelvem.sample.system.queryvo.UserVisitLogInVO.java
 * 所含类: UserVisitLogInVO.java
 * 修改记录：
 * 日期				作者						内容
 * =============================================================
 * 2014-01-06	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.queryvo;

import java.util.Date;


/**
 * <p>UserVisitLogInVO</p>
 * 
 * <p>用户访问记录InVO</p>
 * 
 * <p>Copyright: 版权所有 (c) 2010 - 2012</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName UserVisitLogInVO
 * @author kelvem
 * @version 1.0
 */
public class UserVisitLogInVO implements java.io.Serializable {

	private static final long serialVersionUID = -1L;

	// Fields
	private Integer userVisitLogId;

	private String url;
	private String encoding;
	private Integer contentLength;
	private String methodType;
	private String protocol;
	private String serverIp;
	private String serverPort;
	private String projectName;
	private String path;
	private String queryString;
	private String userPrincipal;
	private String userIp;
	private String sessionId;
	private String browser;
	private String cookie;


	/** default constructor */
	public UserVisitLogInVO() {
	}

	
	public Integer getUserVisitLogId() {
		return this.userVisitLogId;
	}
	public void setUserVisitLogId(Integer userVisitLogId) {
		this.userVisitLogId = userVisitLogId;
	}
	
	
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getEncoding() {
		return this.encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	public Integer getContentLength() {
		return this.contentLength;
	}
	public void setContentLength(Integer contentLength) {
		this.contentLength = contentLength;
	}
	
	public String getMethodType() {
		return this.methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	
	public String getProtocol() {
		return this.protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	public String getServerIp() {
		return this.serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	
	public String getServerPort() {
		return this.serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	
	public String getProjectName() {
		return this.projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getPath() {
		return this.path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getQueryString() {
		return this.queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
	public String getUserPrincipal() {
		return this.userPrincipal;
	}
	public void setUserPrincipal(String userPrincipal) {
		this.userPrincipal = userPrincipal;
	}
	
	public String getUserIp() {
		return this.userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	
	public String getSessionId() {
		return this.sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getBrowser() {
		return this.browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	public String getCookie() {
		return this.cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
}
