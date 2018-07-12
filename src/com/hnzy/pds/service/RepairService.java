package com.hnzy.pds.service;

import java.util.List;

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
	public int state0(@Param("kffl")int kffl);//未接单
	public int state1(@Param("kffl")int kffl);//已接单
	public int state2(@Param("kffl")int kffl);//已完成
	
	public int sum1(@Param("xqm")String xqm,@Param("ldh")String ldh,
			@Param("dyh")String dyh,@Param("hh")String hh,@Param("fl")String fl,@Param("lxdh")String lxdh);
	
	public int state00(@Param("xqm")String xqm,@Param("ldh")String ldh,
	
			@Param("dyh")String dyh,@Param("hh")String hh,@Param("fl")String fl,@Param("lxdh")String lxdh);
	
	public int state11(@Param("xqm")String xqm,@Param("ldh")String ldh,
	
			@Param("dyh")String dyh,@Param("hh")String hh,@Param("fl")String fl,@Param("lxdh")String lxdh);
	
	public int state22(@Param("xqm")String xqm,@Param("ldh")String ldh,
	
			@Param("dyh")String dyh,@Param("hh")String hh,@Param("fl")String fl,@Param("lxdh")String lxdh);
}
