package com.hnzy.pds.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hnzy.pds.pojo.Repair;

public interface RepairService {
	
	/*public List<Repair> findXqName();
	public List<Repair> findBuildNo(@Param("xqName")String xqName);
	public List<Repair> findCellNo(@Param("xqName")String xqName,@Param("BuildNo")String BuildNo);
	
	*//**
	 * ���Ҵ��ط�
	 * @return
	 *//*
	public List<Repair> findplace();
	
	*//**
	 * ���ݴ����һ���վ����
	 *//*
	public List<Repair> findHESName(String place);
	
	public Repair findHes(String xqName,String buildNo,String cellNo,String cs,String sh);
	
//	public List<Repair> findPlace(String xqName);
	*//**
	 * ���ұ�����Ϣ
	 *//*
	public List<Repair>findRepair(int kffl);
	
	*//**
	 * ��ӱ��޵���Ϣ
	 *//*
	public void InsertRepair(Repair repair);
	
	public List<Repair> insert(@Param("place")String place,@Param("HESName")String HESName,
			@Param("xqName")String xqName,@Param("buildNo")String buildNo,
			@Param("cellNo")String cellNo,	@Param("cs")String cs,
			@Param("sh")String sh,@Param("name")String name,
			@Param("telephone")String telephone,@Param("jSname")String jSname,
			@Param("problem")String problem);
	 
	*//**
	 * ɾ��
	 *//*
	public void deleteRepair(String id);
	
	*//**
	 * ͨ��id������Ϣ
	 *//*
	public Repair findRepairById(int id);
	
	*//**
	 * ������Ϣ
	 *//*
	public void updateRepair(Repair repair);
	
	
	*//**
	 * ����״̬������Ϣ
	 *//*
	public List<Repair> findByState(@Param("place")String place,@Param("hesName")String hesName,
			@Param("state")String state,@Param("kffl")int kffl);

	
	public List<Repair> findState(@Param("kffl")int kffl);
	public int sum(Repair repair,@Param("kffl")int kffl);
	
	public int state0(@Param("kffl")int kffl);
	public int state1(@Param("kffl")int kffl);
	public int state2(@Param("kffl")int kffl);
	
	public List<Map<String,Object>> chartSearch(@Param("xqName")String xqName,@Param("kffl")int kffl);
  
	public int stateCx(@Param("place")String place,@Param("hesName")String hesName,
			@Param("state")String state,@Param("kffl")int kffl);
	
	public int stateywc(@Param("place")String place,@Param("hesName")String hesName,
			@Param("state")String state,@Param("kffl")int kffl);
	
	public int stateyjd(@Param("place")String place,@Param("hesName")String hesName,
			@Param("state")String state,@Param("kffl")int kffl);
	
	public int statewjd(@Param("place")String place,@Param("hesName")String hesName,
			@Param("state")String state,@Param("kffl")int kffl);
	
	
	//��ѯ
	List<Repair> Search(String xqName, String buildNo, String cellNo, String SH,String fl,String telephone);
	
 

	public int sum1(@Param("xqName")String xqName,@Param("buildNo")String buildNo,
			@Param("cellNo")String cellNo,@Param("sh")String sh,@Param("fl")String fl,@Param("telephone")String telephone);
	
	
	public int state00(@Param("xqName")String xqName,@Param("build.No")String buildNo,
			@Param("cellNo")String cellNo,@Param("sh")String sh,@Param("fl")String fl,@Param("telephone")String telephone);
	
	
	public int state11(@Param("xqName")String xqName,@Param("buildNo")String buildNo,
			@Param("cellNo")String cellNo,@Param("sh")String sh,@Param("fl")String fl,@Param("telephone")String telephone);
	
	
	public int state22(@Param("xqName")String xqName,@Param("buildNo")String buildNo,
			@Param("cellNo")String cellNo,@Param("sh")String sh,@Param("fl")String fl,@Param("telephone")String telephone);

*/











}
