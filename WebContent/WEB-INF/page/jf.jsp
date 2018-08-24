<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>用户缴费</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet" media="all" />
	<script src="https://code.jquery.com/jquery.min.js" type="text/javascript"></script>
	<script src="https://meetselva.github.io/fixed-table-rows-cols/js/sortable_table.js" type="text/javascript"></script>
	<script src="../js/fixed_table_rc.js" type="text/javascript"></script>
	<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
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
			height: 700,
			colModal: [
			{ width: 30, align: 'center' },
			{ width: 120, align: 'center' },
			{ width: 120, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 65, align: 'center' },
			{ width: 65, align: 'center' },
			{ width: 65, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 70, align: 'center' },
			{ width: 200, align: 'center' },
			],
			sort: true
		});
		
	});
	</script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js">
</script>
<script src="../js/main.js"></script>
<script type="text/javascript">

//数据不允许为空

function add(){
	var yhm=$("#add input[name=yhm]");
	var xqm=$("#add select[name=xqm]");
	var ldh=$("#add select[name=ldh]");
	var dyh=$("#add select[name=dyh]");
	var hh=$("#add input[name=hh]");
	var hh=$("#add input[name=hh]");	
	if(yhm.val()==null||yhm.val()==""||xqm.val()==null||xqm.val()==""||ldh.val()==null||ldh.val()==""||dyh.val()==null||dyh.val()==""||hh.val()==null||hh.val()==""){
		 sAlert('信息不能为空，请填写完整!');
		return false;
	}
	var hh=document.getElementById("hh").value;
	var yhbh=document.getElementById("yhbh").value;
	var jfje=document.getElementById("jfje").value;
	var hjje=document.getElementById("hjje").value;
	 if(isNaN(hh)){
			
			 sAlert('户号必须是数字！');
			document.getElementById("hh").value="";
			return;
	 }
	 if(isNaN(jfje)){
			
		 sAlert('缴费金额必须是数字！');
		document.getElementById("hh").value="";
		return;
     }
	 if(isNaN(hjje)){
			
		 sAlert('合计金额必须是数字！');
		document.getElementById("jfje").value="";
		return;
     }
	 if(isNaN(yhbh)){
			
		sAlert('用户编号必须是数字！并且为八位数');
		document.getElementById("hjje").value="";
		return;
    }
	if(yhbh.length!=8){
		sAlert('用户编号必须是数字！并且为八位数');
		document.getElementById("hjje").value="";
		return;
	}
	$("#add form").submit();
}

//修改时数据不能为空
function edit(){
	var yhbh=document.getElementById("edit_yhbh").value;
	var jfje=document.getElementById("jfjeId").value;
	var hjje=document.getElementById("edit_hjje").value;
	var syje=document.getElementById("edit_syje").value;
	var yyje=document.getElementById("edit_yyje").value;
	if(jfje==null&&jfje==""){
		 sAlert('信息不能为空，请填写完整!');
		return false;
	}
	  if(isNaN(jfje)){
			
			 sAlert('缴费金额必须是数字！');
			document.getElementById("edit_houseNo").value="";
			return;
	 }
	$.ajax({
		type:"post",
		url:"update.action",//获取json数据
		dataType:"json",
		data : {
			"yhbh" : yhbh,
			"jfje" : jfje,
			"hjje" : hjje,
			"syje" : syje,
			"yyje" : yyje,
			"jfje" : jfje,
		},
		success:function(data){
			debugger;
			var d=data.fail;
			if(d=="fail"){
			alert(d);
			}
			searchInfo();
		},
	})
	$("#edit").modal("hide");
	/* $("#edit form").submit(); */
}


function openaddUserPage(){
	$.ajax({
		type:"post",
		url:"findYhNameList.action",//获取json数据
		dataType:"json",
		success:function(data){
			var dd=data;
			var d=dd.xqlist;
			$("#xqmId option:gt(0)").remove();
			for(var i=0;i<d.length;i++){
				var xqm=d[i].xqm;
				var opt="<option value='"+xqm+"'>"+xqm+"</option>";
				$("#xqmIdS").append(opt);
				
			}
		},
	})
	
     $("#xqmIdS").change(
						function() {
							$.get("findYhldhbyxqm.action?xqm="
									+ $("#xqmIdS").val(), function(data) {
								var dd = data;
								var d = dd.xqlist;
							 	$("#ldhId option:gt(0)").remove();
								$("#dyhId option:gt(0)").remove();
								for (var i = 0; i < d.length; i++) {
									var ldh = d[i].ldh;
									var opt = "<option value='"+ldh+"'>"
											+ ldh + "</option>"
									$("#ldhIdS").append(opt);
								}
							});
						});

				$("#ldhIdS").change(//根据小区楼栋号获取  单元号
						function() {
							$.get("findYhdyhByBuild.action?ldh="
									+ $("#ldhIdS").val() + "&xqm="
									+ $("#xqmIdS").val(), function(data) {
								var dd = data;
								var d = dd.dyhList;
								$("#dyhId option:gt(0)").remove();
								for (var i = 0; i < d.length; i++) {
									var dyh = d[i].dyh;
									var opt = "<option value='"+dyh+"'>"
											+ dyh + "</option>"
									$("#dyhIdS").append(opt);
								}
							});
						});
	$("#add").modal({keyboard:false});
	
}
function openEditUserPage(){
	var ckbs=$("#users input[type=checkbox]:checked");
	if(ckbs.length==0){
		sAlert("请选择要缴费的用户");
		return false;
	}
	if(ckbs.length>1){
		sAlert("对不起一次只能选择一个用户，您选择了"+ckbs.length+"个用户");
		return false;
	}
	var id=ckbs.val();
	var yhName=ckbs.parent().next().text();
	var xqName=ckbs.parent().next().next().text();
	var buildNo=ckbs.parent().next().next().next().text();
	var cellNo=ckbs.parent().next().next().next().next().text();
	var houseNo=ckbs.parent().next().next().next().next().next().text();
	var yhbh=ckbs.parent().next().next().next().next().next().next().text();
	var lxdz=ckbs.parent().next().next().next().next().next().next().next().text();
	var yyje=ckbs.parent().next().next().next().next().next().next().next().next().next().text();
	var syje=ckbs.parent().next().next().next().next().next().next().next().next().next().next().text();
	var hjje=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().text();
	$("#edit_yhName").val(yhName);
	$("#edit_xqName").val(xqName);
	$("#edit_buildNo").val(buildNo);
	$("#edit_cellNo").val(cellNo);
	$("#edit_houseNo").val(houseNo);
	$("#edit_yhbh").val(yhbh);
	$("#edit_lxdz").val(lxdz);
	$("#edit_hjje").val(hjje);
	$("#edit_yyje").val(yyje);
	$("#edit_syje").val(syje);
	$("#edit").modal({keyboard:false});
}
//删除
function openDeletePage(){
		var ckbs=$("#users input[type=checkbox]:checked");
		if(ckbs.length==0){
			sAlert("请选择要删除的用户");
			return false;
		}
		if(ckbs.length>1){
			 sAlert('对不起一次只能删除一个');
			return false;
		}
		var id=[];
		$.each(ckbs,function(index,data){
			id[index]=$(data).val();
		});
		var names=ckbs.parent().next();
		var deleteUserNames=[];
		$("#deleteUser .modal-body .col-sm-7").empty();
		$("#deleteUser .modal-body .col-sm-7").append("<h4><span class='text-danger'>你确定要删除下面这"+ckbs.length+"个用户吗？<br/>"+"</span></h4>");
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
					window.location.href="JFfindList.action";
				}
			});		
		});
		
		$("#deleteUser").modal({keyboard:false});
	}
</script>

 <script type="text/javascript">
	/*页面加载就开始执行js*/
	$(document).ready(//根据小区获取  楼栋号
			function() {
				$("#xqmId").change(
						function() {
							$.get("findYhldhbyxqm.action?xqm="
									+ $("#xqmId").val(), function(data) {
								var dd = data;
								var d = dd.xqlist;
							 	$("#ldhId option:gt(0)").remove();
								$("#dyhId option:gt(0)").remove();
								for (var i = 0; i < d.length; i++) {
									var ldh = d[i].ldh;
									var opt = "<option value='"+ldh+"'>"
											+ ldh + "</option>"
									$("#ldhId").append(opt);
								}
							});
						});

				$("#ldhId").change(//根据小区楼栋号获取  单元号
						function() {
							$.get("findYhdyhByBuild.action?ldh="
									+ $("#ldhId").val() + "&xqm="
									+ $("#xqmId").val(), function(data) {
								var dd = data;
								var d = dd.dyhList;
								$("#dyhId option:gt(0)").remove();
								for (var i = 0; i < d.length; i++) {
									var dyh = d[i].dyh;
									var opt = "<option value='"+dyh+"'>"
											+ dyh + "</option>"
									$("#dyhId").append(opt);
								}
							});
						});
			});
</script>
<script type="text/javascript">
function searchInfo() {
	var xqm = $('#xqmId').val();
	var ldh = $('#ldhId').val();
	var dyh = $('#dyhId').val();
	var hh = $('#hhId').val();
	var html = "";
	$.ajax({
		url : "Search.action",
		async : false,
		dataType : "json",
		data : {
			"xqm" : xqm,
			"ldh" : ldh,
			"dyh" : dyh,
			"hh" : hh,
		},
		success : function(data) {
			$("#users").empty();
			var d = data.jfs;
			for (var i = 0; i < d.length; i++) {
				var id=d[i].id;
				var yhxm = d[i].yhMessage.yhxm;
				var xqm = d[i].yhMessage.xqm;
				var ldh = d[i].yhMessage.ldh;
				var dyh = d[i].yhMessage.dyh;
				var hh = d[i].yhMessage.hh;
				var yhbh = d[i].yhbh;
				var lxdh = d[i].yhMessage.lxdh;
				var jfje = d[i].jfje;
				var yyje = d[i].yyje;
				var syje = d[i].syje;
				var hjje = d[i].hjje;
				var userName = d[i].userName;
				var time = FormatDate(d[i].time);
				html += "<tr>";
				html+="<td class='text-center'><input type='checkbox'  value='"+id+"'/></td>";
				html += "<td class='text-center'>" + yhxm + "</td>";
				html += "<td class='text-center'>" + xqm + "</td>";
				html += "<td class='text-center'>" + ldh + "</td>";
				html += "<td class='text-center'>" + dyh + "</td>";
				html += "<td class='text-center'>" + hh+ "</td>";
				html += "<td class='text-center'>" + yhbh + "</td>";
				html += "<td class='text-center'>" + lxdh + "</td>";
				html += "<td class='text-center'>" + jfje + "</td>";
				html += "<td class='text-center'>" + yyje + "</td>";
				html += "<td class='text-center'>" + syje + "</td>";
				html += "<td class='text-center'>" + hjje + "</td>";
				html += "<td class='text-center'>" + userName + "</td>";
				html += "<td class='text-center'>" + time + "</td>";
				html += "</tr>";
			}
			$("#users").append(html);
		}

	})
}
function FormatDate(strTime) {
	var date = new Date(strTime);
	return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
			+ date.getDate() + " " + date.getHours() + ":"
			+ date.getMinutes();
}
</script>
<script type="text/javascript">
	//导出
	function doExportExcel() {
		var xqm = $('#xqmId').val();
		var ldh = $('#ldhId').val();
		var dyh = $('#dyhId').val();
		var hh = $('#hhId').val();
		window.open("JfExportExcel.action?xqm=" + xqm + "&ldh="
				+ ldh + "&dyh=" + dyh + "&hh=" + hh);
	}
</script>
</head>
<body>
<div class="panel panel-success">
 <div class="panel-heading">业主缴费信息</div>
 <div class="panel-body">
 <div id="top">
 <label for="xqmId">选择小区</label> 
 			<select id="xqmId" name="xqm">
					<option value="--选择小区名称--">--选择小区名称--</option>
					<c:forEach items="${XqNameList}" var="list">
						<option>${list.xqm}</option>
					</c:forEach>
			</select> &nbsp;&nbsp;&nbsp; 
			
		<label for="ldhId">楼栋号</label>
			<select   name="ldh" id="ldhId">
				<option value="">--选择楼栋号--</option>
			</select> 
			&nbsp;&nbsp;&nbsp; 
			
		<label for="dyhId">单元号</label>
			<select  name="dyh" id="dyhId">
				<option value="">--选择单元号--</option>
			</select> 
		&nbsp;&nbsp;&nbsp;
		
		户号：<input type="text" name="hh" id="hhId" value="${hh}" size=10px/> 
		<!-- <label for="readMTime">选择时间:</label>
		<input type="text" id="time1" name="time1" class="Wdate" onfocus="WdatePicker();" />
			- -<input type="text" id="time2"  name="time2"  class="Wdate" onfocus="WdatePicker();" /> -->	
		<input type="button" onclick="searchInfo()" value="搜索" class="btn btn-success" style="background: url(../img/secai.png);"/>
		 <button type="button" class="btn btn-success" onClick="openaddUserPage()" style="background: url(../img/secai.png);">添加</button>&nbsp;&nbsp;
        <button type="button" class="btn btn-success" onClick="openEditUserPage()" style="background: url(../img/secai.png);">缴费</button>&nbsp;&nbsp;
		 <button type="button" class="btn btn-success" onClick="openDeletePage()" style="background: url(../img/secai.png);">删除</button>
		 <input type="button" value="导出" class="btn btn-success" onclick="doExportExcel()" style="background: url(../img/secai.png);"/>
         &nbsp;&nbsp;&nbsp;
	<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr height="35px" style="background: url(../img/secai.png);">
						<th></th>
						<th>用户名</th>
						<th>小区名称</th>
						<th>楼栋号</th>
						<th>单元号</th>
						<th>户号</th>
						<th>用户编号</th>
						<th>联系方式</th>
						<th>缴费金额</th>
						<th>已用金额</th>
						<th>剩余金额</th>
						<th>合计金额</th>
						<th>缴费人</th>
						<th>缴费时间</th>
	           </tr>
				</thead>
				<tbody id="users">
					<c:forEach  var="jf" items="${jf}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
									<td><input type="checkbox" value="${jf.id}" /></td>
                                    <td>${jf.yhMessage.yhxm}</td>
                                    <td>${jf.yhMessage.xqm}</td>
                                    <td>${jf.yhMessage.ldh}</td>
                                    <td>${jf.yhMessage.dyh}</td>
                                    <td>${jf.yhMessage.hh}</td>
                                    <td>${jf.yhbh}</td>
                                    <td>${jf.yhMessage.lxdh}</td>
                                    <td>${jf.jfje}</td>
                                    <td>${jf.yyje}</td>
                                    <td>${jf.syje}</td>
                                    <td>${jf.hjje}</td>
                                    <td>${jf.userName}</td>
                                    <td>${jf.time}</td>
					</c:forEach>
				</tbody>
			</table>
			</div>
</div>
</div>
<!-- 添加用户开始 ----------------------------------------------------------------->
<div class="modal fade" id="add">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h4 class="text-center" style="padding: 0; margin: 0;">添加用户</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="add.action" method="post">
						<div class="form-group">						
							<label for="yhm" class="col-sm-2 col-sm-offset-3 control-label">用户名</label>
							
							<div class="col-sm-4">
								<input id="yhm" type="text" name="yhm"
									class="form-control" placeholder="用户名" />
							</div>					
						</div>
												
						<div class="form-group">						
						<label for="xqmIdS" class="col-sm-2 col-sm-offset-3 control-label">小区名</label>
							
							
						<select name="xqm" id="xqmIdS">
						<option value=-1>--选择小区名--</option>
						</select>								
						</div>								
							
						<div class="form-group" >
							
						<label for="ldhS" class="col-sm-2 col-sm-offset-3 control-label">楼栋号</label>
						 <select name="ldh" id="ldhIdS">
							<option value=0>--选择楼栋号--</option>
						</select>
						</div>
						
						<div class="form-group">
						<label for="dyhS" class="col-sm-2 col-sm-offset-3 control-label">单元号</label> 
						<select name="dyh" id="dyhIdS">
							<option value=0>--选择单元号--</option>
						</select>
						</div>	
						
											
						<div class="form-group">						
							<label for="hh" class="col-sm-2 col-sm-offset-3 control-label">户号</label>
							
							<div class="col-sm-4">
								<input id="hh" type="text" name="hh"
									class="form-control" placeholder="户号" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="yhbh" class="col-sm-2 col-sm-offset-3 control-label">用户编号</label>
							
							<div class="col-sm-4">
								<input id="yhbh" type="text" name="yhbh"
									class="form-control" placeholder="用户编号" />
							</div>					
						</div>
						
							<div class="form-group">						
							<label for="lxdh" class="col-sm-2 col-sm-offset-3 control-label">联系方式</label>
							
							<div class="col-sm-4">
								<input id="lxdh" type="text" name="lxdh"
									class="form-control" placeholder="联系电话" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="jfje" class="col-sm-2 col-sm-offset-3 control-label">缴费金额</label>
							
							<div class="col-sm-4">
								<input id="jfje" type="text" name="jfje"
									class="form-control" placeholder="缴费金额" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="hjje" class="col-sm-2 col-sm-offset-3 control-label">合计金额</label>
							
							<div class="col-sm-4">
								<input id="hjje" type="text" name="hjje"
									class="form-control" placeholder="合计金额" />
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
					<h4 class="text-center" style="padding: 0; margin: 0;">修改业主信息</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="update.action" method="post">
					 <input type="hidden" id="edit_id" name="id" />
						
						<div class="form-group">						
							<label for="edit_yhName" class="col-sm-2 col-sm-offset-3 control-label">用户名</label>
							
							<div class="col-sm-4">
								<input id="edit_yhName" type="text" name="yhName"
									class="form-control" placeholder="用户名"  readonly="readonly"/>
							</div>					
						</div>
						
						
						<div class="form-group">						
							<label for="edit_xqName" class="col-sm-2 col-sm-offset-3 control-label">小区名</label>
							
							<div class="col-sm-4">
								<input id="edit_xqName" type="text" name="xqName"
									class="form-control" placeholder="小区名" readonly="readonly"/>
							</div>					
						</div>
						
						<div class="form-group">
							<label for="edit_buildNo" class="col-sm-2 col-sm-offset-3 control-label">楼栋号</label>
							<div class="col-sm-4">
								<input id="edit_buildNo" type="text" name="buildNo"
									class="form-control" placeholder="楼栋号" readonly="readonly"/>
							</div>
						</div>
						<div class="form-group">
							<label for="edit_cellNo" class="col-sm-2 col-sm-offset-3 control-label">单元号</label>
							<div class="col-sm-4">
								<input id="edit_cellNo" type="text" name="cellNo"
									class="form-control" placeholder="单元号" readonly="readonly"/>
							</div>
						</div>
						<div class="form-group">						
							<label for="edit_houseNo" class="col-sm-2 col-sm-offset-3 control-label">户号</label>
							
							<div class="col-sm-4">
								<input id="edit_houseNo" type="text" name="houseNo"
									class="form-control" placeholder="户号" readonly="readonly"/>
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_yhbh" class="col-sm-2 col-sm-offset-3 control-label">用户编号</label>
							
							<div class="col-sm-4">
								<input id="edit_yhbh" type="text" name="yhbh"
									class="form-control" placeholder="用户编号" readonly="readonly"/>
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_lxdz" class="col-sm-2 col-sm-offset-3 control-label">联系地址</label>
							
							<div class="col-sm-4">
								<input id="edit_lxdz" type="text" name="lxdz"
									class="form-control" placeholder="联系地址" readonly="readonly"/>
							</div>					
						</div>
						<div class="form-group">						
							<label for="edit_jfje" class="col-sm-2 col-sm-offset-3 control-label">缴费金额</label>
							
							<div class="col-sm-4">
								<input id="jfjeId" type="text" name="jfje"
									class="form-control" placeholder="缴费金额"/>
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_hjje" class="col-sm-2 col-sm-offset-3 control-label">合计金额</label>
							
							<div class="col-sm-4">
								<input id="edit_hjje" type="text" name="hjje"
									class="form-control" placeholder="合计金额"   readonly="readonly" />
							</div>					
						</div>
						<input id="edit_yyje" type="hidden"  name="yyje"
									class="form-control" />
						<input id="edit_syje" type="hidden"  name="syje"
									class="form-control" />
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
</div>


</body>
</html>