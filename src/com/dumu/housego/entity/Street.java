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
		//
		streets.add(new Street("117",23.17886924743652,114.296272277832, "博罗县"));
		streets.add(new Street("119",22.78518676757812,114.6927947998047, "大亚湾"));
		streets.add(new Street("114",23.08990478515625,114.3892974853516, "惠城区"));
		streets.add(new Street("116",22.99128150939941,114.7264862060547, "惠东县"));
		streets.add(new Street("115",22.7946834564209,114.4630279541016, "惠阳区"));
		streets.add(new Street("120",23.08209609985352,114.3903427124023, "仲恺"));
		//
		streets.add(new Street("93",22.8210391998291,113.8090362548828, "长安镇"));
		streets.add(new Street("108",23.09723854064941,113.7523574829102, "高埗镇"));
		streets.add(new Street("106",23.05708312988281,113.58837890625, "麻涌镇"));
		streets.add(new Street("93",22.82064819335938,113.6793212890625, "虎门镇"));
		streets.add(new Street("112",23.01024627685547,113.6817169189453, "道滘镇"));
		streets.add(new Street("84",23.05331611633301,113.7437362670898, "万江"));
		streets.add(new Street("90",22.99509429931641,113.9546508789062, "东坑镇"));
		streets.add(new Street("83",23.03434562683105,113.7896881103516, "东城"));
		streets.add(new Street("100",22.92128562927246,114.0084075927734, "黄江镇"));
		streets.add(new Street("107",23.09864234924316,113.663932800293, "中堂镇"));
		streets.add(new Street("88",23.07903671264648,114.0285339355469, "企石镇"));
		streets.add(new Street("105",22.74981689453125,114.1547164916992, "凤岗镇"));
		streets.add(new Street("82",23.02432060241699,113.7506408691406, "南城"));
		streets.add(new Street("96",22.94132232666016,113.6767959594727, "厚街镇"));
		streets.add(new Street("98",22.86803245544434,113.8093566894531, "大岭山镇"));
		streets.add(new Street("99",22.94565773010254,113.9505844116211, "大朗镇"));
		streets.add(new Street("97",23.00370979309082,113.8812713623047, "寮步镇"));
		streets.add(new Street("92",22.98104667663574,113.99951171875, "常平镇"));
		streets.add(new Street("89",23.03007888793945,114.1096038818359, "桥头镇"));
		streets.add(new Street("101",22.92082786560059,114.0897827148438, "樟木头镇"));
		streets.add(new Street("110",23.06159782409668,113.6626358032227, "望牛墩镇"));
		streets.add(new Street("113",22.89933013916016,113.8717041015625, "松山湖"));
		streets.add(new Street("95",22.92527008056641,113.6240844726562, "沙田镇"));
		streets.add(new Street("91",23.02480888366699,113.9729919433594, "横沥镇"));
		streets.add(new Street("104",22.85030746459961,114.1708908081055, "清溪镇"));
//		streets.add(new Street("61",22.54869842529297,114.0659484863281, "深惠"));
		streets.add(new Street("111",23.00064277648926,113.6154403686523, "洪梅镇"));
		streets.add(new Street("85",23.11161422729492,113.880729675293, "石龙镇"));
		streets.add(new Street("109",23.10500717163086,113.8198165893555, "石碣镇"));
		streets.add(new Street("86",23.09485244750977,113.9465484619141, "石排镇"));
		streets.add(new Street("81",23.05971717834473,113.7574920654297, "莞城"));
		streets.add(new Street("87",23.08247756958008,113.875617980957, "茶山镇"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
