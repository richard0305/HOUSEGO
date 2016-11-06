package com.dumu.housego;

import java.io.File;

import org.xutils.x;
import org.xutils.common.util.MD5;

import com.bumptech.glide.Glide;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.activity.MainActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.LoginUserInfoModel;
import com.dumu.housego.presenter.ChangeHeadPhotoPresenter;
import com.dumu.housego.presenter.ChangeUserInfoPresenter;
import com.dumu.housego.presenter.IChangeHeadPhotoPresenter;
import com.dumu.housego.presenter.IChangeUserInfoPresenter;
import com.dumu.housego.util.CircleImageView;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyReboundScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.utils.Utils;
import com.dumu.housego.view.IChangeHeadPhotoView;
import com.dumu.housego.view.IChangeUserInfoView;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalMainActivity extends Activity implements IChangeUserInfoView,IChangeHeadPhotoView{
	
	protected static final int CHOOSE_PICTURE=0;
	protected static final int TAKE_PICTURE=1;
	protected static final int CROP_SMALL_PICTURE=2;
	protected static Uri tempUri;
//	private static final int TAKE_PHOTO_REQUEST_CODE = 1;
	
	private LoginUserInfoModel infomodel=new LoginUserInfoModel();
	private TextView tv_person_fenjihao;
	private IChangeUserInfoPresenter userinfopresenter;
	private Button btn_changefenjihao_shenqing,btn_changefenjihao_jiebang,btn_changepassword_submit;
	private  EditText et_changrealname,et_changegerenjieshao,et_change_currentpassword,et_change_Newpassword,et_change_repassword;
	private LinearLayout llPersonalBack,ll_changerealname_back,ll_changefenjihao_back,
	ll_changegerenjieshao_back,ll_changepassword_back;
	private RelativeLayout rl_person_sex, rlMyTouxiang,rl_person_realname,
	rl_person_fenjihao,rl_person_gerenjieshao,rl_personal_changepassword,rl_personal_phone;
	private TextView tvPersonalPhone,tv_changerealname_save,tv_changegerenjieshao_save,tv_person_sex,tv_fenjihao_show,tvfenjihao_show_1;
	private UserInfo userinfo;
	private CircleImageView ivPersonalPhoto;
	private Bitmap head;//ͷ��Bitmap
//	private	 File file=new File("\\sdcard\\HouseGo\\");
//    private static String path=File.pathSeparator;//sd·��
    private IChangeHeadPhotoPresenter headpresenter;
    private TextView tv_personal_viptype;
    private MyReboundScrollView scrollview;
    
    private LinearLayout llchangerealname;
   private LinearLayout llchangfenjiahao;
    private LinearLayout llchangegerenjieshao;
    private LinearLayout llchangepassword;
    
   private LinearLayout llfenjihaojiebang;
  private LinearLayout llfenjihaoshenqing;
   
  Handler handler=new Handler();
private PopupWindow pop;
private LinearLayout ll_popup;
private LinearLayout ll_cancle;
private View parentView;
private PopupWindow popTouXiang;
private LinearLayout ll_cancle_touxiang;
private LinearLayout ll_popup_touxiang;  
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		parentView=getLayoutInflater().inflate(R.layout.activity_personal_main, null);
		setContentView(parentView);
		FontHelper.injectFont(findViewById(android.R.id.content));
		x.view().inject(this);
		setViews();
		showPopWindow();
		showTouxaingWindow();
		userinfopresenter=new ChangeUserInfoPresenter(this);
		
		setListeners();
		
		headpresenter=new ChangeHeadPhotoPresenter(this);
		
	}
	
	private void showTouxaingWindow() {
popTouXiang = new PopupWindow(PersonalMainActivity.this);
		
		View view = getLayoutInflater().inflate(R.layout.item_popupwindows_touxiang, null);

		ll_popup_touxiang = (LinearLayout) view.findViewById(R.id.ll_popup_touxaing);
		ll_cancle_touxiang=(LinearLayout) view.findViewById(R.id.ll_cancle_touxaing);
		popTouXiang.setWidth(LayoutParams.MATCH_PARENT);
		popTouXiang.setHeight(LayoutParams.WRAP_CONTENT);
		popTouXiang.setBackgroundDrawable(new BitmapDrawable());
		popTouXiang.setFocusable(true);
		popTouXiang.setOutsideTouchable(true);
		popTouXiang.setContentView(view);
		
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_touxaing);
		Button bt1 = (Button) view
				.findViewById(R.id.item_popupwindows_touxaing1);
		Button bt2 = (Button) view
				.findViewById(R.id.item_popupwindows_touxaing2);
		Button bt3 = (Button) view
				.findViewById(R.id.item_popupwindows_touxaing_cancel);
		
		parent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popTouXiang.dismiss();
				ll_popup_touxiang.clearAnimation();
				ll_cancle_touxiang.clearAnimation();
			}
		});
		bt1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//拍照,先判断SD卡是否存在
				String SDState=Environment.getExternalStorageState();
				if(SDState.equals(Environment.MEDIA_MOUNTED)){
					
					Intent openCameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					tempUri=Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"image.jpg"));
//					ContentValues values=new ContentValues();
					
					//指定照片保存路径（SD卡），image.jpg为一个临时文本，每次拍照后这个图片都会被替换
					openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
					startActivityForResult(openCameraIntent, TAKE_PICTURE);
					
				}else{
					MyToastShowCenter.CenterToast(getApplicationContext(), "内存卡不存在!");
				}
				
				popTouXiang.dismiss();
				ll_popup_touxiang.clearAnimation();
				ll_cancle_touxiang.clearAnimation();
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//选择本地照片
				
				Intent openAlbumIntent=new Intent(Intent.ACTION_PICK);
				openAlbumIntent.setType("image/*");
				startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
				
				popTouXiang.dismiss();
				ll_popup_touxiang.clearAnimation();
				ll_cancle_touxiang.clearAnimation();
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				popTouXiang.dismiss();
				ll_popup_touxiang.clearAnimation();
				ll_cancle_touxiang.clearAnimation();
			}
		});
		
	}

	private void showPopWindow() {
pop = new PopupWindow(PersonalMainActivity.this);
		
		View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

		ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
		ll_cancle=(LinearLayout) view.findViewById(R.id.ll_cancle);
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);
		
		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
		Button bt1 = (Button) view
				.findViewById(R.id.item_popupwindows_btn1);
		Button bt2 = (Button) view
				.findViewById(R.id.item_popupwindows_btn2);
		Button bt3 = (Button) view
				.findViewById(R.id.item_popupwindows_cancel);
		
		bt1.setText("男");
		bt2.setText("女");
		parent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		bt1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
//				tv_agent_sex.setText("男");
				
				String userid=userinfo.getUserid();
				String sex="1";
				userinfopresenter.ChangeSex(userid, sex);
				
				onResume();
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
//				tv_agent_sex.setText("女");
				String userid=userinfo.getUserid();
				String sex="2";
				userinfopresenter.ChangeSex(userid, sex);
				onResume();
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		
	}
	
	
	@Override
	protected void onResume() {
		
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		
		if(userinfo==null){
			startActivity(new Intent(this, MainActivity.class));
			
		}else{
			String url=userinfo.getUserpic();
			Glide.with(getApplicationContext()).load(url).into(ivPersonalPhoto);
			tvPersonalPhone.setText(userinfo.getUsername()+"");
				tv_fenjihao_show.setText(userinfo.getUsername());
				tvfenjihao_show_1.setText(userinfo.getVtel());
			
				et_changegerenjieshao.setText(userinfo.getAbout()); 
			
			if(userinfo.getModelid().equals("35")){
				tv_personal_viptype.setText("普通会员");
			}else{
				tv_personal_viptype.setText("经纪人");
			}
			et_changrealname.setText(userinfo.getRealname()+"");
			if(userinfo.getSex().equals("0")){
				tv_person_sex.setText("未知");
			}else if(userinfo.getSex().equals("1")){
				tv_person_sex.setText("男");
			}else{
				tv_person_sex.setText("女");
			}
			tv_person_fenjihao.setText(userinfo.getVtel());
			
		}
		super.onResume();
	}
	
	

	private void setListeners() {
		llPersonalBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PersonalMainActivity.this.finish();

			}
		});
		
//		rlMyTouxiang.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				new AlertDialog.Builder(PersonalMainActivity.this).setTitle("图片来源")
//				.setItems(new String[]{"相册","相机"}, new DialogInterface.OnClickListener() {
//					
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						switch(which){
//						case 0:
//							//���������ȡ��Ƭ
//				            Intent intent1 = new Intent(Intent.ACTION_PICK, null);
//				            intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//				            startActivityForResult(intent1, 1);
//						break;
//						case 1://�����������
//				            Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//				            intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
//				                            "head.jpg")));
//				            startActivityForResult(intent2, 2);//����ForResult��
//						break;
//						}
//						
//					}
//				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//					
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						switch(which){
//						case 0:finish();
//						break;
//						}
//						
//					}
//				}).show();
//
//			}
//		});
		

		
		rl_person_realname.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				scrollview.setVisibility(View.GONE);
				llchangerealname.setVisibility(View.VISIBLE);
			}
		});
		
		rl_person_fenjihao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				scrollview.setVisibility(View.GONE);
				
				if(userinfo.getZhuanjie().equals("0")){
					llfenjihaoshenqing.setVisibility(View.VISIBLE);
				}else{
					llfenjihaojiebang.setVisibility(View.VISIBLE);
				}
				llchangfenjiahao.setVisibility(View.VISIBLE);
			}
		});
		
		rl_person_gerenjieshao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				scrollview.setVisibility(View.GONE);
				llchangegerenjieshao.setVisibility(View.VISIBLE);
			}
		});
		
		
		rl_personal_changepassword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				scrollview.setVisibility(View.GONE);
				llchangepassword.setVisibility(View.VISIBLE);
			}
		});
		
		
		
		rl_person_sex.setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View v) {
				
	Animation anim=AnimationUtils.loadAnimation(PersonalMainActivity.this, R.anim.activity_translate_in);
				
				ll_popup.setAnimation(anim);
				ll_cancle.setAnimation(anim);
				pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
				
			}
		});
		
		tv_changerealname_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			String userid=userinfo.getUserid();
			 String realname=et_changrealname.getText()+"";
			 String modelid=userinfo.getModelid();
				userinfopresenter.ChangeRealName(userid, realname, modelid);
				
				handler.postDelayed(new Runnable() {
					public void run() {
						
						llchangerealname.setVisibility(View.GONE);
						scrollview.setVisibility(View.VISIBLE);	
						onResume();
					}
				}, 1000);
			
				
			}
		});
		
		ll_changerealname_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				llchangerealname.setVisibility(View.GONE);
				scrollview.setVisibility(View.VISIBLE);
				onResume();
			}
		});
		
		ll_changepassword_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				et_change_currentpassword.setText("");
				et_change_Newpassword.setText("");
				et_change_repassword.setText("");
				llchangepassword.setVisibility(View.GONE);
				scrollview.setVisibility(View.VISIBLE);
				onResume();
			}
		});
		
		ll_changefenjihao_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				llfenjihaojiebang.setVisibility(View.GONE);
				llfenjihaoshenqing.setVisibility(View.GONE);
				llchangfenjiahao.setVisibility(View.GONE);
				scrollview.setVisibility(View.VISIBLE);
				onResume();
			}
		});
		
		ll_changegerenjieshao_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				llchangegerenjieshao.setVisibility(View.GONE);
				
				scrollview.setVisibility(View.VISIBLE);
				
				onResume();
			}
		});
		
		btn_changefenjihao_shenqing.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String tel=userinfo.getUsername();
				String userid=userinfo.getUserid();
				userinfopresenter.ChangeSQfenjihao(tel, userid);
				handler.postDelayed(new Runnable() {
					public void run() {
						llfenjihaoshenqing.setVisibility(View.GONE);
						llchangfenjiahao.setVisibility(View.GONE);
						scrollview.setVisibility(View.VISIBLE);	
						onResume();
					}
				}, 1000);
				
			}
		});
		
		btn_changefenjihao_jiebang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String tel=userinfo.getUsername();
				String userid=userinfo.getUserid();
				userinfopresenter.ChangeJBfenjihao(tel, userid);
				
				handler.postDelayed(new Runnable() {
					public void run() {
						
						llfenjihaojiebang.setVisibility(View.GONE);
						llchangfenjiahao.setVisibility(View.GONE);
						scrollview.setVisibility(View.VISIBLE);	
						onResume();
					}
				}, 1000);
				
			}
		});
		
		tv_changegerenjieshao_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				String about=et_changegerenjieshao.getText().toString();
				String userid=userinfo.getUserid();
				userinfopresenter.ChangeGeRenJieshao(userid, about);
				handler.postDelayed(new Runnable() {
					public void run() {
						llchangegerenjieshao.setVisibility(View.GONE);
						scrollview.setVisibility(View.VISIBLE);	
						onResume();
					}
				}, 1000);	
			}
		});
		
		btn_changepassword_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String username=userinfo.getUsername();
				String userid=userinfo.getUserid();
				String oldpwd=et_change_currentpassword.getText().toString();
				String pwd1=et_change_Newpassword.getText().toString();
				String pwd2=et_change_repassword.getText().toString();
				
				userinfopresenter.ChangePassword(username, userid, oldpwd, pwd1, pwd2);
				et_change_currentpassword.setText("");
				et_change_Newpassword.setText("");
				et_change_repassword.setText("");
				handler.postDelayed(new Runnable() {
					public void run() {
						llchangepassword.setVisibility(View.GONE);
						scrollview.setVisibility(View.VISIBLE);	
						onResume();
					}
				}, 1000);
				
			}
		});
		
		
		ivPersonalPhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
	Animation anim=AnimationUtils.loadAnimation(PersonalMainActivity.this, R.anim.activity_translate_in);
				
				ll_popup_touxiang.setAnimation(anim);
				ll_cancle_touxiang.setAnimation(anim);
				popTouXiang.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
				
			}
		});
		

	}

	

	private void setViews() {
		btn_changepassword_submit=(Button) findViewById(R.id.btn_changepassword_submit);
		
		
		et_change_currentpassword=(EditText) findViewById(R.id.et_change_currentpassword);
		et_change_Newpassword=(EditText) findViewById(R.id.et_change_Newpassword);
		et_change_repassword=(EditText) findViewById(R.id.et_change_repassword);
		
		
		et_changegerenjieshao=(EditText) findViewById(R.id.et_changegerenjieshao);
		tv_changegerenjieshao_save=(TextView) findViewById(R.id.tv_changegerenjieshao_save);
		btn_changefenjihao_shenqing=(Button) findViewById(R.id.btn_changefenjihao_shenqing);
		btn_changefenjihao_jiebang=(Button) findViewById(R.id.btn_changefenjihao_jiebang);
		tvfenjihao_show_1=(TextView) findViewById(R.id.tv_fenjihao_show_1);
		tv_fenjihao_show=(TextView) findViewById(R.id.tv_fenjihao_show);
		tv_person_sex=(TextView) findViewById(R.id.tv_person_sex);
		et_changrealname=(EditText) findViewById(R.id.et_changrealname);
		
		scrollview=(MyReboundScrollView) findViewById(R.id.scrollview_changeshow);
		
		ll_changerealname_back=(LinearLayout) findViewById(R.id.ll_changerealname_back);
		ll_changegerenjieshao_back=(LinearLayout) findViewById(R.id.ll_changegerenjieshao_back);
		ll_changepassword_back=(LinearLayout) findViewById(R.id.ll_changepassword_back);
		ll_changefenjihao_back=(LinearLayout) findViewById(R.id.ll_changefenjihao_back);
		
		tv_changerealname_save=(TextView) findViewById(R.id.tv_changerealname_save);
		
		rl_personal_phone=(RelativeLayout) findViewById(R.id.rl_personal_phone);
		
		rl_person_fenjihao=(RelativeLayout) findViewById(R.id.rl_person_fenjihao);
		
		rl_person_gerenjieshao=(RelativeLayout) findViewById(R.id.rl_person_gerenjieshao);
		
		rl_person_sex=(RelativeLayout) findViewById(R.id.rl_person_sex);
		rl_personal_changepassword=(RelativeLayout) findViewById(R.id.rl_personal_changepassword);
		
		llPersonalBack = (LinearLayout) findViewById(R.id.ll_personal_back);
		
		tv_personal_viptype=(TextView) findViewById(R.id.tv_personal_viptype);
		
		rlMyTouxiang = (RelativeLayout) findViewById(R.id.rl_MyTouxiang);
		rl_person_realname=(RelativeLayout) findViewById(R.id.rl_person_realname);
		tvPersonalPhone=(TextView) findViewById(R.id.tv_personal_phone);
		tv_person_fenjihao=(TextView) findViewById(R.id.tv_person_fenjihao);
		ivPersonalPhoto=(CircleImageView) findViewById(R.id.iv_personal_Photo);
		
		//各个分栏
		llchangerealname=(LinearLayout) findViewById(R.id.rl_person_changerealname);
		llchangfenjiahao=(LinearLayout) findViewById(R.id.ll__person_changfenjiahao);
		llchangegerenjieshao=(LinearLayout) findViewById(R.id.ll_person_gerenjieshao);
		llchangepassword=(LinearLayout) findViewById(R.id.ll_person_changepassword);
		llfenjihaojiebang=(LinearLayout) findViewById(R.id.fenjihao_jiebang);
		llfenjihaoshenqing=(LinearLayout) findViewById(R.id.fenjihao_shenqing);
//		  Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");//��Sd����ͷ��ת����Bitmap
//	        if(bt!=null){
//	            @SuppressWarnings("deprecation")
//	            Drawable drawable = new BitmapDrawable(bt);//ת����drawable
//	            ivPersonalPhoto.setImageDrawable(drawable);
//	        }else{
//	            /**
//	             *  ���SD����û������Ҫ�ӷ�����ȡͷ��ȡ������ͷ���ٱ�����SD��
//	             * 
//	             */
//	        }
		
		
	}
	
	
//	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//	        switch (requestCode) {
//	        
//	        case 1:
//	            if (resultCode == RESULT_OK) {
//	                cropPhoto(data.getData());//�ü�ͼƬ
//	            }
//	 
//	            break;
//	            
//	        case 2:
//	            if (resultCode == RESULT_OK) {
//	                File temp = new File(Environment.getExternalStorageDirectory()
//	                        + "/head.jpg");
//	                cropPhoto(Uri.fromFile(temp));//�ü�ͼƬ
//	            }
//	 
//	            break;
//	            
//	        case 3:
//	            if (data != null) {
//	                Bundle extras = data.getExtras();
//	                head = 
//	                		extras.getParcelable("data");
//	                if(head!=null){
//	                    /**
//	                     * �ϴ�����������
//	                     */
//	                	String userid=userinfo.getUserid();
//	                	headpresenter.ChangeHead(userid, head);
//	                	
//	                	
//	                    setPicToView(head);//������SD����
//	                	UserInfo userinfo1 = HouseGoApp.getContext().getCurrentUserInfo();
//	                	String url1=userinfo.getUserpic();
//	        			Glide.with(getApplicationContext()).load(url1).into(ivPersonalPhoto);
////	                    ivPersonalPhoto.setImageBitmap(head);//��ImageView��ʾ����
//	                }
//	            }
//	            break;
//	        default:
//	            break;
//	 
//	        }
//	        super.onActivityResult(requestCode, resultCode, data);
//	    }
	
	
    /**
     * ����ϵͳ�Ĳü�
     * @param uri
//     */
//    public void cropPhoto(Uri uri) {
//    	
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        intent.putExtra("crop", "true");
//         // aspectX aspectY �ǿ�ߵı���
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        // outputX outputY �ǲü�ͼƬ���
//        intent.putExtra("outputX", 150);
//        intent.putExtra("outputY", 150);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, 3);
//        
//    }
//    private void setPicToView(Bitmap mBitmap) {
//         String sdStatus = Environment.getExternalStorageState();  
//        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ����  
//               return;  
//           }  
//        FileOutputStream b = null;
//        File file = new File(path);
//        file.mkdirs();// �����ļ���
//        String fileName =path + "head.jpg";//ͼƬ����
//        try {
//            b = new FileOutputStream(fileName);
//            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// ������д���ļ�
//             
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                //�ر���
//                b.flush();
//                b.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
// 
//        }
//
//
//}
//
//	@Override
//	public void changeHeadFail(String errorMessage) {
//		Toast.makeText(getApplicationContext(), "头像更换失败！", Toast.LENGTH_SHORT).show();
//		
//	}
//
//	@Override
//	public void changeHeadSuccess(String picUrl) {
//		
//		Glide.with(getApplicationContext()).load(picUrl).into(ivPersonalPhoto);
//	Toast.makeText(getApplicationContext(), "头像更换成功！", Toast.LENGTH_SHORT).show();
//	}
	
	//监听back键按下事件
	@Override
	public void onBackPressed() {
		// 屏蔽back键盘
//		super.onBackPressed();
	}

	
	@Override
	public void changeInfo(final String info) {
		MyToastShowCenter.CenterToast(getApplicationContext(), info);
		
		handler.postDelayed(new Runnable() {
			public void run() {
				if(info.equals("密码修改成功")){
					HouseGoApp.clearData(userinfo);
					onResume();
					startActivity(new Intent(getBaseContext(), LoginActivity.class));
				}	
			}
		}, 1000);
		
		String userid=userinfo.getUserid();
		infomodel.login(userid,new  AsycnCallBack() {
			@Override
			public void onSuccess(Object success) {
				UserInfo Nuserinfo=(UserInfo) success;
				HouseGoApp.saveLoginInfo(getApplicationContext(), Nuserinfo);
			}
			@Override
			public void onError(Object error) {
			}
		});
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(resultCode==RESULT_OK){super.onActivityResult(requestCode, resultCode, data);
			switch(requestCode){
			case TAKE_PICTURE:
				startPhotoZoom(tempUri);
				break;
			case CHOOSE_PICTURE:
				startPhotoZoom(data.getData());
				break;
			case CROP_SMALL_PICTURE:
				if(data!=null){
				setImageToView(data);
				}
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	/**
	 * 剪裁图片方法的实现
	 */
	protected void startPhotoZoom(Uri uri){
		
	     if (ContextCompat.checkSelfPermission(this,
	                Manifest.permission.CAMERA)
	                != PackageManager.PERMISSION_GRANTED) {
	           ActivityCompat.requestPermissions((Activity) this,
	                    new String[]{Manifest.permission.CAMERA},
	                    TAKE_PICTURE);
	        }
		
		
		if(uri==null){
			Log.i("tag", "the uri is not exist");
		}
		tempUri=uri;
		Intent intent=new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		//设置剪裁
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", "1");
		intent.putExtra("aspectY", "1");
		intent.putExtra("outputX", "150");
		intent.putExtra("outputY", "150");
		intent.putExtra("return-data", true);
		startActivityForResult(intent, CROP_SMALL_PICTURE);
		
	}
	
	/**
	 * 保存剪彩后的图片
	 */
	
	protected void setImageToView(Intent data){
		Bundle extras=data.getExtras();
		if(extras!=null){
			Bitmap photo=extras.getParcelable("data");
//			photo=Utils      //圆形处理图片
			ivPersonalPhoto.setImageBitmap(photo);
			uploadPic(photo);
		}
	}

	//上传服务器代码
	private void uploadPic(Bitmap bitmap) {
		
		String imagePath=Utils.savePhoto(bitmap, Environment.getExternalStorageDirectory().getAbsolutePath(), String.valueOf(System.currentTimeMillis()));
		Log.e("imagepath", imagePath);
		
		if(imagePath!=null){
			//上传代码
			String userid=userinfo.getUserid();
			headpresenter.ChangeHead(userid, imagePath);
		}
		
	}
	
	

	        
	   
	           
	
	
	
	

	@Override
	public void changeHeadFail(String errorMessage) {
		MyToastShowCenter.CenterToast(getApplicationContext(), "头像更换失败！"); 
		
	}

	@Override
	public void changeHeadSuccess(String picUrl) {
		MyToastShowCenter.CenterToast(getApplicationContext(), "头像更换成功！"+picUrl); 
		Glide.with(getApplicationContext()).load(picUrl).into(ivPersonalPhoto);
		
	}
	
	
}
