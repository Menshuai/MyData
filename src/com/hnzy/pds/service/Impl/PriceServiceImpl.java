package com.hnzy.pds.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.PriceDao;
import com.hnzy.pds.pojo.Price;
import com.hnzy.pds.service.PriceService;
@Service
public class PriceServiceImpl implements PriceService
{
	@Autowired
   private PriceDao priceDao;
	@Override
	public Price byPrice(int id)
	{
		return priceDao.byPrice(id);
	}

}
