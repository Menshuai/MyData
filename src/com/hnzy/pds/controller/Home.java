package com.hnzy.pds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Home")
public class Home {
 
	 
		/*//用户管理
		@RequestMapping("/Yhgl")
		public String yhgl(){
			return "yhgl";
		}*/
		
	/*	//角色管理
		@RequestMapping("/RoleGl")
		public String RoleGl(){
			return "Rolegl";
		}*/
		 
		
		//报修
		@RequestMapping("/RepairMe")
		public String RepairMeS(){
			return "/RepairMen";
		}
		
		 //系统设置
		@RequestMapping("/XtszMe")
		public String StszMe(){
			return "/XtszMen";
		}
		
		
}
