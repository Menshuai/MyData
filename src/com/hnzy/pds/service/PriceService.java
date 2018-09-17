package com.hnzy.pds.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hnzy.pds.pojo.Price;

@Service
public interface PriceService
{
  /**
	 * 查找信息
	 * @return
	 */
	public List<Price> find();
	
	/**
	 * 插入信息
	 * @param Djb
	 */
	public  void Insert(Price price);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 更新
	 * @param Djb
	 */
	public void update(Price price);
  
  
  
  public Price	byPrice(int msid);
}
