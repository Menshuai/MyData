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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.pds.pojo.Jf;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.JfService;
import com.hnzy.pds.service.YhMessageService;

@Controller
@RequestMapping("JfController")
public class JfController {

	@Autowired
	private JfService jfService;
	@Autowired
	private YhMessageService yhMessageService;
	private List<YhMessage> yhInfoList;
	 private List<Jf> jfs;

	//实时列表页面
	@RequestMapping("/JFfindList")
	public String findList(HttpServletRequest request ){
		
		yhInfoList=yhMessageService.findXqName();
		request.setAttribute("XqNameList", yhInfoList);
		jfs=jfService.find();
		request.setAttribute("jf", jfs);
		return "jf";
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
	
	@RequestMapping("/JfMe")
	public String Jf(){
		return "JfMen";
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
	//更新缴费及合计金额
	@RequestMapping("update")
	@ResponseBody
	public JSONObject update(HttpSession session,String yhbh,Double jfje,Double hjje,Double syje,Double yyje){
		String userName=(String)session.getAttribute("UserName");
		JSONObject jsonObject =new JSONObject();
		if(userName!=null){
		hjje=jfje+hjje;
		//更新缴费表
		jfService.updateJfHjje(yhbh, jfje, hjje,new Date(),userName);
		//插入缴费历史表
		jfService.InsertJfHistory(yhbh, yyje, syje, jfje, hjje, new Date(), userName);
		}else{
			jsonObject.put("fail","缴费失败");
		}
		return jsonObject;
//		return "redirect:JFfindList.action";
		
	}
	//搜索实时数据
	@RequestMapping("Search")
	@ResponseBody
	public JSONObject Search(String xqm,String ldh,String dyh,String hh) throws UnsupportedEncodingException{
		xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
		JSONObject jsonObject=new JSONObject();
		if(ldh.length()==1){
			ldh="0"+ldh;
		}
		if(dyh.length()==1){
			dyh="0"+dyh;
		}
		jfs=jfService.Sear(xqm, ldh, dyh, hh);
		jsonObject.put("jfs",jfs);
		return jsonObject;
		
	}
	
	
	//搜索历史数据
	@RequestMapping("SearchHistory")
	@ResponseBody
	public JSONObject SearchHistory(String xqm,String ldh,String dyh,String hh,String time1,String time2) throws UnsupportedEncodingException{
		xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
		JSONObject jsonObject=new JSONObject();
		jfs=jfService.JffindHistorySear(xqm, ldh, dyh, hh, time1, time2);
		jsonObject.put("jfs",jfs);
		return jsonObject;
		
	}
	//添加缴费信息
	@RequestMapping("add")
	public String InsertJf(Jf jf){
//		jfService.Insert(jf);
		return "redirect:JFfindList.action";
	}
	
	//导出
			@RequestMapping("JfHistoryExportExcel")
			public void  JfHistoryExportExcel(YhMessage yhInfo,HttpSession session,HttpServletResponse response,String xqm,String ldh,String dyh,String hh,String time1,String time2) throws IOException{
				xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
				//告诉浏览器要弹出的文档类型
				response.setContentType("application/x-execl");
				//告诉浏览器这个文档作为附件给别人下载（放置浏览器不兼容，文件要编码）
				response.setHeader("Content-Disposition", "attachment;filename="+new String("用户信息列表.xls".getBytes(),"ISO-8859-1"));
				//获取输出流
					 ServletOutputStream outputStream=response.getOutputStream();
					 jfService.exportExcel(jfService.JffindHistorySear(xqm, ldh, dyh, hh, time1, time2), outputStream);
					 if(outputStream!=null){
					 outputStream.close();
				}
			}
			//导出
			@RequestMapping("JfExportExcel")
			public void  JfExportExcel(YhMessage yhInfo,HttpSession session,HttpServletResponse response,String xqm,String ldh,String dyh,String hh) throws IOException{
				xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
				//告诉浏览器要弹出的文档类型
				response.setContentType("application/x-execl");
				//告诉浏览器这个文档作为附件给别人下载（放置浏览器不兼容，文件要编码）
				response.setHeader("Content-Disposition", "attachment;filename="+new String("用户信息列表.xls".getBytes(),"ISO-8859-1"));
				//获取输出流
					 ServletOutputStream outputStream=response.getOutputStream();
					 jfService.exportExcel(jfService.Sear(xqm, ldh, dyh, hh), outputStream);
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

