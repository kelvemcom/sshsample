/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2010 - 2012
 * 文件：com.kelvem.sample.system.queryvo.SysUserInVO.java
 * 所含类: SysUserInVO.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-09	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.queryvo;

import java.util.Date;


/**
 * <p>SysUserInVO</p>
 *
 * <p>用户表InVO</p>
 *
 * <p>Copyright: 版权所有 (c) 2010 - 2012</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysUserInVO
 * @author kelvem
 * @version 1.0
 *
 */
public class SysUserInVO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	
	// Fields
	private Integer sysUserId;

	private String sysUserName;
	private String userLogonName;
	private Integer userTypeCode;
	private String userDescs;
	private Integer statusCode;
	private Integer delFlag;
	private Date createTime;

	/** default constructor */
	public SysUserInVO() {
	}

	
	public Integer getSysUserId() {
		return this.sysUserId;
	}
	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}
	
	
	public String getSysUserName() {
		return this.sysUserName;
	}
	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}
	
	public String getUserLogonName() {
		return this.userLogonName;
	}
	public void setUserLogonName(String userLogonName) {
		this.userLogonName = userLogonName;
	}
	
	public Integer getUserTypeCode() {
		return this.userTypeCode;
	}
	public void setUserTypeCode(Integer userTypeCode) {
		this.userTypeCode = userTypeCode;
	}
	
	public String getUserDescs() {
		return this.userDescs;
	}
	public void setUserDescs(String userDescs) {
		this.userDescs = userDescs;
	}
	
	public Integer getStatusCode() {
		return this.statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	public Integer getDelFlag() {
		return this.delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
