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

	<title>增加用户</title>
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
	
	<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap-responsive.css">
	<link rel="stylesheet" href="bootstrap/ext/dataTables.bootstra.css">
    
	<script type="text/javascript" src="jquery/jquery-2.0.2.js"></script>
	<script type="text/javascript" src="jquery/plugin/jquery.pagination.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
	
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="bootstrap/js/html5shiv.js"></script>
	<![endif]-->
	
	<script type="text/javascript">

		function submit_form(button_id) {
			//alert(button_id);
			if (button_id == 'back') {
				document.getElementById("form").action = "${ctx}/page/system/sysUser_sysUserList.action";
				document.getElementById("form").submit();
			} else if (button_id == 'add_done') {
				document.getElementById("form").action = "${ctx}/page/system/sysUser_sysUserAddDone.action";
				document.getElementById("form").submit();
			}
		};
	</script>
   
</head>
  
<body>
  
    <s:include value="/page/navbar.jsp"></s:include>
	
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
			
			<br>
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-5">
					<a href="javascript:submit_form('back');" class="btn btn-info"><i class="glyphicon glyphicon-circle-arrow-left"></i> 返回</a>
					<a href="javascript:submit_form('add_done');" class="btn btn-info"><span class="glyphicon glyphicon-floppy-saved"></span> 增加</a>
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
					<a href="javascript:submit_form('add_done');" class="btn btn-info"><span class="glyphicon glyphicon-floppy-saved"></span> 增加</a>
				</div>
			</div>

		</form>
	</div>
	
	<s:include value="/page/menu.jsp"></s:include>
	
</body>
    
<script type="text/javascript" src="jquery/jquery-2.0.2.js"></script>
<script type="text/javascript" src="jquery/plugin/jquery.pagination.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>

<script type="text/javascript">
    
    var init = false;
    function submit(page_index, jq){
	
    	if (init == false) {
			init = true;
			return false;
		}
    	
    	//alert("submit");
    	
    	$("#query").bind("submit",
		    function()
		    {
		   	   $("#query").append("<input type='hidden' id='pageNo' name='pageNo'  value='" + page_index + "'/>");
		   	   $("#query").append("<input type='hidden' id='pageSize' name='pageSize' value='${pageSize}'/>");

		   	   var form=document.forms["query"];
		   	   form.submit();
		    }
		);
		$("#query").submit();
    	return false;
    }
    
	$(document).ready(function(){
	
		$("tbody tr:even").addClass("info");
		//$("tbody tr:odd").addClass("success");
		
		//$("tr:not(:first)")
		//$("tr:even").each(function(){   this.style.backgroundColor  =  '#ccc' })   //当然even能实现
		
		//alert($("#id_11 td:nth-child(1)").html());
		
		var edit_html = '';
		//edit_html = edit_html + '<tr id="id_edit" class="error">';
		edit_html = edit_html + '	<td>' + $("#id_11 td:nth-child(1)").html() + '</td>';
		edit_html = edit_html + '	<td><input type="text" placeholder="…" value="' + $("#id_11 td:nth-child(2)").html() + '"></td>';
		edit_html = edit_html + '	<td><input type="text" placeholder="…" value="' + $("#id_11 td:nth-child(3)").html() + '"></td>';
		edit_html = edit_html + '	<td><input type="text" placeholder="…" value="' + $("#id_11 td:nth-child(4)").html() + '"></td>';
		edit_html = edit_html + '	<td>';
		edit_html = edit_html + '		<a href="${ctx}/page/sysUser_sysUserList.action?sysUserId=${row.sysUserId}">[保存]</a>';
		edit_html = edit_html + '		<a href="${ctx}/page/sysUser_sysUserList.action?sysUserId=${row.sysUserId}">[增加]</a>';
		edit_html = edit_html + '	</td>';
		//edit_html = edit_html + '</tr>';
		
		//$("#id_11").html(edit_html);
		$("#id_11").addClass("error");
		
		//alert($("#Pagination"));
		//alert($("#Pagination").pagination());
		//current_page:${pageResult.currentPage},
		$("#Pagination").pagination(${pageResult.totalCount}, {
			items_per_page:${pageResult.pageSize},
			current_page:${pageResult.currentPage},
			num_display_entries:8,
			num_edge_entries:2,
			prev_text:"上一页",
			next_text:"下一页",
			
			callback:submit
		});

		
	});		
</script>
</html>
