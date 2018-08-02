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
 <div class="panel-heading">集中器信息</div>
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
			<th>集中器net</th>
			<th>集中器IP</th>
			<th>集中器端口</th>
			<th>安装位置</th>
		</tr>
	</thead>
				<tbody id="jzqinfo">
					<c:forEach  var="jzq" items="${jzq}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
									<td ><input type="checkbox" value="${jzq.id}" /></td>
                                     <td>${jzq.jzqnet}</td>
                                     <td>${jzq.jzqip}</td>
                                     <td>${jzq.jzqport}</td>
                                     <td>${jzq.azdz}</td>
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
					<form class="form form-horizontal" action="InsertJzq.action" method="post">
						
						 <div class="form-group">
							<label for="add_jzqnet"
								class="col-sm-2 col-sm-offset-3 control-label">集中器net</label>
							<div class="col-sm-4">
								<input id="add_jzqnet" type="text" name="jzqnet"
									class="form-control" placeholder="集中器net" />
							</div>
						</div>
					<!--   
						<div class="form-group">
							<label for="add_jzqnet"
								class="col-sm-2 col-sm-offset-3 control-label">本地端口</label>
							<div class="col-sm-4">
								<input id="add_jzqnet" type="text" name="jzqnet"
									class="form-control" placeholder="本地端口" />
							</div>
						</div> -->
						
						<div class="form-group">
							<label for="add_jzqip"
								class="col-sm-2 col-sm-offset-3 control-label">集中器IP</label>
							<div class="col-sm-4">
								<input id="add_jzqip" type="text" name="jzqip"
									class="form-control" placeholder="集中器IP" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="add_jzqport"
								class="col-sm-2 col-sm-offset-3 control-label">集中器端口</label>
							<div class="col-sm-4">
								<input id="add_jzqport" type="text" name="jzqport"
									class="form-control" placeholder="集中器端口" />
							</div>
						</div>
					
						<!-- <div class="form-group">
							<label for="add_xqName"
								class="col-sm-2 col-sm-offset-3 control-label">小区名称</label>
							<div class="col-sm-4">
								<input id="add_xqName" type="text" name="xqName"
									class="form-control" placeholder="小区名称" />
							</div>
						</div> -->
						<!-- <div class="form-group">
							<label for="add_hesName"
								class="col-sm-2 col-sm-offset-3 control-label">换热站名称</label>
							<div class="col-sm-4">
								<input id="add_hesName" type="text" name="hesName"
									class="form-control" placeholder="换热站名称" />
							</div>
						</div> -->
								<div class="form-group">
							<label for="add_azdz"
								class="col-sm-2 col-sm-offset-3 control-label">安装地址</label>
							<div class="col-sm-4">
								<input id="add_azdz" type="text" name="azdz"
									class="form-control" placeholder="安装地址" />
							</div>
						</div>
						
						<!-- <div class="form-group">
							<label for="add_remark"
								class="col-sm-2 col-sm-offset-3 control-label">备注</label>
							<div class="col-sm-4">
								<input id="add_remark" type="text" name="remark"
									class="form-control" placeholder="备注" />
							</div>
						</div> -->
								
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
					<form class="form form-horizontal" action="updateJzq.action" method="post">
					 <input type="hidden" id="edit_id" name="id" />
									<div class="form-group">
							<label for="edit_jzqnet"
								class="col-sm-2 col-sm-offset-3 control-label">集中器net</label>
							<div class="col-sm-4">
								<input id="edit_jzqnet" type="text" name="jzqnet"
									class="form-control"   placeholder="集中器net" />
									
							</div>
						</div>
					
								<div class="form-group">
							<label for="edit_jzqip"
								class="col-sm-2 col-sm-offset-3 control-label">集中器IP</label>
							<div class="col-sm-4">
								<input id="edit_jzqip" type="text" name="jzqip"
									class="form-control" placeholder="集中器IP" />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_jzqport"
								class="col-sm-2 col-sm-offset-3 control-label">集中器端口</label>
							<div class="col-sm-4">
								<input id="edit_jzqport" type="text" name="jzqport"
									class="form-control" placeholder="集中器端口" />
							</div>
						</div>
										
								<div class="form-group">
							<label for="edit_azdz"
								class="col-sm-2 col-sm-offset-3 control-label">安装位置</label>
							<div class="col-sm-4">
								<input id="edit_azdz" type="text" name="azdz"
									class="form-control" placeholder="安装位置" />
										
									
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
			<form action="deleteJzq.action"  method="post">
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
	var jzqnet=$("#add input[name=jzqnet]");
	var jzqip=$("#add input[name=jzqip]");
	var jzqport=$("#add input[name=jzqport]");
	var azdz=$("#add input[name=azdz]");
	
		 if(jzqnet.val()==null||jzqnet.val()==""||jzqip.val()==null||jzqip.val()==""||jzqport.val()==null||jzqport.val()==""||azdz.val()==null||azdz.val()==""){
			 alert('信息不能为空，请填写完整!');
			return false;
		} 
		
		 var jzqport1=document.getElementById("add_jzqport").value;
		 if(isNaN(jzqport1)){
				
				 alert('集中器端口号必须是数字！');
				document.getElementById("add_jzqport").value="";
				return;
			} 
		$("#add form").submit();
	}

//修改时数据不允许为空
 function edit(){
	var jzqnet=$("#edit input[name=jzqnet]");
	var jzqip=$("#edit input[name=jzqip]");
	var jzqport=$("#edit input[name=jzqport]");
	var azdz=$("#edit input[name=azdz]");
		 if(jzqnet.val()==null||jzqnet.val()==""||jzqip.val()==null||jzqip.val()==""||jzqport.val()==null||jzqport.val()==""||azdz.val()==null||azdz.val()==""){
			 alert('信息不能为空，请填写完整!');
			return false;
		} 
		 var jzqnet2=document.getElementById("edit_jzqnet").value;
		 if(isNaN(jzqnet2)){
				
				 alert('集中器net必须是数字！');
				document.getElementById("edit_jzqnet").value="";
				return;
			} 
		 var jzqport2=document.getElementById("edit_jzqport").value;
		 if(isNaN(jzqport2)){+
				
				 Alert('集中器端口号必须是数字！');
				document.getElementById("edit_jzqport").value="";
				return;
			} 

		$("#edit form").submit();
	} 

//修改
function openEditUserPage(){
	var ckbs=$("#jzqinfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		alert("请选择要修改的信息");
		return false;
	}
	if(ckbs.length>1){
		alert("对不起一次只能修改一条信息，您选择了"+ckbs.length+"条信息");
		return false;
	}
	var id=ckbs.val();
	var jzqnet=ckbs.parent().next().text();
	
	var jzqip=ckbs.parent().next().next().text();
	var jzqport=ckbs.parent().next().next().next().text();
	
	var azdz=ckbs.parent().next().next().next().next().text();
	var remark=ckbs.parent().next().next().next().next().next().next().text();
	
	$("#edit_id").val(id);
	$("#edit_jzqnet").val(jzqnet);
	$("#edit_jzqip").val(jzqip);
	$("#edit_jzqport").val(jzqport);
	$("#edit_azdz").val(azdz);
	$("#edit").modal({keyboard:false}); 
}
 
//删除
function openDeletePage(){
		var ckbs=$("#jzqinfo input[type=checkbox]:checked");
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
		$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"个集中器吗？<br/>"+"</span></h4>");
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
				url :"deleteJzq.action?id="+id,
				type : "post",
				success : function() {
					$("#deleteUser").modal("hide");
					window.location.href="JzqfindList.action";
				}
			});		
		});
		$("#deleteUser").modal({keyboard:false});
	}
 
</script>
</html>