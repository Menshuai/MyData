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
        	window.open("/Data/JfController/JFfindList.action","Conframe");//返回的是jf.jsp页面
    	}
		
	</script>
    <script type="text/javascript">
	  function Jf(){//
		    window.open("/Data/JfController/JFfindList.action","Conframe");
		} 
	  
	  function JfHistory(){//
		    window.open("/Data/JfController/JFfindHistList.action","Conframe");
		} 
	  
	  function sj(){//
		    window.open("/Data/JfController/RL.action","Conframe");
		}    
	 </script>
<body><!-- 左边框架小区信息列表 -->
 <!--  <iframe scrolling="no" width="100%"   name="Conframel" id="Conframel" src="/Data/zz/ztree.action" 
    frameborder="0" onload="this.height=0;var fdh=(this.Document?this.Document.body.scrollHeight:this.contentDocument.body.offsetHeight);this.height=(fdh>1700?fdh:1700)">
    </iframe> -->
    
    <button  onclick="Jf()"  type="button" class="btn btn-success btn-block" 
 			style="background-image: url('../img/secai.png');border:1px" type="button" >缴费信息</button>
 	<button  onclick="JfHistory()"  type="button" class="btn btn-success btn-block" 
 			style="background-image: url('../img/secai.png');border:1px" type="button" >缴费历史信息</button>
 			
 			   <button  onclick="sj()"  type="button" class="btn btn-success btn-block" 
 			style="background-image: url('../img/secai.png');border:1px" type="button" >日历</button>
 </body>
	
</html>