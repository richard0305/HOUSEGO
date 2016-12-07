package com.dumu.housego.entity;

public class YHQ {
    private String order_no;
    private String house_id;
    private String coupon_id;
    private String coupon_name;
    private String difu;
    private String shifu;
    private String userid;
    private String buyname;
    private String buytel;
    private String inputtime;
    private String id;
    private String trade_status;
    private String house_title;
    private String pay_status;
    
    
	public YHQ(String order_no, String house_id, String coupon_id, String coupon_name, String difu, String shifu,
			String userid, String buyname, String buytel, String inputtime, String id, String trade_status,
			String house_title,String pay_status) {
		super();
		this.pay_status=pay_status;
		this.order_no = order_no;
		this.house_id = house_id;
		this.coupon_id = coupon_id;
		this.coupon_name = coupon_name;
		this.difu = difu;
		this.shifu = shifu;
		this.userid = userid;
		this.buyname = buyname;
		this.buytel = buytel;
		this.inputtime = inputtime;
		this.id = id;
		this.trade_status = trade_status;
		this.house_title = house_title;
	}
	public YHQ() {
		super();
	}
	
	
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getHouse_id() {
		return house_id;
	}
	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}
	public String getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getCoupon_name() {
		return coupon_name;
	}
	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}
	public String getDifu() {
		return difu;
	}
	public void setDifu(String difu) {
		this.difu = difu;
	}
	public String getShifu() {
		return shifu;
	}
	public void setShifu(String shifu) {
		this.shifu = shifu;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getBuyname() {
		return buyname;
	}
	public void setBuyname(String buyname) {
		this.buyname = buyname;
	}
	public String getBuytel() {
		return buytel;
	}
	public void setBuytel(String buytel) {
		this.buytel = buytel;
	}
	public String getInputtime() {
		return inputtime;
	}
	public void setInputtime(String inputtime) {
		this.inputtime = inputtime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTrade_status() {
		return trade_status;
	}
	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}
	public String getHouse_title() {
		return house_title;
	}
	public void setHouse_title(String house_title) {
		this.house_title = house_title;
	}
	@Override
	public String toString() {
		return "YHQ [order_no=" + order_no + ", house_id=" + house_id + ", coupon_id=" + coupon_id + ", coupon_name="
				+ coupon_name + ", difu=" + difu + ", shifu=" + shifu + ", userid=" + userid + ", buyname=" + buyname
				+ ", buytel=" + buytel + ", inputtime=" + inputtime + ", id=" + id + ", trade_status=" + trade_status
				+ ", house_title=" + house_title + ", pay_status=" + pay_status + "]";
	}

	
    
    
    
    
}
