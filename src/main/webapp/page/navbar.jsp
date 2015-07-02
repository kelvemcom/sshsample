<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    
	<script-un type="text/javascript">
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
	</script-un>
  
     <div class="headerbar">
      
      <a class="menutoggle"><i class="fa fa-bars"></i></a>
      
      <div class="header-right">
        <ul class="headermenu">
          <li>
            <div class="btn-group">
              <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                ${sessionScope.login_user.sysUserName}                
                <span class="caret"></span>
              </button>
              <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                <li><a href="profile.html"><i class="glyphicon glyphicon-user"></i> 我的信息</a></li>
                <li><a href="#"><i class="glyphicon glyphicon-cog"></i> 设置</a></li>
                <li><a href="#"><i class="glyphicon glyphicon-question-sign"></i> 帮助</a></li>
                <li><a href="${ctx}/j_spring_security_logout"><i class="glyphicon glyphicon-log-out"></i> 登出</a></li>
              </ul>
            </div>
          </li>
          <li>
            <button id="chatview" class="btn btn-default tp-icon chat-icon">
                <i class="glyphicon glyphicon-comment"></i>
            </button>
          </li>
        </ul>
      </div><!-- header-right -->
    </div><!-- headerbar -->
	
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
    
