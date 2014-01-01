<%@ page language="java" contentType="text/html; charset=UTF-8"
	isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setStatus(200);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />		
		
		<script type="text/javascript" src="jquery/jquery-2.0.2.js"></script>
		
		<title>ERROR</title>
		
		<script type="text/javascript">
			String.prototype.replaceAll = function(s1,s2) { 
			    return this.replace(new RegExp(s1,"gm"),s2); 
			};
												
			$(document).ready(function(){
				$("#stack").html($("#stack").html().replaceAll(" at ", " <br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp at ")
												.replaceAll("\\(","<font color='#111166'>(")
												.replaceAll("Caused by: ","<br>Caused by: ")
												.replaceAll("\\)","</font>)"));
			});
			
			function showStack(){
				$("#stack").css("display", "block");
				$("#show").css("display", "none");
				
				// 拷贝到剪贴板
				//window.clipboardData.setData('text',$("#stack").text()); 
			}
			
		</script>
	</head>
	<body style="background-color:#F9FFF9">
		
		<br>
		<br>
		<span><font style="font-size: 30px; color:#0088DD"><b>抱歉，系统出现异常，请联系管理员！</b></font></span>					
		<br>
		<br>
		<br><font color="#BB6611">异常信息：</font><font color="#DD2211">
		<b><s:property value="exception.message"/></b></font>
		<br>
		<br>
		<a href="javascript:showStack()" id="show"><br>显示异常堆栈详细信息</a>
		<span id="stack" style="line-height: 22px; color: #555555; display: none;">
			<br><font color="#BB6611">错误堆栈信息如下：</font>
			<br><s:property value="exceptionStack"/>						
		</span>
		
	</body>
</html>