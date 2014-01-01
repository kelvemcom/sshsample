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

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span9">
				<table id='list' class="table table-bordered table-hover table-striped datatable">
					<thead class='info'>
						<tr class='info'>
							<th class="span5">名称</th>
							<th>值</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${empty sysUser}">
						<tr><td colspan="2">未查到相关数据</td></tr>
					</c:if>
					<c:if test="${!empty sysUser}">
						<tr><td class='info'>用户ID</td><td>${sysUser.sysUserId}</td></tr>
						<tr><td class='info'>用户显示名</td><td>${sysUser.sysUserName}</td></tr>
						<tr><td class='info'>用户登录名</td><td>${sysUser.userLogonName}</td></tr>
						<tr><td class='info'>密码</td><td>${sysUser.userPassword}</td></tr>
						<tr><td class='info'>用户类型</td><td>${sysUser.userTypeCode}</td></tr>
						<tr><td class='info'>人个信息ID</td><td>${sysUser.personInfoId}</td></tr>
						<tr><td class='info'>描述</td><td>${sysUser.userDescs}</td></tr>
						<tr><td class='info'>状态标志</td><td>${sysUser.statusCode}</td></tr>
						<tr><td class='info'>状态修改时间</td><td>${sysUser.statusChangeTime}</td></tr>
						<tr><td class='info'>删除标志</td><td>${sysUser.delFlag}</td></tr>
						<tr><td class='info'>删除时间</td><td>${sysUser.delTime}</td></tr>
						<tr><td class='info'>创建时间</td><td>${sysUser.createTime}</td></tr>
						<tr><td class='info'>更新时间</td><td>${sysUser.updateTime}</td></tr>
					</c:if>
					</tbody>
				</table>
			</div>
			
			<s:include value="/page/menu.jsp"></s:include>
		</div>
	</div>
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
