<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>阀门信息列表</title>
</head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
	
	<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet" media="all" />
	<link href="../css/bootstrap.css" type="text/css" rel="stylesheet"	/>
	<script src="https://code.jquery.com/jquery.min.js" type="text/javascript"></script>
	<script src="https://meetselva.github.io/fixed-table-rows-cols/js/sortable_table.js" type="text/javascript"></script>
	<script src="../js/fixed_table_rc.js" type="text/javascript"></script>
	<style>
		html, body {
			font-family: Arial,​​sans-serif;
			font-size: 14px;
			margin: 0;
			padding: 0;
			background-color: #f2f2f2;
		}
		
		div.container {
			padding: 5px 10px;
			width: 2330px;
			margin: 10px auto;
		}
		
		.ft_container table tr {
			background: url(../img/secai.png);
		}
	</style>
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 800,
			colModal : [ 
						{
						width : 80,
						align : 'center'
					}, {
						width : 100,
						align : 'center'
					},
					{
						width : 90,
						align : 'center'
					}, {
						width : 70,
						align : 'center'
					}, {
						width : 70,
						align : 'center'
					}, {
						width : 70,
						align : 'center'
					}, {
						width : 90,
						align : 'center'
					}, {
						width : 90,
						align : 'center'
					}, {
						width : 100,
						align : 'center'
					}, {
						width : 100,
						align : 'center'
					}, {
						width : 90,
						align : 'center'
					}, {
						width : 130,
						align : 'center'
					}
					, {
						width : 100,
						align : 'center'
					}, {
						width : 100,
						align : 'center'
					}, {
						width : 90,
						align : 'center'
					}, {
						width : 90,
						align : 'center'
					} ,{
						width : 90,
						align : 'center'
					},{
						width : 90,
						align : 'center'
					}, {
						width : 90,
						align : 'center'
					},{
						width : 90,
						align : 'center'
					} ,{
						width : 90,
						align : 'center'
					} ,{
						width : 90,
						align : 'center'
					}   
					
		],
			sort: true
		});
		
	});
	</script>
	
	
<body> 
   
<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead style="color: white;">
	<tr height="35px">
			<th>序号</th>
            <th>用户姓名</th>
            <th>小区名称</th>
            <th>楼栋号</th>
            <th>单元号</th>
            
            <th>户号</th>
            <th>阀门状态</th>
            <th>阀门开度</th>
            <th>室内温度</th>
            <th>管道温度</th> 
            
             <th>锁定标识</th>
            <th>更新时间</th>
            <th>用户卡号</th>
            <th>阀门地址</th>
            <th>传感器地址</th>
            
            <th>缴费状态</th>
            <th>用户面积</th>
            <th>区管ID</th>  
            <th>室外温度</th>         
            <th>用户类别</th>
            
            <th>用户分区</th>
	</tr>
	</thead>

<tbody id="fmInfo">
	<c:forEach  var="fn" items="${list}" varStatus="status">
	
	   <tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>	  
		<td>${fn.id}</td>	
		<td>${fn.yhName}</td>
		<td>${fn.xqName}</td>
		<td>${fn.buildNO}</td> 
		<td>${fn.cellNO}</td>
		
		<td>${fn.houseNO}</td>				
		<!-- 阀门状态 -->
		<td>${fn.fm.status}</td>		
		<!-- 阀门开度 -->
		<td>${fn.fm.famKd}</td>
		<!-- 室内温度 -->
		<td>${fn.fm.roomTemp}</td>
		<!-- 管道温度 -->
		<td>${fn.fm.valTemp}</td>
		
		<!-- 锁定标识 -->		
	 	<td>${fn.fm.lockSt}</td>
		<!-- 更新时间 -->
		<td><fmt:formatDate value="${fn.fm.recordTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<!-- 用户卡号 -->
		<td>${fn.idNum}</td> 
		<!--阀门地址 --> 
		<td>${fn.valAd}</td>
		<!--传感器地址 -->
		<td>${fn.WCAd}</td>
		
		
		<!--缴费状态 -->
		<td>${fn.sFJF}</td>
		
		<!-- 面积 -->
		<td>${fn.heatArea}</td>
		
		<!--区管id -->
		<td>${fn.fm.qgID}</td>
		<!--室外温度  -->
		<td>${fn.fm.tqyb }</td>
		
		<!--用户类别 -->
		<td>${fn.yhfl}</td>
		<!--用户分区 -->
		<td>${fn.YLFQ}</td>
		
		  
	 </tr>
	</c:forEach>
  </tbody>
   
  
</table>
</div>

</body>
</html>