<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%  
  String path=request.getContextPath();
  String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<!-- Bootstrap -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://code.jquery.com/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script src="../js/main.js"></script>  
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet" media="all" />
<link href="../js/layer/2.4/skin/layer.css" rel="stylesheet" type="text/css" />

<script src="https://meetselva.github.io/fixed-table-rows-cols/js/sortable_table.js" type="text/javascript"></script>
<script src="../js/fixed_table_rc.js" type="text/javascript"></script>
<script src="../js/layer/2.4/layer.js" type="text/javascript"></script>     

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
			height: 600,
			colModal: [
				{ width: 90, align: 'center' },
				{ width: 160, align: 'center' },
				{ width: 200, align: 'center' },
				{ width: 200, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>


</head>
<body>
	<div class="panel panel-success">
		<div class="panel-heading" >角色管理</div>
		<div class="panel-body">
			<button type="button" class="btn btn-success" data-toggle="modal" data-target="#addUser" style="background: url(../img/secai.png);">添加</button>
				
			<button type="button" class="btn btn-success"
				onClick="openEditUserPage()" style="background: url(../img/secai.png);">修改</button>
				
			<button type="button" class="btn btn-primary"
				onclick="openEditMenuPage()" style="background: url(../img/secai.png);">分配权限</button>
				
			<button type="button" class="btn btn-primary"
				onClick="openCheckUserPage()" style="background: url(../img/secai.png);">查看当前角色下的用户</button>
				
			<button type="button" class="btn btn-danger"
				onClick="openDeleteUserPage()"  style="background: url(../img/secai.png);">删除</button>
			<br /> <br />
			<table id="fixed_hdr2"  class="table table-hover table-bordered table-condensed  "  >
				  <thead style="background: url(../img/secai.png); "  >
					  <tr height="35px" style="background: url(../img/secai.png);">
							<th class="text-center"></th>
							<th class="text-center">角色名</th>
							<th class="text-center">创建时间</th>
							<th class="text-center">最后修改时间</th>
						</tr>
				</thead> 
				<tbody class="text-center" id="users">
					<c:forEach items="${role}" var="role" varStatus="status">
						<tr class="info">
						<c:choose>
							<c:when test="${status.index%2==0 }">
								<td><input type="checkbox" value="${role.id}" /></td> 
								<td>${role.name}</td>
								<td  title="<fmt:formatDate value="${role.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
										<fmt:formatDate value="${role.createTime}"   pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td title="<fmt:formatDate value="${role.lastEditTime}"  pattern="yyyy-MM-dd HH:mm:ss" />">
											<fmt:formatDate value="${role.lastEditTime}"  pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</c:when>
							<c:otherwise>
								<td><input type="checkbox" value="${role.id}" /></td>
								<td>${role.name}</td>
								<td><fmt:formatDate value="${role.createTime}"  pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><fmt:formatDate value="${role.lastEditTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</c:otherwise>
						</c:choose>
						</tr>
						
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
	<!-- ----------------------------添加页面 ------------------------------------------->
	<div class="modal fade" id="addUser">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">添加角色</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="add.action" method="post">
						<div class="form-group">
							<label for="add_username"
								class="col-sm-2 col-sm-offset-3 control-label">角色名称</label>
							<div class="col-sm-4">
								<input id="add_username" type="text" name="name"
									class="form-control" placeholder="角色名称" />
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-5">
								<button type="button" onclick="add()" class="btn btn-primary btn-sm">提交</button>
								<button type="reset" class="btn btn-primary btn-sm">重置</button>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer bg-primary"
					style="padding: 5px 0; margin: 0;">
					<div class="text-center small">河南众源开发管理系统</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ----------------------------添加页面结束------------------------------------------->
	<!---------------------------修改页面开始 --------------------------------------------------------- -->
	<div class="modal fade bs-example-modal-sm" id="editUser">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">修改角色</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="edit.action"  method="post">
						<input type="hidden" id="edit_user_id" name="id" />
						<div class="form-group">
							<label for="edit_username"
								class="col-sm-2 col-sm-offset-3 control-label">角色名称</label>
							<div class="col-sm-4">
								<input id="edit_username" type="text" name="name"
									class="form-control" placeholder="角色名称" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-5">
								<button type="button" onClick="editUser()" class="btn btn-primary btn-sm">提交</button>
								<button type="reset" class="btn btn-primary btn-sm">重置</button>
							</div>
						</div>
						
					</form>
				</div>
				<div class="modal-footer bg-primary" style="padding: 5px 0; margin: 0;">
					<div class="text-center small">河南众源开发管理系统</div>
				</div>
			</div>
		</div>
	</div>
	<!---------------------------修改页面结束 --------------------------------------------------------- -->

	<!---------------------------删除页面开始 --------------------------------------------------------- -->
	<div class="modal fade bs-example-modal-sm" id="deleteUser">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">删除角色</h4>
				</div>
				<div class="modal-body bg-info">
					<div class="row">
						<div class="col-sm-7 col-sm-offset-3"></div>
					</div>
				</div>
				<div class="modal-footer bg-primary"
					style="padding: 5px 0; margin: 0;">
					<div class="text-center small">河南众源开发管理系统</div>
				</div>
			</div>
		</div>
	</div>
	<!---------------------------删除页面结束 --------------------------------------------------------- -->
	<!--------------------------查看角色下的用户页面开始 --------------------------------------------------------- -->
	<div class="modal fade bs-example-modal-sm" id="checkUser">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">
						<span id="roleName"></span>
					</h4>
				</div>
				<div class="modal-body bg-info">
					<div class="row">
						<div id="chackUserNameByRoleId"></div>
					</div>
				</div>
				<div class="modal-footer bg-primary"
					style="padding: 5px 0; margin: 0;">
					<div class="text-center small">河南众源开发管理系统</div>
				</div>
			</div>
		</div>
	</div>
	<!---------------------------查看角色下的用户页面结束 --------------------------------------------------------- -->
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
								<button type="submit" class="btn btn-primary btn-sm">提交</button>
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
	<!---------------------------分配角色页面结束 ------------------------------------------------------------------------ -->
		
<script type="text/javascript">
//分配权限
function openEditMenuPage(){
	var ckbs=$("#users input[type=checkbox]:checked");
	if(ckbs.length==0){
		layer.alert('请选择要修改分配权限的信息', {
			  icon: 1,
			  skin: 'layui-layer-molv'  
			})
		return false;
	}
	if(ckbs.length>1){
		 layer.alert("对不起一次只能修改一条数据，您选择了"+ckbs.length+"条数据", {
			  icon: 1,
			  skin: 'layui-layer-molv' 
			})
		return false;
	}
	$.ajax({
		url : "getqx.action",
		type : "post",
		async : false,
		dataType : "json",
		data : {
			"id" : ckbs.val()
		},
		success : function(data) {
	       var roleMenu=eval(data).roleMenu;
	       var MenuAll=eval(data).MenuAll;
	       $("#amend_user_role").empty();
	       for(var i=0;i<MenuAll.length;i++){
	    	   $("#amend_user_role").append("<div class='form-group col-sm-offset-6'><input class='col-sm-7'  type='checkbox' name='userRoleList' id='"+MenuAll[i].id+ckbs.val()+"' value='"+MenuAll[i].id+"'/><div class='col-sm-offset-1'>"+MenuAll[i].name+"</div></div>");
			   var menu = MenuAll[i].childMenus;
			   for(var j=0;j<menu.length;j++){
				   $("#amend_user_role").append("<div class='form-group col-sm-offset-6'><input class='col-sm-8' type='checkbox' name='userRoleList' id='"+menu[j].id+ckbs.val()+"' value='"+menu[j].id+"'/><div class='col-sm-offset-1'>"+menu[j].name+"</div></div>");
			   }
	       }
	       for(var k=0;k<roleMenu.length;k++){
	    	   $("#"+roleMenu[k].id+ckbs.val()+"").attr("checked","checked");
	    	   var role = roleMenu[k].childMenus;
	    	   for(var l=0;l<role.length;l++){
	    		   $("#"+role[l].id+ckbs.val()+"").attr("checked","checked");
	    	   }
	       }
	    }
	});
	$("#amend_user_id").val(ckbs.val());
	$("#amend_user_name").val(ckbs.parent().next().text());		
	$("#amendRole").modal({keyboard : false});
}
</script>

	<script type="text/javascript">
//添加时数据不能为空
	function add(){
		var name=$("#addUser input[name=name]");
		if(name.val()==null||name.val()==""){
				 
				 layer.alert('角色不能为空', {
					  icon: 1,
					  skin: 'layui-layer-molv' // 
					})
				return false;
			} 
			$("#addUser form").submit();
		}
		
//修改时数据不允许为空
		function editUser() {
			var name = $("#editUser input[name=name]");
			if (name.val() == null || name.val() == "") {
				layer.alert('信息不能为空，请填写完整!', {
					  icon: 1,
					  skin: 'layui-layer-molv' // 
					})
				/* $("#warn_editUser_name").remove();
				name.parent().after("<div id='warn_editUser_name'  class='col-sm-3 text-left control-label'><div class='small text-left  text-danger'>角色名不能为空</div></div>");
			 */
			 return false;
			}
			$("#editUser form").submit();

		}
		
//修改事件		
		function openEditUserPage() {
			var ckbs = $("#users input[type=checkbox]:checked");
			if (ckbs.length == 0) {
				layer.alert('请选择要修改的角色', {
					  icon: 1,
					  skin: 'layui-layer-molv' // 
					})
				return false;
			}
			if (ckbs.length > 1) {
				 layer.alert('对不起一次只能修改一个角色,您选择了"+ckbs.length+"条信息"', {
					  icon: 1,
					  skin: 'layui-layer-molv' // 
					})
				return false;
			}
			var id = ckbs.val();
			var name = ckbs.parent().next().text();
			$("#edit_user_id").val(id);
			$("#edit_username").val(name);
			$("#editUser").modal({keyboard : false});
		}
		
//查看当前角色下的用户
		function openCheckUserPage() {
		debugger;
			var ckbs = $("#users input[type=checkbox]:checked");
			
			if (ckbs.length == 0) {
				layer.alert('请选择要查看的角色', {
					  icon: 1,
					  skin: 'layui-layer-molv' 
					})
				return false;
			}
			if (ckbs.length > 1) {
				layer.alert('对不起一次只能查看一个角色下的所有用户', {
					  icon: 1,
					  skin: 'layui-layer-molv' 
					})
				return false;
			}
			$.ajax({
				url : "findUsers.action",
				type : "post",
				async : false,
				dataType : "json",
				data : {
					"id" : ckbs.val()
				},
				success : function(data) {
					var userList = eval(data).userList;
					$("#chackUserNameByRoleId").empty();
							for (i = 0; i < userList.length; i++) {
								$("#chackUserNameByRoleId").append(
												"<div style='text-align:center; vertical-align:middle;'><span class='text-center' style='padding:0;margin:0;'>"
													+ userList[i]["userName"]+ "</span></div>");
							}
						}
					});
			$("#roleName").text(ckbs.parent().next().text());
			 $("#checkUser").modal({keyboard : false}); 
			
		}
	
	
	//删除
		function openDeleteUserPage(){
			debugger;
			var ckbs=$("#users input[type=checkbox]:checked");
			if(ckbs.length==0){
				layer.alert('请选择要删除的角色', {
					  icon: 1,
					  skin: 'layui-layer-molv' // 
					})
				return false;
			}
			if(ckbs.length>1){
				 layer.alert('对不起一次只能删除一个', {
					  icon: 1,
					  skin: 'layui-layer-molv' // 
					})
				return false;
			}
			var id=[];
			$.each(ckbs,function(index,data){
				id[index]=$(data).val();
			});
			layer.confirm('确定要删除这个角色吗？', {
				  btn: ['确定','取消'], //按钮
				  skin: 'layui-layer-molv' // 样式
				},function(){
					$.ajax({
						url :"delete.action?id="+id,
						type : "post",
						success : function() {
							window.location.href="RoleGl.action";
							layer.msg("删除成功！")
						},
						error: function() {
							layer.alert('删除失败', {
							  icon: 1,
							  skin: 'layui-layer-molv' // 
							})
						}
					});		
				 });
			/* 	});   */
			/* var names=ckbs.parent().next();
			var deleteUserNames=[];
			$("#deleteUser .modal-body .col-sm-7").empty();
			$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"个角色吗？<br/>"+"</span></h4>");
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
					url :"delete.action?id="+id,
					type : "post",

					success : function() {
						$("#deleteUser").modal("hide");
						window.location.href="RoleGl.action";
					}
					
				});		
			});
			$("#deleteUser").modal({keyboard:false}); */			
		}
	</script>
</body>
</html>