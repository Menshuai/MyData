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
	 <script type="text/javascript">
	//全有风盘发送-----------------------------所有风盘发送-------------------------
	function Zx() {
		var kg = $('#kg').val();
		var jf = $('#jf').val();
		var jj = $('#jj').val();
		var ckbs = $("#fixed_hdr2 input[type=checkbox]:checked");
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
				url : "/Data/Cg/SCxZx.action",
				async : false,
				dataType : "json",
				data : {
					"ids" : apiContentStr,
					"kg" : kg,
					"jf" : jf,
					"jj" : jj,
				},
				success : function(data) {
					msg = data.js
					if (msg == "3") {
						alert("不在线!");
					}
					if (msg == "2") {
						alert("成功!");
					}
					if (msg == "0") {
						alert("失败!");
					}
					location.reload()

				}

			});
		}
	}
	 
	function FsLd() {
		var kg = $('#kg').val();
		var jf = $('#jf').val();
		var jj = $('#jj').val();
		var ckbs = $("#fixed_hdr2 input[type=checkbox]:checked");
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
				url : "/Data/Cg/FsLd.action",
				async : false,
				dataType : "json",
				data : {
					"ids" : apiContentStr,
					"kg" : kg,
					"jf" : jf,
					"jj" : jj,
				},
				success : function(data) {
					msg = data.js
					if (msg == "3") {
						alert("不在线!");
					}
					if (msg == "2") {
						alert("成功!");
					}
					if (msg == "0") {
						alert("失败!");
					}
					location.reload()

				}

			});
		}
	}
	
</script>
	
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 600,
			colModal: [
				{ width: 50, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 120, align: 'center' }
				
				 
			 
				/* { width: 120, align: 'center' } */
			],
			sort: true
		});
		
	});
	</script>
</head>
<body>
 <div class="panel panel-success">
 <div class="panel-heading">层管信息</div>
 <div class="panel-body">
<button type="button" class="btn btn-success" data-toggle="modal" data-target="#add" style="background: url(../img/secai.png);">添加</button>&nbsp;&nbsp;
   <button type="button" class="btn btn-success"
				onClick="openEditUserPage()" style="background: url(../img/secai.png);">修改</button>&nbsp;&nbsp;
<button type="button" class="btn btn-success" onClick="openDeletePage()" style="background: url(../img/secai.png);">删除</button>
			<hr />
			
<!-- <button type="button" class="btn btn-success" onclick="kFm()" style="background: url(../img/secai.png);">查询状态</button> -->
 
		选择开关：
	<select id="kg">
		<option value="02">--是否开关--</option>
		<option value="01">自动运行</option>
		<option value="00">强制关闭</option>
	</select>
	&nbsp;&nbsp;&nbsp;
	
	 选择是否计费：
	<select id="jf">
		<option value="02">--是否计费--</option>
		<option value="01">允许计费</option>
		<option value="00">禁止计费</option>
	</select>
	&nbsp;&nbsp;&nbsp;
	
	季节：
	<select id="jj">
		<option value="02">--选择季节--</option>
		<option value="01">夏季</option>
		<option value="00">冬季</option>
	</select>  
	&nbsp;&nbsp;&nbsp;
	<button type="button" class="btn btn-success" onclick="Zx()" style="background: url(../img/secai.png);" >所有某层风盘发送</button>
	<button type="button" class="btn btn-success" onclick="FsLd()" style="background: url(../img/secai.png);" >对单元风盘发送</button>
 <div></div>
 &nbsp; &nbsp; &nbsp;
<form>
	<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
		<tr height="35px" style="background: url(../img/secai.png);">
		<th></th>
			<!-- <th>用户编号</th> -->
			 <th>层管编号</th>
		<!-- 	<th>小区名</th>
			<th>楼栋号</th>
			
			<th>单元号</th>
			<th>户号</th> -->
		<!-- 	<th>风盘个数</th>
			<th>面积</th> -->
		<!-- 	<th>用户姓名</th> -->
			
			<th>集中器net</th>
			<th>安装地址</th>
			<th>备注</th>
			<!-- <th>联系电话</th> -->
		</tr>
	</thead>
				<tbody id="cginfo">
					<c:forEach  var="cg" items="${cg}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
								<%-- 	<td ><input type="checkbox" value="${cg.yhMessage.yhbh}" /></td> --%>
									<td ><input type="checkbox" value="${cg.cgbh}" /></td>
								<%-- 	<td>${cg.yhMessage.yhbh}</td> --%>
									<td>${cg.cgbh}</td>
                             <%--         <td>${cg.yhMessage.xqm}</td>
                                     <td>${cg.yhMessage.cgbh}</td>
                                     
                                     <td>${cg.yhMessage.ldh}</td>
                                     <td>${cg.yhMessage.hh}</td> --%>
                                   <%--   <td>${cg.yhMessage.fpgs}</td>
                                     <td>${cg.yhMessage.mj}</td> --%>
                                  <%--    <td>${cg.yhMessage.yhxm}</td> --%>
                                     
                                     <td>${cg.jzqnet}</td>
                                     <td>${cg.azdz}</td>
                                     <td>${cg.bz}</td>
                                  <%--    <td>${cg.yhMessage.lxdh}</td> --%>
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
					<h4 class="text-center" style="padding: 0; margin: 0;">添加</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="InsertCg.action" method="post">
						
						<div class="form-group">
							<label for="add_cgbh"
								class="col-sm-2 col-sm-offset-3 control-label">层管编号</label>
							<div class="col-sm-4">
								<input id="add_cgbh" type="text" name="cgbh"
									class="form-control" placeholder="层管编号" />
							</div>
						</div>							
								
						<div class="form-group">
							<label for="add_jzqnet"
								class="col-sm-2 col-sm-offset-3 control-label">集中器net</label>
							<div class="col-sm-4">
								<input id="add_jzqnet" type="text" name="jzqnet"
									class="form-control" placeholder="集中器net" />
							</div>
						</div>
						 
						
						<div class="form-group">
							<label for="add_azdz"
								class="col-sm-2 col-sm-offset-3 control-label">安装位置</label>
							<div class="col-sm-4">
								<input id="add_azdz" type="text" name="azdz"
									class="form-control" placeholder="安装位置" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="add_bz"
								class="col-sm-2 col-sm-offset-3 control-label">备注</label>
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
					<form class="form form-horizontal" action="updateCg.action" method="post">
					 <input type="hidden" id="edit_id" name="id" />
						<div class="form-group">
							<label for="edit_cgbh"
								class="col-sm-2 col-sm-offset-3 control-label">层管编号</label>
							<div class="col-sm-4">
								<input id="edit_cgbh" type="text" name="cgbh"
									class="form-control"  placeholder="层管编号" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit_jzqnet"
								class="col-sm-2 col-sm-offset-3 control-label">集中器net</label>
							<div class="col-sm-4">
								<input id="edit_jzqnet" type="text" name="jzqnet"
									class="form-control" placeholder="集中器net" />
							</div>
						</div>
						 
								<div class="form-group">
							<label for="edit_azdz"
								class="col-sm-2 col-sm-offset-3 control-label">安装地址</label>
							<div class="col-sm-4">
								<input id="edit_azdz" type="text" name="azdz"
									class="form-control" placeholder="安装地址" />
							</div>
						</div>
							
						<div class="form-group">
							<label for="edit_bz"
								class="col-sm-2 col-sm-offset-3 control-label">备注</label>
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
<!-- Bootstrap -->
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript">


//添加时数据不能为空
function add(){
	var cgbh=$("#add input[name=cgbh]");
	var jzqnet=$("#add input[name=jzqnet]");
	
	var azdz=$("#add input[name=azdz]");
	var bz=$("#add input[name=bz]");
	
		 if(cgbh.val()==null||cgbh.val()==""||jzqnet.val()==null||jzqnet.val()==""||azdz.val()==null||azdz.val()==""||bz.val()==null||bz.val()==""){
			 sAlert('信息不能为空，请填写完整!');
			return false;
		} 
		 var cgbh1=document.getElementById("add_cgbh").value;
		 if(isNaN(cgbh1)){
				
				 sAlert('本地端口号必须是数字！');
				document.getElementById("add_cgbh").value="";
				return;
			} 
		/*  var jzqPort1=document.getElementById("add_jzqPort").value;
		 if(isNaN(jzqPort1)){
				
				 sAlert('集中器端口号必须是数字！');
				document.getElementById("add_jzqPort").value="";
				return;
			}  */
		$("#add form").submit();
	}

//修改时数据不允许为空
 function edit(){
	var cgbh=$("#edit input[name=cgbh]");
	var jzqnet=$("#edit input[name=jzqnet]");
	var azdz=$("#edit input[name=azdz]");
	var bz=$("#edit input[name=bz]");
		 if(cgbh.val()==null||cgbh.val()==""||jzqnet.val()==null||jzqnet.val()==""||azdz.val()==null||azdz.val()==""||bz.val()==null||bz.val()==""){
			  sAlert('信息不能为空，请填写完整!');
			return false;
		} 
		 var cgbh2=document.getElementById("edit_cgbh").value;
		 if(isNaN(cgbh2)){
				 sAlert('本地端口号必须是数字！');
				document.getElementById("edit_cgbh").value="";
				return;
			} 
		 var jzqnet2=document.getElementById("edit_jzqnet").value;
		 if(isNaN(jzqnet2)){
				 sAlert('集中器端口号必须是数字！');
				document.getElementById("edit_jzqnet").value="";
				return;
			} 
		$("#edit form").submit();
	} 

//修改
function openEditUserPage(){
	var ckbs=$("#cginfo input[type=checkbox]:checked");
	if(ckbs.length==0){
		alert("请选择要修改的信息");
		return false;
	}
	if(ckbs.length>1){
		alert("对不起一次只能修改一条信息，您选择了"+ckbs.length+"条信息");
		return false;
	}
	var id=ckbs.val();
	var cgbh=ckbs.parent().next().text();
	var jzqnet=ckbs.parent().next().next().text();
	var azdz=ckbs.parent().next().next().next().text();
	var bz=ckbs.parent().next().next().next().next().text();
	$("#edit_cgbh").val(cgbh);
	$("#edit_jzqnet").val(jzqnet);
	$("#edit_azdz").val(azdz);
	$("#edit_bz").val(bz);
	$("#edit").modal({keyboard:false}); 
}
 
//删除
function openDeletePage(){
		var ckbs=$("#cginfo input[type=checkbox]:checked");
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
				url :"deleteCg.action?id="+id,
				type : "post",
				success : function() {
					$("#deleteUser").modal("hide");
					window.location.href="CgfindList.action";
				}
			});		
		});
		$("#deleteUser").modal({keyboard:false});
	}
 
</script>
</html>