<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>用户信息列表</title>
</head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
	
	<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
	<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet" media="all" />
	<link href="../css/bootstrap.css" type="text/css" rel="stylesheet"	/>
	<script src="https://code.jquery.com/jquery.min.js" type="text/javascript"></script>
	<script src="../js/fixed_table_rc.js" type="text/javascript"></script>
	<script src="../js/sortable_table.js" type="text/javascript"></script>
	<style>
  		html, body {
			font-family: Arial,​​sans-serif;
			font-size: 14px;
			margin: 0;
			padding: 0;
			background-color: #f2f2f2;
		}
		#Trcolor {
			background: url(../img/secai.png);
		}
		div.container {
			padding: 5px 10px;
			width: 2330px;
			margin: 10px auto;
		}
	</style>
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
			type:"POST",
			url : "searchInfo.action",
			async : false,
			dataType : "json",
			data : {
				"xqm" : xqm,
				"ldh" : ldh,
				"dyh" : dyh,
				"hh" : hh,
			},
			success : function(data) {
				$("#yhInfo").empty();
				var d = data.findXqInfoHistory;
				for (var i = 0; i < d.length; i++) {
					var yhbh = d[i].yhMessage.yhbh;
					var yhxm = d[i].yhMessage.yhxm;
					var xqm = d[i].yhMessage.xqm;
					var ldh = d[i].yhMessage.ldh;
					var dyh = d[i].yhMessage.dyh;
					var hh = d[i].yhMessage.hh;
					var js = d[i].yydl;
					var fpbh = d[i].fpbh;
					var ms = d[i].ms;
					var  dw= d[i].dw;
					var gdtime = d[i].gdtime;
					var zdtime = d[i].zdtime;
					var ddtime = d[i].ddtime;
					var dgdtime = d[i].dgdtime;
					var dzdtime = d[i].dzdtime;
					var dddtime = d[i].dddtime;
					var jf = d[i].jf;
					var sdwd = d[i].sdwd;
					var snwd = d[i].snwd;
					var kg = d[i].kg;
					var bj = d[i].bj;
					var jj = d[i].jj;
					var time = FormatDate(d[i].time);
					var mj = d[i].yhMessage.mj;
					var lxdh = d[i].yhMessage.lxdh;
					var fpdz = d[i].yhMessage.fpdz;
					var bz = d[i].yhMessage.bz;
					html += "<tr <c:if test='${status.index%2==1 }'>style='background-color:#eef3fa'</c:if>>";
					html += "<td class='text-center'><input  type='checkbox' value='"+yhbh+""+fpdz+"'/></td>";
					  if(bj=="01"){
						  html += "<td class='text-center'><font color='red'>" + yhbh + "-"+fpdz+"</font></td>";
					  }else if(bj=="03"){
						  html += "<td class='text-center'><font color='red'>" + yhbh + "-"+fpdz+"</font></td>";
					  }else{
						  html += "<td class='text-center'>" + yhbh + "-"+fpdz+"</td>";
					  }
				/* 	html += "<td class='text-center'>" + yhbh + "</td>"; */
					html += "<td class='text-center'>" + yhxm + "</td>";
					html += "<td class='text-center'>" + xqm + "</td>";
					html += "<td class='text-center'>" + ldh + "</td>";
					html += "<td class='text-center'>" + dyh + "</td>";
					html += "<td class='text-center'>" + hh + "</td>";
					html += "<td class='text-center'>" + fpdz+ "</td>";
					html += "<td class='text-center'>" + js + "</td>";
					html += "<td class='text-center'>" + fpbh + "</td>";
					if(ms=="00"){
						html += "<td class='text-center'>制冷</td>";
					}
					if(ms=="01"){
						html += "<td class='text-center'>制热</td>";
					}
					if(ms=="02"){
						html += "<td class='text-center'>通风</td>";	
					}
					if(dw=="00"){
						html += "<td class='text-center'>停止</td>";	
					}
					if(dw=="01"){
						html += "<td class='text-center'>低档</td>";	
					}
					if(dw=="02"){
						html += "<td class='text-center'>中档</td>";
					}
					if(dw=="03"){
						html += "<td class='text-center'>高档</td>";
					}
					html += "<td class='text-center'>" + gdtime + "</td>";
					html += "<td class='text-center'>" + zdtime + "</td>";
					html += "<td class='text-center'>" + ddtime + "</td>";
					html += "<td class='text-center'>" + dgdtime + "</td>";
					html += "<td class='text-center'>" + dzdtime + "</td>";
					
					html += "<td class='text-center'>" + dddtime + "</td>";
		
					if(jf=="00"){
						html += "<td class='text-center'>禁止计费</td>";
					}
					if(jf=="01"){
						html += "<td class='text-center'>允许计费</td>";
					}
					html += "<td class='text-center'>" + sdwd + "</td>";
					html += "<td class='text-center'>" + snwd+ "</td>";
					if(kg=="00"){
						html += "<td class='text-center'>强制关</td>";
					}
					if(kg=="01"){
						html += "<td class='text-center'>自动运行</td>";
					}
					if(bj=="00"){
						html += "<td class='text-center'>正常</td>";
					}
					if(bj=="01"){
						html += "<td class='text-center'>开盖</td>";
					}
					if(bj=="03"){
						html += "<td class='text-center'>盗热嫌疑</td>";
					}
					if(bj=="04"){
						html += "<td class='text-center'>通讯异常</td>";
					}
					if(jj=="00"){
						html += "<td class='text-center'>夏季</td>";
					}
					if(jj=="01"){
						html += "<td class='text-center'>冬季</td>";
					}
					html += "<td class='text-center' title='"+time+"'>" + time+ "</td>";
					html += "<td class='text-center'>" + mj+ "</td>";
					html += "<td class='text-center'>" + lxdh+ "</td>";
					html += "<td class='text-center'>" + bz+ "</td>";
					
					
					html += "</tr>";
				}
				$("#yhInfo").append(html);
			}

		})
	}
	
	function FormatDate(strTime) {//年月日 时分秒
		var date = new Date(strTime);
		return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
				+ date.getDate() + " " + date.getHours() + ":"
				+ date.getMinutes() + ":" + date.getSeconds();
	}
	
</script>
	 <script type="text/javascript">
	 
	 //查询状态------------------查询状态-------------------------
		function cxzt() { 
		var ckbs = $("#yhInfo input[type=checkbox]:checked");
		var apiContentStr;
		if (ckbs.length == 0) {
			alert("请选择你要查询的数据");
			return false;
		}
		var ids = [];
		$.each(ckbs, function(index, data) {
			ids[index] = $(data).val();
		})
		
		var apiContentStr = "";
		var  apiContens="";
		for (var i = 0; i < ids.length; i++) {
			apiContentStr = ids[i];
			$.ajax({
				url : "/Data/DataController/CxState.action",
				async : false,
				dataType : "json",
				data : {
					"ids" : apiContentStr,
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
					searchInfo();
				}
			});
		}
	}
//单风盘操作-----------------------------单风盘操作-------------------------
function DFs() {
	var kg = $('#kg').val();
	var jf = $('#jf').val();
	var jj = $('#jj').val();
	var ckbs = $("#yhInfo input[type=checkbox]:checked");
	var apiContentStr;
	if (ckbs.length == 0) {
		alert("请选择风盘");
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
			url : "/Data/DataController/DCxZx.action",
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
				searchInfo();
			}

		});
	}
}

</script>

<script type="text/javascript">
//所有风盘操作-----------------------------对一户 所有风盘操作-------------------------
function SFs() {
	var kg = $('#kg').val();
	var jf = $('#jf').val();
	var jj = $('#jj').val();
	var ckbs = $("#yhInfo input[type=checkbox]:checked");
	var apiContentStr;
	if (ckbs.length == 0) {
		alert("请选择风盘");
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
			url : "/Data/DataController/SCxZx.action",
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
				searchInfo();

			}

		});
	}
}

</script>
<script>
	$(function() {
		$('#fixed_hdr2').fxdHdrCol({
			fixedCols : 0,
			width : "100%",
			height :640,
			colModal : [ 
			    {
				width : 40,
				align : 'center'
			}, {
				width : 100,
				align : 'center'
			},{
				width : 120,
				align : 'center'
			},{
				width : 110,
				align : 'center'
			},{
				width : 100,
				align : 'center'
			},{
				width : 60,
				align : 'center'
			},{
				width : 60,
				align : 'center'
			},{
				width : 60,
				align : 'center'
			},{
				width : 70,
				align : 'center'
			},{
				width : 70,
				align : 'center'
			},{
				width : 80,
				align : 'center'
			}, {
				width : 60,
				align : 'center'
			},{
				width : 60,
				align : 'center'
			},  {
				width : 80,
				align : 'center'
			}, {
				width : 80,
				align : 'center'
			}, {
				width : 80,
				align : 'center'
			},
			{
				width : 80,
				align : 'center'
			}, {
				width : 80,
				align : 'center'
			}, {
				width : 80,
				align : 'center'
			},{
				width : 80,
				align : 'center'
			}, {
				width : 80,
				align : 'center'
			}, {
				width : 80,
				align : 'center'
			},{
				width : 80,
				align : 'center'
			},{
				width : 80,
				align : 'center'
			}, {
				width : 120,
				align : 'center'
						
			} , {
				width : 80,
				align : 'center'
			},{
				width : 100,
				align : 'center'
			},{
				width : 60,
				align : 'center'
			}
			],
			sort : true
		});

	});
	</script>
	
<body > 


<div>
<!-- style="width:65%; height: 60%; position: absolute; overflow: auto;text-align: center" -->
 <div class="panel panel-success">
 <div class="panel-heading">设备管理</div>
 	<label for="xqNameId">选择小区</label> 
 			<select id="xqmId" name="xqm">
					<option value="--选择小区名称--">--选择小区名称--</option>
					<c:forEach items="${XqNameList}" var="list">
						<option>${list.xqm}</option>
					</c:forEach>
			</select> &nbsp;&nbsp;&nbsp; 
			
		<label for="buildNoId">楼栋号</label>
			<select   name="ldh" id="ldhId">
				<option value=0>--选择楼栋号--</option>
			</select> 
			&nbsp;&nbsp;&nbsp; 
			
		<label for="cellNoId">单元号</label>
			<select  name="dyh" id="dyhId">
				<option value=0>--选择单元号--</option>
			</select> 
		&nbsp;&nbsp;&nbsp;
		
		户号：<input type="text" name="hh" id="hhId" value="${hh}" size=10px/> 
		
		<input type="button" onclick="searchInfo()" value="搜索" class="btn btn-success" style="background: url(../img/secai.png);"/>
		<br>
   <button type="button" class="btn btn-success" onclick="cxzt()" style="background: url(../img/secai.png);">查询状态</button>
 
 &nbsp;&nbsp;&nbsp;
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
	
	选择季节：
	<select id="jj">
		<option value="02">--选择季节--</option>
		<option value="01">冬季</option>
		<option value="00">夏季</option>
	</select>  
	&nbsp;&nbsp;&nbsp;
	
	 
	 <button type="button" class="btn btn-success" onclick="DFs()" style="background: url(../img/secai.png);">单风盘操作</button>
	 <button type="button" class="btn btn-success" onclick="SFs()" style="background: url(../img/secai.png);">对一户所有风盘操作</button>
	<div></div>
 
 &nbsp;&nbsp;&nbsp;
 &nbsp;&nbsp;&nbsp;
	<table id="fixed_hdr2"  >
		<thead >
	<tr id="Trcolor" height="35px">
			<th></th>         
			<th>风盘地址</th>
			<th>用户姓名</th>
			<th>小区名称</th>
			<th>楼栋</th>
			<th>单元</th>
			<th>户号</th>
		    <th>风盘地址</th>
			<th>已用当量</th>
			<th>风盘编号</th>
			<th>模式</th>
            <th>档位</th>
            <th>制冷高档t</th>
            <th>制冷中档t</th>
            <th>制冷低档t</th>
            <th>制热高档t</th>
            <th>制热中档t</th>
            <th>制热低档t</th>
            <th>计费状态</th>
            <th>设定温度</th>
            <th>出风温度</th>
            <th>远程状态</th>
            <th>报警信息</th>
            <th>季节</th>
            <th>采集时间</th>
            <th>用户面积</th>
            <th>用户电话</th>

            <th>备注</th>
	</tr>                    
	</thead>                      

<tbody id="yhInfo">
	<c:forEach  var="yh" items="${YhList}" varStatus="status">
	   <tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>	  
		<td align="center"><input type="checkbox" value="${yh.yhMessage.yhbh}${yh.yhMessage.fpdz}" /></td>  
		<c:choose>
	  <c:when test="${yh.bj =='01'}">
	  <td><font color="red">${yh.yhMessage.yhbh}-${yh.yhMessage.fpdz}</font></td>
	  </c:when>
	   <c:when test="${yh.bj =='03'}">
	   <td><font color="red">${yh.yhMessage.yhbh}-${yh.yhMessage.fpdz}</font></td>
	  </c:when>
	  <c:otherwise>
	  <td>${yh.yhMessage.yhbh}-${yh.yhMessage.fpdz}</td>
	  </c:otherwise>
	  </c:choose>
	  	<td>${yh.yhMessage.yhxm}</td>	
		<td>${yh.yhMessage.xqm}</td>	
		<td>${yh.yhMessage.ldh}</td>
		<td>${yh.yhMessage.dyh}</td>	
		<td>${yh.yhMessage.hh}</td>	
		<td>${yh.yhMessage.fpdz}</td>
		<td>${yh.yydl}</td>
		<td>${yh.fpbh}</td>
		<c:if test="${yh.ms =='00'}">
   		<td>制冷</td>
		</c:if>
		<c:if test="${yh.ms=='01'}">
   		<td>制热</td>
		</c:if>
		<c:if test="${yh.ms =='02'}">
   		<td>通风</td>
		</c:if>
		<c:if test="${yh.dw =='00'}">
   		<td>停止</td>
		</c:if>
		<c:if test="${yh.dw =='01'}">
   		<td>低档</td>
		</c:if>
		<c:if test="${yh.dw =='02'}">
   		<td>中档</td>
		</c:if>
		<c:if test="${yh.dw =='03'}">
   		<td>高档</td>
		</c:if>
		<td>${yh.gdtime}</td>
		<td>${yh.zdtime}</td>
		<td>${yh.ddtime}</td> 
		
		<td>${yh.dgdtime}</td>
		<td>${yh.dzdtime}</td>
		<td>${yh.dddtime}</td> 
		
		<c:if test="${yh.jf =='00'}">
   		<td>禁止计费</td>
		</c:if>
		<c:if test="${yh.jf =='01'}">
   		<td>允许计费</td>
		</c:if>
		<td>${yh.sdwd}</td>		
	   	<td>${yh.snwd}</td>   
		<c:if test="${yh.kg =='00'}">
   		<td>强制关</td>
		</c:if>
		<c:if test="${yh.kg =='01'}">
   		<td>自动运行</td>
		</c:if>
		<c:if test="${yh.bj =='00'}">
   		<td>正常</td>
		</c:if>
		<c:if test="${yh.bj =='01'}">
   		<td>开盖</td>
		</c:if>
		<c:if test="${yh.bj =='03'}">
   		<td>盗热嫌疑</td>
		</c:if>
		<c:if test="${yh.jj =='00'}">
   		<td>夏季</td>
		</c:if>
		<c:if test="${yh.jj=='01'}">
   		<td>冬季</td>
		</c:if>
		<td>${yh.time}</td>
		<td>${yh.yhMessage.mj}</td>
		<td>${yh.yhMessage.lxdh}</td>
		<td>${yh.yhMessage.bz}</td>
	  
	 </tr>
	</c:forEach>
  </tbody>
</table>
</div>
</div>
</body>
</html>