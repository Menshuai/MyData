package com.hnzy.pds.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.pds.pojo.YhInfo;

 

public interface YhInfoService {
	
	
	public List<YhInfo> findYhNameList();
	public List<YhInfo> findYhBuildNObyXqName(String xqName);
	public List<YhInfo> findYhCellNOByBuild(int build,String xqName);
	public List<YhInfo> findYhHouseNOByBuild(int house,int build,String xqName);
	
	
	
	public List<YhInfo> findrepair();
	
	//根据处查找小区
		public List<YhInfo> findXqNamebyPlace(String place,String hESName);
		public List<YhInfo> findYhBuildNObyXqNamerepair(String xqName,String place,String hESName);
		public List<YhInfo> findYhCellNOByBuildrepair(String LH,String xqName,String place,String hESName);
		public List<YhInfo> findYhHouseNOByBuildrepair(String house,String LH,String xqName);

		
		public List<YhInfo> findPlace();
		
		//一对一查询
		public List<YhInfo> findXqInfoFmHistory();
		
		   //搜索
	    public List<YhInfo> searchInfo(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")int houseNo,@Param("sfjf")String sfjf,@Param("valTemp1")String valTemp1,@Param("valTemp2")String valTemp2,@Param("roomTemp1")String roomTemp1,@Param("roomTemp2")String roomTemp2,@Param("famKd")Integer famKd,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2);
		//根据阀门Id查找用户信息
		public YhInfo findFmId(@Param("valAd")String valAd);
		
}
