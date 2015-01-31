<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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

	<title>编辑用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="jquery/jquery-ui.min.css">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/toggles.css">
    
    <link rel="stylesheet" href="css/style.default.css">
    
	<link rel="stylesheet" href="bootstrap/ext/dataTables.bootstra.css">
	<link rel="stylesheet" href="bootstrap/ext/bootstrap-datetimepicker.css">
	<link rel="stylesheet" href="bootstrap/ext/bootstrap-select.css">
	
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
	  <script src="js/html5shiv.js"></script>
	  <script src="js/respond.min.js"></script>
	<![endif]-->
   
</head>
  
<body>

<!-- Preloader -->
<div id="preloader">
    <div id="status">
        <i class="fa fa-spinner fa-spin"></i>
    </div>
</div>

<section>
  
  <s:include value="/page/menu_left.jsp"/>
  
  <s:include value="/page/menu_right.jsp"/>
  
  <!-- --------------------------------------------------------------------------------------------------------------------------------- -->
  
  <div class="mainpanel">
    
    <s:include value="/page/navbar.jsp"/>
    
    <div class="pageheader">
      <h2><i class="fa fa-home"></i> 用户管理 <span>...</span></h2>
      <div class="breadcrumb-wrapper">
        <span class="label">当前位置:</span>
        <ol class="breadcrumb">
          <li>系统管理</li>
          <li><a href="${ctx}/page/system/sysUser_sysUserList.action">用户管理</a></li>
          <li class="active">用户详情</li>
        </ol>
      </div>
    </div>
    
    <div class="contentpanel">
      <div>
	
	<!-- 
	<div class="row">
		<div class="col-sm-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">750px/12</div>
		<div class="col-sm-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">2</div>
		<div class="col-sm-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">3</div>
		<div class="col-sm-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">4</div>
		<div class="col-sm-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">5</div>
		<div class="col-sm-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">6</div>
		<div class="col-sm-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">7</div>
		<div class="col-sm-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">8</div>
		<div class="col-sm-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">9</div>
		<div class="col-sm-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">10</div>
		<div class="col-sm-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">11</div>
		<div class="col-sm-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">12</div>
	</div>
	-->
	<div class="col-md-9 column">
	
		<form class="form-horizontal" role="form" id="form">
	
			<c:if test="${empty sysUser}">
			<h3>未查到相关数据&nbsp&nbsp&nbsp&nbsp<a href="javascript:submit_form('back');" class="btn btn-info"><i class="glyphicon glyphicon-circle-arrow-left"></i> 返回</a></h3>
			</c:if>
		
			<c:if test="${!empty sysUser}">
			<br>
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-5">
					<a href="javascript:submit_form('back');" class="btn btn-info"><i class="glyphicon glyphicon-circle-arrow-left"></i> 返回</a>
					<a href="javascript:submit_form('update_done');" class="btn btn-info"><span class="glyphicon glyphicon-floppy-saved"></span> 保存</a>
				</div>
			</div>

			<div class="form-group">
				<label for="inputEmail3" class="col-sm-3 control-label">用户ID</label>
				<div class="col-sm-8 col-md-6">
					<input type="text" class="form-control" name="sysUser.sysUserId" placeholder="Email" value="${sysUser.sysUserId}">
				</div>
			</div>
	
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-3 control-label">用户显示名</label>
				<div class="col-sm-8 col-md-6">
					<input type="text" class="form-control" name="sysUser.userLogonName" placeholder="Email" value="${sysUser.userLogonName}">
				</div>
			</div>
	
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-3 control-label">用户登录名</label>
				<div class="col-sm-8 col-md-6">
					<input type="text" class="form-control" name="sysUser.sysUserName" placeholder="Email" value="${sysUser.sysUserName}">
				</div>
			</div>
			
			<div class="form-group">
			    <label for="inputPassword3" class="col-sm-3 control-label">密码</label>
			    <div class="col-sm-8 col-md-6">
			      <input type="password" class="form-control" name="sysUser.userPassword" placeholder="Password" value="${sysUser.userPassword}">
			    </div>
	  		</div>
	
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-3 control-label">用户类型</label>
				<div class="col-sm-8 col-md-6">
					<input type="text" class="form-control" name="sysUser.userTypeCode" placeholder="Email" value="${sysUser.userTypeCode}">
				</div>
			</div>
	
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-3 control-label">人个信息ID</label>
				<div class="col-sm-8 col-md-6">
					<input type="text" class="form-control" name="sysUser.personInfoId" placeholder="Email" value="${sysUser.personInfoId}">
				</div>
			</div>
	
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-3 control-label">描述</label>
				<div class="col-sm-8 col-md-6">
					<input type="text" class="form-control" name="sysUser.userDescs" placeholder="Email" value="${sysUser.userDescs}">
				</div>
			</div>
	
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-3 control-label">状态标志</label>
				<div class="col-sm-8 col-md-6">
					<input type="text" class="form-control" name="sysUser.statusCode" placeholder="Email" value="${sysUser.statusCode}">
				</div>
			</div>
	
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-3 control-label">状态修改时间</label>
				<div class="col-sm-8 col-md-6">
					<input type="text" class="form-control" name="sysUser.statusChangeTime" placeholder="Email" value="${sysUser.statusChangeTime}">
				</div>
			</div>
	
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-3 control-label">删除标志</label>
				<div class="col-sm-8 col-md-6">
					<input type="text" class="form-control" name="sysUser.delFlag" placeholder="Email" value="${sysUser.delFlag}">
				</div>
			</div>
	
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-5">
					<a href="javascript:submit_form('back');" class="btn btn-info"><i class="glyphicon glyphicon-circle-arrow-left"></i> 返回</a>
					<a href="javascript:submit_form('update_done');" class="btn btn-info"><span class="glyphicon glyphicon-floppy-saved"></span> 保存</a>
				</div>
			</div>
			</c:if>
		</form>
		
	</div>
	
      </div><!-- row -->
    </div><!-- contentpanel -->
    
  </div><!-- mainpanel -->
  
</section>
	
</body>



    
<!-- <script type="text/javascript" src="jquery/jquery-2.0.2.js"></script>
<script type="text/javascript" src="jquery/jquery-1.11.0.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script> -->

<script type="text/javascript" src="jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/toggles.min.js"></script>
<script type="text/javascript" src="js/custom.js"></script>

<script type="text/javascript" src="jquery/plugin/jquery.pagination.js"></script>
<script type="text/javascript" src="bootstrap/ext/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="bootstrap/ext/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="bootstrap/ext/bootstrap-select.js"></script>


<script type="text/javascript">

	function submit_form(button_id) {
		//alert(button_id);
		if (button_id == 'back') {
			document.getElementById("form").action = "${ctx}/page/system/sysUser_sysUserList.action";
			document.getElementById("form").submit();
		} else if (button_id == 'update_done') {
			document.getElementById("form").action = "${ctx}/page/system/sysUser_sysUserUpdateDone.action";
			document.getElementById("form").submit();
		}
	};
</script>

</html>
