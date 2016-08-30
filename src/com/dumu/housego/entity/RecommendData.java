package com.dumu.housego.entity;

public class RecommendData {
	private String title;
	private String description;
	private String thumb;
	private String posid;
	private String style;
	private String url;
	private String province_name;
	private String city_name;
	private String area_name;
	
	
	
	
	public RecommendData(String title, String description, String thumb, String posid, String style, String url,
			String province_name, String city_name, String area_name) {
		super();
		this.title = title;
		this.description = description;
		this.thumb = thumb;
		this.posid = posid;
		this.style = style;
		this.url = url;
		this.province_name = province_name;
		this.city_name = city_name;
		this.area_name = area_name;
	}
	public RecommendData() {
		super();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getPosid() {
		return posid;
	}
	public void setPosid(String posid) {
		this.posid = posid;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	@Override
	public String toString() {
		return "data [title=" + title + ", description=" + description + ", thumb=" + thumb + ", posid=" + posid
				+ ", style=" + style + ", url=" + url + ", province_name=" + province_name + ", city_name=" + city_name
				+ ", area_name=" + area_name + "]";
	}

	
}
