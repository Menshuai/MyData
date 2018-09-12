<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel='stylesheet prefetch'  href="../css/csshake.min.css">
<script src="../css/iconfont.js"></script>
<script src="../css/jquery-1.7.2.js"></script>
<script src="../css/index.js"></script>

<script   type="text/javascript">
      //获得当前时间,刻度为一千分一秒
      var initializationTime=(new Date()).getTime();
    
      function showLeftTime(){
          
           var now=new Date();
           var year=now.getFullYear();
           var day=now.getDate();
           var month=now.getMonth()+1;
           
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
          
           if(day>=10 && day<=31){
               day=day
           }else if(day>=0 && day<=9) {
              day=0+""+day; 
           }
		   
           
           var hours=now.getHours();
           var minutes=now.getMinutes();
           var seconds=now.getSeconds();
		   
		   
           if(minutes>=10){
               minutes=minutes
           }else if(minutes>=0 && minutes<=9) {
              minutes=0+""+minutes; 
           }
            if(hours>=10){
               hours=hours
           }else if(hours>=0 && hours<=9) {
              hours=0+""+hours; 
           }
		    if(seconds>=10){
               seconds=seconds
           }else if(seconds>=0 && seconds<=9) {
              seconds=0+""+seconds; 
           }
		   document.all.showh.innerHTML=""+hours;
		   document.all.showm.innerHTML=""+minutes;
           document.all.shows.innerHTML=""+seconds;
            document.all.showtime.innerHTML=""+year+"年"+month+"月"+day+"日 ";
           //一秒刷新一次显示时间
             var timeID=setTimeout(showLeftTime,1000);  
      }
        function custom_close(){
     
    }
    </script>
<style type="text/css">
a:link {
	text-decoration: none;
	color: #000;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
}
a:active {
	text-decoration: none;
}
</style>
</head>
<style>
.container {
	background: url(img/bjt.jpg);
	background-repeat: no-repeat;
	background-size: 100% 100%;
	background-attachment: fixed;
}
.tou {
	display: flex;
	vertical-align: middle;
	color: #000;
	border-bottom: 1px solid #000;
	justify-content: space-between;
}
.touyou {
	font-size: 20px;
	height:30px;
	display: flex;
	width: 100px;
	justify-content: space-between;
	color:#FFF;
	
}
.touzuo {
	font-size: 28px;
}
.dibu {
	display: flex;
	justify-content: center;
	color: #FFF;	

}
.wei {
	width: 9%;
	height:10%;
	margin: 20px;
	font-size: 1em;
	text-align: center;
	cursor: pointer;
	background: #FFF;
	border-radius: 6px;
	line-height: 30px;

}
.wei:hover {
    	width: 10%;
	height:11%;
	margin: 20px;
	font-size: 1em;
	text-align: center;
	cursor: pointer;
	background: #FFF;
	border-radius: 6px;
	line-height: 30px;
}
.fa {
	width: 70px;
	height: 70px;
	background-color: #F00;
	text-align: center;
}
.clock {
	width: 360px;
	height: 70px;
	color: #000;
	font-family: "Lato", sans-serif;
}
.clock div {
	background: #FFF;
	border-radius: 6px;
	width: 70px;
	height: 55px;
	line-height: 60px;
	text-align: center;
	font-size: 44px;
}


</style>

<body class="container" onload="showLeftTime()">
<div class="tou">
 
  <div class="touyou">
 
  </div>

</div>
  <div align="right" style=" font-size:18px; width:100%; height:70%; color:#FFF; ">请登录|</div>

 <div  align="center">
    <div  align="center"  style="width:100%;  height: 30%;">
    saf
    </div>
       <div>
         <div class='clock' style="font-size:48px; text-align:center">
               <div class=' shake shake-slow'><label id="showh">显示时的位置</label></div>
                   <z  style="color:#FFF">:</z>
               <div class=' shake shake-slow'><label id="showm">显示分的位置</label></div>
                   <z  style="color:#FFF">:</z>   
               <div class=' shake shake-slow'><label id="shows">显示秒的位置</label></div>
          </div>
            <div style="color:#FFF"><label id="showtime">显示年月日的位置</label></div>
            <div style="font-size:30px; margin-top:30px; margin-bottom:20px; color:#FFF;">
               <div> 欢迎进入  </div> 
               <div> 河南润恒中央空调计量及能耗监控平台</div>      
            </div>  
        </div>
   
   </div>

  <div class="dibu" >
    <div class="wei"><a href="test.html">
      <div><img src="../img/yunxing.png" width="50%" height="50%" style="margin-top:20%;" /></div>
      运行管理</a></div>
    <div class="wei"><a href="test.html">
      <div><img src="../img/xinxi.png"  width="50%" height="50%" style="margin-top:20%;"/></div>
      信息管理</a></div>
    <div class="wei"><a href="test.html">
      <div><img src="../img/jiapfei.png" width="50%" height="50%" style="margin-top:20%;"/></div>
      缴费管理</a></div>
    <div class="wei"><a href="test.html">
      <div><img src="../img/shebei.png"  width="50%" height="50%" style="margin-top:20%;"/></div>
      设备管理</a></div>
  </div>
  <div class="dibu" >
    <div class="wei"><a href="test.html">
      <div><img src="../img/shuju.png"  width="50%" height="50%" style="margin-top:20%;"/></div>
      数据报表</a></div>
    <div class="wei"><a href="test.html">
      <div><img src="../img/rizhi.png"  width="50%" height="50%" style="margin-top:20%;"/></div>
      操作日志</a></div>
    <div class="wei"><a href="test.html">
      <div><img src="../img/yichang.png"  width="50%" height="50%" style="margin-top:20%;" /></div>
      异常查询</a></div>
    <div class="wei"><a href="test.html">
      <div><img src="../img/xitong.png"  width="50%" height="50%" style="margin-top:20%;"/></div>
      系统设置</a></div>
  </div>


<div style="position:fixed; margin-bottom:20px; bottom:0; color:#FFF; width:100%; text-align:center">Copyright &copy;2018-8月  河南众源系统工程有限公司版权所有</div>
</body>
</html>