package com.hnzy.pds.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.socket.server.ServerSessionMap;
import com.hnzy.hot.socket.util.CzUtil;
import com.hnzy.hot.socket.util.MapUtilsDf;
import com.hnzy.pds.pojo.Cg;
import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.pojo.Rz;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.CgService;
import com.hnzy.pds.service.RzService;
import com.hnzy.pds.service.YhMessageService;

@Controller
@RequestMapping("/Cg")
public class CgController {
	

	@Autowired
	private CgService cgService;
	private List<Cg> cgs;
	@Autowired
	private RzService rzService;
	@Autowired
	private YhMessageService yhMessageService;
	private static Log log = LogFactory.getLog(CgController.class);
	//列表页面
	@RequestMapping("/CgfindList")
	public String findList(HttpServletRequest request ){
		cgs=cgService.find();
		request.setAttribute("cg", cgs);
		return "cg";
	}
	
	//添加层管信息
	@RequestMapping(value="/InsertCg",method=RequestMethod.POST)
	public String Insert(HttpServletRequest request,Cg cg){
		cgService.Insert(cg);
		return "redirect:CgfindList.action";
		
	}
	
	//删除  
	@RequestMapping("/deleteCg")
	@ResponseBody
	public void delete(HttpServletRequest request,@RequestParam("id")String id){
		//删除层管信息
		cgService.delete(id);
	}
	
	//更新层管信息
		@RequestMapping("/updateCg")
		public String update(Cg cg){
			cgService.update(cg);
			return  "redirect:CgfindList.action";
		}
		 String kgString="";
		 String jfString="";
		 String jjString="";
		// 所有风盘发送操作---------------------楼栋发送数据 ----------
	   	@RequestMapping("FsLd")
	 	@ResponseBody
	 	public JSONObject FsLd(HttpSession session,HttpServletRequest request, String ids,String kg,String jf,String jj){
	   		MapUtilsDf.getMapUtils().add("dg", null);
	   		Cg cgxx=cgService.findLdDyByCg(ids);
	   		
	   		
	   		
//	   		YhMessage  dyh=yhMessageService.findyh(ids);//楼栋  
	 		Integer ldhS=cgxx.getYhMessage().getLdh();
	 		String ld=String.valueOf(ldhS);
	 		if(ld.length()==1){
	 			ld=0+ld;
	 		}
	 		System.out.println("楼栋号"+ld); 
//	 		YhMessage dyh=yhMessageService.findyh(ids);//单元
	 		Integer dyhS=cgxx.getYhMessage().getDyh();
	 		String dy=String.valueOf(dyhS);
	 		if(dy.length()==1){
	 			dy=0+dy;
	 		}
	 		System.out.println("单元号"+dy);
	 		 String cgbh=cgxx.getCgbh();
			 System.out.println("cgbh"+cgbh);
			 
			 String cg=cgbh.substring(4);
			 System.out.println("cg--------"+cg);
	 		
	   		// 把FmID转换为int类型      ids为用户编码   
	 		 int ids1 = Integer.valueOf(ids.trim());
	 		 String idsS = Integer.toHexString(ids1);//转换为十六进制  用户编码
	 		 System.out.println("idsS---------"+idsS);
//	 		 YhMessage yhmess=yhMessageService.findJzq(ids);
	 		 
	 		Cg Cgjzq=cgService.findJzqByCg(cgbh);
//	 		 String ip =yhmess.getCg().getJzq().getJzqip();
	 		String ip =Cgjzq.getJzq().getJzqip();
	 		 System.out.println("jzqip----------"+ip);
	 		 
//	 		 String port=yhmess.getCg().getJzq().getJzqport();
	 		 String port=Cgjzq.getJzq().getJzqport();
	 		 
//	 		 String ip =yhmess.getCg().getJzq().getJzqip();
	 		 System.out.println("jzqip----------"+ip);
//	 		 String port=yhmess.getCg().getJzq().getJzqport();
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
	 		 
	 		 //发送数据
	 		 String ja =ld+dy+"F010B1FFF0F0F0FF"+""+kg+""+jf+""+jj+ld+dy+"FF";
	 		 log.info("对某栋楼 所有风盘开关计费季节操作发送指令 ："+ja);
	 		 System.out.println("ja----"+ja);
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
				rz.setCz("发送对某栋楼所有风盘开关计费季节操作："+kgString+","+jfString+","+jjString);
				rz.setCzr((String)session.getAttribute("UserName"));
				rz.setCzsj(new Date());;
				rzService.insert(rz);
	 		 // IP地址和端口号
	 		 String pt = "/" + ip + ":" + port;
	 		 boolean sessionmap = cz(ja, pt);
	 		 System.out.println("ja-------------"+ja);
	 		 try {
	 		 	Thread.sleep(3000);
	 		 } catch (InterruptedException e) {
	 		 	e.printStackTrace();
	 		 }
	 		 JSONObject jsonObject = new JSONObject();
	 		if (sessionmap == false && MapUtilsDf.getMapUtils().get("dg") == null) {
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
	   	
		// 某层所有风盘发送操作-------------------------所有风盘发送操作 ----------
	   	@RequestMapping("SCxZx")
	 	@ResponseBody
	 	public JSONObject SCxZx(HttpSession session,HttpServletRequest request, String ids,String kg,String jf,String jj){
	   		MapUtilsDf.getMapUtils().add("dg", null);
//	   		YhMessage  ldh=yhMessageService.findyh(ids);//楼栋  
	   		Cg cgxx=cgService.findLdDyByCg(ids);
	   		
	 		Integer ldhS=cgxx.getYhMessage().getLdh();
	 		String ld=String.valueOf(ldhS);
	 		if(ld.length()==1){
	 			ld=0+ld;
	 		}
	 		System.out.println("楼栋号"+ld); 
	 		
//	 		YhMessage dyh=yhMessageService.findyh(ids);//单元
	 		Integer dyhS=cgxx.getYhMessage().getDyh();
	 		String dy=String.valueOf(dyhS);
	 		if(dy.length()==1){
	 			dy=0+dy;
	 		}
	 		System.out.println("单元号"+dy);
	 		 String cgbh=cgxx.getCgbh();
			 System.out.println("cgbh"+cgbh);
			 
			 String cg=cgbh.substring(4);
			 System.out.println("cg--------"+cg);
	 		
	   		// 把FmID转换为int类型      ids为用户编码   
	 		 int ids1 = Integer.valueOf(ids.trim());
	 		 String idsS = Integer.toHexString(ids1);//转换为十六进制  用户编码
	 		 System.out.println("idsS---------"+idsS);
	 		 
	 		/* Fp fp=fpService.findfpdz(ids.trim());
	 		 String fpdzs=fp.getFpbh();
	 		 System.out.println("fpdzs---------"+fpdzs);//风盘地址
	 		 */
//	 		 YhMessage yhmess=yhMessageService.findJzq(ids);
	 		 
	 		Cg Cgjzq=cgService.findJzqByCg(cgbh);
//	 		 String ip =yhmess.getCg().getJzq().getJzqip();
	 		String ip =Cgjzq.getJzq().getJzqip();
	 		 System.out.println("jzqip----------"+ip);
	 		 
//	 		 String port=yhmess.getCg().getJzq().getJzqport();
	 		 String port=Cgjzq.getJzq().getJzqport();
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
	 		 
	 		 //发送数据
	 		 String ja =ld+dy+"F010B1"+cg+"F0F0F0FF"+""+kg+""+jf+""+jj+ld+dy+"FF";
	 		 System.out.println("ja----"+ja);
	 		 log.info("对某层 所有风盘开关计费季节操作发送指令：层管编号："+cg+"，指令---"+ja);
	 		 System.out.println("ja----"+ja);
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
				rz.setCz("发送对某层所有风盘开关计费季节操作,层管编号："+kgString+","+jfString+","+jjString);
				rz.setCzr((String)session.getAttribute("UserName"));
				rz.setCzsj(new Date());;
				rzService.insert(rz);
	 		 
	 		 // IP地址和端口号
	 		 String pt = "/" + ip + ":" + port;
	 		 boolean sessionmap = cz(ja, pt);
	 		 System.out.println("ja-------------"+ja);
	 		 try {
	 		 	Thread.sleep(3000);
	 		 } catch (InterruptedException e) {
	 		 	e.printStackTrace();
	 		 }
	 		 JSONObject jsonObject = new JSONObject();
	 		if (sessionmap == false && MapUtilsDf.getMapUtils().get("dg") == null) {
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
