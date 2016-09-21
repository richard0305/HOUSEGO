package com.dumu.housego.entity;

import java.io.Serializable;

public class BlockTradeList implements Serializable{
	 private String id;
	 private String catid;
	 private String posid;
	 private String module;
	 private String modelid;
	
	
	 private String title;
	 private String province;
	 private String thumb;
	 private String zongjia;
	 private String area;
	 private String city;
	 private String style;
	 private String url;
	 private String province_name;
	 private String city_name;
	 private String area_name;

	 private String listorder;
	 private String expiration;
	 private String extention;
	 private String synedit;
	public BlockTradeList(String id, String catid, String posid, String module, String modelid, String title,
			String province, String thumb, String zongjia, String area, String city, String style, String url,
			String province_name, String city_name, String area_name, String listorder, String expiration,
			String extention, String synedit) {
		super();
		this.id = id;
		this.catid = catid;
		this.posid = posid;
		this.module = module;
		this.modelid = modelid;
		this.title = title;
		this.province = province;
		this.thumb = thumb;
		this.zongjia = zongjia;
		this.area = area;
		this.city = city;
		this.style = style;
		this.url = url;
		this.province_name = province_name;
		this.city_name = city_name;
		this.area_name = area_name;
		this.listorder = listorder;
		this.expiration = expiration;
		this.extention = extention;
		this.synedit = synedit;
	}
	public BlockTradeList() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCatid() {
		return catid;
	}
	public void setCatid(String catid) {
		this.catid = catid;
	}
	public String getPosid() {
		return posid;
	}
	public void setPosid(String posid) {
		this.posid = posid;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getZongjia() {
		return zongjia;
	}
	public void setZongjia(String zongjia) {
		this.zongjia = zongjia;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getListorder() {
		return listorder;
	}
	public void setListorder(String listorder) {
		this.listorder = listorder;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public String getExtention() {
		return extention;
	}
	public void setExtention(String extention) {
		this.extention = extention;
	}
	public String getSynedit() {
		return synedit;
	}
	public void setSynedit(String synedit) {
		this.synedit = synedit;
	}
	@Override
	public String toString() {
		return "BlockTradeList [id=" + id + ", catid=" + catid + ", posid=" + posid + ", module=" + module
				+ ", modelid=" + modelid + ", title=" + title + ", province=" + province + ", thumb=" + thumb
				+ ", zongjia=" + zongjia + ", area=" + area + ", city=" + city + ", style=" + style + ", url=" + url
				+ ", province_name=" + province_name + ", city_name=" + city_name + ", area_name=" + area_name
				+ ", listorder=" + listorder + ", expiration=" + expiration + ", extention=" + extention + ", synedit="
				+ synedit + "]";
	}
	 
	 
	 
	 
	 
}
