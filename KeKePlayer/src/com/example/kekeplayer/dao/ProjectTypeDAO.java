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
			String json_result = "{\"result\":[{\"name\":\"推荐应用\", \"class\":\"com.example.kekeplayer.activity.KeKePlayer\"}, {\"name\":\"回看\", \"class\":\"com.example.kekeplayer.activity.KeKePlayer\"}, {\"name\":\"CU直播\", \"class\":\"com.example.kekeplayer.activity.KeKeLive_Cutv\"}, {\"name\":\"快捷直播-播放界面切换频道\", \"class\":\"com.example.kekeplayer.activity.KeKeLive_Togic\"}, {\"name\":\"捷2直播-显示频道图标\", \"class\":\"com.example.kekeplayer.activity.KeKeLive_Togic2\"}, {\"name\":\"本地视频播放\", \"class\":\"com.example.kekeplayer.activity.KeKeLocal\"}, {\"name\":\"自定义直播\", \"class\":\"com.example.kekeplayer.activity.CustomExpand\"} ]}"; // new
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