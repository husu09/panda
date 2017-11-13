package com.su.panda;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.su.panda.download.L14Download;
import com.su.panda.download.L7Download;
import com.su.panda.download.LiveLineDownload;
import com.su.panda.parse.L14Parse;
import com.su.panda.parse.L7Parse;
import com.su.panda.parse.LiveLineParse;

public class AppContext {
	/**
	 * 线路地址匹配
	 */
	private static final Map<Integer, String> liveLineURLMap = new HashMap<>();
	/**
	 * 线路解析器
	 */
	private static final Map<Integer, LiveLineParse> liveLineParseMap = new HashMap<>();
	/**
	 * 下载器
	 */
	private static final Map<Integer, LiveLineDownload> LiveLineDownloadMap = new HashMap<>();

	/**
	 * 执行下载任务的线程池
	 */
	private static ExecutorService tPool = Executors.newCachedThreadPool();

	public static void putUrl(int type, String url) {
		liveLineURLMap.put(type, url);
	}

	public static String getUrl(int type) {
		return liveLineURLMap.get(type);
	}

	public static void putParse(int type, LiveLineParse parse) {
		liveLineParseMap.put(type, parse);
	}

	public static LiveLineParse getParse(int type) {
		return liveLineParseMap.get(type);
	}

	public static void putDownload(int type, LiveLineDownload download) {
		LiveLineDownloadMap.put(type, download);
	}

	public static LiveLineDownload getDownload(int type) {
		return LiveLineDownloadMap.get(type);
	}

	public static void execute(Runnable r) {
		tPool.execute(r);
	}
	
	public static void init() {
		// 解析器
		putParse(2, new L7Parse());
		putParse(7, new L7Parse());
		putParse(14, new L14Parse());
		// 下载器
		putDownload(2, new L7Download());
		putDownload(7, new L7Download());
		putDownload(14, new L14Download());
	}

}
