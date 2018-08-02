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
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="../js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet"	media="all" />
<link href="../css/bootstrap.css" type="text/css" rel="stylesheet"	/>
 <link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet" media="all" />
 
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../js/layer/2.4/skin/layer.css" rel="stylesheet" type="text/css" />
<script src="../js/layer/2.4/layer.js" type="text/javascript"></script>
<script	src="https://meetselva.github.io/fixed-table-rows-cols/js/sortable_table.js"
	type="text/javascript"></script>
<script src="../js/fixed_table_rc.js" type="text/javascript"></script>
<script src="../js/main.js"></script>

<script type="text/javascript" src="../js/bootstrap.js"></script>

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
			var ckbs=$("#users input[type=checkbox]:checked");
			  if(ckbs.length==0){
				layer.alert('请选择要删除的用户', {
					  icon: 1,
					  skin: 'layui-layer-molv' // 
					})
				return false;
			}else if(ckbs.length>1){
				 layer.alert('对不起一次只能删除一个', {
					  icon: 1,
					  skin: 'layui-layer-molv' // 
					})
				return false;
			}else{
			var id=[];
			$.each(ckbs,function(index,data){
				id[index]=$(data).val();
			});
			  layer.confirm('确定要删除这个用户吗？', {
				  btn: ['确定','取消'], //按钮
				  skin: 'layui-layer-molv' // 样式
				},function(){
					$.ajax({
						url :"deleteUser.action?id="+id,
						type : "post",
						success : function() {
							window.location.href="findYh.action";
							layer.msg("删除成功！")
						},
						error: function() {
							layer.alert('删除失败', {
							  icon: 1,
							  skin: 'layui-layer-molv' // 
							})
						}
					});		
				//});
				});  
			/* var names=ckbs.parent().next();
			var deleteUserNames=[];
			$("#deleteUser .modal-body .col-sm-7").empty();
			$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"个用户信息吗？<br/>"+"</span></h4>");
			$.each(names,function(index,data){
				deleteUserNames[index]=$(data).text();
				$("#deleteUser .modal-body h4").append("<span class='small  '>"+"&nbsp;&nbsp;["+$(data).text()+"]&nbsp;&nbsp;"+"</span>");
			});
			$("#deleteUser .modal-body .col-sm-7").append("<div><button type='button' id='btn_delete_user' class='btn btn-sm btn-primary'>确定</button>&nbsp;<button id='closeDeleteUserPage' class='btn btn-sm btn-primary' type='button'>取消</button></div>");
			
			$("#closeDeleteUserPage").click(function(){
				$("#deleteUser").modal("hide");
			}); */
			//alert(id)
			//$("#btn_delete_user").click(function(){	
			}
			// $("#deleteUser").modal({keyboard:false}); 
		}
	
	
//分配角色
	function openEditRolePage(){
		// debugger;
		var ckbs=$("#users input[type=checkbox]:checked");
		if(ckbs.length==0){
			layer.alert('请选择要修改的用户', {
				  icon: 1,
				  skin: 'layui-layer-molv'  
				})
			return false;
		}
		if(ckbs.length>1){
			layer.alert("对不起一次只能修改一个用户，您选择了"+ckbs.length+"个用户", {
				  icon: 1,
				  skin: 'layui-layer-molv' 
				})
			return false;
		}
		$.ajax({
			url : "getAmend.action",
			type : "post",
			async : false,
			dataType : "json",
			data : {  
				"id" : ckbs.val()
			},
			success : function(data) {
			  var dataObj=eval("("+data+")");//转换为json对象 切记，转JSON要去掉两边的“”
		       var map=dataObj.map;
		       var role=dataObj.roles;
		       $("#amend_user_role").empty();
		       for(i=0;i<role.length;i++){  
		    	   if(map[role[i]["id"]]==role[i]["id"]){
		    		   $("#amend_user_role").append("<div class='form-group col-sm-offset-6'><input class='col-sm-8'   type='checkbox' name='userRoleList' value='"+role[i]['id']+"' checked='checked'/><div class='col-sm-offset-1'>"+role[i]['name']+"</div></div>");
		    	   }else{
		    		   $("#amend_user_role" ).append("<div class='form-group col-sm-offset-6'><input class='col-sm-8'  type='checkbox' name='userRoleList' value='"+role[i]['id']+"'/><div class='col-sm-offset-1'>"+role[i]['name']+"</div></div>");
			    	}
		       }	       
		    }
		});
		 $("#amend_user_id").val(ckbs.val());
		$("#amend_user_name").val(ckbs.parent().next().text());		
		/*  $("#amendRole").modal({keyboard:false});     */
		$("#amendRole").modal({keyboard : false});
	}
	
	//分配角色   提交
	function amendRole(){
		$("#amendRole form").submit();
	}
	
	</script>
<body>
<div class="panel panel-success">
 <div class="panel-heading">用户管理</div>
  <div class="panel-body">
<div id="top">
	<button type="button" class="btn btn-success" onClick="openDeletePage()" style="background: url(../img/secai.png);">删除</button>
	<button type="button" class="btn btn-primary" onClick="openEditRolePage()" style="background: url(../img/secai.png);">分配角色</button>
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
			<tbody id="users" class="text-center">
				<c:forEach var="list" items="${user}" varStatus="status">
					<tr class="hover" <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
						<td><input type="checkbox" value="${list.ID}" /></td>
						<td>${list.userName}</td>
						<td>${list.passWord}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	 
	</form>
	</div>
	</div>
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
	<!---------------------------分配角色页面开始 --------------------------------------------------------- -->
	<div class="modal fade bs-example-modal-sm" id="amendRole">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">分配角色</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="amend.action" method="post">
						<div class="form-group">
							<label for="amend_username"
								class="col-sm-2 col-sm-offset-3 control-label">当前用户:</label>
							<div class="col-sm-4">
								<input id="amend_user_name" type="text" readonly="readonly"
									style="border-left: 0px; border-top: 0px; border-right: 0px; border-bottom: 1px" />
							</div>
						</div>
						<div class="form-group" id="amend_user_role"></div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-5">
								<button type="button" onClick="amendRole()" class="btn btn-primary btn-sm">提交</button>
							</div>
						</div>
						<input type="hidden" id="amend_user_id" name="id" />
					</form>
				</div>
				<div class="modal-footer bg-primary"
					style="padding: 5px 0; margin: 0;">
					<div class="text-center small">河南众源开发管理系统</div>
				</div>
			</div>
		</div>
	</div>
	<!---------------------------分配角色页面结束 --------------------------------------------------------- -->
</body>
</html>