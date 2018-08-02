<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>报修详情</title>

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
			font-family: Arial,sans-serif;
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
	
	<script type="text/javascript">
	/*页面加载就开始执行js*/
	$(document).ready(
			function() {
				$("#xqNameId").change(//通过小区获取楼栋
						function() {
							$.get("findXqNameList.action?xqName="+ $("#xqNameId").val(), function(data) {
								var dd = data;
								var d = dd.BuildNOList;
							
								$("#buildNoId option:gt(0)").remove();
								$("#cellNoId option:gt(0)").remove();
								
								for (var i = 0; i < d.length; i++) {
									var buildNo = d[i].buildNo;
									var opt = "<option value='"+buildNo+"'>"
											+ buildNo + "</option>"
									$("#buildNoId").append(opt);
								}
							});
						});

				$("#buildNoId").change(//通过楼栋获取单元
						function() {
							$.get("findCellNOByBuild.action?buildNo="+ $("#buildNoId").val() + "&xqName="
									+ $("#xqNameId").val(), function(data) {
								var dd = data;
								var d = dd.cellList;
								
								$("#cellNoId option:gt(0)").remove();
								
								for (var i = 0; i < d.length; i++) {
									var cellNo = d[i].cellNo;
									var opt = "<option value='"+cellNo+"'>"
											+ cellNo + "</option>"
									$("#cellNoId").append(opt);
								}
							});
						});
			});
	</script>
		
	
	<script type="text/javascript"> 
	//搜索按钮
	function chaxun() {
		var xqName = $('#xqNameId').val();
		var buildNo = $('#buildNoId').val();
		var cellNo = $('#cellNoId').val();
		var houseNo = $('#houseNoId').val();
		var fl="2"
		var html ="";
		var sc="";
		$.ajax({
			 url : "Search.action",
			async : false,
			dataType : "json",  
			
		/* 	url:"listState1.action",
			async : false,
			dataType : "json",   */
			
			data : {
				"xqName" : xqName,
				"buildNo" : buildNo,
				"cellNo" :cellNo,
				"houseNo" : houseNo,
				"fl":fl,
		        },
		  
			success : function(data) {
				$("#stateCount").empty();//总记录id
				$("#xsInfos").empty();//获取数据id为空
				var d = data.SearchList;
				var c=data.sums;
				var y=data.ywc;	
				var w=data.wjd;
				var z=data.yjd;
				sc+="<span>  总记录："+c+"条  &nbsp;&nbsp;  未接单："+w+" 条  &nbsp;&nbsp;   已完成："+y+"条 &nbsp;&nbsp;   已接单："+z+"条</span>" 
					for(var i=0;i<d.length;i++){
						var id=d[i].id;	
						if(d[i].hea.place==undefined){
							var place="";
						}else{
							var place=d[i].hea.place;	
						}
						
						if(d[i].hea.hesName==undefined){
							var hesName="";
						}else{
					 		var hesName=d[i].hea.hesName; 
						}
						if(d[i].xqName==undefined){
							var xqName="";
						}else{
							var xqName=d[i].xqName;
						}
						
						if(d[i].buildNo==undefined){
							var buildNo="";
						}else{
					 		var buildNo=d[i].buildNo;
						}

						if(d[i].cellNo==undefined){
							var cellNo="";
						}else{
							var cellNo=d[i].cellNo;
						}

						if(d[i].houseNo==undefined){
							var houseNo="";
						}else{
							var houseNo=d[i].houseNo;
						}

						if(d[i].cs==undefined){
							var cs="";
						}else{
							var cs =d[i].cs ;
						}
						
						if(d[i].sh==undefined){
							var sh="";
						}else{
							var sh =d[i].sh ;
						}

						if(d[i].name==undefined){
							var name="";
						}else{
							var name=d[i].name;
						}
						
						if(d[i].telephone==undefined){
							var telephone="";
						}else{
							var telephone=d[i].telephone;
						}
						
						if(d[i].problem==undefined){
							var problem="";
						}else{
							var problem=d[i].problem;	
						}

						if(d[i].tJname==undefined){
							var tJname="";
						}else{
							var tJname=d[i].tJname;
						}

						if(d[i].tJtime==undefined){
							var tJtime="";
						}else{
		 					var tJtime=FormatDate(d[i].tJtime);
						}
						
	 					if(d[i].jSname==undefined){
							var jSname="";
						}else{
							var jSname=d[i].jSname;	
						}
						
						if(d[i].jStime==undefined){
							var jStime="";
						}else{
							var jStime=FormatDate(d[i].jStime);
						}
						
						if(d[i].wCname==undefined){
							var wCname="";
						}else{
							var wCname=d[i].wCname;
						}
						
						if(d[i].wCtime==undefined){
							var wCtime="";
						}else{
							var wCtime=FormatDate(d[i].wCtime);
						}
						
						if(d[i].state==undefined){
							var state="";
						}else{
							var state=d[i].state; 
							//alert(state);
						}
						html+="<tr>";
						if(state=="已完成"){
							html+="<td><input type='checkbox' value='"+id+"'/></td>";
							html+="<td  align='center' nowrap='nowrap' title='"+place+"'>"+place+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+xqName+"'>"+xqName+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+buildNo+"'>"+buildNo+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+cellNo+"'>"+cellNo+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+cs+"'>"+cs+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+sh+"'>"+sh+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+state+"'>"+state+"</td>"; 
							html+="<td align='center' nowrap='nowrap' title='"+problem+"'>"+problem+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+tJname+"'>"+tJname+"</td>";
		 			 		html+="<td align='center' nowrap='nowrap' title='"+tJtime+"'>"+tJtime+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+jSname+"'>"+jSname+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+jStime+"'>"+jStime+"</td>";
							html+="<td  align='center' nowrap='nowrap' title='"+wCname+"'>"+wCname+"</td>";
		 					html+="<td  align='center' nowrap='nowrap' title='"+wCtime+"'>"+wCtime+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+hesName+"'>"+hesName+"</td>"; 
							html+="<td align='center' nowrap='nowrap' title='"+name+"'>"+name+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+telephone+"'>"+telephone+"</td>";
						}else{
							html+="<td><input type='checkbox' value='"+id+"'/></td>";
							html+="<td  align='center' nowrap='nowrap' title='"+place+"'>"+place+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+xqName+"'>"+xqName+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+buildNo+"'>"+buildNo+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+cellNo+"'>"+cellNo+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+cs+"'>"+cs+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+sh+"'>"+sh+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+state+"'>"+state+"</td>"; 
							html+="<td align='center' nowrap='nowrap' title='"+problem+"'>"+problem+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+tJname+"'>"+tJname+"</td>";
		 			 		html+="<td align='center' nowrap='nowrap' title='"+tJtime+"'>"+tJtime+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+jSname+"'>"+jSname+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+jStime+"'>"+jStime+"</td>";
							html+="<td  align='center' nowrap='nowrap' title='"+wCname+"'>"+wCname+"</td>";
		 					html+="<td  align='center' nowrap='nowrap' title='"+wCtime+"'>"+wCtime+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+hesName+"'>"+hesName+"</td>"; 
							html+="<td align='center' nowrap='nowrap' title='"+name+"'>"+name+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+telephone+"'>"+telephone+"</td>";
							
						}
						html+="</tr>";
					}
				  $("#stateCount").append(sc);
				$("#xsInfos").append(html);
			}
		})
	}
	
	//时间
	function FormatDate (strTime) {
	    var date = new Date(strTime);
	    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	}
 </script>
 
	<script>
	$(function () {//table标签表格
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols: 0,
			width: "100%",
			height: 660,
			colModal: [
				{ width: 50, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 120, align: 'center' },
				{ width: 60, align: 'center' },
				
				{ width: 60, align: 'center' },
				{ width: 60, align: 'center' },
				{ width: 60, align: 'center' },
				{ width: 100, align: 'center' },
				{ width: 100, align: 'center' },
				
				{ width: 160, align: 'center' },
				{ width: 100, align: 'center' },
				{ width: 160, align: 'center' },
				{ width: 100, align: 'center' },
				{ width: 160, align: 'center' },
				
				{ width: 100, align: 'center' },
				{ width: 60, align: 'center' },
				{ width: 90, align: 'center' },
			

			],
			sort: true
		});
		
	});
	</script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js">
</script>

</head>

<body>
	<div class="panel panel-success" style="border-bottom: null;">
		<div class="panel-heading" style="background-color: white;border-bottom: 1px white; ">报修登记详情</div>
		<span>
		&nbsp;&nbsp;&nbsp;
			选择小区
		<select name="xqName" id="xqNameId" >
							<option value=0>--请选择小区--</option>
								<c:forEach items="${XqNameList}" var="list">
									<option>${list.xqName}</option>
				               </c:forEach>
							
		</select>
		
		&nbsp;&nbsp;&nbsp;
		
		楼栋号<select name="buildNo" id="buildNoId">
			<option value=0>--选择楼栋号--</option>
		   </select>
		
		&nbsp;&nbsp;&nbsp;
		 
		单元号
		<select name="cellNo" id="cellNoId">
			<option value=0>--选择单元号--</option>
		</select>
		 
		&nbsp;&nbsp;&nbsp;
		
		 
		户号：<input type="text" name="houseNo" id="houseNoId" size=10px value="${houseNo}"  />
	 
		
		<label for="JFStatusId">选择缴费状态</label> 
		<select id="JFStatusId" name="sfjf">
			   <option>--选择缴费状态--</option>
			     <option selected="selected">是</option>
			     <option>否</option>
		</select>
		
		联系电话：<input type="text" name="telephone" id="houseNoId" size=10px value="${telephone}"  />
		
		<input onclick="chaxun()" type="button" class="btn btn-success" style="background-image: url('../img/secai.png');border:1px" value="搜索" /> &nbsp;&nbsp; 
		</span>
		
		 
			<div id="stateCount" style="font-size: 15px;">
				<span>&nbsp;&nbsp;&nbsp; 总记录：${sums}条  &nbsp;&nbsp;  未接单：${wjd} 条  &nbsp;&nbsp;   已完成：${ywc}条 &nbsp;&nbsp;   已接单：${yjd}条</span>
		    </div>
		</div>
			<div id="tablediv" >
			
<form>
	<div class="dwrapper">
	<table id="fixed_hdr2">
	<thead>
	<tr style="background-image: url('../img/secai.png'); color: white;">
							<th></th>
							<th>管理处</th>
							<th>小区名</th>
							<th>楼栋号</th>
							<th>单元号</th>
							
							<th>楼层号</th>
							<th>门牌号</th>
							<th>状态</th>
							<th>故障</th>
							<th>提交人</th>
							
							<th>提交时间</th>
							<th>接单人</th>
							<th>接单时间</th>
							<th>维修人</th>
							<th>维修时间</th>
							
							<th>交换站</th>
							<th>联系人</th>
							<th>联系电话</th>
	</tr>
	</thead>
				
					<tbody id="xsInfos">
						<c:forEach var="repa" items="${rep}"  varStatus="status">
							<tr>
							<c:choose>
							<c:when test="${repa.state=='已完成'}">
							<td bgcolor="#99CCFF"><input type="checkbox" value="${repa.id }" /></td>
							<td bgcolor="#99CCFF" title="${repa.hea.place}">${repa.hea.place}</td>
							<td bgcolor="#99CCFF" title="${repa.xqName}">${repa.xqName}</td>
							<td bgcolor="#99CCFF" title="${repa.buildNo}">${repa.buildNo}</td>
							<td bgcolor="#99CCFF" title="${repa.cellNo}">${repa.cellNo}</td>
							<td bgcolor="#99CCFF" title="${repa.cs}">${repa.cs}</td>
							<td bgcolor="#99CCFF" title="${repa.sh}">${repa.sh}</td>
							<td bgcolor="#99CCFF" title="${repa.state}">${repa.state}</td>
							<td bgcolor="#99CCFF" title="${repa.lx}">${repa.lx}</td>
							<td bgcolor="#99CCFF" title="${repa.tJname}">${repa.tJname}</td>
							<td bgcolor="#99CCFF" title="<fmt:formatDate value="${repa.tJtime}" pattern="yyyy-MM-dd HH:mm:ss" />">
								<fmt:formatDate value="${repa.tJtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								
							<td bgcolor="#99CCFF" title="${repa.jSname}">${repa.jSname}</td>
							<td bgcolor="#99CCFF" title="<fmt:formatDate  value="${repa.jStime}" pattern="yyyy-MM-dd HH:mm:ss" />" ><fmt:formatDate  value="${repa.jStime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							
							<td bgcolor="#99CCFF" title="${repa.wCname}">${repa.wCname}</td>
							<td bgcolor="#99CCFF" title="<fmt:formatDate value="${repa.wCtime}" pattern="yyyy-MM-dd HH:mm:ss" />"><fmt:formatDate value="${repa.wCtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							
							<td bgcolor="#99CCFF" title="${repa.hea.hesName}">${repa.hea.hesName}</td>
							<td bgcolor="#99CCFF" title="${repa.name}">${repa.name}</td>
							<td bgcolor="#99CCFF" title="${repa.telephone}">${repa.telephone}</td>
							</c:when>
							
							<c:otherwise>
								<td><input type="checkbox" value="${repa.id }" /></td>
								<td title="${repa.hea.place}">${repa.hea.place}</td>
								<td  title="${repa.xqName}">${repa.xqName}</td>
								<td  title="${repa.buildNo}">${repa.buildNo}</td>
								<td title="${repa.cellNo}">${repa.cellNo}</td>
								<td  title="${repa.cs}">${repa.cs}</td>
								<td  title="${repa.sh}">${repa.sh}</td>
								<td  title="${repa.state}">${repa.state}</td>
								<td  title="${repa.lx}">${repa.lx}</td>
								<td  title="${repa.tJname}">${repa.tJname}</td>
								<td title="<fmt:formatDate value="${repa.tJtime}" pattern="yyyy-MM-dd HH:mm:ss" />">
									<fmt:formatDate value="${repa.tJtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td  title="${repa.jSname}">${repa.jSname}</td>
								<td  title="<fmt:formatDate  value="${repa.jStime}" pattern="yyyy-MM-dd HH:mm:ss" />" >
									<fmt:formatDate  value="${repa.jStime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td  title="${repa.wCname}">${repa.wCname}</td>
								
								<td title="<fmt:formatDate value="${repa.wCtime}" pattern="yyyy-MM-dd HH:mm:ss" />">
									<fmt:formatDate value="${repa.wCtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									
								<td title="${repa.hea.hesName}">${repa.hea.hesName}</td>
								<td title="${repa.name}">${repa.name}</td>
								<td  title="${repa.telephone}">${repa.telephone}</td>
							</c:otherwise> 
							</c:choose>
							</tr>

						</c:forEach>
	  				</tbody>
</table>
</div>
</form>
</div>
</body>
 
</html>
	