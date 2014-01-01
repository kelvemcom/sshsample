/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.service.SeqService.java
 * 所含类: SeqService.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-15	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kelvem.sample.system.dao.SeqDao;

/**
 * <p>SeqService</p>
 *
 * <p>seq管理模块Service</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SeqService
 * @author kelvem
 * @version 1.0
 * 
 */
@Component("seqService")
public class SeqService {
	
	private static Log log = LogFactory.getLog(SeqService.class);

	@Autowired private SeqDao seqDao; 
	
	/**
	 * <p>increment</p>
	 * 
	 * @param seq seq
	 * @return void
	 * @see
	 */
	public Integer increment(){
		
		return (Integer)seqDao.increment();
	}

}
