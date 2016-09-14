package com.dumu.housego;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.bumptech.glide.Glide;
import com.dumu.housego.app.HouseGoApp;
import com.dumu.housego.entity.UserInfo;
import com.dumu.housego.model.IChangeHeadPhotoModel;
import com.dumu.housego.presenter.ChangeHeadPhotoPresenter;
import com.dumu.housego.presenter.IChangeHeadPhotoPresenter;
import com.dumu.housego.util.CircleImageView;
import com.dumu.housego.util.FontHelper;
import com.dumu.housego.view.IChangeHeadPhotoView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalMainActivity extends Activity implements IChangeHeadPhotoView{
	private LinearLayout llPersonalBack;
	private RelativeLayout rlPersonalInfo, rlMyTouxiang;
	private TextView tvPersonalNickname,tvPersonalPhone;
	private UserInfo userinfo;
	private CircleImageView ivPersonalPhoto;
	private Bitmap head;//头像Bitmap
    private static String path="/sdcard/myHead/";//sd路径
    private IChangeHeadPhotoPresenter headpresenter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_main);
		FontHelper.injectFont(findViewById(android.R.id.content));
		setViews();
		setListeners();
		
		userinfo=HouseGoApp.getContext().getCurrentUserInfo();
		if(userinfo==null){
			Toast.makeText(getApplicationContext(), "您还没有登录，请先登录！", Toast.LENGTH_SHORT).show();
		}else{
			String url=userinfo.getUserpic();
			Glide.with(getApplicationContext()).load(url).into(ivPersonalPhoto);
			userinfo=HouseGoApp.getContext().getCurrentUserInfo();
			tvPersonalNickname.setText(userinfo.getNickname());
			tvPersonalPhone.setText(userinfo.getUsername());
		}
		
		
		headpresenter=new ChangeHeadPhotoPresenter(this);
		
		
	}

	private void setListeners() {
		llPersonalBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PersonalMainActivity.this.finish();

			}
		});
		
		rlMyTouxiang.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(PersonalMainActivity.this).setTitle("图片来源")
				.setItems(new String[]{"相册","相机"}, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch(which){
						case 0:
							//从相册里面取照片
				            Intent intent1 = new Intent(Intent.ACTION_PICK, null);
				            intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				            startActivityForResult(intent1, 1);
						break;
						case 1://调用相机拍照
				            Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				            intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
				                            "head.jpg")));
				            startActivityForResult(intent2, 2);//采用ForResult打开
						break;
						}
						
					}
				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch(which){
						case 0:finish();
						break;
						}
						
					}
				}).show();

			}
		});
		
		
		
		rlPersonalInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(getApplicationContext(), ChangeUserInfoActivity.class));
			}
		});

	}

	private void setViews() {
		llPersonalBack = (LinearLayout) findViewById(R.id.ll_personal_back);
		rlPersonalInfo = (RelativeLayout) findViewById(R.id.rl_personal_info);
		rlMyTouxiang = (RelativeLayout) findViewById(R.id.rl_MyTouxiang);
		tvPersonalNickname=(TextView) findViewById(R.id.tv_personal_nickname);
		tvPersonalPhone=(TextView) findViewById(R.id.tv_personal_phone);
		ivPersonalPhoto=(CircleImageView) findViewById(R.id.iv_personal_Photo);
		
		  Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");//从Sd中找头像，转换成Bitmap
	        if(bt!=null){
	            @SuppressWarnings("deprecation")
	            Drawable drawable = new BitmapDrawable(bt);//转换成drawable
	            ivPersonalPhoto.setImageDrawable(drawable);
	        }else{
	            /**
	             *  如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
	             * 
	             */
	        }
		
		
	}
	
	
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        switch (requestCode) {
	        
	        case 1:
	            if (resultCode == RESULT_OK) {
	                cropPhoto(data.getData());//裁剪图片
	            }
	 
	            break;
	            
	        case 2:
	            if (resultCode == RESULT_OK) {
	                File temp = new File(Environment.getExternalStorageDirectory()
	                        + "/head.jpg");
	                cropPhoto(Uri.fromFile(temp));//裁剪图片
	            }
	 
	            break;
	            
	        case 3:
	            if (data != null) {
	                Bundle extras = data.getExtras();
	                head = extras.getParcelable("data");
	                if(head!=null){
	                    /**
	                     * 上传服务器代码
	                     */
	                	String userid=userinfo.getUserid();
	                	headpresenter.ChangeHead(userid, head);
	                	
	                	
	                    setPicToView(head);//保存在SD卡中
	                    ivPersonalPhoto.setImageBitmap(head);//用ImageView显示出来
	                }
	            }
	            break;
	        default:
	            break;
	 
	        }
	        super.onActivityResult(requestCode, resultCode, data);
	    }
	
	
    /**
     * 调用系统的裁剪
     * @param uri
     */
    public void cropPhoto(Uri uri) {
    	
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
         // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
        
    }
    private void setPicToView(Bitmap mBitmap) {
         String sdStatus = Environment.getExternalStorageState();  
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用  
               return;  
           }  
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName =path + "head.jpg";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
 
        }


}

	@Override
	public void changeHeadFail(String errorMessage) {
		Toast.makeText(getApplicationContext(), "头像更换失败！", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void changeHeadSuccess(String picUrl) {
		
		Glide.with(getApplicationContext()).load(picUrl).into(ivPersonalPhoto);
	Toast.makeText(getApplicationContext(), "头像更换成功！", Toast.LENGTH_SHORT).show();
	}
}
