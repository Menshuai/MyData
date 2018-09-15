package com.hnzy.pds.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.pds.pojo.Jf;
import com.hnzy.pds.pojo.Price;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.JfService;
import com.hnzy.pds.service.PriceService;
import com.hnzy.pds.service.YhMessageService;
import com.hnzy.pds.util.DateUtil;
import com.hnzy.pds.util.MountUtil;

@Controller
@RequestMapping("JfController")
public class JfController {

	@Autowired
	private JfService jfService;
	@Autowired
	private YhMessageService yhMessageService;
	@Autowired
	private PriceService priceService;
	private List<YhMessage> yhInfoList;
	private List<Jf> jfs;
	
	
 /* //今日缴费笔数
	@RequestMapping("/jfbs")
	public String findJfbs(HttpServletRequest request){
	
		return "jf";
	}
	
	//今日缴费总金额
	@RequestMapping("/jfzje")
	public String findJfzje(HttpServletRequest request ){
	
		return "jf";
	}  */
	 
//	更新 修改0----------------------------
	@RequestMapping("/updateYhMessage")
	public String update(YhMessage yhMessage){
		yhMessageService.update(yhMessage);
		return  "redirect:yhfindList.action";
	}

	 @RequestMapping("/yfCheck")
	 @ResponseBody
	 public JSONObject yfCheck (String yf,Double mj){
		 JSONObject jsonObject=new JSONObject();
		String[] rq=yf.split("-");
		int year=Integer.valueOf(rq[0]);
		int month=Integer.valueOf(rq[1]);
		
		Calendar c = Calendar.getInstance();
		c.set(year, month, 0); //输入类型为int类型
		int day=c.get(Calendar.DAY_OF_MONTH);
//		  long day=daysBy();
		    System.out.println("包月天数----------"+day);
		  Price price=  priceService.byPrice(2);
		    double by=price.getPrice();
			double qs= day*by*mj;
			double je = Math.round(qs);
			jsonObject.put("je",je);
			String time=yf+"-01   至  "+yf+"-"+day;
			jsonObject.put("time",time);
		    return jsonObject;
	   }
	 
	 
	 
	 @RequestMapping("/yf")
	 @ResponseBody
	 public JSONObject yf (){
		 JSONObject jsonObject=new JSONObject();
		List<Object> list =new ArrayList<>();
		 Calendar date = Calendar.getInstance();
		 int year= date.get(Calendar.YEAR);//当前年
		 int month=date.get(Calendar.MONTH+1);//当前月
		 if(month>=4 && month <=8){ //夏季
			 String rq6=year+"-06";
			 String rq7=year+"-07";
			 String rq8=year+"-08";
			 String rq9=year+"-09";
			 list.add(rq6);
			 list.add(rq7);
			 list.add(rq8);
			 list.add(rq9);
			 
		 }else{
			 String rq11=year+"-11";
			 String rq12=year+"-12";
			 String rq1=year+1+"-01";
			 String rq2=year+1+"-02";
			 String rq3=year+1+"-03";
			 list.add(rq11);
			 list.add(rq12);
			 list.add(rq1);
			 list.add(rq2); 
			 list.add(rq3); 
		 }
		 jsonObject.put("yf", list);
		 return jsonObject;
	 } 
	 
	 
	 //缴费预算
	 @RequestMapping("/jfCheck")
	 @ResponseBody
	 public JSONObject jfCheck(String jf,Double mj) throws ParseException{
		 //按流量
		 JSONObject jsonObject=new JSONObject();
		 if(jf.equals("1")){
			    long day=daysBj();//一季度的天数
			    Price price=  priceService.byPrice(6);
				double Ll=price.getPrice();
				double qs= day*Ll*mj;
				double je = Math.round(qs);
				jsonObject.put("je",je);
				String time=daysTime();
				jsonObject.put("time",time );
		 }else if(jf.equals("3")){ //包季
			    long day=daysBj();//一季度的天数
			    Price price=  priceService.byPrice(3);
				double bj=price.getPrice();
				double qs= day*bj*mj;
				double je = Math.round(qs);
				jsonObject.put("je",je);
				String time=daysTime();
				jsonObject.put("time",time );
		 } else if(jf.equals("4")){ //包年
			    long day=daysBn(); //一年的天数
			    Price price=  priceService.byPrice(4);
				double bn=price.getPrice();
				double qs= day*bn*mj;
				double je = Math.round(qs);
				jsonObject.put("je",je);
				String time=BnTime();
				jsonObject.put("time",time );
		 }
		return jsonObject;
	 }
	 
	 
//	 public long daysLs() throws ParseException{
//		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		    Calendar date = Calendar.getInstance();
//		    String year = String.valueOf(date.get(Calendar.YEAR));
//			 Date d1 = df.parse(""+year+"-09-15 00:00:00");
//		     Date d2 = df.parse(""+year+"-06-15 00:00:00");
//		     long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
//		     long days = diff / (1000 * 60 * 60 * 24);
//		     return days;
//	 }
	 
	 public String daysTime() throws ParseException{
		  Calendar cale = null;  
	      cale = Calendar.getInstance();  
		  int month = cale.get(Calendar.MONTH) + 1; 
		  Calendar date = Calendar.getInstance();
		  String year = String.valueOf(date.get(Calendar.YEAR));
		   Integer yearS =Integer.valueOf(year)+1;
		  if(month>=4&&month<=8){
			  //夏季
				 String d1=year+"-09-15 ";
			     String d2 = year+"-06-15 ";
			     String time=d2+" 至   "+d1;
			     return time;
		  }else{
			  //冬季
			     String d3 =yearS+"-03-15 ";
			     String d4 =year+"-11-15  ";
			     String time = d4+" 至   "+ d3;//这样得到的差值是微秒级别
			     return time;
		  }
		 
	 }
	 
	 
	 public long daysBj() throws ParseException{
		 long days;
		  Calendar cale = null;  
	      cale = Calendar.getInstance();  
		  int month = cale.get(Calendar.MONTH) + 1; 
		  DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Calendar date = Calendar.getInstance();
		  String year = String.valueOf(date.get(Calendar.YEAR));
		   Integer yearS =Integer.valueOf(year)+1;
		  if(month>=4&&month<=8){
			  //夏季
				 Date d1 = df.parse(""+year+"-09-15 00:00:00");
			     Date d2 = df.parse(""+year+"-06-15 00:00:00");
			     long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
			      days = diff / (1000 * 60 * 60 * 24);
			     return days;
		  }else{
			  //冬季
			     Date d3 = df.parse(""+yearS+"-03-15 00:00:00");
			     Date d4 = df.parse(""+year+"-11-15 00:00:00");
			     long diffS = d3.getTime() - d4.getTime();//这样得到的差值是微秒级别
			      days = diffS / (1000 * 60 * 60 * 24);
			     return days;
		  }
		 
	 }
	 public String BnTime() throws ParseException{
		    Calendar date = Calendar.getInstance();
		    String year = String.valueOf(date.get(Calendar.YEAR));
		     Integer yearS =Integer.valueOf(year)+1;
		     String d2 = year+"-06-15 ";
		     
		     String d3 = yearS+"-03-15 ";
		     String time=d2+" 至   "+d3;
		     return time;
	 }
	 
	 public long daysBn() throws ParseException{
		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Calendar date = Calendar.getInstance();
		    String year = String.valueOf(date.get(Calendar.YEAR));
		     Integer yearS =Integer.valueOf(year)+1;
			 Date d1 = df.parse(""+year+"-09-15 00:00:00");
		     Date d2 = df.parse(""+year+"-06-15 00:00:00");
		     long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
		     
		     long days = diff / (1000 * 60 * 60 * 24);
		     Date d3 = df.parse(""+yearS+"-03-15 00:00:00");
		     Date d4 = df.parse(""+year+"-11-15 00:00:00");
		     long diffS = d3.getTime() - d4.getTime();//这样得到的差值是微秒级别
		     long daysS = diffS / (1000 * 60 * 60 * 24);
		     long day=days+daysS;
		     return day;
	 }
	 public long daysBy() throws ParseException{
		   Calendar calendar = Calendar.getInstance();
		    calendar.setTime(new Date());
		    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	 }
	//实时列表页面
	@RequestMapping("/JFfindList")
	public String findList(HttpServletRequest request ){
		request.setAttribute("jfbs", jfService.findJfbs());
		request.setAttribute("jfzje",jfService.findJfzje());
		
		request.setAttribute("jfBzbs", jfService.findBzJfbs());
		request.setAttribute("jfBzjfje", jfService.findBzJfzje());
		
		yhInfoList=yhMessageService.findXqName();
		request.setAttribute("XqNameList", yhInfoList);
		jfs=jfService.find();
		request.setAttribute("jf", jfs);
		return "jf";
	}
	
	//实时列表页面
		@RequestMapping("/JFfindListxx")
		public String findListxx(HttpServletRequest request ){
			
			yhInfoList=yhMessageService.findXqName();
			request.setAttribute("XqNameList", yhInfoList);
			jfs=jfService.find();
			request.setAttribute("jf", jfs);
			return "jfxxbj";
		}
	
	
	//历史数据列表
	@RequestMapping("/JFfindHistList")
	public String JFfindHistList(HttpServletRequest request ){
		yhInfoList=yhMessageService.findXqName();
		request.setAttribute("XqNameList", yhInfoList);
		jfs=jfService.JffindHistory();
		request.setAttribute("jf", jfs);
		return "jfHistory";
	}
	
	 //打印发票
	 @RequestMapping("/findFap")
	 public String findFp(HttpServletRequest req){
		 //接收参数
		 String yhbh=req.getParameter("yhbh");
		 YhMessage ymparm=new YhMessage();
		 ymparm.setYhbh(yhbh);//用户编号
		 //根据用户编号查询用户所在小区-楼栋-单元-户号
		 yhInfoList=yhMessageService.findXqByYhbh(ymparm);
		 
		 Jf jfparm=new Jf();
		 jfparm.setYzbh(yhbh);//业主编号、
		 //根据用户编号查询用户所在小区-楼栋-单元-户号
		 jfs=jfService.findjfByYhbh(jfparm);
		 
		 if(yhInfoList != null && yhInfoList.size() > 0 && jfs != null && jfs.size() > 0){
			 ymparm=yhInfoList.get(0);
			 //日期需要根据yzbh查询数据库
			 req.getSession().setAttribute("xqm",ymparm.getXqm());//小区名称
			 req.getSession().setAttribute("ldh",ymparm.getLdh());//楼栋号
			 req.getSession().setAttribute("dyh",ymparm.getDyh());//单元号
			 req.getSession().setAttribute("hh",ymparm.getHh());//房间号
			 req.getSession().setAttribute("name",ymparm.getYhxm());//用户姓名
			 Jf jfBean=new Jf();//缴费实体。
			 jfBean=jfs.get(0);
			 ymparm=yhInfoList.get(0);
			 req.getSession().setAttribute("rmbD",MountUtil.change(jfBean.getJfje()));//人民币大写
			 req.getSession().setAttribute("rmbX",jfBean.getJfje());//人民币小写
			 String jfType=getJfType(jfBean.getType());
			 //日期需要转换成  ----年--月--日
			 req.getSession().setAttribute("model", jfType);
			 req.getSession().setAttribute("startTime",DateUtil.strToStr(jfBean.getStartTime()));
			 req.getSession().setAttribute("endTime",DateUtil.strToStr(jfBean.getEndTime()));
		 }else{
			 req.getSession().setAttribute("xqm", "未获取到数据！");//小区名称
			 req.getSession().setAttribute("ldh","未获取到数据！");//楼栋号
			 req.getSession().setAttribute("dyh","未获取到数据！");//单元号
			 req.getSession().setAttribute("hh","未获取到数据！");//房间号
			 req.getSession().setAttribute("name","未获取到数据！ ");//用户姓名
			 req.getSession().setAttribute("rmbD","未获取到数据！");//人民币大写
			 req.getSession().setAttribute("rmbX","未获取到数据！");//人民币小写
			 req.getSession().setAttribute("model","未获取到数据！");//收费模式
			 req.getSession().setAttribute("startTime", "未获取到数据！");
			 req.getSession().setAttribute("endTime", "未获取到数据！");
		 }
		 
		return "FaP";// 发票打印页面
	 }
	 
	 /**
	  * 根据缴费表缴费模式返回相应文字
	  * @param typeString
	  * @return
	  */
	 private String getJfType(Integer typeString) {
		String type="";
		 switch (typeString) { 
			case 1:
				type="流量";
				break;
			case 2:
				type="包月";
				break;
			case 3:
				type="包季";
				break;
			case 4:
				type="包年";
				break;
			 default :
				type="包月";
				 break;
			}
		 return type;
	}

	@RequestMapping("/JfMe")
	public String Jf(){
		return "JfMen";
	}
	
	
	@RequestMapping("/RL")
	public String RL(){
		return "日历222";
	}
	
	//根据小区获取 小区名称
	@RequestMapping("findYhNameList")
	@ResponseBody
	public JSONObject findYhNameList(){
		yhInfoList=yhMessageService.findXqName();
		JSONObject jsonObject=new JSONObject() ;
		if(yhInfoList!=null){
			jsonObject.put("xqlist", yhInfoList);
		}else{
			jsonObject.put("fail", null);
		}
		return jsonObject;
	}
	
	
	//根据小区获取  楼栋号
	@RequestMapping("findYhldhbyxqm")
	@ResponseBody
	public JSONObject findYhldhbyxqm(String xqm) throws UnsupportedEncodingException{
		 xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
		yhInfoList=yhMessageService.findYhBuildNObyXqm(xqm);
		JSONObject jsonObject=new JSONObject() ;
		if(yhInfoList!=null){
			jsonObject.put("xqlist", yhInfoList);
		}else{
			jsonObject.put("fail", null);
		}
		return jsonObject;
	}
	
	//根据楼栋号获取  单元号
	@RequestMapping("findYhdyhByBuild")
	@ResponseBody
	public JSONObject findYhdyhByBuild(@Param("ldh")int ldh,@Param("xqm")String xqm) throws UnsupportedEncodingException{
		xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
		yhInfoList=yhMessageService.findYhCellNOByBuild(ldh, xqm);
		JSONObject jsonObject=new JSONObject();
		if(yhInfoList!=null){
			jsonObject.put("dyhList",yhInfoList);
		}else{
			jsonObject.put("fail",null);
		}
		return jsonObject;
	}
	 
	
	
	//修改 
		@RequestMapping(value="/updateYH")
		public String updateYH(YhMessage yhMessage){
			yhMessageService.updateyh(yhMessage);
			return  "redirect:JFfindListxx.action";
		}
		
		
	//更新缴费及合计金额
	@RequestMapping("update")
	@ResponseBody
	public JSONObject update(HttpSession session,String yzbh,Integer jfje,Double hjje,String yf,Integer jf,String time){
		String userName=(String)session.getAttribute("UserName");
		//String userName=(String)session.getAttribute("UserName");
		System.out.println("time-----------"+time);
		String[] jfTime=time.split("至");
		String startTime=jfTime[0];
		String endTime=jfTime[1];
		
		
		JSONObject jsonObject =new JSONObject();
		if(userName!=null&& hjje!=null&&yzbh!=null&& jfje!=null && yf!=null && jf!=null&&hjje!=null ){
		hjje=jfje+hjje;
		//更新缴费表
		
		jfService.updateJfHjje(yzbh, jfje, hjje,new Date(),userName,jf,yf, startTime, endTime);
		//插入缴费历史表
		jfService.InsertJfHistory(yzbh,jfje, hjje, new Date(), userName,jf , startTime, endTime);
		jsonObject.put("js","success");
		}else{
			jsonObject.put("js","fail");
		}
		return jsonObject;
//		return "redirect:JFfindList.action";
		
	}
	//搜索实时数据
	@RequestMapping("Search")
	@ResponseBody
	public JSONObject Search(String xqm,String ldh,String dyh,String hh,int type) throws UnsupportedEncodingException{
		xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
		JSONObject jsonObject=new JSONObject();
		if(ldh.length()==1){
			ldh="0"+ldh;
		}
		if(dyh.length()==1){
			dyh="0"+dyh;
		}
		jfs=jfService.Sear(xqm, ldh, dyh, hh,type);
		jsonObject.put("jfs",jfs);
		return jsonObject;
		
	}
	
	
	//搜索历史数据
	@RequestMapping("SearchHistory")
	@ResponseBody
	public JSONObject SearchHistory(String xqm,String ldh,String dyh,String hh,Integer type,String time1,String time2) throws UnsupportedEncodingException{
		xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
		JSONObject jsonObject=new JSONObject();
		jfs=jfService.JffindHistorySear(xqm, ldh, dyh, hh, time1, time2,type);
		jsonObject.put("jfs",jfs);
		return jsonObject;
		
	}
	//添加缴费信息
	@RequestMapping("add")
	public String InsertJf(Jf jf){
		jfService.Insert(jf);
		return "redirect:JFfindList.action";
	}
	
	//导出
			@RequestMapping("JfHistoryExportExcel")
			public void  JfHistoryExportExcel(YhMessage yhInfo,HttpSession session,HttpServletResponse response,String xqm,String ldh,String dyh,String hh,String time1,String time2,Integer type) throws IOException{
				xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
				//告诉浏览器要弹出的文档类型
				response.setContentType("application/x-execl");
				//告诉浏览器这个文档作为附件给别人下载（放置浏览器不兼容，文件要编码）
				response.setHeader("Content-Disposition", "attachment;filename="+new String("用户信息列表.xls".getBytes(),"ISO-8859-1"));
				//获取输出流
					 ServletOutputStream outputStream=response.getOutputStream();
					 jfService.exportExcel(jfService.JffindHistorySear(xqm, ldh, dyh, hh, time1, time2,type), outputStream);
					 if(outputStream!=null){
					 outputStream.close();
				}
			}
			//导出
			@RequestMapping("JfExportExcel")
			public void  JfExportExcel(YhMessage yhInfo,HttpSession session,HttpServletResponse response,String xqm,String ldh,String dyh,String hh,Integer type) throws IOException{
				xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
				//告诉浏览器要弹出的文档类型
				response.setContentType("application/x-execl");
				//告诉浏览器这个文档作为附件给别人下载（放置浏览器不兼容，文件要编码）
				response.setHeader("Content-Disposition", "attachment;filename="+new String("用户信息列表.xls".getBytes(),"ISO-8859-1"));
				//获取输出流
					 ServletOutputStream outputStream=response.getOutputStream();
					 jfService.exportExcel(jfService.Sear(xqm, ldh, dyh, hh,type), outputStream);
					 if(outputStream!=null){
					 outputStream.close();
				}
			}
			
			@RequestMapping("delete")
			@ResponseBody
			public JSONObject delete(int id){
				JSONObject jsonObject=new JSONObject();
				jfService.delete(id);
				return jsonObject;
			}
}

