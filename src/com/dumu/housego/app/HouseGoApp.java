package com.dumu.housego.app;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.xutils.x;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.mapapi.SDKInitializer;
import com.dumu.housego.R;
import com.dumu.housego.entity.User;
import com.dumu.housego.entity.UserInfo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class HouseGoApp extends Application {
	private static HouseGoApp context;
	private static RequestQueue Queue;
	private static HouseGoApp housegoapp;
	private User user;
	private UserInfo userinfo;

	public static HouseGoApp getContext() {
		return context;
	}

	public static RequestQueue getQueue() {
		return Queue;
	}

	public HouseGoApp() {
		super();

	}

	public static HouseGoApp getHousegoapp() {
		return housegoapp;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		housegoapp=this;
		context = this;
		Queue = Volley.newRequestQueue(context);
		x.Ext.init(getHousegoapp());
		x.Ext.setDebug(true);
		  //��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext  
        //ע��÷���Ҫ��setContentView����֮ǰʵ��  
        SDKInitializer.initialize(getApplicationContext());  
		
		
		   
	}

	/**
	 * ���浱ǰ�û�
	 */
	public void SaveCurrentUser(User user) {
		this.user = user;
	}

	public User getCurrentUser() {
		return this.user;

	}

	/**
	 * ���浱ǰ�û���Ϣ
	 */
	public void SaveCurrentUserInfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public UserInfo getCurrentUserInfo() {
		return this.userinfo;

	}
	
	/**
     * ʹ��SharedPreferences�����û���¼��Ϣ
     * @param context
     * @param username
     * @param password
     */
    public static void saveLoginInfo(Context context,UserInfo userinfo){
        //��ȡSharedPreferences����
        SharedPreferences sharedPre=context.getSharedPreferences("config", context.MODE_PRIVATE);
        //��ȡEditor����
        Editor editor=sharedPre.edit();
        //���ò���
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(userinfo);
            String string64 = new String(Base64.encode(baos.toByteArray(),
                    0));
          //�ύ
            editor.putString("token", string64).commit();
        }catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public static UserInfo getLoginInfo(Context context) {
    	UserInfo userinfo = null;
        try {
            String base64 = context.getSharedPreferences("config", context.MODE_PRIVATE).getString("token", "");
            if (base64.equals("")) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            userinfo = (UserInfo) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userinfo;
    
    }

}
