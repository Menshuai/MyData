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
<<<<<<< HEAD
=======
		//插入历史数据
		public void InsertYh(Data data);
>>>>>>> 990cc4c6d7a497bab62f519edc682a272acf0df6
}
