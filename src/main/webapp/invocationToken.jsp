<%@ page language="java" contentType="text/html; charset=UTF-8"
	isErrorPage="true" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	response.setStatus(200);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="shortcut icon" href="favicon.ico" />
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/css/style2.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.5.1.min.js"></script>
		<title>重复提交</title>
		
	</head>
	<body class="return_page">
		<!--CSS九宫格圆角代码开始-->
		<div class="ct">
			<span class="tl"></span><span class="tr"></span>
		</div>
		<div class="cm">
			<div class="formbox">
				<div class="content">
					<table align="center">
						<tr>
							<td class="msg_bug" height="35px">
							<span>重复提交，请不要点击过快！</span>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="cb">
			<span class="bl"></span><span class="br"></span>
		</div>
		<!--CSS九宫格圆角代码结束-->
	</body>
</html>