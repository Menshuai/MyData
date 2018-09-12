 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/theme.min.css" type="text/css" rel="stylesheet" />
<link href="../css/theme.min.css" type="text/css" rel="stylesheet" />
<link href="../css/style.css" type="text/css" rel="stylesheet" />

</head>
<style>
.touyou {
	width: 130px;
	height: 100px;
	background-color: #3CF;
	text-align: center
}
.touyou img {
	margin: 22px auto;
}
</style>
<body>
<table width="1618" height="357" border="0">
  <tr>
    <td width="500"><div style="width:300px; height:100px; border:1px solid #999; display:flex;">
        <div  class="touyou" > <img src="../img/i.png" width="40" height="20" /> </div>
        <div align="center" style=" width:250px;height:100px; margin-top:25px;">
          <div style="font-size: 22px"> 100</div>
          <div style="margin-top:5px; font-size: 18px">今日缴费笔数</div>
        </div>
      </div></td>
     <td width="500"><div style="width:300px; height:100px; border:1px solid #999; display:flex;">
        <div  class="touyou" > <img src="../img/i.png" width="40" height="40" /> </div>
        <div align="center" style=" width:250px;height:150px; margin-top:25px;">
          <div  style="font-size: 22px"> 100</div>
          <div style="margin-top:5px; font-size: 18px">今日缴费总1金额</div>
        </div>
      </div></td>
   <td width="437"><td width="10"><td width="10"><td width="606" rowspan="2">
        
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
		<p class="aboluo-currday">.</p>
		
	</div>
</div>
<script type="text/javascript" src="css/jquery-1.8.3.js"></script>
<script type="text/javascript" src="css/script.js"></script>
<td width="122"></td>
   
  </tr>
  <tr>
      <td width="500"><div style="width:300px; height:100px; border:1px solid #999; display:flex;">
        <div  class="touyou" > <img src="../img/i.png" width="40" height="40" /> </div>
        <div align="center" style=" width:250px;height:150px; margin-top:25px;">
          <div style="font-size: 22px"> 150</div>
          <div style="margin-top:5px; font-size: 18px">本周缴费笔数</div>
        </div>
      </div></td>
     <td width="500"><div style="width:300px; height:100px; border:1px solid #999; display:flex;">
        <div  class="touyou" > <img src="../img/i.png" width="40" height="40" /> </div>
        <div align="center" style=" width:250px;height:150px; margin-top:25px;">
          <div style="font-size: 22px"> 150</div>
          <div style="margin-top:5px; font-size: 18px">本周缴费总金额</div>
        </div>
      </div></td>

  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
