package com.example.kekeplayer.type;

import java.io.Serializable;
import java.util.List;

public class TogicTvChannel2Type implements Serializable {

	private static final long serialVersionUID = 1L;

	private String channel_id;
	private String channel_name;
	private String icon_url;
	private String province;
	private String mode;
	private String url;
	private List<String> second_url;

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getSecond_url() {
		return second_url;
	}

	public void setSecond_url(List<String> second_url) {
		this.second_url = second_url;
	}

}
