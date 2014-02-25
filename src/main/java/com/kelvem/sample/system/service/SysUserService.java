/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.service.SysUserService.java
 * 所含类: SysUserService.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-10	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kelvem.common.DomainConstant.STATUS_CODE;
import com.kelvem.common.model.PageResults;
import com.kelvem.sample.system.dao.SysUserDao;
import com.kelvem.sample.system.model.SysUserModel;
import com.kelvem.sample.system.queryvo.SysUserInVO;

/**
 * <p>SysUserService</p>
 *
 * <p>用户表管理模块Service</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysUserService
 * @author kelvem
 * @version 1.0
 * 
 */
@Component("sysUserService")
public class SysUserService {
	
	private static Log log = LogFactory.getLog(SysUserService.class);

	@Autowired private SysUserDao sysUserDao; 
	
	/**
	 * <p>查询用户表列表</p>
	 * 
	 * @param pageNo 第几页
	 * @param pageSize 每页显示条数
	 * @param sysUserInVO 查询条件VO 
	 * @return PageResults<SysUserModel> 用户表列表
	 * @see
	 */
	public PageResults<SysUserModel> querySysUser(int pageNo, int pageSize, SysUserInVO sysUserInVO){ 
		
//		if (sysUserInVO == null || sysUserInVO.isEmpty()) {
//			throw new RuntimeException("参数为空");
//		}
		PageResults<SysUserModel> pr = this.sysUserDao.querySysUser(pageNo, pageSize, sysUserInVO);
		
		return pr;
	}
	
	/**
	 * <p>查询用户表</p>
	 * 
	 * @param id 唯一ID
	 * @return SysUserModel 用户表
	 * @see
	 */
	public SysUserModel getSysUserById(Integer sysUserId){
		
		SysUserModel result = this.getSysUserDao().getSysUserById(sysUserId);
		return result;
	}
	
	/**
	 * <p>查询用户表</p>
	 * 
	 * @param name 登陆名
	 * @return SysUserModel 用户表
	 * @see
	 */
	public SysUserModel getSysUserByName(String userName){
		
		SysUserModel result = this.getSysUserDao().getSysUserByName(userName);
		return result;
	}
	
	/**
	 * <p>保存用户表</p>
	 * 
	 * @param sysUser 用户表
	 * @return void
	 * @see
	 */
	public void saveSysUser(SysUserModel sysUser){
		
		this.getSysUserDao().saveSysUser(sysUser);
	}
	
	/**
	 * <p>更新用户表</p>
	 * 
	 * @param sysUser 用户表
	 * @return void
	 * @see
	 */
	public void updateSysUser(SysUserModel sysUser){
				
		this.getSysUserDao().updateSysUser(sysUser);
	}

	
	/**
	 * <p>启用用户</p>
	 * 
	 * @param sysUserId 用户Id
	 * @param statusCode 状态值域
	 * @return void
	 * @see
	 */
	public void enableSysUser(Integer sysUserId, boolean enable) {
		
		if (enable == true) {
			this.getSysUserDao().enableSysUser(sysUserId, STATUS_CODE.ENABLE.getValue());
		} else {
			this.getSysUserDao().enableSysUser(sysUserId, STATUS_CODE.DISENABLE.getValue());
		}
	}
	
	/**
	 * <p>删除用户表</p>
	 * 
	 * @param sysUser 用户表
	 * @return void
	 * @see
	 */
	public void deleteSysUser(SysUserModel sysUser){

		this.getSysUserDao().deleteSysUser(sysUser);
	}

	public SysUserDao getSysUserDao() {
		return sysUserDao;
	}

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	
}
