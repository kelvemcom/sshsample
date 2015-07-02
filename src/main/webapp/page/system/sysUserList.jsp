<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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

	<title>用户列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
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
	
	<!-- Le styles
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
    -->
</head>
  
<body>

<!-- Preloader
<div id="preloader">
    <div id="status">
        <i class="fa fa-spinner fa-spin"></i>
    </div>
</div>
 -->
 
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
          <li><a href="index.html">系统管理</a></li>
          <li class="active">用户管理</li>
        </ol>
      </div>
    </div>
    
    <div class="contentpanel">
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
 	
		<form id="form" action="page/system/sysUser_sysUserList.action" method="get" class="form-horizontal">
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
					<k:domain type="select" name="queryVo.userTypeCode" value="${queryVo.userTypeCode}" cssClass="selectpicker show-tick form-control" 
						domainType="userTypeCode" emptyItem=""/>
				</div>
				<div class="input-group col-sm-4 col-lg-3 column">
					<span class="input-group-addon">状态标志</span>
					<!-- <input type="text" class="form-control" placeholder="" name="queryVo.statusCode" value="${queryVo.statusCode}"> -->
					<k:domain type="select" name="queryVo.statusCode" value="${queryVo.statusCode}" cssClass="selectpicker show-tick form-control aa"
						domainType="statusCode" emptyItem=""/>
				</div>
				<div class="input-group col-sm-4 col-lg-3 column">
					<span class="input-group-addon">测试用</span>
					<!-- <input type="text" class="form-control" placeholder="" name="queryVo.statusCode" value="${queryVo.statusCode}"> -->
                    <select id="bs3Select1" class="selectpicker show-tick form-control" data-live-search="false">
                        <option id="bb">cow</option>
                        <option>bull</option>
                        <option class="get-class" disabled>ox</option>
                        <optgroup label="test" data-subtext="another test" data-icon="icon-ok">
                            <option>ASD</option>
                            <option selected>Bla</option>
                            <option>Ble</option>
                        </optgroup>
                    </select>
				</div>
                <div class="input-group date form_datetime col-sm-4 col-lg-3 column" data-date="<s:property value='queryVo.createTime'/>" 
                		data-date-format="yyyy-mm-dd" data-link-field="dtp_input">
	                <span class="input-group-addon">创建时间</span>
                    <input class="form-control" size="16" type="text" name="queryVo.createTime" value="<s:property value='queryVo.createTime'/>" readonly>
                    <!--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>-->
					<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
					<input type="hidden" id="dtp_input" value=""/><br/>
                </div>
				
				<div class="input-group col-sm-4 col-lg-3 column pull-right">
					<button class="btn btn-info" type="button" onclick="submit_query(0, '');"><i class="glyphicon glyphicon-search"></i> 查询</button>&nbsp;
					<button class="btn btn-info" type="button" onclick="clear();"><i class="glyphicon glyphicon-remove-circle"></i> 清空</button>
				</div>
			</div>
		
			<br>
			
			<div id="data_table" class="row">
				<button class="btn btn-info" type="button" onclick="submit_form('add');" id="add"><i class="glyphicon glyphicon-plus-sign"></i> 增加</button>
				<button class="btn btn-info" type="button" onclick="submit_form('enable');" id="enable"><i class="glyphicon glyphicon-ok-sign"></i> 启用</button>
				<button class="btn btn-info" type="button" onclick="submit_form('disenable');" id="disenable"><i class="glyphicon glyphicon-remove-sign"></i> 停用</button>
				<br>
				<div class="table-responsive">
			
				<table id='list' class="table table-hover table-striped datatable" style="word-break:break-all;">
					<thead class='info'>
						<tr class='info'>
							<th><input type="checkbox" id="select_all" onclick="onchange_checkbox('select_all');"></th>
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
<!-- <script type="text/javascript" src="js/custom.js"></script> --> 

<script type="text/javascript" src="jquery/plugin/jquery.pagination.js"></script>
<script type="text/javascript" src="bootstrap/ext/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="bootstrap/ext/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="bootstrap/ext/bootstrap-select.js"></script>
    	
<script type="text/javascript">

	function submit_form(button_id) {
		//alert(button_id);
		if (button_id == 'add') {
			document.getElementById("form").action = "${ctx}/page/system/sysUser_sysUserAdd.action";
			document.getElementById("form").submit();
		} else if (button_id == 'enable') {
			document.getElementById("form").action = "${ctx}/page/system/sysUser_sysUserEnable.action";
			document.getElementById("form").submit();
		} else if (button_id == 'disenable') {
			document.getElementById("form").action = "${ctx}/page/system/sysUser_sysUserDisenable.action";
			document.getElementById("form").submit();
		}
    	return false;
	};
    
    var init = false;
    function submit_query(page_index, jq){

    	if(init == false){
    		init = true;
    		return false;
    	}
    	
    	//alert(page_index);
    	$("#pageNo").val(page_index);
    	document.getElementById("form").action = "${ctx}/page/system/sysUser_sysUserList.action";
    	document.getElementById("form").submit();
    	return false;
    }
    
    function clear_input(jquery_selector){
    	$(jquery_selector + " :input").val("");
    }
    
    function clear_select(jquery_selector){
    	$(jquery_selector + " select option:first").attr("selected", true);
    }
    
    function clear(){
    	clear_input("#form");
    	//clear_select("#form");
    }
    
    function onchange_checkbox(ckb_id){
    	var flag = document.getElementById(ckb_id).checked;
	    
	    var ckbs = document.all['selected'];
	    for(var i=0;i<ckbs.length;i++)
	    {
	    	ckbs[i].checked = flag; 
	    }
    }
    
	$(document).ready(function(){
	
		$('.form_datetime').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
	        showMeridian: 1
	    });
		
		$('.selectpicker').selectpicker({
	        style: 'btn-default',
	        size: 'auto',
	        title: null,
	        selectedTextFormat : 'values',
	        noneSelectedText : '',
	        noneResultsText : 'No results match',
	        countSelectedText: '{0} of {1} selected',
	        width: false,
	        container: false,
	        hideDisabled: false,
	        showSubtext: false,
	        showIcon: true,
	        showContent: true,
	        dropupAuto: true,
	        header: false,
	        liveSearch: false,
	        multipleSeparator: ', ',
	        iconBase: 'glyphicon',
	        tickIcon: 'glyphicon-ok'
        });
		

		$("#pagination").pagination(${pageResult.totalCount}, {
			items_per_page:${pageResult.pageSize},
			current_page:${pageResult.currentPage},
			num_display_entries:8,
			num_edge_entries:2,
			prev_text:"上一页",
			next_text:"下一页",
			
			callback:submit_query
		});
		//alert($("#pagination").html());
		$("#pagination").children().wrap("<li></li>");
		if (${pageResult.totalCount} <= ${pageResult.pageSize}) {
			$("#pagination").html("");
		}
 		//alert($("#pagination").html());

 		
 		/*
 		 * -------------------------------------------------------------------------------------------------------------------------
 		 */
 		
		//$("tbody tr:even").addClass("info");
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
		
	});		
</script>
</html>
