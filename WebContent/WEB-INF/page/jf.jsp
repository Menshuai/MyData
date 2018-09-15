<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>用户缴费</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/biao.js"></script>

<!-- <link href="../css/theme.min.css" type="text/css" rel="stylesheet" /> -->
<!-- <link href="../css/style.css" type="text/css" rel="stylesheet" /> -->
<!-- <link href="../css/bootstrap.css" rel="stylesheet"> -->
 <!--  <link href="../css/thene.min.css" type="text/css" rel="stylesheet" />
<link href="../css/style.css" type="text/css" rel="stylesheet" />   -->

<link href="../js/layer/2.4/skin/layer.css" rel="stylesheet" type="text/css" />
<link href="../js/layui/css/layui.css" rel="stylesheet" type="text/css" />
<script src="../js/layer/2.4/layer.js" type="text/javascript"></script>
<script src="../js/layui/layui.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet" media="all" />
<script src="https://code.jquery.com/jquery.min.js" type="text/javascript"></script>
<script src="https://meetselva.github.io/fixed-table-rows-cols/js/sortable_table.js" type="text/javascript"></script>
<script src="../js/fixed_table_rc.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<style>
.touyou {
	width: 100px;
	height: 80px;
	background-color: #3CF;
	text-align: center
}
.touyou img {
	margin: 22px auto;
}
html, body {
	font-family: Arial, ​​sans-serif;
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
.tou{
	 display: flex;

	}
.div1{
	width:300px;
	height:100px;
	font-size:20px;
	line-height:90px;
	background-color:#4BC9D3;
	}	
.div2{
	width:300px;
	height:100px;
	font-size:20px;
	line-height:90px;
	background-color:#4BC9D3;
	margin-left:20px;
	}
.div3{
	width:300px;
	height:100px;
	font-size:20px;
	line-height:90px;
	background-color:#FFFF00;
	margin-top:10px;
	
	}
.div4{
	width:300px;
	height:100px;
	font-size:20px;
	line-height:90px;
	background-color:#FFFF00;
	margin-left:400px;
	margin-top:40px;
	}	
.div5{
	background-color:#4BC9D3;
	margin-left:100px;
	}
</style>
<script>
	$(function() {

		$('#fixed_hdr2').fxdHdrCol({
			fixedCols : 0,
			width : "100%",
			height : 700,
			colModal : [ {
				width : 30,
				align : 'center'
			}, {
				width : 120,
				align : 'center'
			}, {
				width : 120,
				align : 'center'
			}, {
				width : 70,
				align : 'center'
			}, {
				width : 65,
				align : 'center'
			}, {
				width : 65,
				align : 'center'
			}, {
				width : 65,
				align : 'center'
			}, {
				width : 90,
				align : 'center'
			}, {
				width : 90,
				align : 'center'
			}, {
				width : 100,
				align : 'center'
			}, {
				width : 80,
				align : 'center'
			}, {
				width : 70,
				align : 'center'
			},
			
			{
				width : 80,
				align : 'center'
			}, {
				width : 80,
				align : 'center'
			},
			 {
				width : 70,
				align : 'center'
			},
			 {
				width : 70,
				align : 'center'
			},{
				width : 130,
				align : 'center'
			}, {
				width : 130,
				align : 'center'
			}, {
				width : 200,
				align : 'center'
			}, ],
			sort : true
		});

	});
</script>

<link href="../css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="../js/bootstrap.js">
	
</script>
<script src="../js/main.js"></script>
<!-- <script src="../js/wljf/wljf-jf.js"></script> -->
<script type="text/javascript">
	//数据不允许为空
	function add() {
		var yhm = $("#add input[name=yhm]");
		var xqm = $("#add select[name=xqm]");
		var ldh = $("#add select[name=ldh]");
		var dyh = $("#add select[name=dyh]");
		var hh = $("#add input[name=hh]");
		if (yhm.val() == null || yhm.val() == "" || xqm.val() == null
				|| xqm.val() == "" || ldh.val() == null || ldh.val() == ""
				|| dyh.val() == null || dyh.val() == "" || hh.val() == null
				|| hh.val() == "") {
			sAlert('信息不能为空，请填写完整!');
			return false;
		}
		var hh = document.getElementById("hh").value;
		var yzbh = document.getElementById("yzbh").value;
		var jfje = document.getElementById("jfje").value;
		var hjje = document.getElementById("hjje").value;
		if (isNaN(hh)) {

			sAlert('户号必须是数字！');
			document.getElementById("hh").value = "";
			return;
		}
		if (isNaN(jfje)) {

			sAlert('缴费金额必须是数字！');
			document.getElementById("hh").value = "";
			return;
		}
		if (isNaN(hjje)) { 

			sAlert('合计金额必须是数字！');
			document.getElementById("jfje").value = "";
			return;
		}
		if (isNaN(yzbh)) {

			sAlert('业主编号必须是数字！并且为八位数');
			document.getElementById("hjje").value = "";
			return;
		}
		if (yhbh.length != 8) {
			sAlert('用户编号必须是数字！并且为八位数');
			document.getElementById("hjje").value = "";
			return;
		}
		$("#add form").submit();
	}

	//修改时数据不能为空
	function edit() {
		var yzbh = document.getElementById("edit_yzbh").value;//业主编号
		var jfje = document.getElementById("jfjeId").value;//缴费金额
		var hjje = document.getElementById("edit_hjje").value;
		var syje="";
		var yf = document.getElementById("yf").value;
		var jf = $("input[name='jf']:checked").val();
		var time=document.getElementById("time").innerText;
		if(jf==undefined){
			jf=0;
		}
		if ( jfje == "") {
			sAlert('信息不能为空，请填写完整!');
			return false;
		}
		if (isNaN(jfje)) {

			sAlert('缴费金额必须是数字！');
			document.getElementById("edit_houseNo").value = "";
			return;
		}
		$.ajax({
			type : "post",
			url : "update.action",//获取json数据
			dataType : "json",
			data : {
				"yzbh" : yzbh,
				"jfje" : jfje,
				"hjje" : hjje,
				"syje" : syje,
				"yf" : yf,
				"jf" : jf,
				"time":time,
			},
			success : function(data) {
				var d = data.js;
				if (d == "success") {
					//询问框
					layer.confirm('缴费成功,你要打印凭证吗?', {
					  skin: 'layui-layer-molv', //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
					  btn: ['前往','不要'] //按钮
					}, function(){
						  layer.msg('跳转成功', {icon: 1});
						  //跳转到 缴费页面 FaP页面
						   window.location="/Data/JfController/findFap.action?yhbh="+yzbh;
						}, function(){
						  searchInfo();//刷新页面，应该是在点击取消后刷新 
						  layer.msg('欢迎下次再来', {
						    time:3000, //30s后自动关闭
						  });
					});
					
				}else{
					alert("缴费失败！");
				}
				
			},
		})
		$("#edit").modal("hide");
		/* $("#edit form").submit(); */
	}

	function openaddUserPage() {
		$.ajax({
			type : "post",
			url : "findYhNameList.action",//获取json数据
			dataType : "json",
			success : function(data) {
				var dd = data;
				var d = dd.xqlist;
				$("#xqmId option:gt(0)").remove();
				for (var i = 0; i < d.length; i++) {
					var xqm = d[i].xqm;
					var opt = "<option value='"+xqm+"'>" + xqm + "</option>";
					$("#xqmIdS").append(opt);

				}
			},
		})

		$("#xqmIdS").change(
				function() {
					$.get("findYhldhbyxqm.action?xqm=" + $("#xqmIdS").val(),
							function(data) {
								var dd = data;
								var d = dd.xqlist;
								$("#ldhId option:gt(0)").remove();
								$("#dyhId option:gt(0)").remove();
								for (var i = 0; i < d.length; i++) {
									var ldh = d[i].ldh;
									var opt = "<option value='"+ldh+"'>" + ldh
											+ "</option>"
									$("#ldhIdS").append(opt);
								}
							});
				});

		$("#ldhIdS").change(
				//根据小区楼栋号获取  单元号
				function() {
					$.get("findYhdyhByBuild.action?ldh=" + $("#ldhIdS").val()
							+ "&xqm=" + $("#xqmIdS").val(), function(data) {
						var dd = data;
						var d = dd.dyhList;
						$("#dyhId option:gt(0)").remove();
						for (var i = 0; i < d.length; i++) {
							var dyh = d[i].dyh;
							var opt = "<option value='"+dyh+"'>" + dyh
									+ "</option>"
							$("#dyhIdS").append(opt);
						}
					});
				});
		$("#add").modal({
			keyboard : false
		});

	}
	function openEditUserPage() {
		var ckbs = $("#users input[type=checkbox]:checked");
		if (ckbs.length == 0) {
			sAlert("请选择要缴费的用户");
			return false;
		}
		if (ckbs.length > 1) {
			sAlert("对不起一次只能选择一个用户，您选择了" + ckbs.length + "个用户");
			return false;
		}
		var id = ckbs.val();
		var yhName = ckbs.parent().next().text();
		var xqName = ckbs.parent().next().next().text();
		var buildNo = ckbs.parent().next().next().next().text();
		var cellNo = ckbs.parent().next().next().next().next().text();
		var houseNo = ckbs.parent().next().next().next().next().next().text();
		var yzbh = ckbs.parent().next().next().next().next().next().next()
				.text();
		var lxdz = ckbs.parent().next().next().next().next().next().next()
				.next().text();
		var mj = ckbs.parent().next().next().next().next().next().next()
		.next().next().text();
		var hjje = ckbs.parent().next().next().next().next().next().next()
				.next().next().next().next().next().text();
		$("#edit_yhName").val(yhName);
		$("#edit_xqName").val(xqName);
		$("#edit_buildNo").val(buildNo);
		$("#edit_cellNo").val(cellNo);
		$("#edit_houseNo").val(houseNo);
		$("#edit_yzbh").val(yzbh);
		$("#edit_lxdz").val(lxdz);
		$("#edit_hjje").val(hjje);
		$("#edit_mj").val(mj);
		
		$.get("yf.action", function(data) {
			var dd = data;
			var d = dd.yf;
			$("#yf option:gt(0)").remove();
			for (var i = 0; i < d.length; i++) {
				var yf = d[i];
				
				var opt = "<option value='"+yf+"'>" + yf
						+ "</option>"
				$("#yf").append(opt);
			}
		});
		
		$("#edit").modal({
			keyboard : false
		});
		
		
		
	}
	//删除
	function openDeletePage() {
		var ckbs = $("#users input[type=checkbox]:checked");
		if (ckbs.length == 0) {
			sAlert("请选择要删除的用户");
			return false;
		}
		if (ckbs.length > 1) {
			sAlert('对不起一次只能删除一个');
			return false;
		}
		var id = [];
		$.each(ckbs, function(index, data) {
			id[index] = $(data).val();
		});
		var names = ckbs.parent().next();
		var deleteUserNames = [];
		$("#deleteUser .modal-body .col-sm-7").empty();
		$("#deleteUser .modal-body .col-sm-7").append(
				"<h4><span class='text-danger'>你确定要删除下面这" + ckbs.length
						+ "个用户吗？<br/>" + "</span></h4>");
		$.each(names, function(index, data) {
			deleteUserNames[index] = $(data).text();
			$("#deleteUser .modal-body h4").append(
					"<span class='small  '>" + "&nbsp;&nbsp;[" + $(data).text()
							+ "]&nbsp;&nbsp;" + "</span>");
		});
		$("#deleteUser .modal-body .col-sm-7")
				.append(
						"<div><button type='button' id='btn_delete_user' class='btn btn-sm btn-primary'>确定</button>&nbsp;<button id='closeDeleteUserPage' class='btn btn-sm btn-primary' type='button'>取消</button></div>");
		$("#closeDeleteUserPage").click(function() {
			$("#deleteUser").modal("hide");
		});
		$("#btn_delete_user").click(function() {
			$.ajax({
				url : "delete.action?id=" + id,
				type : "post",
				success : function() {
					$("#deleteUser").modal("hide");
					window.location.href = "JFfindList.action";
				}
			});
		});

		$("#deleteUser").modal({
			keyboard : false
		});
	}
</script>

<script type="text/javascript">
	/*页面加载就开始执行js*/
	$(document).ready(
			//根据小区获取  楼栋号
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
									var opt = "<option value='"+ldh+"'>" + ldh
											+ "</option>"
									$("#ldhId").append(opt);
								}
							});
						});

				$("#ldhId").change(
						//根据小区楼栋号获取  单元号
						function() {
							$.get("findYhdyhByBuild.action?ldh="
									+ $("#ldhId").val() + "&xqm="
									+ $("#xqmId").val(), function(data) {
								var dd = data;
								var d = dd.dyhList;
								$("#dyhId option:gt(0)").remove();
								for (var i = 0; i < d.length; i++) {
									var dyh = d[i].dyh;
									var opt = "<option value='"+dyh+"'>" + dyh
											+ "</option>"
									$("#dyhId").append(opt);
								}
							});
						});
				
				$("#yf").change(
						function() {
							$("input:radio[name=jf]").attr("checked",false);
							var mj = $('#edit_mj').val();
							var yf=$("#yf").val();
							$.get("yfCheck.action?yf="
									+ yf+"&mj="+mj, function(data) {
										var je=data.je;
										var time=data.time;
										var tim=document.getElementById("time");
										tim.innerHTML=time;
							            $('#jfjeId').val(je);
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
		var type=$('#type').val();
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
						"type":type,
					},
					success : function(data) {
						$("#users").empty();
						var d = data.jfs;
						for (var i = 0; i < d.length; i++) {
							var id = d[i].id;
							var yhxm = d[i].yhMessage.yhxm;
							var xqm = d[i].yhMessage.xqm;
							var ldh = d[i].yhMessage.ldh;
							var dyh = d[i].yhMessage.dyh;
							var hh = d[i].yhMessage.hh;
							var mj = d[i].yhMessage.mj;
							var yzbh = d[i].yzbh;
							var lxdh = d[i].yhMessage.lxdh;
							var jfje = d[i].jfje;
							var hjje = d[i].hjje;
							var yydl = d[i].yydl;
							var userName = d[i].userName;
							var time = FormatDate(d[i].time);
							var type=d[i].type;
							if(type==0){
								type="包月";
							}else if(type==1){
								type="按量";
							}else if(type==3){
								type="包季"
							}else if(type==4){
								type="包年"
							}else if(type==undefined){
								type="";
							}else{
								type="";
							}
							var startTime=d[i].startTime;
							var endTime=d[i].endTime;
							if(startTime==undefined){
								startTime="";
							}
							if(endTime==undefined){
								endTime="";
							}
							html += "<tr>";
							html += "<td class='text-center'><input type='checkbox'  value='"+id+"'/></td>";
							html += "<td class='text-center'>" + yhxm + "</td>";
							html += "<td class='text-center'>" + xqm + "</td>";
							html += "<td class='text-center'>" + ldh + "</td>";
							html += "<td class='text-center'>" + dyh + "</td>";
							html += "<td class='text-center'>" + hh + "</td>";
							html += "<td class='text-center'>" + yzbh + "</td>";
							html += "<td class='text-center'>" + lxdh + "</td>";
							html += "<td class='text-center'>" + mj + "</td>";
							html += "<td class='text-center'>" + yydl + "</td>";
							html += "<td class='text-center'>" + jfje + "</td>";
							html += "<td class='text-center'>" + hjje + "</td>";
							html += "<td class='text-center'>" + userName+ "</td>";
							html += "<td class='text-center'>" + type+ "</td>";
							html += "<td class='text-center'>" + startTime+ "</td>";
							html += "<td class='text-center'>" + endTime+ "</td>";
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
		var type=$('#type').val();
		window.open("JfExportExcel.action?xqm=" + xqm + "&ldh=" + ldh + "&dyh="
				+ dyh + "&hh=" + hh+"&type="+type);
	}
</script>
<script type="text/javascript">

function onJe(){
		var jf = $("input[name='jf']:checked").val();
        $("#yf").get(0).selectedIndex=0; 
		var mj = $('#edit_mj').val();
		$.ajax({
					url : "jfCheck.action",
					async : false,
					dataType : "json",
					data : {
						"jf" : jf,
						"mj" :mj,
					},
					success : function(data) {
					var je=data.je;
					var time=data.time;
					var tim=document.getElementById("time");
					tim.innerHTML=time;
		            $('#jfjeId').val(je);
					}
	})
}
</script>
</head>
<body>
<table width="1300" height="200" border="0"   >
  <tr >
     <td width="252"><div style="width:250px; height:80px; border:1px solid #999; display:flex;">
        <div  class="touyou" > <img src="../img/i.png" width="40" height="40" /> </div>
        <div align="center" style=" width:250px;height:150px; margin-top:25px;">
          <div style="font-size: 20px ;color: red;">${jfbs}</div>
          <div style="margin-top:5px;">今日缴费笔数</div>
        </div>
      </div>
      </td>
      
     <td width="252"><div style="width:250px; height:80px; border:1px solid #999; display:flex;">
        <div  class="touyou" > <img src="../img/i.png" width="40" height="40" /> </div>
        <div align="center" style=" width:250px;height:150px; margin-top:25px;">
          <div style="font-size: 20px ;color: red;">${jfzje}</div>
          <div style="margin-top:5px;">今日缴费总金额</div>
        </div>
      </div>
      </td>
      
   <td width="291"><td width="4"><td width="4"><td width="5"><td width="6"><td width="9"><td width="15"><td width="10"><td width="606" rowspan="2">
<!--         
<div class="aboluo-w-700">
	<div class="aboluo-leftdiv">
		<div class="aboluo-tools">
			<div class="aboluo-calendar-select-year"></div>
			<div class="aboluo-calendar-month">
				<a class="aboluo-month-a-perv" href="javascript:;">&lt; </a>
				<a class="aboluo-month-a-next" href="javascript:;"> &gt;</a>
			</div>
			<input type="button" class="aboluo-toToday" value="返回今天" />
		</div>
		<div class="aboluo-rilidiv">
			<table class="aboluo-rilitable" cellspacing="0" cellpadding="0" >
				<thead class="aboluo-rilithead">
					<tr>
						<th>一</td>
						<th>二</td>
						<th>三</td>
						<th>四</td>
						<th>五</td>
						<th style="color:red;">六</td>
						<th style="color:red;">日</td>
					</tr>
				</thead>
			</table>
		</div>
	</div>
    	   <div class="aboluo-rightdiv">
		  <p class="aboluo-xssj"><p>  
	  	  <p class="aboluo-currday">.</p>  黄色背景哪一天  
	   </div>      
</div> -->
<script type="text/javascript" src="css/jquery-1.8.3.js"></script>
<script type="text/javascript" src="css/script.js"></script>
<td width="93"></td>
</tr>

  <tr>
     <td width="565"><div style="width:250px; height:80px; border:1px solid #999; display:flex;">
        <div  class="touyou" > <img src="../img/i.png" width="40" height="40" /> </div>
        <div align="center" style=" width:250px;height:150px; margin-top:25px;">
          <div style="font-size: 20px;color: red;">${jfBzbs}</div>
          <div style="margin-top:5px;">本周缴费笔数</div>
        </div>
      </div></td>
     <td width="252"><div style="width:250px; height:80px; border:1px solid #999; display:flex;">
        <div  class="touyou" > <img src="../img/i.png" width="40" height="40" /> </div>
        <div align="center" style=" width:250px;height:150px; margin-top:25px;">
          <div style="font-size: 20px;color: red;">${jfBzjfje}</div>
          <div style="margin-top:5px;">本周缴费总金额</div>
        </div>
      </div>
      </td>

  </tr>
  <tr>
  </tr>
</table>
 <!-- 日历页面-->
  	<%--   <c:import url="日历222.jsp"></c:import>   --%>
  	
<script type="text/javascript" src="../js/script.js"></script>
<!-- <div class="panel-heading">业主缴费信息</div> -->
		<div class="panel-body">
			<div id="top">
				<label for="xqmId">选择小区</label> <select id="xqmId" name="xqm">
					<option value="--选择小区名称--">--选择小区名称--</option>
					<c:forEach items="${XqNameList}" var="list">
						<option>${list.xqm}</option>
					</c:forEach>
				</select> &nbsp;&nbsp;&nbsp; <label for="ldhId">楼栋号</label> <select
					name="ldh" id="ldhId">
					<option value="">--选择楼栋号--</option>
				</select> &nbsp;&nbsp;&nbsp; <label for="dyhId">单元号</label> <select
					name="dyh" id="dyhId">
					<option value="">--选择单元号--</option>
				</select> &nbsp;&nbsp;&nbsp; 户号：<input type="text" name="hh" id="hhId"
					value="${hh}" size=10px />
				 &nbsp;&nbsp;&nbsp; 
				缴费类型<select id="type">
				<option value="2">-选择缴费类型-</option>
				 <option value="1">按量</option>
				 <option value="0">包月</option>
				 <option value="3">包季</option>
				 <option value="4">包年</option>
				   </select>	
				<!-- <label for="readMTime">选择时间:</label>
		<input type="text" id="time1" name="time1" class="Wdate" onfocus="WdatePicker();" />
			- -<input type="text" id="time2"  name="time2"  class="Wdate" onfocus="WdatePicker();" /> -->
				<input type="button" onclick="searchInfo()" value="搜索"
					class="btn btn-success" style="background: url(../img/secai.png);" />
			<!-- 	<button type="button" class="btn btn-success"
					onClick="openaddUserPage()"
					style="background: url(../img/secai.png);">添加</button> -->
				&nbsp;&nbsp;
				<button type="button" class="btn btn-success"
					onClick="openEditUserPage()"
					style="background: url(../img/secai.png);">缴费</button>
				&nbsp;&nbsp;
				<!-- <button type="button" class="btn btn-success"
					onClick="openDeletePage()"
					style="background: url(../img/secai.png);">删除</button> -->
				<input type="button" value="导出" class="btn btn-success"
					onclick="doExportExcel()"
					style="background: url(../img/secai.png);" /> &nbsp;&nbsp;&nbsp;
					
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
								<!-- <th>用户编号</th> -->
								<th>业主编号</th>
								<th>联系方式</th>
								<th>用户面积</th>
								<th>已用当量</th>
								<th>缴费金额</th>
								<th>合计金额</th>
								
								<th>剩余金额</th>
								<th>已用金额</th>
								
								<th>缴费人</th>
								<th>缴费类型</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>缴费时间</th>
							</tr>
						</thead>
						<tbody id="users">
							<c:forEach var="jf" items="${jf}" varStatus="status">
								<tr
									<c:if test="${status.index%2==1 }">style="background-color:#eef3fa"</c:if>>
									<td><input type="checkbox" value="${jf.yzbh}" /></td>
									<td>${jf.yhMessage.yhxm}</td>
									<td>${jf.yhMessage.xqm}</td>
									<td>${jf.yhMessage.ldh}</td>
									<td>${jf.yhMessage.dyh}</td>
									<td>${jf.yhMessage.hh}</td>
									<td>${jf.yzbh}</td>
									<td>${jf.yhMessage.lxdh}</td>
									<td>${jf.yhMessage.mj}</td>
									<td>${jf.yydl}</td>
									<td>${jf.jfje}</td>
									<td>${jf.hjje}</td>
									
									<td>${jf.syje}</td>
									<td>${jf.yyje}</td>
									
									<td>${jf.userName}</td>
									<c:choose>
									<c:when test="${jf.type==0}">
									  <td>包月</td>
									</c:when>
									<c:when test="${jf.type==1}">
									<td>按量</td>
									</c:when>
									<c:when test="${jf.type==3}">
									<td>包季</td>
									</c:when>
									<c:when test="${jf.type==4}">
									<td>包年</td>
									</c:when>
									<c:otherwise>
									<td></td>
									</c:otherwise>
									</c:choose>
									<td>${jf.startTime}</td>
									<td>${jf.endTime}</td>
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
						<form class="form form-horizontal" action="add.action"
							method="post">
							<div class="form-group">
								<label for="yhm" class="col-sm-2 col-sm-offset-3 control-label">用户名</label>

								<div class="col-sm-4">
									<input id="yhm" type="text" name="yhm" class="form-control"
										placeholder="用户名" />
								</div>
							</div>

							<div class="form-group">
								<label for="xqmIdS"
									class="col-sm-2 col-sm-offset-3 control-label">小区名</label> <select
									name="xqm" id="xqmIdS">
									<option value=-1>--选择小区名--</option>
								</select>
							</div>

							<div class="form-group">

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
									<input id="hh" type="text" name="hh" class="form-control"
										placeholder="户号" />
								</div>
							</div>
							<div class="form-group">
								<label for="yzbh" class="col-sm-2 col-sm-offset-3 control-label">业主编号</label>

								<div class="col-sm-4">
									<input id="yzbh" type="text" name="yzbh" class="form-control"
										placeholder="业主编号" />
								</div>
							</div>

							<div class="form-group">
								<label for="lxdh" class="col-sm-2 col-sm-offset-3 control-label">联系方式</label>

								<div class="col-sm-4">
									<input id="lxdh" type="text" name="lxdh" class="form-control"
										placeholder="联系电话" />
								</div>
							</div>
							<div class="form-group">
								<label for="jfje" class="col-sm-2 col-sm-offset-3 control-label">缴费金额</label>

								<div class="col-sm-4">
									<input id="jfje" type="text" name="jfje" class="form-control"
										placeholder="缴费金额" />
								</div>
							</div>
							<div class="form-group">
								<label for="hjje" class="col-sm-2 col-sm-offset-3 control-label">合计金额</label>

								<div class="col-sm-4">
									<input id="hjje" type="text" name="hjje" class="form-control"
										placeholder="合计金额" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-5">
									<button type="button" onclick="add()"
										class="btn btn-primary btn-sm">提交</button>
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
						<h4 class="text-center" style="padding: 0; margin: 0;">业主缴费</h4>
					</div>
					<div class="modal-body bg-info">
						<form class="form form-horizontal" action="update.action"
							method="post">
							<input type="hidden" id="edit_id" name="id" />
							<input type="hidden" id="edit_yhbh" name="yhbh" />

							<div class="form-group">
								<label for="edit_yhName"
									class="col-sm-2 col-sm-offset-3 control-label">用户名</label>

								<div class="col-sm-4">
									<input id="edit_yhName" type="text" name="yhName"
										class="form-control" placeholder="用户名" readonly="readonly" />
								</div>
							</div>


							<div class="form-group">
								<label for="edit_xqName"
									class="col-sm-2 col-sm-offset-3 control-label">小区名</label>

								<div class="col-sm-4">
									<input id="edit_xqName" type="text" name="xqName"
										class="form-control" placeholder="小区名" readonly="readonly" />
								</div>
							</div>

							<div class="form-group">
								<label for="edit_buildNo"
									class="col-sm-2 col-sm-offset-3 control-label">楼栋号</label>
								<div class="col-sm-4">
									<input id="edit_buildNo" type="text" name="buildNo"
										class="form-control" placeholder="楼栋号" readonly="readonly" />
								</div>
							</div>
							<div class="form-group">
								<label for="edit_cellNo"
									class="col-sm-2 col-sm-offset-3 control-label">单元号</label>
								<div class="col-sm-4">
									<input id="edit_cellNo" type="text" name="cellNo"
										class="form-control" placeholder="单元号" readonly="readonly" />
								</div>
							</div>
							<div class="form-group">
								<label for="edit_houseNo"
									class="col-sm-2 col-sm-offset-3 control-label">户号</label>

								<div class="col-sm-4">
									<input id="edit_houseNo" type="text" name="houseNo"
										class="form-control" placeholder="户号" readonly="readonly" />
								</div>
							</div>
							<div class="form-group">
								<label for="edit_yhbh"
									class="col-sm-2 col-sm-offset-3 control-label">业主编号</label>

								<div class="col-sm-4">
									<input id="edit_yzbh" type="text" name="yzbh"
										class="form-control" placeholder="业主编号" readonly="readonly" />
								</div>
							</div>
							<div class="form-group">
								<label for="edit_lxdz"
									class="col-sm-2 col-sm-offset-3 control-label">联系地址</label>

								<div class="col-sm-4">
									<input id="edit_lxdz" type="text" name="lxdz"
										class="form-control" placeholder="联系地址" readonly="readonly" />
								</div>
							</div>
							<div class="form-group">
							<label for="edit_mj"
									class="col-sm-2 col-sm-offset-3 control-label">用户面积</label>
							<div class="col-sm-4">
							<input id="edit_mj" type="text" name="mj"
							 class="form-control" placeholder="用户面积" readonly="readonly">
							</div>
							
							</div>
							<div class="form-group">
 									<div class="col-sm-8 col-sm-offset-2 control-label" > 
									<input type="radio" onclick="onJe()" name="jf"  value="1"> 按量 
								&nbsp;&nbsp;&nbsp; 									   包月:
									<select id="yf">
									<option value="0">-选择月份-</option>
								    </select>
								      &nbsp;&nbsp;&nbsp;  
									 <input type="radio" onclick="onJe()" name="jf"  value="3"> 包季 
									 &nbsp;&nbsp;&nbsp; 
									 <input type="radio" onclick="onJe()" name="jf" value="4"> 包年
									
								</div> 
							</div>
							<div class="form-group">
							<label for="edit_grTime"
							 		class="col-sm-2 col-sm-offset-3 control-label">供热时间</label>
								<div class="col-sm-4  control-label" id="time" style="color: red">
								
								</div>
							</div>


							<div class="form-group">
								<label for="edit_jfje"
									class="col-sm-2 col-sm-offset-3 control-label">预交金额</label>

								<div class="col-sm-4">
									<input id="jfjeId" type="text" name="jfje" class="form-control"
										placeholder="预交金额" />
								</div>
							</div>
							<div class="form-group">
								<label for="edit_hjje"
									class="col-sm-2 col-sm-offset-3 control-label">合计金额</label>

								<div class="col-sm-4">
									<input id="edit_hjje" type="text" name="hjje"
										class="form-control" placeholder="合计金额" readonly="readonly" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-5">
									<button type="button" onclick="edit()"
										class="btn btn-primary btn-sm">提交</button>
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