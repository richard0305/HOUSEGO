package com.dumu.housego.entity;

public class User {
	private String username;
	private String modelid;//注册模型（35：普通会员，36：经纪人）
	private String password;
	private String password2;
	private String yzm;
	
	
	
	public User(String username, String modelid, String password, String password2, String yzm) {
		super();
		this.username = username;
		this.modelid = modelid;
		this.password = password;
		this.password2 = password2;
		this.yzm = yzm;
	}
	
	
	
	public User() {
		super();
	}
	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getYzm() {
		return yzm;
	}
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	
	
	
	@Override
	public String toString() {
		return "User [username=" + username + ", modelid=" + modelid + ", password=" + password + ", password2="
				+ password2 + ", yzm=" + yzm + "]";
	}
	
	
	
	
	
}
