package com.su.panda.parse;

import com.su.panda.LiveData;

public interface LiveLineParse {
	/**
	 * 解析线路信息
	 */
	public String parse(LiveData data, String vlStr, String sign, String rid, String time);
}
