/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.dao.SysRoleDao.java
 * 所含类: SysRoleDao.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-28	kelvem			创建文件，实现基本功能
 * ============================================================*/

package com.kelvem.sample.system.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.kelvem.common.base.Hibernate4DaoBase;
import com.kelvem.common.model.PageResults;
import com.kelvem.sample.system.SystemsException;
import com.kelvem.sample.system.model.SysAuthorityModel;
import com.kelvem.sample.system.model.SysRoleModel;
import com.kelvem.sample.system.queryvo.SysRoleInVO;

/**
 * <p>SysRoleDao</p>
 *
 * <p>系统角色管理模块Dao</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysRoleDao
 * @author kelvem
 * @version 1.0
 * 
 */
@Component("sysRoleDao")
public class SysRoleDao extends Hibernate4DaoBase<SysRoleModel, Integer> {
	
	private static Log log = LogFactory.getLog(SysRoleDao.class);

	/**
	 * <p>查询系统角色列表</p>
	 * 
	 * @param pageNo 第几页
	 * @param pageSize 每页显示条数
	 * @param sysRoleInVO 查询条件VO 
	 * @return PageResults<SysRoleModel> 系统角色列表
	 * @see
	 */
	public PageResults<SysRoleModel> querySysRole(int pageNo,
			int pageSize, SysRoleInVO sysRoleInVO) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysRoleModel.class);
//		if (monLargeBetInVO != null) {
//			
//			Integer riskTypeCode = monLargeBetInVO.getRiskTypeCode();
//			if (riskTypeCode != null && riskTypeCode > 0) {
//				detachedCriteria.add(Restrictions.eq("riskTypeCode", riskTypeCode));
//			}
//			
//			Integer riskStatusCode = monLargeBetInVO.getRiskStatusCode();
//			if (riskStatusCode != null && riskStatusCode > 0) {
//				detachedCriteria.add(Restrictions.eq("riskStatusCode", riskStatusCode));
//			}
//			
//			Date startDate = monLargeBetInVO.getStartDate();
//			Date endDate = monLargeBetInVO.getEndDate();
//			if(this.isNotEmpty(startDate) && this.isNotEmpty(endDate))
//			{
//				detachedCriteria.add(Restrictions.between("occurTime", startDate,endDate));
//			}
//			else if (this.isNotEmpty(startDate)) {
//				detachedCriteria.add(Restrictions.ge("occurTime", startDate));
//			} else if (this.isNotEmpty(endDate)) {
//				detachedCriteria.add(Restrictions.le("occurTime", endDate));
//			}
//		}
//		detachedCriteria.add(Restrictions.in("provinceCenterId", provinceCenterIds));
//		
//		detachedCriteria.addOrder(Order.desc("occurTime"));
//		
		return super.findPageByCriteria(detachedCriteria, pageSize, pageNo);
	}

	/**
	 * <p>查询系统所有角色</p>
	 * 
	 * @return SysRoleModel 系统角色
	 * @see
	 */
	public List<SysRoleModel> queryAllSysRole() {
		return super.findByExample(new SysRoleModel());
	}

	/**
	 * <p>查询系统角色</p>
	 * 
	 * @param sysRoleId 系统角色唯一ID
	 * @return SysRoleModel 系统角色
	 * @see
	 */
	public SysRoleModel getSysRoleById(Integer sysRoleId) {
		return super.getById(SysRoleModel.class, sysRoleId);
	}

	/**
	 * <p>保存系统角色</p>
	 * 
	 * @param sysRole 系统角色
	 * @return Integer keyId
	 * @see
	 */
	public Integer saveSysRole(SysRoleModel sysRole) {
		return (Integer)super.save(sysRole);
	}
	
	/**
	 * <p>更新系统角色</p>
	 * 
	 * @param sysRole 系统角色
	 * @return void
	 * @see
	 */
	public void updateSysRole(SysRoleModel sysRole) {
		super.update(sysRole);
	}
	
	/**
	 * <p>删除系统角色</p>
	 * 
	 * @param sysRole 系统角色
	 * @return void
	 * @see
	 */
	public void deleteSysRole(SysRoleModel sysRole){
		super.delete(sysRole);
	}
	
	/**
	 * <p>查询系统角色</p>
	 * 
	 * @param sysRole 系统角色
	 * @return void
	 * @see
	 */
	public List<SysRoleModel> querySysRole(Integer sysAuthorityId){

		if (sysAuthorityId == null) {
			return null;
		}
		
		SysRoleModel model = new SysRoleModel();
		model.setSysAuthorityId(sysAuthorityId);
		
		return this.findByExample(model);
	}

	/**
	 * <p>查询系统菜单表</p>
	 * 
	 * @param id	菜单Id
	 * @param name	菜单名称
	 * @param url	菜单地址
	 * @return List<SysRoleModel> 系统菜单表
	 * @see
	 */
	public List<SysRoleModel> querySysRole(Integer id, String name, String url) {

		if (id == null && name == null && url == null) {
			throw new SystemsException("params is null");
		}
		
		Criteria criteria = this.getSession().createCriteria(SysRoleModel.class);
		if (id != null && id > 0) {
			criteria.add(Restrictions.eq("sysRoleId", id));
		}
		if (name != null) {
			criteria.add(Restrictions.eq("sysRoleName", name));
		}
		if (url != null) {
			criteria.add(Restrictions.eq("menuUrl", url));
		}
		
		criteria.setCacheable(true);
		return (List<SysRoleModel>)criteria.list();
	}
}
