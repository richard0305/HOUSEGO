package com.dumu.housego;

import java.util.ArrayList;
import java.util.List;

import com.dumu.housego.adapter.ErShouFangRecommendAdapter;
import com.dumu.housego.entity.ErShouFangRecommendData;
import com.dumu.housego.entity.FourDataPrograma;
import com.dumu.housego.presenter.ErShouFangProgramaPresenter;
import com.dumu.housego.presenter.IFourDataProgramePresenter;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.utils.MyListener;
import com.dumu.housego.utils.PullToRefreshLayout;
import com.dumu.housego.utils.PullToRefreshLayout.OnRefreshListener;
import com.dumu.housego.view.IErShouFangRecommendView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

public class ErShouFangMainActivity extends Activity implements IErShouFangRecommendView {
	private LinearLayout llErshoufang;
	private ErShouFangRecommendAdapter adapter;
	private IFourDataProgramePresenter presenter;
	private List<ErShouFangRecommendData> ershoufangrecommends;
	private FourDataPrograma fourdata;
	private Spinner ershoufangQuyuSp1;
	private Spinner ershoufangQuyuSp2;
	private Spinner ershoufangQuyuSp3;
	private Spinner ershoufangQuyuSp4;

	private List<String> spinnerList1 = new ArrayList<String>();
	private List<String> spinnerList2 = new ArrayList<String>();
	private List<String> spinnerList3 = new ArrayList<String>();
	private List<String> spinnerList4 = new ArrayList<String>();
	private ArrayAdapter<String> Spinneradapter;    
	
	private ListView listView;
	private PullToRefreshLayout ptrl;
	private boolean isFirstIn = true;

	private int page=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_er_shou_fang_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListener();
		
		spinnerList1.add("区域");    
		spinnerList1.add("罗湖");    
		spinnerList1.add("福山");    
		spinnerList1.add("南山");    
		spinnerList1.add("龙岗");  
		
		
		
		
		presenter = new ErShouFangProgramaPresenter(this);

		fourdata = new FourDataPrograma();
		fourdata.setCatid("6");
		fourdata.setPage("1");
		presenter.LoadProgrameData(fourdata);
		Spinneradapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerList1); 
		Spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
		ershoufangQuyuSp1.setAdapter(Spinneradapter); 
		String str=(String) ershoufangQuyuSp1.getSelectedItem();
	}
	
	
//	首次进入，刷新
//	@Override
//	public void onWindowFocusChanged(boolean hasFocus)
//	{
//		super.onWindowFocusChanged(hasFocus);
//		// ��һ�ν����Զ�ˢ��
//		if (isFirstIn)
//		{
//			ptrl.autoRefresh();
//			isFirstIn = false;
//		}
//	}
	

	private void setListener() {
		llErshoufang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ErShouFangMainActivity.this.finish();

			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			 @Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				//
				 Intent i = new Intent(ErShouFangMainActivity.this,ErShouFangDetailsActivity.class);
				 String catid=ershoufangrecommends.get(position).getCatid();
				 String ID=ershoufangrecommends.get(position).getId();
				 i.putExtra("catid", catid);
				 i.putExtra("id", ID);
				 startActivity(i);
			}
		});
		
		ptrl.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
				page=page+1;
				fourdata.setCatid("6");
				fourdata.setPage(page+"");
				presenter.LoadProgrameData(fourdata);
				adapter.notifyDataSetChanged();
				
			}
			
			@Override
			public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
				
				
			}
		});

	}

	private void setViews() {
		ptrl = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
		ptrl.setOnRefreshListener(new MyListener());
		listView = (ListView) findViewById(R.id.content_view);
		
		llErshoufang = (LinearLayout) findViewById(R.id.ll_ershoufang_back);
		ershoufangQuyuSp1=(Spinner) findViewById(R.id.ershoufang_quyu_sp1);
		ershoufangQuyuSp2=(Spinner) findViewById(R.id.ershoufang_quyu_sp2);
		ershoufangQuyuSp3=(Spinner) findViewById(R.id.ershoufang_quyu_sp3);
		ershoufangQuyuSp4=(Spinner) findViewById(R.id.ershoufang_quyu_sp4);
	}

	@Override
	public void showData(List<ErShouFangRecommendData> ershoufangrecommends) {
		this.ershoufangrecommends = ershoufangrecommends;
		Log.e("2016-10-10", "2016-10-10"+ershoufangrecommends);
		adapter = new ErShouFangRecommendAdapter(ershoufangrecommends, getApplicationContext());
		listView.setAdapter(adapter);

	}

}
