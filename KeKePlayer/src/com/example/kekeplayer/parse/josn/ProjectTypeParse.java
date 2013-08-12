package com.example.kekeplayer.parse.josn;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.kekeplayer.type.ProjectType;
public class ProjectTypeParse {

	public ProjectType parse(JSONObject paramJSONObject) throws JSONException {
		ProjectType projectType = new ProjectType();
		if (paramJSONObject.has("name")) {
			String name = paramJSONObject.getString("name");
			projectType.setName(name);
		}
		if (paramJSONObject.has("class")) {
			String classStr = paramJSONObject.getString("class");
			projectType.setTargetClass(classStr);
		}
		return projectType;
	}
}
