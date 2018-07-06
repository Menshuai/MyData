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
	
}
