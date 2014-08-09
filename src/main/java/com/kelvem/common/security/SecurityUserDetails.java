package com.kelvem.common.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.kelvem.sample.system.model.SysUserModel;

public class SecurityUserDetails extends User{

	/**
	 * 登陆用户信息对象
	 */
	private SysUserModel sysUser;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3854827415704889610L;

	/**
	 * <p>构造函数</p>
	 * 
	 * @param sysUser
	 * @param authorities
	 */
	public SecurityUserDetails(SysUserModel sysUser, Collection<? extends GrantedAuthority> authorities) {
		super(sysUser.getUserLogonName(), sysUser.getUserPassword(), true, true, true, true, authorities);
		this.sysUser = sysUser;
	}

	/**
	 * @return the sysUser
	 */
	public SysUserModel getSysUser() {
		return sysUser;
	}

	/**
	 * @param sysUser the sysUser to set
	 */
	public void setSysUser(SysUserModel sysUser) {
		this.sysUser = sysUser;
	}
	
}