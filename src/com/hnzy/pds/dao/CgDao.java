package com.hnzy.pds.dao;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.pds.pojo.Cg;



public interface CgDao extends BaseDao<Cg>{

	//根据层管编号查找楼栋单元
	public Cg findLdDyByCg(String cgbh);
	//根据层管编号查找集中器和端口号
	public Cg findJzqByCg(String cgbh);
}
