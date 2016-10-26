package com.yanglaoClient.mvp.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	
	
	
	
	private static Toast toast;

	/**
	 * 显示Toast提示。
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showToast(Context context, int resId) {
		showToast(context, resId, Toast.LENGTH_SHORT);
	}

	/**
	 * 显示Toast提示。
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showToast(Context context, String msg) {
		showToast(context, msg, Toast.LENGTH_SHORT);
	}
	
	/**
	 * 显示Toast提示。
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showToast(Context context, int resId,int duration) {
		if(toast!=null){
			toast.setText(resId);
		}else{
			toast = Toast.makeText(context, resId, duration);
		}
		toast.show();
	}
	
	/**
	 * 显示Toast提示。
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showToast(Context context, String msg,int duration) {
		if(toast!=null){
			toast.setText(msg);
		}else{
			toast = Toast.makeText(context, msg, duration);
		}
		toast.show();
	}

	public static void stopToast(){
		if(toast!=null){
			toast.cancel();
		}
	}
	
}
