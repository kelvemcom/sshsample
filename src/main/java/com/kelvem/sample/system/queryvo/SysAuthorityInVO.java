/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2010 - 2012
 * 文件：com.kelvem.sample.system.queryvo.SysAuthorityInVO.java
 * 所含类: SysAuthorityInVO.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-09	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.queryvo;

import java.util.Date;


/**
 * <p>SysAuthorityInVO</p>
 *
 * <p>系统权限表InVO</p>
 *
 * <p>Copyright: 版权所有 (c) 2010 - 2012</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysAuthorityInVO
 * @author kelvem
 * @version 1.0
 *
 */
public class SysAuthorityInVO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;

	// Constructors
		private Integer authorityId;

		private String authorityName;
	private String authorityDesc;
	private String authorityUrl;
	private String statusCode;
	private Date statusChangeTime;
	private Integer delFlag;
	private Date delTime;
	private Date createTime;
	private Date updateTime;

	/** default constructor */
	public SysAuthorityInVO() {
	}

		public Integer getAuthorityId() {
		return this.authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}


		public String getAuthorityName() {
		return this.authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDesc() {
		return this.authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	public String getAuthorityUrl() {
		return this.authorityUrl;
	}

	public void setAuthorityUrl(String authorityUrl) {
		this.authorityUrl = authorityUrl;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
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
