<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>用户缴费</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet" media="all" />
	<script src="https://code.jquery.com/jquery.min.js" type="text/javascript"></script>
	<script src="https://meetselva.github.io/fixed-table-rows-cols/js/sortable_table.js" type="text/javascript"></script>
	<script src="../js/fixed_table_rc.js" type="text/javascript"></script>
	<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
	<style>
		html, body {
			font-family: Arial,​​sans-serif;
			font-size: 13px;
			margin: 0;
			padding: 0;
			background-color: #f2f2f2;
		}
		
		div.container {
			padding: 5px 10px;
			width: 2330px;
			margin: 10px auto;
		}
		
		.ft_container table tr th {
			background: url(../images/secai.jpg);
		}
	</style>
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 700,
			colModal: [
			{ width: 50, align: 'center' },
			{ width: 130, align: 'center' },
			{ width: 120, align: 'center' },
			{ width: 65, align: 'center' },
			{ width: 65, align: 'center' },
			
			{ width: 55, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 150, align: 'center' },
			{ width: 150, align: 'center' }
			
			],
			sort: true
		});
		
	});
	</script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js">
</script>
<script src="../js/main.js"></script>
 <script type="text/javascript">
	/*页面加载就开始执行js*/
	$(document).ready(//根据小区获取  楼栋号
			function() {
				$("#xqmId").change(
						function() {
							$.get("findYhldhbyxqm.action?xqm="
									+ $("#xqmId").val(), function(data) {
								var dd = data;
								var d = dd.xqlist;
							 	$("#ldhId option:gt(0)").remove();
								$("#dyhId option:gt(0)").remove();
								for (var i = 0; i < d.length; i++) {
									var ldh = d[i].ldh;
									var opt = "<option value='"+ldh+"'>"
											+ ldh + "</option>"
									$("#ldhId").append(opt);
								}
							});
						});

				$("#ldhId").change(//根据小区楼栋号获取  单元号
						function() {
							$.get("findYhdyhByBuild.action?ldh="
									+ $("#ldhId").val() + "&xqm="
									+ $("#xqmId").val(), function(data) {
								var dd = data;
								var d = dd.dyhList;
								$("#dyhId option:gt(0)").remove();
								for (var i = 0; i < d.length; i++) {
									var dyh = d[i].dyh;
									var opt = "<option value='"+dyh+"'>"
											+ dyh + "</option>"
									$("#dyhId").append(opt);
								}
							});
						});
			});
</script>
<script type="text/javascript">
function searchInfo() {
	var xqm = $('#xqmId').val();
	var ldh = $('#ldhId').val();
	var dyh = $('#dyhId').val();
	var hh = $('#hhId').val();
	var time1 = $('#time1').val();
	var time2 = $('#time2').val();
	var type=$('#type').val();
	var html = "";
	$.ajax({
		url : "SearchHistory.action",
		async : false,
		dataType : "json",
		data : {
			"xqm" : xqm,
			"ldh" : ldh,
			"dyh" : dyh,
			"hh" : hh,
			"time1" : time1,
			"time2" : time2,
			"type":type,
		},
		success : function(data) {
			$("#users").empty();
			var d = data.jfs;
			for (var i = 0; i < d.length; i++) {
				var id=d[i].id;
				var yhxm = d[i].yhMessage.yhxm;
				var xqm = d[i].yhMessage.xqm;
				var ldh = d[i].yhMessage.ldh;
				var dyh = d[i].yhMessage.dyh;
				var hh = d[i].yhMessage.hh;
				var yzbh = d[i].yzbh;
				var lxdh = d[i].yhMessage.lxdh;
				var jfje = d[i].jfje;
				var hjje = d[i].hjje;
				var userName = d[i].userName;
				var time = FormatDate(d[i].time);
				var type=d[i].type;
				if(type==0){
					type="包月";
				}else if(type==1){
					type="按量";
				}else if(type==3){
					type="包季"
				}else if(type==4){
					type="包年"
				}else if(type==undefined){
					type="";
				}else{
					type="";
				}
				var startTime=d[i].startTime;
				var endTime=d[i].endTime;
				if(startTime==undefined){
					startTime="";
				}
				if(endTime==undefined){
					endTime="";
				}
				html += "<tr>";
				html+="<td class='text-center'><input type='checkbox'  value='"+id+"'/></td>";
				html += "<td class='text-center'>" + yhxm + "</td>";
				html += "<td class='text-center'>" + xqm + "</td>";
				html += "<td class='text-center'>" + ldh + "</td>";
				html += "<td class='text-center'>" + dyh + "</td>";
				html += "<td class='text-center'>" + hh+ "</td>";
				html += "<td class='text-center'>" + yzbh + "</td>";
				html += "<td class='text-center'>" + lxdh + "</td>";
				html += "<td class='text-center'>" + jfje + "</td>";
				html += "<td class='text-center'>" + hjje + "</td>";
				html += "<td class='text-center'>" + userName + "</td>";
				html += "<td class='text-center'>" + type+ "</td>";
				html += "<td class='text-center'>" + startTime+ "</td>";
				html += "<td class='text-center'>" + endTime+ "</td>";
				html += "<td class='text-center'>" + time + "</td>";
				html += "</tr>";
			}
			$("#users").append(html);
		}

	})
}
function FormatDate(strTime) {
	var date = new Date(strTime);
	return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
			+ date.getDate() + " " + date.getHours() + ":"
			+ date.getMinutes();
}
</script>

<script type="text/javascript">
	//导出
	function doExportExcel() {
		var type=$('#type').val();
		var xqm = $('#xqmId').val();
		var ldh = $('#ldhId').val();
		var dyh = $('#dyhId').val();
		var hh = $('#hhId').val();
		var time1 = $('#time1').val();
		var time2 = $('#time2').val();
		window.open("JfHistoryExportExcel.action?xqm=" + xqm + "&ldh="
				+ ldh + "&dyh=" + dyh + "&hh=" + hh
				+"&time1="+ time1+"&time2="+ time2+"&type="+type);
	}
</script>
</head>
<body>
<div class="panel panel-success">
 <div class="panel-heading">业主缴费信息</div>
 <div class="panel-body">
 <div id="top">
 <label for="xqmId">选择小区</label> 
 			<select id="xqmId" name="xqm">
					<option value="--选择小区名称--">--选择小区名称--</option>
					<c:forEach items="${XqNameList}" var="list">
						<option>${list.xqm}</option>
					</c:forEach>
			</select> &nbsp;&nbsp;&nbsp; 
			
		<label for="ldhId">楼栋号</label>
			<select   name="ldh" id="ldhId">
				<option value=0>--选择楼栋号--</option>
			</select> 
			&nbsp;&nbsp;&nbsp; 
			
		<label for="dyhId">单元号</label>
			<select  name="dyh" id="dyhId">
				<option value=0>--选择单元号--</option>
			</select> 
		&nbsp;&nbsp;&nbsp;
		
		户号：<input type="text" name="hh" id="hhId" value="${hh}" size=10px/> 
		 &nbsp;&nbsp;&nbsp; 
				缴费类型<select id="type">
				<option value="2">-选择缴费类型-</option>
				 <option value="1">按量</option>
				 <option value="0">包月</option>
				 <option value="3">包季</option>
				 <option value="4">包年</option>
				   </select>	
		<label for="readMTime">选择时间:</label>
		<input type="text" id="time1" name="time1" class="Wdate" onfocus="WdatePicker();" />
			- -<input type="text" id="time2"  name="time2"  class="Wdate" onfocus="WdatePicker();" />	
		<input type="button" onclick="searchInfo()" value="搜索" class="btn btn-success" style="background: url(../img/secai.png);"/>
	 <input type="button" value="导出" class="btn btn-success" onclick="doExportExcel()" style="background: url(../img/secai.png);"/>
         &nbsp;&nbsp;&nbsp;
	<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr height="35px" style="background: url(../img/secai.png);">
						<th></th>
						<th>用户名</th>
						<th>小区名称</th>
						<th>楼栋号</th>
						<th>单元号</th>
						<th>户号</th>
						<th>业主编号</th>
						<th>联系方式</th>
						<th>缴费金额</th>
						<th>合计金额</th>
						<th>缴费人</th>
						<th>缴费类型</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>缴费时间</th>
	           </tr>
				</thead>
				<tbody id="users">
					<c:forEach  var="jf" items="${jf}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
									<td><input type="checkbox" value="${jf.id}" /></td>
                                    <td>${jf.yhMessage.yhxm}</td>
                                    <td>${jf.yhMessage.xqm}</td>
                                    <td>${jf.yhMessage.ldh}</td>
                                    <td>${jf.yhMessage.dyh}</td>
                                    <td>${jf.yhMessage.hh}</td>
                                    <td>${jf.yzbh}</td>
                                    <td>${jf.yhMessage.lxdh}</td>
                                    <td>${jf.jfje}</td>
                                    <td>${jf.hjje}</td>
                                    <td>${jf.userName}</td>
                                    <c:choose>
									<c:when test="${jf.type==0}">
									  <td>包月</td>
									</c:when>
									<c:when test="${jf.type==1}">
									<td>按量</td>
									</c:when>
									<c:when test="${jf.type==3}">
									<td>包季</td>
									</c:when>
									<c:when test="${jf.type==4}">
									<td>包年</td>
									</c:when>
									<c:otherwise>
									<td></td>
									</c:otherwise>
									</c:choose>
									<td>${jf.startTime}</td>
									<td>${jf.endTime}</td>
									<td>${jf.time}</td>
					</c:forEach>
				</tbody>
			</table>
			</div>
</div>
</div>
</div>
</body>
</html>