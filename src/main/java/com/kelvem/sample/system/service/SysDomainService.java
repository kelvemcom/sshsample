/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.service.SysDomainService.java
 * 所含类: SysDomainService.java
 * 修改记录：
 * 日期				作者						内容
 * =============================================================
 * 2014-02-22	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kelvem.common.utils.StringUtil;
import com.kelvem.sample.system.dao.SysDomainDao;
import com.kelvem.sample.system.model.SysDomainModel;

/**
 * <p>SysDomainService</p>
 * 
 * <p>值域管理管理模块Service</p>
 * 
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysDomainService
 * @author kelvem
 * @version 1.0
 */
@Component("sysDomainService")
public class SysDomainService {

	private static Log log = LogFactory.getLog(SysDomainService.class);

	@Autowired private SysDomainDao sysDomainDao; 

	/**
	 * <p>查询值域管理列表</p>
	 * 
	 * @param domainType 值域类型 
	 * @return List<SysDomainModel> 值域管理列表
	 * @see
	 */
	public List<SysDomainModel> querySysDomain(String domainType){ 
		
		if (StringUtil.isEmpty(domainType)) {
			log.error("参数domainType为空");
			throw new RuntimeException("参数domainType为空");
		}
		List<SysDomainModel> list = this.sysDomainDao.querySysDomain(domainType);
		
		return list;
	}

	/**
	 * <p>保存值域管理</p>
	 * 
	 * @param sysDomain 值域管理
	 * @return Integer keyId
	 * @see
	 */
	public Integer saveSysDomain(SysDomainModel sysDomain){
		
		return this.getSysDomainDao().saveSysDomain(sysDomain);
	}

	/**
	 * <p>更新值域管理</p>
	 * 
	 * @param sysDomain 值域管理
	 * @return void
	 * @see
	 */
	public void updateSysDomain(SysDomainModel sysDomain){
				
		this.getSysDomainDao().updateSysDomain(sysDomain);
	}

	/**
	 * <p>删除值域管理</p>
	 * 
	 * @param sysDomain 值域管理
	 * @return void
	 * @see
	 */
	public void deleteSysDomain(SysDomainModel sysDomain){

		this.getSysDomainDao().deleteSysDomain(sysDomain);
	}

	public SysDomainDao getSysDomainDao() {
		return sysDomainDao;
	}

	public void setSysDomainDao(SysDomainDao sysDomainDao) {
		this.sysDomainDao = sysDomainDao;
	}

}
