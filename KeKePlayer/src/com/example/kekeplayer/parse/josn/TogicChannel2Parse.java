package com.example.kekeplayer.parse.josn;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.kekeplayer.type.TogicTvChannel2Type;

public class TogicChannel2Parse {
	public TogicTvChannel2Type parse(JSONObject JObject) throws JSONException {
		TogicTvChannel2Type type = new TogicTvChannel2Type();
		if (JObject.has("channel_id")) {
			String channel_id = JObject.getString("channel_id");
			type.setChannel_id(channel_id);
		}
		if (JObject.has("channel_name")) {
			String channel_name = JObject.getString("channel_name");
			type.setChannel_name(channel_name);
		}
		if (JObject.has("icon_url")) {
			String icon_url = JObject.getString("icon_url");
			if (icon_url.startsWith("/upload")) {
				icon_url = "http://tv.togic.com" + icon_url;
			}
			type.setIcon_url(icon_url);
		}
		if (JObject.has("province")) {
			String province = JObject.getString("province");
			type.setProvince(province);
		}
		if (JObject.has("mode")) {
			String mode = JObject.getString("mode");
			type.setMode(mode);
		}
		if (JObject.has("url")) {
			String url = JObject.getString("url");
			type.setUrl(url);
		}
		if (JObject.has("second_url")) {
			JSONArray second_url = JObject.getJSONArray("second_url");
			int count = second_url.length();
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < count; i++) {
				list.add((String) second_url.get(i));
			}
			type.setSecond_url(list);
		}
		return type;
	}
}