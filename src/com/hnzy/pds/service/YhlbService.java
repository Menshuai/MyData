package com.hnzy.pds.service;

import java.util.List;

import com.hnzy.pds.pojo.Yhlb;

public interface YhlbService  {

	/**
	 * 查找信息
	 * @return
	 */
	public List<Yhlb> find();
	
	/**
	 * 插入信息
	 * @param Yhlb
	 */
	public  void Insert(Yhlb yhlb);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 更新
	 * @param Yhlb
	 */
	public void update(Yhlb yhlb);
}
