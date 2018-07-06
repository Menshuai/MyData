package com.hnzy.hot.base;

import java.io.Serializable;
import java.util.List;

import com.hnzy.pds.pojo.FmHistory;

public interface BaseDao<T extends Serializable> {
	/**
	 * 
	 * @return
	 */
	
	public List<T> find();
	public List<T> findAll();

	 /**
	  * 
	  * @param xqName
	  * @param buildNO
	  * @param cellNO
	  * @return
	  */
	public T findZtree(String xqName,Integer buildNO,Integer cellNO);
	
	/**
	 * @author Administrator 
	 */
	public void Insert( T entity);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int delete(int id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public T findById(int id);

	/**
	 * 
	 * @param entity
	 */
	public void update(T  entity);
	
}
