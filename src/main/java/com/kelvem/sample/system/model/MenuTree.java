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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.kelvem.common.utils.StringUtil;


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
public class MenuTree implements java.io.Serializable {

	private static final long serialVersionUID = -1L;
	
	public List<MenuTree> menuList = new ArrayList<MenuTree>();
	public String name = null;
	public String url = null;
	public String image = null;
	
	public Map<String, MenuTree> menuMap = new HashMap<String, MenuTree>();
	
	public MenuTree(String url){
		this.name = "";
		this.url = url;
	}
	
	private MenuTree(String name, String url, String image){
		this.name = name;
		this.url = url;
		this.image = image;
	}
	
	public void addNode(SysRoleModel node){

		MenuTree menu = this;
		if (!StringUtil.isEmpty(node.getMenuLevel1())) {
			String url = null;
			if (StringUtil.isEmpty(node.getMenuLevel2())) {
				url = node.getMenuUrl();
			} else {
				url = null;
			}
			MenuTree buf = menu.menuMap.get(node.getMenuLevel1());
			if (buf == null) {
				buf = new MenuTree(node.getMenuLevel1(), url, node.getMenuImage());
				menu.addNode(buf);
			}
			menu = buf;
		}
		
		if (!StringUtil.isEmpty(node.getMenuLevel2())) {
			String url = null;
			if (StringUtil.isEmpty(node.getMenuLevel3())) {
				url = node.getMenuUrl();
			} else {
				url = null;
			}
			MenuTree buf = menu.menuMap.get(node.getMenuLevel2());
			if (buf == null) {
				buf = new MenuTree(node.getMenuLevel2(), url, node.getMenuImage());
				menu.addNode(buf);
			}
			menu = buf;
		}
		
		if (!StringUtil.isEmpty(node.getMenuLevel3())) {
			String url = null;
			if (StringUtil.isEmpty(node.getMenuLevel4())) {
				url = node.getMenuUrl();
			} else {
				url = null;
			}
			MenuTree buf = menu.menuMap.get(node.getMenuLevel2());
			if (buf == null) {
				buf = new MenuTree(node.getMenuLevel3(), url, node.getMenuImage());
				menu.addNode(buf);
			}
			menu = buf;
		}
		
		if (!StringUtil.isEmpty(node.getMenuLevel4())) {
			String url = node.getMenuUrl();
			MenuTree buf = menu.menuMap.get(node.getMenuLevel4());
			if (buf == null) {
				buf = new MenuTree(node.getMenuLevel4(), url, node.getMenuImage());
				menu.addNode(buf);
			}
		}
	}
	

	public void addNode(MenuTree menu){
		this.menuList.add(menu);
		this.menuMap.put(menu.name, menu);
	}
	
	public JSONObject toJson(){

		JSONObject json = new JSONObject();
		if (!StringUtil.isEmpty(this.name)) {
			json.element("name", this.name);
		}
		
		if (!StringUtil.isEmpty(this.url)) {
			json.element("url", this.url);
		}
		
		if (!StringUtil.isEmpty(this.image)) {
			json.element("image", this.image);
		}

		JSONArray array = new JSONArray();
		for (MenuTree menuTree : menuList) {
			JSONObject subMenu = menuTree.toJson();
			array.add(subMenu);
		}
		if (array.size() > 0) {
			json.element("sub_menu", array);
		}
		
		return json;
	}
}
