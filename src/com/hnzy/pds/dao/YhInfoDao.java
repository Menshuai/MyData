package com.hnzy.pds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.pds.pojo.YhInfo;

 

public interface YhInfoDao  extends BaseDao<YhInfo>{

	
	public List<YhInfo> findYhBuildNObyXqName(String xqName);
	public List<YhInfo> findYhCellNOByBuild(int build,String xqName);
	public List<YhInfo> findYhHouseNOByBuild(int house,int build,String xqName);
	
	
	public List<YhInfo> findrepair();
	
	public List<YhInfo> findXqNamebyPlace(@Param("place") String place,@Param("hESName") String hESName);
	public List<YhInfo> findYhBuildNObyXqNamerepair(@Param("xqName") String xqName,@Param("place") String place,@Param("hESName") String hESName);
	public List<YhInfo> findYhCellNOByBuildrepair(@Param("LH") String LH,@Param("xqName")  String xqName,@Param("place") String place,@Param("hESName") String hESName);
	public List<YhInfo> findYhHouseNOByBuildrepair(@Param("house") String house,@Param("LH") String LH,@Param("xqName")  String xqName);
	
	
	public List<YhInfo> findPlace();
	
	
	//һ��һ�Ĳ�ѯ
    public List<YhInfo> findXqInfoFmHistory();
    
	   //����
    public List<YhInfo> searchInfo(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("houseNo")int houseNo,@Param("sfjf")String sfjf,@Param("valTemp1")String valTemp1,@Param("valTemp2")String valTemp2,@Param("roomTemp1")String roomTemp1,@Param("roomTemp2")String roomTemp2,@Param("famKd")Integer famKd,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2);
	//���ݷ���Id�����û���Ϣ
	public YhInfo findFmId(@Param("valAd")String valAd);
}
