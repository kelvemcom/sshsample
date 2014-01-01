/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.dao.SysAuthorityDao.java
 * 所含类: SysAuthorityDao.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-09	kelvem			创建文件，实现基本功能
 * ============================================================*/

package com.kelvem.sample.system.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.kelvem.common.base.Hibernate4DaoBase;
import com.kelvem.common.model.PageResults;
import com.kelvem.sample.system.SystemsException;
import com.kelvem.sample.system.model.SysAuthorityModel;
import com.kelvem.sample.system.queryvo.SysAuthorityInVO;

/**
 * <p>SysAuthorityDao</p>
 *
 * <p>系统权限表管理模块Dao</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysAuthorityDao
 * @author kelvem
 * @version 1.0
 * 
 */

@Component("sysAuthorityDao")
public class SysAuthorityDao extends Hibernate4DaoBase<SysAuthorityModel, Integer> {
	
	private static Log log = LogFactory.getLog(SysAuthorityDao.class);

	/**
	 * <p>查询系统权限表列表</p>
	 * 
	 * @param pageNo 第几页
	 * @param pageSize 每页显示条数
	 * @param sysAuthorityInVO 查询条件VO 
	 * @return PageResults<SysAuthorityModel> 系统权限表列表
	 * @see
	 */
	public PageResults<SysAuthorityModel> querySysAuthority(int pageNo,
			int pageSize, SysAuthorityInVO sysAuthorityInVO) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SysAuthorityModel.class);
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
	 * <p>查询系统权限表</p>
	 * 
	 * @param authorityId 系统权限表唯一ID
	 * @return SysAuthorityModel 系统权限表
	 * @see
	 */
	public SysAuthorityModel getSysAuthorityById(Integer authorityId) {
		return super.getById(SysAuthorityModel.class, authorityId);
	}

	/**
	 * <p>保存系统权限表</p>
	 * 
	 * @param sysAuthority 系统权限表
	 * @return void
	 * @see
	 */
	public void saveSysAuthority(SysAuthorityModel sysAuthority) {
		super.save(sysAuthority);
	}
	
	/**
	 * <p>更新系统权限表</p>
	 * 
	 * @param sysAuthority 系统权限表
	 * @return void
	 * @see
	 */
	public void updateSysAuthority(SysAuthorityModel sysAuthority) {
		super.update(sysAuthority);
	}
	
	/**
	 * <p>删除系统权限表</p>
	 * 
	 * @param sysAuthority 系统权限表
	 * @return void
	 * @see
	 */
	public void deleteSysAuthority(SysAuthorityModel sysAuthority){
		super.delete(sysAuthority);
	}

	/**
	 * <p>查询系统权限表</p>
	 * 
	 * @param id	权限Id
	 * @param name	权限名称
	 * @param url	权限地址
	 * @return List<SysAuthorityModel> 系统权限表
	 * @see
	 */
	public List<SysAuthorityModel> querySysAuthority(Integer id, String name, String url) {

		if (id == null && name == null && url == null) {
			throw new SystemsException("params is null");
		}
//		SysAuthorityModel model = new SysAuthorityModel();
//		model.setSysAuthorityId(id);
//		model.setSysAuthorityName(name);
//		model.setSysAuthorityUrl(url);
//		
//		return super.findByExample(model);
		
		Criteria criteria = this.getSession().createCriteria(SysAuthorityModel.class);
		if (id != null && id > 0) {
			criteria.add(Restrictions.eq("sysAuthorityId", id));
		}
		if (name != null) {
			criteria.add(Restrictions.eq("sysAuthorityName", name));
		}
		if (url != null) {
			criteria.add(Restrictions.eq("sysAuthorityUrl", url));
		}
		
		criteria.setCacheable(true);
		return (List<SysAuthorityModel>)criteria.list();
	}
}
