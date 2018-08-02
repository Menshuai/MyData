package com.hnzy.pds.dao;

import java.util.List;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.pds.pojo.Fp;

public interface FpDao extends BaseDao<Fp>{

	public Fp findfpbh(String yhbh);
	//查找风盘所有的型号
	public List<Fp> findfp();
}
