package com.hnzy.pds.service;

import java.util.List;

import com.hnzy.pds.pojo.Fp;


public interface FpService {

	/**
	 * 查找信息
	 * @return
	 */
	public List<Fp> find();
	
	/**
	 * 插入信息
	 * @param Fp
	 */
	public  void Insert(Fp fp);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 更新
	 * @param Fp
	 */
	public void update(Fp fp);
	
	/**
	 * 根据用户编号查询风盘编号
	 * @param yhbh
	 * @return
	 */
	public Fp findfpbh(String yhbh);
	
	//查找风盘所有的型号
		public List<Fp> findfp();
}
