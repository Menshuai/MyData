package com.hnzy.pds.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.pds.pojo.FmHistory;



public interface FmHistoryService {
	

	public List<FmHistory> findFmHistory();
	public List<FmHistory>fmHistoriesStatus();
	
	//ɢ��ͼ��ѯ
	public List<FmHistory> chartSearchSd(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
	
	//����id��ѯ������Ϣ
	public FmHistory findSd(int id);
}
