/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.model.SysUserModel.java
 * 所含类: SysUserModel.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-10	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.model;

import java.util.Date;


/**
 * <p>SysUserModel</p>
 *
 * <p>用户Model</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysUserModel
 * @author kelvem
 * @version 1.0
 *
 */
public class SysUserModel implements java.io.Serializable {

	private static final long serialVersionUID = -1L;
	
	// Fields
	private Integer sysUserId;

	private String sysUserName;
	private String userLogonName;
	private String userPassword;
	private Integer userTypeCode;
	private Integer personInfoId;
	private String userDescs;
	private Integer statusCode;
	private Date statusChangeTime;
	private Integer delFlag;
	private Date delTime;
	private Date createTime;
	private Date updateTime;


	/** default constructor */
	public SysUserModel() {
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
	
	public String getUserPassword() {
		return this.userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public Integer getUserTypeCode() {
		return this.userTypeCode;
	}
	public void setUserTypeCode(Integer userTypeCode) {
		this.userTypeCode = userTypeCode;
	}
	
	public Integer getPersonInfoId() {
		return this.personInfoId;
	}
	public void setPersonInfoId(Integer personInfoId) {
		this.personInfoId = personInfoId;
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
	
	public Date getStatusChangeTime() {
		return this.statusChangeTime;
	}
	public void setStatusChangeTime(Date statusChangeTime) {
		this.statusChangeTime = statusChangeTime;
	}
	
	public Integer getDelFlag() {
		return this.delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	public Date getDelTime() {
		return this.delTime;
	}
	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	

}
