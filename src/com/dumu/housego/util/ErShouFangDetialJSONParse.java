package com.dumu.housego.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.dumu.housego.entity.ErShouFangDetails;

public class ErShouFangDetialJSONParse {
	
	public static ErShouFangDetails parseSearch(String json) throws JSONException {
		JSONObject j = new JSONObject(json); 
		ErShouFangDetails n = new ErShouFangDetails();
			
			n.setId(j.getString("id"));
			n.setCatid(j.getString("catid"));
			n.setPosid(j.getString("posid"));
			n.setTitle(j.getString("title"));
			n.setTing(j.getString("ting"));
			n.setShi(j.getString("shi"));
			n.setThumb(j.getString("thumb"));
//			n.setAllow_comment(j.getString("allow_comment"));
			n.setArea(j.getString("area"));
			n.setAreaname(j.getString("areaname"));
			n.setBianhao(j.getString("bianhao"));
			n.setBiaoqian(j.getString("biaoqian"));
			n.setCeng(j.getString("ceng"));
			n.setChanquansuoshu(j.getString("chanquansuoshu"));
			n.setChaoxiang(j.getString("chaoxiang"));
			n.setChenghu(j.getString("chenghu"));
			n.setChu(j.getString("chu"));
			n.setCity(j.getString("city"));
			n.setCityname(j.getString("cityname"));
//			n.setContent(j.getString("content"));
			n.setContract(j.getString("contract"));
			n.setCurceng(j.getString("curceng"));
			n.setDayviews(j.getString("dayviews"));
			n.setDesc(j.getString("desc"));
			n.setDescription(j.getString("description"));
			n.setDianti(j.getString("dianti"));
			n.setDitiexian(j.getString("ditiexian"));
			n.setDituzuobiao(j.getString("dituzuobiao"));
			n.setDiyaxinxi(j.getString("diyaxinxi"));
			n.setFangling(j.getString("fangling"));
			n.setFangwuyongtu(j.getString("fangwuyongtu"));
			n.setFenpei_time(j.getString("fenpei_time"));
			n.setGuapaidate(j.getString("guapaidate"));
			n.setHexinmaidian(j.getString("hexinmaidian"));
			n.setHidetel(j.getString("hidetel"));
			n.setHuxing(j.getString("huxing"));
			n.setHuxingintro(j.getString("huxingintro"));
			n.setIdcard(j.getString("idcard"));
			n.setInputtime(j.getString("inputtime"));
			n.setIslink(j.getString("islink"));
			n.setIsweiyi(j.getString("isweiyi"));
			n.setJianzhujiegou(j.getString("jianzhujiegou"));
			n.setJianzhumianji(j.getString("jianzhumianji"));
			n.setJianzhutype(j.getString("jianzhutype"));
			n.setJiaotong(j.getString("jiaotong"));
			n.setJiaoyiquanshu(j.getString("jiaoyiquanshu"));
			n.setJiegou(j.getString("jiegou"));
			n.setJingweidu(j.getString("jingweidu"));
			n.setJjr_id(j.getString("jjr_id"));
			n.setKeywords(j.getString("keywords"));
			n.setListorder(j.getString("listorder"));
			n.setLock(j.getString("lock"));
			n.setLouceng(j.getString("louceng"));
			n.setLoudong(j.getString("loudong"));
//			n.setMaxcharperpage(j.getString("maxcharperpage"));
			n.setMenpai(j.getString("menpai"));
			n.setMonthviews(j.getString("monthviews"));
//			n.setPaginationtype(j.getString("paginationtype"));
//			n.setPaytype(j.getString("paytype"));
			n.setPics(j.getString("pics"));
			n.setPosid(j.getString("posid"));
			n.setProvince(j.getString("province"));
			n.setPub_type(j.getString("pub_type"));
			n.setQuanshudiya(j.getString("quanshudiya"));
//			n.setRelation(j.getString("relation"));
			n.setShangcijiaoyi(j.getString("shangcijiaoyi"));
			n.setShenghuopeitao(j.getString("shenghuopeitao"));
			n.setShuifeijiexi(j.getString("shuifeijiexi"));
			n.setShuxing(j.getString("shuxing"));
			n.setStatus(j.getString("status"));
			n.setStyle(j.getString("style"));
			n.setSysadd(j.getString("sysadd"));
			n.setTags(j.getString("tags"));
			n.setTaoneimianji(j.getString("taoneimianji"));
//			n.setTemplate(j.getString("template"));
			n.setTihubili(j.getString("tihubili"));
			n.setTouzifenxi(j.getString("touzifenxi"));
			n.setTypeid(j.getString("typeid"));
			n.setUpdatetime(j.getString("updatetime"));
			n.setUrl(j.getString("url"));
			n.setUsername(j.getString("username"));
			n.setViews(j.getString("views"));
			n.setViewsupdatetime(j.getString("viewsupdatetime"));
			n.setWeekviews(j.getString("weekviews"));
			n.setWei(j.getString("wei"));
			n.setXiaoqu(j.getString("xiaoqu"));
			n.setXiaoquintro(j.getString("xiaoquintro"));
//			n.setXiaoquName(j.getString("xiaoquName"));
			n.setXiaoquyoushi(j.getString("xiaoquyoushi"));
			n.setXuexiaomingcheng(j.getString("xuexiaomingcheng"));
			n.setYangtai(j.getString("yangtai"));
			n.setYesterdayviews(j.getString("yesterdayviews"));
			n.setYezhushuo(j.getString("yezhushuo"));
			n.setZaishou(j.getString("zaishou"));
			n.setZhuangxiu(j.getString("zhuangxiu"));
			n.setZongceng(j.getString("zongceng"));
			n.setZongjia(j.getString("zongjia"));
			n.setZuobiaodizhi(j.getString("zuobiaodizhi"));
			n.setZxdesc(j.getString("zxdesc"));
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		return n;

	}

}