<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


  <!-- --------------------------------------------------------------------------------------------------------------------------------- -->
  
  <div class="rightpanel">
    <!-- Nav tabs -->
    <ul class="nav nav-tabs nav-justified">
        <li class="active"><a href="#rp-settings" data-toggle="tab"><i class="fa fa-gear"></i></a></li>
        <li><a href="#rp-alluser" data-toggle="tab"><i class="fa fa-users"></i></a></li>
    </ul>
        
    <!-- Tab panes -->
    <div class="tab-content">

        <div class="tab-pane active" id="rp-settings">
            <h5 class="sidebartitle">Settings</h5>
            <div class="form-group">
                <label class="col-xs-8 control-label">Show Offline Users</label>
                <div class="col-xs-4 control-label">
                    <div class="toggle toggle-success"></div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-8 control-label">Enable History</label>
                <div class="col-xs-4 control-label">
                    <div class="toggle toggle-success"></div>
                </div>
            </div>
        </div><!-- tab-pane -->
        
        <div class="tab-pane" id="rp-alluser">
            <h5 class="sidebartitle">Setting2</h5>
            <div class="form-group">
                <label class="col-xs-8 control-label">Show Offline Users</label>
                <div class="col-xs-4 control-label">
                    <div class="toggle toggle-success"></div>
                </div>
            </div>
        </div>
        
    </div><!-- tab-content -->
  </div><!-- rightpanel -->

