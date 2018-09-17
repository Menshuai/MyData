package com.hnzy.pds.service.Impl;

import java.util.List;

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
	public Price byPrice(int msid)
	{
		return priceDao.byPrice(msid);
	}
	@Override
	public List<Price> find() {
		return priceDao.find();
	}
	@Override
	public void Insert(Price price) {
		priceDao.Insert(price);		
	}
	@Override
	public void delete(String id) {
		priceDao.delete(Integer.parseInt(id));
	}
	@Override
	public void update(Price price) {
		priceDao.update(price);
	}

}
