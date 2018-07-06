package com.hnzy.pds.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.pds.pojo.Yhlb;
import com.hnzy.pds.service.YhlbService;

@Controller
@RequestMapping("/Yhlb")
public class YhlbController {

	@Autowired
	private YhlbService yhlbService;
	private List<Yhlb> yhlbs;
	
	

	//列表页面
	@RequestMapping("/YhlbfindList")
	public String findList(HttpServletRequest request ){
		yhlbs=yhlbService.find();
		request.setAttribute("yhlb", yhlbs);
		return "yhlb";
		
	}
	
	    //添加集中器信息
		@RequestMapping(value="/InsertYhlb",method=RequestMethod.POST)
		public String Insert(HttpServletRequest request,Yhlb yhlb){
			yhlbService.Insert(yhlb);
			return "redirect:YhlbfindList.action";
		}
		
		//删除
		@RequestMapping("/deleteYhlb")
		@ResponseBody
		public void delete(HttpServletRequest request,@RequestParam("id")String id){
			//删除集中器信息
			yhlbService.delete(id);
		}
		
		//更新
		@RequestMapping("/updateYhlb")
		public String update(Yhlb yhlb){
			yhlbService.update(yhlb);
			return  "redirect:YhlbfindList.action";
		}
}
