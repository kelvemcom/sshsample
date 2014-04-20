/**============================================================
 * 版权：kelvem 版权所有 (c) 2012 - 2013
 * 文件：com.kelvem.system.service.TestService.java
 * 所含类: TestService.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-08-29	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kelvem.sample.queue.Sample6QueueService;


/**
 * <p>TestService</p>
 *
 * <p>用于测试的Stub</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2013</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName TestService
 * @author kelvem
 * @version 1.0
 */
@Component("testService")
public class TestService {
	
	private static Log log = LogFactory.getLog(TestService.class);
	
	/**
	 * <p>什么也不做</p>
	 * 
	 *  void
	 * @see
	 */
	public void doNothing(){
		log.info("什么也不做");
	}

	@Autowired private Sample6QueueService sampleQueueService;
	
	public void sendMsg(String msg) throws Exception {

		sampleQueueService.sendMessage(msg);
//		throw new RuntimeException("MQ Err");
	}


}
