package com.hnzy.pds.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.pds.pojo.Fp;
import com.hnzy.pds.service.FpService;

@Controller
@RequestMapping("/Fp")
public class FpController {

	@Autowired
	private FpService fpService;
	private List<Fp> fps;
	
	//列表页面
		@RequestMapping("/FpfindList")
		public String findList(HttpServletRequest request ){
			fps=fpService.find();
			request.setAttribute("fp", fps);
			return "fp";
			
		}
	
		//添加集中器信息
		@RequestMapping(value="/InsertFp",method=RequestMethod.POST)
		public String Insert(HttpServletRequest request,Fp fp){
			fpService.Insert(fp);
			return "redirect:FpfindList.action";
		}
		
		//删除
		@RequestMapping("/deleteFp")
		@ResponseBody
		public void delete(HttpServletRequest request,@RequestParam("id")String id){
			//删除集中器信息
			fpService.delete(id);
		}
		
		//更新
		@RequestMapping("/updateFp")
		public String update(Fp fp){
			fpService.update(fp);
			return  "redirect:FpfindList.action";
		}
		
	
	
	
}
