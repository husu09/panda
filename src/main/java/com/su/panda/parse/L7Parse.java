package com.su.panda.parse;

import com.su.panda.AppConst;
import com.su.panda.AppContext;
import com.su.panda.LiveData;
import com.su.panda.LiveStatus;

public class L7Parse implements LiveLineParse {
	
	
	@Override
	public String parse(LiveData data, String vlStr, String sign, String rid, String time) {
		String[] arr = vlStr.split("_");
		// 获取线路
		String liveUrl = AppContext.getUrl(Integer.parseInt(arr[0]));
		if (liveUrl == null) {
			data.setStatus(LiveStatus.PARSE_LIVE_LINE_ERR);
			return null;
		}
		data.setLiveLineType(Integer.parseInt(arr[0]));
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
