package com.hnzy.pds.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
 
import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.pojo.Rz;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.DataService;
import com.hnzy.pds.service.RzService;
import com.hnzy.pds.service.YhMessageService;

@Controller
@RequestMapping("Home")
public class Home {

	@Autowired
	private DataService dataService;
	
	@Autowired 
	private YhMessageService yhMessageService;
	private List<YhMessage> yhInfoList;
	@Autowired
	private RzService rzService;
	
	//数据报表
		@RequestMapping("sjbb")
		public String sjbb(HttpServletRequest request) {
			List<Data> YhList=dataService.find();
			request.setAttribute("YhList", YhList);
			System.out.println("-----YhList:"+YhList);
			return "sjbb";
		}
		
		//用户管理
		@RequestMapping("/Yhgl")
		public String yhgl(){
			return "yhgl";
		}
		
		
		//角色管理
		@RequestMapping("/RoleGl")
		public String RoleGl(){
			return "Rolegl";
		}
		
		
		//数据报表  左右栏
		@RequestMapping("/HomeMe")
		public String SkqMe(){
			return "/SjbbMen";
		}
		
		//报修
		@RequestMapping("/RepairMe")
		public String RepairMeS(){
			return "/RepairMen";
		}
		
		 //系统设置
		@RequestMapping("/XtszMe")
		public String StszMe(){
			return "/XtszMen";
		}
		
		//搜索并显示
		@RequestMapping("searchInfo")
		@ResponseBody
		public JSONObject searchInfo(HttpServletRequest request,ModelMap map,@Param("xqm")String xqm,@Param("ldh")int ldh,
				@Param("dyh")int dyh,@Param("hh")Integer hh,@Param("time") String time) throws UnsupportedEncodingException{
			xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
			JSONObject jsonObject=new JSONObject();
			//hh为null查询实时表，则查询历史表
			if(hh==null){
				 hh=0;
				 yhInfoList= yhMessageService.searchInfo(xqm, ldh, dyh, hh,time);
				jsonObject.put("findXqInfoFmHistory",yhInfoList);
			}/*else{
				 yhInfoList= yhMessageService.searchFmHistory(xqm, ldh, dyh, hh, sfjf,valTemp1,valTemp2,roomTemp1,roomTemp2,famKd,recordTime1,recordTime2);
				jsonObject.put("findXqInfoFmHistory",yhInfoList );	
			}*/
			return jsonObject;		
		}
		//导出
		@RequestMapping("YhInfodoExportExcel")
		public void  YhInfodoExportExcel(YhMessage yhInfo,HttpSession session,HttpServletResponse response,@Param("xqm")String xqm,@Param("ldh")int ldh,
				@Param("dyh")int dyh,@Param("hh")Integer hh,@Param("time") String time) throws IOException{
			xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
			//告诉浏览器要弹出的文档类型
			response.setContentType("application/x-execl");
			//告诉浏览器这个文档作为附件给别人下载（放置浏览器不兼容，文件要编码）
			response.setHeader("Content-Disposition", "attachment;filename="+new String("用户信息列表.xls".getBytes(),"ISO-8859-1"));
			//获取输出流
			if(hh==null){
				 hh=0;
					ServletOutputStream outputStream=response.getOutputStream();
					 
					yhMessageService.exportExcel(yhMessageService.searchInfo(xqm, ldh, dyh, hh,time), outputStream);
					if(outputStream!=null){
						outputStream.close();
					}	 
			}else{
			ServletOutputStream outputStream=response.getOutputStream();
		//	yhMessageService.exportExcel(yhMessageService.searchFmHistory(xqm, ldh, dyh, hh, sfjf,valTemp1,valTemp2,roomTemp1,roomTemp2,famKd,recordTime1,recordTime2), outputStream);
				 
			if(outputStream!=null){
				outputStream.close();
			  }
		    }
			//日志
			Rz rz=new Rz();
			rz.setCz("导出:小区名称："+xqm+",楼栋号："+ldh+",单元号："+dyh);
			rz.setCzr((String)session.getAttribute("userName"));
			rz.setCzsj(new Date());;
			rzService.insert(rz);
		}
		
		//获取所有的小区
		@RequestMapping("findYhNameList")
		public String findYhNameList(@RequestParam(value="pageNum",required=false)String pageNum,
				ModelMap map,HttpServletRequest request,YhMessage yhInfo){
			yhInfoList=yhMessageService.findXqName();
			request.setAttribute("yhInfoList", yhInfoList);
			//map.addAttribute("yhInfoList",yhInfoList);
			return "sjbb";		
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
		
		//根据小区楼栋号获取  单元号
		@RequestMapping("findYhdyhByBuild")
		@ResponseBody
		public JSONObject findYhdyhByBuild(@Param("build")int build,@Param("xqm")String xqm) throws UnsupportedEncodingException{
			xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
			yhInfoList=yhMessageService.findYhCellNOByBuild(build, xqm);
			JSONObject jsonObject=new JSONObject();
			if(yhInfoList!=null){
				jsonObject.put("cellList",yhInfoList);
			}else{
				jsonObject.put("fail",null);
			}
			return jsonObject;
			
		}
}
