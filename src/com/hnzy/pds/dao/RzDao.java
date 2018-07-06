package com.hnzy.pds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.pds.pojo.Rz;
 

public interface RzDao extends BaseDao<Rz>{
	public List<Rz> Search(@Param("cz") String cz);
}
