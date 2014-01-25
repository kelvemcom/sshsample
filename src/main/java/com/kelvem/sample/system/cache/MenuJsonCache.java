package com.kelvem.sample.system.cache;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kelvem.sample.system.model.MenuTree;
import com.kelvem.sample.system.model.SysRoleModel;
import com.kelvem.sample.system.service.SysRoleService;

@Component("menuJsonCache")
public class MenuJsonCache {
	
	private static Log log = LogFactory.getLog(MenuJsonCache.class);

	@Autowired private SysRoleService sysRoleService; 
	
	private static String jsonMenuTree = null;

	
	public String getJsonMenuTree() {
		
		if (jsonMenuTree != null) {
			return jsonMenuTree;
		}
		
		MenuTree menuTree = new MenuTree("");
		
		List<SysRoleModel> listRole = sysRoleService.queryAllSysRole();
		for (SysRoleModel sysRoleModel : listRole) {
			menuTree.addNode(sysRoleModel);
		}
		
		jsonMenuTree = menuTree.toJson().toString(); 
		return jsonMenuTree;
	}

}








