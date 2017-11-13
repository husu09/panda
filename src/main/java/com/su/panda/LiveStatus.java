package com.su.panda;

public enum LiveStatus {
	GET_SUCCESS(0, "获取api信息成功"), NET_CONNECT_ERR(1, "请求api网络错误"), CALL_API_ERR(2, "调用api失败"), LIVE_UNOPEN(3,
			"直播未开始"), SYSTEM_ERR(4, "系统错误"), PARSE_LIVE_LINE_ERR(5, "解析线路错误");
	private final int status;
	private final String message;

	private LiveStatus(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
