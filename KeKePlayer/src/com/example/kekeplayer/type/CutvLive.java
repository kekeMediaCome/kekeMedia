package com.example.kekeplayer.type;

import java.io.Serializable;

public class CutvLive implements Serializable {

	private static final long serialVersionUID = 1L;

	private int tv_id;
	private String tv_name;
	private String tv_thumb_img;
	private String tv_logo;

	public int getTv_id() {
		return tv_id;
	}

	public void setTv_id(int tv_id) {
		this.tv_id = tv_id;
	}

	public String getTv_name() {
		return tv_name;
	}

	public void setTv_name(String tv_name) {
		this.tv_name = tv_name;
	}

	public String getTv_thumb_img() {
		return tv_thumb_img;
	}

	public void setTv_thumb_img(String tv_thumb_img) {
		this.tv_thumb_img = tv_thumb_img;
	}

	public String getTv_logo() {
		return tv_logo;
	}

	public void setTv_logo(String tv_logo) {
		this.tv_logo = tv_logo;
	}

}
