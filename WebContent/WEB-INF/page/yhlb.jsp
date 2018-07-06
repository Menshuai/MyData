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
				{ width: 120, align: 'center' }
			],
			sort: true
		});
		
	});
	</script>
</head>
<body>
 <div class="panel panel-success">
 <div class="panel-heading">用户类别信息</div>
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
			<th>住宅</th>
			<th>商用</th>
			<th>廉租房</th>
			<th>其他</th>
		</tr>
	</thead>
				<tbody id="yhlbinfo">
					<c:forEach  var="yhlb" items="${yhlb}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
									<td ><input type="checkbox" value="${yhlb.id}" /></td>
                                     <td>${yhlb.zz}</td>
                                     <td>${yhlb.sy}</td>
                                     <td>${yhlb.lzf}</td>
                                     <td>${yhlb.qt}</td>
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
					<form class="form form-horizontal" action="InsertYhlb.action" method="post">
						
						 <div class="form-group">
							<label for="add_zz"
								class="col-sm-2 col-sm-offset-3 control-label">住宅</label>
							<div class="col-sm-4">
								<input id="add_zz" type="text" name="zz"
									class="form-control" placeholder="住宅" />
							</div>
						</div>
					
						
						<div class="form-group">
							<label for="add_sy"
								class="col-sm-2 col-sm-offset-3 control-label">商用</label>
							<div class="col-sm-4">
								<input id="add_sy" type="text" name="sy"
									class="form-control" placeholder="商用" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="add_lzf"
								class="col-sm-2 col-sm-offset-3 control-label">廉租房</label>
							<div class="col-sm-4">
								<input id="add_lzf" type="text" name="lzf"
									class="form-control" placeholder="廉租房" />
							</div>
						</div>
					
						
								<div class="form-group">
							<label for="add_qt"
								class="col-sm-2 col-sm-offset-3 control-label">其他</label>
							<div class="col-sm-4">
								<input id="add_qt" type="text" name="qt"
									class="form-control" placeholder="其他" />
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
					<form class="form form-horizontal" action="updateYhlb.action" method="post">
					 <input type="hidden" id="edit_id" name="id" />
									<div class="form-group">
							<label for="edit_zz"
								class="col-sm-2 col-sm-offset-3 control-label">住宅</label>
							<div class="col-sm-4">
								<input id="edit_zz" type="text" name="zz"
									class="form-control"   placeholder="住宅" />
									
							</div>
						</div>
						<!-- <div class="form-group">
							<label for="edit_zz"
								class="col-sm-2 col-sm-offset-3 control-label">本地端口</label>
							<div class="col-sm-4">
								<input id="edit_zz" type="text" name="zz"
									class="form-control" placeholder="本地端口"/>
									
							</div>
						</div> -->
								<div class="form-group">
							<label for="edit_sy"
								class="col-sm-2 col-sm-offset-3 control-label">商用</label>
							<div class="col-sm-4">
								<input id="edit_sy" type="text" name="sy"
									class="form-control" placeholder="商用" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_lzf"
								class="col-sm-2 col-sm-offset-3 control-label">廉租房</label>
							<div class="col-sm-4">
								<input id="edit_lzf" type="text" name="lzf"
									class="form-control" placeholder="廉租房" />
							</div>
						</div>
							<!-- <div class="form-group">
							<label for="edit_status"	class="col-sm-2 col-sm-offset-3 control-label">连接状态</label>

							<div class="col-sm-4">

									<select id="edit_status" name="status" class="form-control" placeholder="连接状态" >
										<option value=0>离线</option>
										<option value=1>在线</option>
									</select>
									
							</div>
						</div> -->
					
							<!-- 	<div class="form-group">
							<label for="edit_xqName"
								class="col-sm-2 col-sm-offset-3 control-label">小区名称</label>
							<div class="col-sm-4">
								<input id="edit_xqName" type="text" name="xqName"
									class="form-control" placeholder="小区名称" />
								
							</div>
						</div> -->
						<!-- <div class="form-group">
							<label for="edit_hesName"
								class="col-sm-2 col-sm-offset-3 control-label">换热站名称</label>
							<div class="col-sm-4">
								<input id="edit_hesName" type="text" name="hesName"
									class="form-control" placeholder="换热站名称" />
								
							</div>
						</div> -->
								<div class="form-group">
							<label for="edit_qt"
								class="col-sm-2 col-sm-offset-3 control-label">其他</label>
							<div class="col-sm-4">
								<input id="edit_qt" type="text" name="qt"
									class="form-control" placeholder="其他" />
										
									
							</div>
						</div>
							
						<!-- <div class="form-group">
							<label for="edit_remark"
								class="col-sm-2 col-sm-offset-3 control-label">备注</label>
							<div class="col-sm-4">
								<input id="edit_remark" type="text" name="remark"
									class="form-control" placeholder="备注" />
							</div>
						</div> -->
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
			<form action="deleteyhlb.action"  method="post">
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
	var zz=$("#add input[name=zz]");
	var sy=$("#add input[name=sy]");
	var lzf=$("#add input[name=lzf]");
	var qt=$("#add input[name=qt]");
	
		 if(zz.val()==null||zz.val()==""||sy.val()==null||sy.val()==""||lzf.val()==null||lzf.val()==""||qt.val()==null||qt.val()==""){
			 alert('信息不能为空，请填写完整!');
			return false;
		} 
		
		 var lzf1=document.getElementById("add_lzf").value;
		/*  if(isNaN(lzf1)){
				
				 alert('集中器端口号必须是数字！');
				document.getElementById("add_lzf").value="";
				return;
			}  *
		$("#add form").submit();
	}

//修改时数据不允许为空
 function edit(){
	var zz=$("#edit input[name=zz]");
	var sy=$("#edit input[name=sy]");
	var lzf=$("#edit input[name=lzf]");
	var qt=$("#edit input[name=qt]");
		 if(zz.val()==null||zz.val()==""||sy.val()==null||sy.val()==""||lzf.val()==null||lzf.val()==""||qt.val()==null||qt.val()==""){
			 alert('信息不能为空，请填写完整!');
			return false;
		} 
		 /* var zz2=document.getElementById("edit_zz").value;
		 if(isNaN(zz2)){
				
				 alert('集中器net必须是数字！');
				document.getElementById("edit_zz").value="";
				return;
			} 
		 var lzf2=document.getElementById("edit_lzf").value;
		 if(isNaN(lzf2)){
				
				 Alert('集中器端口号必须是数字！');
				document.getElementById("edit_lzf").value="";
				return;
			}  */

		$("#edit form").submit();
	} 

//修改
function openEditUserPage(){
	var ckbs=$("#yhlbinfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		alert("请选择要修改的信息");
		return false;
	}
	if(ckbs.length>1){
		alert("对不起一次只能修改一条信息，您选择了"+ckbs.length+"条信息");
		return false;
	}
	var id=ckbs.val();
	var zz=ckbs.parent().next().text();
	
	var sy=ckbs.parent().next().next().text();
	var lzf=ckbs.parent().next().next().next().text();
	
	var qt=ckbs.parent().next().next().next().next().text();
	var remark=ckbs.parent().next().next().next().next().next().next().text();
	
	$("#edit_id").val(id);
	$("#edit_zz").val(zz);
	$("#edit_sy").val(sy);
	$("#edit_lzf").val(lzf);
	$("#edit_qt").val(qt);
	$("#edit").modal({keyboard:false}); 
}
 
//删除
function openDeletePage(){
		var ckbs=$("#yhlbinfo input[type=checkbox]:checked");
		if(ckbs.length==0){
			alert("请选择要删除的集中器");
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
		$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"条用户信息吗？<br/>"+"</span></h4>");
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
				url :"deleteYhlb.action?id="+id,
				type : "post",
				success : function() {
					$("#deleteUser").modal("hide");
					window.location.href="YhlbfindList.action";
				}
			});		
		});
		$("#deleteUser").modal({keyboard:false});
	}
 
</script>
</html>