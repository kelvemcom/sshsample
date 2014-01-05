/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.service.UserVisitLogService.java
 * 所含类: UserVisitLogService.java
 * 修改记录：
 * 日期				作者						内容
 * =============================================================
 * 2014-01-06	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kelvem.common.model.PageResults;
import com.kelvem.sample.system.dao.UserVisitLogDao;
import com.kelvem.sample.system.model.UserVisitLogModel;
import com.kelvem.sample.system.queryvo.UserVisitLogInVO;

/**
 * <p>UserVisitLogService</p>
 * 
 * <p>用户访问记录管理模块Service</p>
 * 
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName UserVisitLogService
 * @author kelvem
 * @version 1.0
 */
@Component("userVisitLogService")
public class UserVisitLogService {

	private static Log log = LogFactory.getLog(UserVisitLogService.class);

	@Autowired private UserVisitLogDao userVisitLogDao; 

	/**
	 * <p>查询用户访问记录列表</p>
	 * 
	 * @param pageNo 第几页
	 * @param pageSize 每页显示条数
	 * @param userVisitLogInVO 查询条件VO 
	 * @return PageResults<UserVisitLogModel> 用户访问记录列表
	 * @see
	 */
	public PageResults<UserVisitLogModel> queryUserVisitLog(int pageNo, int pageSize, UserVisitLogInVO userVisitLogInVO){ 
		
		PageResults<UserVisitLogModel> pr = this.userVisitLogDao.queryUserVisitLog(pageNo, pageSize, userVisitLogInVO);
				
		List<UserVisitLogModel> list = new ArrayList<UserVisitLogModel>(pr.getResults().size());
		
		for (UserVisitLogModel obj : pr.getResults()) {
			
//			obj.setStrOccurTime(DateUtils.getDateTimeString(obj.getOccurTime()));
//			obj.setStrDealTime(DateUtils.getDateTimeString(obj.getDealTime()));
			
			list.add(obj);
		}
		
		pr.setResults(list);
		
		return pr;
	}

	/**
	 * <p>查询用户访问记录</p>
	 * 
	 * @param id 唯一ID
	 * @return UserVisitLogModel 用户访问记录
	 * @see
	 */
	public UserVisitLogModel getUserVisitLogById(Integer id){
		
		UserVisitLogModel result = this.getUserVisitLogDao().getUserVisitLogById(id);
		return result;
	}

	/**
	 * <p>保存用户访问记录</p>
	 * 
	 * @param userVisitLog 用户访问记录
	 * @return Integer keyId
	 * @see
	 */
	public Integer saveUserVisitLog(UserVisitLogModel userVisitLog){
		
		return this.getUserVisitLogDao().saveUserVisitLog(userVisitLog);
	}

	/**
	 * <p>更新用户访问记录</p>
	 * 
	 * @param userVisitLog 用户访问记录
	 * @return void
	 * @see
	 */
	public void updateUserVisitLog(UserVisitLogModel userVisitLog){
				
		this.getUserVisitLogDao().updateUserVisitLog(userVisitLog);
	}

	/**
	 * <p>删除用户访问记录</p>
	 * 
	 * @param userVisitLog 用户访问记录
	 * @return void
	 * @see
	 */
	public void deleteUserVisitLog(UserVisitLogModel userVisitLog){

		this.getUserVisitLogDao().deleteUserVisitLog(userVisitLog);
	}

	public UserVisitLogDao getUserVisitLogDao() {
		return userVisitLogDao;
	}

	public void setUserVisitLogDao(UserVisitLogDao userVisitLogDao) {
		this.userVisitLogDao = userVisitLogDao;
	}

}
