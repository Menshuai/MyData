package com.hnzy.pds.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.pds.pojo.Rz;
import com.hnzy.pds.pojo.User;
import com.hnzy.pds.pojo.YhMessage;
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
	
	
	private String msg;
	private  User  userl;
	private List<User>   user;
	//
		@RequestMapping("/toLogin")
		public String tologin(){
			List<User> user=userService.find();
			return"login";
		}
		
		@RequestMapping("/toMen")
		public String toMen(){
		return "toMen";
		}
		
		
		@RequestMapping("/index")
		public String index(){
			return"index";
		}
		
		//用户列表
		@RequestMapping("findYh")
		public String findYh(HttpServletRequest request){
			user=userService.find();
			request.setAttribute("user",user);
			return "user";
		}
		
		//删除一个用户名 密码
		@RequestMapping("/deleteUser")
		@ResponseBody
		public void delete(@RequestParam("id")String id){
			userService.deleteUser(id);
			
		}
		
		@RequestMapping("/login")//�ж� �����Ƿ�Ϊ��
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
