package com.hnzy.pds.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.pds.pojo.Djb;
 
import com.hnzy.pds.service.DjbService;

/**
 * 小区信息单价表
 * @author Administrator
 *
 */
@Controller
@RequestMapping("Djb")
public class DjbController {

	@Autowired
	private DjbService djbService;
	private List<Djb> djbs;
	
	//列表页面
	@RequestMapping("DjbfindList")
	public String findList(HttpServletRequest request){
		djbs=djbService.find();
		request.setAttribute("djb", djbs);
		return "djb";//返回小区信息jsp页面
		
	}
	
		//添加 信息
		@RequestMapping(value="/InsertDjb",method=RequestMethod.POST)
		public String Insert(HttpServletRequest request,Djb djb){
			djbService.Insert(djb);
			return "redirect:DjbfindList.action";
		}
		
		//删除
		@RequestMapping("/deleteDjb")
		@ResponseBody
		public void delete(HttpServletRequest request,Djb djb,@RequestParam("id")String id){
			//删除信息
			djbService.delete(id);
		}
		
		//更新
		@RequestMapping("/updateDjb")
		public String update(Djb djb){
			djbService.update(djb);
			return  "redirect:DjbfindList.action";
		}
		
	
}
