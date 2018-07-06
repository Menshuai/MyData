<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
 	<script type="text/javascript" src="../js/static/h-ui.admin/js/H-ui.admin.js"></script>
<link href="../css/fixed_table_rc.css" type="text/css" rel="stylesheet"	media="all" />
<link href="../css/bootstrap.css" type="text/css" rel="stylesheet"	/>
<script src="../js/jquery-1.11.1.min.js" type="text/javascript"></script> 
	 <script type="text/javascript">

	window.onload = function() {
        window.open("/Data/Repair/SqazList.action","Conframe");
    }
	</script>
	
	  <script type="text/javascript">
	  
	  function an(){//安装详情
		    window.open("/Data/Repair/SqazList.action","Conframe");
		} 
	  
	   function azdj(){//安装登记  
		    window.open("/Data/Repair/InsertRepair2.action","Conframe");
		 
		}  
	  
	   function tbfx(){//图表分析
		    window.open("/Data/Repair/StateCharts.action","Conframe");
	  		
		}  
	   
	 </script>

<body>
<br>
 <button  onclick="an()"  type="button" class="btn btn-success btn-block" 
 				style="background-image: url('../img/secai.png');border:1px" type="button" >安装详情</button>
 				
 <button  onclick="azdj()"  type="button" class="btn btn-success btn-block" 
 				style="background-image: url('../img/secai.png');border:1px" type="button" >安装登记</button>
 				
  <button  onclick="tbfx()"  type="button" class="btn btn-success btn-block" 
 				style="background-image: url('../img/secai.png');border:1px"  type="button" >图表分析</button>   
 				
  
</body>
	<body>
	</body>
</html>