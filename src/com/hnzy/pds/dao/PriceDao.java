package com.hnzy.pds.dao;

import com.hnzy.hot.base.BaseDao;
import com.hnzy.pds.pojo.Price;

public interface PriceDao extends BaseDao<Price>
{
	public Price byPrice(int msid);
}
