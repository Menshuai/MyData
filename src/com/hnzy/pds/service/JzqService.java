package com.hnzy.pds.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.pds.pojo.Jzq;

public interface JzqService {

	/**
	 * 查找信息
	 * @return
	 */
	public List<Jzq> find();
	
	/**
	 * 插入信息
	 * @param jzq
	 */
	public  void Insert(Jzq jzq);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 更新
	 * @param jzq
	 */
	public void update(Jzq jzq);
	
	//根据集中器ip  port查找jzqnet
	public Jzq findJzqnet( @Param("jzqip")String jzqip,@Param("jzqport")Integer jzqport);
	
	
	//更新ip port
	public void updateIpPort(String jzqip,Integer jzqport,String jzqnet);
	
}
