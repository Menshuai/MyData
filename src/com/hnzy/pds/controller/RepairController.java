package com.hnzy.pds.controller;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
 
import com.hnzy.pds.pojo.Repair;
import com.hnzy.pds.pojo.Rz;
import com.hnzy.pds.pojo.YhInfo;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.RepairService;
 
import com.hnzy.pds.service.UserService;
 
import com.hnzy.pds.service.YhMessageService;

@Controller
@RequestMapping("/Repair")
public class RepairController {
	
	private static final String BuildNo = null;
	@Autowired
	private RepairService repairService;
	private List<Repair> yhInfoList1;
	@Autowired
	private UserService userService;
	
	private List<Rz> rzs;
	private List<YhInfo> yhInfoList;
	private List<Repair> repairs;
	private List<Repair> hes;
	
	//报修
	@RequestMapping("/Repairlist")
	public String list(HttpServletRequest request,Repair repair){
	 	repairs = repairService.findRepair(0);
		request.setAttribute("rep",repairs);
		request.setAttribute("st",repairService.findState(0));
		request.setAttribute("XqNameList", repairService.findXqName());
		request.setAttribute("sums",repairService.sum(repair,0));
		request.setAttribute("wjd",repairService.statewjd(0));
		request.setAttribute("yjd",repairService.stateyjd(0));
		request.setAttribute("ywc",repairService.stateywc(0));
 		return "repair";
	}
	
	
	//申请安装
		@RequestMapping("SqazList")
		public String SqazList(HttpServletRequest request,Repair repair){
			repairs=repairService.findRepair(1);
			request.setAttribute("rep",repairs);
			request.setAttribute("st",repairService.findState(1));
			request.setAttribute("XqNameList", repairService.findXqName());
			request.setAttribute("sums",repairService.sum(repair,1));
			request.setAttribute("wjd",repairService.statewjd(1));
			request.setAttribute("yjd",repairService.stateyjd(1));
			request.setAttribute("ywc",repairService.stateywc(1)); 
			return "Sqaz";
		}
	
		@RequestMapping("/RepairMe")
		public String RepairMeS(){
			return "/RepairMen";
		}
		
		@RequestMapping("SqazMe")
		public String Sqazs(){
			return "SqazMen";
		}
		
		//图标分析
		@RequestMapping("/TbfxList")
		public String Tbfx(){
			return "/Tbfx";
		}
		 
		
		//异常查询
		@RequestMapping("/YccxList")
		public String Yccx(){
			return "/Yccx";
		}
	
 	 
		@RequestMapping("/findXqNameList")
		public String findXqNameList(HttpServletRequest request,Repair repair,String xqm){
			return "repair";
		}
	
		//根据小区获取  楼栋号
		@RequestMapping("findYhldhbyxqm")
		@ResponseBody
		public JSONObject findYhldhbyxqm(String xqm) throws UnsupportedEncodingException{
			 xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
			yhInfoList1=repairService.findYhBuildNObyXqm(xqm);
			JSONObject jsonObject=new JSONObject() ;
			if(yhInfoList1!=null){
				jsonObject.put("ldhList", yhInfoList1);
			}else{
				jsonObject.put("fail", null);
			}
			return jsonObject;
		}
		
		//根据楼栋号获取  单元号
		@RequestMapping("findYhdyhByBuild")
		@ResponseBody
		public JSONObject findYhdyhByBuild(@Param("ldh")String ldh,@Param("xqm")String xqm) throws UnsupportedEncodingException{
			xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
			yhInfoList1=repairService.findYhCellNOByBuild(ldh, xqm);
			JSONObject jsonObject=new JSONObject();
			if(yhInfoList1!=null){
				jsonObject.put("dyhList",yhInfoList1);
			}else{
				jsonObject.put("fail",null);
			}
			return jsonObject;
			}
		 
		
	
			//添加页面  add                                                       
			@RequestMapping(value="/InsertRepair")
			public String  InsertRepair(HttpSession session,Repair repair,Rz rz,HttpServletRequest request,String place,String hESName) throws UnsupportedEncodingException{
				//日志
				 rz.setCzr((String)session.getAttribute("userName")); 
				rz.setCz("小区名"+repair.getXqm()+"楼栋"+repair.getLdh()+"单元"+repair.getDyh()); 
				rz.setCzsj(new Date());
				//rzService.insert(rz); 
				repairs = repairService.findRepair(0);
			    request.setAttribute("XqNameList", repairService.findXqName());
				request.setAttribute("jsName", userService.findJSName()); 
				repair.setLx("类型");
 			 //	repair.setTjr(new String((String) session.getAttribute("yhxm")));
				repair.setTjsj(new Date());
			// repairService.InsertRepair(repair);
				return "add";
			}	
			
			//add2  添加 重定向 申请安装
			@RequestMapping("InsertRepair2")
			public String InsertRe2(String jSname, String wt,String xqm,String ldh,String dyh,Repair repair,HttpServletRequest request) throws UnsupportedEncodingException{
				repairs = repairService.findRepair(0);
			    request.setAttribute("XqNameList", repairService.findXqName());
				 request.setAttribute("jsName", userService.findJSName()); 
				 repair.setLx("类型");
				return "add2";
			}	
			
			//添加故障申报
			@RequestMapping(value="InsertRe")
			public String InsertRe(String wt,String yhxm,String jsr,String xqm,String ldh,String dyh,String hh,HttpSession session,Repair repair,Rz rz,HttpServletRequest request) throws UnsupportedEncodingException{
				//向日志表添加操做
				rz.setCzr((String)session.getAttribute("userName"));//获取操作人
				rz.setCz("添加"+repair.getXqm()+"小区"+repair.getLdh()+"楼"+repair.getDyh()+"户号报修内容");//获取操作内容
				rz.setCzsj(new Date());//获取操作时间
			//	rzService.insert(rz);
				repair.setLx("故障申报");
				repair.setTjr(new String(((String) session.getAttribute("userName"))));
				repair.setTjsj(new Date());
				repairService.InsertRepair(repair);
				return "redirect:Repairlist.action"; //重定向到repair页面 
			}
			
			//添加申请安装
			@RequestMapping(value="InsertRepair2" , method=RequestMethod.POST)
			public String  InsertRepairS(String wt,String yhxm,String jsr,String xqm,String ldh,String dyh,String hh,HttpSession session,Repair repair,Rz rz,HttpServletRequest request ) throws UnsupportedEncodingException{
				//向日志表添加操做
				rz.setCzr((String)session.getAttribute("userName"));//获取操作人
				rz.setCz("添加"+repair.getXqm()+"小区"+repair.getLdh()+"楼"+repair.getDyh()+"户号报修内容");//获取操作内容
				rz.setCzsj(new Date());//获取操作时间
			//	rzService.insert(rz);
				repair.setLx("申请安装");
				repair.setTjr(new String(((String) session.getAttribute("userName"))));
				repair.setTjsj(new Date());
				repairService.InsertRepair(repair);
				return "redirect:SqazList.action";
			}
			
			 
			@RequestMapping("Repair")
			public String Repair(){	
				return "RepairMen";
			}
			
			
			@RequestMapping("Sqaz")
			public String Sqaz(){
				return "SqazMen";
			}
			
			
			
			@RequestMapping("/State")
			public String State(HttpServletRequest request,Repair repair, int kffl){	
				//request.setAttribute("repairService",repairService.findRepair(kffl));
				return "repair";
		}
	 
			/*	
				//申请安装 
				@RequestMapping(value="InsertRepairS")
				public String  InsertRepairS(HttpSession session,Repair repair,Rz rz ) throws UnsupportedEncodingException{
					
					 rz.setCzr((String)session.getAttribute("userName"));
					rz.setCz("���"+repair.getXqName()+"С��"+repair.getBuildNo()+"¥"+repair.getCellNo()+"���ű�������");
					rz.setCzsj(new Date()); 
					rzService.insert(rz);
			 		repair.setLx("类型");
					repair.setTjr(new String(((String) session.getAttribute("UserName"))));
					repair.setTjsj(new Date()); 
					repairService.InsertRepair(repair);
					return "/repairSqaz";
				}*/
			
		
				//更新
				@RequestMapping(value="updateRepair" )
				public String updateRepair(HttpSession session,Repair repair,Rz rz){
					//日志
					rz.setCzr((String)session.getAttribute("userName"));
					rz.setCz("小区名称"+repair.getXqm()+"楼栋号"+repair.getLdh()+"单元号"+repair.getDyh());
					rz.setCzsj(new Date()); 
					 
					//日志
					repair.setTjr(new String(((String) session.getAttribute("userName"))));
					repair.setTjsj(new Date());
				 	repairService.updateRepair(repair);
					return "redirect:Repairlist.action";
				}
				 
				
				//更新
				@RequestMapping(value="updateRepairS", method=RequestMethod.POST)
				public String updateRepairS(HttpSession session,Repair repair,Rz rz){
				
					rz.setCzr((String)session.getAttribute("userName"));
					rz.setCz("小区名"+repair.getXqm()+"楼栋"+repair.getLdh()+"单元"+repair.getDyh());
					rz.setCzsj(new Date());
				   
					repair.setTjr(new String(((String) session.getAttribute("userName"))));
					repair.setTjsj(new Date());
					repairService.updateRepair(repair);
					return "redirect:RepairlistS.action";	
				}
				 
				
				//搜索
				@ResponseBody
				@RequestMapping("Search")
				public JSONObject Search(HttpServletRequest request,Repair repair,@Param("xqm")String xqm,@Param("ldh")String ldh,@Param("dyh")String dyh,@Param("hh")String hh,@Param("fl")String fl,@Param("lxdh")String lxdh) throws UnsupportedEncodingException{
					xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
					ldh=new String(ldh.getBytes("ISO-8859-1"),"utf-8")+"";
					JSONObject jsonObject=new JSONObject();
				 	jsonObject.put("SearchList", repairService.Search(xqm, ldh, dyh, hh,fl,lxdh));
				 	jsonObject.put("sums",repairService.sum1(xqm, ldh, dyh, hh,fl,lxdh));//总计
					jsonObject.put("wjd",repairService.state00(xqm, ldh, dyh, hh,fl,lxdh));//未接单
					jsonObject.put("yjd",repairService.state11(xqm, ldh, dyh, hh,fl,lxdh));//已接单
					jsonObject.put("ywc",repairService.state22(xqm, ldh, dyh, hh,fl,lxdh)); //已完成
					return jsonObject;
				}
				
				//搜索
				@ResponseBody
				@RequestMapping("Search2")
				public JSONObject Search2(HttpServletRequest request,Repair repair,@Param("xqm")String xqm,@Param("ldh")String ldh,@Param("dyh")String dyh,@Param("hh")String hh,String fl,@Param("lxdh")String lxdh) throws UnsupportedEncodingException{
					xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
					ldh=new String(ldh.getBytes("ISO-8859-1"),"utf-8")+"";
					JSONObject jsonObject=new JSONObject();
				 	jsonObject.put("SearchList2",  repairService.Search(xqm, ldh, dyh, hh, fl, lxdh));
					jsonObject.put("sums",repairService.sum1(xqm, ldh, dyh, hh,fl,lxdh));//总计
					jsonObject.put("wjd",repairService.state00(xqm, ldh, dyh, hh,fl,lxdh));//未接单
					jsonObject.put("yjd",repairService.state11(xqm, ldh, dyh, hh,fl,lxdh));//已接单
					jsonObject.put("ywc",repairService.state22(xqm, ldh, dyh, hh,fl,lxdh));//已完成
					return jsonObject;
				}
				
				
				//搜索方法
				@RequestMapping("/listState1")
				@ResponseBody
				public JSONObject listState1(HttpServletRequest request,Repair repair,@Param("zt")String zt,@Param("kffl")int kffl) throws UnsupportedEncodingException{		
					zt=new String(zt.getBytes("ISO-8859-1"),"utf-8")+"";
					JSONObject json=new JSONObject();
				/* repairs= repairService.findByState(place,hesName,state,kffl);
					int Cx=repairService.stateCx(place, hesName, state,kffl);
					int ywc=repairService.stateywc(place, hesName, state,kffl);
					int yjd=repairService.stateyjd(place, hesName, state,kffl);
					int wjd=repairService.statewjd(place, hesName, state,kffl);
					json.put("wjd", wjd);
					json.put("yjd", yjd);
					json.put("ywc", ywc);
					json.put("cx", Cx);
					json.put("rep",repairs);*/
					return json;
				}
				
				//根据状态比例显示饼状图
				@RequestMapping("/StateChart")
				public String State(HttpServletRequest request,Repair repair){	
					request.setAttribute("yhInfolist", repairService.findXqName());
					return "chart";
				}
				
				
				//根据状态比例显示饼状图
				@RequestMapping("/StateCharts")
				public String States(HttpServletRequest request,Repair repair){	
					
					/*request.setAttribute("yhInfolist", yhInfoService.findrepair());*/
					request.setAttribute("yhInfolist", repairService.findXqName());
					return "chartSqaz";
				}
				 
				
			 	// chart页面
				@RequestMapping("chartSearch")
				@ResponseBody
				public Map<String, Object>chartSearch(HttpServletResponse response,HttpServletRequest request,@Param("xqm")String xqm,@Param("kffl")int kffl) throws UnsupportedEncodingException{
					 xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
					Map<String,Object> map=new HashMap<String,Object>(); 
					 JSONArray members=new JSONArray();
					 List<Object> listmap=new ArrayList<Object>();
					 List<Map<String,Object>> search=repairService.chartSearch(xqm,kffl);
			            for(Map<String, Object> m:search){
			            	for(String k:m.keySet()){
			            		JSONArray member=new JSONArray();
			            		member.add(k);
			            		member.add(m.get(k));
			            		members.add(member);
			            		listmap.add(m.get(k));
			            	}
			            }
			              map.put("data", members);
			              map.put("yjd", listmap.get(2));
			              map.put("ywc", listmap.get(1));
			              map.put("wjd", listmap.get(0));
					  return map; 
				} 
				
				@RequestMapping("findYhNameList")
				@ResponseBody
				public JSONObject findYhNameList(){
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("jsname", userService.findJSName());
					jsonObject.put("listPlace",repairService.findXqName());
					return jsonObject;		
				}
				 
				  
				public List<Rz> getRzs()
				{
					return rzs;
				}

				public void setRzs(List<Rz> rzs)
				{
					this.rzs = rzs;
				}

}
