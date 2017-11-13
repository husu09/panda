package com.su.panda.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.su.panda.AppConst;
import com.su.panda.AppContext;
import com.su.panda.AppUtil;
import com.su.panda.LiveData;

public class L14Download implements LiveLineDownload, Runnable {

	private InputStream in;
	private String fileName;

	@Override
	public void run() {

		File file = new File(AppConst.SAVE_DIR + fileName + ".m4s");
		OutputStream out = null;
		try {
			if (!file.exists() && !file.createNewFile()) {
				return;
			}
			out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int hasRead = 0;
			while ((hasRead = in.read(buf)) > 0) {
				out.write(buf, 0, hasRead);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				in = null;
			}
			try {
				out.close();
			} catch (IOException e) {
				out = null;
			}
			System.out.println("视频流结束");
		}

	}

	@Override
	public void download(LiveData data) {
		// 分片时间戳
		String os = Long.toHexString(System.currentTimeMillis() / 1000);
		while (true) {
			try {
				data.setMainUrl(data.getMainUrl().replaceFirst("\\?", os));
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
				if (conn.getResponseCode() != 200) {
					continue;
				}
				System.out.println(AppUtil.getDateTime() + " 开始：");
				in = conn.getInputStream();
				fileName = os;
				AppContext.execute(this);
				Long nos = Long.valueOf(os, 16) + 1;
				os = Long.toHexString(nos);
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
	}

}
