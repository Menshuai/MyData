<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
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
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 600,
			colModal: [
				{ width: 60, align: 'center' },
				
				{ width: 100, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 100, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 100, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
</head>
<body>
 <div class="panel panel-success">
 <div class="panel-heading">风盘信息</div>
 <div class="panel-body">
<button type="button" class="btn btn-success" data-toggle="modal" data-target="#add" style="background: url(../img/secai.png);">添加</button>&nbsp;&nbsp;
 
 <button type="button" class="btn btn-success"
				onClick="openEditUserPage()" style="background: url(../img/secai.png);">修改</button>&nbsp;&nbsp;
<button type="button" class="btn btn-success" onClick="openDeletePage()" style="background: url(../img/secai.png);">删除</button>
			<hr />
<form>
	<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
		<tr height="35px" style="background: url(../img/secai.png);">
			<th></th>
			<th>风盘编号</th>
			<th>用户编号</th>
			<th>型号</th>
			<th>位置</th>
			<th>备注</th>
		</tr>
	</thead>
				<tbody id="fpinfo">
					<c:forEach  var="fp" items="${fp}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
									<td ><input type="checkbox" value="${fp.id}" /></td>
                                     <td>${fp.fpbh}</td>
                                     <td>${fp.yhbh}</td>
                                     <td>${fp.xh}</td>
                                     <td>${fp.wz}</td>
                                     <td>${fp.bz}</td>
					</c:forEach>
				</tbody>
			</table>
</div>
</form>
</div>
</div>
<!-- 添加用户开始 ----------------------------------------------------------------->
<div class="modal fade" id="add">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">添&nbsp;&nbsp;&nbsp;加</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="InsertFp.action" method="post">
						
						 <div class="form-group">
							<label for="add_fpbh"
								class="col-sm-2 col-sm-offset-3 control-label">风盘编号</label>
							<div class="col-sm-4">
								<input id="add_fpbh" type="text" name="fpbh"
									class="form-control" placeholder="风盘编号" />
							</div>
						</div>
					 
						<div class="form-group">
							<label for="add_yhbh"
								class="col-sm-2 col-sm-offset-3 control-label">用户编号</label>
							<div class="col-sm-4">
								<input id="add_yhbh" type="text" name="yhbh"
									class="form-control" placeholder="用户编号" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="add_xh"
								class="col-sm-2 col-sm-offset-3 control-label">型&nbsp;&nbsp;&nbsp;号</label>
							<div class="col-sm-4">
								<input id="add_xh" type="text" name="xh"
									class="form-control" placeholder="型号" />
							</div>
						</div>
					
						 
								<div class="form-group">
							<label for="add_wz"
								class="col-sm-2 col-sm-offset-3 control-label">安装位置</label>
							<div class="col-sm-4">
								<input id="add_wz" type="text" name="wz"
									class="form-control" placeholder="安装位置" />
							</div>
						</div>
						
						  <div class="form-group">
							<label for="add_bz"
								class="col-sm-2 col-sm-offset-3 control-label">备&nbsp;&nbsp;&nbsp;注</label>
							<div class="col-sm-4">
								<input id="add_bz" type="text" name="bz"
									class="form-control" placeholder="备注" />
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
			</div>
		</div>
	</div>
<!-- 添加用户结束 -------------------------------------------------- -->
<!---------------------------修改页面开始 --------------------------------------------------------- -->
	<div class="modal fade bs-example-modal-sm" id="edit">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">修 &nbsp;&nbsp;&nbsp;改</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="updateFp.action" method="post">
					 <input type="hidden" id="edit_id" name="id" />
									<div class="form-group">
							<label for="edit_fpbh"
								class="col-sm-2 col-sm-offset-3 control-label">风盘编号</label>
							<div class="col-sm-4">
								<input id="edit_fpbh" type="text" name="fpbh"
									class="form-control"   placeholder="风盘编号" />
									
							</div>
						</div>
						 
								<div class="form-group">
							<label for="edit_yhbh"
								class="col-sm-2 col-sm-offset-3 control-label">用户编号</label>
							<div class="col-sm-4">
								<input id="edit_yhbh" type="text" name="yhbh"
									class="form-control" placeholder="用户编号" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_xh"
								class="col-sm-2 col-sm-offset-3 control-label">型&nbsp;&nbsp;&nbsp;号</label>
							<div class="col-sm-4">
								<input id="edit_xh" type="text" name="xh"
									class="form-control" placeholder="型号" />
							</div>
						</div>
							 
								<div class="form-group">
							<label for="edit_wz"
								class="col-sm-2 col-sm-offset-3 control-label">安装位置</label>
							<div class="col-sm-4">
								<input id="edit_wz" type="text" name="wz"
									class="form-control" placeholder="安装位置" />
										
									
							</div>
						</div>
							
						 <div class="form-group">
							<label for="edit_bz"
								class="col-sm-2 col-sm-offset-3 control-label">备&nbsp;&nbsp;&nbsp;注</label>
							<div class="col-sm-4">
								<input id="edit_bz" type="text" name="bz"
									class="form-control" placeholder="备注" />
							</div>
						</div> 
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-5">
								<button type="button" onclick="edit()" class="btn btn-primary btn-sm">提交</button>
								<button type="reset" class="btn btn-primary btn-sm">重置</button>
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
	<!---------------------------修改页面结束 --------------------------------------------------------- -->

	<!---------------------------删除页面开始 --------------------------------------------------------- -->
	<div class="modal fade bs-example-modal-sm" id="deleteUser">
		<div class="modal-dialog">
			<div class="modal-content">
			<form action="deleteFp.action"  method="post">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">删&nbsp;&nbsp;&nbsp;除</h4>
				</div>
				<div class="modal-body bg-info">
					<div class="row">
						<div class="col-sm-7 col-sm-offset-3"></div>
					</div>
				</div>
				<div class="modal-footer bg-primary"
					style="padding: 5px 0; margin: 0;">
					<div class="text-center small">河南众源</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	<!---------------------------删除页面结束 --------------------------------------------------------- -->



</body>
<!-- Bootstrap -->
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript">

//添加时数据不能为空
function add(){
	var fpbh=$("#add input[name=fpbh]");
	var yhbh=$("#add input[name=yhbh]");
	var xh=$("#add input[name=xh]");
	var wz=$("#add input[name=wz]");
	var bz=$("#add input[name=bz]");
	
		 if(fpbh.val()==null||fpbh.val()==""||yhbh.val()==null||yhbh.val()==""||xh.val()==null||xh.val()==""||wz.val()==null||wz.val()==""||bz.val()==null||bz.val()==""){
			 alert('信息不能为空，请填写完整!');
			return false;
		} 
		
		 var xh1=document.getElementById("add_xh").value;
		 if(isNaN(xh1)){
				
				 alert('型号必须是数字！');
				document.getElementById("add_xh").value="";
				return;
			} 
		$("#add form").submit();
	}

//修改时数据不允许为空
 function edit(){
	var fpbh=$("#edit input[name=fpbh]");
	var yhbh=$("#edit input[name=yhbh]");
	var xh=$("#edit input[name=xh]");
	var wz=$("#edit input[name=wz]");
	var bz=$("#add input[name=bz]");
		/*  if(fpbh.val()==null||fpbh.val()==""||yhbh.val()==null||yhbh.val()==""||xh.val()==null||xh.val()==""||wz.val()==null||wz.val()==""||bz.val()==null||bz.val()==""){
			 alert('信息不能为空，请填写完整!');
			return false;
		}  */
		 
		/*  
		 var fpbh2=document.getElementById("edit_fpbh").value;
		 if(isNaN(fpbh2)){
				
				 alert('风盘编号必须是数字！');
				document.getElementById("edit_fpbh").value="";
				return;
			} 
		 
		 
		 var xh2=document.getElementById("edit_xh").value;
		 if(isNaN(xh2)){
				
				 alert('型号号必须是数字！');
				document.getElementById("edit_xh").value="";
				return;
			}  */

		$("#edit form").submit();
	} 

//修改
function openEditUserPage(){
	var ckbs=$("#fpinfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		alert("请选择要修改的信息");
		return false;
	}
	if(ckbs.length>1){
		alert("对不起一次只能修改一条信息，您选择了"+ckbs.length+"条信息");
		return false;
	}
	var id=ckbs.val();
	
	var fpbh=ckbs.parent().next().text();
	var yhbh=ckbs.parent().next().next().text();
	var xh=ckbs.parent().next().next().next().text();
	var wz=ckbs.parent().next().next().next().next().text();
	var bz=ckbs.parent().next().next().next().next().next().text();
	
	$("#edit_id").val(id);
	
	$("#edit_fpbh").val(fpbh);
	$("#edit_yhbh").val(yhbh);
	$("#edit_xh").val(xh);
	$("#edit_wz").val(wz);
	$("#edit_bz").val(bz);
	
	$("#edit").modal({keyboard:false}); 
}
 
//删除
function openDeletePage(){
		var ckbs=$("#fpinfo input[type=checkbox]:checked");
		if(ckbs.length==0){
			alert("请选择要删除的风盘");
			return false;
		}
		if(ckbs.length>1){
			 alert('对不起一次只能删除一个');
			return false;
		}
		var id=[];
		$.each(ckbs,function(index,data){
			id[index]=$(data).val();
		});
		var names=ckbs.parent().next();
		var deleteUserNames=[];
		$("#deleteUser .modal-body .col-sm-7").empty();
		$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"条数据吗？<br/>"+"</span></h4>");
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
				url :"deleteFp.action?id="+id,
				type : "post",
				success : function() {
					$("#deleteUser").modal("hide");
					window.location.href="FpfindList.action";
				}
			});		
		});
		$("#deleteUser").modal({keyboard:false});
	}
 
</script>
</html>