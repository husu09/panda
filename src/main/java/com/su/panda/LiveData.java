package com.su.panda;

import java.util.ArrayList;
import java.util.List;

public class LiveData {
	private	LiveStatus status;
	/**
	 * 线程类型
	 * */
	private int liveLineType;
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
	
	public int getLiveLineType() {
		return liveLineType;
	}
	public void setLiveLineType(int liveLineType) {
		this.liveLineType = liveLineType;
	}
	@Override
	public String toString() {
		return "LiveData [status=" + status + ", liveLineType=" + liveLineType + ", mainUrl=" + mainUrl + ", backupUrl="
				+ backupUrl + "]";
	}
	
	
	
	
}
