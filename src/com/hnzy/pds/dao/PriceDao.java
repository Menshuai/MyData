package com.hnzy.pds.dao;

import java.util.List;

import com.hnzy.pds.pojo.Price;

public interface PriceDao{
	
	public Price byPrice(int id);
	/**
	 * 查找信息
	 * @return
	 */
	public List<Price> find();
	
	/**
	 * 插入信息
	 * @param Price
	 */
	public  void Insert(Price price);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 更新
	 * @param Price
	 */
	public void update(Price price);
	
}
