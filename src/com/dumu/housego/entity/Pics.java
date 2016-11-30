package com.dumu.housego.entity;

public class Pics {
	private String url;
	private String alt;
	public Pics(String url, String alt) {
		super();
		this.url = url;
		this.alt = alt;
	}
	public Pics() {
		super();
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	@Override
	public String toString() {
		return "Pics [url=" + url + ", alt=" + alt + "]";
	}
	
	
	
}
