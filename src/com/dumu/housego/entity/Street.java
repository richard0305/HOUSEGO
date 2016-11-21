package com.dumu.housego.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Street implements Serializable{
	private String id;
	private double latitude;
	private double longitude;
	private String name;
	public Street(String id,double latitude, double longitude, String name) {
		super();
		this.id=id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		
	}
	public Street() {
		super();
	}
	
	public String  getId(){
		return id;
	}
	public void setId(String  id) {
		this.id = id;
	}
	
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	@Override
	public String toString() {
		return "Street [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", name=" + name + "]";
	}



	public static List<Street> streets = new ArrayList<Street>();
	static {
		streets.add(new Street("69",22.719931, 114.241984, "龙岗中心城"));
		streets.add(new Street("33",22.54433, 114.068631, "福田中心"));
		streets.add(new Street("65",22.635423, 114.133659, "布吉"));
		streets.add(new Street("45",22.547214, 113.985865, "华侨城"));
		streets.add(new Street("38",22.561803, 114.068676, "莲花"));
		streets.add(new Street("32",22.51244900364, 114.05740789396, "保税区"));
		streets.add(new Street("48",22.552238, 113.931872, "南头"));
		streets.add(new Street("52",22.489944, 113.919881, "蛇口"));
		streets.add(new Street("40",22.55784, 114.050261, "景田"));
		streets.add(new Street("51",22.522585,113.902236, "前海"));
		streets.add(new Street("66",22.656642,114.206821, "横岗"));
		streets.add(new Street("30",22.575051,114.047817, "梅林"));
		streets.add(new Street("37",22.539264,114.034639, "车公庙"));
		streets.add(new Street("35",22.553766,114.033166, "香蜜湖"));
		streets.add(new Street("50",22.520152,113.92576, "南山中心"));
		streets.add(new Street("17",22.571392,114.139723, "翠竹"));
		streets.add(new Street("46",22.516238,113.939118, "后海"));
		streets.add(new Street("29",22.529288,114.042469, "上下沙"));
		streets.add(new Street("54",22.56503410211,114.23296227279, "沙头角"));
		streets.add(new Street("34",22.550560856132,114.09093837029, "华强"));
		streets.add(new Street("57",22.562266,113.89565, "宝安中心"));
		streets.add(new Street("36",22.527719,114.074628, "皇岗"));
		streets.add(new Street("64",22.629117454088,114.07449072316, "坂田"));
		streets.add(new Street("41",22.689232,114.132299, "平湖"));
		streets.add(new Street("62",22.600191,113.858245, "西乡"));
		streets.add(new Street("53",22.57735,113.956204, "西丽"));
		streets.add(new Street("16",22.568807,114.179631, "莲塘"));
		streets.add(new Street("70",22.657455,114.032227, "龙华中心"));
		streets.add(new Street("58",22.686748,113.819329, "福永"));
		streets.add(new Street("18",22.55442,114.128569, "东门"));
		streets.add(new Street("63",22.580537,113.914223, "新安"));
		streets.add(new Street("47",22.549232,113.954273, "科技园"));
		streets.add(new Street("31",22.530106,114.050679, "新洲"));
		streets.add(new Street("68",22.765347,114.311051, "坪地"));
		streets.add(new Street("72",22.622584720476,114.04786064158, "民治"));
		streets.add(new Street("43",22.549996,114.01132, "竹子林"));
		streets.add(new Street("55",22.597845,114.270382, "盐田港"));
		streets.add(new Street("28",22.567395,114.105686, "八卦岭"));
		streets.add(new Street("27",22.584577207172,114.09261483899, "银湖"));
		streets.add(new Street("23",22.588163,114.131581, "布心"));
		streets.add(new Street("73",22.711183,114.062906, "观澜"));
		streets.add(new Street("21",22.571544,114.128059, "洪湖"));
		streets.add(new Street("44",22.568307,114.087294, "笔架山"));
		streets.add(new Street("34",22.541939474418,114.08882175458, "华强南"));
		streets.add(new Street("71",22.685307,114.012792, "大浪"));
		streets.add(new Street("22",22.571083,114.118472, "笋岗"));
		streets.add(new Street("74",22.757803,113.923689, "公明"));
		streets.add(new Street("76",22.70512,114.355493, "坪山"));
		streets.add(new Street("15",22.577933,114.14636, "水库"));
		streets.add(new Street("19",22.559741,114.145866, "黄贝岭"));
		streets.add(new Street("56",22.603342,114.31291, "梅沙"));
		streets.add(new Street("78",22.631135,114.479045, "大鹏"));
		streets.add(new Street("60",22.791961262185,113.84573771383, "松岗"));
		streets.add(new Street("61",22.736955,113.813831, "沙井"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
