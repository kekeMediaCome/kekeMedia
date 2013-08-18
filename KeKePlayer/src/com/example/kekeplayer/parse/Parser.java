package com.example.kekeplayer.parse;

import org.json.JSONException;
import org.json.JSONObject;

public abstract interface Parser<T> {
	public abstract T parse(JSONObject paramJSONObject) throws JSONException;

//	public abstract Group parse(JSONArray paramJSONArray) throws JSONException;
}
