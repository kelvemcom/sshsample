package com.kelvem.common.security;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.kelvem.sample.system.service.SysUserService;

public class KelvemAccessDecisionManager implements AccessDecisionManager {
	
	private static Log log = LogFactory.getLog(KelvemAccessDecisionManager.class);

	private SysUserService sysUserService;
	
	/**
	 * 
	 * <p>判断用户角色是否有该URL的访问权限</p>
	 * 
	 * @param userRoles 权限类
	 * @param object URL对象
	 * @param needRoles
	 * 
	 * @return CPrizeLevelDef
	 * 
	 * @throws AccessDeniedException 权限不足异常
	 * @throws InsufficientAuthenticationException
	 */
    public void decide(Authentication userRoles, Object object, Collection<ConfigAttribute> needRoles)
            throws AccessDeniedException, InsufficientAuthenticationException {
    	
    	log.info("decide params [userRoles:" + userRoles.getAuthorities() + ", needRoles:" + needRoles + "]");
        if(needRoles == null){
            return ;
        }
        // System.out.println("UmpAccessDecisionManager:decide# " + object.toString());
        // 判断访问权限
        Iterator<ConfigAttribute> ite=needRoles.iterator();
        while(ite.hasNext()){
            ConfigAttribute ca=ite.next();
            String needRole=((SecurityConfig)ca).getAttribute();
            for(GrantedAuthority ga:userRoles.getAuthorities()){
                if(needRole.equals(ga.getAuthority())){  //ga is user's role.
                	log.info("decide access");
                    return;
                }
            }
        }
        
        // 当没有访问权限时
        log.info("decide fail");
        // ###
        // throw new AccessDeniedException("no right");
    }
	
    public boolean supports(ConfigAttribute attribute) {
        // TODO Auto-generated method stub
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
    
}