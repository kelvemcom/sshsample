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
    
    <title>index.jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

    </style>
    <link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <script type="text/javascript" src="jquery/jquery-2.0.2.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="bootstrap/js/html5shiv.js"></script>
    <![endif]-->

	<script type="text/javascript">

	    function getJson() {
	        var currentTime= new Date().getTime();
	        
			$.ajax({
				type : "POST",
				url : "${ctx}/json_getJson.action",
				cache: false,
				
			}).done(function(data) {
				//alert("data.jsonData : " + data.jsonData);
				var json = eval(data.jsonData);
				//alert("json: " + json);
				alert(data.jsonData.msg);
				$('#alert_msg').text(json.msg);

				$('#myModal').modal({
					keyboard : false,
					show : true
				});
				
			}).fail(function() {
				alert("error");
			}).always(function() {
				//alert("complete");
			});
		}
	</script>
	
  </head>
  
  <body>
  
    <s:include value="page/navbar.jsp"></s:include> 
  	
  	<br>
  	
<div class="container-fluid">
	
	<a href="javascript:getJson()">getJson()</a>
	<div class="row-fluid">
		<div class="span9">
			<div id="tabs-940234" class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel-200997" data-toggle="tab">第一部分</a>
					</li>
					<li>
						<a href="#panel-814575" data-toggle="tab">第二部分</a>
					</li>
				</ul>
				<div class="tab-content">
					<div id="panel-200997" class="tab-pane active">
						<p>
							第一部分内容.
						</p>
					</div>
					<div id="panel-814575" class="tab-pane">
						<p>
							第二部分内容.
						</p>
					</div>
				</div>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>编号</th>
						<th>产品</th>
						<th>交付时间</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>TB - Monthly</td>
						<td>01/04/2012</td>
						<td>Default</td>
					</tr>
					<tr class="success">
						<td>1</td>
						<td>TB - Monthly</td>
						<td>01/04/2012</td>
						<td>Approved</td>
					</tr>
					<tr class="error">
						<td>2</td>
						<td>TB - Monthly</td>
						<td>02/04/2012</td>
						<td>Declined</td>
					</tr>
					<tr class="warning">
						<td>3</td>
						<td>TB - Monthly</td>
						<td>03/04/2012</td>
						<td>Pending</td>
					</tr>
					<tr class="info">
						<td>4</td>
						<td>TB - Monthly</td>
						<td>04/04/2012</td>
						<td>Call in to confirm</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<s:include value="page/menu.jsp"></s:include>
		 
	</div>
	<div class="row-fluid">
		<div class="span12">
		</div>
	</div>
	
	<!-- <a href="#myModal" role="button" class="btn" data-toggle="modal">查看演示案例</a> -->
	
	<!-- Modal -->
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">Info</h3>
	  </div> 
	  <div class="modal-body">
	    <p id="alert_msg"> Loading ... ...</p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
	  </div>
	</div>
</div>


	
  </body>
</html>
