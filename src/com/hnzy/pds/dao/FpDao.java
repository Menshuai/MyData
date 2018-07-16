package com.hnzy.pds.dao;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.pds.pojo.Fp;

public interface FpDao extends BaseDao<Fp>{

	public Fp findfpbh(String yhbh);
}
