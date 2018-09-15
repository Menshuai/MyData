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
import com.hnzy.pds.pojo.Price;
import com.hnzy.pds.service.PriceService;

@Controller
@RequestMapping("/Price")
public class PriceController {

	@Autowired
	private PriceService priceService;
	private List<Price> price;
	
	@RequestMapping("/pricefindList")
	public String findList(HttpServletRequest request ){
		price=priceService.find();
		request.setAttribute("price", price);
		return "Price";
	}
	
	
	//添加 信息
	@RequestMapping(value="/InsertPrice",method=RequestMethod.POST)
	public String Insert(HttpServletRequest request,Price price){
		priceService.Insert(price);
		return "redirect:pricefindList.action";
	}
	
	//删除
	@RequestMapping("/deletePrice")
	@ResponseBody
	public void delete(HttpServletRequest request,@RequestParam("id")String id){
		//删除信息
		priceService.delete(id);
	}
	
	//更新
	@RequestMapping("/updatePrice")
	public String update(Price price){
		priceService.update(price);
		return  "redirect:pricefindList.action";
	}
	
	
}
 