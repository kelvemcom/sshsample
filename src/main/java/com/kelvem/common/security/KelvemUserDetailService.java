package com.kelvem.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kelvem.sample.system.model.SysRoleModel;
import com.kelvem.sample.system.model.SysUserModel;
import com.kelvem.sample.system.service.SysUserService;

public class KelvemUserDetailService implements UserDetailsService {

	private static final Log log = LogFactory.getLog(KelvemUserDetailService.class);
	
	@Autowired private SysUserService sysUserService;
	
	private SysUserModel loginUser = null;
	
	/**
     * 
     * <p>根据用户名加载用户及角色信息</p>
     * 
     * @param username 用户名
     * 
     * @return UserDetails 用户信息
     * @see
     */
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		
		log.info("用户(" + userName + ")登录中");
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

		this.loginUser = sysUserService.getSysUserByName(userName);
		
		if ( loginUser == null || loginUser.getDelFlag() == null || loginUser.getDelFlag() != 0 ){
			loginUser = new SysUserModel();
			loginUser.setUserLogonName(" ");
			loginUser.setUserPassword(" ");
			log.info("用户(" + userName + ")不存在！");
			
			return new SecurityUserDetails(loginUser, new ArrayList<GrantedAuthority>());
		} else if (loginUser.getStatusCode() != 10){
			loginUser = new SysUserModel();
			loginUser.setUserLogonName(" ");
			loginUser.setUserPassword(" ");
			log.info("用户(" + userName + ")处于停用状态！");
			
			return new SecurityUserDetails(loginUser, new ArrayList<GrantedAuthority>());
		}
		
		Set<SysRoleModel> roleSet = loginUser.getSysRoleSet();
		for (SysRoleModel role : roleSet) {
			GrantedAuthorityImpl auth = new GrantedAuthorityImpl(role.getSysRoleName());
			auths.add(auth);
		}
//		List<SysAuthorityModel> listAuth = loginUser.getAuthorityList();
//		if (listAuth != null){
//	        for (SysAuthorityModel vo : listAuth) {
//				GrantedAuthorityImpl auth = new GrantedAuthorityImpl(vo.getAuthorityName());
//				auths.add(auth);
//			}
//		}
		
		GrantedAuthorityImpl auth = new GrantedAuthorityImpl("ROLE_ANONYMOUS");
		auths.add(auth);
		
		SecurityUserDetails user = new SecurityUserDetails(loginUser, auths);
		log.info("用户(" + userName + ")登录成功！");
		return user;
	}
	
	public SysUserModel getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(SysUserModel loginUser) {
		this.loginUser = loginUser;
	}

}