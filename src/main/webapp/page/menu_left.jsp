<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- --------------------------------------------------------------------------------------------------------------------------------- -->
  <div class="leftpanel">
    
    <div class="logopanel">
        <h1><span>SSHSample</span></h1>
    </div><!-- logopanel -->
    
    <div class="leftpanelinner"> 
      
      <h5 class="sidebartitle">Navigation</h5>
      <ul class="nav nav-pills nav-stacked nav-bracket" id="menu_root">
      
<!-- 
        <li><a href="index.html"><i class="fa fa-home"></i> <span>Dashboard</span></a></li>
        <li><a href="page/system/sysRole_sysUserRoleList.action"><span class="pull-right badge badge-success">2</span><i class="fa fa-envelope-o"></i> <span>Email</span></a></li>
        <li class="nav-parent nav-active active"><a href="#"><span class="pull-right"><strong>+&nbsp;</strong></span><i class="fa fa-edit"></i> <span>系统管理</span></a>
          <ul class="children" style="display: block;">
            <li class="active"><a href="${ctx}/page/system/sysUser_sysUserList.action"><i class="fa fa-caret-right"></i> 用户管理</a></li>
            <li><a href="form-layouts.html"><i class="fa fa-caret-right"></i> 角色管理</a></li>
            <li><a href="form-validation.html"><i class="fa fa-caret-right"></i> 权限管理</a></li>
          </ul>
        </li>
        <li class="nav-parent"><a href="#"><span class="pull-right"><strong>+&nbsp;</strong></span><i class="fa fa-suitcase"></i> <span>UI Elements</span></a>
          <ul class="children">
            <li><a href="buttons.html"><i class="fa fa-caret-right"></i> Buttons</a></li>
            <li><a href="icons.html"><i class="fa fa-caret-right"></i> Icons</a></li>
            <li><a href="typography.html"><i class="fa fa-caret-right"></i> Typography</a></li>
            <li><a href="alerts.html"><i class="fa fa-caret-right"></i> Alerts &amp; Notifications</a></li>
            <li><a href="tabs-accordions.html"><i class="fa fa-caret-right"></i> Tabs &amp; Accordions</a></li>
            <li><a href="sliders.html"><i class="fa fa-caret-right"></i> Sliders</a></li>
            <li><a href="graphs.html"><i class="fa fa-caret-right"></i> Graphs &amp; Charts</a></li>
            <li><a href="extras.html"><i class="fa fa-caret-right"></i> Extras</a></li>
          </ul>
        </li>
        <li><a href="${ctx}/tool/memory.jsp"><i class="fa fa-th-list"></i> <span>Memory</span></a></li>
        <li><a href="${ctx}/page/self-query.html?table=sys_user"><i class="fa fa-th-list"></i> <span>self-query</span></a></li>
 -->
 
<c:forEach  items="${sessionScope.json_menu_tree.sub_menu}" var="item">
  <c:choose>  
   	<c:when test="${fn:length(item.sub_menu)>0}">
   		<li style="white-space:nowrap;" id="${item.name}" class="nav-parent" ><a href="#"><span class="pull-right"><strong>+&nbsp;</strong></span><i class="fa ${item.image}"></i> <span>${item.name}</span></a>
   		  <ul class="children">
   		  <c:forEach  items="${item.sub_menu}" var="sub_item">
   			<li style="white-space:nowrap;" id="${sub_item.name}"><a href="${ctx}${sub_item.url}"><i class="fa fa-caret-right"></i> ${sub_item.name}</a></li>
   		  </c:forEach>
   		  </ul>
   		</li>
   	</c:when>  
   	<c:otherwise>
   		<li style="white-space:nowrap;" id="${item.name}"><a href="${ctx}${item.url}"><i class="fa ${item.image}"></i> <span>${item.name}</span></a></li>
   	</c:otherwise>  
  </c:choose>
</c:forEach>
     	
      </ul>


    </div><!-- leftpanelinner -->
    
  </div><!-- leftpanel -->


<script type="text/javascript" src="jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript">

// 取得请求的参数字符串
var queryString = '${pageContext.request.queryString}';

// 取得请求的URL，但不包括请求之参数字符串
var requestURL = '${pageContext.request.requestURL}';

// 服务的web application 的名称
var contextPath = '${pageContext.request.contextPath}';

//alert(JSON.stringify(${sessionScope.json_curr_menu}));

$("li").each(function(){

	if ($(this).attr("id") == ${sessionScope.json_curr_menu}.menu_level1) {
    	$(this).attr("class", "nav-parent nav-active active");
    	$(this).children("ul.children").attr("style", "display: block;");
	}
	if ($(this).attr("id") == ${sessionScope.json_curr_menu}.menu_level2) {
    	$(this).attr("class", "active");
	}
});
 
</script>

