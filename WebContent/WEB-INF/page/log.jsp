<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
	
	<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet" media="all" />
	<script src="https://code.jquery.com/jquery.min.js" type="text/javascript"></script>
	<script src="https://meetselva.github.io/fixed-table-rows-cols/js/sortable_table.js" type="text/javascript"></script>
	<script src="../js/fixed_table_rc.js" type="text/javascript"></script>
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
	<script type="text/javascript">
	
	function searchInfo(){
		var czId = $('#czId').val();
		window.location="Search.action?cz="+czId
		
	}
	</script>
	<script type="text/javascript">
	</script>
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 800,
			colModal: [
			{ width: 130, align: 'center' },
			{ width: 560, align: 'center' },
			{ width: 200, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js">
</script>
</head>
<body>
<div class="panel panel-success" style="width: 99%; height: 100%; position: absolute; overflow: auto;">
 <div class="panel-heading">日志管理</div>

<div class="dwrapper">
 操作内容模糊查询：<input type="text" id="czId" value="${cz}">
  <input type="button" onclick="searchInfo()" value="搜索" class="btn btn-success" style="background: url(../img/secai.png);"/> 
	<table id="fixed_hdr2">
	<thead>
	<tr style="background: url(../img/secai.png);">
						<th>操作人</th>
						<th>操作内容</th>
						<th>操作时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach  var="log" items="${log}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
                                     <td>${log.czr}</td>
									<td>${log.cz}</td>
									<td><fmt:formatDate value="${log.czsj}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                </tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
</body>
</html>