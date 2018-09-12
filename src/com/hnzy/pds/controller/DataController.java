package com.hnzy.pds.controller;

 
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.socket.server.ServerSessionMap;
import com.hnzy.hot.socket.util.CzUtil;
import com.hnzy.hot.socket.util.MapUtilsDf;
import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.pojo.Rz;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.DataService;
import com.hnzy.pds.service.RzService;
import com.hnzy.pds.service.YhMessageService;
 
@Controller
@RequestMapping("/DataController")
public class DataController {
	 
	@Autowired
	private DataService dataService;
	@Autowired
	private RzService rzService;
	@Autowired
	private YhMessageService yhMessageService;
	private List<YhMessage> yhInfoList;
	private static Log log = LogFactory.getLog(DataController.class);
	public List<Data> YhList;
	public List<Data> BjList;//报警
	//首页
	@RequestMapping("/data")
	public String sjbb(HttpServletRequest request)  {
//		Map<String ,Integer >map=new HashMap<String ,Integer >();
		 String time= getTimeByHour(12);
		//通讯异常
		int txyc=dataService.TxycNum(time);
		//盗热异常
		int dryc=dataService.DrycNum("03");
		//开盖异常
		int kgyc=dataService.DrycNum("01");
		request.setAttribute("txyc",txyc);
		request.setAttribute("dryc",dryc);
		request.setAttribute("kgyc",kgyc);
		
		List<Data> YhList=dataService.find();
		
		request.setAttribute("YhList", YhList);
		return "/data";  //数据表data.jsp
	}
	
	
	//设备管理
	@RequestMapping("/dataSbgl")
	public String Sbgl(HttpServletRequest request)  {
		yhInfoList=yhMessageService.findXqName();
		request.setAttribute("XqNameList", yhInfoList);
		List<Data> YhList=dataService.find();
		request.setAttribute("YhList", YhList);
		return "/sbgl";//设备管理sbgljsp
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
		
		//异常查询  首页 左边框架
		@RequestMapping("YccxMe")
		public String Yc(){
				return "YccxMen";
			}
			
		//异常查询
		@RequestMapping("Yccx")
		public String Yccx(HttpServletRequest request){
			BjList=dataService.Search("01");
			request.setAttribute("YhList", BjList);
			return "Yccx";
		}
		@RequestMapping("YccxHistory")
		public String YccxHistory(HttpServletRequest request ){
			List<Data>bjList=dataService.SearchHistoryYc("--选择小区名称--", "0","0",0,"01");
			yhInfoList=yhMessageService.findXqName();
			request.setAttribute("XqNameList", yhInfoList);
			request.setAttribute("YhList", bjList);
			return "YccxHistory";
		}
		
		//异常查询历史搜索
		@RequestMapping("SearchHistoty")
		@ResponseBody
		public JSONObject SearchHistoty(HttpServletRequest request, @Param("xqm")String xqm,@Param("ldh")String ldh,
				@Param("dyh")String dyh,@Param("hh")Integer hh,@Param("bj")String bj) throws UnsupportedEncodingException{
			bj=new String(bj.getBytes("ISO-8859-1"),"UTF-8")+"";
//			xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
			if(hh==null){
				hh=0;
			}
			BjList=dataService.SearchHistoryYc(xqm, ldh, dyh, hh,bj);
			jsonObject.put("BjList",BjList);
			return jsonObject;
		}
		
//		//首页显示异常查询数目
//		@RequestMapping("findYcNum")
//		@ResponseBody
//		public JSONObject findYcNum(){
//			JSONObject jsonObject=new JSONObject();
//			 String time= getTimeByHour(12);
//			//通讯异常
//			int txyc=dataService.TxycNum(time);
//			//盗热异常
//			int dryc=dataService.DrycNum("03");
//			//开盖异常
//			int kgyc=dataService.DrycNum("01");
//			jsonObject.put("txyc",txyc);
//			jsonObject.put("dryc",dryc);
//			jsonObject.put("kgyc",kgyc);
//			return jsonObject;
//		}
		
		//导出
				@RequestMapping("YhInfodoExportExcelHist")
				public void  YhInfodoExportExcelHist(YhMessage yhInfo,HttpSession session,HttpServletResponse response, @Param("xqm")String xqm,@Param("ldh")String ldh,
						@Param("dyh")String dyh,@Param("hh")Integer hh,@Param("bj")String bj) throws IOException{
				 /*	xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";*/
					bj=new String(bj.getBytes("ISO-8859-1"),"utf-8")+"";
					//告诉浏览器要弹出的文档类型
					response.setContentType("application/x-execl");
					//告诉浏览器这个文档作为附件给别人下载（放置浏览器不兼容，文件要编码）
					response.setHeader("Content-Disposition", "attachment;filename="+new String("报警信息列表.xls".getBytes(),"ISO-8859-1"));
					//获取输出流
					 ServletOutputStream outputStream=response.getOutputStream();
					 if(hh==null){
							hh=0;
						}
					 yhMessageService.exportExcel(dataService.SearchHistoryYc( xqm, ldh, dyh, hh,bj), outputStream);
					 if(outputStream!=null){
						outputStream.close();
					 }
				 	//日志
				/*	Rz rz=new Rz();
					rz.setCz("导出:小区名称："+xqm+",楼栋号："+ldh+",单元号："+dyh);
					rz.setCzr((String)session.getAttribute("UserName"));
					rz.setCzsj(new Date());;
					rzService.insert(rz); */
				}
		
		//异常查询中的  搜索按钮
		@RequestMapping("Search")
		@ResponseBody
		 public JSONObject Search(HttpServletRequest request,@Param("bj")String bj) throws UnsupportedEncodingException{
			bj=new String(bj.getBytes("ISO-8859-1"),"UTF-8")+"";
			JSONObject jsonObject=new JSONObject();
              if(bj.equals("04")){
            	  String time= getTimeByHour(12);
                 BjList= dataService.searchInfo("--选择小区名称--", 0, 0, 0, "",time); 
                  }else{
				 BjList=dataService.Search(bj);
                  }
				jsonObject.put("BjList",BjList);
			 return jsonObject;
		 }
		
		  public static String getTimeByHour(int hour) {

		        Calendar calendar = Calendar.getInstance();

		        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);

		        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
		  }
		//导出
		@RequestMapping("YhInfodoExportExcel")
		public void  YhInfodoExportExcel(YhMessage yhInfo,HttpSession session,HttpServletResponse response,@Param("xqm")String xqm,
				  @Param("bj") String bj) throws IOException{
		 /*	xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";*/
			bj=new String(bj.getBytes("ISO-8859-1"),"utf-8")+"";
			//告诉浏览器要弹出的文档类型
			response.setContentType("application/x-execl");
			//告诉浏览器这个文档作为附件给别人下载（放置浏览器不兼容，文件要编码）
			response.setHeader("Content-Disposition", "attachment;filename="+new String("报警信息列表.xls".getBytes(),"ISO-8859-1"));
			//获取输出流
			 ServletOutputStream outputStream=response.getOutputStream();
			 if(bj.equals("04")){
           	    String time= getTimeByHour(12);
                BjList= dataService.searchInfo("--选择小区名称--", 0, 0, 0, "",time); 
                 }else{
				 BjList=dataService.Search(bj);
                 }
			 yhMessageService.exportExcel(BjList, outputStream);
			 if(outputStream!=null){
				outputStream.close();
			 }
		 	//日志
		/*	Rz rz=new Rz();
			rz.setCz("导出:小区名称："+xqm+",楼栋号："+ldh+",单元号："+dyh);
			rz.setCzr((String)session.getAttribute("UserName"));
			rz.setCzsj(new Date());;
			rzService.insert(rz); */
		}
			
		
			//搜索并显示
			@RequestMapping("searchInfo")
			@ResponseBody
			public JSONObject searchInfo(HttpServletRequest request,ModelMap map,@Param("xqm")String xqm,@Param("ldh")int ldh,
							@Param("dyh")int dyh,@Param("hh")Integer hh) throws UnsupportedEncodingException{
				JSONObject jsonObject=new JSONObject();
				//hh为null查询实时表，否则查询历史表
				if(hh==null){
					hh=0;
					YhList= dataService.searchInfo(xqm, ldh, dyh, hh, "", "");
					jsonObject.put("findXqInfoHistory",YhList);
				}else{
					YhList= dataService.searchHistory(xqm, ldh, dyh, hh,"","");
					jsonObject.put("findXqInfoHistory",YhList );
				}
					return jsonObject;		
				}
 
	         String fpdz;
	         String idString;
			JSONObject jsonObject=new JSONObject();
			
			@RequestMapping("/DataMe")
			public String SkqMe(){
				return "/DataMen";
			}
			
			@RequestMapping("/SbglMe")
			public String DataMe(){
				return "/SbglMen";
			}	
			String fpdzS;
	//查询状态------对某一户--------------查询状态-----------------
	@ResponseBody
	@RequestMapping("CxState")
	public JSONObject CxState(HttpSession session,HttpServletRequest request, String ids,Data zykt,YhMessage yhMessage){
		MapUtilsDf.getMapUtils().add("kt", null);
		//用户编号
		if(ids.length()>9){
			 idString=ids.substring(0, ids.length()-2);
			 fpdzS=ids.substring(ids.length()-2);
			//风盘编号
			String	fp=Integer.toHexString(Integer.valueOf(ids.substring(ids.length()-2)));
			 fpdz="0"+fp;
		}else{
		//用户编号
		 idString=ids.substring(0, ids.length()-1);
		//风盘编号
		 fpdz="0"+ids.substring(ids.length()-1);
		 fpdzS=fpdz;
		}
		//用户编号转换为十进制
		int ids1 = Integer.valueOf(idString); 
		String idsS = Integer.toHexString(ids1);//16进制     
	 	YhMessage  ldh=yhMessageService.finldh(idString,fpdzS);//楼栋  
	 	String ld=ldh.getLdh();
		if(ld.length()==1){
			ld=0+ld;
		}
		
		String dy=ldh.getDyh();
		if(dy.length()==1){
			dy=0+dy;
		}
		
		 String cgbh=ldh.getCgbh();
		 System.out.println("cgbh"+cgbh);
		 
		 String cg=cgbh.substring(4);
		 
		String ja =ld+dy+"F010B5"+cg+""+idsS+fpdz+ld+dy+"FFFFFFFF";//起始到结束  01终端
		log.info("对某户 楼栋号："+ld+"单元号："+dy+"风盘地址:"+fpdzS+"单个风盘查询操作发送指令 ："+ja);
		YhMessage yhmess=yhMessageService.findJzq(idString);
		String ip =yhmess.getCg().getJzq().getJzqip();
		String port=yhmess.getCg().getJzq().getJzqport();
		// IP地址和端口号
		String pt = "/" + ip + ":" + port; 
		boolean sessionmap = cz(ja, pt);
		//日志
		Rz rz=new Rz();
		rz.setCz("对某户 楼栋号："+ld+"单元号："+dy+"风盘地址:"+fpdzS+"操作发送指令");
		rz.setCzr((String)session.getAttribute("UserName"));
		rz.setCzsj(new Date());;
		rzService.insert(rz);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject();
	   if (sessionmap == false && MapUtilsDf.getMapUtils().get("kt") == null) {
			log.info("发送数据失败,集中器不在线");
			MapUtilsDf.getMapUtils().add("kt", null);
//			dataService.update("04");
			jsonObject.put("js", "3");
			return jsonObject;
		}else if(MapUtilsDf.getMapUtils().get("kt")!= null&&MapUtilsDf.getMapUtils().get("kt").equals(idString)){
			jsonObject.put("js", "2");
			return jsonObject;
		}else {
//			dataService.update("04",idString,fpdzS);
			MapUtilsDf.getMapUtils().add("kt", null);
			jsonObject.put("js", "0");
			return jsonObject;
		}
	} 
	 String kgString="";
	 String jfString="";
	 String jjString="";
	// ---------------------单个风盘操作---------
	 String	fp;
	 String fpbh;
  	@RequestMapping("DCxZx")
	@ResponseBody
	public JSONObject DCxZx(HttpSession session,HttpServletRequest request, String ids,String fpdz,Data zykt,String kg,String jf,String jj){
  		MapUtilsDf.getMapUtils().add("kt", null);
  		
		if(ids.length()>9){
			 idString=ids.substring(0, ids.length()-2);
			 System.out.println("idString--"+idString);
			//风盘编号
			 fpbh=ids.substring(ids.length()-2);
			 fp=Integer.toHexString(Integer.valueOf(ids.substring(ids.length()-2)));
			 fpdz="0"+fp;
			 System.out.println("fpdz--"+fpdz);
		}else{
		//用户编号
		 idString=ids.substring(0, ids.length()-1);
		//风盘编号
		 fpdz="0"+ids.substring(ids.length()-1);
		 fpbh=fpdz;
		}
 
  		YhMessage  ldh=yhMessageService.finldh(idString,fpbh);//楼栋  
  		String ld=ldh.getLdh();
		if(ld.length()==1){
			ld=0+ld;
		}
		System.out.println("楼栋号"+ld); 
		String cgbh=ldh.getCgbh();
		String cg=cgbh.substring(4);
		System.out.println("cg---"+cg);
		String dy=ldh.getDyh();
		if(dy.length()==1){
			dy=0+dy;
		}
		System.out.println("单元号"+dy);
  		//     ids为用户编码   
		 int ids1 = Integer.valueOf(idString);
		 String idsS = Integer.toHexString(ids1);//转换为十六进制  用户编码
		 YhMessage yhmess=yhMessageService.findJzq(idString);
		 String ip =yhmess.getCg().getJzq().getJzqip();
		 System.out.println("jzqip----------"+ip);
		 
		 String port=yhmess.getCg().getJzq().getJzqport();
		 System.out.println("jzqport----------"+port);
		 
		 if(kg.equals("02")){
		 	kg="FF";
		 }
		 if(jf.equals("02")){
		 	jf="FF";
		 }
		 if(jj.equals("02")){
		 	jj="FF";
		 }
		 
		 //空调状态
		 String ja =ld+dy+"F010B1"+cg+""+idsS+fpdz+""+kg+""+jf+""+jj+ld+dy+"FF";
		 System.out.println("ja----"+ja);
		 // IP地址和端口号
		 String pt = "/" + ip + ":" + port;
		 boolean sessionmap = cz(ja, pt);
		 log.info("对某户单个 风盘开关计费季节操作发送指令 ："+ja);
			//日志
	
		 if(kg.equals("00")){
			 kgString="强关";
		 }else if(kg.equals("01")){
			 kgString="自动"; 
		 }else{
			 kgString="";  
		 }
		 if(jf.equals("00")){
			 jfString="禁止计费";
		 }else if(jf.equals("01")){
			 jfString="允许计费";
		 }
		 if(jj.equals("00")){
			 jjString="夏季";
		 }else if(jj.equals("01")){
			 jjString="冬季";
		 }
			Rz rz=new Rz();
			rz.setCz("对单户 风盘开关计费季节操作,楼栋号："+ld+",单元号："+dy+",风盘地址："+fpdz+",开关:"+kgString+",计费："+jfString+",季节："+jjString);
			rz.setCzr((String)session.getAttribute("UserName"));
			rz.setCzsj(new Date());
			rzService.insert(rz);
		 try {
		 	Thread.sleep(4000);
		 } catch (InterruptedException e) {
		 	e.printStackTrace();
		 }
		 JSONObject jsonObject = new JSONObject();
		 if (sessionmap == false && MapUtilsDf.getMapUtils().get("kt") == null) {
		 	log.info("发送数据失败");
//		 	dataService.update("04",idString,fpdz.toString());
		 	MapUtilsDf.getMapUtils().add("kt", null);
		 	jsonObject.put("js", "3");
		 	return jsonObject;
		 }else if( MapUtilsDf.getMapUtils().get("kt") != null && MapUtilsDf.getMapUtils().get("kt").equals(idString)){
			 MapUtilsDf.getMapUtils().add("kt", null);
			 jsonObject.put("js", "2");
			return jsonObject;
		 }else{
//			 dataService.update("04",idString,fpdzS);
			 MapUtilsDf.getMapUtils().add("kt", null);
			 jsonObject.put("js", "0");
			return jsonObject;
		}
    	} 
  	
 // 所有风盘发送操作-------------------------对一户 所有风盘发送操作 ----------
   	@RequestMapping("SCxZx")
 	@ResponseBody
 	public JSONObject SCxZx(HttpSession session,HttpServletRequest request, String ids,String fpdz,Data zykt,String kg,String jf,String jj){
   		MapUtilsDf.getMapUtils().add("dg", null);
   		//用户编号
		if(ids.length()>9){
			 idString=ids.substring(0, ids.length()-2);
			 System.out.println("idString--"+idString);
			//风盘编号
			String	fp=Integer.toHexString(Integer.valueOf(ids.substring(ids.length()-2)));
			 fpdz="0"+fp;
			 System.out.println("fpdz--"+fpdz);
		}else{
		//用户编号
		 idString=ids.substring(0, ids.length()-1);
		//风盘编号
		 fpdz="0"+ids.substring(ids.length()-1);
		System.out.println(idString+"---"+fpdz);
		}
  	   
   		YhMessage  ldh=yhMessageService.findyh(idString);//楼栋  
   		String ld=ldh.getLdh();
// 		String ld=String.valueOf(ldhS);
 		if(ld.length()==1){
 			ld=0+ld;
 		}
 		System.out.println("楼栋号"+ld); 
 		 String cgbh=ldh.getCgbh();
		 System.out.println("cgbh"+cgbh);
		 String cg=cgbh.substring(4);
		 System.out.println("cg--"+cg);
		 String dy=ldh.getDyh();
// 		String dy=String.valueOf(dyhS);
 		if(dy.length()==1){
 			dy=0+dy;
 		}
 		System.out.println("单元号"+dy);
 		
   		// 把FmID转换为int类型      ids为用户编码   
 		 int ids1 = Integer.valueOf(idString);
 		 String idsS = Integer.toHexString(ids1);//转换为十六进制  用户编码
 		 System.out.println("idsS---------"+idsS);

 		 YhMessage yhmess=yhMessageService.findJzq(idString);
 		 String ip =yhmess.getCg().getJzq().getJzqip();
 		 System.out.println("jzqip----------"+ip);
 		 
 		 String port=yhmess.getCg().getJzq().getJzqport();
 		 System.out.println("jzqport----------"+port);
 		 
 		 if(kg.equals("02")){
 		 	kg="FF";
 		 }
 		 if(jf.equals("02")){
 		 	jf="FF";
 		 }
 		 if(jj.equals("02")){
 		 	jj="FF";
 		 }
 		 //空调状态
 		 String ja =ld+dy+"F010B1"+cg+""+idsS+"FF"+""+kg+""+jf+""+jj+ld+dy+"FF";
 		 System.out.println("ja----"+ja);
 		 // IP地址和端口号
 		 String pt = "/" + ip + ":" + port;
 		 boolean sessionmap = cz(ja, pt);
 		 log.info("对某户 所有风盘开关计费季节操作,楼栋号:"+ld+"单元号："+dy+",发送指令 ："+ja);
 		 if(kg.equals("00")){
			 kgString="强关";
		 }else if(kg.equals("01")){
			 kgString="自动"; 
		 }else{
			 kgString="";  
		 }
		 if(jf.equals("00")){
			 jfString="禁止计费";
		 }else if(jf.equals("01")){
			 jfString="允许计费";
		 }
		 if(jj.equals("00")){
			 jjString="夏季";
		 }else if(jj.equals("01")){
			 jjString="冬季";
		 }
			Rz rz=new Rz();
			rz.setCz("发送对单户 所有风盘开关计费季节操作,风盘地址："+fpdz+",开关："+kgString+",计费"+jfString+",季节"+jjString);
			rz.setCzr((String)session.getAttribute("UserName"));
			rz.setCzsj(new Date());;
			rzService.insert(rz);
 		 try {
 		 	Thread.sleep(4000);
 		 } catch (InterruptedException e) {
 		 	e.printStackTrace();
 		 }
 		 JSONObject jsonObject = new JSONObject();
 		 if (sessionmap == false && MapUtilsDf.getMapUtils().get("dg") == null) {
 		 	log.info("发送数据失败");
 		 	/*dataService.update("04");*/
 		 	MapUtilsDf.getMapUtils().add("dg", null);
 		 	jsonObject.put("js", "3");
 		 	return jsonObject;
 		 }else if( MapUtilsDf.getMapUtils().get("dg")!= null&& MapUtilsDf.getMapUtils().get("dg").equals("success")){
 			MapUtilsDf.getMapUtils().add("dg", null);
 		 	jsonObject.put("js", "2");
 		 	return jsonObject;
 		 }else{
// 			dataService.update("04",idString,fpdzS);
 			MapUtilsDf.getMapUtils().add("dg", null);
 		 	jsonObject.put("js", "0");
 			return jsonObject;
 		  }
     	} 
			// 抽取相同部分
			public boolean cz(String ja, String pt) {
				// 把十六进制数，转换为十进制相加
				int jia = CzUtil.FsZh(ja);
				// 十进制转换为十六进制
				String hex = Integer.toHexString(jia);
				// 截取相加结果后两位
				String je = null;
				for (int j = 0; j < hex.length() - 1; j++) {
					je = hex.charAt(hex.length() - 2) + "" + hex.charAt(hex.length() - 1);
				}
				String[] keys = new String[] { pt };
				String mString =ja+je+"FF";
				System.out.println("mString发送数据----------"+mString);
				// 解码
				byte[] b = CzUtil.jm(mString);
				ServerSessionMap sessionMap = ServerSessionMap.getInstance();
				boolean sessionmap = sessionMap.sendMessage(keys, b);
				return sessionmap;
			}		
	}
