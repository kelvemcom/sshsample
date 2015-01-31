/**
 * 
 */
package com.kelvem.common.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.kelvem.sample.system.model.MenuTree;
import com.kelvem.sample.system.model.SysAuthorityModel;
import com.kelvem.sample.system.model.SysUserModel;
import com.opensymphony.xwork2.ActionContext;



/**
 * @author kelvem
 *
 */
public class SessionUtils {

	public final static String rand = "rand";
	public final static String json_menu_tree = "json_menu_tree";
	public final static String json_curr_menu = "json_curr_menu";
	
	public final static String login_user = "login_user";
	public final static String auth_list = "auth_list";
	
	private SessionUtils(){
		// do nothing
	}

	/**
	 * ValidateCode
	 */
	public static String getValidateCode() {
		Object o = ActionContext.getContext().getSession().get(rand);
		if (o == null) {
			return null;
		} else {
			return o.toString();
		}
	}
	
	public static void setValidateCode(String rand) {
		ActionContext.getContext().getSession().put(rand, rand);
	}


	/**
	 * MenuTree
	 */
	public static JSONObject getMenuTree(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		
		HttpSession session = request.getSession(true);
		Object o = session.getAttribute(json_menu_tree);
		if (o == null) {
			return null;
		} else {
			return (JSONObject)o;
		}
	}
	
	public static void setMenuTree(HttpServletRequest request, MenuTree menuTree) {
		if (menuTree == null || request == null) {
			return;
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute(json_menu_tree, menuTree.toJson());
	}
	

	/**
	 * CurrMenu
	 */
	public static JSONObject getCurrMenu(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		
		HttpSession session = request.getSession(true);
		Object o = session.getAttribute(json_curr_menu);
		if (o == null) {
			return null;
		} else {
			return (JSONObject)o;
		}
	}
	
	public static void setCurrMenu(HttpServletRequest request, JSONObject json) {
		if (json == null || request == null) {
			return;
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute(json_curr_menu, json);
	}
	

	/**
	 * AuthList
	 */
	@SuppressWarnings("unchecked")
	public static List<SysAuthorityModel> getAuthList(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		
		HttpSession session = request.getSession(true);
		Object o = session.getAttribute(auth_list);
		if (o == null) {
			return null;
		} else {
			return (List<SysAuthorityModel>)o;
		}
	}
	
	public static void setAuthList(HttpServletRequest request, List<SysAuthorityModel> authList) {
		if (authList == null || request == null) {
			return;
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute(auth_list, authList);
	}

	/**
	 * LoginUser
	 */
	public static SysUserModel getLoginUser(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		
		HttpSession session = request.getSession(true);
		Object o = session.getAttribute(login_user);
		if (o == null) {
			return null;
		} else {
			return (SysUserModel)o;
		}
	}
	
	public static void setLoginUser(HttpServletRequest request, SysUserModel loginUser) {
		if (loginUser == null || request == null) {
			return;
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute(login_user, loginUser);
	}
	
	
	
	
	
	
}
