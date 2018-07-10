package com.hnzy.pds.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.ibatis.annotations.Param;

import com.hnzy.pds.pojo.Jzq;
import com.hnzy.pds.pojo.Repair;
import com.hnzy.pds.pojo.YhMessage;

public interface YhMessageService {
	

	public List<YhMessage> find();
	
	public List<YhMessage> findXQ();
	
	public List<YhMessage> findBOByXQ(YhMessage yhMessage);
		
	public List<YhMessage> findCOByXQAndBO(YhMessage yhMessage);
	 
	
	 //查找小区名
	public List<YhMessage> findXQN();
	
	//通过小区查找楼栋
	public List<YhMessage> findLDH(@Param("xqm")String xqm);
		
	//通过小区 楼栋查找单元
	public List<YhMessage> findDYH(@Param("xqm")String xqm,@Param("ldh")String ldh); 
			
	 
	public List<YhMessage> findZ(@Param("xqm")String xqm,@Param("ldh")String ldh,@Param("dyh")String dyh);
	
	
	
	/**
	 * 插入信息
	 * @paramYhMessage
	 */
	public  void Insert(YhMessage  yhMesasge);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 更新
	 * @paramYhMessage
	 */
	public void update(YhMessage yhMesasge);
	
	
	/**
	 * 查找集中器ip port
	 */
	public YhMessage  findJzq(String cgbh);
	
	//根据用户编码 查找楼栋号
	public  YhMessage finldh(String  yhbh);
		
	//根据用户编码 查找单元号
	public  YhMessage findyh(String  yhbh);
	
	//查找小区名字
	public List<YhMessage> findXqName();
	
	//查找用户
	public List<YhMessage> findYhNameList();
	//通过小区获取楼栋
	public List<YhMessage> findYhBuildNObyXqm(String xqm);
	//通过小区楼栋获取单元
	public List<YhMessage> findYhCellNOByBuild(int ldh,String xqm);
		
	 //导出------
	public void exportExcel(List<YhMessage>yhInfosList,ServletOutputStream outputStream) throws IOException;
		
	//搜索------
	public List<YhMessage> searchInfo(@Param("xqm")String xqm,@Param("ldh")int ldh,@Param("dyh")int dyh,
			@Param("hh")int hh,@Param("time1") String time1,@Param("time2") String time2);
		
	//历史数据
		public List<YhMessage> searchHistory(@Param("xqm")String xqm,@Param("ldh")int ldh,@Param("dyh")int dyh,
				@Param("hh")int hh,@Param("time1") String time1,@Param("time2") String time2);
}
