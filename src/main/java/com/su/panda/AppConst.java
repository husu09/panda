package com.su.panda;

import java.io.InputStream;
import java.util.Properties;

public class AppConst {
	/**
	 * 房间id
	 */
	public final static int ROOM_ID;
	/**
	 * roomKey不确定该参数来源，稳定性：不确定
	 */
	public final static String ROOM_KEY;
	/**
	 * 获取视频信息的 api 地址，稳定性：高
	 */
	public final static String API_URL;
	/**
	 * 获取视频的地址
	 */
	public final static String LIVE_LINE_1;
	public final static String LIVE_LINE_2;
	public final static String LIVE_LINE_3;
	public final static String LIVE_LINE_4;
	public final static String LIVE_LINE_5;
	public final static String LIVE_LINE_6;
	public final static String LIVE_LINE_7;
	public final static String LIVE_LINE_8;
	public final static String LIVE_LINE_9;
	public final static String LIVE_LINE_10;
	public final static String LIVE_LINE_11;
	public final static String LIVE_LINE_12;
	public final static String LIVE_LINE_13;
	public final static String LIVE_LINE_14;
	public final static String LIVE_LINE_15;
	public final static String LIVE_LINE_16;
	public final static String LIVE_LINE_17;
	public final static String LIVE_LINE_18;
	public final static String LIVE_LINE_19;
	public final static String LIVE_LINE_20;
	public final static String LIVE_LINE_21;
	public final static String LIVE_LINE_22;
	public final static String LIVE_LINE_23;
	public final static String LIVE_LINE_24;
	public final static String LIVE_LINE_25;
	/**
	 * 文件保存路径
	 */
	public static final String SAVE_DIR;

	static {

		Properties p = new Properties();
		InputStream in;
		try {
			in = AppConst.class.getResourceAsStream("/config.properties");
			p.load(in);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("加载配置信息出错");
		}
		ROOM_ID = Integer.parseInt(p.getProperty("roomid"));
		ROOM_KEY = p.getProperty("roomKey");
		API_URL = p.getProperty("apiUrl");

		LIVE_LINE_1 = p.getProperty("defaultLiveLine.1");
		LIVE_LINE_2 = p.getProperty("defaultLiveLine.2");
		LIVE_LINE_3 = p.getProperty("defaultLiveLine.3");
		LIVE_LINE_4 = p.getProperty("defaultLiveLine.4");
		LIVE_LINE_5 = p.getProperty("defaultLiveLine.5");
		LIVE_LINE_6 = p.getProperty("defaultLiveLine.6");
		LIVE_LINE_7 = p.getProperty("defaultLiveLine.7");
		LIVE_LINE_8 = p.getProperty("defaultLiveLine.8");
		LIVE_LINE_9 = p.getProperty("defaultLiveLine.9");
		LIVE_LINE_10 = p.getProperty("defaultLiveLine.10");
		LIVE_LINE_11 = p.getProperty("defaultLiveLine.11");
		LIVE_LINE_12 = p.getProperty("defaultLiveLine.12");
		LIVE_LINE_13 = p.getProperty("defaultLiveLine.13");
		LIVE_LINE_14 = p.getProperty("defaultLiveLine.14");
		LIVE_LINE_15 = p.getProperty("defaultLiveLine.15");
		LIVE_LINE_16 = p.getProperty("defaultLiveLine.16");
		LIVE_LINE_17 = p.getProperty("defaultLiveLine.17");
		LIVE_LINE_18 = p.getProperty("defaultLiveLine.18");
		LIVE_LINE_19 = p.getProperty("defaultLiveLine.19");
		LIVE_LINE_20 = p.getProperty("defaultLiveLine.20");
		LIVE_LINE_21 = p.getProperty("defaultLiveLine.21");
		LIVE_LINE_22 = p.getProperty("defaultLiveLine.22");
		LIVE_LINE_23 = p.getProperty("defaultLiveLine.23");
		LIVE_LINE_24 = p.getProperty("defaultLiveLine.24");
		LIVE_LINE_25 = p.getProperty("defaultLiveLine.25");
		SAVE_DIR = p.getProperty("saveDir");

		AppContext.putUrl(1, LIVE_LINE_1);
		AppContext.putUrl(2, LIVE_LINE_2);
		AppContext.putUrl(3, LIVE_LINE_3);
		AppContext.putUrl(4, LIVE_LINE_4);
		AppContext.putUrl(5, LIVE_LINE_5);
		AppContext.putUrl(6, LIVE_LINE_6);
		AppContext.putUrl(7, LIVE_LINE_7);
		AppContext.putUrl(8, LIVE_LINE_8);
		AppContext.putUrl(9, LIVE_LINE_9);
		AppContext.putUrl(10, LIVE_LINE_10);
		AppContext.putUrl(11, LIVE_LINE_11);
		AppContext.putUrl(12, LIVE_LINE_12);
		AppContext.putUrl(13, LIVE_LINE_13);
		AppContext.putUrl(14, LIVE_LINE_14);
		AppContext.putUrl(15, LIVE_LINE_15);
		AppContext.putUrl(16, LIVE_LINE_16);
		AppContext.putUrl(17, LIVE_LINE_17);
		AppContext.putUrl(18, LIVE_LINE_18);
		AppContext.putUrl(19, LIVE_LINE_19);
		AppContext.putUrl(20, LIVE_LINE_20);
		AppContext.putUrl(21, LIVE_LINE_21);
		AppContext.putUrl(22, LIVE_LINE_22);
		AppContext.putUrl(23, LIVE_LINE_23);
		AppContext.putUrl(24, LIVE_LINE_24);
		AppContext.putUrl(25, LIVE_LINE_25);

	}

}
