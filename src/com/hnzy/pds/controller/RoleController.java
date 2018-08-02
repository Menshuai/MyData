package com.hnzy.pds.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
 
import com.hnzy.pds.pojo.Menu;
import com.hnzy.pds.pojo.Role;
import com.hnzy.pds.pojo.User;
import com.hnzy.pds.service.MenuService;
import com.hnzy.pds.service.RoleService;

@Controller
@RequestMapping("/roleController")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MenuService menuService;
	
	private List<Role> roles;
	
	//查询角色列表
	@RequestMapping("/RoleGl")
	public String list(HttpServletRequest request){
		roles=roleService.findAllRole();
		request.setAttribute("role", roles);
		return "Rolegl";
	}
	
	//添加一个角色
	@RequestMapping("/add")
	public String addUser(Role role){
		role.setCreateTime(new Date());
		roleService.save(role);
		return "redirect:RoleGl.action";
		 
	}
	
	//修改角色
	@RequestMapping("/edit")
	public String editUser(Role role){
		role.setLastEditTime(new Date());
		roleService.edit(role);
		return "redirect:RoleGl.action";
		
	}
	
	//删除一个角色，支持一次删除多个
	@ResponseBody
	@RequestMapping("/delete")
	public void deleteUser(@RequestParam String id){
		roleService.delete(id);
	}
	
	//修改角色并返回用户界面
	@RequestMapping(value = "/amend")
	public String editrole(HttpServletRequest request,@RequestParam(value = "id", required = false) String id){
		String[] roleId=request.getParameterValues("userRoleList");
	 
		menuService.editURole(roleId, id);
		return "redirect:RoleGl.action";
	}
	
	//分配权限
	@RequestMapping("getqx")
	@ResponseBody
	public JSONObject getAmend(Integer id){
		//查询角色目前所用户的菜单
		List<Menu> roleMenu=menuService.findMenuByRId(id);
		//查询所有的菜单
		List<Menu> menuAll=menuService.find();
		JSONObject jsonObject=new  JSONObject();
		jsonObject.put("roleMenu", roleMenu);
		jsonObject.put("MenuAll", menuAll);
		return  jsonObject;
	}
	
	
	//查询某个角色下的所有用户
	@ResponseBody
	@RequestMapping(value="/findUsers")
	public JSONObject findUsers(String id){
		JSONObject json=new JSONObject();
		List<User> userList=roleService.findUsers(id);
		json.put("userList", userList);
		return json;
	}
	
	
	
	@RequestMapping("getAmend")
    public JSONObject getAmend(){
		return null;
     }

	public List<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(List<Role> roles)
	{
		this.roles = roles;
	}
}
