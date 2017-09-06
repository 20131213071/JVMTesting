package com.apmdemo.common;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ToolsUtil{
	
	//获取系统时间
	public static String getSysDate(){
		SimpleDateFormat sysTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sysTime.format(new Date());
	}
	
}
