package com.su.panda;

import java.util.ArrayList;
import java.util.List;

public class LiveData {
	/**
	 * 状态码:1-请求api失败，2-api调用失败，3-直播未开始,4-系统错误
	 * */
	private	LiveStatus status;
	/**
	 * 视频主地址
	 * */
	private String mainUrl;
	/**
	 * 视频备用地址
	 * */
	private List<String> backupUrl;
	
	public LiveStatus getStatus() {
		return status;
	}
	public void setStatus(LiveStatus status) {
		this.status = status;
	}
	public String getMainUrl() {
		return mainUrl;
	}
	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}
	public List<String> getBackupUrl() {
		if (backupUrl == null) {
			backupUrl = new ArrayList<>(5);
		}
		return backupUrl;
	}
	public void setBackupUrl(List<String> backupUrl) {
		this.backupUrl = backupUrl;
	}
	@Override
	public String toString() {
		return "LiveData [status=" + status + ", mainUrl=" + mainUrl + ", backupUrl=" + backupUrl + "]";
	}
	
	
	
}
