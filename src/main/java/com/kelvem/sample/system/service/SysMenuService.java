/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.service.SysMenuService.java
 * 所含类: SysMenuService.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2014-01-09	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kelvem.sample.system.model.SysRoleModel;

/**
 * <p>SysMenuService</p>
 *
 * <p>系统菜单管理模块Service</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysMenuService
 * @author kelvem
 * @version 1.0
 * 
 */
@Component("sysMenuService")
public class SysMenuService {
	
	private static Log log = LogFactory.getLog(SysMenuService.class);

	@Autowired private SysRoleService sysRoleService;
	@Autowired private SysAuthorityService sysAuthorityService;


	public SysRoleModel getSysMenu(String url){
		
		List<SysRoleModel> list = sysRoleService.querySysRole(null, null, url);
		
		if (list == null || list.size() <= 0) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public boolean isMenu(String url){
		
		List<SysRoleModel> list = sysRoleService.querySysRole(null, null, url);
		
		if (list == null || list.size() <= 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public SysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	public SysAuthorityService getSysAuthorityService() {
		return sysAuthorityService;
	}

	public void setSysAuthorityService(SysAuthorityService sysAuthorityService) {
		this.sysAuthorityService = sysAuthorityService;
	}
	
}
