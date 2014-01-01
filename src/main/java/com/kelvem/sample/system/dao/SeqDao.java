/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.dao.SeqDao.java
 * 所含类: SeqDao.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-15	kelvem			创建文件，实现基本功能
 * ============================================================*/

package com.kelvem.sample.system.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;

import com.kelvem.common.base.Hibernate4DaoBase;
import com.kelvem.common.model.PageResults;

import com.kelvem.sample.system.model.SeqModel;

/**
 * <p>SeqDao</p>
 *
 * <p>seq管理模块Dao</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SeqDao
 * @author kelvem
 * @version 1.0
 * 
 */
@Component("seqDao")
public class SeqDao extends Hibernate4DaoBase<SeqModel, Integer> {
	
	private static Log log = LogFactory.getLog(SeqDao.class);

	/**
	 * <p>保存seq</p>
	 * 
	 * @param seq seq
	 * @return void
	 * @see
	 */
	public Integer increment() {
		SeqModel seq = new SeqModel();
		return (Integer)super.save(seq);
	}
}
