package com.hnzy.pds.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.hnzy.pds.pojo.Data;

public class ExcelUtilBj {
	public static void exportExcel(List<Data> yhInfosList,ServletOutputStream outputStream) throws IOException{
		//定义显示日期的公共格式
    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyy-MM-dd:HH:mm:ss");
    String newdate=simpleDateFormat.format(new Date());
		//创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建合并单元格对象
		CellRangeAddress cellRangeAddress=new CellRangeAddress(0,0,0,0);
		//头标题样式
		HSSFCellStyle style1 = creatCellStyle(workbook, (short) 14);
		//列标题样式
		HSSFCellStyle style2 = creatCellStyle(workbook, (short) 11);
		//创建工作表
		HSSFSheet sheet = workbook.createSheet("中央空调信息列表");
		//加载合并单元格对象
		sheet.addMergedRegion(cellRangeAddress);
		//设置默认列宽
		sheet.setDefaultColumnWidth(15);
		//创建行
		//创建头标题行，并设置头标题
		HSSFRow row1 = sheet.createRow(0);
		HSSFCell cell1 = row1.createCell(0);
		//创建题行，并设置头标题
		HSSFRow row2 = sheet.createRow(1);
		String[] titles =
		{ "用户编码", "用户姓名", "小区名称", "楼栋号",  "单元号","户号" ,"风盘地址", "已用当量","风盘编号","模式","档位", "制冷高档", "制冷中档", "制冷低档","制热高档","制热中档 ","制热低档","计费状态","设定温度" ,"室内温度","远程状态","报警信息","季节","采集时间","用户面积","用户电话","备注"};
		for (int i = 0; i < titles.length; i++)
		{
			HSSFCell cell2 = row2.createCell(i);
			//加载单元格样式
			cell2.setCellStyle(style1);
			cell2.setCellValue(titles[i]);
		}
		//操作单元格，将用户写Execl
		if(yhInfosList!=null){
			for(int j=0;j<yhInfosList.size();j++){
			HSSFRow row =sheet .createRow(j+2);
			
			HSSFCell c1=row.createCell(0);
			c1.setCellValue(yhInfosList.get(j).getYhbh());
			
			HSSFCell c2=row.createCell(1);
			c2.setCellValue(yhInfosList.get(j).getYhMessage().getYhxm());
			
			HSSFCell c3=row.createCell(2);
			c3.setCellValue(yhInfosList.get(j).getYhMessage().getXqm());
//			HSSFCell c4=row.createCell(3);
//			c4.setCellValue(yhInfosList.get(j).getYhMessage().getLdh());
//			HSSFCell c5=row.createCell(4);
//			c5.setCellValue(yhInfosList.get(j).getYhMessage().getDyh());
//			HSSFCell c6=row.createCell(5);
//			c6.setCellValue(yhInfosList.get(j).getYhMessage().getHh());
//			HSSFCell c7=row.createCell(6);
//			c7.setCellValue(yhInfosList.get(j).getYydl());
//			HSSFCell c8=row.createCell(7);
//			c8.setCellValue(yhInfosList.get(j).getFpbh());
//			HSSFCell c9=row.createCell(8);
//			if(yhInfosList.get(j).getMs().equals("00")){
//				c9.setCellValue("制冷");
//			}
//			if(yhInfosList.get(j).getMs().equals("01")){
//				c9.setCellValue("制热");
//			}
//			if(yhInfosList.get(j).getMs().equals("02")){
//				c9.setCellValue("通风");
//			}
//			HSSFCell c10=row.createCell(9);
//			if(yhInfosList.get(j).getDw().equals("00")){
//				c10.setCellValue("停止");
//			}
//			if(yhInfosList.get(j).getDw().equals("01")){
//				c10.setCellValue("低档");
//			}
//			if(yhInfosList.get(j).getDw().equals("02")){
//				c10.setCellValue("中档");
//			}
//			if(yhInfosList.get(j).getDw().equals("03")){
//				c10.setCellValue("高档");
//			}
////			c9.setCellValue(yhInfosList.get(j).getDw());
//			HSSFCell c11=row.createCell(10);
//			c11.setCellValue(yhInfosList.get(j).getGdtime());
//			HSSFCell c12=row.createCell(11);
//			c12.setCellValue(yhInfosList.get(j).getZdtime());
//			HSSFCell c13=row.createCell(12);
//			c13.setCellValue(yhInfosList.get(j).getDgdtime());
//			HSSFCell c14=row.createCell(13);
//			c14.setCellValue(yhInfosList.get(j).getDzdtime());
//			HSSFCell c15=row.createCell(14);
//			c15.setCellValue(yhInfosList.get(j).getDddtime());
//			
//			HSSFCell c16=row.createCell(15);
//			if(yhInfosList.get(j).getJf().equals("00")){
//				c16.setCellValue("禁止计费");
//			}
//			if(yhInfosList.get(j).getJf().equals("01")){
//				c16.setCellValue("允许计费");
//			}
////			c15.setCellValue(yhInfosList.get(j).getJf());
//			HSSFCell c17=row.createCell(16);
//			c17.setCellValue(yhInfosList.get(j).getSdwd());
//			HSSFCell c18=row.createCell(17);
//			c18.setCellValue(yhInfosList.get(j).getSnwd());
//			HSSFCell c19=row.createCell(18);
//			if(yhInfosList.get(j).getKg().equals("00")){
//				c19.setCellValue("强制关");
//			}
//			if(yhInfosList.get(j).getKg().equals("01")){
//				c19.setCellValue("自动运行");
//			}
////			c18.setCellValue(yhInfosList.get(j).getKg());
//			HSSFCell c20=row.createCell(19);
//			c20.setCellValue(yhInfosList.get(j).getBj());
//			HSSFCell c21=row.createCell(20);
//			if(yhInfosList.get(j).getBj().equals("00")){
//				c21.setCellValue("正常");
//			}
//			if(yhInfosList.get(j).getBj().equals("01")){
//				c21.setCellValue("开盖");
//			}
//			if(yhInfosList.get(j).getBj().equals("03")){
//				c21.setCellValue("盗热嫌疑");
//			}
//			HSSFCell c22=row.createCell(21);
//			if(yhInfosList.get(j).getJj().equals("00")){
//				c22.setCellValue("夏季");
//			}
//			if(yhInfosList.get(j).getJj().equals("01")){
//				c22.setCellValue("冬季");
//			}
////			c21.setCellValue(yhInfosList.get(j).getJj());
////			c20.setCellValue(yhInfosList.get(j).getJj());
//			HSSFCell c23=row.createCell(22);
//			c23.setCellValue(newdate.format(yhInfosList.get(j).getTime()));
//			HSSFCell c24=row.createCell(23);
//			c24.setCellValue(yhInfosList.get(j).getMs());
//			HSSFCell c25=row.createCell(24);
//			c25.setCellValue(yhInfosList.get(j).getYhMessage().getMj());
//			HSSFCell c26=row.createCell(25);
//			c26.setCellValue(yhInfosList.get(j).getYhMessage().getLxdh());
//			
			
			
			HSSFCell c4=row.createCell(3);
			c4.setCellValue(yhInfosList.get(j).getYhMessage().getLdh());
			
			HSSFCell c5=row.createCell(4);
			c5.setCellValue(yhInfosList.get(j).getYhMessage().getDyh());
			
			HSSFCell c6=row.createCell(5);
			c6.setCellValue(yhInfosList.get(j).getYhMessage().getHh());
			
			HSSFCell c7=row.createCell(6);
			c7.setCellValue(yhInfosList.get(j).getYhMessage().getfpdz());
			
			HSSFCell c8=row.createCell(7);//已用当量
			c8.setCellValue(yhInfosList.get(j).getYydl());
			
			HSSFCell c9=row.createCell(8);
			c9.setCellValue(yhInfosList.get(j).getFpbh());
			
			HSSFCell c10=row.createCell(9);
			if(yhInfosList.get(j).getMs().equals("00")){
				c10.setCellValue("制冷");
			}
			if(yhInfosList.get(j).getMs().equals("01")){
				c10.setCellValue("制热");
			}
			if(yhInfosList.get(j).getMs().equals("02")){
				c10.setCellValue("通风");
			}
			HSSFCell c11=row.createCell(10);//档位
			if(yhInfosList.get(j).getDw().equals("00")){
				c11.setCellValue("停止");
			}
			if(yhInfosList.get(j).getDw().equals("01")){
				c11.setCellValue("低档");
			}
			if(yhInfosList.get(j).getDw().equals("02")){
				c11.setCellValue("中档");
			}
			if(yhInfosList.get(j).getDw().equals("03")){
				c11.setCellValue("高档");
			}
//			c9.setCellValue(yhInfosList.get(j).getDw());
			HSSFCell c12=row.createCell(11);
			c12.setCellValue(yhInfosList.get(j).getGdtime());
			
			HSSFCell c13=row.createCell(12);
			c13.setCellValue(yhInfosList.get(j).getZdtime());
			
			HSSFCell c14=row.createCell(13);
			c14.setCellValue(yhInfosList.get(j).getDdtime());
			
			HSSFCell c15=row.createCell(14);
			c15.setCellValue(yhInfosList.get(j).getDgdtime());
			
			HSSFCell c16=row.createCell(15);
			c16.setCellValue(yhInfosList.get(j).getDzdtime());
			
			HSSFCell c17=row.createCell(16);
			c17.setCellValue(yhInfosList.get(j).getDddtime());
			
			HSSFCell c18=row.createCell(17);
			if(yhInfosList.get(j).getJf().equals("00")){
				c18.setCellValue("禁止计费");
			}
			if(yhInfosList.get(j).getJf().equals("01")){
				c18.setCellValue("允许计费");
			}
 
			HSSFCell c19=row.createCell(18);
			c19.setCellValue(yhInfosList.get(j).getSdwd());
			
			HSSFCell c20=row.createCell(19);
			c20.setCellValue(yhInfosList.get(j).getSnwd());
			
			HSSFCell c21=row.createCell(20);//开关
			if(yhInfosList.get(j).getKg().equals("00")){
				c21.setCellValue("强制关");
			}
			if(yhInfosList.get(j).getKg().equals("01")){
				c21.setCellValue("自动运行");
			}
 
		/*	HSSFCell c22=row.createCell(21);
			c20.setCellValue(yhInfosList.get(j).getBj());*/
			
			HSSFCell c22=row.createCell(21);
			if(yhInfosList.get(j).getBj().equals("00")){
				c22.setCellValue("正常");
			}
			if(yhInfosList.get(j).getBj().equals("01")){
				c22.setCellValue("开盖");
			}
			if(yhInfosList.get(j).getBj().equals("03")){
				c22.setCellValue("盗热嫌疑");
			}
			HSSFCell c23=row.createCell(22);
			if(yhInfosList.get(j).getJj().equals("00")){
				c23.setCellValue("夏季");
			}
			if(yhInfosList.get(j).getJj().equals("01")){
				c23.setCellValue("冬季");
			}
//			c21.setCellValue(yhInfosList.get(j).getJj());
//			c20.setCellValue(yhInfosList.get(j).getJj());
			HSSFCell c24=row.createCell(23);
			c24.setCellValue(newdate.format(yhInfosList.get(j).getTime()));
			
		/*	HSSFCell c24=row.createCell(23);
			c24.setCellValue(yhInfosList.get(j).getMs());*/
			HSSFCell c25=row.createCell(24);
			c25.setCellValue(yhInfosList.get(j).getYhMessage().getMj());
			
			HSSFCell c26=row.createCell(25);
			c26.setCellValue(yhInfosList.get(j).getYhMessage().getLxdh());
			
			 
//			HSSFCell c27=row.createCell(26);
//			c27.setCellValue(yhInfosList.get(j).getYhMessage().getfpdz());
			HSSFCell c27=row.createCell(26);
			c27.setCellValue(yhInfosList.get(j).getYhMessage().getBz());

			}
		}
		workbook.write(outputStream);
		workbook.close();
	}
	private static HSSFCellStyle creatCellStyle(HSSFWorkbook workbook, short fontSize)
	{
		HSSFCellStyle style=workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//创建字体
		HSSFFont font=workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体
		font.setFontHeightInPoints(fontSize);
		//加载字体
		style.setFont(font);
		
		return style;
	}

}
