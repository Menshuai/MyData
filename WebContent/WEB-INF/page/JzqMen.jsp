<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>报修模块左边按钮栏</title>
 	<script type="text/javascript" src="../js/static/h-ui.admin/js/H-ui.admin.js"></script>
<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet"	media="all" />
<link href="../css/bootstrap.css" type="text/css" rel="stylesheet"	/>
<script src="../js/jquery-1.11.1.min.js" type="text/javascript"></script> 
	 <script type="text/javascript">
	window.onload = function() {//项目名      controler类名  方法名
        window.open("/Data/Jzq/JzqfindList.action","Conframe");
    }
	</script>
	
	 <script type="text/javascript">
	  function jzq5(){//集中器详情
		    window.open("/Data/Jzq/JzqfindList.action","Conframe");
		} 
	  
	  function yhb1(){//用户表
		    window.open("/Data/YhMessageCon/yhfindList.action","Conframe");
		  
		} 
	  
	  function fpb2(){//风盘表
		    window.open("/Data/Fp/FpfindList.action","Conframe");
		} 
	  
	  function cg3(){//层管表
		    window.open("/Data/Cg/CgfindList.action","Conframe");
		} 
	  
	  function yhlb4(){//用户类别
		    window.open("/Data/Yhlb/YhlbfindList.action","Conframe");
		} 
	  
	  function jzq5(){//集中器详情
		    window.open("/Data/Jzq/JzqfindList.action","Conframe");
		} 
	  function glb6(){//功率表
		    window.open("/Data/Gl/GlfindList.action","Conframe");
		} 
	   
	  function dj7(){//单价
		    window.open("/Data/Djb/DjbfindList.action","Conframe");
		} 
	 
	 </script>

<body>
<br>
<button  onclick="jzq5()"  type="button" class="btn btn-success btn-block" 
 				style="background-image: url('../img/secai.png');border:1px" type="button" >集中器信息</button>
 				
 <button  onclick="yhb1()"  type="button" class="btn btn-success btn-block" 
 				style="background-image: url('../img/secai.png');border:1px" type="button" >用户信息</button>
 				
 <button  onclick="fpb2()"  type="button" class="btn btn-success btn-block" 
 				style="background-image: url('../img/secai.png');border:1px" type="button" >风盘信息</button>

 <button  onclick="cg3()"  type="button" class="btn btn-success btn-block" 
 				style="background-image: url('../img/secai.png');border:1px" type="button" >层管信息</button>  
 
 <button  onclick="yhlb4()"  type="button" class="btn btn-success btn-block" 
 				style="background-image: url('../img/secai.png');border:1px" type="button" >用户类别</button>
 				
 <button  onclick="jzq5()"  type="button" class="btn btn-success btn-block" 
 				style="background-image: url('../img/secai.png');border:1px" type="button" >集中器信息</button>
 				

 <button  onclick="glb6()"  type="button" class="btn btn-success btn-block" 
 				style="background-image: url('../img/secai.png');border:1px" type="button" >功率表信息</button>
 				
 <button  onclick="dj7()"  type="button" class="btn btn-success btn-block" 
 				style="background-image: url('../img/secai.png');border:1px" type="button" >单价信息</button>  
 				
 
</body>
	 
</html>