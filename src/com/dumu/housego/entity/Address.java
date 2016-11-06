package com.dumu.housego.entity;

public class Address {
	private String id;
	private String pid;
	private String name;
	private String cid;
	public Address(String id, String pid, String name, String cid) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.cid = cid;
	}
	public Address() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", pid=" + pid + ", name=" + name + ", cid=" + cid + "]";
	}
	
	
	
}
