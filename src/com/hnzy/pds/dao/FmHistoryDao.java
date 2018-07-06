package com.hnzy.pds.dao;
 
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.pds.pojo.FmHistory;



 

public interface FmHistoryDao  extends BaseDao<FmHistory>{
 

	//����״̬��ѯ
	public List<FmHistory>fmHistoriesStatus();
 
   //ɢ��ͼ��ѯ
	public List<FmHistory> chartSearchSd(@Param("xqName")String xqName,@Param("buildNo")int buildNo,@Param("cellNo")int cellNo,@Param("sfjf")String sfjf,@Param("status")String status);
   
	//����id��ѯ������Ϣ
	public FmHistory findSd(int id);
}
