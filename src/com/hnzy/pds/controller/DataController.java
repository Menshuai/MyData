package com.hnzy.pds.controller;

 
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.hnzy.pds.service.FpService;
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
	@Autowired
	private FpService fpService;
	private static Log log = LogFactory.getLog(DataController.class);
	public List<Data> YhList;
	//首页
	@RequestMapping("/data")
	public String sjbb(HttpServletRequest request)  {
	
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
			
			//搜索并显示
			@RequestMapping("searchInfo")
			@ResponseBody
			public JSONObject searchInfo(HttpServletRequest request,ModelMap map,@Param("xqm")String xqm,@Param("ldh")int ldh,
							@Param("dyh")int dyh,@Param("hh")Integer hh) throws UnsupportedEncodingException{
				JSONObject jsonObject=new JSONObject();
				//hh为null查询实时表，否则查询历史表
				if(hh==null){
					hh=0;
//					List<Data> YhList=dataService.find();
					YhList= dataService.searchInfo(xqm, ldh, dyh, hh, "", "");
					jsonObject.put("findXqInfoHistory",YhList);
				}else{
					YhList= dataService.searchHistory(xqm, ldh, dyh, hh,"","");
					jsonObject.put("findXqInfoHistory",YhList );
				}
					return jsonObject;		
				}
	@RequestMapping("/DataMe")
	public String SkqMe(){
		return "/DataMen";
	}
	
	@RequestMapping("/SbglMe")
	public String DataMe(){
		return "/SbglMen";
	}
	String fpdz;
	String idString;
	//查询状态------对某一户--------------查询状态-----------------
	@ResponseBody
	@RequestMapping("CxState")
	public JSONObject CxState(HttpSession session,HttpServletRequest request, String ids,Data zykt,YhMessage yhMessage){
		MapUtilsDf.getMapUtils().add("kt", null);
		//用户编号
		System.out.println(ids);
		if(ids.length()==10){
			 idString=ids.substring(0, ids.length()-2);
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
		//用户编号转换为十进制
		int ids1 = Integer.valueOf(idString); 
		String idsS = Integer.toHexString(ids1);//16进制     
	 	YhMessage  ldh=yhMessageService.finldh(idString,fpdz);//楼栋  
		Integer ldhS=ldh.getLdh();
		String ld=String.valueOf(ldhS);
		if(ld.length()==1){
			ld=0+ld;
		}
		
		Integer dyhS=ldh.getDyh();
		String dy=String.valueOf(dyhS);
		if(dy.length()==1){
			dy=0+dy;
		}
		
		 String cgbh=ldh.getCgbh();
		 System.out.println("cgbh"+cgbh);
		 
		 String cg=cgbh.substring(4);
		 
//		String ja =ld+dy+"F010B5"+cg+""+idsS+fpdz+"FFFFFFFFFFFF";//起始到结束  01终端
		String ja =ld+dy+"F010B5"+cg+""+idsS+fpdz+ld+dy+"FFFFFFFF";//起始到结束  01终端
		log.info("对某户 楼栋号："+ldhS+"单元号："+dyhS+"风盘地址:"+fpdz+"单个风盘查询操作发送指令 ："+ja);
		YhMessage yhmess=yhMessageService.findJzq(idString);
		String ip =yhmess.getCg().getJzq().getJzqip();
		String port=yhmess.getCg().getJzq().getJzqport();
		// IP地址和端口号
		String pt = "/" + ip + ":" + port; 
		System.out.println("pt-------------"+pt);
		boolean sessionmap = cz(ja, pt);
		//日志
		Rz rz=new Rz();
		rz.setCz("发送对某一户单个风盘查询指令");
		rz.setCzr((String)session.getAttribute("UserName"));
		rz.setCzsj(new Date());;
		rzService.insert(rz);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject();
			if (sessionmap == false && MapUtilsDf.getMapUtils().get("kt") == null) {
			log.info("发送数据失败,集中器不在线");
			MapUtilsDf.getMapUtils().add("kt", null);
			jsonObject.put("js", "3");
			return jsonObject;
		}else if(MapUtilsDf.getMapUtils().get("kt")!= null&&MapUtilsDf.getMapUtils().get("kt").equals("success")){
			jsonObject.put("js", "2");
			return jsonObject;
		}else {
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
  		
		if(ids.length()==10){
			 idString=ids.substring(0, ids.length()-2);
			 System.out.println("idString--"+idString);
//			 Integer idString=Integer.valueOf(ids.substring(1, ids.length()-1));
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
		Integer ldhS=ldh.getLdh();
		String ld=String.valueOf(ldhS);
		if(ld.length()==1){
			ld=0+ld;
		}
		System.out.println("楼栋号"+ld); 
		String cgbh=ldh.getCgbh();
		String cg=cgbh.substring(4);
		System.out.println("cg---"+cg);
		Integer dyhS=ldh.getDyh();
		String dy=String.valueOf(dyhS);
		if(dy.length()==1){
			dy=0+dy;
		}
		System.out.println("单元号"+dy);
  		// 把FmID转换为int类型      ids为用户编码   
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
			rz.setCz("对某户单个 风盘开关计费季节操作，楼栋号："+ldhS+"单元号："+dyhS+"风盘地址："+fpdz+"--"+kgString+","+jfString+","+jjString);
			rz.setCzr((String)session.getAttribute("UserName"));
			rz.setCzsj(new Date());
			rzService.insert(rz);
		 try {
		 	Thread.sleep(3000);
		 } catch (InterruptedException e) {
		 	e.printStackTrace();
		 }
		 JSONObject jsonObject = new JSONObject();
		 if (sessionmap == false && MapUtilsDf.getMapUtils().get("kt") == null) {
		 	log.info("发送数据失败");
		 	MapUtilsDf.getMapUtils().add("kt", null);
		 	jsonObject.put("js", "3");
		 	return jsonObject;
		 }else if( MapUtilsDf.getMapUtils().get("kt") != null && MapUtilsDf.getMapUtils().get("kt").equals("success")){
			 MapUtilsDf.getMapUtils().add("kt", null);
			 jsonObject.put("js", "2");
			return jsonObject;
		 }else{
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
		if(ids.length()==10){
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
 		Integer ldhS=ldh.getLdh();
 		String ld=String.valueOf(ldhS);
 		if(ld.length()==1){
 			ld=0+ld;
 		}
 		System.out.println("楼栋号"+ld); 
 		 String cgbh=ldh.getCgbh();
		 System.out.println("cgbh"+cgbh);
		 String cg=cgbh.substring(4);
		 System.out.println("cg--"+cg);
 		Integer dyhS=ldh.getDyh();
 		String dy=String.valueOf(dyhS);
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
 		 log.info("对某户 所有风盘开关计费季节操作，楼栋号:"+ldhS+"单元号："+dyhS+",发送指令 ："+ja);
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
			rz.setCz("发送对某一户 所有风盘开关计费季节操作,风盘地址："+fpdz+"--"+kgString+","+jfString+","+jjString);
			rz.setCzr((String)session.getAttribute("UserName"));
			rz.setCzsj(new Date());;
			rzService.insert(rz);
 		 try {
 		 	Thread.sleep(3000);
 		 } catch (InterruptedException e) {
 		 	e.printStackTrace();
 		 }
 		 JSONObject jsonObject = new JSONObject();
 		 if (sessionmap == false && MapUtilsDf.getMapUtils().get("dg") == null) {
 		 	log.info("发送数据失败");
 		 	MapUtilsDf.getMapUtils().add("dg", null);
 		 	jsonObject.put("js", "3");
 		 	return jsonObject;
 		 }else if( MapUtilsDf.getMapUtils().get("dg")!= null&& MapUtilsDf.getMapUtils().get("dg").equals("success")){
 			MapUtilsDf.getMapUtils().add("dg", null);
 		 	jsonObject.put("js", "2");
 		 	return jsonObject;
 		 }else{
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
