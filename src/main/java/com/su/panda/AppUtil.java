package com.su.panda;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppUtil {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdf_ = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static String getDateTime() {
		return sdf.format(new Date());
	}
	
	public static String getFileName() {
		return sdf_.format(new Date());
	}
}
