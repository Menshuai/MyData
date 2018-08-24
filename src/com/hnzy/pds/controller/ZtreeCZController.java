package com.hnzy.pds.controller;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.pojo.VillageTreeNodes;
 
import com.hnzy.pds.service.YhMessageService;
 
 
import com.hnzy.pds.util.StringUtil;


@Controller
@RequestMapping("/Ztreecz")
public class ZtreeCZController {
	@Autowired
	private YhMessageService  yhMessageService;

	private List<YhMessage> list;

	 

	// 树形图
	@RequestMapping("ztreecz")
	public String getVillageTreeMenu(HttpServletRequest request) {
		List<VillageTreeNodes> nodes = new ArrayList<VillageTreeNodes>();
		List<YhMessage> ldList = this.yhMessageService.findXQ();
		
		for (int i = 0; i < ldList.size(); i++) {
			String xqname = ldList.get(i).getXqm();
			VillageTreeNodes nodesLD = new VillageTreeNodes();
			nodesLD.setNodeId(i + 40);
			nodesLD.setParentId(0);
			nodesLD.setNodeName(xqname+"小区");
			nodes.add(nodesLD);
			YhMessage villageLD = new YhMessage();
			villageLD.setXqm(xqname);;
			List<YhMessage> lcList = this.yhMessageService.findBOByXQ(villageLD);
			for (int j = 0; j < lcList.size(); j++) {
				String ldh = lcList.get(j).getLdh();
				String ldhNo=null;
				ldhNo=String.valueOf(ldh);
				VillageTreeNodes nodesLC = new VillageTreeNodes();
				if (i > 0) {
					if (j > 0) {
						nodesLC.setNodeId((i + 40) * 40 + j);
					} else {
						nodesLC.setNodeId((i + 40) * 40);
					}
				} else if (j > 0) {
					nodesLC.setNodeId((i + 40) * 40 + j);
				} else {
					nodesLC.setNodeId((i + 40) * 40);
				}
				nodesLC.setParentId(i + 40);
				nodesLC.setNodeName(ldhNo+"楼");
				nodes.add(nodesLC);
				YhMessage villageLC = new YhMessage();
				villageLC.setXqm(xqname);
				villageLC.setLdh(ldh);
				List<YhMessage> dyhList = this.yhMessageService.findCOByXQAndBO(villageLC);
				for (int k = 0; k < dyhList.size(); k++) {
					String dyhNO = dyhList.get(k).getDyh();
					String ZHM = dyhList.get(k).getYhxm();
					VillageTreeNodes nodesdyh = new VillageTreeNodes();
					if (i > 0) {
						if (j > 0) {
							if (k > 0) {
								nodesdyh.setNodeId(((i + 40) * 40 + j) * 40 + k);
							} else {
								nodesdyh.setNodeId(((i + 40) * 40 + j) * 40);
							}
							nodesdyh.setParentId((i + 40) * 40 + j);
						} else if (k > 0) {
							nodesdyh.setNodeId((i + 40) * 400 + k);
							nodesdyh.setParentId((i + 40) * 40);
						} else {
							nodesdyh.setNodeId((i + 40) * 400);
							nodesdyh.setParentId((i + 40) * 40);
						}
					} else if (j > 0) {
						if (k > 0) {
							nodesdyh.setNodeId(((i + 40) * 40 + j) * 40 + k);
						} else {
							nodesdyh.setNodeId(((i + 40) * 40 + j) * 40);
						}
						nodesdyh.setParentId((i + 40) * 40 + j);
					} else if (k > 0) {
						nodesdyh.setNodeId((i + 40) * 400 + k);
						nodesdyh.setParentId((i + 40) * 40);
					} else {
						nodesdyh.setNodeId((i + 40) * 400);
						nodesdyh.setParentId((i + 40) * 40);
					}
					String dyh=null;
					 dyh=String.valueOf(dyhNO+" "+ZHM);
					 nodesdyh.setNodeName(dyh);
					nodes.add(nodesdyh);
				}
			}
		}
		StringBuffer buf=new StringBuffer();
		for (int i = 0; i < nodes.size(); i++) {
			VillageTreeNodes villageNodes = nodes.get(i);
			buf.append("{id:" + villageNodes.getNodeId() + ",pId:"
					+ villageNodes.getParentId() + " ,name: \""+ villageNodes.getNodeName()  + "\"},");
		}
		buf.deleteCharAt(buf.length() - 1);
		request.setAttribute("buf", buf);
		return "ztreecz";
	}

	//树形图主页数据查询
	@RequestMapping("findcz")
	public ModelAndView findJf(HttpServletRequest request, @Param("xqname") String xqname, @Param("ldh") String ldh,
			@Param("dyh") String dyh) throws UnsupportedEncodingException {
		 ModelAndView mav = new ModelAndView();
		if(StringUtil.isEmpty(xqname)){
			 xqname = new String(xqname.getBytes("ISO-8859-1"), "utf-8") + "";
				if (xqname.substring(xqname.length()-1).equals("小区")) {
					xqname=xqname.substring(0, xqname.length()-2);
				}
			}
		
		

	if(StringUtil.isEmpty(ldh)){
		ldh = new String(ldh.getBytes("ISO-8859-1"), "utf-8") + "";
		if (ldh.substring(ldh.length()-1).equals("楼栋")) {
			ldh=ldh.substring(0, ldh.length()-1);
		}
	}
	
	
	
	if(StringUtil.isEmpty(dyh)){
		dyh = new String(dyh.getBytes("ISO-8859-1"), "utf-8");	
		if (dyh.indexOf(" ") > 0 ) {
			//以空格分割字符串
			  String[] a = dyh.split("\\ ");
			    for (int i = 0; i<a.length; i++){
			    	  //将第一个空格前的字符串取出
			    	dyh=a[0];
			    }
		}
	}
	return mav;
	

	
		
//		if (StringUtil.isEmpty(xqname)&&StringUtil.isEmpty(ldh)&&StringUtil.isEmpty(dyh)) {
//			list = YhMessageService.findJfLS(xqname, ldh, dyh);
//			request.setAttribute("list", list);
//			mav.addObject("list", list);
//		}else {
//			list = YhMessageService.findJFS(xqname, ldh, dyh);
//			request.setAttribute("list", list);
//		}
//		
//		if (xqname!=null&&ldh==null&&dyh==null) {
//			mav.addObject("xqname", xqname);
//		}
//		
//		if (xqname!=null&&ldh!=null&&dyh==null) {
//			mav.addObject("xqname", xqname);
//			mav.addObject("ldh", ldh);
//		}
//		if (xqname!=null&&ldh!=null&&dyh!=null) {
//			mav.addObject("xqname", xqname);
//			mav.addObject("ldh", ldh);
//			mav.addObject("dyh", dyh);
//	}
//		mav.setViewName("sfjfList");
//		return mav;
	}

	
	
	

	public List<YhMessage> getList() {
		return list;
	}

	public void setList(List<YhMessage> list) {
		this.list = list;
	}

}
