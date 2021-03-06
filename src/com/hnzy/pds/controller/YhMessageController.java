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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.pojo.Fp;
import com.hnzy.pds.pojo.Jf;
import com.hnzy.pds.pojo.Rz;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.DataService;
import com.hnzy.pds.service.FpService;
import com.hnzy.pds.service.JfService;
import com.hnzy.pds.service.RzService;
import com.hnzy.pds.service.YhMessageService;
import com.sun.org.apache.xpath.internal.operations.And;

@Controller
@RequestMapping("/YhMessageCon")
public class YhMessageController {
	
	@Autowired
	private YhMessageService yhMessageService;
	private List<YhMessage> yhMessages;
	private List<YhMessage> yhInfoList;
	@Autowired
	private DataService dataService;
	@Autowired
	private RzService rzService;
	@Autowired
	private JfService jfService;
	@Autowired
	private FpService fpService;
	public List<Data> YhList;
  
  
 
	//数据报表  左右栏
	@RequestMapping("/HomeMe")
	public String SkqMe(){
		return "/SjbbMen";
	}
	
	//数据报表
	@RequestMapping("sjbb")
	public String sjbb(HttpServletRequest request) {
		YhList=dataService.find();
		yhInfoList=yhMessageService.findXqName();
		request.setAttribute("XqNameList", yhInfoList);
		request.setAttribute("YhList", YhList);
		return "sjbb";
	}
			
	//中央空调数据列表
	@RequestMapping("/YhMessagefindList")
	public String findyh(HttpServletRequest request){
		List<YhMessage> yhlistMe=yhMessageService.find();
		request.setAttribute("YhListMe", yhlistMe);
		return "/data";
	}

	//用户列表页面
	@RequestMapping("/yhfindList")
	public String findList(HttpServletRequest request ){
		yhMessages=yhMessageService.find();
		request.setAttribute("yhMessage", yhMessages);
		return "/yhMessage";
	}
	
	@RequestMapping("/findfp")
	@ResponseBody
	public JSONObject findfp(){
		JSONObject jsonObject=new JSONObject();
		List<Fp> fps=fpService.findfp();
		jsonObject.put("fps",fps);
		return jsonObject;
	}
	
	
	//添加用户信息
		@RequestMapping(value="/InsertYhMessage",method=RequestMethod.POST)
		public String Insert(HttpServletRequest request,YhMessage yhMessage,String fpxh,String fpbh,String wz,String fpbz){
			if(fpxh!=null && fpbh!=null && wz!=null){
			Integer fpgs=yhMessage.getfpdz();
		    for(int i=1;i<=fpgs;i++){
		    	Integer fpdz=Integer.valueOf("0"+i);
		    	yhMessage.setfpdz(fpdz);
		    	//添加用户信息
		    	yhMessageService.Insert(yhMessage);
		    	//添加缴费实时信息
		    	Jf jf=new Jf();
		    	jf.setGetime("");
		    	jf.setHjje(0);
		    	jf.setJfje(0);
		    	jf.setSyje(0);
		    	jf.setYhbh(yhMessage.getYhbh());
		    	jf.setYyje(0);
		    	jf.setUserName("");
		    	jf.setTime("");
		    	jfService.Insert(jf);
		    	//添加风盘表
		    	Fp fp =new  Fp();
		    	fp.setFpbh(fpbh);
		    	fp.setYhbh(yhMessage.getYhbh());
		    	fp.setBz(fpbz);
		    	fp.setXh(fpxh);
		    	fp.setWz(wz);
		    	fpService.Insert(fp);
		    	//添加实时表
		    	Data data=new Data();
		    	data.setFpbh(fpbh);
		    	data.setYhbh(yhMessage.getYhbh());
		    	data.setFpdz(fpdz);
		    	data.setGdtime("0");
		    	data.setZdtime("0");
		    	data.setDddtime("0");
		    	data.setJf("00");
		    	data.setSdwd("");
		    	data.setSnwd("");
		    	data.setKg("00");
		    	data.setMs("00");
		    	data.setDw("00");
		    	data.setBj("00");
		    	data.setJj("00");
		    	data.setTime("");
		    	data.setYydl(0);
		    	data.setSyje(0);
		    	data.setYyje(0);
		    	data.setJbf(0);
		    	data.setNlf(0);
		    	data.setGdtime("0");
		    	data.setDzdtime("0");
		    	data.setZdtime("0");
		    	dataService.InsertYhSSb(data);
		    }
			}
			return "redirect:yhfindList.action";
		}
		
		
		//删除
		@RequestMapping("/deleteYhMessage")
		@ResponseBody
		public void delete(HttpServletRequest request,YhMessage yhMessage,@RequestParam("id")String id){
			//删除用户信息
			yhMessageService.delete(id);
		}
		
		//更新 修改
		@RequestMapping("/updateYhMessage")
		public String update(YhMessage yhMessage){
			yhMessageService.update(yhMessage);
			return  "redirect:yhfindList.action";
		}
		
		
		//获取所有的小区
		@RequestMapping("findYhNameList")
		public String findYhNameList(ModelMap map,HttpServletRequest request){
			yhInfoList=yhMessageService.findXqName();
			request.setAttribute("XqNameList", yhInfoList);
			//map.addAttribute("XqNameList",yhInfoList);
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
						@Param("dyh")int dyh,@Param("hh")Integer hh,@Param("time1") String time1,@Param("time2") String time2) throws UnsupportedEncodingException{
			JSONObject jsonObject=new JSONObject();
			//hh为null查询实时表，否则查询历史表
			if(hh==null){
				hh=0;
//				List<Data> YhList=dataService.find();
				YhList= dataService.searchInfo(xqm, ldh, dyh, hh, time1, time2);
				jsonObject.put("findXqInfoHistory",YhList);
			}else{
				YhList= dataService.searchHistory(xqm, ldh, dyh, hh,time1,time2);
				jsonObject.put("findXqInfoHistory",YhList );
			}
				return jsonObject;		
			}
		
				
		//导出
		@RequestMapping("YhInfodoExportExcel")
		public void  YhInfodoExportExcel(YhMessage yhInfo,HttpSession session,HttpServletResponse response,@Param("xqm")String xqm,@Param("ldh")int ldh,
					@Param("dyh")int dyh,@Param("hh")Integer hh,@Param("time1") String time1,@Param("time2") String time2) throws IOException{
			xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
			//告诉浏览器要弹出的文档类型
			response.setContentType("application/x-execl");
			//告诉浏览器这个文档作为附件给别人下载（放置浏览器不兼容，文件要编码）
			response.setHeader("Content-Disposition", "attachment;filename="+new String("用户信息列表.xls".getBytes(),"ISO-8859-1"));
			//获取输出流
			if(hh==null){
				 hh=0;
				 ServletOutputStream outputStream=response.getOutputStream();
				 yhMessageService.exportExcel(dataService.searchInfo(xqm, ldh, dyh, hh, time1, time2), outputStream);
				 if(outputStream!=null){
						outputStream.close();
				}
						 
			}else{
				ServletOutputStream outputStream=response.getOutputStream();
				yhMessageService.exportExcel(dataService.searchHistory(xqm, ldh, dyh, hh,time1,time2), outputStream);
				if(outputStream!=null){
					outputStream.close();
				}
			}
			
					
			//日志
			Rz rz=new Rz();
			rz.setCz("导出:小区名称："+xqm+",楼栋号："+ldh+",单元号："+dyh);
			rz.setCzr((String)session.getAttribute("UserName"));
			rz.setCzsj(new Date());;
			rzService.insert(rz);
			
		}
				
	
}
