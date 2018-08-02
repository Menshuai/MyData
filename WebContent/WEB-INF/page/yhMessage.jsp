<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	<script>
	$(function () {
		
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 800,
			colModal: [
			{ width: 50, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 100, align: 'center' },
			{ width: 80, align: 'center' },
			
			{ width: 80, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 90, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 80, align: 'center' },
			
			{ width: 80, align: 'center' },
			{ width: 80, align: 'center' },
			{ width: 80, align: 'center' }
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

//数据不允许为空   添加
function add(){
	var yhbh=$("#add input[name=yhbh]");
	var cgbh=$("#add input[name=cgbh]");
	var xqm=$("#add input[name=xqm]");
	var ldh=$("#add input[name=ldh]");
	var dyh=$("#add input[name=dyh]");
	
	var hh=$("#add input[name=hh]");
	var fpdz=$("#add input[name=fpdz]");
	var mj=$("#add input[name=mj]");
	var yhxm=$("#add input[name=yhxm]");
	
	var lxdh=$("#add input[name=lxdh]");
	var bz=$("#add input[name=bz]");
	var yhlx = $("input[name='yhlx']:checked").val();
	alert(yhlx)

 if(yhbh.val()==null||yhbh.val()==""||cgbh.val()==null||cgbh.val()==""||xqm.val()==null||xqm.val()==""||ldh.val()==null||ldh.val()==""||dyh.val()==null||dyh.val()==""||hh.val()==null||hh.val()==""||fpdz.val()==null||fpdz.val()==""||mj.val()==null||mj.val()==""||yhlx==null||yhlx==""||yhxm.val()==null||yhxm.val()==""||lxdh.val()==null||lxdh.val()==""||bz.val()==null||bz.val()==""){
		 alert('信息不能为空，请填写完整!');
		return false;
	} 
	var fpgs=document.getElementById("fpgs").value;
	 if(!isNaN(fpdz)){
			 sAlert('风盘个数必须是数字！');
			document.getElementById("fpgs").value="";
			return;
	 } 
	$("#add form").submit();
}

//修改时数据不能为空
function edit(){
	var yhbh=$("#add input[name=yhbh]");
	var cgbh=$("#add select[name=cgbh]");
	var xqm=$("#add select[name=xqm]");
	var ldh=$("#add select[name=ldh]");
	var dyh=$("#add input[name=dyh]");
	
	var hh=$("#add input[name=hh]");
	var fpdz=$("#add input[name=fpdz]");
	var mj=$("#add input[name=mj]");
	var yhlx=$("#add input[name=yhlx]");
	var yhxm=$("#add input[name=yhxm]");
	
	var lxdh=$("#add input[name=lxdh]");
	var bz=$("#add input[name=bz]");
	/* 
	if(yhbh.val()==null||yhbh.val()==""||cgbh.val()==null||cgbh.val()==""||
			xqm.val()==null||xqm.val()==""||ldh.val()==null||ldh.val()==""||
			dyh.val()==null||dyh.val()==""||hh.val()==null||hh.val()==""||
			fpdz.val()==null||fpdz.val()==""||mj.val()==null||mj.val()==""||
			yhlx.val()==null||yhlx.val()==""||yhxm.val()==null||yhxm.val()==""||
			lxdh.val()==null||lxdh.val()==""||bz.val()==null||bz.val()==""){
		 alert('信息不能为空，请填写完整!');
		return false;
	} */
	var dyh2=document.getElementById("edit_dyh").value;
	 if(isNaN(dyh2)){
			
			 alert('单元号必须是数字！');
			document.getElementById("edit_dyh").value="";
			return;
	 }

	$("#edit form").submit();
}


  function openaddUserPage(){
	$.ajax({
		type:"post",
		url:"findfp.action",//获取json数据
		dataType:"json",
		success:function(data){
			var dd=data;
			var d=dd.fps;
			$("#fpxhId option:gt(0)").remove();
			for(var i=0;i<d.length;i++){
				var xh=d[i].xh;
				var opt=xh+": <input type='radio' name='fpxh' value='"+xh+"'/>";
				$("#fpxhId").append(opt);
				
			}
		},
	})
	$("#add").modal({keyboard:false});
	
}  
function openEditUserPage(){
	var ckbs=$("#users input[type=checkbox]:checked");
	if(ckbs.length==0){
		sAlert("请选择要修改的用户");
		return false;
	}
	if(ckbs.length>1){
		sAlert("对不起一次只能修改一个用户，您选择了"+ckbs.length+"个用户");
		return false;
	}
	var id=ckbs.val();
	var yhbh=ckbs.parent().next().text();
	var cgbh=ckbs.parent().next().next().text();
	var xqm=ckbs.parent().next().next().next().text();
	var ldh=ckbs.parent().next().next().next().next().text();
	
	var dyh=ckbs.parent().next().next().next().next().next().text();
	var hh=ckbs.parent().next().next().next().next().next().next().text();
	var fpdz=ckbs.parent().next().next().next().next().next().next().next().text();
	var mj=ckbs.parent().next().next().next().next().next().next().next().next().text();
	var yhlx=ckbs.parent().next().next().next().next().next().next().next().next().next().text(); 
	
	var yhxm=ckbs.parent().next().next().next().next().next().next().next().next().next().next().text(); 
	var lxdh=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().text(); 
	var bz=ckbs.parent().next().next().next().next().next().next().next().next().next().next().next().next().text(); 
	$("#edit_id").val(id);
	$("#edit_yhbh").val(yhbh);
	$("#edit_cgbh").val(cgbh);
	$("#edit_xqm").val(xqm);
	$("#edit_ldh").val(ldh);
	$("#edit_dyh").val(dyh);
	
	$("#edit_hh").val(hh);
	$("#edit_fpdz").val(fpdz);
	$("#edit_mj").val(mj);
	$("#edit_yhlx").val(yhlx);
	
	$("#edit_yhxm").val(yhxm);
	$("#edit_lxdh").val(lxdh);
	$("#edit_bz").val(bz);
	
	$("#edit").modal({keyboard:false});
}
////删除
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
				url :"deleteYhMessage.action?id="+id,
				type : "post",
				success : function() {
					$("#deleteUser").modal("hide");
					window.location.href="yhfindList.action";
				}
			});		
		});
		
		$("#deleteUser").modal({keyboard:false});
	}
</script>
</head>
<body>
 <div class="panel panel-success">
 <div class="panel-heading">用户信息</div>
 <div class="panel-body">
 <div id="top">
  <button type="button" class="btn btn-success"    onClick="openaddUserPage()" style="background: url(../img/secai.png);">添加</button>&nbsp;&nbsp;
   <button type="button" class="btn btn-success" onClick="openEditUserPage()" style="background: url(../img/secai.png);">修改</button>&nbsp;&nbsp;
	<button type="button" class="btn btn-success" onClick="openDeletePage()" style="background: url(../img/secai.png);">删除</button>
 
 </div> 
			 <div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr  height="35px" style="background: url(../img/secai.png);">
						<th></th>
						<th>用户编号</th>
						<th>层管编号</th>
						<th>小区名</th>
						<th>楼栋号</th>
						
						<th>单元号</th>
						<th>户号</th>
						<th>风盘地址</th>
						<th>面积</th>
						<th>用户类型</th>
						
						
						<th>用户姓名</th>
						<th>联系电话</th>
						<th>备注</th>
						
					</tr>
				</thead>
				<tbody id="users">
					<c:forEach  var="yh" items="${yhMessage}" varStatus="status">
								<tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
									<td><input type="checkbox" value="${yh.id}" /></td>
                                     <td>${yh.yhbh}</td>
                                     <td>${yh.cgbh}</td>
                                      <td>${yh.xqm}</td>
                                     <td>${yh.ldh}</td>
                                     <td>${yh.dyh}</td>
                                     <td>${yh.hh}</td>
                                     <td>${yh.fpdz}</td>
                                     <td>${yh.mj}</td>
                                     <td>${yh.yhlx}</td>
                                     <td>${yh.yhxm}</td>
                                     <td>${yh.lxdh}</td>
                                      <td>无</td>
                                     
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
					<form class="form form-horizontal" action="InsertYhMessage.action" method="post">
						<div class="form-group">						
							<label for="yhbh" class="col-sm-2 col-sm-offset-3 control-label">用户编号</label>
							
							<div class="col-sm-4">
								<input id="yhbh" type="text" name="yhbh"
									class="form-control" placeholder="用户编号" />
							</div>					
						</div>
						
						<div class="form-group">						
							<label for="cgbh" class="col-sm-2 col-sm-offset-3 control-label">层管编号</label>
							
							<div class="col-sm-4">
								<input id="cgbh" type="text" name="cgbh"
									class="form-control" placeholder="层管编号" />
							</div>					
						</div>
						
						<div class="form-group" >
						<label for="xqm" class="col-sm-2 col-sm-offset-3 control-label">小区名</label>
						
						<div class="col-sm-4">
								<input id="xqm" type="text" name="xqm"
									class="form-control" placeholder="小区名" />
							</div>	
						</div>
						
						<div class="form-group">
						<label for="ldh" class="col-sm-2 col-sm-offset-3 control-label">楼栋号</label> 
					
						<div class="col-sm-4">
								<input id="ldh" type="text" name="ldh"
									class="form-control" placeholder="楼栋号" />
							</div>	
						</div>	
						
											
						<div class="form-group">						
							<label for="dyh" class="col-sm-2 col-sm-offset-3 control-label">单元号</label>
							
							<div class="col-sm-4">
								<input id="dyh" type="text" name="dyh"
									class="form-control" placeholder="单元号" />
							</div>					
						</div>
						<div class="form-group">						
							<label for="hh" class="col-sm-2 col-sm-offset-3 control-label">户号</label>
							
							<div class="col-sm-4">
								<input id="hh" type="text" name="hh"
									class="form-control" placeholder="户号" />
							</div>					
						</div>
												
						
							  <div class="form-group">						
							<label for="fpdz" class="col-sm-2 col-sm-offset-3 control-label">风盘个数</label>
							
							<div class="col-sm-4">
								<input id="fpgs" type="text" name="fpdz"
									class="form-control" placeholder="风盘个数" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="mj" class="col-sm-2 col-sm-offset-3 control-label">面 &nbsp;&nbsp;&nbsp;积</label>
							
							<div class="col-sm-4">
								<input id="mj" type="text" name="mj"
									class="form-control" placeholder="面积" />
							</div>					
						</div> 
						
							
							<div class="form-group">						
							<label for="yhlx" class="col-sm-2 col-sm-offset-3 control-label">用户类型</label>
							
							<div class="col-sm-4">
								<!-- <input id="yhlx" type="text" name="yhlx"
									class="form-control" placeholder="用户类型" /> -->
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;住宅：<input type="radio" name="yhlx" value="01"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商用    ：<input type="radio" name="yhlx" value="02"/>
							      &nbsp;廉租房： <input type="radio" name="yhlx" value="03" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他： <input type="radio" name="yhlx" value="04" />
							</div>					
						</div>
						<!-- 风盘信息添加 -->
						<div class="form-group">						
							<label for="fpbh" class="col-sm-2 col-sm-offset-3 control-label">风盘编号</label>
							
							<div class="col-sm-4">
								<input id="fpbh" type="text" name="fpbh"
									class="form-control" placeholder="风盘编号" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="fpxhId" class="col-sm-2 col-sm-offset-3 control-label">风盘型号</label>
								<div class="col-sm-4" id="fpxhId">
							</div>	
							</div>	
							<div class="form-group">						
							<label for="wz" class="col-sm-2 col-sm-offset-3 control-label">风盘安装位置</label>
							
							<div class="col-sm-4">
								<input id="wz" type="text" name="wz"
									class="form-control" placeholder="风盘安装位置" />
							</div>					
						</div>	
							
							<div class="form-group">						
							<label for="yhxm" class="col-sm-2 col-sm-offset-3 control-label">用户姓名</label>
							
							<div class="col-sm-4">
								<input id="yhxm" type="text" name="yhxm"
									class="form-control" placeholder="用户姓名" />
							</div>					
						</div>
						<div class="form-group">						
							<label for="lxdh" class="col-sm-2 col-sm-offset-3 control-label">联系电话</label>
							
							<div class="col-sm-4">
								<input id="lxdh" type="text" name="lxdh"
									class="form-control" placeholder="联系电话" />
							</div>					
						</div>
					   <div class="form-group">						
							<label for="fpbz" class="col-sm-2 col-sm-offset-3 control-label">风盘备注</label>
							
							<div class="col-sm-4">
								<input id="fpbz" type="text" name="fpbz"
									class="form-control" placeholder="风盘备注" />
							</div>					
						</div>
						<div class="form-group">						
							<label for="bz" class="col-sm-2 col-sm-offset-3 control-label">用户备注</label>
							
							<div class="col-sm-4">
								<input id="bz" type="text" name="bz"
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
					<h4 class="text-center" style="padding: 0; margin: 0;">修改业主信息</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" action="updateYhMessage.action" method="post">
					 <input type="hidden" id="edit_id" name="id" />
						
						<div class="form-group">						
							<label for="edit_yhbh" class="col-sm-2 col-sm-offset-3 control-label">用户编号</label>
							
							<div class="col-sm-4">
								<input id="edit_yhbh" type="text" name="yhbh"
									class="form-control" placeholder="用户编号"   />
							</div>					
						</div>
						
						
						
						<div class="form-group">						
							<label for="edit_cgbh" class="col-sm-2 col-sm-offset-3 control-label">层管编号</label>
							
							<div class="col-sm-4">
								<input id="edit_cgbh" type="text" name="cgbh"
									class="form-control" placeholder="层管编号"  />
							</div>					
						</div>
						
						<div class="form-group">
							<label for="edit_xqm" class="col-sm-2 col-sm-offset-3 control-label">小区名</label>
							<div class="col-sm-4">
								<input id="edit_xqm" type="text" name="xqm"
									class="form-control" placeholder="小区名"  />
							</div>
						</div>
						<div class="form-group">
							<label for="edit_ldh" class="col-sm-2 col-sm-offset-3 control-label">楼栋号</label>
							<div class="col-sm-4">
								<input id="edit_ldh" type="text" name="ldh"
									class="form-control" placeholder="楼栋号"  />
							</div>
						</div>
							<div class="form-group">						
							<label for="edit_dyh" class="col-sm-2 col-sm-offset-3 control-label">单元号</label>
							
							<div class="col-sm-4">
								<input id="edit_dyh" type="text" name="dyh"
									class="form-control" placeholder="单元号"  />
							</div>					
						</div>
						<div class="form-group">						
							<label for="edit_hh" class="col-sm-2 col-sm-offset-3 control-label">户号</label>
							
							<div class="col-sm-4">
								<input id="edit_hh" type="text" name="hh"
									class="form-control" placeholder="户号" />
							</div>					
						</div>
												
						
							<div class="form-group">						
							<label for="edit_fpdz" class="col-sm-2 col-sm-offset-3 control-label">风盘地址</label>
							
							<div class="col-sm-4">
								<input id="edit_fpdz" type="text" name="fpdz"
									class="form-control" placeholder="风盘地址" />
							</div>					
						</div>
							<div class="form-group">						
							<label for="edit_mj" class="col-sm-2 col-sm-offset-3 control-label">面积</label>
							
							<div class="col-sm-4">
								<input id="edit_mj" type="text" name="mj"
									class="form-control" placeholder="面积" />
							</div>					
						</div>
						
							 
							<div class="form-group">						
							<label for="edit_yhlx" class="col-sm-2 col-sm-offset-3 control-label">用户类型</label>
							
							<div class="col-sm-4">
								<input id="edit_yhlx" type="text" name="yhlx"
									class="form-control" placeholder="用户类型" />
							</div>					
						</div>
							
							<div class="form-group">						
							<label for="edit_yhxm" class="col-sm-2 col-sm-offset-3 control-label">用户姓名</label>
							
							<div class="col-sm-4">
								<input id="edit_yhxm" type="text" name="yhxm"
									class="form-control" placeholder="用户姓名" />
							</div>					
						</div>
						<div class="form-group">						
							<label for="edit_lxdh" class="col-sm-2 col-sm-offset-3 control-label">联系电话</label>
							
							<div class="col-sm-4">
								<input id="edit_lxdh" type="text" name="lxdh"
									class="form-control" placeholder="联系电话" />
							</div>					
						</div>
			 
						<div class="form-group">						
							<label for="edit_bz" class="col-sm-2 col-sm-offset-3 control-label">备注</label>
							
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