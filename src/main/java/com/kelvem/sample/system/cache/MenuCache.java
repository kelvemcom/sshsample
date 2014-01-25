package com.kelvem.sample.system.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kelvem.common.utils.StringUtil;
import com.kelvem.sample.system.model.MenuTree;
import com.kelvem.sample.system.model.SysRoleModel;
import com.kelvem.sample.system.service.SysRoleService;

@Component("menuCache")
public class MenuCache {
	
	private static Log log = LogFactory.getLog(MenuCache.class);

	@Autowired private SysRoleService sysRoleService; 
	
	private static MenuTree menuTree = null;
	@Transactional
	public MenuTree getMenuTree() {
		
		if (menuTree != null) {
			return menuTree;
		}
		
		menuTree = new MenuTree("");
		
		List<SysRoleModel> listRole = sysRoleService.queryAllSysRole();
		for (SysRoleModel sysRoleModel : listRole) {
			menuTree.addNode(sysRoleModel);
		}
		
		return menuTree;
	}

	
	private static Map<String, SysRoleModel> menuMap = null;
	@Transactional
	public SysRoleModel getCurrMenu(String url) {

		if (StringUtil.isEmpty(url)) {
			return null;
		}
		
		if (menuMap == null) {
			menuMap = new HashMap<String, SysRoleModel>();
			List<SysRoleModel> listRole = sysRoleService.queryAllSysRole();
			for (SysRoleModel sysRoleModel : listRole) {
				menuMap.put(sysRoleModel.getMenuUrl(), sysRoleModel);
			}
		}
		
		if (menuMap.containsKey(url)) {
			return menuMap.get(url);
		} else {
			return null;
		}
	}

}








