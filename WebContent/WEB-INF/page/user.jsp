<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>用户显示数据</title>
</head>

<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet"	media="all" />
<link href="../css/bootstrap.css" type="text/css" rel="stylesheet"	/>
<script src="../js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script	src="https://meetselva.github.io/fixed-table-rows-cols/js/sortable_table.js"
	type="text/javascript"></script>
<script src="../js/fixed_table_rc.js" type="text/javascript"></script>
<style>
html, body {
	font-family: Arial, ​​sans-serif;
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

.ft_container thead tr {
	background: url(../img/secai.png);
}
</style>
<script>
	$(function() {

		$('#fixed_hdr2').fxdHdrCol({
			fixedCols : 0,
			width : "100%",
			height : 800,
			colModal : [ 
				{
				width : 200,
				align : 'center'
			}, {
				width : 400,
				align : 'center'
			}
],
			sort : true
		});

	});
	</script>
<body>

	<div class="dwrapper">
		<table id="fixed_hdr2">
			<thead style="color: white;">
				<tr height="35px">
					<th>用户姓名</th>
					<th>密码</th>
				</tr>
			</thead>
			<tbody id="SearchId">
				<c:forEach var="list" items="${user}" varStatus="status">
					<tr
						<c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
						<td>${list.userName}</td>
						<td>${list.passWord}</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>