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
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<base href="<%=basePath%>">

	<title>用户列表</title>
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
			if (button_id == 'add') {
				document.getElementById("table_form").action = "${ctx}/page/system/sysUser_sysUserAdd.action";
				document.getElementById("table_form").submit();
			} else if (button_id == 'enable') {
				document.getElementById("table_form").action = "${ctx}/page/system/sysUser_sysUserEnable.action";
				document.getElementById("table_form").submit();
			} else if (button_id == 'disenable') {
				document.getElementById("table_form").action = "${ctx}/page/system/sysUser_sysUserDisenable.action";
				document.getElementById("table_form").submit();
			}
		};
	</script>
	
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
	</style>
   
</head>
  
<body>

	<div class="container1">
    <s:include value="/page/navbar.jsp"></s:include>
    
	
		<!--   <s:include value="/page/menu.jsp"></s:include> -->
		
		<div class="col-md-2">
			<div id="menu_level2" class="panel-group">
			</div>
		</div>
		
		
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
	<div id="main" class="col-md-9 column">
		
		${breadcrumb}
		
		<form id="query_form" action="page/system/sysUser_sysUserList.action" method="get" class="form-horizontal" role="form">
			<div class="row">
				<div class="input-group col-sm-4 col-lg-3 column">
					<span class="input-group-addon">用户ID</span>
					<input type="text" class="form-control" placeholder="" name="queryVo.sysUserId" value="${queryVo.sysUserId}">
				</div>
				<div class="input-group col-sm-4 col-lg-3 column">
					<span class="input-group-addon">登录名</span>
					<input type="text" class="form-control" placeholder="" name="queryVo.sysUserName" value="${queryVo.sysUserName}" title="模糊查询">
				</div>
				<div class="input-group col-sm-4 col-lg-3 column">
					<span class="input-group-addon">显示名</span>
					<input type="text" class="form-control" placeholder="" name="queryVo.userLogonName" value="${queryVo.userLogonName}" title="模糊查询">
				</div>
				<div class="input-group col-sm-4 col-lg-3 column">
					<span class="input-group-addon">用户类型</span>
					<!-- <input type="text" class="form-control" placeholder="" name="queryVo.userTypeCode" value="${queryVo.userTypeCode}"> -->
					<k:domain type="select" name="queryVo.userTypeCode" value="${queryVo.userTypeCode}" cssClass="form-control" cssStyle="width: 70%;" 
						domainType="userTypeCode" emptyItem=""/>
				</div>
				<div class="input-group col-sm-4 col-lg-3 column">
					<span class="input-group-addon">状态标志</span>
					<!-- <input type="text" class="form-control" placeholder="" name="queryVo.statusCode" value="${queryVo.statusCode}"> -->
					<k:domain type="select" name="queryVo.statusCode" value="${queryVo.statusCode}" cssClass="form-control" cssStyle="width: 70%;" 
						domainType="statusCode" emptyItem=""/>
				</div>
				<div class="input-group col-sm-4 col-lg-3 column">
					<span class="input-group-addon">创建时间</span>
					<input type="text" class="form-control" placeholder="" name="queryVo.createTime" value="${queryVo.createTime}">
				</div>
				
				<div class="input-group col-sm-4 col-lg-3 column pull-right">
					<button class="btn btn-info" type="button" onclick="submit();"><i class="glyphicon glyphicon-search"></i> 查询</button>&nbsp;
					<button class="btn btn-info" type="button" onclick="clear();"><i class="glyphicon glyphicon-remove-circle"></i> 清空</button>
				</div>
			</div>
		</form>
		
		<br>
		
		<div class="row" style="border-top-style: solid; border-top-color: #888888; padding-top: 10px;">
		<form id="table_form" action="" method="get" class="form-horizontal" role="form">
			<button class="btn btn-info" type="button" onclick="submit_form('add');" id="add"><i class="glyphicon glyphicon-plus-sign"></i> 增加</button>
			<button class="btn btn-info" type="button" onclick="submit_form('enable');" id="enable"><i class="glyphicon glyphicon-ok-sign"></i> 启用</button>
			<button class="btn btn-info" type="button" onclick="submit_form('disenable');" id="disenable"><i class="glyphicon glyphicon-remove-sign"></i> 停用</button>
			<br>
			<div class="table-responsive">
		
			<table id='list' class="table table-hover table-striped datatable" style="word-break:break-all;">
				<thead class='info'>
					<tr class='info'>
						<th><input type="checkbox" id="select_all" onclick="onchange_checkbox();"></th>
						<th>用户ID</th>
						<th>用户显示名</th>
						<th>用户登录名</th>
						<th>用户类型</th>
						<th>状态标志</th>
						<th>创建时间</th>
						<th>更新时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty pageResult.results}">
					<c:forEach items="${pageResult.results}" var="row" varStatus="sta">
					<tr id="id_${row.sysUserId}">
						<td><input type="checkbox" id="selected" name="selected" value="${row.sysUserId}"></td>
						<td>${row.sysUserId}</td>
						<td>${row.sysUserName}</td>
						<td>${row.userLogonName}</td>
						<td><k:domain type="label" domainType="userTypeCode" value="${row.userTypeCode}"/></td>
						<td><k:domain type="label" domainType="statusCode" value="${row.statusCode}"/></td>
						<td>${row.createTime}</td>
						<td>${row.updateTime}</td>
						<td style="min-width: 60px">
							<a href="${ctx}/page/system/sysUser_sysUserDetail.action?sysUser.sysUserId=${row.sysUserId}">[详情]</a>
							<a href="${ctx}/page/system/sysUser_sysUserUpdate.action?sysUser.sysUserId=${row.sysUserId}">[修改]</a> 
							<a href="${ctx}/page/system/sysUser_sysUserDel.action?sysUser.sysUserId=${row.sysUserId}">[删除]</a>
						</td>
					</tr>
					</c:forEach>
					</c:if>
				</tbody>
			</table>
			</div>
		</form>
		</div>
		
		<div class="col-md-3 col-md-offset-1">
			<br>
			PageResults.totalCount = ${pageResult.totalCount}<br>
			PageResults.pageSize = ${pageResult.pageSize}<br>
			PageResults.currentPage = ${pageResult.currentPage}<br>
			param.formName = ${param.formName}<br>
		</div>
		<div class="col-md-3 col-md-offset-1">
			<br>
			ctx=${ctx}<br>
			basePath=${basePath}<br>
			path=${path}<br>
		</div>
		
	</div>
	
	</div>

</body>

<script type="text/javascript">
    
    var init = false;
    function submit(page_index, jq){
	
    	if (init == false) {
			init = true;
			return false;
		}
    	
    	//alert("submit");
    	
    	$("#add_form").bind("submit",
		    function()
		    {
		   	   $("#add_form").append("<input type='hidden' id='pageNo' name='pageNo'  value='" + page_index + "'/>");
		   	   $("#add_form").append("<input type='hidden' id='pageSize' name='pageSize' value='${pageSize}'/>");

		   	   var form=document.forms["add_form"];
		   	   form.submit();
		    }
		);
		$("#add_form").submit();
    	return false;
    }
    
    function clear_input(jquery_selector){
    	$(jquery_selector + " :input").val("");
    }
    
    
    function clear(){
    	clear_input("#query_form");
    }
    
    $("#check1").click(function(){
    	//alert($(this).attr("checked"));
    	//alert(document.getElementById("check1").checked);
    	alert($('#check1:checked'));
    	
    })
    
    function onchange_checkbox(){
    	var flag = document.getElementById("select_all").checked;
	    
	    var ckbs = document.all['selected'];
	    for(var i=0;i<ckbs.length;i++) 
	    { 
	    	ckbs[i].checked = flag; 
	    }
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
