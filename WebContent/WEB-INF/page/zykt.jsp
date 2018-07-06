<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet"
	media="all" />
<script src="https://code.jquery.com/jquery.min.js"
	type="text/javascript"></script>
<script
	src="https://meetselva.github.io/fixed-table-rows-cols/js/sortable_table.js"
	type="text/javascript"></script>
<script src="../js/fixed_table_rc.js" type="text/javascript"></script>
<style>
html, body {
	font-family: Arial, ​​sans-serif;
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
<!-- Bootstrap -->
<link href="../css/bootstrap.css" rel="stylesheet">
<script src="../js/bootstrap.js"></script>
<script type="text/javascript"
	src="../js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-datetimepicker.min.css">
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function kFm() {
		var ckbs = $("#fmInfo input[type=checkbox]:checked");
		var apiContentStr;
		if (ckbs.length == 0) {
			alert("请选择要打开的风盘");
			return false;
		}
		var ids = [];
		$.each(ckbs, function(index, data) {
			ids[index] = $(data).val();
		})
		var apiContentStr = "";
		for (var i = 0; i < ids.length; i++) {
			apiContentStr = ids[i];
			$.ajax({
				url : "/zy-AirCond/zyktCon/CxState.action",
				async : false,
				dataType : "json",
				data : {
					"ids" : apiContentStr,
				},
				success : function(data) {
					msg = data.js
					if (msg == "3") {
						alert("不在线!");
					}
					if (msg == "0") {
						alert("指令发送成功!");
					}
					location.reload()

				}

			});
		}
	}

	function kz(obj) {
		var qd = obj;
		var ckbs = $("#fmInfo input[type=checkbox]:checked");
		var apiContentStr;
		if (ckbs.length == 0) {
			alert("请选择要关闭的风盘");
			return false;
		}
		var ids = [];
		$.each(ckbs, function(index, data) {
			ids[index] = $(data).val();
		})
		var apiContentStr = "";
		for (var i = 0; i < ids.length; i++) {
			apiContentStr = ids[i];
			$.ajax({
				url : "/zy-AirCond/zyktCon/CxKz.action",
				async : false,
				dataType : "json",
				data : {
					"ids" : apiContentStr,
					"qd" : qd,
				},
				success : function(data) {
					msg = data.js
					if (msg == "3") {
						alert("不在线!");
					}
					if (msg == "0") {
						alert("指令发送成功!");
					}
					location.reload()

				}

			});
		}
	}

	function jf(obj) {
		var jd = obj;
		var ckbs = $("#fmInfo input[type=checkbox]:checked");
		var apiContentStr;
		if (ckbs.length == 0) {
			alert("请选择要计费的风盘");
			return false;
		}
		var ids = [];
		$.each(ckbs, function(index, data) {
			ids[index] = $(data).val();
		})
		var apiContentStr = "";
		for (var i = 0; i < ids.length; i++) {
			apiContentStr = ids[i];
			$.ajax({
				url : "/zy-AirCond/zyktCon/CxJd.action",
				async : false,
				dataType : "json",
				data : {
					"ids" : apiContentStr,
					"jd" : jd,
				},
				success : function(data) {
					msg = data.js
					if (msg == "3") {
						alert("集中器不在线!");
					}
					if (msg == "0") {
						alert("成功!");
					}
					location.reload()

				}

			});
		}
	}

	function Zx() {
		var kg = $('#kg').val();
		var jf = $('#jf').val();
		var wd = $('#wd').val();
		var ckbs = $("#fmInfo input[type=checkbox]:checked");
		var apiContentStr;
		if (ckbs.length == 0) {
			alert("请选择要计费的风盘");
			return false;
		}
		if (wd != "") {
			var re = /^\+?[1-9][0-9]*$/;
			/*   if (!re.test(wd))
			    {
			        alert("请输入正确的数值,只允许输入整数!");
			        document.getElementById("wd").value = "";
			        return false;
			     }
			  
			  var re = ^\+?[1-9][0-9]*$; */
			if (wd<30 && wd>15 && !re.test(wd)) {
					alert("请输入正确的数值,只允许输入整数!");
					document.getElementById("wd").value = "";
					return false;

			}
		}
		var ids = [];
		$.each(ckbs, function(index, data) {
			ids[index] = $(data).val();
		})
		var apiContentStr = "";
		for (var i = 0; i < ids.length; i++) {
			apiContentStr = ids[i];
			$.ajax({
				url : "/zy-AirCond/zyktCon/CxZx.action",
				async : false,
				dataType : "json",
				data : {
					"ids" : apiContentStr,
					"kg" : kg,
					"jf" : jf,
				},
				success : function(data) {
					msg = data.js
					if (msg == "3") {
						alert("不在线!");
					}
					if (msg == "0") {
						alert("指令发送成功!");
					}
					location.reload()

				}

			});
		}
	}
</script>
<script type="text/javascript">
	function checkRate() {
		var re = /^(?:0|[1-9][0-9]?|10000000)$/;
		var nubmer = document.getElementById("wd").value;

		if (!re.test(nubmer)) {
			alert("请输入正确的数值,只允许输入整数!");
			document.getElementById("wd").value = "";
			return false;
		}
	}
</script>
<script>
	$(function() {

		$('#fixed_hdr2').fxdHdrCol({
			fixedCols : 0,
			width : "100%",
			height : 500,
			colModal : [ {
				width : 60,
				align : 'center'
			}, {
				width : 120,
				align : 'center'
			}, {
				width : 80,
				align : 'center'
			}, {
				width : 90,
				align : 'center'
			}, {
				width : 90,
				align : 'center'
			}, {
				width : 90,
				align : 'center'
			}, {
				width : 90,
				align : 'center'
			}, {
				width : 90,
				align : 'center'
			}, {
				width : 90,
				align : 'center'
			}, {
				width : 90,
				align : 'center'
			}, {
				width : 90,
				align : 'center'
			}, {
				width : 90,
				align : 'center'
			}, {
				width : 90,
				align : 'center'
			},  {
				width : 110,
				align : 'center'
			},{
				width : 140,
				align : 'center'
			}, ],
			sort : true
		});

	});
</script>
<body>
	<button type="button" class="btn btn-success" onclick="kFm()">查询状态</button>
	<!-- <button type="button" class="btn btn-success" onclick="kz(01)">强开</button>
	<button type="button" class="btn btn-success" onclick="kz(00)">强关</button>
	<button type="button" class="btn btn-success" onclick="jf(00)">允许计费</button>
	<button type="button" class="btn btn-success" onclick="jf(01)">禁止计费</button> -->
	选择开关：
	<select id="kg">
		<option value="02">请选择是否开关</option>
		<option value="01">自动运行</option>
		<option value="00">强制关闭</option>
	</select> 选择是否计费：
	<select id="jf">
		<option value="02">请选择是否计费</option>
		<option value="01">允许计费</option>
		<option value="00">禁止计费</option>
	</select>
	<!--  远程设定温度：
	<input type="text" id="wd"> -->
	<button type="button" class="btn btn-success" onclick="Zx()">发送</button>
	<div class="dwrapper">
		<table id="fixed_hdr2">
			<thead>
				<tr>
					<th></th>
					<th>风盘编号</th>
					<th>用户姓名</th>
					<th>已用当量</th>
					<th>已用金额</th>
					<th>风盘状态</th>
					<th>风盘档位</th>
					<th>高档时间</th>
					<th>低档时间</th>
					<th>中档时间</th>
					<th>是否计费</th>
					<th>设定温度</th>
					<th>室内温度</th>
					<th>远程状态</th>
					<th>采集时间</th>
				</tr>
			</thead>


			<tbody id="fmInfo">
				<c:forEach var="zy" items="${findZykt}" varStatus="status">
					<tr>
						<td><input type="checkbox" value="${zy.fpid}" /></td>
						<td>${zy.fpid}</td>
						<td>${zy.yh.yhName}</td>
						<td>${zy.yydl}</td>
						<td></td>
						<c:if test="${zy.status==00}">
							<td>制冷</td>
						</c:if>
						<c:if test="${zy.status==01}">
							<td>制热</td>
						</c:if>
						<c:if test="${zy.dw==00}">
							<td>停止</td>
						</c:if>
						<c:if test="${zy.dw==01}">
							<td>低档</td>
						</c:if>
						<c:if test="${zy.dw==02}">
							<td>中档</td>
						</c:if>
						<c:if test="${zy.dw==03}">
							<td>高档</td>
						</c:if>
						<td>${zy.gdg}</td>
						<td>${zy.gdd}</td>
						<td>${zy.zdS}</td>
						<c:if test="${zy.jf==00}">
							<td>禁止计费</td>
						</c:if>
						<c:if test="${zy.jf==01}">
							<td>允许计费</td>
						</c:if>
						<td>${zy.sdw}</td>
						<td>${zy.sw}</td>
						<td>${zy.state}</td>
						<td><fmt:formatDate value="${zy.date}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>