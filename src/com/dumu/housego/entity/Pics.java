package com.dumu.housego.entity;

import java.io.Serializable;

public class Pics implements Serializable{
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
