package com.dumu.housego.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.ErShouFangDetails;
import com.dumu.housego.entity.NewHouseDetail;

public class NewHouseDetialJSONParse {

	public static NewHouseDetail parseSearch(String json) throws JSONException {
		JSONObject j = new JSONObject(json);
		NewHouseDetail n = new NewHouseDetail();

		n.setId(j.getString("id"));
		n.setCatid(j.getString("catid"));
		n.setPosid(j.getString("posid"));
		n.setTitle(j.getString("title"));

		n.setArea(j.getString("area"));
		n.setAreaname(j.getString("areaname"));
		n.setBianhao(j.getString("bianhao"));
		n.setChanquannianxian(j.getString("chanquannianxian"));
		n.setCity(j.getString("city"));
		n.setCityname(j.getString("cityname"));
		n.setContacttel(j.getString("contacttel"));
		n.setDafen_hj(j.getString("dafen_hj"));
		n.setDafen_jt(j.getString("dafen_jt"));
		n.setDafen_zbpt(j.getString("dafen_zbpt"));
		n.setDayviews(j.getString("dayviews"));
		n.setDescription(j.getString("description"));
		n.setDianping(j.getString("dianping"));
		n.setDituzuobiao(j.getString("dituzuobiao"));
		n.setFabulaiyuan(j.getString("fabulaiyuan"));
		n.setFangwuyongtu(j.getString("fangwuyongtu"));
		n.setGuihuachewei(j.getString("guihuachewei"));
		n.setGuihuahushu(j.getString("guihuahushu"));
		n.setHasyhq(j.getString("hasyhq"));
		n.setHighzongjia(j.getString("highzongjia"));
		n.setInputtime(j.getString("inputtime"));
		n.setIslink(j.getString("islink"));
		n.setJianzhuleixing(j.getString("jianzhuleixing"));
		n.setJianzhumianji(j.getString("jianzhumianji"));
		n.setJiaofangdate(j.getString("jiaofangdate"));
		n.setJingweidu(j.getString("jingweidu"));
		n.setJunjia(j.getString("junjia"));
		n.setKaifashang(j.getString("kaifashang"));
		n.setKaipandate(j.getString("kaipandate"));
		n.setKeywords(j.getString("keywords"));
		n.setListorder(j.getString("listorder"));
		n.setLoupandizhi(j.getString("loupandizhi"));
		n.setLoupandongtai(j.getString("loupandongtai"));
		n.setLoupantupian(j.getString("loupantupian"));
		n.setLowzongjia(j.getString("lowzongjia"));
		n.setLvhualv(j.getString("lvhualv"));
		n.setMianjiarea(j.getString("mianjiarea"));
		n.setMonthviews(j.getString("monthviews"));
		n.setPosid(j.getString("posid"));
		n.setProvince(j.getString("province"));
		n.setRongjilv(j.getString("rongjilv"));
		n.setShiarea(j.getString("shiarea"));
		n.setShijingtu(j.getString("shijingtu"));
		n.setShouloudizhi(j.getString("shouloudizhi"));
		n.setShuidianranqi(j.getString("shuidianranqi"));
		n.setStatus(j.getString("status"));
		n.setStyle(j.getString("style"));
		n.setSysadd(j.getString("sysadd"));
		n.setTags(j.getString("tags"));
		n.setTypeid(j.getString("typeid"));
		n.setThumb(j.getString("thumb"));
		n.setUpdatetime(j.getString("updatetime"));
		n.setUrl(j.getString("url"));
		n.setUsername(j.getString("username"));
		n.setViews(j.getString("views"));
		n.setViewsupdatetime(j.getString("viewsupdatetime"));
		n.setWeekviews(j.getString("weekviews"));
		n.setWeizhitu(j.getString("weizhitu"));
		n.setWuyefei(j.getString("wuyefei"));
		n.setWuyegongsi(j.getString("wuyegongsi"));
		n.setWuyeleixing(j.getString("wuyeleixing"));
		n.setXiaoqu(j.getString("xiaoqu"));
		n.setXiaoqutu(j.getString("xiaoqutu"));
		n.setXiaoqutype(j.getString("xiaoqutype"));
		n.setYangbantu(j.getString("yangbantu"));
		n.setYesterdayviews(j.getString("yesterdayviews"));
		n.setYhq_enddate(j.getString("yhq_enddate"));
		n.setYhquan(j.getString("yhquan"));
		n.setZaishou(j.getString("zaishou"));
		n.setZhandimianji(j.getString("zhandimianji"));
		n.setZhuangxiu(j.getString("zhuangxiu"));
		n.setZhulihuxing(j.getString("zhulihuxing"));
		n.setZongjiarange(j.getString("zongjiarange"));
		n.setZuobiaodizhi(j.getString("zuobiaodizhi"));

		return n;

	}

}
