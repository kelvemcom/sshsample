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

	// Constructors
		private Integer userId;

		private String userNickName;
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
	public SysUserInVO() {
	}

		public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


		public String getUserNickName() {
		return this.userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
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
