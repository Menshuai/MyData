<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
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
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 500,
			colModal: [
			{ width: 60, align: 'center' },
			{ width: 120, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 120, align: 'center' },
			{ width: 120, align: 'center' }
			 
			],
			sort: true
		});
		
	});
	</script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<!-- Bootstrap -->
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js"></script>
<style type="text/css">
  #top{ padding-top: 10px;}
</style>

<script type="text/javascript">

//添加时数据不能为空
function add(){
	var nldj=$("#add input[name=nldj]");
	var mjdj=$("#add input[name=mjdj]");
	var yhlb=$("#add input[name=yhlb]");
	var bz=$("#add input[name=bz]");
	 
	if(nldj.val()==null||nldj.val()==""||mjdj.val()==null||mjdj.val()==""||yhlb.val()==null||yhlb.val()==""||bz.val()==null||bz.val()==""){
			 alert('信息不能为空，请填写完整!');
			return false;
		} 
	
	/* var lxdh1=document.getElementById("add_lxdh").value;
	if(isNaN(lxdh1)){
				 alert('联系电话必须是数字！');
			document.getElementById("add_lxdh").value="";
				return;
	} */
	
	/* var nldj1=document.getElementById("add_nldj").value;
	if(isNaN(nldj1)){
		 sAlert('能量价格必须是数字！');
	document.getElementById("add_nldj").value="";
		return;
	} 
	
	var mjdj=document.getElementById("add_mjdj").value;
	if(isNaN(mjdj1)){
		 sAlert('面积价格必须是数字！');
	document.getElementById("add_mjdj").value="";
		return;
	}  */
	/* var bz1=document.getElementById("add_bz").value;
	if(isNaN(bz1)){
		 sAlert('面积价格必须是数字！');
	document.getElementById("add_bz").value="";
		return;
	}  */
	
		$("#add form").submit();
	}
	
	
//修改时数据不允许为空
 function edit(){
	 
		var nldj=$("#edit input[name=nldj]");
		var mjdj=$("#edit input[name=mjdj]");
		
		var yhlb=$("#edit input[name=yhlb]");
		var bz=$("#edit input[name=bz]");
		
		if(nldj.val()==null||nldj.val()==""||mjdj.val()==null||mjdj.val()==""||yhlb.val()==null||yhlb.val()==""||bz.val()==null||bz.val()==""){
			 sAlert('信息不能为空，请填写完整!');
			return false;
		} 
		/* var lxdh2=document.getElementById("edit_lxdh").value;
		if(isNaN(lxdh2)){
					 alert('联系电话必须是数字！');
				document.getElementById("edit_lxdh").value="";
					return;
				} 
		var nldj2=document.getElementById("edit_nldj").value;
		if(isNaN(nldj2)){
			 alert('能量价格必须是数字！');
		document.getElementById("edit_nldj").value="";
			return;
		} 
		var mjdj2=document.getElementById("edit_mjdj").value;
		if(isNaN(mjdj2)){
			 alert('面积价格1必须是数字！');
		document.getElementById("edit_mjdj").value="";
			return;
		} 
		var bz2=document.getElementById("edit_bz").value;
		if(isNaN(bz2)){
			 alert('面积价格2必须是数字！');
		document.getElementById("edit_bz").value="";
			return;
		}  */
		$("#edit form").submit();
	} 
	
	
//修改数据
function openEditUserPage(){
	var ckbs=$("#xqinfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		alert("请选择要修改的信息");
		return false;
	}
	if(ckbs.length>1){
		sAlert("对不起一次只能修改一条信息，您选择了"+ckbs.length+"条信息");
		return false;
	}
	var id=ckbs.val();
	
	var nldj=ckbs.parent().next().text();
	var mjdj=ckbs.parent().next().next().text();
	
	var yhlb=ckbs.parent().next().next().next().text();
	var bz=ckbs.parent().next().next().next().next().text();
	
	$("#edit_id").val(id);
	
	$("#edit_nldj").val(nldj);
	$("#edit_mjdj").val(mjdj);

	$("#edit_yhlb").val(yhlb);
	$("#edit_bz").val(bz);
	
	$("#edit").modal({keyboard:false});
}


//删除
function openDeletePage(){
		var ckbs=$("#xqinfo input[type=checkbox]:checked");
		if(ckbs.length==0){
			alert("请选择要删除的小区");
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
				url :"deleteDjb.action?id="+id,
				type : "post",
				success : function() {
					$("#deleteUser").modal("hide");
					window.location.href="DjbfindList.action";
				}
				
			});		
		});
		
		$("#deleteUser").modal({keyboard:false});
	}
</script>

</head>
<body>
 <div class="panel panel-success">
 <div class="panel-heading">用户单价信息</div>
 <div class="panel-body"  style="width: 99%; height: 90%; position: absolute; overflow:auto;">
 <div id="top">
	<button type="button" class="btn btn-success" data-toggle="modal" data-target="#add" style="background: url(../img/secai.png);">添加</button>&nbsp;&nbsp;
   <button type="button" class="btn btn-success" onClick="openEditUserPage()" style="background: url(../img/secai.png);">修改</button>&nbsp;&nbsp;
	<button type="button" class="btn btn-success" onClick="openDeletePage()" style="background: url(../img/secai.png);">删除</button>
</div>
			<hr />
	<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
		<tr height="35px" style="background: url(../img/secai.png);">
						<th></th>
						<th>能量单价</th>
						<th>面积单价</th>
						<th>用户类别</th>
						<th>备注</th>
		</tr>
				</thead>
				<tbody id="xqinfo">
					<c:forEach  var="djb" items="${djb}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
									 <td><input type="checkbox" value="${djb.id}" /></td>
                                     <td>${djb.nldj}</td>
                                     <td>${djb.mjdj}</td>
                                     <td>${djb.yhlb}</td>
                                     <td>${djb.bz}</td>
					</c:forEach>
				</tbody>
			</table>
</div>
</div>
<!-- 添加用户开始 ----------------------------------------------------------------->
<div class="modal fade" id="add">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">添加</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="InsertDjb.action" method="post">
						 
						<div class="form-group">
							<label for="add_nldj"
								class="col-sm-2 col-sm-offset-3 control-label">能量单价</label>
							<div class="col-sm-4">
								<input id="add_nldj" type="text" name="nldj"
									class="form-control" placeholder="能量单价" />
							</div>
						</div>
								<div class="form-group">
							<label for="add_mjdj"
								class="col-sm-2 col-sm-offset-3 control-label">面积单价</label>
							<div class="col-sm-4">
								<input id="add_mjdj" type="text" name="mjdj"
									class="form-control" placeholder="面积单价" />
							</div>
						</div>
						 
						<div class="form-group">
							<label for="add_yhlb"
								class="col-sm-2 col-sm-offset-3 control-label">用户类别</label>
							<div class="col-sm-4">
								<input id="add_yhlb" type="text" name="yhlb"
									class="form-control" placeholder="用户类别" />
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
					<h4 class="text-center" style="padding: 0; margin: 0;">修改</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="updateDjb.action" method="post">
					 <input type="hidden" id="edit_id" name="id" />
								
						<div class="form-group">
							<label for="edit_nldj"
								class="col-sm-2 col-sm-offset-3 control-label">能量单价</label>
							<div class="col-sm-4">
								<input id="edit_nldj" type="text" name="nldj"
									class="form-control" placeholder="能量单价" />
							</div>
						</div>
								<div class="form-group">
							<label for="edit_mjdj"
								class="col-sm-2 col-sm-offset-3 control-label">面积单价</label>
							<div class="col-sm-4">
								<input id="edit_mjdj" type="text" name="mjdj"
									class="form-control" placeholder="面积单价" />
							</div>
						</div>
					 
								 <div class="form-group">
							<label for="edit_yhlb"
								class="col-sm-2 col-sm-offset-3 control-label">用户类别</label>
							<div class="col-sm-4">
								<input id="edit_yhlb" type="text" name="yhlb"
									class="form-control" placeholder="用户类别" />
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
			<form action="">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">删除</h4>
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
</html>