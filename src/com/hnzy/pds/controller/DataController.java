package com.hnzy.pds.controller;

 
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.socket.server.ServerSessionMap;
import com.hnzy.hot.socket.util.CzUtil;
import com.hnzy.hot.socket.util.MapUtilsDf;
import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.pojo.Fp;
import com.hnzy.pds.pojo.Jzq;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.DataService;
import com.hnzy.pds.service.FpService;
import com.hnzy.pds.service.YhMessageService;
 
@Controller
@RequestMapping("/DataController")
public class DataController {
	 
	@Autowired
	private DataService dataService;
	
	@Autowired
	private YhMessageService yhMessageService;
	
	@Autowired
	private FpService fpService;
	private static Log log = LogFactory.getLog(DataController.class);
	
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
		List<Data> YhList=dataService.find();
		request.setAttribute("YhList", YhList);
		return "/sbgl";//设备管理sbgljsp
	}
	
	@RequestMapping("/DataMe")
	public String SkqMe(){
		return "/DataMen";
	}
	
	@RequestMapping("/SbglMe")
	public String DataMe(){
		return "/SbglMen";
	}
	 
	//查询状态------对某一户--------------查询状态-----------------
	@ResponseBody
	@RequestMapping("CxState")
	public JSONObject CxState(HttpServletRequest request, String ids,Data zykt,YhMessage yhMessage){
		MapUtilsDf.getMapUtils().add("kt", null);
		//用户编号
		String idString=ids.substring(0, ids.length()-1);
		//风盘编号
		String fpbh=ids.substring(ids.length()-1);
		System.out.println(idString+"---"+fpbh);
		//用户编号转换为十进制
		int ids1 = Integer.valueOf(idString); 
		String idsS = Integer.toHexString(ids1);//16进制     
	 	YhMessage  ldh=yhMessageService.finldh(idString);//楼栋  
		Integer ldhS=ldh.getLdh();
		String ld=String.valueOf(ldhS);
		if(ld.length()==1){
			ld=0+ld;
		}
		
		YhMessage dyh=yhMessageService.findyh(idString);//单元
		Integer dyhS=dyh.getDyh();
		String dy=String.valueOf(dyhS);
		if(dy.length()==1){
			dy=0+dy;
		}
		
		 String cgbh=ldh.getCgbh();
		 System.out.println("cgbh"+cgbh);
		 
		 String cg=cgbh.substring(4);
		 
		String ja =ld+dy+"F010B5"+cg+""+idsS+"0"+fpbh+"FFFFFFFFFFFF";//起始到结束  01终端
		
		YhMessage yhmess=yhMessageService.findJzq(idString);
		String ip =yhmess.getCg().getJzq().getJzqip();
		String port=yhmess.getCg().getJzq().getJzqport();
		// IP地址和端口号
		String pt = "/" + ip + ":" + port; 
		System.out.println("pt-------------"+pt);
		boolean sessionmap = cz(ja, pt);
		
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
	
	// ---------------------单个风盘操作---------
  	@RequestMapping("DCxZx")
	@ResponseBody
	public JSONObject DCxZx(HttpServletRequest request, String ids,String fpdz,Data zykt,String kg,String jf,String jj){
  		MapUtilsDf.getMapUtils().add("kt", null);
  		//用户编号
  		String idString=ids.substring(0, ids.length()-1);
  		//风盘编号
  		String fpbh=ids.substring(ids.length()-1);
  		YhMessage  ldh=yhMessageService.finldh(idString);//楼栋  
		Integer ldhS=ldh.getLdh();
		String ld=String.valueOf(ldhS);
		if(ld.length()==1){
			ld=0+ld;
		}
		System.out.println("楼栋号"+ld); 
		String cgbh=ldh.getCgbh();
		String cg=cgbh.substring(4);
		System.out.println("cg---"+cg);
		YhMessage dyh=yhMessageService.findyh(idString);//单元
		Integer dyhS=dyh.getDyh();
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
		 String ja =ld+dy+"F010B1"+cg+""+idsS+"0"+fpbh+""+""+kg+""+jf+""+jj+"FFFFFF";
		 System.out.println("ja----"+ja);
		 // IP地址和端口号
		 String pt = "/" + ip + ":" + port;
		 boolean sessionmap = cz(ja, pt);
		 log.info("风盘操作发送数据："+ja);
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
 	public JSONObject SCxZx(HttpServletRequest request, String ids,String fpdz,Data zykt,String kg,String jf,String jj){
   		MapUtilsDf.getMapUtils().add("dg", null);
   		//用户编号
  	   String idString=ids.substring(0, ids.length()-1);
   		
   		YhMessage  ldh=yhMessageService.finldh(idString);//楼栋  
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
 		YhMessage dyh=yhMessageService.findyh(idString);//单元
 		Integer dyhS=dyh.getDyh();
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
 // 	 String ja =ld+dy+"F010B1"+cg+""+idsS+"FF"+""+kg+""+jf+""+jj+"FFFFFF"; 
 		 String ja =ld+dy+"F010B1"+cg+""+idsS+"FF"+""+kg+""+jf+""+jj+"FFFFFF";
 		 System.out.println("ja----"+ja);
 		 // IP地址和端口号
 		 String pt = "/" + ip + ":" + port;
 		 boolean sessionmap = cz(ja, pt);
 		 log.info("风盘操作发送数据："+ja);
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
