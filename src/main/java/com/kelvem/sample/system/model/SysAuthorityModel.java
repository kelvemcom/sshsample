/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.model.SysAuthorityModel.java
 * 所含类: SysAuthorityModel.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-24	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.model;

import java.util.Date;


/**
 * <p>SysAuthorityModel</p>
 *
 * <p>系统权限Model</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysAuthorityModel
 * @author kelvem
 * @version 1.0
 *
 */
public class SysAuthorityModel implements java.io.Serializable {

	private static final long serialVersionUID = -1L;
	
	// Fields
	private Integer sysAuthorityId;

	private String sysAuthorityName;
	private String sysAuthorityDesc;
	private String sysAuthorityUrl;
	private String sysAuthorityType;
	private String controlType;
	private Integer statusCode;
	private Date statusChangeTime;
	private Integer delFlag;
	private Date delTime;
	private Date createTime;
	private Date updateTime;


	/** default constructor */
	public SysAuthorityModel() {
	}
	
	public Integer getSysAuthorityId() {
		return this.sysAuthorityId;
	}
	public void setSysAuthorityId(Integer sysAuthorityId) {
		this.sysAuthorityId = sysAuthorityId;
	}
	
	
	public String getSysAuthorityName() {
		return this.sysAuthorityName;
	}
	public void setSysAuthorityName(String sysAuthorityName) {
		this.sysAuthorityName = sysAuthorityName;
	}
	
	public String getSysAuthorityDesc() {
		return this.sysAuthorityDesc;
	}
	public void setSysAuthorityDesc(String sysAuthorityDesc) {
		this.sysAuthorityDesc = sysAuthorityDesc;
	}
	
	public String getSysAuthorityUrl() {
		return this.sysAuthorityUrl;
	}
	public void setSysAuthorityUrl(String sysAuthorityUrl) {
		this.sysAuthorityUrl = sysAuthorityUrl;
	}
	
	public String getSysAuthorityType() {
		return this.sysAuthorityType;
	}
	public void setSysAuthorityType(String sysAuthorityType) {
		this.sysAuthorityType = sysAuthorityType;
	}
	
	public String getControlType() {
		return this.controlType;
	}
	public void setControlType(String controlType) {
		this.controlType = controlType;
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
