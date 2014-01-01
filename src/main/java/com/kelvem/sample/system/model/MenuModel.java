/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.model.MenuModel.java
 * 所含类: MenuModel.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-28	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.model;

import java.util.Date;


/**
 * <p>MenuModel</p>
 *
 * <p>系统角色Model</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName MenuModel
 * @author kelvem
 * @version 1.0
 *
 */
public class MenuModel implements java.io.Serializable {

	private static final long serialVersionUID = -1L;
	
	// Fields
	private String sysRoleName;
	private String sysRoleDesc;
	private String menuLevel1;
	private String menuLevel2;
	private String menuLevel3;
	private String menuLevel4;
	private Integer sysAuthorityId;
	private String menuUrl;
	private String menuParams;


	/** default constructor */
	public MenuModel() {
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
	
	public String getMenuParams() {
		return this.menuParams;
	}
	public void setMenuParams(String menuParams) {
		this.menuParams = menuParams;
	}
	
	public String getMenuUrl() {
		return this.menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	

}
