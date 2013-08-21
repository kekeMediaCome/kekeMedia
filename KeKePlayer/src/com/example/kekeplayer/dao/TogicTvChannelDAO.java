package com.example.kekeplayer.dao;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.kekeplayer.parse.josn.TogicChannelParse;
import com.example.kekeplayer.type.TogicTvChannelType;
import com.example.kekeplayer.utils.https.HttpUtil;

public class TogicTvChannelDAO {
	public List<TogicTvChannelType> getTvChannel() {
		List<TogicTvChannelType> list = new ArrayList<TogicTvChannelType>();
		String url = "http://tv.togic.com/api/channels?api=1";
		InputStream stream = HttpUtil.GetInputStreamFromURL(url);
		String readString;
		StringBuffer bufer = new StringBuffer();
		try {
			int len;
			byte[] data = new byte[1024];
			while ((len = stream.read(data)) != -1) {
				readString = new String(data, 0, len);
				bufer.append(readString);
			}
		} catch (Exception e) {
		}
		try {
			JSONArray result = new JSONArray(bufer.toString());
			int count = result.length();
			TogicChannelParse parse = new TogicChannelParse();
			for (int i = 0; i < count; i++) {
				JSONObject object = result.getJSONObject(i);
				if (object.has("province")) {
					String province = object.getString("province");
					if (!"中央".equals(province)) {
						list.add(parse.parse(object));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
