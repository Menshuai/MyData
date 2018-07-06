package com.hnzy.pds.util;

import java.util.Properties;

public class PropertyUtil {

	private static Properties props;

	private PropertyUtil() {
	}
	public static String InformationSame="新密码与原密码相同"; 
	public static String InformationNoSame="新密码与确认密码不一致"; 
	public static String Informationerror="账户或密码错误"; 
	public static String Informationempty="账户或密码为空"; 
	public static String Accountdisabled="账户已停用，请联系管理员"; 
	public static String InformationName="用户名已存在"; 

//
	public static Properties getSysProperties() {
		return props;
	}

	//获取属性
	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	
	public static String getProperty(String key, String defaultVal) {
		return props.getProperty(key, defaultVal);
	}

}
