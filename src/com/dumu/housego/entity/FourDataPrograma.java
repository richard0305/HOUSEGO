package com.dumu.housego.entity;

public class FourDataPrograma {
	private String catid; //选择数据类型，必填
	private String page;//页数，第一页20条数据
	private String ct;//主区域 7=宝安区
	private String ar;//子区域15=水库
	private String zj;//总价50-100W
	private String shi;// 室
	private String zx; //装修类型
	private String yt;  //类型
	private String wy;  //属性
	private String xq;  //是否小区
	private String order; //排序
	private String kwds;  //关键词
	private String mj;   //面积
	private String qs;    //物业
	private String ly;   //来源
	private String dt;   //地铁一号线
	private String cx;   //朝向
	private String lc;   //楼层
	private String zl;   //出租类型
	private String kf;  //是否随时看房
	private String fs;  //转让方式
	private String lx; //类型
	private String sx;  //属性
	public FourDataPrograma(String catid, String page, String ct, String ar, String zj, String shi, String zx,
			String yt, String wy, String xq, String order, String kwds, String mj, String qs, String ly, String dt,
			String cx, String lc, String zl, String kf, String fs, String lx, String sx) {
		super();
		this.catid = catid;
		this.page = page;
		this.ct = ct;
		this.ar = ar;
		this.zj = zj;
		this.shi = shi;
		this.zx = zx;
		this.yt = yt;
		this.wy = wy;
		this.xq = xq;
		this.order = order;
		this.kwds = kwds;
		this.mj = mj;
		this.qs = qs;
		this.ly = ly;
		this.dt = dt;
		this.cx = cx;
		this.lc = lc;
		this.zl = zl;
		this.kf = kf;
		this.fs = fs;
		this.lx = lx;
		this.sx = sx;
	}
	public FourDataPrograma() {
		super();
	}
	public String getCatid() {
		return catid;
	}
	public void setCatid(String catid) {
		this.catid = catid;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	public String getAr() {
		return ar;
	}
	public void setAr(String ar) {
		this.ar = ar;
	}
	public String getZj() {
		return zj;
	}
	public void setZj(String zj) {
		this.zj = zj;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getZx() {
		return zx;
	}
	public void setZx(String zx) {
		this.zx = zx;
	}
	public String getYt() {
		return yt;
	}
	public void setYt(String yt) {
		this.yt = yt;
	}
	public String getWy() {
		return wy;
	}
	public void setWy(String wy) {
		this.wy = wy;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getKwds() {
		return kwds;
	}
	public void setKwds(String kwds) {
		this.kwds = kwds;
	}
	public String getMj() {
		return mj;
	}
	public void setMj(String mj) {
		this.mj = mj;
	}
	public String getQs() {
		return qs;
	}
	public void setQs(String qs) {
		this.qs = qs;
	}
	public String getLy() {
		return ly;
	}
	public void setLy(String ly) {
		this.ly = ly;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getCx() {
		return cx;
	}
	public void setCx(String cx) {
		this.cx = cx;
	}
	public String getLc() {
		return lc;
	}
	public void setLc(String lc) {
		this.lc = lc;
	}
	public String getZl() {
		return zl;
	}
	public void setZl(String zl) {
		this.zl = zl;
	}
	public String getKf() {
		return kf;
	}
	public void setKf(String kf) {
		this.kf = kf;
	}
	public String getFs() {
		return fs;
	}
	public void setFs(String fs) {
		this.fs = fs;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getSx() {
		return sx;
	}
	public void setSx(String sx) {
		this.sx = sx;
	}
	@Override
	public String toString() {
		return "FourDataPrograma [catid=" + catid + ", page=" + page + ", ct=" + ct + ", ar=" + ar + ", zj=" + zj
				+ ", shi=" + shi + ", zx=" + zx + ", yt=" + yt + ", wy=" + wy + ", xq=" + xq + ", order=" + order
				+ ", kwds=" + kwds + ", mj=" + mj + ", qs=" + qs + ", ly=" + ly + ", dt=" + dt + ", cx=" + cx + ", lc="
				+ lc + ", zl=" + zl + ", kf=" + kf + ", fs=" + fs + ", lx=" + lx + ", sx=" + sx + "]";
	}
	
	
	

}
