<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    
	<script type="text/javascript">
    	$(document).ready(function(){
    		var msg = "${showMsg}";
    		if( msg != ''){
	   			if ($('#msgShow').html() == undefined) {
	   				alert(msg);
	   			} else {
		   			$('#msgShow').text(msg);
					$('#msgDiv').modal({keyboard : false,show : true});
				}
			}
		});

		var json_menu_tree=<%=session.getAttribute("json_menu_tree")%>;
		var json_curr_menu=<%=session.getAttribute("json_curr_menu")%>;
		
		$(document).ready(function(){

			var first_level1_name;
			var size = json_menu_tree.sub_menu.length;
			for (var index=size-1; index>=0; index--) {
				name = json_menu_tree.sub_menu[index].name;
				url = json_menu_tree.sub_menu[index].url;
				if (url == null) {
					url = 'javascript:menu_level1_click("' + name + '");';
				}
				$("#menu_level1").prepend("<li menu_level='1' id='menu_level1_" + name + "'><a href='" + url + "'>" + name + "</a></li>");
			}
			//alert($("#menu_level1").html());
			if (json_curr_menu.menu_level1 == null) {
				//alert($("[menu_level='1']:first").text());
				menu_level1_click($("[menu_level='1']:first").text());
			} else {
				//alert(json_curr_menu.menu_level1);
				menu_level1_click(json_curr_menu.menu_level1);
				menu_level2_click(json_curr_menu.menu_level2);
				menu_level3_click(json_curr_menu.menu_level3);
			}

		});

		function menu_level1_click(menu_level1_name) {
			//alert(menu_level1_name);
			$("[menu_level='1']").removeClass('active');
			$("#menu_level1_" + menu_level1_name).addClass('active');

			var json_menu_level2;
			for ( var n1 in json_menu_tree.sub_menu) {
				if (menu_level1_name == json_menu_tree.sub_menu[n1].name) {
					json_menu_level2 = json_menu_tree.sub_menu[n1];
				}
			}
			if (json_menu_level2 == null) {
				return;
			}

			var html_menu_level2 = '';

			for ( var n2 in json_menu_level2.sub_menu) {

				name = json_menu_level2.sub_menu[n2].name;
				url = json_menu_level2.sub_menu[n2].url;
				json_menu_level3 = json_menu_level2.sub_menu[n2];

				html_menu_level2 += '			<div class="panel panel-default">\r\n';
				html_menu_level2 += '				<div class="panel-heading">\r\n';
				html_menu_level2 += '					 <a class="panel-title" href="#menu_level2_' + name + '" id="title_level2_' + name 
								+ '" style="color: #4488cc" data-toggle="collapse" data-parent="#menu_level2">' + name + '</a>\r\n';
				html_menu_level2 += '				</div>\r\n';

				html_menu_level2 += '			<div menu_level="2" id="menu_level2_' + name + '" class="panel-collapse in">\r\n';
				html_menu_level2 += '				<div class="panel-body">\r\n';
				for (n3 in json_menu_level3.sub_menu) {
					sub_name = json_menu_level3.sub_menu[n3].name;
					sub_url = json_menu_level3.sub_menu[n3].url;
					html_menu_level2 += '					<a menu_level="3" id="menu_level3_' + sub_name + '" style="color: black" href="${ctx}' + sub_url + '">' + sub_name + '</a><br>\r\n';
				}
				html_menu_level2 += '				</div>\r\n';
				html_menu_level2 += '			</div>\r\n';
				html_menu_level2 += '		</div>\r\n';
			}
			$("#menu_level2").html(html_menu_level2);

			//alert($("[menu_level='2']:first").attr("id"));
			menu_level2_click($("[menu_level='2']:first").attr("id").substring(12));

		};

		function menu_level2_click(menu_level2_name) {
			//alert(menu_level2_name);
			$("[menu_level='2']").removeClass();
			$("[menu_level='2']").addClass('panel-collapse collapse');
			$("#menu_level2_" + menu_level2_name).addClass('panel-collapse in');
			$("#title_level2_" + menu_level2_name).css('color', '#4488cc');
			//alert($("#menu_level2_" + menu_level2_name).text());
		};

		function menu_level3_click(menu_level3_name) {
			//alert(menu_level3_name);
			$(("#menu_level3_" + menu_level3_name).replace(".", "\\.")).css('color', '#4488cc');
		};
		
	</script>
  
    <header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner">
     <div class="navbar-header">
       <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
         <span class="sr-only">Toggle navigation</span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
       </button>
       <a class="navbar-brand" href="#">SSH2Sample</a>
     </div>
     <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
		<ul class="nav navbar-nav" id="menu_level1">
			<!-- 
			<li class="active"><a href="#">Home</a></li>
			<li><a href="#about">About</a></li>
			<li><a href="#contact">Contact</a></li>
            -->
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<!-- BEGIN NOTIFICATION DROPDOWN -->	
			<li class="dropdown">
				<a class="dropdown-toggle" href="#"  data-toggle="dropdown"><i class="glyphicon glyphicon-info-sign"></i><span class="badge"> 6</span></a>
				<ul class="dropdown-menu extended notification">
					<li><p>You have 14 new notifications</p></li>
					<li><a href="#"><span class="label label-success"><i class="glyphicon glyphicon-plus"></i></span>New user registered.<span class="time">Just now</span></a></li>
					<li><a href="#"><span class="label label-important"><i class="glyphicon glyphicon-remove-sign"></i></span>Server #12 overloaded.<span class="time">15 mins</span></a></li>
					<li class="external"><a href="#">See all notifications <i class="m-icon-swapright"></i></a></li>
				</ul>
			</li>
			<!-- END NOTIFICATION DROPDOWN -->
			<li class="dropdown">
				<a class="dropdown-toggle" href="#" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i> UserName<strong class="caret"></strong></a>
				<ul class="dropdown-menu">
					<li><a href="#"><i class="icon-user"></i> 我的信息</a></li>
					<li><a href="#"><i class="icon-tasks"></i> 我的任务</a></li>
					<li class="divider"></li>
					<li><a href="#"><i class="icon-key"></i> 修改密码</a></li>
					<li><a href="#">退出</a></li>
				</ul>
			</li>
			<li class="divider-vertical"></li>
			<li><a href="#"><i class="glyphicon glyphicon-off"></i> 退出</a></li>
		</ul>
     </nav><!--/.nav-collapse -->
   </header>
	
	<!-- <a href="#showMsg" role="button" class="btn" data-toggle="modal">查看演示案例</a> -->
	
	<!-- Modal -->
	<div id="msgDiv" class="modal fade" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" data-dismiss="modal" class="close">×</button>
					<div class="modal-title h3">Info</div>
				</div>
				<div class="modal-body">
					<p id="msgShow">Loading ... ...</p>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" data-dismiss="modal">关闭</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
