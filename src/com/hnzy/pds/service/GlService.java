package com.hnzy.pds.service;

import java.util.List;

import com.hnzy.pds.pojo.Gl;

public interface GlService {

	/**
	 * 查找信息
	 * @return
	 */
	public List<Gl> find();
	
	/**
	 * 插入信息
	 * @param Gl
	 */
	public  void Insert(Gl gl);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 更新
	 * @param Gl
	 */
	public void update(Gl gl);
}
