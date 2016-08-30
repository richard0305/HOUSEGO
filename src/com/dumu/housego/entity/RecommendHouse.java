package com.dumu.housego.entity;

import java.util.List;

public class RecommendHouse {
    private String id;
    private String catid;
    private String posid;
    private String module;
    private String modelid;
    private String thumb;
    private RecommendData data;
    private String listorder;
    private String expiration;
    private String extention;
    private String synedit;
    
    
	public RecommendHouse(String id, String catid, String posid, String module, String modelid, String thumb, RecommendData data,
			String listorder, String expiration, String extention, String synedit) {
		super();
		this.id = id;
		this.catid = catid;
		this.posid = posid;
		this.module = module;
		this.modelid = modelid;
		this.thumb = thumb;
		this.data = data;
		this.listorder = listorder;
		this.expiration = expiration;
		this.extention = extention;
		this.synedit = synedit;
	}
	public RecommendHouse() {
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
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public RecommendData getData() {
		return data;
	}
	public void setData(RecommendData data) {
		this.data = data;
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
		return "RecommendHouse [id=" + id + ", catid=" + catid + ", posid=" + posid + ", module=" + module
				+ ", modelid=" + modelid + ", thumb=" + thumb + ", data=" + data + ", listorder=" + listorder
				+ ", expiration=" + expiration + ", extention=" + extention + ", synedit=" + synedit + "]";
	}
    
    
    
    
    
}
