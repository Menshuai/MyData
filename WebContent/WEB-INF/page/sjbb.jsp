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

<title>数据报表</title>
</head>

	<script src="https://code.jquery.com/jquery.min.js" type="text/javascript"></script><!-- 用到JQUERY的地方，jquery一定是在JS前面加载 -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.css" rel="stylesheet">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<!-- <script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script> -->
	<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet" media="all" />
	<link href="../css/bootstrap.css" type="text/css" rel="stylesheet"	/>
	<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
	<script src="https://meetselva.github.io/fixed-table-rows-cols/js/sortable_table.js" type="text/javascript"></script>
	<script src="../js/fixed_table_rc.js" type="text/javascript"></script>
	<style>
		html, body {
			font-family: Arial,​​sans-serif;
			font-size: 14px;
			margin: 0;
			padding: 0;
			background-color: #f2f2f2;
		}
		
		div.container {
			padding: 5px 10px;
			width: 2330px;
			margin: 10px auto;
		}
		
		.ft_container table tr {
			background: url(../img/secai.png);
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
								debugger;
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
		debugger;
		var xqm = $('#xqmId').val();
		var ldh = $('#ldhId').val();
		var dyh = $('#dyhId').val();
		var hh = $('#hhId').val();
		var time1 = $('#time1').val();
		var time2 = $('#time2').val();
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
				"time1":time1,
				"time2":time2,
			},
			success : function(data) {
				$("#yhInfo").empty();
				var d = data.findXqInfoHistory;
				alert(d.length);
				for (var i = 0; i < d.length; i++) {
					var yhbh = d[i].yhMessage.yhbh;
					alert(d[i].yhMessage.yhbh);
					var yhxm = d[i].yhMessage.yhxm;
					var xqm = d[i].yhMessage.xqm;
					var ldh = d[i].yhMessage.ldh;
					var dyh = d[i].yhMessage.dyh;
					var hh = d[i].yhMessage.hh;
					var js = d[i].js;
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
					html += "<tr>";
					html += "<td class='text-center'>" + yhbh + "</td>";
					html += "<td class='text-center'>" + yhxm + "</td>";
					html += "<td class='text-center'>" + xqm + "</td>";
					html += "<td class='text-center'>" + ldh + "</td>";
					html += "<td class='text-center'>" + dyh + "</td>";
					
					html += "<td class='text-center'>" + hh + "</td>";
					html += "<td class='text-center'>" + js + "</td>";
					html += "<td class='text-center'>" + fpbh + "</td>";
					html += "<td class='text-center'>" + ms + "</td>";
					html += "<td class='text-center'>" + dw + "</td>";
					
					html += "<td class='text-center'>" + gdtime + "</td>";
					html += "<td class='text-center'>" + zdtime + "</td>";
					html += "<td class='text-center'>" + ddtime + "</td>";
					html += "<td class='text-center'>" + dgdtime + "</td>";
					html += "<td class='text-center'>" + dztime + "</td>";
					
					html += "<td class='text-center'>" + dddtime + "</td>";
					html += "<td class='text-center'>" + jf + "</td>";
					html += "<td class='text-center'>" + sdwd + "</td>";
					html += "<td class='text-center'>" + snwd+ "</td>";
					html += "<td class='text-center'>" + kg+ "</td>";
					html += "<td class='text-center'>" + bj+ "</td>";
					html += "<td class='text-center'>" + jj+ "</td>";
					html += "<td class='text-center' title='"+time+"'>" + time+ "</td>";
					html += "<td class='text-center'>" + mj+ "</td>";
					html += "<td class='text-center'>" + lxdh+ "</td>";
					html += "<td class='text-center'>" + fpdz+ "</td>";
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
	//导出
	function doExportExcel() {
		var xqm = $('#xqmId').val();
		var ldh = $('#ldhId').val();
		var dyh = $('#dyhId').val();
		var hh = $('#hhId').val();
		var time1 = $('#time1').val();
		var time2 = $('#time2').val();
		window.open("YhInfodoExportExcel.action?xqm=" + xqm + "&ldh="
				+ ldh + "&dyh=" + dyh + "&hh=" + hh
				+"&time1="+ time1+"&time2="+ time2);
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
			}, /* {
				width : 50,
				align : 'center'
			}, */ {
				width : 80,
				align : 'center'
			},{
				width : 80,
				align : 'center'
			},{
				width : 80,
				align : 'center'
				//-------------5-----
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
				width : 80,
				align : 'center'
					//-----------10-------
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
					//----------15-------
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
					//---------------20-----	
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
				width : 90,
				align : 'center'
						
			} ,{
				width : 120,
				align : 'center'
				
			} ,{
				width : 80,
				align : 'center'
			},{
				width : 100,
				align : 'center'
			}, {
				width : 80,
				align : 'center'
			} ,{
				width : 60,
				align : 'center'
			}/*  ,{
				width : 60,
				align : 'center'
			}  */
			],
			sort : true
		});

	});
	</script>
	
<body > 

<div class="panel panel-success">

<div>
<!-- style="width:65%; height: 60%; position: absolute; overflow: auto;text-align: center" -->
 <h3>数据报表</h3> &nbsp;&nbsp;&nbsp;
 <div></div>
 <div class="panel panel-success">
 		<label for="xqNameId">选择小区</label> 
 			<select id="xqmId" name="xqm">
					<option value=0>--选择小区名称--</option>
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
				
		&nbsp;&nbsp;&nbsp;
		  
			 
		<label for="readMTime">选择时间:</label>
		<input type="text" id="time1" name="time1" class="Wdate" onfocus="WdatePicker();" />
			- -<input type="text" id="time2"  name="time2"  class="Wdate" onfocus="WdatePicker();" />	
	<input type="button" onclick="searchInfo()" value="搜索" class="btn btn-success" style="background: url(../img/secai.png);"/> 
	<input type="button" value="导出" class="btn btn-success" onclick="doExportExcel()" style="background: url(../img/secai.png);"/>
 &nbsp;&nbsp;&nbsp;
 
 <div id="tablediv">
		<form id="myForm">
			<div class="dwrapper">
	<table id="fixed_hdr2"  >
		<thead >
	<tr height="35px" style="background: url(../img/secai.png);" >
			<th></th>         
		<!-- 	<th>序号</th> -->
			<th>用户编号</th>
			<th>用户姓名</th>
			<th>小区名称</th>
			
			<th>楼栋</th>
			<th>单元</th>
			<th>户号</th>
			<th>已用当量</th>
			<th>风盘编号</th>
		<!--  ----------------10 -->		
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
     <!-- --------------------20 -->        
            <th>室内温度</th>
            <th>远程状态</th>
            <th>报警信息</th>
            <th>季节</th>
            <th>采集时间</th>
            <th>用户面积</th>
            
            <th>用户电话</th>
            <th>风盘地址</th>
            <th>备注</th>
	</tr>                    
	</thead>                      
 
<tbody id="yhInfo">
	<c:forEach  var="yh" items="${YhList}" varStatus="status">
	   <tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>	  
		<td><input type="checkbox" value="${yh.id}" /></td>  
		<%-- <td>${yh.id}</td>	 --%>
		<td>${yh.yhMessage.yhbh}</td>	
		<td>${yh.yhMessage.yhxm}</td>	
		<td>${yh.yhMessage.xqm}</td>	
		<td>${yh.yhMessage.ldh}</td>
		<td>${yh.yhMessage.dyh}</td>	
		<td>${yh.yhMessage.hh}</td>	
		<td>${yh.js}</td>
		<td>${yh.fpbh}</td>
	<!-- -----------------------------10----->
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
		<!-- ---------------------------20 -->
		<c:if test="${yh.jj =='00'}">
   		<td>夏季</td>
		</c:if>
		<c:if test="${yh.jj=='01'}">
   		<td>冬季</td>
		</c:if>
		
		<td>${yh.time}</td>
		 
		<td>${yh.yhMessage.mj}</td>
		<td>${yh.yhMessage.lxdh}</td>
		<td>${yh.yhMessage.fpdz}</td>
		<td>${yh.yhMessage.bz}</td>
		<%-- <c:if test="${yh.yhMessage.bz=='1'}">
		<td>无</td>
		</c:if> --%>
		
	 </tr>
	</c:forEach>
  </tbody>
  		 
</table>
</div>
</form>
</div>
</div>
</div>
</body>
</html>