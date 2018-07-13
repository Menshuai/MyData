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
import com.hnzy.pds.service.YhInfoService;
import com.hnzy.pds.service.YhMessageService;

@Controller
@RequestMapping("/Repair")
public class RepairController {
	
	private static final String BuildNo = null;
	@Autowired
	private RepairService repairService;
	private List<Repair> yhInfoList1;
	@Autowired
	private  YhInfoService yhInfoService;
	
	@Autowired
	private UserService userService;
	
	private List<Rz> rzs;
	private List<YhInfo> yhInfoList;
	private List<Repair> repairs;
	private List<Repair> hes;
	
	
	@RequestMapping("/Repairlist")
	public String list(HttpServletRequest request,Repair repair){
	 	repairs = repairService.findRepair(0);
	 	System.out.println("repairs-------"+repairs);
	    request.setAttribute("XqNameList", repairService.findXqName());
		request.setAttribute("rep",repairs);
		request.setAttribute("sums",repairService.sum(repair,0));
		request.setAttribute("wjd",repairService.state0(0));
		request.setAttribute("yjd",repairService.state1(0));
		request.setAttribute("ywc",repairService.state2(0));
 		return "repair";
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
			System.out.println("111111111111111111111");
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
				/*rz.setCzr((String)session.getAttribute("userName")); 
				rz.setCz("���"+repair.getXqName()+"С��"+repair.getBuildNo()+"¥"+repair.getCellNo()+"���ű�������");//��ȡ��������
				rz.setCzsj(new Date());
				rzService.insert(rz);*/
				
				repairs = repairService.findRepair(0);
			 	System.out.println("repairs-------"+repairs);
			    request.setAttribute("XqNameList", repairService.findXqName());
//				repairService.findplace();
				 request.setAttribute("jsName", userService.findJSName()); 
				 repair.setLx("类型");
 			//	repair.setTjr(new String((String) session.getAttribute("yhxm")));
 			//	repair.settJname(new String((String) session.getAttribute("yhxm")));
//				repair.settJtime(new Date());
//				repairService.InsertRepair(repair);
				return "add";
			}	
			
			
			//add 添加重定向到repair页面
			@RequestMapping("InsertRe")
			public String InsertRe(String wt,String yhxm,String jsr,String xqm,String ldh,String dyh,HttpSession session,Repair repair,Rz rz,HttpServletRequest request) throws UnsupportedEncodingException{
				xqm=new String(xqm.getBytes("ISO-8859-1"),"utf-8")+"";
				ldh=new String(ldh.getBytes("ISO-8859-1"),"utf-8")+"";
				jsr=new String(jsr.getBytes("ISO-8859-1"),"utf-8")+"";
				yhxm=new String(yhxm.getBytes("ISO-8859-1"),"utf-8")+"";
				wt=new String(wt.getBytes("ISO-8859-1"),"utf-8")+"";
			 	request.setAttribute("jsName", userService.findJSName());
				
				repair.setXqm(xqm);
				repair.setLdh(ldh);
				repair.setDyh(dyh);
				repair.setYhxm(yhxm);
				repair.setWt(wt);
				repair.setLx("类型");
				repair.setTjr(((String) session.getAttribute("yhxm")));
				repair.setTjsj(new Date());
			 //	repairService.InsertRepair(repair);
				return "redirect:Repairlist.action"; //重定向到repair页面 
				
				
			}
			
			//add2  添加 重定向
			@RequestMapping("InsertRe2")
			public String InsertRe2(String jSname,String name,String problem,String place,String hESName,String xqName,String buildNo,String cellNo,Repair repair,HttpServletRequest request) throws UnsupportedEncodingException{
				place=new String(place.getBytes("ISO-8859-1"),"utf-8")+"";
				hESName=new String(hESName.getBytes("ISO-8859-1"),"utf-8")+"";
				xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
				buildNo=new String(buildNo.getBytes("ISO-8859-1"),"utf-8")+"";
				jSname=new String(jSname.getBytes("ISO-8859-1"),"utf-8")+"";
				name=new String(name.getBytes("ISO-8859-1"),"utf-8")+"";
				problem=new String(problem.getBytes("ISO-8859-1"),"utf-8")+"";
				//request.setAttribute("listPlace", repairService.findplace());
				request.setAttribute("jsName", userService.findJSName());
			/*	repair.setBuildNo(buildNo);
				repair.setPlace(place);
				repair.sethESName(hESName);
				repair.setjSname(jSname);
				repair.setXqName(xqName);
				repair.setName(name);
				repair.setProblem(problem);
				repair.setType("���밲װ");
				repair.settJtime(new Date());*/
			//	repairService.InsertRepair(repair);
				return "redirect:RepairlistS.action ";//�ض���repairSqaz���밲װ����ҳ��	
				
			}
			
			
			
			@RequestMapping(value="/InsertRepair2")
			public String  InsertRepair(HttpSession session,Repair repair,Rz rz,HttpServletRequest request ) throws UnsupportedEncodingException{
				repairs = repairService.findRepair(0);
			 	System.out.println("repairs-------"+repairs);
			    request.setAttribute("XqNameList", repairService.findXqName());
//				repairService.findplace();
				 request.setAttribute("jsName", userService.findJSName());  
			//	request.setAttribute("listPlace", repairService.findplace());
			/*	request.setAttribute("jsName", userService.findJSName());*/
				return "add2";
			}	
			
		 
	
	@RequestMapping("Repair")
	public String Repair(){	
		return "RepairMen";
	}
	
	
	@RequestMapping("Sqaz")
	public String Sqaz(){
		return "SqazMen";
	}
	
	@RequestMapping("SqazList")
	public String SqazList(){
		return "Sqaz";
	}
	
		@RequestMapping("/State")
		public String State(HttpServletRequest request,Repair repair, int kffl){	
			//��ȡ���е�С��
		//	request.setAttribute("repairService",repairService.findRepair(kffl));
			return "repair";
		}
	 
		
	//申请安装
		@RequestMapping("/RepairlistS")
		public String lists(HttpServletRequest request,Repair repair){
	 	repairs=repairService.findRepair(1);
		request.setAttribute("XqNameList", repairService.findXqName());
		request.setAttribute("rep",repairs);
		request.setAttribute("sums",repairService.sum(repair,1));
		request.setAttribute("wjd",repairService.state0(1));
		request.setAttribute("yjd",repairService.state1(1));
		request.setAttribute("ywc",repairService.state2(1)); 
		return "/Sqaz";
		}
		 
		//申请安装  ---添加
		@RequestMapping(value="InsertRepairS")
		public String  InsertRepairS(HttpSession session,Repair repair,Rz rz ) throws UnsupportedEncodingException{
			//����־����Ӳ���
			/* rz.setCzr((String)session.getAttribute("userName"));//��ȡ������
			rz.setCz("���"+repair.getXqName()+"С��"+repair.getBuildNo()+"¥"+repair.getCellNo()+"���ű�������");//��ȡ��������
			rz.setCzsj(new Date()); 
			rzService.insert(rz);*/
	 		repair.setLx("���밲װ");
			repair.setTjr(new String(((String) session.getAttribute("UserName"))));
			repair.setTjsj(new Date()); 
		//	repairService.InsertRepair(repair);
			return "/repairSqaz";
			
		}
	
		
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
					//����־����Ӳ���
					rz.setCzr((String)session.getAttribute("userName"));//��ȡ������
					rz.setCz("�޸�"+repair.getXqm()+"С��"+repair.getLdh()+"¥"+repair.getDyh()+"��Ԫ"+"���ű�������");//��ȡ��������
					rz.setCzsj(new Date());//��ȡ����ʱ��
				 
					//�޸�
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
				 	System.out.println(" sql"+repairService.Search(xqm, ldh, dyh, hh,fl,lxdh));
				 	System.out.println("SearchList-----");
				 	System.out.println("f1"+fl);
					
				 	jsonObject.put("sums",repairService.sum1(xqm, ldh, dyh, hh,fl,lxdh));
					jsonObject.put("wjd",repairService.state00(xqm, ldh, dyh, hh,fl,lxdh));
					jsonObject.put("yjd",repairService.state11(xqm, ldh, dyh, hh,fl,lxdh));
					jsonObject.put("ywc",repairService.state22(xqm, ldh, dyh, hh,fl,lxdh));  
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
					return jsonObject;
				}
				
				
				//��������
				@RequestMapping("/listState1")
				@ResponseBody
				public JSONObject listState1(HttpServletRequest request,Repair repair,@Param("zt")String zt,@Param("kffl")int kffl) throws UnsupportedEncodingException{		
					zt=new String(zt.getBytes("ISO-8859-1"),"utf-8")+"";
					
					JSONObject json=new JSONObject();
			/*		repairs= repairService.findByState(place,hesName,state,kffl);
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
				
	 
				
				//����״̬������ʾ��״ͼ-----����
				@RequestMapping("/StateChart")
				public String State(HttpServletRequest request,Repair repair){	
					//��ȡ���е�С��
					/*request.setAttribute("yhInfolist", yhInfoService.findrepair());*/
					return "chart";
				}
				
				
				//����״̬������ʾ��״ͼ---���밲װ
				@RequestMapping("/StateCharts")
				public String States(HttpServletRequest request,Repair repair){	
					//��ȡ���е�С��
					/*request.setAttribute("yhInfolist", yhInfoService.findrepair());*/
					return "chartSqaz";
				}
				
				
				
				//���ݴ���ȡ����վ
				@RequestMapping("findHes")
				@ResponseBody
				public JSONObject findHes(String place) throws UnsupportedEncodingException{
					place=new String(place.getBytes("ISO-8859-1"),"utf-8")+"";
					JSONObject jsonObject=new JSONObject() ;
			//		hes=repairService.findHESName(place);
					jsonObject.put("heslist", hes);
					return jsonObject;
				}
				
			 	// chart页面
				@RequestMapping("chartSearch")
				@ResponseBody
				public Map<String, Object>chartSearch(HttpServletResponse response,HttpServletRequest request,@Param("xqName")String xqName,@Param("kffl")int kffl) throws UnsupportedEncodingException{
					 xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
					Map<String,Object> map=new HashMap<String,Object>(); 
					 JSONArray members=new JSONArray();
					 List<Object> listmap=new ArrayList<Object>();
					 List<Map<String,Object>> search=repairService.chartSearch(xqName,kffl);
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
				
				//��ȡ���е�С��
				@RequestMapping("findYhNameList")
				@ResponseBody
				public JSONObject findYhNameList(){
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("yhInfolist",yhInfoService.findrepair());
					jsonObject.put("jsname", userService.findJSName());
					
					//��ȡ���еĴ�
				//	jsonObject.put("listPlace",repairService.findplace());
					
					return jsonObject;		
				}
				
				
				//���ݴ�,����վ��ȡ���е�С������
				@RequestMapping("findXqNamebyPlace")
				@ResponseBody
				public JSONObject findXqNamebyPlace(String place,String hESName) throws UnsupportedEncodingException{
					 hESName=new String(hESName.getBytes("ISO-8859-1"),"utf-8")+"";
					place=new String(place.getBytes("ISO-8859-1"),"utf-8")+"";
					yhInfoList=yhInfoService.findXqNamebyPlace(place,hESName);
					JSONObject jsonObject=new JSONObject() ;
					jsonObject.put("jsname", userService.findJSName());
					if(yhInfoList!=null){
						jsonObject.put("xqNameList", yhInfoList);
					}else{
						jsonObject.put("fail", null);
					}
					return jsonObject;
					
				}
				
				/*//通过小区查找楼栋
					@RequestMapping("findYhBuildNObyXqName")
					@ResponseBody
					public JSONObject findYhBuildNObyXqName(String xqName,String place ,String hESName) throws UnsupportedEncodingException{
						 xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
						 place=new String(place.getBytes("ISO-8859-1"),"utf-8")+"";
						 hESName=new String(hESName.getBytes("ISO-8859-1"),"utf-8")+"";
						 yhInfoList=yhInfoService.findYhBuildNObyXqNamerepair(xqName,place,hESName);
						JSONObject jsonObject=new JSONObject() ;
						if(yhInfoList!=null){
							jsonObject.put("buildNoList", yhInfoList);
						}else{
							jsonObject.put("fail", null);
						}
						return jsonObject;
					}*/	
				 
	
			/*	//�通过楼栋获取单元
				@RequestMapping("findYhCellNOByBuild")
				@ResponseBody
				public JSONObject findYhCellNOByBuild(@Param("build")String buildNo,@Param("xqName")String xqName,@Param("place")String place) throws UnsupportedEncodingException{
					xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
					buildNo=new String(buildNo.getBytes("ISO-8859-1"),"utf-8")+"";
					yhInfoList=yhInfoService.findYhCellNOByBuildrepair(buildNo, xqName,place,hESName);
					JSONObject jsonObject=new JSONObject();
					if(yhInfoList!=null){
						jsonObject.put("cellList",yhInfoList);
					}else{
						jsonObject.put("fail",null);
					}
					return jsonObject;
					
				}*/
				
				  
	
				public List<Rz> getRzs()
				{
					return rzs;
				}

				public void setRzs(List<Rz> rzs)
				{
					this.rzs = rzs;
				}

}
