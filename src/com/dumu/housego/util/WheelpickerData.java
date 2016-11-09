package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

public class WheelpickerData {
	//楼层
	public static List<String>LOUCENG1=new ArrayList<String>();
	public static List<String>LOUCENG2=new ArrayList<String>();
	static
	{
		for (int i = -3; i < 104; i++) {
			String atlouceng=i+"层";
			LOUCENG1.add(atlouceng);
		}
		for (int i = 0; i < 104; i++) {
			String atlouceng1="共"+i+"层";
			LOUCENG2.add(atlouceng1);
		}
	}
	
	//物业性质
	public static List<String> WUYETYPE=new ArrayList<String>();
	static
	{
		WUYETYPE.add("商品房");
		WUYETYPE.add("村委统建");
		WUYETYPE.add("开发商建设");
		WUYETYPE.add("个人自建房");
		WUYETYPE.add("广东省军区军产房");
		WUYETYPE.add("武警部队军产房");
		WUYETYPE.add("工业长租房");
		WUYETYPE.add("工业产权房");
		WUYETYPE.add("其他");
	}
	
	//楼层属性
	public static List<String>LOUCENGMenu=new ArrayList<String>();
	static
	{
		LOUCENGMenu.add("底层");
		LOUCENGMenu.add("中层");
		LOUCENGMenu.add("高层");
	}
	//抵押信息
	public static List<String>DIYAXINXI=new ArrayList<String>();
	static
	{
		DIYAXINXI.add("无抵押");
		DIYAXINXI.add("有抵押");
		DIYAXINXI.add("暂无数据");
	}
	
	//户型
	public static List<String>HUTYPE1=new ArrayList<String>();
	public static List<String>HUTYPE2=new ArrayList<String>();
	public static List<String>HUTYPE3=new ArrayList<String>();
	static
	{
		HUTYPE1.add("1室");
		HUTYPE1.add("2室");
		HUTYPE1.add("3室");
		HUTYPE1.add("4室");
		HUTYPE1.add("5室");
		HUTYPE1.add("5室以上");
		HUTYPE2.add("0厅");
		HUTYPE2.add("1厅");
		HUTYPE2.add("2厅");
		HUTYPE2.add("3厅");
		HUTYPE2.add("3厅以上");
		HUTYPE3.add("1卫");
		HUTYPE3.add("2卫");
		HUTYPE3.add("3卫");
		HUTYPE3.add("3卫以上");
	}
	//房屋属性
	public static List<String>HOUSESX1=new ArrayList<String>();
	public static List<String>HOUSESX2=new ArrayList<String>();
	public static List<String>HOUSESX3=new ArrayList<String>();
	static
	{
		HOUSESX1.add("平层");
		HOUSESX1.add("复式");
		HOUSESX1.add("跃层");
		HOUSESX1.add("错层");
		HOUSESX1.add("开间");
		HOUSESX2.add("毛坯");
		HOUSESX2.add("简装");
		HOUSESX2.add("精装");
		HOUSESX3.add("南");
		HOUSESX3.add("北");
		HOUSESX3.add("东");
		HOUSESX3.add("西");
		HOUSESX3.add("南北");
	}
	
	
}
