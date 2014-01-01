<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<base href="<%=basePath%>">

	<title>用户详情</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!-- Le styles -->
	<style type="text/css">
		body {
			padding-top: 40px;
			padding-bottom: 40px;
			background-color: #f5f5f5;
		}
	</style>
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="bootstrap/ext/dataTables.bootstra.css" rel="stylesheet">
    
	<script type="text/javascript" src="jquery/jquery-2.0.2.js"></script>
	<script type="text/javascript" src="jquery/plugin/jquery.pagination.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
	
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="bootstrap/js/html5shiv.js"></script>
	<![endif]-->
   
</head>
  
<body>
  
    <s:include value="/page/navbar.jsp"></s:include> 
	<br>
	<form id="back" action="page/system/sysUser_sysUserList.action" method="get">
		<button class="btn btn-info" type="button" onclick="submit();">返回</button>
	</form>
	<form id="update" action="page/system/sysUser_sysUserUpdate.action?sysUser.sysUserId=${sysUser.sysUserId}" method="get">
		<input type="hidden" name="sysUser.sysUserId" value="${sysUser.sysUserId}"/>
		<button class="btn btn-info" type="button" onclick="submit();">编辑</button>
	</form>

<form class="form-horizontal" role="form">

  <div class="form-group">
    <label for="inputEmail3" class="col-sm-6 control-label">用户显示名</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Email" value="${sysUserName}">
    </div>
  </div>

  <div class="form-group">
    <label for="inputEmail3" class="col-sm-6 control-label">用户登录名</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Email" value="${userLogonName}">
    </div>
  </div>

  <div class="form-group">
    <label for="inputEmail3" class="col-sm-6 control-label">密码</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Email" value="${userPassword}">
    </div>
  </div>

  <div class="form-group">
    <label for="inputEmail3" class="col-sm-6 control-label">用户类型</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Email" value="${userTypeCode}">
    </div>
  </div>

  <div class="form-group">
    <label for="inputEmail3" class="col-sm-6 control-label">人个信息ID</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Email" value="${personInfoId}">
    </div>
  </div>

  <div class="form-group">
    <label for="inputEmail3" class="col-sm-6 control-label">描述</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Email" value="${userDescs}">
    </div>
  </div>

  <div class="form-group">
    <label for="inputEmail3" class="col-sm-6 control-label">状态标志</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Email" value="${statusCode}">
    </div>
  </div>

  <div class="form-group">
    <label for="inputEmail3" class="col-sm-6 control-label">状态修改时间</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Email" value="${statusChangeTime}">
    </div>
  </div>

  <div class="form-group">
    <label for="inputEmail3" class="col-sm-6 control-label">删除标志</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Email" value="${delFlag}">
    </div>
  </div>

  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Sign in</button>
    </div>
  </div>
  
</form>
	
	
</body>

</html>
