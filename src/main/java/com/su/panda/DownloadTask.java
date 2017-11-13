package com.su.panda;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DownloadTask implements Runnable {

	private InputStream in;

	public DownloadTask(InputStream in) {
		this.in = in;
	}

	@Override
	public void run() {
		String fileName = AppUtil.getFileName();
		File file = new File(AppConst.SAVE_DIR + fileName + ".flv");
		OutputStream out = null;
		try {
			if (!file.createNewFile()) {
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
			AppStart.flag = true;
			System.out.println("视频流结束");
		}

	}

}
