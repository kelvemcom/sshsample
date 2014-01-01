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

	<title>用户信息列表</title>
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

	<!--link rel="stylesheet/less" href="less/bootstrap.less" type="text/css" /-->
	<!--link rel="stylesheet/less" href="less/responsive.less" type="text/css" /-->
	<!--script src="js/less-1.3.3.min.js"></script-->
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	
	<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="bootstrap/ext/dataTables.bootstra.css" rel="stylesheet">
	
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="bootstrap/js/html5shiv.js"></script>
	<![endif]-->

	<!-- Fav and touch icons -->
	<link rel="apple-touch-icon-precomposed" sizes="144x144" href="bootstrap/img/apple-touch-icon-144-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="bootstrap/img/apple-touch-icon-114-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72" href="bootstrap/img/apple-touch-icon-72-precomposed.png">
	<link rel="apple-touch-icon-precomposed" href="bootstrap/img/apple-touch-icon-57-precomposed.png">
	<link rel="shortcut icon" href="bootstrap/img/favicon.png">
    
	<script type="text/javascript" src="jquery/jquery-2.0.2.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="jquery/plugin/jquery.pagination.js"></script>
   
</head>
  
<body>
  
    <s:include value="navbar.jsp"></s:include> 
	<br>
	<p>success error warning info</p>
	<form id="query" action="/SSH2Sample/page/user_listUser.action" method="get">
		<button class="btn btn-info" type="button" onclick="submit();">Large button</button>
	</form>
	
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-8 column">
				<table id='list' class="table table-bordered table-hover table-striped datatable">
					<thead class='info'>
						<tr class='info'>
							<th>ID</th>
							<th>姓名</th>
							<th>创建时间</th>
							<th>信息ID</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty pageResult.results}">
							<c:forEach items="${pageResult.results}" var="row" varStatus="sta">
						<tr id="id_${row.userId}">
							<td>${row.userId}</td>
							<td>${row.userName}</td>
							<td>${row.lastLoginTime}</td>
							<td>${row.userInfoId}</td>
							<td>
								<a href="${ctx}/page/user_delUser.action?user.userId=${row.userId}">[删除]</a>
								<a href="${ctx}/page/user_listUser.action?user.userId=${row.userId}">[修改]</a>
								<a href="${ctx}/page/user_listUser.action?user.userId=${row.userId}">[增加]</a>
							</td>
							
						</tr>
							</c:forEach>
						</c:if>
						
						<!-- 
						<tr id="id_edit" class="error">
							<td><input type="text" placeholder="Type something…"></td>
							<td><input type="text" placeholder="Type something…"></td>
							<td><input type="text" placeholder="Type something…"></td>
							<td><input type="text" placeholder="Type something…"></td>
						</tr>
						 -->
					</tbody>
				</table>
			</div>
			
			<s:include value="menu.jsp"></s:include>
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
		edit_html = edit_html + '		<a href="${ctx}/page/user_listUser.action?userId=${row.userId}">[保存]</a>';
		edit_html = edit_html + '		<a href="${ctx}/page/user_listUser.action?userId=${row.userId}">[增加]</a>';
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
