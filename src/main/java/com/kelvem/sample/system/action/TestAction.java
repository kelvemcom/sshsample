package com.kelvem.sample.system.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kelvem.common.base.ActionBase;
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
	

	
}
