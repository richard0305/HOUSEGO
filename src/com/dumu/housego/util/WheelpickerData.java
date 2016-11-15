package com.dumu.housego.util;

import java.util.ArrayList;
import java.util.List;

public class WheelpickerData {
	// 楼层
	public static List<String> LOUCENG1 = new ArrayList<String>();
	public static List<String> LOUCENG2 = new ArrayList<String>();
	static {
		for (int i = -3; i < 104; i++) {
			String atlouceng = i + "层";
			LOUCENG1.add(atlouceng);
		}
		for (int i = 0; i < 104; i++) {
			String atlouceng1 = "共" + i + "层";
			LOUCENG2.add(atlouceng1);
		}
	}

	// 物业性质
	public static List<String> WUYETYPE = new ArrayList<String>();
	static {
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

	// 楼层属性
	public static List<String> LOUCENGMenu = new ArrayList<String>();
	static {
		LOUCENGMenu.add("底层");
		LOUCENGMenu.add("中层");
		LOUCENGMenu.add("高层");
	}
	// 抵押信息
	public static List<String> DIYAXINXI = new ArrayList<String>();
	static {
		DIYAXINXI.add("无抵押");
		DIYAXINXI.add("有抵押");
		DIYAXINXI.add("暂无数据");
	}

	// 户型
	public static List<String> HUTYPE1 = new ArrayList<String>();
	public static List<String> HUTYPE2 = new ArrayList<String>();
	public static List<String> HUTYPE3 = new ArrayList<String>();
	static {
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
	// 房屋属性
	public static List<String> HOUSESX1 = new ArrayList<String>();
	public static List<String> HOUSESX2 = new ArrayList<String>();
	public static List<String> HOUSESX3 = new ArrayList<String>();
	static {
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
	// 建筑类型
	public static List<String> JIANZHUTYPE = new ArrayList<String>();
	static {
		JIANZHUTYPE.add("板塔结合");
	}
	// 建筑结构
	public static List<String> JIANZHUJIEGOU = new ArrayList<String>();
	static {
		JIANZHUJIEGOU.add("钢混结构");
	}
	// 梯户比例
	public static List<String> TIHUBILI = new ArrayList<String>();
	static {
		TIHUBILI.add("三梯六户");
		TIHUBILI.add("三梯八户");
		TIHUBILI.add("三梯九户");
		TIHUBILI.add("两梯三户");
	}
	// 房屋用途
	public static List<String> HOUSEUSE = new ArrayList<String>();
	static {
		HOUSEUSE.add("住宅");
		HOUSEUSE.add("公寓");
		HOUSEUSE.add("写字楼");
		HOUSEUSE.add("商铺");
		HOUSEUSE.add("其他");
	}
	// 产权所属
	public static List<String> CHANQUANSUOSHU = new ArrayList<String>();
	static {
		CHANQUANSUOSHU.add("共有");
		CHANQUANSUOSHU.add("非共有");
	}
	// 是否有电梯
	public static List<String> SFDIANTI = new ArrayList<String>();
	static {
		SFDIANTI.add("有");
		SFDIANTI.add("无");
	}
	// 是否唯一住宅
	public static List<String> WEIYIZHUZHAI = new ArrayList<String>();
	static {
		WEIYIZHUZHAI.add("是");
		WEIYIZHUZHAI.add("否");
	}

	// 标签
	public static String BIAOQIAN1 = "全明格局";
	public static String BIAOQIAN2 = "全景落地窗";
	public static String BIAOQIAN3 = "厨卫不对门";
	public static String BIAOQIAN4 = "户型方正";
	public static String BIAOQIAN5 = "主卧带卫";
	public static String BIAOQIAN6 = "车位车库";
	// 地铁线
	public static String DITIELINE1 = "1号线(罗宝线)";
	public static String DITIELINE2 = "2号线(蛇口线)";
	public static String DITIELINE3 = "3号线(龙岗线)";
	public static String DITIELINE4 = "4号线(龙华线)";
	public static String DITIELINE5 = "5号线(环中线)";
	public static String DITIELINE6 = "11号线";
	
	// 出租方式
	public static List<String> CHUZUTYPE = new ArrayList<String>();
	static {
		CHUZUTYPE.add("整租");
		CHUZUTYPE.add("合租");
		CHUZUTYPE.add("--");
	}
	// 出租方式
	public static List<String> ZHIFUTYPE = new ArrayList<String>();
	static {
		ZHIFUTYPE.add("付一压一");
		ZHIFUTYPE.add("付一压二");
		ZHIFUTYPE.add("付一压三");
		ZHIFUTYPE.add("付三压一");
		ZHIFUTYPE.add("其他");
	}
	// 发布方式
	public static List<String> FABUFANGSHI = new ArrayList<String>();
	static {
		FABUFANGSHI.add("自售");
		FABUFANGSHI.add("委托给经纪人");
		FABUFANGSHI.add("委托给平台");
	}
	// 是否隐藏手机号码
		public static List<String> YINCANGPHONE = new ArrayList<String>();
		static {
			YINCANGPHONE.add("公开");
			YINCANGPHONE.add("保密");
		}
}
