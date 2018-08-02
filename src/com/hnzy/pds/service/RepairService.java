package com.hnzy.pds.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hnzy.pds.pojo.Repair;



public interface RepairService {
	
	//查找小区
	public List<Repair> findXqName();
	
	//通过小区获取楼栋----
	public List<Repair> findYhBuildNObyXqm(String xqm);
	
	//通过小区楼栋获取单元----
	public List<Repair> findYhCellNOByBuild(String ldh,String xqm);

	//查找 kff1=0 故障申报 或者=1申请维修
	public List<Repair> findRepair(int kffl);
	//搜索
	public List<Repair> Search(@Param("xqm")String xqm,@Param("ldh")String ldh,
			@Param("dyh")String dyh,@Param("hh")String hh,@Param("fl")String fl,@Param("lxdh")String lxdh);

	public int sum(Repair repair,@Param("kffl")int kffl);
	public int statewjd(@Param("kffl")int kffl);//未接单
	public int stateyjd(@Param("kffl")int kffl);//已接单
	public int stateywc(@Param("kffl")int kffl);//已完成
	
	public int sum1(@Param("xqm")String xqm,@Param("ldh")String ldh,
			@Param("dyh")String dyh,@Param("hh")String hh,@Param("fl")String fl,@Param("lxdh")String lxdh);
	
	public int state00(@Param("xqm")String xqm,@Param("ldh")String ldh,
	
			@Param("dyh")String dyh,@Param("hh")String hh,@Param("fl")String fl,@Param("lxdh")String lxdh);
	
	public int state11(@Param("xqm")String xqm,@Param("ldh")String ldh,
	
			@Param("dyh")String dyh,@Param("hh")String hh,@Param("fl")String fl,@Param("lxdh")String lxdh);
	
	public int state22(@Param("xqm")String xqm,@Param("ldh")String ldh,
	
			@Param("dyh")String dyh,@Param("hh")String hh,@Param("fl")String fl,@Param("lxdh")String lxdh);

		//更新
		public void updateRepair(Repair repair);
		
		//添加
		public void InsertRepair(Repair repair);
		
		//chart 搜索
		public List<Map<String,Object>> chartSearch(@Param("xqm")String xqm,@Param("kffl")int kffl);

		//
		public List<Repair> findState(@Param("kffl")int kffl);
}
