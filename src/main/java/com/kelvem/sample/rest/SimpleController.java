package com.kelvem.sample.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kelvem.sample.system.model.SysUserModel;
import com.kelvem.sample.system.service.SysUserService;

/**
 * 
 * @author kelvem
 * 
 * http://localhost:8080/sshsample/rest/simple/user/7
 * http://localhost:8080/sshsample/rest/simple/ajax
 */
@Controller
@RequestMapping("/simple")
public class SimpleController {
	
	private static Log log = LogFactory.getLog(SimpleController.class);
	
	@Autowired private SysUserService sysUserService;
	
	//映射路径/simple/index当访问这个路径时，执行这个方法
	@RequestMapping("/index")
	public String index(HttpServletRequest request ,HttpServletResponse response){
               //response,request会自动传进来
		request.setAttribute("message", "Hello,This is a example of Spring3 RESTful!");
		log.info("Hello,This is a example of Spring3 RESTful!");
		
		return "index.jsp";
	}
	
	//根据ID获取不同的内容，通过@PathVariable 获得属性
	@RequestMapping(value="/user/{user_id}",method=RequestMethod.GET)
	public @ResponseBody String get(@PathVariable Integer user_id,HttpServletRequest request ,HttpServletResponse response) throws IOException{
		log.info("sysUserService.getSysUserById(user_id)");
		SysUserModel user = sysUserService.getSysUserById(user_id);
		log.info("user : " + user);
		JSONObject json = new JSONObject();
		json.element("user_id", user.getSysUserId());
		json.element("user_name", user.getUserLogonName());
		return user.getUserLogonName();
	}
	
	//返回null
	@RequestMapping(value="/null",method=RequestMethod.GET)
	public String getNull(@PathVariable String id,HttpServletRequest request ,HttpServletResponse response) throws IOException{
		request.setAttribute("message", "Hello,This is a example of Spring3 RESTful!<br/>ID:"+id+"");
		response.getWriter().write("You put result is null");
		return null;
	}

	@RequestMapping("/ajax")
	@ResponseBody
	public List<String> ajax(HttpServletRequest request){
		List<String> list=new ArrayList<String>();
		list.add("电视");
		list.add("洗衣机");
		list.add("冰箱");
		list.add("电脑");
		list.add("汽车");
		list.add("空调");
		list.add("自行车");
		list.add("饮水机");
		list.add("热水器");
		return list;
	}

	@RequestMapping("/ajax1")
	@ResponseBody
	public Object ajax1(HttpServletRequest request){
		List<String> list=new ArrayList<String>();
		list.add("电视");
		list.add("洗衣机");
		list.add("冰箱");
		list.add("电脑");
		list.add("汽车");
		list.add("空调");
		list.add("自行车");
		list.add("饮水机");
		list.add("热水器");
		return list;
	}
}
