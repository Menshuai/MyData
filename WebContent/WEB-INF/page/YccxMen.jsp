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

	window.onload = function() {//项目名      controler类名    方法名
        window.open("/Data/DataController/Yccx.action","Conframe");
    }
	</script>
	
	  <script type="text/javascript">
	  
	  function yccx(){// 异常查询
		    window.open("/Data/DataController/Yccx.action","Conframe");
		} 
	  function yccxHistory(){// 历史异常查询
		    window.open("/Data/DataController/YccxHistory.action","Conframe");
		} 
	 </script>
<body>
<br>
 <button  onclick="yccx()"  type="button" class="btn btn-success btn-block" 
 			style="background-image: url('../img/secai.png');border:1px" type="button" >异常查询</button>
<button  onclick="yccxHistory()"  type="button" class="btn btn-success btn-block" 
 			style="background-image: url('../img/secai.png');border:1px" type="button" >异常历史查询</button> 
</body>
	<body>
	</body>
</html>