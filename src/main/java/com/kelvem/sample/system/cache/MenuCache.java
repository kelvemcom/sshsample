package com.kelvem.sample.system.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kelvem.sample.system.model.SysRoleModel;
import com.kelvem.sample.system.service.SysRoleService;

@Component("menuCache")
public class MenuCache {
	
	private static Log log = LogFactory.getLog(MenuCache.class);

	@Autowired private SysRoleService sysRoleService; 
	
	private static MenuTree menuTree = null;

	
	public MenuTree getMenuTree() {
		
		if (menuTree != null) {
			return menuTree;
		}
		
		menuTree = new MenuTree("root");
		
		List<SysRoleModel> listRole = sysRoleService.queryAllSysRole();
		for (SysRoleModel sysRoleModel : listRole) {
			menuTree.addNode(sysRoleModel);
		}
		
		return menuTree;
	}

public class MenuTree{
	
	public List<MenuTree> menuList = new ArrayList<MenuTree>();
	public String Name = null;
	public String url = null;
	
	public Map<String, MenuTree> menuMap = new HashMap<String, MenuTree>();
	
	public MenuTree(String name){
		this.Name = name;
	}
	public void addNode(List<String> nodes){
		if (nodes == null || nodes.size() <= 0) {
			return;
		}
		if (nodes.get(0) == null || nodes.get(0).trim().length() <= 0) {
			return;
		}
		
		String nodeName = nodes.get(0);
		nodes.remove(0);
		
		if (menuMap.containsKey(nodeName)) {
			MenuTree tree = menuMap.get(nodeName);
			tree.addNode(nodes);
		} else {
			MenuTree tree = new MenuTree(nodeName);
			menuList.add(tree);
			menuMap.put(nodeName, tree);
			tree.addNode(nodes);
		}
		
	}
	public void addNode(SysRoleModel node){
		List<String> nodes = new ArrayList<String>();
		if (node.getMenuLevel1() != null && node.getMenuLevel1().trim().length() > 0) {
			nodes.add(node.getMenuLevel1());
			if (node.getMenuLevel2() != null && node.getMenuLevel2().trim().length() > 0) {
				nodes.add(node.getMenuLevel2());
				if (node.getMenuLevel3() != null && node.getMenuLevel3().trim().length() > 0) {
					nodes.add(node.getMenuLevel3());
					if (node.getMenuLevel4() != null && node.getMenuLevel4().trim().length() > 0) {
						nodes.add(node.getMenuLevel4());
					}
				}
			}
		}
		nodes.add(node.getSysRoleName());
		addNode(nodes);
	}
	public String toJson(){
		StringBuilder sb = new StringBuilder();
		for (MenuTree menuTree : menuList) {
			if (menuTree.menuList.size() > 0) {
				sb.append("【" + menuTree.Name + "】");
				sb.append(":");
				sb.append(menuTree.toJson());
			} else {
				sb.append(menuTree.Name);
				sb.append(",");
			}
		}
		return sb.toString();
	}
}

}








