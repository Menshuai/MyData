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
					location.reload()
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
				location.reload()
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
				location.reload()

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
				width : 50,
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


<div>
<!-- style="width:65%; height: 60%; position: absolute; overflow: auto;text-align: center" -->
 <h3>中央空调信息</h3> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <div></div>
 
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
	
	<!-- 选择风盘地址：
	<select id="jj">
		<option value="02">--风盘地址--</option>
		<option value="00">01</option>
		<option value="01">02</option>
		<option value="01">03</option>
		<option value="01">04</option>
		<option value="04">05</option>
	</select>   -->
	<!-- <button type="button" class="btn btn-success" onclick="Fs()" style="background: url(../img/secai.png);">发送</button> -->
	 <button type="button" class="btn btn-success" onclick="DFs()" style="background: url(../img/secai.png);">单风盘操作</button>
	 <button type="button" class="btn btn-success" onclick="SFs()" style="background: url(../img/secai.png);">对一户所有风盘操作</button>
	<div></div>
 
 &nbsp;&nbsp;&nbsp;
 &nbsp;&nbsp;&nbsp;
	<table id="fixed_hdr2"  >
		<thead >
	<tr height="35px" style="background: url(../img/secai.png);" >
			<th></th>         
			<th>序号</th>
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
            <th>风盘个数</th>
            <th>备注</th>
	</tr>                    
	</thead>                      

<tbody id="yhInfo">
	<c:forEach  var="yh" items="${YhList}" varStatus="status">
	   <tr <c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>	  
		<td><input type="checkbox" value="${yh.yhMessage.yhbh}${yh.yhMessage.fpdz}" /></td>  
		<td>${yh.id}</td>	
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
		 <%--  <td><fmt:formatDate value="${yh.time}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
		<td>${yh.yhMessage.mj}</td>
		<td>${yh.yhMessage.lxdh}</td>
		<td>${yh.yhMessage.fpdz}</td>
		<td>${yh.yhMessage.bz}</td>
		 
		
	 </tr>
	</c:forEach>
  </tbody>
</table>
</div>

</body>
</html>