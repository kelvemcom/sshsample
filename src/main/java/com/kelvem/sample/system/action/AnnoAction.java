package com.kelvem.sample.system.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;


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

@Component("annoAction")
@ParentPackage("application-struts-default")
@Namespace("/")
@Results({ 
	@Result(name = "success", location = "/index.jsp"), 
	@Result(name = "json", type= "json"), 
	@Result(name = "error", location = "/error.jsp"), 
	@Result(name = "dispatcher", type = "dispatcher", location = "/index.jsp"), 
	@Result(name = "redirect", type = "redirect", location = "/index.jsp"),
	@Result(name = "redirectAction", type = "redirectAction", location = "dispatcher.action"),
//	@Result(name = "directAction", type = "direct-action", location = "/index.jsp"),
	@Result(name = "plainText", type = "plainText", location = "/index.jsp")
})
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
public class AnnoAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	public String execute() throws Exception {

		System.out.println("default");
		return SUCCESS;
	}
	
	@Action("exception")
	public String exception() throws Exception {
		throw new Exception("出错啦");
	}

	@Action("plainText")
	public String plainText() throws Exception {
		System.out.println("plainText");
		return "plainText";
	}

	@Action("dispatcher")
	public String dispatcher() throws Exception {
		System.out.println("dispatcher");
		return "dispatcher";
	}

	@Action("redirect")
	public String redirect() throws Exception {
		System.out.println("redirect");
		return "redirect";
	}

	@Action("redirectAction")
	public String redirectAction() throws Exception {
		System.out.println("redirectAction");
		return "redirectAction";
	}

	@Action("directAction")
	public String directAction() throws Exception {
		System.out.println("direct-action");
		return "direct-action";
	}

	private static int count = 0;
	@Action("duplicate")
	public String duplicate() throws Exception {
		Thread.sleep(3000);
		System.out.println("duplicate " + (count++));
		return "success";
	}
	
}
