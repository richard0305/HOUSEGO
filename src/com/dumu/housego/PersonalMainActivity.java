package com.dumu.housego;

import org.xutils.x;

import com.bumptech.glide.Glide;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.activity.MainActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.LoginUserInfoModel;
import com.dumu.housego.presenter.ChangeUserInfoPresenter;
import com.dumu.housego.presenter.IChangeHeadPhotoPresenter;
import com.dumu.housego.presenter.IChangeUserInfoPresenter;
import com.dumu.housego.util.CircleImageView;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyReboundScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.view.IChangeUserInfoView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalMainActivity extends Activity implements IChangeUserInfoView{
	private LoginUserInfoModel infomodel=new LoginUserInfoModel();
	
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
    
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		x.view().inject(this);
		setViews();
		userinfopresenter=new ChangeUserInfoPresenter(this);
		setListeners();
		
//		headpresenter=new ChangeHeadPhotoPresenter(this);
		
	}
	
	
	@Override
	protected void onResume() {
		
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		
		if(userinfo==null){
//			MyToastShowCenter.CenterToast(getApplicationContext(), "您还没有登录，请先登录！");
			startActivity(new Intent(getBaseContext(), MainActivity.class));
			
		}else{
			String url=userinfo.getUserpic();
			Glide.with(getApplicationContext()).load(url).into(ivPersonalPhoto);
//			userinfo=HouseGoApp.getContext().getCurrentUserInfo();
//			tvPersonalNickname.setText(userinfo.getNickname()+"");
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
				MyToastShowCenter.CenterToast(getApplicationContext(), userinfo.getRealname());
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
						et_change_currentpassword.setText("");
						et_change_Newpassword.setText("");
						et_change_repassword.setText("");
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
				handler.postDelayed(new Runnable() {
					public void run() {
						llchangepassword.setVisibility(View.GONE);
						scrollview.setVisibility(View.VISIBLE);	
						onResume();
					}
				}, 1000);
				
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
}
