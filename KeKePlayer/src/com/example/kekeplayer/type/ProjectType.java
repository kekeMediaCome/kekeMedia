package com.example.kekeplayer.type;

import java.io.Serializable;

public class ProjectType implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String targetClass;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTargetClass() {
		return targetClass;
	}
	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}
}
