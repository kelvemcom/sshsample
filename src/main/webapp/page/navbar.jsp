<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
    
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
    </script>
    
    <div class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation">
     <div class="navbar-header">
       <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
         <span class="sr-only">Toggle navigation</span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
       </button>
       <a class="navbar-brand" href="#">SSH2Sample</a>
     </div>
     <div class="navbar-collapse collapse">
		<ul class="nav navbar-nav">
           <li class="active"><a href="#">Home</a></li>
           <li><a href="#about">About</a></li>
           <li><a href="#contact">Contact</a></li>
           <li class="dropdown">
				<a class="dropdown-toggle" href="#" data-toggle="dropdown">下拉菜单<strong class="caret"></strong></a>
				<ul class="dropdown-menu">
					<li><a href="http://getbootstrap.com/">bootstrap</a></li>
					<li><a href="http://www.layoutit.com/build">layoutit</a></li>
					<li><a href="#">其他</a></li>
					<li class="divider"></li>
					<li class="nav-header">标签</li>
					<li><a href="#">链接1</a></li>
					<li><a href="#">链接2</a></li>
				</ul>
		   </li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#">右边链接</a></li>
			<li class="divider-vertical"></li>
			<li class="dropdown">
				<a class="dropdown-toggle" href="#" data-toggle="dropdown">下拉菜单<strong class="caret"></strong></a>
				<ul class="dropdown-menu">
					<li><a href="#">下拉导航1</a></li>
					<li><a href="#">下拉导航2</a></li>
					<li><a href="#">其他</a></li>
				</ul>
			</li>
		</ul>
     </div><!--/.nav-collapse -->
   </div>
   
   <br>
	${breadcrumb}
	
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
