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
		
		div.container {
			padding: 5px 10px;
			width: 2330px;
			margin: 10px auto;
		}
		
		.ft_container table tr {
			background: url(../img/secai.png);
		}
	</style>
 

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
			},{
				width : 100,
				align : 'center'
			},{
				width : 100,
				align : 'center'
			},{
				width : 120,
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
  <div class="panel-heading">首页信息</div>
 &nbsp;&nbsp;&nbsp;
	<table id="fixed_hdr2"  >
		<thead >
	<tr height="35px" style="background: url(../img/secai.png);" >
			<th></th>         
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
		<%-- <td>${yh.yhMessage.id}</td>	 --%>
		<td>${yh.yhMessage.yhbh}</td>	
		<td>${yh.yhMessage.yhxm}</td>	
		<td>${yh.yhMessage.xqm}</td>	
		<td>${yh.yhMessage.ldh}</td>
		<td>${yh.yhMessage.dyh}</td>	
		<td>${yh.yhMessage.hh}</td>	
		<td>${yh.yydl}</td>
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
	 </tr>
	</c:forEach>
  </tbody>
</table>
</div>
</div>
</body>
</html>