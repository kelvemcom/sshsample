/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.model.SysRoleModel.java
 * 所含类: SysRoleModel.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-28	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.model;

import java.util.Date;
import java.util.Set;


/**
 * <p>SysRoleModel</p>
 *
 * <p>系统角色Model</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysRoleModel
 * @author kelvem
 * @version 1.0
 *
 */
public class SysRoleModel implements java.io.Serializable {

	private static final long serialVersionUID = -1L;
	
	// Fields
	private Integer sysRoleId;

	private String sysRoleName;
	private String sysRoleDesc;
	private String menuLevel1;
	private String menuLevel2;
	private String menuLevel3;
	private String menuLevel4;
	private Integer sysAuthorityId;
	private String menuUrl;
	private String menuParams;
	private Integer menuSort;
	private Integer statusCode;
	private Date statusChangeTime;
	private Integer delFlag;
	private Date delTime;
	private Date createTime;
	private Date updateTime;

	private SysAuthorityModel sysAuthorityModel;
	private Set<SysAuthorityModel> sysAuthoritySet;

	/** default constructor */
	public SysRoleModel() {
	}

	
	public Integer getSysRoleId() {
		return this.sysRoleId;
	}
	public void setSysRoleId(Integer sysRoleId) {
		this.sysRoleId = sysRoleId;
	}
	
	
	public String getSysRoleName() {
		return this.sysRoleName;
	}
	public void setSysRoleName(String sysRoleName) {
		this.sysRoleName = sysRoleName;
	}
	
	public String getSysRoleDesc() {
		return this.sysRoleDesc;
	}
	public void setSysRoleDesc(String sysRoleDesc) {
		this.sysRoleDesc = sysRoleDesc;
	}
	
	public String getMenuLevel1() {
		return this.menuLevel1;
	}
	public void setMenuLevel1(String menuLevel1) {
		this.menuLevel1 = menuLevel1;
	}
	
	public String getMenuLevel2() {
		return this.menuLevel2;
	}
	public void setMenuLevel2(String menuLevel2) {
		this.menuLevel2 = menuLevel2;
	}
	
	public String getMenuLevel3() {
		return this.menuLevel3;
	}
	public void setMenuLevel3(String menuLevel3) {
		this.menuLevel3 = menuLevel3;
	}
	
	public String getMenuLevel4() {
		return this.menuLevel4;
	}
	public void setMenuLevel4(String menuLevel4) {
		this.menuLevel4 = menuLevel4;
	}
	
	public Integer getSysAuthorityId() {
		return this.sysAuthorityId;
	}
	public void setSysAuthorityId(Integer sysAuthorityId) {
		this.sysAuthorityId = sysAuthorityId;
	}

	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	
	public String getMenuParams() {
		return this.menuParams;
	}
	public void setMenuParams(String menuParams) {
		this.menuParams = menuParams;
	}
	
	public Integer getMenuSort() {
		return this.menuSort;
	}
	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
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

	public SysAuthorityModel getSysAuthorityModel() {
		return sysAuthorityModel;
	}
	public void setSysAuthorityModel(SysAuthorityModel sysAuthorityModel) {
		this.sysAuthorityModel = sysAuthorityModel;
	}

	public Set<SysAuthorityModel> getSysAuthoritySet() {
		return sysAuthoritySet;
	}
	public void setSysAuthoritySet(Set<SysAuthorityModel> sysAuthoritySet) {
		this.sysAuthoritySet = sysAuthoritySet;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SysRoleModel) {
			return this.getSysRoleId().equals(((SysRoleModel)obj).getSysRoleId());
		} else {
			return super.equals(obj);
		}
	}
	
	@Override
	public int hashCode() {
		return this.getSysRoleId();
	}
}
