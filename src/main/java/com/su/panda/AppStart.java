package com.su.panda;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AppStart {

	public static volatile boolean flag = true;

	private static AppStart app = new AppStart();
	/**
	 * 执行下载任务的线程池
	 */
	private static ExecutorService tPool = Executors.newCachedThreadPool();

	public static void main(String[] args) {

		LiveData data = app.getLiveData(AppConst.API_URL, AppConst.ROOM_ID, AppConst.ROOM_KEY);
		while (true) {
			try {
				if (flag) {
					URL url = new URL(data.getMainUrl());
					// 打开和 url 之间的连接
					HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
					// 设置通用的请求属性
					conn.setRequestProperty("accept", "*/*");
					conn.setRequestProperty("connection", "keep-alive");
					conn.setRequestProperty("user-agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
					// 建立实际连接
					conn.connect();
					if (conn.getResponseCode() == 403) {
						System.out.println("403连接异常!!!");
						return;
					}
					System.out.println(AppUtil.getDateTime() + " 开始：");
					tPool.execute(new DownloadTask(conn.getInputStream()));
					flag = false;
				}
			} catch (IOException e) {
				// e.printStackTrace();
				System.out.println("......");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 解析api消息
	 */
	public LiveData getLiveData(String apiUrl, int roomid, String roomKey) {
		LiveData liveData = new LiveData();
		HttpClient client = HttpClients.createDefault();
		StringBuilder curl = new StringBuilder(apiUrl);
		curl.append("?roomid=");
		curl.append(roomid);
		curl.append("&roomkey=");
		curl.append(roomKey);

		HttpGet get = new HttpGet(curl.toString());
		try {
			HttpResponse response = client.execute(get);
			HttpEntity httpEntity = response.getEntity();
			String result = EntityUtils.toString(httpEntity, "utf-8");
			JSONObject jso = (JSONObject) JSON.parse(result);
			int errno = jso.getIntValue("errno");
			String errmsg = jso.getString("errmsg");
			if (errno != 0) {
				System.out.println(errmsg);
				liveData.setStatus(LiveStatus.CALL_API_ERR);
				return liveData;
			}
			JSONObject data = jso.getJSONObject("data");
			int cover_status = data.getJSONObject("roominfo").getIntValue("cover_status");
			// TODO 判断开播状态，没有找到时跟开播状态相关的信息
			JSONObject videoinfo = jso.getJSONObject("data").getJSONObject("videoinfo");
			JSONObject plflag_list = videoinfo.getJSONObject("plflag_list");
			JSONObject auth = plflag_list.getJSONObject("auth");
			String plflag = videoinfo.getString("plflag");
			String rid = auth.getString("rid");
			String sign = auth.getString("sign");
			String time = auth.getString("time");
			// 备用线路
			JSONArray backup = plflag_list.getJSONArray("backup");
			for (Object s : backup) {
				if (s instanceof String) {
					liveData.getBackupUrl().add(app.parseLiveLineInfo((String) s, sign, rid, time));
				}
			}
			// 主线路
			String main = plflag_list.getString("main");
			liveData.setMainUrl(app.parseLiveLineInfo(main, sign, rid, time));

		} catch (ParseLiveLineException e) {
			liveData.setStatus(LiveStatus.PARSE_LIVE_LINE_ERR);
			e.printStackTrace();
		} catch (IOException e) {
			liveData.setStatus(LiveStatus.NET_CONNECT_ERR);
			e.printStackTrace();
		} catch (Exception e) {
			liveData.setStatus(LiveStatus.SYSTEM_ERR);
			e.printStackTrace();
		}
		liveData.setStatus(LiveStatus.GET_SUCCESS);
		return liveData;
	}

	/**
	 * 解析线路信息
	 * 
	 * @throws ParseLiveLineException
	 */
	public String parseLiveLineInfo(String vlStr, String sign, String rid, String time) throws ParseLiveLineException {
		String[] arr = vlStr.split("_");
		// 使用默认线路
		String liveUrl = AppConst.DEFAULT_LIVE_LINE_1;
		/*
		 * String liveUrl = liveLineMap.get(arr[0]); if (liveUrl == null) {
		 * throw new ParseLiveLineException(); }
		 */
		liveUrl = liveUrl.replaceFirst("\\?", arr[1]).replaceFirst("\\?", AppConst.ROOM_KEY);
		StringBuilder sbl = new StringBuilder(liveUrl);
		sbl.append("?");
		sbl.append("sign=");
		sbl.append(sign);
		sbl.append("&ts=");
		sbl.append(time);
		sbl.append("&rid=");
		sbl.append(rid);
		return sbl.toString();
	}

}
