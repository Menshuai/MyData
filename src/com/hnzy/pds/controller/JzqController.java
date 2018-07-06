package com.hnzy.pds.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.hot.socket.server.ServerSessionMap;
import com.hnzy.hot.socket.util.CzUtil;
import com.hnzy.hot.socket.util.MapUtils;
import com.hnzy.pds.pojo.Jzq;
import com.hnzy.pds.service.JzqService;
 
@Controller
@RequestMapping("/Jzq")
public class JzqController {

	@Autowired
	private JzqService jzqService;
	private List<Jzq> jzqs;
	
	private static Log log = LogFactory.getLog(JzqController.class);
	//列表页面
	@RequestMapping("/JzqfindList")
	public String findList(HttpServletRequest request ){
		jzqs=jzqService.find();
		request.setAttribute("jzq", jzqs);
		return "jzq";
		
	}
	
	@RequestMapping("JzqMe")
	public String Jzq(){	
		return "JzqMen";
	}
	
	
	//添加集中器信息
	@RequestMapping(value="/InsertJzq",method=RequestMethod.POST)
	public String Insert(HttpServletRequest request,Jzq jzq){
		jzqService.Insert(jzq);
		return "redirect:JzqfindList.action";
	}
	
	//删除
	@RequestMapping("/deleteJzq")
	@ResponseBody
	public void delete(HttpServletRequest request,Jzq jzq,@RequestParam("id")String id){
		//删除集中器信息
		jzqService.delete(id);
	}
	
	//更新
	@RequestMapping("/updateJzq")
	public String update(Jzq jzq){
		jzqService.update(jzq);
		return  "redirect:JzqfindList.action";
	}
	
	@RequestMapping("Cxzt")
	@ResponseBody 
	public String Cxzt(String jzqnet,String jzqip,String jzqport){
		String pt = "/" + jzqip + ":" + jzqport;
		String ja = "F00A0100" + jzqnet ;
		boolean sessionmap = cz(ja, pt);
		Object log;
		System.out.println("查询状态发送数据："+ja);
		//log.info("查询状态发送数据："+ja);
		
		try {
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(sessionmap==true && MapUtils.getMapUtils().get("jzq")!=null && MapUtils.getMapUtils().get("jzq").equals("success")){
			 MapUtils.getMapUtils().add("jzq", null);
			return "0";
		}else if(sessionmap==true && MapUtils.getMapUtils().get("jzq")!=null && MapUtils.getMapUtils().get("jzq").equals("fail")){
			 MapUtils.getMapUtils().add("jzq", null);
			return "1";
		}else if(sessionmap==false){
			 MapUtils.getMapUtils().add("jzq", null);
			 System.out.println("发送数据失败,集中器不在线");
			 
			return "3";
		}else {
			 MapUtils.getMapUtils().add("jzq", null);
			return "2";
		}	
	}
	//抽取相同部分
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
		String mString = ja + je + "FF";
		// 解码
		byte[] b = CzUtil.jm(mString);
		ServerSessionMap sessionMap = ServerSessionMap.getInstance();
		boolean sessionmap = sessionMap.sendMessage(keys, b);
		return sessionmap;

	}
	
	
	
}
