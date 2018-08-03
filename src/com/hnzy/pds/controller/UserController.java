package com.hnzy.pds.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.pds.pojo.Menu;
import com.hnzy.pds.pojo.Role;
import com.hnzy.pds.pojo.Rz;
import com.hnzy.pds.pojo.User;
import com.hnzy.pds.service.MenuService;
import com.hnzy.pds.service.RoleService;
import com.hnzy.pds.service.RzService;
import com.hnzy.pds.service.UserService;
import com.hnzy.pds.util.MD5Util;
import com.hnzy.pds.util.PropertyUtil;
import com.hnzy.pds.util.StringUtil;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RzService rzService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	private final static Logger logger = Logger.getLogger(UserController.class);
	
	private String msg;
	private  User  userl;
	private List<User>   user;
	
	//注册
		@RequestMapping("/toLogin")
		public String tologin(){
			List<User> user=userService.find();
			return"login";
		}
		
		@RequestMapping("/toMen")
		public String toMen(){
		return "toMen";
		}
		
		//首页
		@RequestMapping("/index")
		public String index(){
			return"index";
		}
		
		//用户列表
		@RequestMapping("/findYh")
		public String findYh(HttpServletRequest request){
			user=userService.find();
			request.setAttribute("user",user);
			return "user";
		}
		
		 
				
		//获取用户角色，与角色列表
	 	@ResponseBody
		@RequestMapping("getAmend")
		public String getAmend(String id){
		//查询用户角色
		List<Role> userRole=roleService.findRoleId(id);
		//查询所有角色
		List<Role> roleAll=roleService.findAllRole();
		//定义一个map集合，并添加数据
		HashMap<String,String> map=new HashMap<String,String>();
		if(userRole==null){
			for(Role role:roleAll){
				map.put(role.getId().toString(),"0");
			}
		}else{
			for(Role role:roleAll){
				for(Role uRole: userRole){
					if(uRole.getId().equals(role.getId())){
						map.put(role.getId().toString(),uRole.getId().toString());
						break;
					}else{
						map.put(role.getId().toString(),"0");
					}
				}
							
			}
		}
		//返回JSON数据
		JSONObject json=new JSONObject();
		json.put("map", map);
		json.put("roles",roleAll);
		return json.toJSONString();
	}
	 	
	 	//修改角色并返回到用户页面
	 	@RequestMapping("amend")
	 	public String editrole(HttpServletRequest request,@RequestParam(value="id", required = false )String id){
	 		String[] roleId= request.getParameterValues("userRoleList");
	 		
	 		roleService.editURole(roleId, id);
	 		return "redirect:findYh.action";
	 	}
		
		//删除一个用户名 密码
		@RequestMapping("/deleteUser")
		@ResponseBody
		public void deleteUser(@RequestParam("id")String id){
			logger.info("接收到的userID为:"+id);
			userService.delete(id);
			
		}
		 
		
		@RequestMapping("/login")
		public String login(HttpSession session,String username,String password,HttpServletRequest request){
			if (StringUtil.isNoEmpty(username) && StringUtil.isNoEmpty(password)) {
				password=MD5Util.string2MD5(password);
				User info = userService.findUserByNameAndMD5(username, password);			
				if(info!=null){
					request.getSession().setAttribute("admins", info);
					request.getSession().setAttribute("UserName", info.getUserName());
					request.getSession().setAttribute("PassWord", info.getPassWord());
					request.getSession().setAttribute("ID", info.getID());
					Rz rz=new Rz();
					rz.setCzr((String)session.getAttribute("UserName"));//��ȡ������
					rz.setCz("登录成功");            
					rz.setCzsj(new Date());        
					rzService.insert(rz);
					//根据用户查询用户的菜单及url
					List<Menu> menuList=menuService.findMenuByUserName(username);
					request.setAttribute("menuList", menuList);
					return "index";
					
				}else{
					 msg = PropertyUtil.Informationerror;	
					//	request.setAttribute("msg", msg); 
					 return "login";	
				}
			}else {
					 msg = PropertyUtil.Informationempty;
				//		request.setAttribute("msg", msg); 
						
					 return "login";
				}

		}
			
		
		@RequestMapping("yhzc")
		public String yhzc(){
			return "yhzc";
		} 
		
		    //注册
		        @RequestMapping("regist")  
		        public String regist(User user,String username,String password){  
		        	userl= userService.findUserByName(username);
		        	if (userl==null) {
		        		user.setPassWord(MD5Util.string2MD5(password));
		        		user.setUserName(username);
		        		userService.regist(user);
		        		
						 return "suceed";  //-->parent.location.href = "me/index.action";
					} else {
						msg = PropertyUtil.InformationName;
					}
		        	return "yhzc";
		        }  
		        
		        
		      //修改密码
		    	@RequestMapping("xgmm")
		    	public String xgmm(){
		    		return "xgmm";
		    	}
		    	
		    	//更新
		    	@RequestMapping("updapwd")
		    	public String updapwd(User u,int id,String password1 ,String password2,String username) {
		    				userl = this.userService.findUserById(id);
		    				String password11=MD5Util.string2MD5(password1);
		    				String password12=MD5Util.string2MD5(password2);
		    				if (userl.getPassWord().equals(password11)==false) {
								if (password11.equals(password12)) {  //password1������   password2ȷ������
									u.setPassWord(password11);//
									u.setID(id);
									u.setUserName(username);
									this.userService.updateUse(u);
									// 
									return "pwdsuccess";  //-->parent.location.href = "user/toLogin.action";//
								} 
		    				}
		    		return "xgmm";
		    	}
		    	
				 

}
