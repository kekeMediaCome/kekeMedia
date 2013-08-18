package com.example.kekeplayer.type;

import java.io.Serializable;

public class CutvLiveSub implements Serializable {

	private static final long serialVersionUID = 1L;

	private int channel_id;
	private String channel_name;
	private String thumb;
	private String mobile_url;

	public int getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(int channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getMobile_url() {
		return mobile_url;
	}

	public void setMobile_url(String mobile_url) {
		this.mobile_url = mobile_url;
	}

}
