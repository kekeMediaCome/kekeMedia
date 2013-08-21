package com.example.kekeplayer.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.kekeplayer.parse.josn.TogicChannel2Parse;
import com.example.kekeplayer.type.TogicTvChannel2Type;
import com.example.kekeplayer.utils.https.HttpApiImpl;

public class TogicTvChannel2DAO {
	public List<TogicTvChannel2Type> getTvChannel() {
		List<TogicTvChannel2Type> list = new ArrayList<TogicTvChannel2Type>();
		String url = "http://tv.togic.com/api/channels?api=2";
		String json = new HttpApiImpl().doHttpGet(url);
		try {
			JSONArray result = new JSONArray(json);
			int count = result.length();
			TogicChannel2Parse parse = new TogicChannel2Parse();
			for (int i = 0; i < count; i++) {
				JSONObject object = result.getJSONObject(i);
				list.add(parse.parse(object));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}
