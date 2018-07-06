package com.hnzy.pds.util;

import java.util.UUID;

//×Ö·û´®ÅÐ¶Ï
public class StringUtil {

	//Ð¡Ð´×Ö·û
	public static String gainUUID(){
		String strUUID=UUID.randomUUID().toString();
		return strUUID;	
	}
	
	//ÅÐ¶ÏÊÇ·ñÎª¿Õ
	public static boolean isEmpty(String str){
		return ((str == null) || ("".equals(str.trim())));
	}
	
	//²»ÊÇ¿Õ
	public static boolean isNoEmpty(String str){
		return !((str==null)||("".equals(str.trim())));
	}
}
