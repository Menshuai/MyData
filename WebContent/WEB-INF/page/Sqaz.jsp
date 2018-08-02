<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>申请安装</title>

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
				$("#xqmId").change(//通过小区获取楼栋
						function() {
							$.get("findYhldhbyxqm.action?xqm="+ $("#xqmId").val(), function(data) {
								var dd = data;
								var d = dd.ldhList;
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

				$("#ldhId").change(//通过楼栋获取单元
						function() {
							$.get("findYhdyhByBuild.action?ldh="+ $("#ldhId").val() + "&xqm="
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
	
	//搜索按钮   
	function sousuo() {
		var xqm = $('#xqmId').val();
		var ldh = $('#ldhId').val();
		var dyh = $('#dyhId').val();
		var hh = $('#hhId').val();
		var lxdh = $('#lxdhId').val();
		var fl="1"       // fl=1是故障申报
		var html ="";
		var sc="";
		$.ajax({
			 url : "Search.action",
			async : false,
			dataType : "json",  
			data : {
				"xqm" : xqm,
				"ldh" : ldh,
				"dyh" :dyh,
				"hh" : hh,
				"lxdh" : lxdh,
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
				sc+="<span>  总记录："+c+"条  &nbsp;&nbsp;  未接单："+w+" 条  &nbsp;&nbsp;  已完成："+y+"条 &nbsp;&nbsp;   已接单："+z+"条</span>" 
					for(var i=0;i<d.length;i++){
						var id=d[i].id;	
						if(d[i].xqm==undefined){
							var xqm="";
						}else{
							var xqm=d[i].xqm;
						}
						
						if(d[i].ldh==undefined){
							var ldh="";
						}else{
					 		var ldh=d[i].ldh;
						}

						if(d[i].dyh==undefined){
							var dyh="";
						}else{
							var dyh=d[i].dyh;
						}

						if(d[i].hh==undefined){
							var hh="";
						}else{
							var hh=d[i].hh;
						}

						if(d[i].zt==undefined){
							var zt="";
						}else{
							var zt=d[i].zt;
						}
						
						if(d[i].wt==wt){
							var wt="";
						}else{
							var wt=d[i].wt;	
						}
						
						if(d[i].lxdh==undefined){
							var lxdh="";
						}else{
							var lxdh=d[i].lxdh;
						}
						
						if(d[i].tjr==undefined){
							var tjr="";
						}else{
							var tjr=d[i].tjr;
						}

						if(d[i].tjsj==undefined){
							var tjsj="";
						}else{
		 					var tjsj=FormatDate(d[i].tjsj);
						}
						
	 					if(d[i].jsr==undefined){
							var jsr="";
						}else{
							var jsr=d[i].jsr;	
						}
						
						if(d[i].jssj==undefined){
							var jssj="";
						}else{
							var jssj=FormatDate(d[i].jssj);
						}
						
						if(d[i].yhxm==undefined){
							var yhxm="";
						}else{
							var yhxm= d[i].yhxm;
						}
						 
						if(d[i].lxdh==undefined){
							var lxdh="";
						}else{
							var lxdh= d[i].lxdh;
						}
						
						 
						html+="<tr>";
						  if(zt=="已完成"){
							html+="<td><input type='checkbox' value='"+id+"'/></td>";
							html+="<td align='center' nowrap='nowrap' title='"+xqm+"'>"+xqm+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+ldh+"'>"+ldh+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+dyh+"'>"+dyh+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+hh+"'>"+hh+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+zt+"'>"+zt+"</td>"; 
							html+="<td align='center' nowrap='nowrap' title='"+wt+"'>"+wt+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+tjr+"'>"+tjr+"</td>";
		 			 		html+="<td align='center' nowrap='nowrap' title='"+tjsj+"'>"+tjsj+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+jsr+"'>"+jsr+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+jssj+"'>"+jssj+"</td>";
							html+="<td  align='center' nowrap='nowrap' title='"+yhxm+"'>"+yhxm+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+lxdh+"'>"+lxdh+"</td>";
						}else{  
							html+="<td><input type='checkbox' value='"+id+"'/></td>";
							html+="<td align='center' nowrap='nowrap' title='"+xqm+"'>"+xqm+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+ldh+"'>"+ldh+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+dyh+"'>"+dyh+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+hh+"'>"+hh+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+zt+"'>"+zt+"</td>"; 
							html+="<td align='center' nowrap='nowrap' title='"+wt+"'>"+wt+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+tjr+"'>"+tjr+"</td>";
		 			 		html+="<td align='center' nowrap='nowrap' title='"+tjsj+"'>"+tjsj+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+jsr+"'>"+jsr+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+jssj+"'>"+jssj+"</td>";
							html+="<td  align='center' nowrap='nowrap' title='"+yhxm+"'>"+yhxm+"</td>";
							html+="<td align='center' nowrap='nowrap' title='"+lxdh+"'>"+lxdh+"</td>";
							
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
	if(str!=event){
		 //
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
			 	{ width: 100, align: 'center' }, 
				{ width: 80, align: 'center' },
				{ width: 80, align: 'center' },
				{ width: 80, align: 'center' },
				
				{ width: 80, align: 'center' },
				{ width: 80, align: 'center' },
				{ width: 80, align: 'center' },
				{ width: 150, align: 'center' },
				{ width: 80, align: 'center' },
				
				{ width: 150, align: 'center' },
				{ width: 80, align: 'center' },
				{ width: 100, align: 'center' }
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
		<div class="panel-heading" style="background-color: white;border-bottom: 1px white; ">申请安装详情</div>
		<span>
		&nbsp;&nbsp;&nbsp;
			选择小区
		<select name="xqm" id="xqmId" >
							<option value=0>--选择小区名称--</option> 
								<c:forEach items="${XqNameList}" var="list">
									<option>${list.xqm}</option>
				               </c:forEach>
		</select>
		&nbsp;&nbsp;&nbsp;
		楼栋号<select name="ldh" id="ldhId">
			<option value=0>--选择楼栋号--</option>
		   </select>
		&nbsp;&nbsp;&nbsp;
		单元号
		<select name="dyh" id="dyhId">
			<option value=0>--选择单元号--</option>
		</select>
		&nbsp;&nbsp;&nbsp;
		户号：<input type="text" name="hh" id="hhId" size=10px value="${hh}"  />
		
		&nbsp;&nbsp;&nbsp;
		<label for="JFStatusId">选择缴费状态</label> 
		<select id="JFStatusId" name="sfjf">
			   <option>--选择缴费状态--</option>
			     <option selected="selected">是</option>
			     <option>否</option>
		</select>
		
		&nbsp;&nbsp;&nbsp;
		联系电话：<input type="text" name="lxdh" id="lxdhId" size=10px value="${lxdh}"  />
		
		<input onclick="sousuo()" type="button" class="btn btn-success" 
			style="background-image: url('../img/secai.png');border:1px" value="搜索" /> &nbsp;&nbsp; 
		</span>
		 &nbsp;&nbsp;&nbsp;
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
 							<th>小区名</th>
							<th>楼栋号</th>
							<th>单元号</th>
							<th>门牌号</th>
							<th>状态</th>
							<th>故障</th> 
							<th>提交人</th>
							<th>提交时间</th>
							<th>接单人</th>
							
							<th>接单时间</th>
							<th>联系人</th>
							<th>联系电话</th>
	</tr>
	</thead>
		   <tbody id="xsInfos">
		 	<c:forEach var="repa" items="${rep}"  varStatus="status">
		 		<tr>
		 		<c:choose>
		 	<c:when test="${repa.zt=='已完成'}">
		 		<td bgcolor="#99CCFF"><input type="checkbox" value="${repa.id}" /></td>
		 		<td bgcolor="#99CCFF" title="${repa.xqm}">${repa.xqm}</td>
		 		<td bgcolor="#99CCFF" title="${repa.ldh}">${repa.ldh}</td>
		 		<td bgcolor="#99CCFF" title="${repa.dyh}">${repa.dyh}</td>
		 		<td bgcolor="#99CCFF" title="${repa.hh}">${repa.hh}</td>
		 		<td bgcolor="#99CCFF" title="${repa.zt}">${repa.zt}</td>
		 		<td bgcolor="#99CCFF" title="${repa.lx}">${repa.lx}</td>
		 		<td bgcolor="#99CCFF" title="${repa.tjr}">${repa.tjr}</td>
		 		<td bgcolor="#99CCFF" title="<fmt:formatDate value="${repa.tjsj}" pattern="yyyy-MM-dd HH:mm:ss" />">
		 			<fmt:formatDate value="${repa.tjsj}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		 		<td bgcolor="#99CCFF" title="${repa.jsr}">${repa.jsr}</td>
		 		<td bgcolor="#99CCFF" title="<fmt:formatDate  value="${repa.jssj}" pattern="yyyy-MM-dd HH:mm:ss" />" >
		 			<fmt:formatDate  value="${repa.jssj}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		 		<td bgcolor="#99CCFF" title="${repa.yhxm}">${repa.yhxm}</td>
		 		<td bgcolor="#99CCFF" title="${repa.lxdh}">${repa.lxdh}</td>
		 	</c:when>
		 		<c:otherwise>
					 	<td><input type="checkbox" value="${repa.id}" /></td>
					 	<td  title="${repa.xqm}">${repa.xqm}</td>
					 	<td  title="${repa.ldh}">${repa.ldh}</td>
					 	<td title="${repa.dyh}">${repa.dyh}</td>
					 	<td  title="${repa.hh}">${repa.hh}</td>
					 	<td  title="${repa.zt}">${repa.zt}</td>
					 	<td  title="${repa.lx}">${repa.lx}</td>
					 	<td  title="${repa.tjr}">${repa.tjr}</td>
					 	<td title="<fmt:formatDate value="${repa.tjsj}" pattern="yyyy-MM-dd HH:mm:ss" />">
					 		<fmt:formatDate value="${repa.tjsj}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					 	<td  title="${repa.jsr}">${repa.jsr}</td>
					 	<td  title="<fmt:formatDate  value="${repa.jssj}" pattern="yyyy-MM-dd HH:mm:ss" />" >
					 		<fmt:formatDate  value="${repa.jssj}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				 		<td title="${repa.yhxm}">${repa.yhxm}</td>
				 		<td  title="${repa.lxdh}">${repa.lxdh}</td>
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
	