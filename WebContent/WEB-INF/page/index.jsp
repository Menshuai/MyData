<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>中央空调主页</title>
<link rel="stylesheet" type="text/css" href="../css/admin-all.css" />
<link rel="stylesheet" type="text/css" href="../css/base.css" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="../css/jquery-ui-1.8.22.custom.css" />
<script type="text/javascript" src="../js/jquery-1.7.2.js"></script>
<script type="text/javascript"
	src="../js/jquery-ui-1.8.22.custom.min.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<style type="text/css">
*{margin:0;padding:0;list-style-type:none; }
        #zhu{font-size: 16px;width:30px;height:140px; position: fixed;right:2px; overflow:hidden;z-index:9999; top:180px; background-color: #24CAB6;}
        #zhu ul{padding-left:30px;}
        #zhu p{border-bottom:1px solid #D4B4CE;font-size:20px;height:35px;line-height:40px ; width:1000px;background:#24CAB6;color:#fbfbfb; }
        #zhu li{height:35px;line-height:35px;font-size:14px;border-bottom:1px #D4B4CE dotted;right: 0px;position:inherit;background-color: #24CAB6;}
        #zhu li a{text-decoration:none;color:#fbfbfb;}
        #zhu .zu {background-color: #24CAB6;border:0px solid #D4B4CE;width:20px;height:270px;line-height:20px ;font-size:15px;position:absolute;text-align: center;top:10px}
        #zhu .zu a{color:#fbfbfb;text-decoration:none;}
</style>


</head>
<script type="text/javascript">

$(document).ready(function(){
    $("#zhu").mouseover(function(){
        $(this).stop();
        $(this).animate({width:180},400);
    })
    $("#zhu").mouseout(function(){
        $(this).stop();
        $(this).animate({width:30},300);
    });
});
$(document).ready(function(){
    $("#zhu").mouseover(function(){
        $(this).stop();
        $(this).animate({width:180},400);
    })
    $("#zhu").mouseout(function(){
        $(this).stop();
        $(this).animate({width:30},300);
    });
});

function add(title, url, w, h){
	layer_show(title, url, w, h);
}


function tc(){ //安全退出
	window.location='<%=basePath%>user/toLogin.action';
}

</script>
<body>
	<div class="warp">
		<!--头部开始-->
		<div class="top_c">
			<div class="top-menu">
				<ul class="top-menu-nav"> 
					<li><a target="Conframel" href="<%=basePath%>DataController/DataMe.action">首页</a></li>
				 	<li><a target="Conframel" href="<%=basePath%>Jzq/JzqMe.action">信息管理</a></li>
					<li><a target="Conframel" href="<%=basePath%>JfController/JfMe.action">缴费管理</a></li>
					<li><a target="Conframel" href="<%=basePath%>DataController/SbglMe.action">设备管理</a></li>
					 
					<li><a target="Conframel" href="<%=basePath%>YhMessageCon/HomeMe.action">数据报表</a></li>
					<li><a href="#">客服管理<i class="tip-up"></i></a>
						<ul class="kidc">							
							<li><a target="Conframel" href="<%=basePath%>Repair/RepairMe.action">报修登记</a></li>
							<li><a target="Conframel" href="<%=basePath%>Repair/SqazMe.action">申请安装</a></li>
						</ul>  
					</li>  
					<li><a target="Conframel" href="<%=basePath%>RzController/RzMe.action">操作日志</a></li>
					<li><a target="Conframel" href="<%=basePath%>DataController/YccxMe.action">异常查询</a></li>
					<li><a target="Conframel" href="<%=basePath%>Home/XtszMe.action">系统设置</a></li>
				</ul>
			</div>
			<div class="bottom_c1 ">
			
			</div>
			<div class="top-nav">
			
										
				&nbsp;&nbsp;&nbsp;
			
				 欢迎您！ ${UserName} &nbsp;&nbsp;&nbsp;&nbsp;<a onclick="add('修改密码','<%=basePath%>user/xgmm.action','600','400')">修改密码</a>| 
				 <a target="Conframe" onclick="add('用户注册','<%=basePath%>user/yhzc.action','600','300')">用户注册</a>| <a href="#" onclick="tc()" style="color: white;">安全退出</a>
			</div>
		</div>
		 
		<!--左边框架开始-->
		<div class="left_c left">
			<h1>信息列表</h1>
			  <div class="acc">
				<iframe scrolling="no" width="100%"   name="Conframel" id="Conframel" src="<%=basePath%>zz/ztree.action" frameborder="0" onload="this.height=0;var fdh=(this.Document?this.Document.body.scrollHeight:this.contentDocument.body.offsetHeight);this.height=(fdh>1700?fdh:1700)"></iframe>
			</div>
		</div>
		<!--左边框架结束-->
		
		
		<!--右边框架开始-->
		 <div class="right_c">
			  <div class="nav-tip" onClick="javascript:void(0)">&nbsp;</div>	 
		 </div>	 
		 
		<div class="Conframe" >
			<iframe  name="Conframe" id="Conframe"  src="<%=basePath%>DataController/data.action"></iframe>
		</div>
		<!--右边框架结束-->


		<!--底部开始-->
		<div class="bottom_c" style="background-image: url('../img/bjt.png'); ">Copyright &copy;2018-4月  河南众源系统工程有限公司
			版权所有
		</div>
		<!--底部结束-->
	 
</body>
<script type="text/javascript" src="../js/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../js/static/h-ui.admin/js/H-ui.admin.js"></script>
</html>