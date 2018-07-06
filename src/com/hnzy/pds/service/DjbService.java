package com.hnzy.pds.service;

import java.util.List;

import com.hnzy.pds.pojo.Djb;

public interface DjbService {

	/**
	 * 查找信息
	 * @return
	 */
	public List<Djb> find();
	
	/**
	 * 插入信息
	 * @param Djb
	 */
	public  void Insert(Djb djb);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 更新
	 * @param Djb
	 */
	public void update(Djb djb);
}
