package com.su.panda;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
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
	public final static String DEFAULT_LIVE_LINE_1;
	/**
	 * 文件保存路径
	 */
	public static final String SAVE_DIR;

	/**
	 * 线路地址匹配
	 */
	private static final Map<Integer, String> liveLineMap = new HashMap<>();
	static {
		// 15 类型：第一个?号代表线程，第二个?号代表roomKey
		liveLineMap.put(15, "https://pl?.live.panda.tv/live_panda/?.flv");
		// TODO 其它类型
	}

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
		DEFAULT_LIVE_LINE_1 = p.getProperty("defaultLiveLine.1");
		SAVE_DIR = p.getProperty("saveDir");

	}

	public static Map<Integer, String> getLivelinemap() {
		return liveLineMap;
	}

}
