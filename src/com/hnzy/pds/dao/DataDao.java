package com.hnzy.pds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.pojo.YhMessage;

public interface DataDao {

	public List<Data> find();
	
	public Data findf(String fpbh);
	
	//根据用户编号更新
	public void updateS(String ms, String dw, double gdg, double zdS, double gdd, double dgdg, double dzdS, double dgdd,
			String jf, int sdw, int sw, String kg, int bj, String jj, String time);
	public Data findData(@Param("yhbh")String yhbh,@Param("fpdz")Integer fpdz);
	//根据用户编号和风盘地址更新实时数据
	public void updateYhbhF(Data data);
	//插入历史数据
	public void InsertYh(Data data);
	//插入实时表
	public void InsertYhSSb(Data data);
	//数据报表搜索------
	public List<Data> searchInfo(@Param("xqm")String xqm,@Param("ldh")int ldh,@Param("dyh")int dyh,
				@Param("hh")int hh,@Param("time1") String time1,@Param("time2") String time2);
	//历史数据
	public List<Data> searchHistory(@Param("xqm")String xqm,@Param("ldh")int ldh,@Param("dyh")int dyh,
				@Param("hh")int hh,@Param("time1") String time1,@Param("time2") String time2);
		//获取计算用户当量，金额
		public Data findYh(@Param("yhbh")String yhbh,@Param("fpdz")Integer fpdz);
		
		//更新用户费用信息
		public void updateJf(Data data);
		
	
	
	
	//异常查询 搜索
	public List<Data> Search(@Param("bj") String bj);
	
}
