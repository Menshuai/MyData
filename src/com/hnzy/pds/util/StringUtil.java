package com.hnzy.pds.util;

import java.util.UUID;

//�ַ����ж�
public class StringUtil {

	//Сд�ַ�
	public static String gainUUID(){
		String strUUID=UUID.randomUUID().toString();
		return strUUID;	
	}
	
	//�ж��Ƿ�Ϊ��
	public static boolean isEmpty(String str){
		return ((str == null) || ("".equals(str.trim())));
	}
	
	//���ǿ�
	public static boolean isNoEmpty(String str){
		return !((str==null)||("".equals(str.trim())));
	}
}
