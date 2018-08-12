package com.frid.tool;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;
/**
 * 工具类集合
 * */
public class FTool {
	/**
	 * 设置drawableTop上图片大小
	 * @param tv 
	 */
	public static void setDrawableTop(TextView tv,int ii){
		Drawable drawable = tv.getCompoundDrawables()[1];
		drawable.setBounds(0, 0,ii,ii);
		tv.setCompoundDrawables(null, drawable, null, null);
	}
	
	/**
	 * 确保传输不会报错~
	 * */
	public static String ts(Object ss) {
		String s = "";
		try { s = ss.toString().trim(); } catch (Exception e) {
			try { s = ss+"".toString(); } catch (Exception e1) {
				return "";
			}
		}
		if(s == null || s.equals("null") || s.equals("NULL")) s = "";
		return s;
	}

	/**
	 * 获取当前时间
	 * */
	public static String getCurrentTime() {
		Date date = new Date();
		//format  返回的时间样式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
		String currentTime = sdf.format(date);
		return currentTime;
	}

	/**
	 * 获取当前时间
	 * 2018-04-07T16:24:27.8129704+08:00
	 * */
	@SuppressLint("SimpleDateFormat") 
	public static String getCurrentDate() {
		Date date = new Date();//2018-04-10T15:44:55.811+0800
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+08:00");
		String str = df.format(date);
		return str;
	}
	
	/**
	 * 生成随机字符串
	 * */ 
	public static String getRandomString(int length) { //length表示生成字符串的长度
		String base = "QWERTYUIOPASDFGHJKLZXCVBNM0123456789";   
		Random random = new Random();   
		StringBuffer sb = new StringBuffer();   
		for (int i = 0; i < length; i++) {   
			int number = random.nextInt(base.length());   
			sb.append(base.charAt(number));   
		}   
		return sb.toString();   
	}  

	/**
	 * Uri转文件路径
	 * */
	public static String getPath(Context context, Uri uri) {



		if ("content".equalsIgnoreCase(uri.getScheme())) {

			String[] projection = { "_data" };

			Cursor cursor = null;



			try {

				cursor = context.getContentResolver().query(uri, projection,null, null, null);

				int column_index = cursor.getColumnIndexOrThrow("_data");

				if (cursor.moveToFirst()) {

					return cursor.getString(column_index);

				}

			} catch (Exception e) {

				// Eat it

			}

		}



		else if ("file".equalsIgnoreCase(uri.getScheme())) {

			return uri.getPath();

		}



		return null;

	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param fileName
	 * @return 文件后缀名
	 */
	public static String getFileType(String fileName) {
		if (fileName != null) {
			int typeIndex = fileName.lastIndexOf(".");
			if (typeIndex != -1) {
				String fileType = fileName.substring(typeIndex + 1)
						.toLowerCase();
				return fileType;
			}
		}
		return "";
	}

	/**
	 * 根据后缀名判断是否是图片文件
	 * 
	 * @param type
	 * @return 是否是图片结果true or false
	 */
	public static boolean isImage(String type) {
		if (type != null
				&& (type.equals("jpg") || type.equals("gif")
						|| type.equals("png") || type.equals("jpeg")
						|| type.equals("bmp") || type.equals("wbmp")
						|| type.equals("ico") || type.equals("jpe"))) {
			return true;
		}
		return false;
	}

	/**
	 * 格式秒到HH：mm：ss字符串
	 *
	 * @param seconds seconds
	 * @return String of formatted in HH:mm:ss
	 */
	public static String seconds2HH_mm_ss(long seconds) {

		long h = 0;
		long m = 0;
		long s = 0;
		long temp = seconds % 3600;

		if (seconds > 3600) {
			h = seconds / 3600;
			if (temp != 0) {
				if (temp > 60) {
					m = temp / 60;
					if (temp % 60 != 0) {
						s = temp % 60;
					}
				} else {
					s = temp;
				}
			}
		} else {
			m = seconds / 60;
			if (seconds % 60 != 0) {
				s = seconds % 60;
			}
		}

		String dh = h < 10 ? "0" + h : h + "";
		String dm = m < 10 ? "0" + m : m + "";
		String ds = s < 10 ? "0" + s : s + "";

		return dh + ":" + dm + ":" + ds;
	}

	/**
	 * 返回byte的数据大小对应的文本
	 * 
	 * @param size
	 * @return
	 */
	public static String kBMB(int size) {
		if (size < 0) {
			size = 0;
		}
		DecimalFormat formater = new DecimalFormat("####.00");
		String temp = "0MB";
		try {
			if (size < 1024) {
				temp =  size + "bytes";
			} else if (size < 1024 * 1024) {
				float kbsize = size / 1024f;
				temp =  formater.format(kbsize) + "KB";
			} else if (size < 1024 * 1024 * 1024) {
				float mbsize = size / 1024f / 1024f;
				temp =  formater.format(mbsize) + "MB";
			} else if (size < 1024 * 1024 * 1024 * 1024) {
				float gbsize = size / 1024f / 1024f / 1024f;
				temp =  formater.format(gbsize) + "GB";
			} else {
				temp =  "size: error";
			}
		} catch (Exception e) {
			Log.e("", "");
		}
		return temp;
	}


}
