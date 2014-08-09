package com.kelvem.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.kelvem.sample.system.model.SysRoleModel;
import com.kelvem.sample.system.service.SysRoleService;

public class KelvemInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private static Log log = LogFactory.getLog(KelvemInvocationSecurityMetadataSource.class);

	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	* 
	* <p>根据Url获取角色信息</p>
	* 
	* @param object Url
	* 
	* @return Collection<ConfigAttribute> 角色信息
	* @see
	*/
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		
		log.debug("KelvemInvocationSecurityMetadataSource.getAttributes : " + object.toString());
		Collection<ConfigAttribute> result = new ArrayList<ConfigAttribute>(0);
		
		// 获取登录用户信息
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//HttpServletRequest request = ((FilterInvocation)object).getRequest();
		//SecurityContext context = (SecurityContext)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		//if(context != null){
		//	obj = (SecurityUserDetails) context.getAuthentication().getPrincipal();
		//}
		SecurityUserDetails user = null;
		if ( obj instanceof SecurityUserDetails){ 
			user = (SecurityUserDetails) obj;    	
		}
		if ( user == null || user.getSysUser() == null){
			return result;
		}    	
		
		// 获取用户访问的Url
		String url = ((FilterInvocation)object).getRequestUrl();
		
		// 如下Url，登录后即可访问
		if (url.startsWith("/index.html")) {
			ConfigAttribute ca = new SecurityConfig("ROLE_ANONYMOUS");
			result.add(ca);
			return result;
		}
		
		// 查找对应Attr
		Collection<ConfigAttribute> bufAttrs = getMapConfigAttributes().get(url);
		if (bufAttrs != null) {
			result.addAll(bufAttrs);
		}
		
		// 如下Url，有访问权限才可以访问
//		List<SysAuthorityVO> listAuth = user.getSysUser().getAuthorityList();
//		for (SysAuthorityVO vo : listAuth) {
//		    String resURL = vo.getAuthorityUrl();
//		    // if (urlMatcher.pathMatchesUrl(resURL, url)) {
//		    if (url.startsWith(resURL)) {
//		    	Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
//		    	ConfigAttribute ca = new SecurityConfig(vo.getAuthorityName());
//		    	atts.add(ca);
//		    	result.addAll(atts);
//		    } 
//		}
		
		return result;
	
	}
	
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	
	private Map<String, Collection<ConfigAttribute>> securityMetadataSource = null;
	
	public Map<String, Collection<ConfigAttribute>> getMapConfigAttributes() {
	
		if (securityMetadataSource != null) {
			return securityMetadataSource;
		}
		securityMetadataSource = new HashMap<String, Collection<ConfigAttribute>>();
		
		List<SysRoleModel> roleList = sysRoleService.queryAllSysRole();
		for (SysRoleModel role : roleList) {
			ConfigAttribute ca = new SecurityConfig(role.getSysRoleName());
			String key = role.getMenuUrl();
			if (securityMetadataSource.containsKey(key)) {
				securityMetadataSource.get(key).add(ca);
			} else {
				Collection<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
				list.add(ca);
				securityMetadataSource.put(key, list);
			}
		}
		
		return securityMetadataSource;
	}
}
