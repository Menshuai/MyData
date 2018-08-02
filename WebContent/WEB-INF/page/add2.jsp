<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>报修详情 添加报修单</title>
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
			font-family: Arial,??sans-serif;
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
		
		
	</style>
<link href="../css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script src="../js/main.js"></script>
</head>

   <script type="text/javascript">  
	/*页面加载就开始执行js*/
	 $(document).ready(function() {
		
		$("#xqmId").change(//小区
				function(){	
				  $.get("findYhldhbyxqm.action?xqm="
						  + $("#xqmId").val(),function(data) {
					var d=data.ldhList; 
					$("#ldhId option:gt(0)").remove();
					$("#dyhId option:gt(0)").remove();
					
					for(var i=0;i<d.length;i++){
						var ldh=d[i].ldh;
						var opt="<option value='"+ldh+"'>"
							+ldh+"</option>"
						$("#ldhId").append(opt);//楼栋
					}
					});
				});
				
		$("#ldhId").change(//楼栋
		function() {//单元方法
			$.get("findYhdyhByBuild.action?ldh="
					+ $("#ldhId").val()+"&xqm="
					+ $("#xqmId").val(),function(data) {
				var dd=data;
				var d=dd.dyhList;
				$("#dyhId option:gt(0)").remove();
				for(var i=0;i<d.length;i++){
					var dyh=d[i].dyh;
					var opt="<option value='"+dyh+"'>"+dyh+"</option>"
					$("#dyhId").append(opt);//单元
				}
				});
			 });	
		
		
  });
	</script> 
<body>
		<div id="add">
		<div >
			<div >
				<div>
					<h4 class="text-center" style="padding: 0; margin: 0;">添加安装登记单</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" id="Myform" action="InsertRepair2.action">
						
					<!-- 获取所有的小区 -->										
						<div class="form-group">						
						<label for="xqmId" class="col-sm-2 col-sm-offset-3 control-label">小区名:</label>
						<div  class="col-sm-4" >
						<select name="xqm" id="xqmId">
							<option value=0>--请选择小区--</option>
								<c:forEach items="${XqNameList}" var="list">
									<option>${list.xqm}</option>
								</c:forEach>
						</select>
						</div>
					
						</div>								
						<!-- 获取楼栋号 -->	
						<div class="form-group" >
						<label for="ldhId" class="col-sm-2 col-sm-offset-3 control-label">楼栋号:</label>
						<div class="col-sm-4">
						 <select name="ldh" id="ldhId" >
							<option value=0>--选择楼栋号--</option>
						</select>
						</div>
						</div>
						<!-- 获取楼单元号 -->	
						<div class="form-group">
						<label for="dyhId" class="col-sm-2 col-sm-offset-3 control-label">单元号:</label> 
						<div class="col-sm-4">
						<select name="dyh" id="dyhId" >
							<option value=0>--选择单元号--</option>
						</select>
						</div>
						</div>		
						<div class="form-group">						
							<label for="hhId" class="col-sm-2 col-sm-offset-3 control-label">门牌号:</label>
							
							<div class="col-sm-4">
								<input id="hhId" type="text" style="width:200px" name="hh"
									class="form-control" placeholder="hh" />
							</div>					
						</div>		
							<div class="form-group">						
							<label for="yhxmId" class="col-sm-2 col-sm-offset-3 control-label">联系人:</label>
						<div class="col-sm-4">
						<input id="yhxmId"  type="text"style="width:200px" name="yhxm" 
								class="form-control" placeholder="yhxm" >
						</div>
						</div>
						<div class="form-group">						
							<label for="lxdhId" class="col-sm-2 col-sm-offset-3 control-label">电话:</label>
						<div class="col-sm-4"><input id="lxdhId" type="text" style="width:200px" name="lxdh" class="form-control" placeholder="lxdh" ></div>
						</div>
						
						<div class="form-group" >	
							<label for="jsrId" class="col-sm-2 col-sm-offset-3 control-label">接单人:</label>
							<div class="col-sm-4">
							
						<select name="jsr" id="jsrId" >
							<option value=>--请选择接收人--</option>
							 <c:forEach items="${jsName}" var="list">
									<option>${list.userName}</option>
				               </c:forEach> 
						</select>		
						</div>
						</div>
					
						<div class="form-group">						
						<label for="wtId" class="col-sm-2 col-sm-offset-3 control-label">故障:</label>
							<div class="col-sm-4">
							<textarea id="wtId"  style="overflow-x: hidden;width:350px;height:230px;" class="form-control" name="wt" placeholder="请输入问题" ></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-5">
							<button type="submit" name="submit"  onClick="add()"  class="btn btn-primary btn-sm" >提交</button>&nbsp;&nbsp;&nbsp;
							<button type="reset" class="btn btn-primary btn-sm">重置</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 表单页面----------------------------------------------------------- -->
</body>
<script type="text/javascript">

/*提交  单击事件add()*/
function add(){
   
	/* var place=$("#add select[name=place]");
	var hESName=$("#add select[name=hESName]"); */
	var xqm=$("#add select[name=xqm]");
	var ldh=$("#add select[name=ldh]");
	var dyh=$("#add select[name=dyh]");
	var hh=$("#add input[name=hh]"); 
	var jsr=$("#add select[name=jsr]");
	var yhxm=$("#add input[name=yhxm]"); 
	var lxdh=$("#add input[name=lxdh]");
	var wt=$("#add textarea[name=wt]");
	
	var hhId=document.getElementById("hhId").value;
			 if(isNaN(hhId)){
					 sAlert('室号必须是数字！');
					document.getElementById("hhId").value="";
					return false;
			 } 
	/* 		 
	   if(jsr.val()==null||jsr.val()==""||xqm.val()==null||xqm.val()==""||ldh.val()==null||ldh.val()==""|| dyh.val()==null||dyh.val()==""||hh.val()==null||hh.val()==""||yhxm.val()==null||yhxm.val()==""||lxdh.val()==null||lxdh.val()==""||wt.val()==null||wt.val()==""){
		 sAlert('信息不能为空，请填写完整!');
		return false;
	}  */  
	
	var lxdh1=document.getElementById("lxdh").value;
	 if(isNaN(lxdh1)){
			 Alert('联系方式请输入数字');
			document.getElementById("lxdh").value="";
			return false;
	 }  
	/*  $("#Myform").submit();  */
    $("#add form").submit();
}
</script>



</html>