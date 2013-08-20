package com.example.kekeplayer.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.kekeplayer.parse.josn.ProjectTypeParse;
import com.example.kekeplayer.type.ProjectType;

public class ProjectTypeDAO {
	public List<ProjectType> getProjectTypes() {
		List<ProjectType> list = new ArrayList<ProjectType>();
		try {
			String json_result = "{\"result\":[{\"name\":\"推荐应用\", \"class\":\"com.example.kekeplayer.activity.KeKePlayer\"}, {\"name\":\"回看\", \"class\":\"com.example.kekeplayer.activity.KeKePlayer\"}, {\"name\":\"CUTV直播\", \"class\":\"com.example.kekeplayer.activity.KeKeLive_Cutv\"}]}"; // new
			// HttpApiImpl().doHttpGet(url);
			JSONObject result = new JSONObject(json_result);// JSONUtils.getBodyAndCheckHeader(url,
															// json_result);
			JSONArray pragramsArray = result.getJSONArray("result");
			ProjectTypeParse projectTypeParse = new ProjectTypeParse();
			int size = pragramsArray.length();
			for (int i = 0; i < size; i++) {
				list.add(projectTypeParse.parse(pragramsArray.getJSONObject(i)));
			}
		} catch (Exception e) {
		}
		return list;
	}
}
