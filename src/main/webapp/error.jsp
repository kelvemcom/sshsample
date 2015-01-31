<%@ page language="java" contentType="text/html; charset=UTF-8"
	isErrorPage="true" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<base href="<%=basePath%>">	
		

	<script type="text/javascript" src="${ctx}/jquery/jquery-1.10.2.min.js"></script>
		
		<title>ERROR</title>
		
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
	
	
	<script type="text/javascript">
		String.prototype.replaceAll = function(s1,s2) { 
		    return this.replace(new RegExp(s1,"gm"),s2); 
		};
		
		$(document).ready(function(){
			$("#stack").html($("#stack").html().replaceAll("at ", " <br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp at ")
											.replaceAll("\\(","<font color='#111166'>(")
											.replaceAll("Caused by: ","<br>Caused by: ")
											.replaceAll("\\)","</font>)"));
		});
		
		function showStack(){
			$("#stack").css("display", "block");
			$("#show").css("display", "none");
			
			// 拷贝到剪贴板
			window.clipboardData.setData('text',$("#stack").text()); 
		}
		
	</script>
		
</html>