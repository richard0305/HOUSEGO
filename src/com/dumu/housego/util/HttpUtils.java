package com.dumu.housego.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * ��װ��������
 */
public class HttpUtils {
	
	/**
	 * ����get���� ��ȡ����˷��ص�������
	 * @param url
	 * @return
	 * @throws IOExceptionException 
	 */
	public static InputStream get(String path) throws IOException{
		URL url = new URL(path);
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		InputStream is = conn.getInputStream();
		return is;
	}
	
	/**
	 * ����post����  
	 * @param path  ������Դ·��
	 * @param paramMap ��������  ���û�в��� ��Ϊnull
	 * @return  ����˷��ص�������
	 */
	public static InputStream post(String path, Map<String, String> paramMap) throws IOException {
		URL url = new URL(path);
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		//���ò���
		conn.setDoOutput(true);
		StringBuilder params = new StringBuilder();
		if(paramMap != null){
			//��paramMap�����еĲ�����ƴ�ӵ�params��
			Set<String> keys=paramMap.keySet();
			Iterator<String> ite = keys.iterator();
			while(ite.hasNext()){
				String key = ite.next();
				String value = paramMap.get(key);
				//��key=valueƴ�ӵ�params�ַ�����
				params.append(key+"="+value+"&");
			}
			//key=value&key=value&key=value&
			params.deleteCharAt(params.length()-1);
			//��params��Ϊ��������������
			OutputStream os = conn.getOutputStream();
			os.write(params.toString().getBytes("utf-8"));
			os.flush();
		}
		//��ȡ������
		InputStream is = conn.getInputStream();
		return is;
	}
	
	/**
	 * �������� ����utf-8�������Ϊ�ַ���
	 * @param is
	 * @return �����ɹ����ַ���
	 */
	public static String isToString(InputStream is)throws IOException{
		StringBuilder sb = new StringBuilder();
		String line = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		while((line = reader.readLine()) != null){
			sb.append(line);
		}
		return sb.toString();
	}
	
}



