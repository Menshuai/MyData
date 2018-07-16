package com.hnzy.pds.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.pojo.Rz;
import com.hnzy.pds.service.DataService;
import com.hnzy.pds.service.RzService;

  
@Controller
@RequestMapping("/RzController")
public class RzController {
	
	@Autowired
	private RzService rzService;
	@Autowired
	private DataService dataService;
	/*@RequestMapping("rzList")
	public String RzList(){
		return "Rz";
	}*/
	
	@RequestMapping("RzMe")
	public String Rz(){
		return "RzMen";
	}
	
	@RequestMapping("YccxMe")
	public String Yc(){
		return "YccxMen";
	}
	
	@RequestMapping("Yccx")
	public String Yccx(HttpServletRequest request){
		List<Data> YhList=dataService.find();
		request.setAttribute("YhList", YhList);
		return "Yccx";
	}
	
	
	//搜索
	@RequestMapping("Search")
	public String  Search(HttpServletRequest request,String cz) throws UnsupportedEncodingException
	{
	    if(cz!=null){
	    cz=new String(cz.getBytes("ISO-8859-1"),"utf-8")+"";
	    }
		List<Rz> rzList = rzService.Search("%"+cz+"%");
		request.setAttribute("cz",cz);
		request.setAttribute("log", rzList);
		return "/log";
	}
	
    //查询列表
	@RequestMapping("rzList")
	public String rzList(HttpServletRequest request)
	{
		List<Rz> rzList = rzService.rzList();
		request.setAttribute("log", rzList);
		return "/log";
	}
	
}
