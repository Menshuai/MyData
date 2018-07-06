package com.hnzy.pds.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.pds.dao.YhInfoDao;
import com.hnzy.pds.pojo.YhInfo;
import com.hnzy.pds.service.YhInfoService;



@Service
public class YhInfoServiceImpl implements YhInfoService{
	
	@Autowired 
	private YhInfoDao yhInfoDao;
	
	
	@Override
	public List<YhInfo> findrepair() {
		 
		return yhInfoDao.findrepair();
	}


	@Override
	public List<YhInfo> findXqNamebyPlace(String place, String hESName) {
		return yhInfoDao.findXqNamebyPlace(place, hESName);
	}


	@Override
	public List<YhInfo> findYhBuildNObyXqNamerepair(String xqName, String place, String hESName) {
		return yhInfoDao.findYhBuildNObyXqNamerepair(xqName, place, hESName);
	}


	@Override
	public List<YhInfo> findYhCellNOByBuildrepair(String LH, String xqName, String place, String hESName) {
		return yhInfoDao.findYhCellNOByBuildrepair(LH, xqName, place, hESName);
	}


	@Override
	public List<YhInfo> findYhHouseNOByBuildrepair(String house, String LH, String xqName) {
		return yhInfoDao.findYhHouseNOByBuildrepair(house, LH, xqName);
	}


	@Override
	public List<YhInfo> findYhNameList() {
		return yhInfoDao.find();
	}


	@Override
	public List<YhInfo> findYhBuildNObyXqName(String xqName) {
		return yhInfoDao.findYhBuildNObyXqName(xqName);
	}


	@Override
	public List<YhInfo> findYhCellNOByBuild(int build, String xqName) {
		return yhInfoDao.findYhCellNOByBuild(build, xqName);
	}


	@Override
	public List<YhInfo> findYhHouseNOByBuild(int house, int build, String xqName) {
		return yhInfoDao.findYhHouseNOByBuild(house, build, xqName);
	}


	@Override
	public List<YhInfo> findPlace() {
		return yhInfoDao.findPlace();
	}


	@Override
	public List<YhInfo> findXqInfoFmHistory() {
		return yhInfoDao.findXqInfoFmHistory();
	}


	@Override
	public List<YhInfo> searchInfo(String xqName, int buildNo, int cellNo, int houseNo, String sfjf, String valTemp1,
			String valTemp2, String roomTemp1, String roomTemp2, Integer famKd, String recordTime1,
			String recordTime2) {
		return yhInfoDao.searchInfo(xqName, buildNo, cellNo, houseNo, sfjf, valTemp1, valTemp2, roomTemp1, roomTemp2, famKd, recordTime1, recordTime2);
	}


	@Override
	public YhInfo findFmId(String valAd) {
		return yhInfoDao.findFmId(valAd);
	}

}














