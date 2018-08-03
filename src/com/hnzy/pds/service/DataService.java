package com.hnzy.pds.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.pds.pojo.Data;

public interface DataService {

	public List<Data> find();
	
	public Data  findf(String fpbh);
/*
	  void updateS(String ms,String dw,String gdtime,String zdtime,
			String ddtime,String dgdtime,String dzdtime,String dddtime,String jf,
			String sdwd,String snwd,String kg,String bj,String jj,String time);*/

	public void updateS(String ms, String dw, double gdg, double zdS, double gdd, double dgdg, double dzdS, double dgdd,
			String jf, int sdw, int sw, String kg, int bj, String jj, String time);
	
	//根据用户编号和风盘地址更新实时数据
	public void updateYhbhF(Data data);
 
	//插入历史数据
	public void InsertYh(Data data);
		
	//搜索------
	public List<Data> searchInfo(@Param("xqm")String xqm,@Param("ldh")int ldh,@Param("dyh")int dyh,
						@Param("hh")int hh,@Param("time1") String time1,@Param("time2") String time2);
				
	//历史数据
	public List<Data> searchHistory(@Param("xqm")String xqm,@Param("ldh")int ldh,@Param("dyh")int dyh,
						@Param("hh")int hh,@Param("time1") String time1,@Param("time2") String time2);
				public Data findData(@Param("yhbh")String yhbh,@Param("fpdz")Integer fpdz);
				//获取计算用户当量，金额
				public Data findYh(@Param("yhbh")String yhbh,@Param("fpdz")Integer fpdz);
				//更新用户费用信息
				public void updateJf(Data datajf);
				//插入实时表
				public void InsertYhSSb(Data data);
//	public Data findData(@Param("yhbh")String yhbh,@Param("fpdz")String fpdz);
	
	//异常查询 搜索
	public List<Data> Search(@Param("bj") String bj);
	
}
