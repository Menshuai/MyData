package com.hnzy.pds.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.pds.pojo.Jzq;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.YhMessageService;

@Controller
@RequestMapping("/YhMessageCon")
public class YhMessageController {

	
	@Autowired
	private YhMessageService yhMessageService;
	
	private List<YhMessage> yhMessages;
	
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
	
	
	//添加用户信息
		@RequestMapping(value="/InsertYhMessage",method=RequestMethod.POST)
		public String Insert(HttpServletRequest request,YhMessage yhMessage){
			yhMessageService.Insert(yhMessage);
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
		
		
		
	
}
