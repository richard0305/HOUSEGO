package com.dumu.housego;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.bumptech.glide.Glide;
import com.dumu.housego.activity.LoginActivity;
import com.dumu.housego.activity.MainActivity;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.IModel.AsycnCallBack;
import com.dumu.housego.model.LoginUserInfoModel;
import com.dumu.housego.presenter.ChangeUserInfoPresenter;
import com.dumu.housego.presenter.IChangeUserInfoPresenter;
import com.dumu.housego.util.CircleImageView;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.util.MyReboundScrollView;
import com.dumu.housego.util.MyToastShowCenter;
import com.dumu.housego.util.UrlFactory;
import com.dumu.housego.utils.UploadUtil;
import com.dumu.housego.utils.UploadUtil.OnUploadProcessListener;
import com.dumu.housego.view.IChangeUserInfoView;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AgentChangeUserInfoActivity extends Activity implements OnUploadProcessListener, IChangeUserInfoView {
	

	/**
	 * 请求裁剪码
	 */
	public static final int REQUEST_CODE_GETIMAGE_BYCROP = 1;
	/**
	 * 请求相机码
	 */
	public static final int REQUEST_CODE_GETIMAGE_BYCAMERA = 2;
	/**
	 * 请求相册
	 */
	public static final int REQUEST_CODE_GETIMAGE_BYSDCARD = 3;

	/**
	 * 去上传文件
	 */
	protected static final int TO_UPLOAD_FILE = 4;  

	/**
	 * 上传文件响应
	 */
	protected static final int UPLOAD_FILE_DONE = 5;  //

	private Uri origUri;
	
	
	private IChangeUserInfoPresenter userinfopresenter;
	private LoginUserInfoModel infomodel = new LoginUserInfoModel();

	private LinearLayout ll_agent_back;

	private EditText et_agent_changrealname, et_agent_changegerenjieshao, et_agent_changeshenfenzheng,
			et_agent_changecompanyname, et_agent_change_currentpassword, et_agent_change_Newpassword,
			et_agent_change_repassword;
	private LinearLayout ll_agent_changerealname, ll_agent_changerealname_back, ll__aegnt_changfenjiahao,
			ll_agent_changefenjihao_back, agent_fenjihao_shenqing, agent_fenjihao_jiebang, ll_agent_gerenjieshao,
			ll_agent_changegerenjieshao_back, ll_agent_changeshenfenzheng, ll_agent_changeshenfenzheng_back,
			ll_agent_changecompanyname, ll_agent_changecompanyname_back, ll_agent_changepassword,
			ll_agent_changepassword_back;

	private RelativeLayout rl_agent_shenfenzheng, rl_agent_mainarea, rl_agent_zhiyeType, rl_agent_companyName,
			rl_agent_chongyeshijian, rl_agent_upload, rl_agent_changepassword, rl_agent_realname, rl_agent_sex,
			rl_agent_fenjihao, rl_agent_gerenjieshao;

	private TextView tv_agent_shenfenzheng, tv_agent_mainarea, tv_agent_zhiyeType, tv_agent_realname,
			tv_agent_chongyeshijian, tv_agent_phone, tv_agent_viptype, tv_agent_sex, tv_agent_fenjihao,
			tv_agent_fenjihao_show, tv_agent_fenjihao_show_1, tv_agent_changegerenjieshao_save,
			tv_agent_changeshenfenzheng_save, tv_agent_changecompanyname_save;
	private Button btn_agent_changefenjihao_shenqing, btn_agent_changefenjihao_jiebang, btn_agent_changepassword_submit;

	private TextView tv_agent_changerealname_save;

	private CircleImageView iv_agent_Photo;
	private UserInfo userinfo = HouseGoApp.getContext().getCurrentUserInfo();;

	private MyReboundScrollView agent_changeshow;

	protected Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			String userid = userinfo.getUserid();

			infomodel.login(userid, new AsycnCallBack() {
				@Override
				public void onSuccess(Object success) {
					UserInfo Nuserinfo = (UserInfo) success;
					HouseGoApp.getContext().SaveCurrentUserInfo(Nuserinfo);
					HouseGoApp.saveLoginInfo(getApplicationContext(), Nuserinfo);
				}

				@Override
				public void onError(Object error) {
				}
			});
			userinfo = HouseGoApp.getContext().getCurrentUserInfo();



		}
	};
	
	
	
	
	private PopupWindow pop;
	private LinearLayout ll_popup;

	private View parentView;
	private LinearLayout ll_cancle;
	private PopupWindow popType;
	private LinearLayout ll_popup_zhiyeType;
	private LinearLayout ll_cancle_zhiyeType;
	private PopupWindow popWorkTime;
	private LinearLayout ll_popup_worktime;
	private LinearLayout ll_cancle_worktime;
	private String tcb1="";
	private String tcb2="";
	private String tcb3="";
	private String tcb4="";
	private String tcb5="";
	private String tcb6="";
	private String tcb7="";
	private String tcb8="";
	private String tcb9="";
	private String tcb10="";
	private String tcb11="";
	private String tcb12="";
	private PopupWindow popTouXiang;
	private LinearLayout ll_popup_touxiang;
	private LinearLayout ll_cancle_touxiang;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		parentView = getLayoutInflater().inflate(R.layout.activity_agent_change_user_info, null);
		setContentView(parentView);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		userinfopresenter = new ChangeUserInfoPresenter(this);
		showPopWindowSex();
		showPopWindowType();
		showPopWindowWorkTime();
		setListener();
	}

	private void showPopWindowWorkTime() {
		popWorkTime = new PopupWindow(AgentChangeUserInfoActivity.this);

		View view = getLayoutInflater().inflate(R.layout.item_popupwindows_worktime, null);

		ll_popup_worktime = (LinearLayout) view.findViewById(R.id.ll_popup_worktime);
		ll_cancle_worktime = (LinearLayout) view.findViewById(R.id.ll_cancle_worktime);
		popWorkTime.setWidth(LayoutParams.MATCH_PARENT);
		popWorkTime.setHeight(LayoutParams.WRAP_CONTENT);
		popWorkTime.setBackgroundDrawable(new BitmapDrawable());
		popWorkTime.setFocusable(true);
		popWorkTime.setOutsideTouchable(true);
		popWorkTime.setContentView(view);

		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_worktime);
		Button bt4 = (Button) view.findViewById(R.id.item_popupwindows_worktime_btn4);
		Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_worktime_btn2);
		Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_worktime_btn3);
		Button cancle = (Button) view.findViewById(R.id.item_popupwindows_worktime_cancel);
		parent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popWorkTime.dismiss();
				ll_popup_worktime.clearAnimation();
				ll_cancle_worktime.clearAnimation();
			}
		});

		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				String userid = userinfo.getUserid();
				String worktime = "1-2";
				userinfopresenter.ChangeWorkTime(userid, worktime);
				tv_agent_chongyeshijian.setText(worktime+"年");
				popWorkTime.dismiss();
				ll_popup_worktime.clearAnimation();
				ll_cancle_worktime.clearAnimation();
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String userid = userinfo.getUserid();
				String worktime = "2-5";
				userinfopresenter.ChangeWorkTime(userid, worktime);
				tv_agent_chongyeshijian.setText(worktime+"年");
				popWorkTime.dismiss();
				ll_popup_worktime.clearAnimation();
				ll_cancle_worktime.clearAnimation();
			}
		});

		bt4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String userid = userinfo.getUserid();
				String worktime = "5";
				userinfopresenter.ChangeWorkTime(userid, worktime);
				tv_agent_chongyeshijian.setText(worktime+"年");
				popWorkTime.dismiss();
				ll_popup_worktime.clearAnimation();
				ll_cancle_worktime.clearAnimation();
			}
		});
		cancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				popWorkTime.dismiss();
				ll_popup_worktime.clearAnimation();
				ll_cancle_worktime.clearAnimation();
			}
		});

	}
	/**
	 * 创建对话框
	 */
	protected void creatPopupwindows() {

		popTouXiang = new PopupWindow(AgentChangeUserInfoActivity.this);

		View view = getLayoutInflater().inflate(R.layout.item_popupwindows_touxiang, null);

		ll_popup_touxiang = (LinearLayout) view.findViewById(R.id.ll_popup_touxaing);
		ll_cancle_touxiang = (LinearLayout) view.findViewById(R.id.ll_cancle_touxaing);
		popTouXiang.setWidth(LayoutParams.MATCH_PARENT);
		popTouXiang.setHeight(LayoutParams.WRAP_CONTENT);
		popTouXiang.setBackgroundDrawable(new BitmapDrawable());
		popTouXiang.setFocusable(true);
		popTouXiang.setOutsideTouchable(true);
		popTouXiang.setContentView(view);

		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_touxaing);
		Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_touxaing1);
		Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_touxaing2);
		Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_touxaing_cancel);

		bt1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//照相
				photo();
				pop.dismiss();
				ll_popup_touxiang.clearAnimation();
				ll_cancle_touxiang.clearAnimation();
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent;
				// Android6.0以上，要动态申请读写权限
				if (ContextCompat.checkSelfPermission(getApplicationContext(),
						Manifest.permission.WRITE_EXTERNAL_STORAGE)
						!= PackageManager.PERMISSION_GRANTED) {
					//申请读写权限
					ActivityCompat.requestPermissions(AgentChangeUserInfoActivity.this,
							new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
							REQUEST_CODE_GETIMAGE_BYCROP);
				} else{

					if (Build.VERSION.SDK_INT < 19) {
						intent = new Intent();
						intent.setAction(Intent.ACTION_GET_CONTENT);
						intent.setType("image/*");
						startActivityForResult(Intent.createChooser(intent, "选择图片"),
								REQUEST_CODE_GETIMAGE_BYCROP);
					} else {
						intent = new Intent(Intent.ACTION_PICK,
								Images.Media.EXTERNAL_CONTENT_URI);
						intent.setType("image/*");
						startActivityForResult(Intent.createChooser(intent, "选择图片"),
								REQUEST_CODE_GETIMAGE_BYCROP);
					}
				}
				pop.dismiss();
				//清除动画
				ll_popup_touxiang.clearAnimation();
				ll_cancle_touxiang.clearAnimation();
			}
		});

		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pop.dismiss();
				ll_popup_touxiang.clearAnimation();
				ll_cancle_touxiang.clearAnimation();
			}
		});

	}
	
	
	/**
	 * 照相
	 */
	protected void photo() {
		String savePath ="";
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			//SD卡处于挂载状态,创建图片保存的文件夹路径
			savePath = Environment.getExternalStorageDirectory().getAbsolutePath()
					+"/image/";
			File saveDir = new File(savePath);
			if(!saveDir.exists()){
				//文件夹不存在，创建文件夹
				saveDir.mkdirs();
			}
		}else{
			Toast.makeText(this, "无法保存照片，请检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
			return;
		}

		//创建图片名称
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
		.format(new Date());
		String fileName = "zhicuo_" + timeStamp + ".jpg";// 照片命名

		//自定义图片保存路径
		File out = new File(savePath, fileName);
		Uri uri = Uri.fromFile(out);
		origUri = uri;

		// 动态申请照相权限和读写权限
		if (ContextCompat.checkSelfPermission(getApplicationContext(),
				Manifest.permission.CAMERA)
				!= PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this,
					new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
					REQUEST_CODE_GETIMAGE_BYCAMERA);
		} else{
			Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			Log.i("MainActivity", uri.toString());
			openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
			startActivityForResult(openCameraIntent,
					REQUEST_CODE_GETIMAGE_BYCAMERA);
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent imageReturnIntent) {
		//回调处理结果
		if (resultCode != Activity.RESULT_OK)
			return;

		switch (requestCode) {
		case REQUEST_CODE_GETIMAGE_BYCAMERA :
			startActionCrop(origUri);// 拍照后裁剪
			break;
		case REQUEST_CODE_GETIMAGE_BYCROP:
			startActionCrop(imageReturnIntent.getData());// 选图后裁剪
			break;
		case REQUEST_CODE_GETIMAGE_BYSDCARD :
			//发送上传头像的消息
			handler1.sendEmptyMessage(TO_UPLOAD_FILE);
			break;
		}

	}

	private Handler handler1 = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case TO_UPLOAD_FILE:
				toUploadFile();
				break;

			case UPLOAD_FILE_DONE:
				//响应返回的结果
				if(msg.arg1 == UploadUtil.UPLOAD_SUCCESS_CODE ){
					String result = (String) msg.obj;
					try {
						//上传成功之后，服务端返回的数据
						JSONObject obj = new JSONObject(result);
						//获取服务端返回的头像地址
						String portrait = obj.getString("avatarUrls");
						//根据自己业务做后续处理
						Glide.with(getApplicationContext()).load(portrait).into(iv_agent_Photo);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}else if(msg.arg1 == UploadUtil.UPLOAD_SERVER_ERROR_CODE){
					Toast.makeText(AgentChangeUserInfoActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	/**
	 * 请求裁剪
	 * @param origUri
	 */
	private void startActionCrop(Uri data) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(data, "image/*");
		intent.putExtra("output", this.getUploadTempFile(data));
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);// 裁剪框比例
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 200);// 输出图片大小
		intent.putExtra("outputY", 200);
		intent.putExtra("scale", true);// 去黑边
		intent.putExtra("scaleUpIfNeeded", true);// 去黑边
		startActivityForResult(intent,
				REQUEST_CODE_GETIMAGE_BYSDCARD);
	}
	/**
	 * 上传头像到服务器
	 */
	protected void toUploadFile() {
		String fileKey = "img";
		UploadUtil uploadUtil = UploadUtil.getInstance();
		String url=UrlFactory.PostChangeHeadPhotoUrl();
		uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
		//定义一个Map集合，封装请求服务端时需要的参数
		Map<String, String> params = new HashMap<String, String>();
		//在这里根据服务端需要的参数自己来定
//		params.put("userId", user.getUserId());
//		params.put("tocken", user.getTocken());
//		params.put("picType", "1");
		params.put("userid",userinfo.getUserid());
		
		
		
		if(protraitFile.exists()){
			//参数三：请求的url，这里我用到了公司的url
			uploadUtil.uploadFile(protraitFile.getAbsolutePath() ,fileKey, url,params);
		}
	}
	//获取保存头像地址的Uri
	private Uri getUploadTempFile(Uri uri) {
		String portraitPath = "";
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			//保存图像的文件夹路径
			portraitPath = Environment.getExternalStorageDirectory().getAbsolutePath()
					+"/image/Portrait";
			File saveDir = new File(portraitPath);
			if(!saveDir.exists()){
				saveDir.mkdirs();
			}
		}else {
			Toast.makeText(this, "无法保存照片，请检查SD卡是否挂载", Toast.LENGTH_SHORT).show();;
			return null;
		}

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
		.format(new Date());

		String thePath = getAbsolutePathFromNoStandardUri(uri);
		if (thePath.isEmpty()) {
			thePath = getAbsoluteImagePath(uri);
		}

		//获取图片路径的扩展名
		String ext = thePath.substring(thePath.lastIndexOf('.') + 1);
		ext = ext.isEmpty() ? "jpg" : ext;

		// 照片命名
		String cropFileName = "crop_" + timeStamp + "." + ext;
		protraitFile = new File(portraitPath, cropFileName);
		cropUri = Uri.fromFile(protraitFile);
		return cropUri;
	}




	private String SDCARD_MNT = "/mnt/sdcard";
	private String SDCARD = "/sdcard";
	private Uri cropUri;
	private File protraitFile;
	private RelativeLayout parent;

	/**
	 * 判断当前Url是否标准的content://样式，如果不是，则返回绝对路径
	 * @param uri
	 * @return
	 */
	private String getAbsolutePathFromNoStandardUri(Uri mUri) {
		String filePath = "";

		String mUriString = mUri.toString();
		mUriString = Uri.decode(mUriString);

		String pre1 = "file://" + SDCARD + File.separator;
		String pre2 = "file://" + SDCARD_MNT + File.separator;

		if (mUriString.startsWith(pre1)) {
			filePath = Environment.getExternalStorageDirectory().getPath()
					+ File.separator + mUriString.substring(pre1.length());
		} else if (mUriString.startsWith(pre2)) {
			filePath = Environment.getExternalStorageDirectory().getPath()
					+ File.separator + mUriString.substring(pre2.length());
		}
		return filePath;
	}

	/**
	 * 通过uri获取文件的绝对路径
	 * @param uri
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private String getAbsoluteImagePath(Uri uri) {

		String imagePath = "";
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, proj, // Which columns to
				// return
				null, // WHERE clause; which rows to return (all rows)
				null, // WHERE clause selection arguments (none)
				null); // Order-by clause (ascending by name)

		if (cursor != null) {
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			if (cursor.getCount() > 0 && cursor.moveToFirst()) {
				imagePath = cursor.getString(column_index);
			}
		}
		return imagePath;
	}
	
	
	@Override
	public void onUploadDone(int responseCode, String message) {
		Message msg = Message.obtain();
		msg.what = UPLOAD_FILE_DONE;
		msg.arg1 = responseCode;
		msg.obj = message;	
		handler.sendMessage(msg);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void showPopWindowType() {
		popType = new PopupWindow(AgentChangeUserInfoActivity.this);

		View view = getLayoutInflater().inflate(R.layout.item_popupwindows_zhiyetype, null);

		ll_popup_zhiyeType = (LinearLayout) view.findViewById(R.id.ll_popup_yizhitype);
		ll_cancle_zhiyeType = (LinearLayout) view.findViewById(R.id.ll_cancle_yizhitype);
		popType.setWidth(LayoutParams.MATCH_PARENT);
		popType.setHeight(LayoutParams.WRAP_CONTENT);
		popType.setBackgroundDrawable(new BitmapDrawable());
		popType.setFocusable(true);
		popType.setOutsideTouchable(true);
		popType.setContentView(view);

		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent_yizhitype);
		Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_yizhitype_btn1);
		Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_yizhitype_btn2);
		Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_yizhitype_cancel);
		Button bt4 = (Button) view.findViewById(R.id.item_popupwindows_yizhitype_btn3);
		parent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popType.dismiss();
				ll_popup_zhiyeType.clearAnimation();
				ll_cancle_zhiyeType.clearAnimation();
			}
		});

		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				String userid = userinfo.getUserid();
				String leixing = "个人";
				userinfopresenter.ChangeLeixing(userid, leixing);
				tv_agent_zhiyeType.setText(leixing);
				popType.dismiss();
				ll_popup_zhiyeType.clearAnimation();
				ll_cancle_zhiyeType.clearAnimation();
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				popType.dismiss();
				ll_popup_zhiyeType.clearAnimation();
				ll_cancle_zhiyeType.clearAnimation();
			}
		});
		bt4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String userid = userinfo.getUserid();
				String leixing = "公司";
				userinfopresenter.ChangeLeixing(userid, leixing);
				tv_agent_zhiyeType.setText(leixing);
				popType.dismiss();
				ll_popup_zhiyeType.clearAnimation();
				ll_cancle_zhiyeType.clearAnimation();
			}
		});

	}

	private void showPopWindowSex() {
		pop = new PopupWindow(AgentChangeUserInfoActivity.this);

		View view = getLayoutInflater().inflate(R.layout.item_popupwindows_1, null);

		ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
		ll_cancle = (LinearLayout) view.findViewById(R.id.ll_cancle);
		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);

		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
		Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_btn1);
		Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_btn2);
		Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);

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

				// tv_agent_sex.setText("男");

				String userid = userinfo.getUserid();
				String sex = "1";
				userinfopresenter.ChangeSex(userid, sex);
				tv_agent_sex.setText("男");
				pop.dismiss();
				ll_popup.clearAnimation();
				ll_cancle.clearAnimation();
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// tv_agent_sex.setText("女");
				String userid = userinfo.getUserid();
				String sex = "2";
				userinfopresenter.ChangeSex(userid, sex);
				tv_agent_sex.setText("女");
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

		// userinfo=HouseGoApp.getLoginInfo(getApplicationContext());
		if (userinfo == null) {
			startActivity(new Intent(this, MainActivity.class));

		} else {
			String url = userinfo.getUserpic();
			Glide.with(getApplicationContext()).load(url).into(iv_agent_Photo);

			if (userinfo.getModelid().equals("35")) {
				tv_agent_viptype.setText("普通会员");
			} else {
				tv_agent_viptype.setText("经纪人");
			}

			if (userinfo.getSex().equals("0")) {
				tv_agent_sex.setText("未知");
			} else if (userinfo.getSex().equals("1")) {
				tv_agent_sex.setText("男");
			} else {
				tv_agent_sex.setText("女");
			}
			tv_agent_fenjihao_show.setText(userinfo.getUsername());
			tv_agent_fenjihao_show_1.setText(userinfo.getVtel());

			tv_agent_realname.setText(userinfo.getRealname());
			tv_agent_phone.setText(userinfo.getUsername() + "");
			tv_agent_fenjihao.setText(userinfo.getVtel());
			tv_agent_shenfenzheng.setText(userinfo.getCardnumber());
			tv_agent_mainarea.setText(userinfo.getMainarea());
			tv_agent_zhiyeType.setText(userinfo.getLeixing());
			tv_agent_chongyeshijian.setText(userinfo.getWorktime() + "年");
			et_agent_changrealname.setText(userinfo.getRealname());
			et_agent_changegerenjieshao.setText(userinfo.getAbout());

		}

		super.onResume();
	}

	private void setListener() {
		ll_agent_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		iv_agent_Photo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyToastShowCenter.CenterToast(getApplicationContext(), "选择图片");
			}
		});

		rl_agent_realname.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				agent_changeshow.setVisibility(View.GONE);
				ll_agent_changerealname.setVisibility(View.VISIBLE);

			}
		});

		rl_agent_sex.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(AgentChangeUserInfoActivity.this,
						R.anim.activity_translate_in);

				ll_popup.setAnimation(anim);
				ll_cancle.setAnimation(anim);
				pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

			}

		});

		rl_agent_fenjihao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				agent_changeshow.setVisibility(View.GONE);

				if (userinfo.getZhuanjie().equals("0")) {
					agent_fenjihao_shenqing.setVisibility(View.VISIBLE);
				} else {
					agent_fenjihao_jiebang.setVisibility(View.VISIBLE);
				}
				ll__aegnt_changfenjiahao.setVisibility(View.VISIBLE);

			}
		});

		rl_agent_gerenjieshao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				agent_changeshow.setVisibility(View.GONE);
				ll_agent_gerenjieshao.setVisibility(View.VISIBLE);
			}
		});

		rl_agent_shenfenzheng.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				agent_changeshow.setVisibility(View.GONE);
				ll_agent_changeshenfenzheng.setVisibility(View.VISIBLE);
			}
		});

		rl_agent_mainarea.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CityCheckDialog();
			}
		});

		rl_agent_zhiyeType.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(AgentChangeUserInfoActivity.this,
						R.anim.activity_translate_in);

				ll_popup_zhiyeType.setAnimation(anim);
				ll_cancle_zhiyeType.setAnimation(anim);
				popType.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			}
		});

		rl_agent_companyName.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				agent_changeshow.setVisibility(View.GONE);
				ll_agent_changecompanyname.setVisibility(View.VISIBLE);
			}
		});

		rl_agent_chongyeshijian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(AgentChangeUserInfoActivity.this,
						R.anim.activity_translate_in);

				ll_popup_worktime.setAnimation(anim);
				ll_cancle_worktime.setAnimation(anim);
				popWorkTime.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
			}
		});

		rl_agent_upload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});

		rl_agent_changepassword.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				agent_changeshow.setVisibility(View.GONE);
				ll_agent_changepassword.setVisibility(View.VISIBLE);
			}
		});

		/**
		 * 各个模型功能 真实姓名
		 * 
		 */

		ll_agent_changerealname_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ll_agent_changerealname.setVisibility(View.GONE);
				agent_changeshow.setVisibility(View.VISIBLE);

			}
		});

		tv_agent_changerealname_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String userid = userinfo.getUserid();
				String realname = et_agent_changrealname.getText().toString();
				String modelid = userinfo.getModelid();
				userinfopresenter.ChangeRealName(userid, realname, modelid);
				tv_agent_realname.setText(realname);
				handler.postDelayed(new Runnable() {
					public void run() {
						ll_agent_changerealname.setVisibility(View.GONE);
						agent_changeshow.setVisibility(View.VISIBLE);
						onResume();
					}
				}, 1000);

			}
		});

		/**
		 * 各个模型功能 分机号
		 */
		ll_agent_changefenjihao_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				agent_fenjihao_jiebang.setVisibility(View.GONE);
				agent_fenjihao_shenqing.setVisibility(View.GONE);
				ll__aegnt_changfenjiahao.setVisibility(View.GONE);
				agent_changeshow.setVisibility(View.VISIBLE);

			}
		});

		btn_agent_changefenjihao_shenqing.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				String tel = userinfo.getUsername();
				String userid = userinfo.getUserid();
				userinfopresenter.ChangeSQfenjihao(tel, userid);

				handler.postDelayed(new Runnable() {
					public void run() {
						agent_fenjihao_shenqing.setVisibility(View.GONE);
						ll__aegnt_changfenjiahao.setVisibility(View.GONE);
						agent_changeshow.setVisibility(View.VISIBLE);
						onResume();
					}
				}, 1000);

			}
		});
		btn_agent_changefenjihao_jiebang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				String tel = userinfo.getUsername();
				String userid = userinfo.getUserid();
				userinfopresenter.ChangeSQfenjihao(tel, userid);

				handler.postDelayed(new Runnable() {
					public void run() {
						agent_fenjihao_jiebang.setVisibility(View.GONE);
						ll__aegnt_changfenjiahao.setVisibility(View.GONE);
						agent_changeshow.setVisibility(View.VISIBLE);
						onResume();
					}
				}, 1000);

			}
		});

		/**
		 * 各个模型功能 个人介绍
		 */
		ll_agent_changegerenjieshao_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ll_agent_gerenjieshao.setVisibility(View.GONE);
				agent_changeshow.setVisibility(View.VISIBLE);
			}
		});

		tv_agent_changegerenjieshao_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				String userid = userinfo.getUserid();
				String about = et_agent_changegerenjieshao.getText().toString();
				userinfopresenter.ChangeGeRenJieshao(userid, about);
				
				handler.postDelayed(new Runnable() {
					public void run() {
						ll_agent_gerenjieshao.setVisibility(View.GONE);
						agent_changeshow.setVisibility(View.VISIBLE);
						onResume();
					}
				}, 1000);

			}
		});

		/**
		 * 各个模型功能 身份证号码
		 */
		ll_agent_changeshenfenzheng_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ll_agent_changeshenfenzheng.setVisibility(View.GONE);
				agent_changeshow.setVisibility(View.VISIBLE);
			}
		});

		tv_agent_changeshenfenzheng_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String userid = userinfo.getUserid();
				String cardnumber = et_agent_changeshenfenzheng.getText().toString();
				userinfopresenter.ChangeShenFenZheng(userid, cardnumber);
				handler.postDelayed(new Runnable() {
					public void run() {
						ll_agent_changeshenfenzheng.setVisibility(View.GONE);
						agent_changeshow.setVisibility(View.VISIBLE);
						onResume();
					}
				}, 1000);
			}
		});

		/**
		 * 各个模型功能 公司名称
		 */
		ll_agent_changecompanyname_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ll_agent_changecompanyname.setVisibility(View.GONE);
				agent_changeshow.setVisibility(View.VISIBLE);
			}
		});

		tv_agent_changecompanyname_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				String userid = userinfo.getUserid();
				String coname = et_agent_changecompanyname.getText().toString();
				userinfopresenter.ChangeConame(userid, coname);

				handler.postDelayed(new Runnable() {
					public void run() {
						ll_agent_changecompanyname.setVisibility(View.GONE);
						agent_changeshow.setVisibility(View.VISIBLE);
						onResume();
					}
				}, 1000);
			}
		});

		/**
		 * 各个模型功能 密码修改
		 */
		ll_agent_changepassword_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ll_agent_changepassword.setVisibility(View.GONE);
				agent_changeshow.setVisibility(View.VISIBLE);
			}
		});

		btn_agent_changepassword_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				String username = userinfo.getUsername();
				String userid = userinfo.getUserid();
				String oldpwd = et_agent_change_currentpassword.getText().toString();
				String pwd1 = et_agent_change_Newpassword.getText().toString();
				String pwd2 = et_agent_change_repassword.getText().toString();
				userinfopresenter.ChangePassword(username, userid, oldpwd, pwd1, pwd2);

				handler.postDelayed(new Runnable() {
					public void run() {
						ll_agent_changepassword.setVisibility(View.GONE);
						agent_changeshow.setVisibility(View.VISIBLE);
						onResume();
					}
				}, 1000);

			}
		});

	}

	private void setViews() {
		ll_agent_back = (LinearLayout) findViewById(R.id.ll_agent_changeinfo_back);
		// 各个条目
		rl_agent_changepassword = (RelativeLayout) findViewById(R.id.rl_agent_changepassword);
		rl_agent_chongyeshijian = (RelativeLayout) findViewById(R.id.rl_agent_chongyeshijian);
		rl_agent_companyName = (RelativeLayout) findViewById(R.id.rl_agent_companyName);
		rl_agent_fenjihao = (RelativeLayout) findViewById(R.id.rl_agent_fenjihao);
		rl_agent_gerenjieshao = (RelativeLayout) findViewById(R.id.rl_agent_gerenjieshao);
		rl_agent_mainarea = (RelativeLayout) findViewById(R.id.rl_agent_mainarea);
		rl_agent_realname = (RelativeLayout) findViewById(R.id.rl_agent_realname);
		rl_agent_sex = (RelativeLayout) findViewById(R.id.rl_agent_sex);
		rl_agent_shenfenzheng = (RelativeLayout) findViewById(R.id.rl_agent_shenfenzheng);
		rl_agent_upload = (RelativeLayout) findViewById(R.id.rl_agent_upload);
		rl_agent_zhiyeType = (RelativeLayout) findViewById(R.id.rl_agent_zhiyeType);
		// 显示文本
		tv_agent_chongyeshijian = (TextView) findViewById(R.id.tv_agent_chongyeshijian);
		tv_agent_fenjihao = (TextView) findViewById(R.id.tv_agent_fenjihao);
		tv_agent_mainarea = (TextView) findViewById(R.id.tv_agent_mainarea);
		tv_agent_phone = (TextView) findViewById(R.id.tv_agent_phone);
		tv_agent_sex = (TextView) findViewById(R.id.tv_agent_sex);
		tv_agent_shenfenzheng = (TextView) findViewById(R.id.tv_agent_shenfenzheng);
		tv_agent_viptype = (TextView) findViewById(R.id.tv_agent_viptype);
		tv_agent_zhiyeType = (TextView) findViewById(R.id.tv_agent_zhiyeType);
		tv_agent_realname = (TextView) findViewById(R.id.tv_agent_realname);
		// 图片
		iv_agent_Photo = (CircleImageView) findViewById(R.id.iv_agent_Photo);
		// 整个框架
		agent_changeshow = (MyReboundScrollView) findViewById(R.id.agent_changeshow);

		// 真实姓名栏目
		ll_agent_changerealname = (LinearLayout) findViewById(R.id.ll_agent_changerealname);
		ll_agent_changerealname_back = (LinearLayout) findViewById(R.id.ll_agent_changerealname_back);
		tv_agent_changerealname_save = (TextView) findViewById(R.id.tv_agent_changerealname_save);
		et_agent_changrealname = (EditText) findViewById(R.id.et_agent_changrealname);
		// 分机号
		ll__aegnt_changfenjiahao = (LinearLayout) findViewById(R.id.ll_aegnt_changfenjiahao);
		ll_agent_changefenjihao_back = (LinearLayout) findViewById(R.id.ll_agent_changefenjihao_back);
		agent_fenjihao_jiebang = (LinearLayout) findViewById(R.id.agent_fenjihao_jiebang);
		agent_fenjihao_shenqing = (LinearLayout) findViewById(R.id.agent_fenjihao_shenqing);
		tv_agent_fenjihao_show = (TextView) findViewById(R.id.tv_agent_fenjihao_show);
		tv_agent_fenjihao_show_1 = (TextView) findViewById(R.id.tv_agent_fenjihao_show_1);
		btn_agent_changefenjihao_shenqing = (Button) findViewById(R.id.btn_agent_changefenjihao_shenqing);
		btn_agent_changefenjihao_jiebang = (Button) findViewById(R.id.btn_agent_changefenjihao_jiebang);
		// 个人说明
		ll_agent_gerenjieshao = (LinearLayout) findViewById(R.id.ll_agent_gerenjieshao);
		ll_agent_changegerenjieshao_back = (LinearLayout) findViewById(R.id.ll_agent_changegerenjieshao_back);
		tv_agent_changegerenjieshao_save = (TextView) findViewById(R.id.tv_agent_changegerenjieshao_save);
		et_agent_changegerenjieshao = (EditText) findViewById(R.id.et_agent_changegerenjieshao);
		// 身份证
		ll_agent_changeshenfenzheng = (LinearLayout) findViewById(R.id.ll_agent_changeshenfenzheng);
		ll_agent_changeshenfenzheng_back = (LinearLayout) findViewById(R.id.ll_agent_changeshenfenzheng_back);
		tv_agent_changeshenfenzheng_save = (TextView) findViewById(R.id.tv_agent_changeshenfenzheng_save);
		et_agent_changeshenfenzheng = (EditText) findViewById(R.id.et_agent_changeshenfenzheng);
		// 公司名称
		ll_agent_changecompanyname = (LinearLayout) findViewById(R.id.ll_agent_changecompanyname);
		ll_agent_changecompanyname_back = (LinearLayout) findViewById(R.id.ll_agent_changecompanyname_back);
		tv_agent_changecompanyname_save = (TextView) findViewById(R.id.tv_agent_changecompanyname_save);
		et_agent_changecompanyname = (EditText) findViewById(R.id.et_agent_changecompanyname);
		// 密码修改
		ll_agent_changepassword = (LinearLayout) findViewById(R.id.ll_agent_changepassword);
		ll_agent_changepassword_back = (LinearLayout) findViewById(R.id.ll_agent_changepassword_back);
		et_agent_change_currentpassword = (EditText) findViewById(R.id.et_agent_change_currentpassword);
		et_agent_change_Newpassword = (EditText) findViewById(R.id.et_agent_change_Newpassword);
		et_agent_change_repassword = (EditText) findViewById(R.id.et_agent_change_repassword);
		btn_agent_changepassword_submit = (Button) findViewById(R.id.btn_agent_changepassword_submit);

	}

	@Override
	public void changeInfo(final String info) {

		Message msg = new Message();
		handler.sendMessage(msg);

		MyToastShowCenter.CenterToast(getApplicationContext(), info);

		handler.postDelayed(new Runnable() {
			public void run() {
				if (info.equals("密码修改成功")) {
					HouseGoApp.clearData(userinfo);
					onResume();
					startActivity(new Intent(getBaseContext(), LoginActivity.class));
				}
			}
		}, 1000);

	}
	
	
	/**
	 * 标签，与地铁线
	 * 
	 * @param Data
	 *            数据源，需要展示的数据
	 * @param tv
	 *            确认后，所需要显示的什么空间上
	 * @param title
	 *            中间显示条目的内容
	 */

	public void CityCheckDialog() {
		final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.show();
		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setContentView(R.layout.alerlog_check_city);
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		TextView tvSure = (TextView) window.findViewById(R.id.tv_wheel_sure);
		TextView tvCancle = (TextView) window.findViewById(R.id.tv_wheel_cancle);
		TextView tvTitle = (TextView) window.findViewById(R.id.tv_wheel_title);

		final CheckBox cb1 = (CheckBox) window.findViewById(R.id.check_1);
		final CheckBox cb2 = (CheckBox) window.findViewById(R.id.check_2);
		final CheckBox cb3 = (CheckBox) window.findViewById(R.id.check_3);
		final CheckBox cb4 = (CheckBox) window.findViewById(R.id.check_4);
		final CheckBox cb5 = (CheckBox) window.findViewById(R.id.check_5);
		final CheckBox cb6 = (CheckBox) window.findViewById(R.id.check_6);
		final CheckBox cb7 = (CheckBox) window.findViewById(R.id.check_7);
		final CheckBox cb8 = (CheckBox) window.findViewById(R.id.check_8);
		final CheckBox cb9 = (CheckBox) window.findViewById(R.id.check_9);
		final CheckBox cb10 = (CheckBox) window.findViewById(R.id.check_10);
		final CheckBox cb11= (CheckBox) window.findViewById(R.id.check_11);
		final CheckBox cb12= (CheckBox) window.findViewById(R.id.check_12);
		
		cb12.
		setOnCheckedChangeListener(
				new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb12 = ","+(String) buttonView.getText().toString();
				} else {
					tcb12 ="";
				}
			}
		});
		
		cb11.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb11 = ","+(String) buttonView.getText().toString();
				} else {
					tcb11 = "";
				}
			}
		});
		cb10.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb10 = ","+(String) buttonView.getText().toString();
				} else {
					tcb10 = "";
				}
			}
		});
		cb9.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb9 = ","+(String) buttonView.getText().toString();
				} else {
					tcb9 = "";
				}
			}
		});
		cb8.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb8 = ","+(String) buttonView.getText().toString();
				} else {
					tcb8 = "";
				}
			}
		});
		cb7.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb7 = ","+(String) buttonView.getText().toString();
				} else {
					tcb7 = "";
				}
			}
		});
		
		cb6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb6 = ","+(String) buttonView.getText().toString();
				} else {
					tcb6 = "";
				}
			}
		});
		cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb1 = ","+(String) buttonView.getText().toString();
				} else {
					tcb1 = "";
				}
			}
		});
		cb2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb2 = ","+(String) buttonView.getText().toString();
				} else {
					tcb2 = "";
				}
			}
		});
		cb3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb3 = ","+(String) buttonView.getText().toString();
				} else {
					tcb3 = "";
				}
			}
		});
		cb4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb4 = ","+(String) buttonView.getText().toString();
				} else {
					tcb4 = "";
				}
			}
		});
		cb5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					tcb5 = ","+(String) buttonView.getText().toString();
				} else {
					tcb5 = "";
				}
			}
		});

		tvSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String cb=tcb1+tcb2+tcb3+tcb4+tcb5+tcb6+tcb7+tcb8+tcb9+tcb10+tcb11+tcb12;
				
				if(cb.startsWith(",")){
					String tx=cb.substring(1);
					userinfopresenter.ChangeMainArea(userinfo.getUserid(), tx);
					tv_agent_mainarea.setText(tx.toString());
				}else{
					String tx=cb;
					userinfopresenter.ChangeMainArea(userinfo.getUserid(), tx);
					tv_agent_mainarea.setText(tx.toString());
				}
				
				alertDialog.cancel();
			}
		});
		tvCancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.cancel();
			}
		});

	}

	@Override
	public void onUploadProcess(int uploadSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initUpload(int fileSize) {
		// TODO Auto-generated method stub
		
	}


}
