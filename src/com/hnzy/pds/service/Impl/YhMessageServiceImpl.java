package com.hnzy.pds.service.Impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.hnzy.pds.dao.YhMessageDao;
import com.hnzy.pds.pojo.Data;
import com.hnzy.pds.pojo.YhMessage;
import com.hnzy.pds.service.YhMessageService;
import com.hnzy.pds.util.ExcelUtilZykt;

@Service("yhMessageService")
public class YhMessageServiceImpl implements YhMessageService{
	
	@Autowired
	private YhMessageDao yhMessageDao;
	
	@Override
	public List<YhMessage> find() {
		return yhMessageDao.find();
	}

 	@Override
	public List<YhMessage> findLDH(String xqm) {
		return yhMessageDao.findLDH(xqm);
	}

	@Override
	public List<YhMessage> findDYH(String xqm, String ldh) {
		return yhMessageDao.findDYH(xqm, ldh);
	} 

	@Override
	public List<YhMessage> findZ(String xqm, String ldh, String dyh) {
		return yhMessageDao.findZ(xqm, ldh, dyh);
	}

   	@Override
	public List<YhMessage> findXQ() {
		return yhMessageDao.findXQ();
	} 

 	@Override
	public List<YhMessage> findXQN() {
		return yhMessageDao.findXQN();
	} 

	@Override
	public List<YhMessage> findBOByXQ(YhMessage yhMessage) {
		return yhMessageDao.findBO(yhMessage);
	}

	@Override
	public List<YhMessage> findCOByXQAndBO(YhMessage yhMessage) {
		return yhMessageDao.findCO(yhMessage);
	}

	@Override
	public void Insert(YhMessage yhMesasge) {
		yhMessageDao.Insert(yhMesasge);		
	}

	@Override
	public void delete(String id) {
		yhMessageDao.delete(Integer.parseInt(id));		
	}

	@Override
	public void update(YhMessage yhMesasge) {
		yhMessageDao.update(yhMesasge);
	}

	@Override
	public YhMessage findJzq(String cgbh) {
		return yhMessageDao.findJzq(cgbh);
	}

	@Override
	public YhMessage finldh(String yhbh,String fpdz) {
		return yhMessageDao.finldh(yhbh,fpdz);
	}

	@Override
	public YhMessage findyh(String yhbh) {
		return yhMessageDao.findyh(yhbh);
	}

	@Override
	public List<YhMessage> findYhNameList() {
		return yhMessageDao.findYhNameList();
	}

	@Override
	public List<YhMessage> findYhBuildNObyXqm(String xqm) {
		return yhMessageDao.findYhBuildNObyXqm(xqm);
	}

	@Override
	public List<YhMessage> findYhCellNOByBuild(int ldh, String xqm) {
		return yhMessageDao.findYhCellNOByBuild(ldh, xqm);
	}

	@Override
	public void exportExcel(List<Data> yhInfosList, ServletOutputStream outputStream) throws IOException {
		ExcelUtilZykt.exportExcel(yhInfosList, outputStream);
	}
	@Override
	public List<YhMessage> findXqName() {
		return yhMessageDao.findXqName();
	}

	@Override
	public int findYf()
	{
		//获取月份
		return yhMessageDao.findYf();
	}

	@Override
	public void updateYf(int yf)
	{
		yhMessageDao.updateYf(yf);
	} 	
}
