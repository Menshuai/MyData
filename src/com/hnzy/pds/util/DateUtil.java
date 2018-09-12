package com.hnzy.pds.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
 
/**
 * 
 * <p>
 * 时间转换工具类
 * </p>
 * @date 2016-11-22 上午09:48:59
 * @author ms
 * 
 */
public class DateUtil {
	
	public final static SimpleDateFormat sdfDateTimeFormatYMDEnd = new SimpleDateFormat("yyyy年MM月dd日   ");
	public final static SimpleDateFormat sdfDateTimeFormatYMDStrt = new SimpleDateFormat("yyyy-MM-dd ");
	 /**
	   * 转换日期格式
	   * @param strDate 字符串日期
	   * @param srcFormat 原始格式
	   * @param tarFormat 目标格式
	   * @return
	   */
	  public static String strToStr(String strDate){
	       Date date;
			try {
				date = sdfDateTimeFormatYMDStrt.parse(strDate);
		        strDate = sdfDateTimeFormatYMDEnd.format(date);
			} catch (ParseException e) {
				return "日期转换出错";
			}
		      return strDate;
	  }
	
	public static void main(String[] args) {
		String s=DateUtil.strToStr("2018-12-01 11:23:10.111");
		System.out.println("格式化日期為："+s);
	}
}