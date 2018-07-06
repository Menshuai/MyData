package com.hnzy.pds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
 
import com.hnzy.pds.pojo.Repair;


public interface RepairDao  extends BaseDao<Repair>{

	/**
	 * 
	 * ����״̬������Ϣ
	 * @return
	 *//*
	public List<Repair> findplace();
	//��ȡС������
//	public List<Repair>findXqName();
	
	*//**
	 * ����ά��
	 *//*
 public List<Repair> findRepair(@Param("kffl")int kffl );
	//����С����¥������Ԫ�����Ų��һ���վ
	public Repair findHes(@Param("xqName") String xqName,@Param("buildNo") String buildNo,
			@Param("cellNo") String cellNo, @Param("cs") String cs ,@Param("sh") String sh);
	
	*//**
	 * ���ݴ����һ���վ
	 * @param place
	 * @return
	 *//*
	public List<Repair> findHESName(String place);
	
//	public List<Repair> findPlace(String xqName);
	public List<Repair> findXqName();
	public List<Repair> findBuildNo(@Param("xqName")String xqName);
	public List<Repair> findCellNo(@Param("xqName")String xqName,@Param("BuildNo")String BuildNo);
	
	 
	 
							
	
	
	//��ѯ
	public List<Repair> Search(@Param("xqName")String xqName,@Param("buildNo")String buildNo,
			@Param("cellNo")String cellNo,@Param("sh")String sh,@Param("fl")String fl,@Param("telephone")String telephone);
	
	
	*//**
	 * ͨ��״̬����
	 * @param place
	 * @param hesName
	 * @param state
	 * @param kffl
	 * @return
	 *//*
	public List<Repair> findByState(@Param("place")String place,@Param("hesName")String hesName,
			@Param("state")String state,@Param("kffl")int kffl);
	
	public List<Repair> findState(@Param("kffl")int kffl);
	public int sum(Repair repair,@Param("kffl")int kffl);
	
	
	public int state0(@Param("kffl")int kffl);
	public int state1(@Param("kffl")int kffl);
	public int state2(@Param("kffl")int kffl);
	
	
	public int sum1(@Param("xqName")String xqName,@Param("buildNo")String buildNo,
			@Param("cellNo")String cellNo,@Param("sh")String sh,@Param("fl")String fl,@Param("telephone")String telephone);
	
	public int state00(@Param("xqName")String xqName,@Param("buildNo")String buildNo,
	
			@Param("cellNo")String cellNo,@Param("sh")String sh,@Param("fl")String fl,@Param("telephone")String telephone);
	
	public int state11(@Param("xqName")String xqName,@Param("buildNo")String buildNo,
	
			@Param("cellNo")String cellNo,@Param("sh")String sh,@Param("fl")String fl,@Param("telephone")String telephone);
	
	public int state22(@Param("xqName")String xqName,@Param("buildNo")String buildNo,
	
			@Param("cellNo")String cellNo,@Param("sh")String sh,@Param("fl")String fl,@Param("telephone")String telephone);
	
	
	
	public int statePlace0(@Param("xqName")String xqName,@Param("kffl")int kffl);
	public int statePlace1(@Param("xqName")String xqName,@Param("kffl")int kffl);
	public int statePlace2(@Param("xqName")String xqName,@Param("kffl")int kffl);
	
	public int stateCx(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl);
	public int stateywc(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl);
	public int stateyjd(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl);
	public int statewjd(@Param("place")String place,@Param("hesName")String hesName,@Param("state")String state,@Param("kffl")int kffl);*/
}
