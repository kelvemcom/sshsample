package com.kelvem.sample.system.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kelvem.common.base.ActionBase;
import com.kelvem.sample.queue.Sample1Queue;
import com.kelvem.sample.queue.Sample2Queue;
import com.kelvem.sample.queue.SampleQueue;
import com.kelvem.sample.system.service.TestService;


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

@Component("testAction")
public class TestAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	private static Log log = LogFactory.getLog(TestAction.class);
	
	@Autowired private TestService testService;
	
	public String execute() throws Exception {

		System.out.println("default");
		return SUCCESS;
	}
	
	/**
	 * Log
	 */
	
	public String writeLog100000() throws Exception {
		Date start = new Date();
		for (int i = 0; i < 100000; i++) {
			testService.doNothing();
		}
		log.info("start : " + start);
		log.info("end   : " + new Date());
		return "json";
	}
	
	/**
	 * Queue
	 */
	@Autowired private SampleQueue sampleQueue;
	@Autowired private Sample1Queue sample2Queue;
	@Autowired private Sample2Queue sample3Queue;
	
	private String msg = "msg";
	public String getMsg() {return msg;}
	public void setMsg(String msg) {this.msg = msg;}

//	@Transactional
	public String sendMsg() throws Exception {

		sampleQueue.sendMessage(msg);
		this.showMsg(msg);
		return "index";
	}
//	@Transactional
	public String sendMsg2() throws Exception {

		sample2Queue.sendMessage(msg);
		this.showMsg(msg);
		return "index";
	}
//	@Transactional
	public String sendMsg3() throws Exception {

		sample3Queue.sendMessage(msg);
		this.showMsg(msg);
		return "index";
//		throw new RuntimeException("MQ Err");
	}
	@Transactional
	public String sendMsg4() throws Exception {

		testService.sendMsg(msg);
		this.showMsg(msg);
		return "index";
	}
	
	public String sendMsg10000() throws Exception {

		for (int i = 0; i < 10000; i++) {
			sample2Queue.sendMessage(String.valueOf(i));
		}
		return "json";
	}
	
	@Transactional
	public String sendMsgWithErr() throws Exception {

		for (int i = 0; i < 1; i++) {
			sample2Queue.sendMessage(String.valueOf(i));
		}
		throw new Exception("sendMsgWithErr");
	}

	
}
