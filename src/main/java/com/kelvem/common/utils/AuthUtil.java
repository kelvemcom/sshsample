/**
 * 
 */
package com.kelvem.common.utils;

import java.util.Date;

import com.kelvem.common.ModelConstant;
import com.kelvem.sample.system.model.SysAuthorityModel;



/**
 * @author kelvem
 *
 */
public class AuthUtil {

	private AuthUtil(){
		// do nothing
	}
	
	public static void main(String[] args) {
		System.out.println(getAuthType("/page/system/sysUser_updatesUserAaddel.action"));
	}
	
	public static SysAuthorityModel createSysAuthorityModel(String url) {

		SysAuthorityModel model = new SysAuthorityModel();
		
		String authName = url;
		if (url != null 
				&& url.length() > 0 
				&& url.startsWith("/")
				&& url.contains("*")) {
			authName = url.substring(url.lastIndexOf("/") + 1);
		}
		model.setSysAuthorityName(authName);
		model.setSysAuthorityUrl(url);
		model.setSysAuthorityType(getAuthType(url));
		model.setControlType("login");
		model.setCreateTime(new Date());
		model.setStatusCode(10);
		model.setStatusChangeTime(new Date());
		model.setDelFlag(ModelConstant.FLAG_DEL_FALSE);
		
		return model;
	}

	public static String getAuthType(String url){
		
		if (url == null || url.length() <= 0) {
			return "unknow";
		} else if (RegxUtil.contain(url, "(Query|query|List|list|Get|get|Detail|detail)(|Done).(jsp|do|action|html)")) {
			return "read";
		} else if (RegxUtil.contain(url, "(Add|add)(|Done).(jsp|do|action|html)")) {
			return "add";
		} else if (RegxUtil.contain(url, "(Update|update)(|Done).(jsp|do|action|html)")) {
			return "update";
		} else if (RegxUtil.contain(url, "(Delete|delete|Del|del)(|Done).(jsp|do|action|html)")) {
			return "delete";
		} else if (RegxUtil.contain(url, ".(css|js|jpg|JPG|png|PNG|gif|GIF)$")) {
			return "resource";
		}
		
		String authType = "unknow";
		int matchCnt = 0;
		
		if (RegxUtil.contain(url, "(Query|query|List|list|Get|get|Detail|detail)[^.]*.(jsp|do|action|html)")) {
			authType = "read";
			matchCnt++;
		}
		if (RegxUtil.contain(url, "(Add|add)[^.]*.(jsp|do|action|html)")) {
			authType = "add";
			matchCnt++;
		}
		if (RegxUtil.contain(url, "(Update|update)[^.]*.(jsp|do|action|html)")) {
			authType = "update";
			matchCnt++;
		}
		if (RegxUtil.contain(url, "(Delete|delete|Del|del)[^.]*.(jsp|do|action|html)")) {
			authType = "delete";
			matchCnt++;
		}
		
		if (matchCnt > 1) {
			return "unknow";
		} else {
			return authType;
		}
	}
}
