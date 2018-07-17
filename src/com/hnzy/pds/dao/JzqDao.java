package com.hnzy.pds.dao;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.pds.pojo.Jzq;

public interface JzqDao extends BaseDao<Jzq>{

	//根据集中器ip查找jzqnet
	public Jzq findJzqnet( @Param("jzqip")String jzqip,@Param("jzqport")Integer jzqport);
	
	
	//更新ip port
	public void updateIpPort(@Param("jzqip") String jzqip,@Param("jzqport") Integer jzqport, @Param("jzqnet") String jzqnet);
}
