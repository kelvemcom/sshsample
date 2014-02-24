/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.dao.SysDomainDao.java
 * 所含类: SysDomainDao.java
 * 修改记录：
 * 日期				作者						内容
 * =============================================================
 * 2014-02-22	kelvem			创建文件，实现基本功能
 * ============================================================*/

package com.kelvem.sample.system.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.kelvem.common.base.Hibernate4DaoBase;
import com.kelvem.common.utils.StringUtil;
import com.kelvem.sample.system.model.SysDomainModel;

/**
 * <p>SysDomainDao</p>
 * 
 * <p>值域管理管理模块Dao</p>
 * 
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysDomainDao
 * @author kelvem
 * @version 1.0
 */
@Component("sysDomainDao")
public class SysDomainDao extends Hibernate4DaoBase<SysDomainModel, Integer> {

	private static Log log = LogFactory.getLog(SysDomainDao.class);

	/**
	 * <p>查询值域管理列表</p>
	 * 
	 * @param domainType 值域类型
	 * @return List<SysDomainModel> 值域管理列表
	 * @see
	 */
	public List<SysDomainModel> querySysDomain(String domainType) {

		Criteria criteria = this.getSession().createCriteria(SysDomainModel.class);
		if (!StringUtil.isEmpty(domainType)) {
			criteria.add(Restrictions.eq("domainType", domainType));
		}
		
		criteria.setCacheable(true);
		return (List<SysDomainModel>)criteria.list();
	}

	/**
	 * <p>保存值域管理</p>
	 * 
	 * @param sysDomain 值域管理
	 * @return Integer keyId
	 * @see
	 */
	public Integer saveSysDomain(SysDomainModel sysDomain) {
		return (Integer)super.save(sysDomain);
	}

	/**
	 * <p>更新值域管理</p>
	 * 
	 * @param sysDomain 值域管理
	 * @return void
	 * @see
	 */
	public void updateSysDomain(SysDomainModel sysDomain) {
		super.update(sysDomain);
	}

	/**
	 * <p>删除值域管理</p>
	 * 
	 * @param sysDomain 值域管理
	 * @return void
	 * @see
	 */
	public void deleteSysDomain(SysDomainModel sysDomain){
		super.delete(sysDomain);
	}
}
