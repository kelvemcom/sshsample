/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.model.SysDomainModel.java
 * 所含类: SysDomainModel.java
 * 修改记录：
 * 日期				作者						内容
 * =============================================================
 * 2014-02-22	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.model;

import java.util.Date;


/**
 * <p>SysDomainModel</p>
 * 
 * <p>值域管理Model</p>
 * 
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysDomainModel
 * @author kelvem
 * @version 1.0
 */
public class SysDomainModel implements java.io.Serializable {

	private static final long serialVersionUID = -1L;
	
	private String domainType;
	private String domainName;
	private Integer keyCode;
	private String keyName;
	private String keyDesc;


	/** default constructor */
	public SysDomainModel() {
	}
	
	
	public String getDomainType() {
		return this.domainType;
	}
	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}
	
	public String getDomainName() {
		return this.domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	public Integer getKeyCode() {
		return this.keyCode;
	}
	public void setKeyCode(Integer keyCode) {
		this.keyCode = keyCode;
	}
	
	public String getKeyName() {
		return this.keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	
	public String getKeyDesc() {
		return this.keyDesc;
	}
	public void setKeyDesc(String keyDesc) {
		this.keyDesc = keyDesc;
	}
	

}
