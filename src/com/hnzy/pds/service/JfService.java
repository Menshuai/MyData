package com.hnzy.pds.service;

import java.util.List;

import com.hnzy.pds.pojo.Jf;

public interface JfService {

	/**
	 * 查找信息
	 * @return
	 */
	public List<Jf> find();
	
	/**
	 * 插入信息
	 * @param Jf
	 */
	public  void Insert(Jf jf);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 更新
	 * @param Jf
	 */
	public void update(Jf jf);
}
