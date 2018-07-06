<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>申请安装 添加登记单</title>
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
		$("#placeID").change(
		function(){	
		  $.get("findHes.action?place="+ $("#placeID").val(),function(data) {
			var dd=data.heslist;
			$("#hESNameID option:gt(0)").remove();
			for(var i=0;i<dd.length;i++){
				var hESName=dd[i].hESName;
				var opt="<option value='"+hESName+"'>"+hESName+"</option>"
				$("#hESNameID").append(opt);
			}
			});
		}); 
		
		$("#hESNameID").change(//换热站
				function() {//小区方法
					$.get("findXqNamebyPlace.action?hESName="+ $("#hESNameID").val()+"&place="+ $("#placeID").val(),function(data) {
						var dd=data;
						var d=dd.xqNameList;
						$("#xqNameId option:gt(0)").remove();
						for(var i=0;i<d.length;i++){
							var xqName=d[i].xqName;
							var opt="<option value='"+xqName+"'>"+xqName+"</option>"
							$("#xqNameId").append(opt);//小区
						}
						});
					 });
		
		$("#xqNameId").change(//小区
				function(){	
				  $.get("findYhBuildNObyXqName.action?xqName="+ $("#xqNameId").val()+"&hESName="+ $("#hESNameID").val()+"&place="+ $("#placeID").val(),function(data) {
					var d=data.buildNoList; 
					$("#buildNoId option:gt(0)").remove();
					$("#cellNoId option:gt(0)").remove();
					
					for(var i=0;i<d.length;i++){
						var buildNo=d[i].lH;
						var opt="<option value='"+buildNo+"'>"+buildNo+"</option>"
						$("#buildNoId").append(opt);//楼栋
					}
					});
				});
				
		$("#buildNoId").change(//楼栋
		function() {//单元方法
			$.get("findYhCellNOByBuild.action?buildNo="+ $("#buildNoId").val()+"&xqName="+ $("#xqNameId").val()+"&hESName="+ $("#hESNameID").val()+"&place="+ $("#placeID").val(),function(data) {
				var dd=data;
				var d=dd.cellList;
				$("#cellNoId option:gt(0)").remove();
				for(var i=0;i<d.length;i++){
					var cellNo=d[i].dYH;
					var opt="<option value='"+cellNo+"'>"+cellNo+"</option>"
					$("#cellNoId").append(opt);//单元
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
					<h4 class="text-center" style="padding: 0; margin: 0;">添加登记单</h4>
				</div>
				<div class="modal-body bg-info">
					<form class="form form-horizontal" id="Myform" action="InsertRe2.action">
						
						
						<!-- 获取所有的处 -->		
						<div class="form-group">						
						<label for="place" class="col-sm-2 col-sm-offset-3 control-label">管理处:</label>
						<div  class="col-sm-4" >
						<select name="place" id="placeID">
							<option value=>--请选择处--</option>
								<c:forEach items="${listPlace}" var="list">
									<option>${list.place}</option>
				               </c:forEach>
							
						</select>
						</div>					
						</div>			
						
						<!-- 根据处获取所有的换热站-->		
						<div class="form-group">						
						<label for="hESName" class="col-sm-2 col-sm-offset-3 control-label">交换站:</label>
						<div  class="col-sm-4" >
						<select name="hESName" id="hESNameID"  value="">
							<option value=0>--请选择交换站--</option>
						</select>
						</div>					
						</div>			
						
							<!-- 获取所有的小区 -->										
						<div class="form-group">						
						<label for="xqName" class="col-sm-2 col-sm-offset-3 control-label">小区名:</label>
						<div  class="col-sm-4" >
						<select name="xqName" id="xqNameId">
							<option value=0>--请选择小区--</option>
							
						<%-- 	<c:forEach items="${yhInfoList}" var="list">
									<option>${list.xqName}</option>
				               </c:forEach> --%>
							 
						</select>
						</div>
					
						</div>								
						<!-- 获取楼栋号 -->	
						<div class="form-group" >
						<label for="buildNoId" class="col-sm-2 col-sm-offset-3 control-label">楼栋号:</label>
						<div class="col-sm-4">
						 <select name="buildNo" id="buildNoId" >
							<option value=0>--选择楼栋号--</option>
						</select>
						</div>
						</div>
						<!-- 获取楼单元号 -->	
						<div class="form-group">
						<label for="cellNoId" class="col-sm-2 col-sm-offset-3 control-label">单元号:</label> 
						<div class="col-sm-4">
						<select name="cellNo" id="cellNoId" >
							<option value=0>--选择单元号--</option>
						</select>
						</div>
						</div>		
						<!-- 获取层号 -->					
						<div class="form-group">						
							<label for="CS" class="col-sm-2 col-sm-offset-3 control-label">楼层号:</label>
							
							<div class="col-sm-4">
								<input id="CSId" type="text" name="cs" onblur="findcs()"
									class="form-control" placeholder="cs" />
							</div>					
						</div>
						<!-- 获取室号 -->
						<div class="form-group">						
							<label for="SH" class="col-sm-2 col-sm-offset-3 control-label">门牌号:</label>
							
							<div class="col-sm-4">
								<input id="SHId" type="text" name="sh"
									class="form-control" placeholder="sh" />
							</div>					
						</div>		
							<div class="form-group">						
							<label for="name" class="col-sm-2 col-sm-offset-3 control-label">联系人:</label>
						<div class="col-sm-4">
						<input id="name"  type="text" name="name" 
								class="form-control" placeholder="name" >
						</div>
						</div>
						<div class="form-group">						
							<label for="telephone" class="col-sm-2 col-sm-offset-3 control-label">电话:</label>
						<div class="col-sm-4"><input id="telephone" type="text" name="telephone" class="form-control" placeholder="telephone" ></div>
						</div>
						
						<div class="form-group">	
							<label for="jSname" class="col-sm-2 col-sm-offset-3 control-label">接单人:</label>
							<div class="col-sm-4">
							
						<select name="jSname" id="jSnameId" >
							<option value=>--请选择接收人--</option>
							 <c:forEach items="${jsName}" var="list">
									<option>${list.userName}</option>
									
				               </c:forEach> 
						</select>		
						</div>
						</div>
					
						<div class="form-group">						
						<label for="problem" class="col-sm-2 col-sm-offset-3 control-label">故障:</label>
							<div class="col-sm-4">
							<textarea id="problem"  style="overflow-x: hidden;width:300px;height:100px;" class="form-control" name="problem" placeholder="problem"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-5">
							<button type="submit"  onClick="add()"  name="submit"   class="btn btn-primary btn-sm" >提交</button>
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
  	 
	var place=$("#add select[name=place]");
	var hESName=$("#add select[name=hESName]");
	
	var xqName=$("#add select[name=xqName]");
	var buildNo=$("#add select[name=buildNo]");
	var cellNo=$("#add select[name=cellNo]");
 	var cs=$("#add input[name=cs]");
	var sh=$("#add input[name=sh]"); 
	var jSname=$("#add select[name=jSname]");
	 var name=$("#add input[name=name]"); 
	var telephone=$("#add input[name=telephone]");
	var problem=$("#add textarea[name=problem]");
	
	var CsId=document.getElementById("CSId").value;
	  if(isNaN(CsId)){
			 sAlert('层号必须是数字！');
			document.getElementById("CSId").value="";
			return false;
	 }	
	var ShId=document.getElementById("SHId").value;
			 if(isNaN(ShId)){
					 sAlert('室号必须是数字！');
					document.getElementById("SHId").value="";
					return false;
			 } 
			 
	   if(place.val()==null||place.val()==""||hESName.val()==null||hESName.val()==""||jSname.val()==null||jSname.val()==""||xqName.val()==null||xqName.val()==""||buildNo.val()==null||buildNo.val()==""|| cellNo.val()==null||cellNo.val()==""||cs.val()==null||cs.val()==""||sh.val()==null||sh.val()==""||name.val()==null||name.val()==""||telephone.val()==null||telephone.val()==""||problem.val()==null||problem.val()==""){
		 sAlert('信息不能为空，请填写完整!');
		return false;
	}   
	
	var telephone1=document.getElementById("telephone").value;
	 if(isNaN(telephone1)){
			 Alert('联系方式请输入数字');
			document.getElementById("telephone").value="";
			return false;
	 } 
/* 	 $("#Myform").submit();  */
   $("#add form").submit();   
	 
}

</script>


<!-- <script type="text/javascript">
//数据不允许为空     提交：onclick=edit()
function edit(){
		var jSname=$("#edit select[name=jSname]");
		var cs=$("#edit input[name=cs]");
		var sh=$("#edit input[name=sh]");
		var name=$("#edit input[name=name]");
		var telephone=$("#edit input[name=telephone]");
		var problem=$("#edit textarea[name=problem]");
		if(jSname.val()==null||jSname.val()==""||sh.val()==null||sh.val()==""||cs.val()==null||cs.val()==""||name.val()==null||name.val()==""||telephone.val()==null||telephone.val()==""||problem.val()==null||problem.val()==""){
			 sAlert('信息不能为空，请填写完整!');
			return false;
		}
		var css=document.getElementById("edit_cs").value;
		 if(isNaN(css)){
				 sAlert('层号必须是数字！');
				document.getElementById("edit_cs").value="";
				return false;
		 }
			var shs=document.getElementById("edit_sh").value;
			 if(isNaN(shs)){
					 sAlert('室号必须是数字！');
					document.getElementById("edit_sh").value="";
					return false;
			 }
		 var telephone1=document.getElementById("edit_telephone").value;
		 if(isNaN(telephone1)){
				 sAlert('联系方式请输入数字');
				document.getElementById("edit_telephone").value="";
				return false;
		 }
		$("#edit form").submit();
		}

</script> -->




</html>