/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.dao.SysUserDao.java
 * 所含类: SysUserDao.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-10	kelvem			创建文件，实现基本功能
 * ============================================================*/

package com.kelvem.sample.system.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.kelvem.common.DomainConstant;
import com.kelvem.common.base.Hibernate4DaoBase;
import com.kelvem.common.model.PageResults;
import com.kelvem.common.utils.StringUtil;
import com.kelvem.sample.system.SystemsException;
import com.kelvem.sample.system.model.SysUserModel;
import com.kelvem.sample.system.queryvo.SysUserInVO;

/**
 * <p>SysUserDao</p>
 *
 * <p>用户表管理模块Dao</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysUserDao
 * @author kelvem
 * @version 1.0
 * 
 */
@Component("sysUserDao")
public class SysUserDao extends Hibernate4DaoBase<SysUserModel, Integer> {
	
	private static Log log = LogFactory.getLog(SysUserDao.class);

	/**
	 * <p>查询用户表列表</p>
	 * 
	 * @param pageNo 第几页
	 * @param pageSize 每页显示条数
	 * @param sysUserInVO 查询条件VO 
	 * @return PageResults<SysUserModel> 用户表列表
	 * @see
	 */
	public PageResults<SysUserModel> querySysUser(int pageNo, int pageSize, SysUserInVO sysUserInVO) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysUserModel.class);
		if (sysUserInVO != null) {
			
			Integer sysUserId = sysUserInVO.getSysUserId();
			if (this.isNotEmpty(sysUserId)) {
				detachedCriteria.add(Restrictions.eq("sysUserId", sysUserId));
			}
			
			String sysUserName = sysUserInVO.getSysUserName();
			if (this.isNotEmpty(sysUserName)) {
				detachedCriteria.add(Restrictions.like("sysUserName", "%" + sysUserName + "%"));
			}
			
			String userLogonName = sysUserInVO.getUserLogonName();
			if (this.isNotEmpty(userLogonName)) {
				detachedCriteria.add(Restrictions.like("userLogonName", "%" + userLogonName + "%"));
			}
			
			Integer userTypeCode = sysUserInVO.getUserTypeCode();
			if (this.isNotEmpty(userTypeCode) && userTypeCode > 0) {
				detachedCriteria.add(Restrictions.eq("userTypeCode", userTypeCode));
			}
			
			Integer statusCode = sysUserInVO.getStatusCode();
			if (this.isNotEmpty(statusCode) && statusCode > 0) {
				detachedCriteria.add(Restrictions.eq("statusCode", statusCode));
			}
			
			Date createTime = sysUserInVO.getCreateTime();
			if(this.isNotEmpty(createTime))
			{
				detachedCriteria.add(Restrictions.gt("createTime", createTime));
			}
		}
		
		detachedCriteria.add(Restrictions.eq("delFlag", DomainConstant.FLAG_DEL_FALSE));
		
		return super.findPageByCriteria(detachedCriteria, pageSize, pageNo);
	}

	/**
	 * <p>查询用户表</p>
	 * 
	 * @param userId 用户表唯一ID
	 * @return SysUserModel 用户表
	 * @see
	 */
	public SysUserModel getSysUserById(Integer userId) {
		return super.getById(SysUserModel.class, userId);
	}

	/**
	 * <p>查询用户表</p>
	 * 
	 * @param userId 用户表唯一ID
	 * @return SysUserModel 用户表
	 * @see
	 */
	public SysUserModel getSysUserByName(String userName) {

		if (userName == null) {
			throw new SystemsException("params is null");
		}
		
		Criteria criteria = this.getSession().createCriteria(SysUserModel.class);
		if (!StringUtil.isEmpty(userName)) {
			criteria.add(Restrictions.or(Restrictions.eq("sysUserName", userName), Restrictions.eq("sysUserName", "loginRole")));
		}
		
		criteria.setCacheable(true);
		List<SysUserModel> userList = (List<SysUserModel>)criteria.list();
		if (userList.size() > 0) {
			return userList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * <p>保存用户表</p>
	 * 
	 * @param sysUser 用户表
	 * @return void
	 * @see
	 */
	public Serializable saveSysUser(SysUserModel sysUser) {
		Serializable id = super.save(sysUser);
		return id;
	}
	
	/**
	 * <p>更新用户表</p>
	 * 
	 * @param sysUser 用户表
	 * @return void
	 * @see
	 */
	public void updateSysUser(SysUserModel sysUser) {
		super.update(sysUser);
	}
	
	/**
	 * <p>启用用户</p>
	 * 
	 * @param sysUserId 用户Id
	 * @param statusCode 状态值域
	 * @return void
	 * @see
	 */
	public void enableSysUser(Integer sysUserId, Integer statusCode) {
		SysUserModel sysUser = this.getSysUserById(sysUserId);
		sysUser.setStatusCode(statusCode);
		sysUser.setStatusChangeTime(new Date());
		super.update(sysUser);
	}
	
	/**
	 * <p>逻辑删除用户</p>
	 * 
	 * @param sysUserId 用户Id
	 * @param statusCode 状态值域
	 * @return void
	 * @see
	 */
	public void deleteSysUserFlag(SysUserModel sysUser){
		sysUser.setDelFlag(1);
		sysUser.setDelTime(new Date());
		super.update(sysUser);
	}
	
	/**
	 * <p>删除用户</p>
	 * 
	 * @param sysUser 用户表
	 * @return void
	 * @see
	 */
	public void deleteSysUser(SysUserModel sysUser){
		super.delete(sysUser);
	}
}
