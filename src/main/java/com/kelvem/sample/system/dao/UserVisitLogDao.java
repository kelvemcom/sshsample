/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.dao.UserVisitLogDao.java
 * 所含类: UserVisitLogDao.java
 * 修改记录：
 * 日期				作者						内容
 * =============================================================
 * 2014-01-06	kelvem			创建文件，实现基本功能
 * ============================================================*/

package com.kelvem.sample.system.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;

import com.kelvem.common.base.Hibernate4DaoBase;
import com.kelvem.common.model.PageResults;

import com.kelvem.sample.system.model.UserVisitLogModel;
import com.kelvem.sample.system.queryvo.UserVisitLogInVO;

/**
 * <p>UserVisitLogDao</p>
 * 
 * <p>用户访问记录管理模块Dao</p>
 * 
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName UserVisitLogDao
 * @author kelvem
 * @version 1.0
 */
@Component("userVisitLogDao")
public class UserVisitLogDao extends Hibernate4DaoBase<UserVisitLogModel, Integer> {

	private static Log log = LogFactory.getLog(UserVisitLogDao.class);

	/**
	 * <p>查询用户访问记录列表</p>
	 * 
	 * @param pageNo 第几页
	 * @param pageSize 每页显示条数
	 * @param userVisitLogInVO 查询条件VO
	 * @return PageResults<UserVisitLogModel> 用户访问记录列表
	 * @see
	 */
	public PageResults<UserVisitLogModel> queryUserVisitLog(int pageNo,
			int pageSize, UserVisitLogInVO userVisitLogInVO) {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserVisitLogModel.class);
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
	 * <p>查询用户访问记录</p>
	 * 
	 * @param userVisitLogId 用户访问记录唯一ID
	 * @return UserVisitLogModel 用户访问记录
	 * @see
	 */
	public UserVisitLogModel getUserVisitLogById(Integer userVisitLogId) {
		return super.getById(UserVisitLogModel.class, userVisitLogId);
	}

	/**
	 * <p>保存用户访问记录</p>
	 * 
	 * @param userVisitLog 用户访问记录
	 * @return Integer keyId
	 * @see
	 */
	public Integer saveUserVisitLog(UserVisitLogModel userVisitLog) {
		return (Integer)super.save(userVisitLog);
	}

	/**
	 * <p>更新用户访问记录</p>
	 * 
	 * @param userVisitLog 用户访问记录
	 * @return void
	 * @see
	 */
	public void updateUserVisitLog(UserVisitLogModel userVisitLog) {
		super.update(userVisitLog);
	}

	/**
	 * <p>删除用户访问记录</p>
	 * 
	 * @param userVisitLog 用户访问记录
	 * @return void
	 * @see
	 */
	public void deleteUserVisitLog(UserVisitLogModel userVisitLog){
		super.delete(userVisitLog);
	}
}
