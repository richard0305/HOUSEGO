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
		  //在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  
        SDKInitializer.initialize(getApplicationContext());  
		
		
		   
	}

	/**
	 * 保存当前用户
	 */
	public void SaveCurrentUser(User user) {
		this.user = user;
	}

	public User getCurrentUser() {
		return this.user;

	}

	/**
	 * 保存当前用户信息
	 */
	public void SaveCurrentUserInfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public UserInfo getCurrentUserInfo() {
		return this.userinfo;

	}
	
	/**
     * 使用SharedPreferences保存用户登录信息
     * @param context
     * @param username
     * @param password
     */
    public static void saveLoginInfo(Context context,UserInfo userinfo){
        //获取SharedPreferences对象
        SharedPreferences sharedPre=context.getSharedPreferences("config", context.MODE_PRIVATE);
        //获取Editor对象
        Editor editor=sharedPre.edit();
        //设置参数
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(userinfo);
            String string64 = new String(Base64.encode(baos.toByteArray(),
                    0));
          //提交
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
