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
import com.hnzy.pds.service.RepairService;
 
import com.hnzy.pds.service.UserService;
import com.hnzy.pds.service.YhInfoService;

@Controller
@RequestMapping("/Repair")
public class RepairController {
	
	private static final String BuildNo = null;
	@Autowired
	private RepairService repairService;
	 
	
	@Autowired
	private  YhInfoService yhInfoService;
	
	@Autowired
	private UserService userService;
	
	private List<Rz> rzs;
	private List<YhInfo> yhInfoList;
	private List<Repair> repairs;
	private List<Repair> hes;
	
	//������޵Ǽ�---------   ����
	@RequestMapping("/Repairlist")
	public String list(HttpServletRequest request,Repair repair){
	/*	System.out.println("--000000000--------------");
    repairs = repairService.findRepair(0);
	    request.setAttribute("XqNameList", repairService.findXqName());
		request.setAttribute("rep",repairs);
		request.setAttribute("st",repairService.findState(0));
		request.setAttribute("list",repairService.findplace());
		request.setAttribute("sums",repairService.sum(repair,0));
		request.setAttribute("wjd",repairService.state0(0));
		request.setAttribute("yjd",repairService.state1(0));
		request.setAttribute("ywc",repairService.state2(0));
		//System.out.println("-----------------��������-----------");
*/		return "repair";
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
	
//	//repairҳ���ȡС�� 
//	@RequestMapping("/findXqNameList")
//	public String findXqNameList(HttpServletRequest request,Repair repair,String xqName){
//		return "repair";
//	}
	
	//repairҳ�� ����С����ȡ¥����
		@RequestMapping("findXqNameList")
		@ResponseBody
		public JSONObject findBuildNObyXqName(String xqName,Repair repair,HttpServletRequest request) throws UnsupportedEncodingException{
			 xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
		//	 repairs=repairService.findBuildNo(xqName);
			
		//	yhInfoList=yhInfoService.findYhBuildNObyXqName(xqName);
			JSONObject jsonObject=new JSONObject() ;
			if(repairs!=null){
				jsonObject.put("BuildNOList", repairs);
			}else{
				jsonObject.put("fail", null);
			}
			return jsonObject;
		}
		
		
		//repairҳ��  ����С��¥���Ż�ȡ��Ԫ��
		@RequestMapping("findCellNOByBuild")
		@ResponseBody
		public JSONObject findCellNOByBuild(@Param("xqName")String xqName,Repair repair,@Param("buildNo")String buildNo) throws UnsupportedEncodingException{
			xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
			buildNo=new String(buildNo.getBytes("ISO-8859-1"),"utf-8")+"";
			//repairs=repairService.findCellNo(xqName, buildNo);
			
			// yhInfoList=yhInfoService.findYhCellNOByBuild(build, xqName);
			JSONObject jsonObject=new JSONObject();
			if(repairs!=null){
				jsonObject.put("cellList",repairs);
			}else{
				jsonObject.put("fail",null);
			}
			return jsonObject;
			
		}
		
	
	  //��ӹ����걨----------   ���޵Ǽ�                                                                 
			@RequestMapping(value="/InsertRepair")
			public String  InsertRepair(HttpSession session,Repair repair,Rz rz,HttpServletRequest request,String place,String hESName) throws UnsupportedEncodingException{
				//����־����Ӳ���
				/*rz.setCzr((String)session.getAttribute("userName"));//��ȡ������
				rz.setCz("���"+repair.getXqName()+"С��"+repair.getBuildNo()+"¥"+repair.getCellNo()+"���ű�������");//��ȡ��������
				rz.setCzsj(new Date());//��ȡ����ʱ��
				rzService.insert(rz);*/
				/*��ȡ��*/
//				repairService.findplace();
				//request.setAttribute("listPlace", repairService.findplace());
				/*request.setAttribute("jsName", userService.findJSName());*/
//				repair.setType("�����걨");
//				repair.settJname(new String(((String) session.getAttribute("UserName"))));
//				repair.settJtime(new Date());
//				repairService.InsertRepair(repair);
				return "add";
			}	
			
			
			//add���޵Ǽ�   ��ӱ�ҳ���ض�������ҳ��  repairҳ��
			@RequestMapping("InsertRe")
			public String InsertRe(String problem,String name,String jSname,String place,String hESName,String xqName,String buildNo,String cellNo,HttpSession session,Repair repair,Rz rz,HttpServletRequest request) throws UnsupportedEncodingException{
				place=new String(place.getBytes("ISO-8859-1"),"utf-8")+"";
				hESName=new String(hESName.getBytes("ISO-8859-1"),"utf-8")+"";
				xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
				buildNo=new String(buildNo.getBytes("ISO-8859-1"),"utf-8")+"";
				jSname=new String(jSname.getBytes("ISO-8859-1"),"utf-8")+"";
				name=new String(name.getBytes("ISO-8859-1"),"utf-8")+"";
				problem=new String(problem.getBytes("ISO-8859-1"),"utf-8")+"";
			//	request.setAttribute("listPlace", repairService.findplace());
				request.setAttribute("jsName", userService.findJSName());
				repair.setBuildNo(buildNo);
				repair.setPlace(place);
				repair.sethESName(hESName);
				repair.setjSname(jSname);
				repair.setXqName(xqName);
				repair.setName(name);
				repair.setProblem(problem);
				repair.setType("�����걨");
			//	repair.settJname(new String(((String) session.getAttribute("userName"))));
				repair.settJtime(new Date());
			//	repairService.InsertRepair(repair);
				return "redirect:Repairlist.action";//�ض��� ��repair��������ҳ��
				
				
			}
			
			//add2 ��װ�Ǽ�  ��ӵǼ�ҳ���ض���
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
				repair.setBuildNo(buildNo);
				repair.setPlace(place);
				repair.sethESName(hESName);
				repair.setjSname(jSname);
				repair.setXqName(xqName);
				repair.setName(name);
				repair.setProblem(problem);
				repair.setType("���밲װ");
				repair.settJtime(new Date());
			//	repairService.InsertRepair(repair);
				return "redirect:RepairlistS.action ";//�ض���repairSqaz���밲װ����ҳ��	
				
			}
			
			
			
			@RequestMapping(value="/InsertRepair2")
			public String  InsertRepair(HttpSession session,Repair repair,Rz rz,HttpServletRequest request ) throws UnsupportedEncodingException{
				 
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
	 
		
	//������Ϣ���밲װ 
		@RequestMapping("/RepairlistS")
		public String lists(HttpServletRequest request,Repair repair){
				//Ҽ�����밲װ
	/*	repairs=repairService.findRepair(1);
		request.setAttribute("XqNameList", repairService.findXqName());
		request.setAttribute("rep",repairs);
		request.setAttribute("st",repairService.findState(1));
		request.setAttribute("list",repairService.findplace());
		request.setAttribute("sums",repairService.sum(repair,1));
		request.setAttribute("wjd",repairService.state0(1));
		request.setAttribute("yjd",repairService.state1(1));
		request.setAttribute("ywc",repairService.state2(1));*/
		 
		return "/repairSqaz";
		
		}
		 
				//������밲װ
				@RequestMapping(value="InsertRepairS")
				public String  InsertRepairS(HttpSession session,Repair repair,Rz rz ) throws UnsupportedEncodingException{
					//����־����Ӳ���
					/*rz.setCzr((String)session.getAttribute("userName"));//��ȡ������
					rz.setCz("���"+repair.getXqName()+"С��"+repair.getBuildNo()+"¥"+repair.getCellNo()+"���ű�������");//��ȡ��������
					rz.setCzsj(new Date());//��ȡ����ʱ��
					rzService.insert(rz);*/
					repair.setType("���밲װ");
					repair.settJname(new String(((String) session.getAttribute("UserName"))));
					repair.settJtime(new Date());
				//	repairService.InsertRepair(repair);
					//System.out.println("������밲װ-----------------");
					return "/repairSqaz";
					
				}
			
				
				//������Ϣ
				@RequestMapping(value="updateRepair" )
				public String updateRepair(HttpSession session,Repair repair,Rz rz){
					//����־����Ӳ���
					rz.setCzr((String)session.getAttribute("userName"));//��ȡ������
					rz.setCz("�޸�"+repair.getXqName()+"С��"+repair.getBuildNo()+"¥"+repair.getCellNo()+"��Ԫ"+"���ű�������");//��ȡ��������
					rz.setCzsj(new Date());//��ȡ����ʱ��
					 
					//�޸�
					repair.settJname(new String(((String) session.getAttribute("userName"))));
					repair.settJtime(new Date());
				//	repairService.updateRepair(repair);
					return "redirect:Repairlist.action";
				}
				
				
				
				//������Ϣ
				@RequestMapping(value="updateRepairS", method=RequestMethod.POST)
				public String updateRepairS(HttpSession session,Repair repair,Rz rz){
					//����־����Ӳ���
					rz.setCzr((String)session.getAttribute("userName"));//��ȡ������
					rz.setCz("�޸�"+repair.getXqName()+"С��"+repair.getBuildNo()+"¥"+repair.getCellNo()+"��Ԫ"+"���ű�������");//��ȡ��������
					rz.setCzsj(new Date());//��ȡ����ʱ��
				 
					//�޸�
					repair.settJname(new String(((String) session.getAttribute("userName"))));
					repair.settJtime(new Date());
				//	repairService.updateRepair(repair);
					return "redirect:RepairlistS.action";	
				}
				
				
				
				//���޵Ǽ�---��ѯ
				@ResponseBody
				@RequestMapping("Search")
				public JSONObject Search(HttpServletRequest request,Repair repair,@Param("xqName")String xqName,@Param("buildNo")String buildNo,@Param("cellNo")String cellNo,@Param("houseNo")String houseNo,String fl,@Param("telephone")String telephone) throws UnsupportedEncodingException{
					xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
					buildNo=new String(buildNo.getBytes("ISO-8859-1"),"utf-8")+"";
					JSONObject jsonObject=new JSONObject();
				/*	jsonObject.put("SearchList",  repairService.Search(xqName, buildNo, cellNo, houseNo,fl,telephone));
					
					jsonObject.put("sums",repairService.sum1(xqName, buildNo, cellNo, houseNo,fl,telephone));
					jsonObject.put("wjd",repairService.state00(xqName, buildNo, cellNo, houseNo,fl,telephone));
					jsonObject.put("yjd",repairService.state11(xqName, buildNo, cellNo, houseNo,fl,telephone));
					jsonObject.put("ywc",repairService.state22(xqName, buildNo, cellNo, houseNo,fl,telephone));*/
					return jsonObject;
				}
				
				//���밲װ---��ѯ
				@ResponseBody
				@RequestMapping("Search2")
				public JSONObject Search2(HttpServletRequest request,Repair repair,@Param("xqName")String xqName,@Param("buildNo")String buildNo,@Param("cellNo")String cellNo,@Param("houseNo")String houseNo,String fl,@Param("telephone")String telephone) throws UnsupportedEncodingException{
					xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
					buildNo=new String(buildNo.getBytes("ISO-8859-1"),"utf-8")+"";
					JSONObject jsonObject=new JSONObject();
				//	jsonObject.put("SearchList2",  repairService.Search(xqName, buildNo, cellNo, houseNo, fl,telephone));
					return jsonObject;
				}
				
				
			
				
				//��������
				@RequestMapping("/listState1")
				@ResponseBody
				public JSONObject listState1(HttpServletRequest request,Repair repair,@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl) throws UnsupportedEncodingException{		
					place=new String(place.getBytes("ISO-8859-1"),"utf-8")+"";
					hesName=new String(hesName.getBytes("ISO-8859-1"),"utf-8")+"";
					state=new String(state.getBytes("ISO-8859-1"),"utf-8")+"";
					
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
				
			/*	//����С��������ʾ��ͼ
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
				}*/
				
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
				
				//����С���ʹ���ȡ----¥����
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
					}	
				 
	
				//����С��¥���ţ�����ȡ----��Ԫ��
				@RequestMapping("findYhCellNOByBuild")
				@ResponseBody
				public JSONObject findYhCellNOByBuild(@Param("hESName")String hESName,@Param("build")String buildNo,@Param("xqName")String xqName,@Param("place")String place) throws UnsupportedEncodingException{
					place=new String(place.getBytes("ISO-8859-1"),"utf-8")+"";
					xqName=new String(xqName.getBytes("ISO-8859-1"),"utf-8")+"";
					buildNo=new String(buildNo.getBytes("ISO-8859-1"),"utf-8")+"";
					hESName=new String(hESName.getBytes("ISO-8859-1"),"utf-8")+"";
					yhInfoList=yhInfoService.findYhCellNOByBuildrepair(buildNo, xqName,place,hESName);
					JSONObject jsonObject=new JSONObject();
					if(yhInfoList!=null){
						jsonObject.put("cellList",yhInfoList);
					}else{
						jsonObject.put("fail",null);
					}
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
