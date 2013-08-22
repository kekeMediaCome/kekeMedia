package com.example.kekeplayer.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.kekeplayer.KeKeApp;
import com.example.kekeplayer.KeKeSetting;
import com.example.kekeplayer.parse.josn.MediaTypeParse;
import com.example.kekeplayer.type.MediaType;
import com.example.kekeplayer.utils.JSONUtils;
import com.example.kekeplayer.utils.https.HttpApiImpl;

public class MediaTypeDAO {
	public List<MediaType> getMediaTypes(String uri,String type) throws JSONException {
		List<MediaType> list = new ArrayList<MediaType>();
		StringBuffer buffer = new StringBuffer();
		buffer.append(uri).append(KeKeSetting.IFACE_GET_MEDIA_TYPE)
				.append(type).append(KeKeSetting.PID)
				.append("&version=");
		buffer.append(KeKeApp.getVersion());
		String url = buffer.toString();
		String json = new HttpApiImpl().doHttpGet(url);
		JSONObject result = JSONUtils.getBodyAndCheckHeader(url, json);
		JSONArray mediatypesArray = result.getJSONArray("mediatypes");
		MediaTypeParse mediaTypeParse = new MediaTypeParse();
		int size = mediatypesArray.length();
		for (int i = 0; i < size; i++) {
			list.add(mediaTypeParse.parse(mediatypesArray.getJSONObject(i)));
		}
		return list;
	}
}
