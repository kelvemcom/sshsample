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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;

import com.kelvem.common.base.Hibernate4DaoBase;
import com.kelvem.common.model.PageResults;
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
	public PageResults<SysUserModel> querySysUser(int pageNo,
			int pageSize, SysUserInVO sysUserInVO) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysUserModel.class);
		if (sysUserInVO != null) {
//			
//			Integer riskTypeCode = sysUserInVO.getRiskTypeCode();
//			if (riskTypeCode != null && riskTypeCode > 0) {
//				detachedCriteria.add(Restrictions.eq("riskTypeCode", riskTypeCode));
//			}
//			
//			Integer riskStatusCode = sysUserInVO.getRiskStatusCode();
//			if (riskStatusCode != null && riskStatusCode > 0) {
//				detachedCriteria.add(Restrictions.eq("riskStatusCode", riskStatusCode));
//			}
//			
//			Date startDate = sysUserInVO.getStartDate();
//			Date endDate = sysUserInVO.getEndDate();
//			if(this.isNotEmpty(startDate) && this.isNotEmpty(endDate))
//			{
//				detachedCriteria.add(Restrictions.between("occurTime", startDate,endDate));
//			}
//			else if (this.isNotEmpty(startDate)) {
//				detachedCriteria.add(Restrictions.ge("occurTime", startDate));
//			} else if (this.isNotEmpty(endDate)) {
//				detachedCriteria.add(Restrictions.le("occurTime", endDate));
//			}
		}
		
//		detachedCriteria.add(Restrictions.eq("userTypeCode", 10));
		
		return super.findPageByCriteria(detachedCriteria, pageSize, pageNo);
	}

	/**
	 * <p>查询用户表</p>
	 * 
	 * @param uSERID 用户表唯一ID
	 * @return SysUserModel 用户表
	 * @see
	 */
	public SysUserModel getSysUserById(Integer uSERID) {
		return super.getById(SysUserModel.class, uSERID);
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
	 * <p>删除用户表</p>
	 * 
	 * @param sysUser 用户表
	 * @return void
	 * @see
	 */
	public void deleteSysUser(SysUserModel sysUser){
		super.delete(sysUser);
	}
}
