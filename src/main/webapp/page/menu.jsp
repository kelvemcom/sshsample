<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

		<br>
		<div class="col-xs-12 col-md-3 column">
			<div id="panel-1" class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						 <a class="panel-title" href="#panel-element-1" data-toggle="collapse" data-parent="#panel-1">ActionResultType</a>
					</div>
					<div id="panel-element-1" class="panel-collapse in">
						<div class="panel-body">
							ctx = ${ctx}<br>
							<a href='${ctx}/anno.action'>anno.action</a><br>
							<a href='${ctx}/dispatcher.action'>dispatcher.action</a><br>
							<a href='${ctx}/redirect.action'>redirect.action</a><br>
							<a href='${ctx}/plainText.action'>plainText.action</a><br>
							<a href='${ctx}/redirectAction.action'>redirectAction.action</a><br>
							<a href='${ctx}/directAction.action'>directAction.action(NG)</a><br>
							<a href='${ctx}/duplicate.action'>duplicate.action</a><br>
							<a href='javascript:getJson();'>getJson()</a><br>
							
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						 <a class="panel-title collapsed" href="#panel-element-2" data-toggle="collapse" data-parent="#panel-1">SSH2Func</a>
					</div>
					<div id="panel-element-2" class="panel-collapse collapse">
						<div class="panel-body">
							<a href='${ctx}/page/user_listUser.action'>user_listUser.action</a><br>
							<a href='${ctx}/page/user_listUser1.action'>user_listUser1.action</a><br>
							<a href='${ctx}/page/self-query.html?table=v3_orders'>self_query_a1_c</a><br>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						 <a class="panel-title collapsed" href="#panel-element-3" data-toggle="collapse" data-parent="#panel-1">Queue</a>
					</div>
					<div id="panel-element-3" class="panel-collapse collapse">
						<div class="panel-body">
							<a href='${ctx}/page/test_sendMsg.action?msg=abc' >sample_queue</a><br>
							<a href='${ctx}/page/test_sendMsg2.action?msg=abc'>sample2_queue</a><br>
							<a href='${ctx}/page/test_sendMsg3.action?msg=abc'>sample3_queue</a><br>
							<a href='${ctx}/page/test_sendMsg4.action?msg=abc'>sample4_queue</a><br>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						 <a class="panel-title collapsed" href="#panel-element-4" data-toggle="collapse" data-parent="#panel-1">SysUser</a>
					</div>
					<div id="panel-element-4" class="panel-collapse collapse">
						<div class="panel-body">
							${ctx}<br>
							<a href='${ctx}/page/system/sysUser_sysUserList.action'>sys_user_list</a><br>
						</div>
					</div>
				</div>
			</div>
		</div>


