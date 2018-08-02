package com.hnzy.pds.service;

import java.util.List;

import com.hnzy.pds.pojo.Cg;


public interface CgService {

	/**
	 * 查找信息
	 * @return
	 */
	public List<Cg> find();
	
	/**
	 * 插入信息
	 * @param Cg
	 */
	public  void Insert(Cg cg);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 更新
	 * @param Cg
	 */
	public void update(Cg cg);
	//根据层管编号查找楼栋单元
	public Cg findLdDyByCg(String cgbh);
	//根据层管编号查找集中器和端口号
	public Cg findJzqByCg(String cgbh);
}
