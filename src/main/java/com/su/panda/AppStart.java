package com.su.panda;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.su.panda.download.LiveLineDownload;
import com.su.panda.parse.LiveLineParse;

public class AppStart {

	private static AppStart app = new AppStart();

	public static void main(String[] args) {
		AppContext.init();
		// 不可修复的错误直接关闭
		LiveData data = app.getLiveData(AppConst.API_URL, AppConst.ROOM_ID, AppConst.ROOM_KEY);
		if (data.getStatus().getStatus() != 0 && data.getStatus().getStatus() != 3) {
			app.parintStatus(data.getStatus());
			return;
		}
		LiveLineDownload download = AppContext.getDownload(data.getLiveLineType());
		download.download(data);
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
			// TODO 判断开播状态，没有找到时跟开播状态相关的信息
			JSONObject videoinfo = data.getJSONObject("videoinfo");
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
					// liveData.getBackupUrl().add(app.parseLiveLineInfo((String) s, sign, rid,
					// time));
				}
			}
			// 主线路
			String main = plflag_list.getString("main");
			String[] arr = main.split("_");
			LiveLineParse parse = AppContext.getParse(Integer.parseInt(arr[0]));
			if (parse == null) {
				liveData.setStatus(LiveStatus.NO_PARSE);
				return liveData;
			}
			String url = parse.parse(liveData, main, sign, rid, time);
			liveData.setMainUrl(url);

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

	public void parintStatus(LiveStatus ls) {
		System.out.println(ls.getStatus() + " : " + ls.getMessage());
	}

}
