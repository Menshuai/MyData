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

<title>用户 密码</title>
</head>

<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet"	media="all" />
<link href="../css/bootstrap.css" type="text/css" rel="stylesheet"	/>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
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
				width : 60,
				align : 'center'
			}, 
			{
				width : 200,
				align : 'center'
			},{
				width : 400,
				align : 'center'
			}
],
			sort : true
		});

	});
	</script>
	<script type="text/javascript">
	// 删除
	function openDeletePage(){
			var ckbs=$("#SearchId input[type=checkbox]:checked");
			  if(ckbs.length==0){
				alert("请选择要删除的用户");
				return false;
			}else if(ckbs.length>1){
				 alert('对不起一次只能删除一个');
				return false;
			}else{
			var id=[];
			$.each(ckbs,function(index,data){
				id[index]=$(data).val();
			});
			var names=ckbs.parent().next();
			var deleteUserNames=[];
			$("#deleteUser .modal-body .col-sm-7").empty();
			alert(11);
			$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"个用户信息吗？<br/>"+"</span></h4>");
			alert("13");
			$.each(names,function(index,data){
				deleteUserNames[index]=$(data).text();
				
				$("#deleteUser .modal-body h4").append("<span class='small  '>"+"&nbsp;&nbsp;["+$(data).text()+"]&nbsp;&nbsp;"+"</span>");
			});
			$("#deleteUser .modal-body .col-sm-7").append("<div><button type='button' id='btn_delete_user' class='btn btn-sm btn-primary'>确定</button>&nbsp;<button id='closeDeleteUserPage' class='btn btn-sm btn-primary' type='button'>取消</button></div>");
			
			$("#closeDeleteUserPage").click(function(){
				$("#deleteUser").modal("hide");
			});
			$("#btn_delete_user").click(function(){	
				$.ajax({
					url :"deleteUser.action?id="+id,
					type : "post",
					success : function() {
						$("#deleteUser").modal("hide");
						window.location.href="findYh.action";
					}
				});		
			});
			}
			 $("#deleteUser").modal({keyboard:false}); 

		}
	</script>
<body>
 
<div id="top">
	<button type="button" class="btn btn-success" onClick="openDeletePage()" style="background: url(../img/secai.png);">删除</button>
</div>
<form>
	<div class="dwrapper">
		<table id="fixed_hdr2">
			<thead style="color: white;">
				<tr height="35px">
				<th></th>
					<th>用户姓名</th>
					<th>密码</th>
				</tr>
			</thead>
			<tbody id="SearchId">
				<c:forEach var="list" items="${user}" varStatus="status">
					<tr
						<c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
						<td><input type="checkbox" value="${list.ID}" /></td>
						<td>${list.userName}</td>
						<td>${list.passWord}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</form>
	<!---------------------------删除页面开始 --------------------------------------------------------- -->
	<div class="modal fade bs-example-modal-sm" id="deleteUser">
		<div class="modal-dialog">
			<div class="modal-content">
			<form action="deleteUser.action">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">删除用户</h4>
				</div>
				<div class="modal-body bg-info">
					<div class="row">
						<div class="col-sm-7 col-sm-offset-3"></div>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	<!---------------------------删除页面结束 --------------------------------------------------------- -->
</body>
</html>