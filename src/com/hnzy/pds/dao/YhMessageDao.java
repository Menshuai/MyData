package com.hnzy.pds.dao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.pojo.YhMessage;
 

public interface YhMessageDao  extends BaseDao<YhMessage>{

	public List<YhMessage> find();
 
	public List<YhMessage> findXQ();
	
	public List<YhMessage> findBO(YhMessage village);
	
	public List<YhMessage> findCO(YhMessage village);
	
	public List<YhMessage> findZ(@Param("xqm")String xqm,@Param("ldh")String ldh,@Param("dyh")String dyh);

	//查找小区名
	public List<YhMessage> findXQN();
	//查找楼栋号
	public List<YhMessage> findLDH(@Param("xqm")String xqm);
	//查找单元号	
	public List<YhMessage> findDYH(@Param("xqm")String xqm,@Param("ldh")String ldh); 
	/*	
	*//**
	 * 插入信息
	 * @paramYhMessage
	 *//*
	public  void  Insert(YhMessage  yhMesasge);
	
	*//**
	 * 删除
	 * @param id
	 *//*
	public void delete(String id);
	
	*//**
	 * 更新
	 * @paramYhMessage
	 *//*
	public void update(YhMessage yhMesasge);*/
	
	
	//查找集中器ip和端口号
	public YhMessage  findJzq(String cgbh);
	
	//根据用户编码 查找楼栋号
	public   YhMessage  finldh(@Param("yhbh")String yhbh,@Param("fpdz")String fpdz);
	
	//根据用户编码 查找单元号
	public   YhMessage  findyh(String  yhbh);
	 
	
	//查找小区名字
	public List<YhMessage> findXqName();
		
	//查找用户
	public List<YhMessage> findYhNameList();
	
	
	
	//通过小区获取楼栋----
	public List<YhMessage> findYhBuildNObyXqm(String xqm);
	//通过小区楼栋获取单元----
	public List<YhMessage> findYhCellNOByBuild(int ldh,String xqm);
	
	//获取户号
	public List<YhMessage> findYhHouseNOByBuildrepair(String hh,String LH,String xqm);
	//导出------
	public void exportExcel(List<Data>yhInfosList,ServletOutputStream outputStream) throws IOException;
	
	//搜索------
//	public List<YhMessage> searchInfo(@Param("xqm")String xqm,@Param("ldh")int ldh,@Param("dyh")int dyh,
//			@Param("hh")int hh,@Param("time1") String time1,@Param("time2") String time2);
	
	//历史数据
//	public List<YhMessage> searchHistory(@Param("xqm")String xqm,@Param("ldh")int ldh,@Param("dyh")int dyh,
//			@Param("hh")int hh,@Param("time1") String time1,@Param("time2") String time2);
	//查找月份
	public int  findYf();
	//更新当前月份
	public void updateYf(int yf);
}
