
<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=urf-8">
        <title>调度任务列表</title>
        <style type="text/css">
        	table {
			    font-size: 12px;
			    border-collapse: collapse
			}
			
			button {
			    padding: 2px 2px 0px 2px;
			    border: #7EBF4F 1px solid;
			    FILTER:progid:DXImageTransform.Microsoft.gradient( GradientType = 0, startColorstr = #ffffff, endColorstr = #B3D997 );
			    CURSOR: pointer;
			}
        </style>
		<script type="text/javascript">
		function doCmd(action,triggerName,group,triggerState, expression){
			
			if(action == 'pause' && triggerState=='PAUSED'){
				alert("该Trigger己经暂停！");
				return;
			}
			
		    if(action == 'resume' && triggerState != 'PAUSED'){
				alert("该Trigger正在运行中！");
				return;
			}
			
			//客户端两次编码，服务端再解码，否测中文乱码 
			triggerName = encodeURIComponent(encodeURIComponent(triggerName));
			group = encodeURIComponent(encodeURIComponent(group));
            $.ajax({
                url: '${pageContext.request.contextPath}/JobProcessServlet?jobtype=200&action='+action+'&triggerName='+triggerName+'&group='+group,
                type: 'post',
                //dataType: 'xml',
               // timeout: 3000,
                error: function(){
                   alert("执行失败！");		
                },
                success: function(xml){
					if (xml == 0) {
						alert("执行成功！");
						window.location.reload();
					}else{
						alert("执行失败！");	
					}		   
                }
            });
		}
		</script>
    </head>
    <body>
    	<br><br>
        <table  border="1">
            <tr>
                <th nowrap>Trigger名称</th>
                <th nowrap>SCHED_NAME</th>
                <th nowrap>Trigger分组</th>
                <th nowrap>JobDetail类</th>
                <th nowrap>下次执行时间</th>
                <th nowrap>上次执行时间</th>
                <th nowrap>优先级</th>
                <th nowrap>Trigger状态</th>
                <th nowrap>Trigger类型</th>
                <th nowrap>开始时间</th>
                <th nowrap>结束时间</th>
                <th nowrap>持久化</th>
                <th nowrap title="IS_NONCONCURRENT">并行</th>
                <th nowrap>CRON_EXPRESSION</th>
                <th nowrap>动作命令</th>
            </tr>
            <c:forEach var="map" items="${list}">
                <tr>
                    <td nowrap>${map.display_name}</td>
                    <td nowrap>${map.SCHED_NAME}</td>
                    <td nowrap>${map.trigger_group}</td>
                    <td nowrap>${map.job_class_name}</td>
                    <td nowrap>${map.next_fire_time}</td>
                    <td nowrap>${map.prev_fire_time}</td>
                    <td nowrap>${map.priority}</td>
                    <td nowrap>${map.statu}</td>
                    <td nowrap>${map.trigger_type}</td>
                    <td nowrap>${map.start_time}</td>
                    <td nowrap>${map.end_time}</td>
                    <td nowrap>${map.IS_DURABLE}</td>
                    <td nowrap>${map.IS_NONCONCURRENT}</td>
                    <td nowrap><input style="background-color: lightblue" value="${map.cron_expression}"/></td>
                    <td nowrap>
                    	<!-- <input type="button" id="edit"   value="保存" onclick="doCmd('save','${map.trigger_name}','${map.trigger_group}','${map.trigger_state}','${map.trigger_state}')"> -->
                    	<input type="button" id="pause"  value="暂停" onclick="doCmd('pause','${map.trigger_name}','${map.trigger_group}','${map.trigger_state}')">
						<input type="button" id="resume" value="恢复" onclick="doCmd('resume','${map.trigger_name}','${map.trigger_group}','${map.trigger_state}')">
						<input type="button" id="remove" value="删除" onclick="doCmd('remove','${map.trigger_name}','${map.trigger_group}','${map.trigger_state}')">						
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
