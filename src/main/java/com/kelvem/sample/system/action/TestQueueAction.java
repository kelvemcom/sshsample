package com.kelvem.sample.system.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kelvem.common.base.ActionBase;
import com.kelvem.sample.queue.Sample6Queue;


//常用的注解如下：
//Namespace：指定命名空间。
//ParentPackage：指定父包。
//Result：提供了Action结果的映射。（一个结果的映射）
//Results：“Result”注解列表
//ResultPath：指定结果页面的基路径。
//Action：指定Action的访问URL。
//Actions：“Action”注解列表。
//ExceptionMapping：指定异常映射。（映射一个声明异常）
//ExceptionMappings：一级声明异常的数组。
//InterceptorRef：拦截器引用。
//InterceptorRefs：拦截器引用组。

@Component("testQueueAction")
public class TestQueueAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(TestQueueAction.class);
	
	/**
	 * Queue
	 */
	@Autowired private Sample6Queue sampleQueue;
	
	private String msg = "msg";

	// http://kelvem-pc2:8080/sshsample/page/testQueue_sendMsg.action?msg=abc
	@Transactional
	public String sendMsg() throws Exception {
		sampleQueue.sendMessage(msg);
		return "json";
	}
	
//	@Transactional
	public String sendMsg2() throws Exception {
		sampleQueue.sendMessage(msg);
		return "json";
	}
	
//	@Transactional
	public String sendMsg3() throws Exception {
		sampleQueue.sendMessage(msg);
		throw new RuntimeException("MQ Err");
	}
	@Transactional
	public String sendMsg4() throws Exception {
		sampleQueue.sendMessage(msg);
		throw new RuntimeException("MQ Err");
	}
	
	public String sendMsg10000() throws Exception {

		for (int i = 0; i < 10000; i++) {
			sampleQueue.sendMessage(String.valueOf(i));
		}
		return "json";
	}
	
	@Transactional
	public String sendMsgWithErr() throws Exception {

		for (int i = 0; i < 10; i++) {
			sampleQueue.sendMessage(String.valueOf(i));
		}
		throw new Exception("sendMsgWithErr");
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
