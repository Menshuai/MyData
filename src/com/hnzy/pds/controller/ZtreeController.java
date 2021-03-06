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
import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.pojo.VillageTreeNodes;
import com.hnzy.pds.service.DataService;
import com.hnzy.pds.service.YhMessageService;
 
import com.hnzy.pds.util.StringUtil;


@Controller
@RequestMapping("/zz")
public class ZtreeController {
	private static final Integer ldh = null;
	@Autowired
	private YhMessageService yhMessageService;
	private List<YhMessage> list;
	@Autowired
	private DataService dataService;
	public List<Data> YhList;
	@RequestMapping("ztree")
	public String getVillageTreeMenu(HttpServletRequest request) {
		List<VillageTreeNodes> nodes = new ArrayList<VillageTreeNodes>();
		List<YhMessage> ldList = this.yhMessageService.findXQ();
		//小区 
		for (int i = 0; i < ldList.size(); i++) {
			String xqm = ldList.get(i).getXqm();
			VillageTreeNodes nodesLD = new VillageTreeNodes();
			nodesLD.setNodeId(i + 40);
			nodesLD.setParentId(0);
			nodesLD.setNodeName(xqm+"");
			nodes.add(nodesLD);
			YhMessage villageLD = new YhMessage();
			villageLD.setXqm(xqm);
			List<YhMessage> lcList = this.yhMessageService.findBOByXQ(villageLD);
			//楼栋
			for (int j = 0; j < lcList.size(); j++) {
				String ldhNo = lcList.get(j).getLdh();
				String ldhNoNo=null;
				ldhNoNo=String.valueOf(ldhNo);
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
				nodesLC.setNodeName(ldhNoNo+"栋楼");
				
				nodes.add(nodesLC);
				YhMessage villageLC = new YhMessage();
				villageLC.setXqm(xqm);
				villageLC.setLdh(ldhNo);
				List<YhMessage> dyhNoList = this.yhMessageService.findCOByXQAndBO(villageLC);
				//单元
				for (int k = 0; k < dyhNoList.size(); k++) {
					String dyhNo = dyhNoList.get(k).getDyh();
					String ZHM = dyhNoList.get(k).getYhxm();
					VillageTreeNodes nodesdyhNo = new VillageTreeNodes();
					if (i > 0) {
						if (j > 0) {
							if (k > 0) {
								nodesdyhNo.setNodeId(((i + 40) * 40 + j) * 40 + k);
							} else {
								nodesdyhNo.setNodeId(((i + 40) * 40 + j) * 40);
							}
							nodesdyhNo.setParentId((i + 40) * 40 + j);
						} else if (k > 0) {
							nodesdyhNo.setNodeId((i + 40) * 400 + k);
							nodesdyhNo.setParentId((i + 40) * 40);
						} else {
							nodesdyhNo.setNodeId((i + 40) * 400);
							nodesdyhNo.setParentId((i + 40) * 40);
						}
					} else if (j > 0) {
						if (k > 0) {
							nodesdyhNo.setNodeId(((i + 40) * 40 + j) * 40 + k);
						} else {
							nodesdyhNo.setNodeId(((i + 40) * 40 + j) * 40);
						}
						nodesdyhNo.setParentId((i + 40) * 40 + j);
					} else if (k > 0) {
						nodesdyhNo.setNodeId((i + 40) * 400 + k);
						nodesdyhNo.setParentId((i + 40) * 40);
					} else {
						nodesdyhNo.setNodeId((i + 40) * 400);
						nodesdyhNo.setParentId((i + 40) * 40);
					}
					String dyhnO=null;
					 dyhnO=String.valueOf(dyhNo);
					 nodesdyhNo.setNodeName(dyhnO);
					nodes.add(nodesdyhNo);
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
		return "ztree";
		}
	/**
	 * 树形图主页数据查询
	 * @throws UnsupportedEncodingException 
	 * */
	@RequestMapping("findz")
	public ModelAndView findz(HttpServletRequest request, @Param("xqm") String xqm, @Param("ldh") String ldh,
			@Param("dyh") String dyh) throws UnsupportedEncodingException  {
		ModelAndView mav = new ModelAndView();
		if(xqm!=null){
			xqm= new String(xqm.getBytes("ISO-8859-1"), "utf-8") + "";
		}
		 Integer ldhS =0;
		 Integer dyhS =0;
		 if(ldh!=null){
			 ldh = new String(ldh.getBytes("ISO-8859-1"), "utf-8") + "";
			 ldhS=Integer.valueOf(ldh.substring(0, ldh.length()-2));
		 }

		 if(dyh!=null){
			 if(StringUtil.isEmpty(dyh)){
				 dyh = new String(dyh.getBytes("ISO-8859-1"), "utf-8");	
				 if (dyh.indexOf(" ") > 0 ) {
					 //以空格分割字符串
					 String[] a = dyh.split("\\ ");
					 for (int i = 0; i<a.length; i++){
						 //将第一个空格前的字符串取出
						 dyhS=Integer.valueOf(a[0]);
					 }
				 }
			 }
		 }
		 YhList= dataService.searchInfo(xqm, ldhS, dyhS, 0, "", "");
//		 list = yhMessageService.findZ(xqm, ldh, dyh);
		 mav.setViewName("data");
		 mav.addObject("YhList", YhList);
		 return mav;
	}
	
	
	/**
	 * 显示所有用户信息
	 * */
	@RequestMapping("data")
	public ModelAndView find() { //视图层ModelAndView
		ModelAndView mav = new ModelAndView();
		list = yhMessageService.find();
		mav.setViewName("data");//返回的文件名
		mav.addObject("list", list);
		return mav;
	}
}
