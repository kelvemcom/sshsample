<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sa" uri="/sampleTag"%>
<%@ taglib prefix="k" uri="/kelvemTag"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<base href="<%=basePath%>">

	<title>用户角色列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap-responsive.css">
	<link rel="stylesheet" href="bootstrap/ext/dataTables.bootstra.css">
	<link rel="stylesheet" href="bootstrap/ext/bootstrap-datetimepicker.css">
	<link rel="stylesheet" href="bootstrap/ext/bootstrap-select.css">
    
	<!-- <script type="text/javascript" src="jquery/jquery-2.0.2.js"></script> -->
	<script type="text/javascript" src="jquery/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="jquery/plugin/jquery.pagination.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="bootstrap/ext/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="bootstrap/ext/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="bootstrap/ext/bootstrap-select.js"></script>

	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="bootstrap/js/html5shiv.js"></script>
	<![endif]-->
	
	<!-- Le styles -->
	<style type="text/css">
		
		@media (min-width: 1350px) {
			.container {
				width: 1320px;
			}
		}
		@media (min-width: 1550px) {
			.container {
				width: 1520px;
			}
		}
		
		body {
			position: relative; /* For scrollyspy */
			padding-top: 60px; /* Account for fixed navbar */
			background-color: #f5f5f5;
		}
		.input-group {
			padding: 5px 15px 5px 15px
		}
		.input-group-addon {
			text-align: right;
		}
		
		#main {
			height: 100%;
			background-color: #ffffff;
		}
		
		#data_table {
			border-top-style: solid; 
			border-top-color: #888888; 
			padding-top: 10px;
			
			border-bottom-style: solid; 
			border-bottom-color: #888888; 
			padding-bottom: 10px;
		}
	</style>
   
</head>
  
<body>

<!-- <div class="container"> -->
<div>
		
	<!--
	<div class="row">
		<div class="col-md-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">970px/12</div>
		<div class="col-md-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">2</div>
		<div class="col-md-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">3</div>
		<div class="col-md-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">4</div>
		<div class="col-md-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">5</div>
		<div class="col-md-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">6</div>
		<div class="col-md-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">7</div>
		<div class="col-md-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">8</div>
		<div class="col-md-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">9</div>
		<div class="col-md-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">10</div>
		<div class="col-md-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">11</div>
		<div class="col-md-1" style="border-width: 2px; border-color: blue; border-style: solid; min-height: 50px">12</div>
	</div>
	 -->

    <s:include value="/page/navbar.jsp"></s:include>
	
	<s:include value="/page/menu.jsp"></s:include>
	
	<div id="main" class="col-md-9 column">
		
		${breadcrumb}
					
		<form id="form" action="page/system/sysRole_sysUserRoleList.action" method="get" class="form-horizontal" role="form">
		
			<br>
			
			<div id="data_table" class="row">
				<button class="btn btn-info" type="button" onclick="submit_form('add');" id="add"><i class="glyphicon glyphicon-floppy-saved"></i> 保存</button>
				<button class="btn btn-info" type="button" onclick="submit_form('enable');" id="enable"><i class="glyphicon glyphicon-ok-sign"></i> 启用</button>
				<button class="btn btn-info" type="button" onclick="submit_form('disenable');" id="disenable"><i class="glyphicon glyphicon-remove-sign"></i> 停用</button>
				
				<div class="table-responsive">
			
				<table id='list' class="table table-hover table-striped datatable" style="word-break:break-all;">
					<thead class='info'>
						<tr class='info'>
							<th><input type="checkbox" id="select_all" onclick="onchange_checkbox('select_all');"></th>
							<th>角色ID</th>
							<th>角色显示名</th>
							<th>状态标志</th>
							<th>创建时间</th>
							<th>更新时间</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty pageResult.results}">
						<c:forEach items="${pageResult.results}" var="row" varStatus="sta">
						<tr id="id_${row.sysRoleId}">
							<td><input type="checkbox" id="selected" name="selected" value="${row.sysRoleId}"></td>
							<td>${row.sysRoleId}</td>
							<td>${row.sysRoleName}</td>
							<td><k:domain type="label" domainType="statusCode" value="${row.statusCode}"/></td>
							<td>${row.createTime}</td>
							<td>${row.updateTime}</td>
						</tr>
						</c:forEach>
						</c:if>
					</tbody>
				</table>
				<c:if test="${empty pageResult.results}">
					<h4>&nbsp;&nbsp;&nbsp;没有查询到相关数据</h4>
				</c:if>
				</div>
				<div>
					<ul id="pagination" class="pagination column pull-right"></ul>
					<input type="hidden" id='pageNo' name='pageNo'  value='${pageResult.currentPage}'/><br>
					<input type='hidden' id='pageSize' name='pageSize' value='${pageResult.pageSize}'/><br>
				</div>
			</div>
		</form>
		
	</div>
	
</div>

</body>

	
<script type="text/javascript">

	function submit_form(button_id) {
		//alert(button_id);
		if (button_id == 'add') {
			document.getElementById("form").action = "${ctx}/page/system/sysRole_sysUserRoleUpdate.action";
			document.getElementById("form").submit();
		} else if (button_id == 'enable') {
			document.getElementById("form").action = "${ctx}/page/system/sysRole_sysRoleEnable.action";
			document.getElementById("form").submit();
		} else if (button_id == 'disenable') {
			document.getElementById("form").action = "${ctx}/page/system/sysRole_sysRoleDisenable.action";
			document.getElementById("form").submit();
		}
    	return false;
	};
    
    function onchange_checkbox(ckb_id){
    	var flag = document.getElementById(ckb_id).checked;
	    
	    var ckbs = document.all['selected'];
	    for(var i=0;i<ckbs.length;i++)
	    {
	    	ckbs[i].checked = flag; 
	    }
    }
    
	$(document).ready(function(){
		
	});		
</script>
</html>
