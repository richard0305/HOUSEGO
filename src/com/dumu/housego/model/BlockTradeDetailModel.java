package com.dumu.housego.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.BlockTradeDetail;
import com.dumu.housego.entity.RecommendNews;
import com.dumu.housego.util.CommonRequest;
import com.dumu.housego.util.HttpUtils;
import com.dumu.housego.util.JSONParse;
import com.dumu.housego.util.RemoveBOM;
import com.dumu.housego.util.UrlFactory;

import android.os.AsyncTask;
import android.util.Log;

public class BlockTradeDetailModel implements IBlockTradeDetailaModel{
	private BlockTradeDetail b;

	public BlockTradeDetailModel() {
		super();
	}

	@Override
	public void FindBlockTradeDetail(final String catid, final String id, final AsycnCallBack back) {
		
		new AsyncTask<String, String, BlockTradeDetail>() {

			@Override
			protected BlockTradeDetail doInBackground(String... params) {

				try {
					String url = UrlFactory.GetRecommendListToDetailUrl1(catid, id);
					b=new BlockTradeDetail();
					Document doc=Jsoup.connect(url).get();
					b.setTitle(doc.getElementsByClass("house_title").text());
					b.setGoudijine(doc.getElementsByClass("total_price").text().replaceAll("\\D+", ""));
					b.setThumb(doc.getElementsByAttribute("img origin-src").toString());
//					JSONObject obj=new JSONObject(e);
					
//					InputStream is = HttpUtils.get(url);
//					String json = HttpUtils.isToString(is);
//					b=new BlockTradeDetail();
//					JSONObject obj=new JSONObject(json);
					
//					
//					
//					b.setTypeid(obj.getString("typeid"));
//					b.setTitle(obj.getString("title"));
//					b.setStyle(obj.getString("style"));
//					b.setThumb(obj.getString("thumb"));
//					b.setKeywords(obj.getString("keywords"));
//					b.setTags(obj.getString("tags"));
//					b.setDescription(obj.getString("description"));
//					b.setPosid(obj.getString("posid"));
//					b.setUrl(obj.getString("url"));
//					b.setListorder(obj.getString("listorder"));
//					b.setStatus(obj.getString("status"));
//					b.setSysadd(obj.getString("sysadd"));
//					b.setIslink(obj.getString("islink"));
//					b.setUsername(obj.getString("username"));
//					b.setInputtime(obj.getString("inputtime"));
//					b.setUpdatetime(obj.getString("updatetime"));
//					b.setViews(obj.getString("views"));
//					b.setYesterdayviews(obj.getString("yesterdayviews"));
//					b.setDayviews(obj.getString("dayviews"));
//					b.setWeekviews(obj.getString("weekviews"));
//					b.setMonthviews(obj.getString("monthviews"));
//					b.setViewsupdatetime(obj.getString("viewsupdatetime"));
//					b.setAddress(obj.getString("address"));
//					b.setCity(obj.getString("city"));
//					b.setArea(obj.getString("area"));
//					b.setProvince(obj.getString("province"));
//					b.setJingweidu(obj.getString("jingweidu"));
//					b.setZuobiaodizhi(obj.getString("zuobiaodizhi"));
//					b.setDituzuobiao(obj.getString("dituzuobiao"));
//					b.setDitiexian(obj.getString("ditiexian"));
//					b.setZongjia(obj.getString("zongjia"));
//					b.setZhandimianji(obj.getString("zhandimianji"));
//					b.setContactname(obj.getString("contactname"));
//					b.setBianhao(obj.getString("bianhao"));
//					b.setWuyetype(obj.getString("wuyetype"));
//					b.setHezuofangshi(obj.getString("hezuofangshi"));
//					b.setShiyongnianxian(obj.getString("shiyongnianxian"));
//					b.setGoudijine(obj.getString("goudijine"));
//					b.setTel(obj.getString("tel"));
//					b.setHasgd(obj.getString("hasgd"));
//					b.setGdinfo(obj.getString("gdinfo"));
//					b.setContent(obj.getString("content"));
//					b.setPaginationtype(obj.getString("paginationtype"));
//					b.setMaxcharperpage(obj.getString("maxcharperpage"));
//					b.setTemplate(obj.getString("template"));
//					b.setPaytype(obj.getString("paytype"));
//					b.setAllow_comment(obj.getString("allow_comment"));
//					b.setRelation(obj.getString("relation"));
//					b.setXuzhi(obj.getString("xuzhi"));
//					b.setRenzheng(obj.getString("renzheng"));
//					b.setCityname(obj.getString("cityname"));
//					b.setAreaname(obj.getString("areaname"));
					
					Log.i("----------------", "0000120120121212================="+b);
					back.onSuccess(b);
					return b;
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(BlockTradeDetail b) {
				back.onSuccess(b);
			}
		}.execute();
		
//		final String url=UrlFactory.GetRecommendListToDetailUrl(catid, id);
//		
//		Log.i("--==-----===-=-=--=21122", "-------------------------"+url);
//		CommonRequest request=new CommonRequest(Request.Method.GET,url, new Listener<String>() {
//
//			
//			@Override
//			public void onResponse(String response) {
//				try {
//					Document doc=Jsoup.connect(url).get();
//					String e=doc.body().text();
//					
////					String data=RemoveBOM.removeBOM(response);
//					Log.i("--==-----===-=-=--=21122", "-------------------------"+e);
//					JSONObject obj=new JSONObject(e);
//					b=new BlockTradeDetail();
//					b.setTypeid(obj.getString("typeid"));
//					b.setTitle(obj.getString("title"));
//					b.setStyle(obj.getString("style"));
//					b.setThumb(obj.getString("thumb"));
//					b.setKeywords(obj.getString("keywords"));
//					b.setTags(obj.getString("tags"));
//					b.setDescription(obj.getString("description"));
//					b.setPosid(obj.getString("posid"));
//					b.setUrl(obj.getString("url"));
//					b.setListorder(obj.getString("listorder"));
//					b.setStatus(obj.getString("status"));
//					b.setSysadd(obj.getString("sysadd"));
//					b.setIslink(obj.getString("islink"));
//					b.setUsername(obj.getString("username"));
//					b.setInputtime(obj.getString("inputtime"));
//					b.setUpdatetime(obj.getString("updatetime"));
//					b.setViews(obj.getString("views"));
//					b.setYesterdayviews(obj.getString("yesterdayviews"));
//					b.setDayviews(obj.getString("dayviews"));
//					b.setWeekviews(obj.getString("weekviews"));
//					b.setMonthviews(obj.getString("monthviews"));
//					b.setViewsupdatetime(obj.getString("viewsupdatetime"));
//					b.setAddress(obj.getString("address"));
//					b.setCity(obj.getString("city"));
//					b.setArea(obj.getString("area"));
//					b.setProvince(obj.getString("province"));
//					b.setJingweidu(obj.getString("jingweidu"));
//					b.setZuobiaodizhi(obj.getString("zuobiaodizhi"));
//					b.setDituzuobiao(obj.getString("dituzuobiao"));
//					b.setDitiexian(obj.getString("ditiexian"));
//					b.setZongjia(obj.getString("zongjia"));
//					b.setZhandimianji(obj.getString("zhandimianji"));
//					b.setContactname(obj.getString("contactname"));
//					b.setBianhao(obj.getString("bianhao"));
//					b.setWuyetype(obj.getString("wuyetype"));
//					b.setHezuofangshi(obj.getString("hezuofangshi"));
//					b.setShiyongnianxian(obj.getString("shiyongnianxian"));
//					b.setGoudijine(obj.getString("goudijine"));
//					b.setTel(obj.getString("tel"));
//					b.setHasgd(obj.getString("hasgd"));
//					b.setGdinfo(obj.getString("gdinfo"));
//					b.setContent(obj.getString("content"));
//					b.setPaginationtype(obj.getString("paginationtype"));
//					b.setMaxcharperpage(obj.getString("maxcharperpage"));
//					b.setTemplate(obj.getString("template"));
//					b.setPaytype(obj.getString("paytype"));
//					b.setAllow_comment(obj.getString("allow_comment"));
//					b.setRelation(obj.getString("relation"));
//					b.setXuzhi(obj.getString("xuzhi"));
//					b.setRenzheng(obj.getString("renzheng"));
//					b.setCityname(obj.getString("cityname"));
//					b.setAreaname(obj.getString("areaname"));
//					
//					Log.i("----------------", "0000120120121212================="+b);
//					
//					back.onSuccess(b);
//					
//					
//					
//					
//				} catch (JSONException e) {
//					
//					e.printStackTrace();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//			}
//		}, new ErrorListener() {
//			@Override
//			public void onErrorResponse(VolleyError error) {
//			back.onError(error.getMessage());
//				
//			}
//		})
//
//		;
//		HouseGoApp.getQueue().add(request);
//	}
}
}
