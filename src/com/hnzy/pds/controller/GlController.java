package com.hnzy.pds.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.pds.pojo.Gl;
import com.hnzy.pds.service.GlService;



@Controller
@RequestMapping("/Gl")
public class GlController {

	@Autowired
	private GlService glService;
	
	private List<Gl> gls;

	//列表页面
	@RequestMapping("/GlfindList")
	public String findList(HttpServletRequest request ){
		gls=glService.find();
		request.setAttribute("gl", gls);
		return "gl";
	}
	
	//添加集中器信息
		@RequestMapping(value="/InsertGl",method=RequestMethod.POST)
		public String Insert(HttpServletRequest request,Gl gl){
			glService.Insert(gl);
			return "redirect:GlfindList.action";
		}
		
		//删除
		@RequestMapping("/deleteGl")
		@ResponseBody
		public void delete(HttpServletRequest request,@RequestParam("id")String id){
			//删除集中器信息
			glService.delete(id);
		}
		
		//更新
		@RequestMapping("/updateGl")
		public String update(Gl gl){
			glService.update(gl);
			return  "redirect:GlfindList.action";
		}
	
}
