package com.example.kekeplayer.parse.josn;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.kekeplayer.type.TogicTvChannelType;

public class TogicChannelParse {

	public TogicTvChannelType parse(JSONObject paramJSONObject)
			throws JSONException {
		TogicTvChannelType type = new TogicTvChannelType();
		if (paramJSONObject.has("_id")) {
			String _id = paramJSONObject.getString("_id");
			type.set_id(_id);
		}
		if (paramJSONObject.has("category")) {
			JSONArray category = paramJSONObject.getJSONArray("category");
			int count = category.length();
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < count; i++) {
				list.add((String) category.get(i));
			}
			type.setCategory(list);
		}
		if (paramJSONObject.has("icon")) {
			String icon = paramJSONObject.getString("icon");
			type.setIcon(icon);
		}
		if (paramJSONObject.has("province")) {
			String province = paramJSONObject.getString("province");
			type.setProvince(province);
		}
		if (paramJSONObject.has("title")) {
			String title = paramJSONObject.getString("title");
			type.setTitle(title);
		}
		if (paramJSONObject.has("urls")) {
			JSONArray urls = paramJSONObject.getJSONArray("urls");
			int count = urls.length();
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < count; i++) {
				list.add((String) urls.get(i));
			}
			type.setUrls(list);
		}
		if (paramJSONObject.has("num")) {
			int num = paramJSONObject.getInt("num");
			type.setNum(num);
		}
		return type;
	}
}
