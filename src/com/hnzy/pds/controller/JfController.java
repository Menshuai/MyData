package com.hnzy.pds.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnzy.pds.pojo.Jf;
import com.hnzy.pds.service.JfService;

@Controller
@RequestMapping("JfController")
public class JfController {

	@Autowired
	private JfService jfService;
	
	 private List<Jf> jfs;

	//列表页面
	@RequestMapping("/JFfindList")
	public String findList(HttpServletRequest request ){
		jfs=jfService.find();
		request.setAttribute("jf", jfs);
		return "jf";
	}
	
	@RequestMapping("/JfMe")
	public String Jf(){
		return "JfMen";
	}
	
	
}

