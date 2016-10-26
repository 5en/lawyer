package com.yanglaoClient.mvp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.yanglaoClient.mvp.presenter.BaseApplication;


public class SpUtils {

	private static SharedPreferences sp;
	private static final String CONFIG_SETTING = "config_setting";

	static {
		sp = BaseApplication.getApplication().getSharedPreferences(CONFIG_SETTING, Context.MODE_PRIVATE);
	}

	private SpUtils() {
	}
	
	public static SharedPreferences getSp(){
		return sp;
	}

	/**
	 * 设置第一次启动
	 * 
	 * @param isFirst
	 */
	public static void setFirstLaunch(boolean isFirst) {
		sp.edit().putBoolean("isfirst", isFirst).commit();
	}

	/**
	 * 是否第一次启动
	 * 
	 * @return
	 */
	public static boolean isFirst() {
		return sp.getBoolean("isfirst", true);
	}

	/**
	 * 设置是否有手势密码
	 * 
	 * @param isGesture
	 */
	public static void setGesturePwd(String userId, boolean isGesture) {
		sp.edit().putBoolean(userId + "_isGesture", isGesture).commit();
	}

	/**
	 * 获取是否有手势密码
	 * 
	 * @return
	 */
	public static boolean isHavGesture(String userId) {
		return sp.getBoolean(userId + "_isGesture", false);
	}

	/**
	 * 设置是否第一次进入就诊记录
	 * 
	 * @return
	 */
	public static void setFirstJz(String userId, boolean isFirst) {
		sp.edit().putBoolean(userId + "_isFirst", isFirst).commit();
	}

	/**
	 * 获取是否隐藏隐藏病种
	 * 
	 * @return
	 */
	public static boolean isFirstJz(String userId) {
		return sp.getBoolean(userId + "_isFirst", true);
	}

	/**
	 * 设置是否隐藏隐藏病种
	 * 
	 * @return
	 */
	public static void setIsHide(String userId, boolean isHide) {
		sp.edit().putBoolean(userId + "_isHide", isHide).commit();
	}

	/**
	 * 获取是否隐藏隐藏病种
	 * 
	 * @return
	 */
	public static boolean isHide(String userId) {
		return sp.getBoolean(userId + "_isHide", true);
	}

	/**
	 * 设置手势密码
	 * 
	 * @return
	 */
	public static void setGesturePwd(String userId, String pwd) {
		if (TextUtils.isEmpty(pwd)) {
			sp.edit().remove(userId + "_gesture").commit();
			setGestureCount(userId, 0);
		} else {
			sp.edit().putString(userId + "_gesture", Utils.md5(pwd)).commit();
			setGestureCount(userId, 5);
		}
	}

	/**
	 * 获取手势密码
	 * 
	 * @return
	 */
	public static String getGesturePwd(String userId) {
		return sp.getString(userId + "_gesture", "");
	}

	/**
	 * 设置手势错误次数
	 * 
	 * @return
	 */
	public static void setGestureCount(String userId, int count) {
//		if(count<=0){
//			sp.edit().remove(userId + "_gesture_count").commit();
//		}else{
			sp.edit().putInt(userId + "_gesture_count", count).commit();
//		}
	}

	/**
	 * 获取手势密码
	 * 
	 * @return
	 */
	public static int getGestureCount(String userId) {
		return sp.getInt(userId + "_gesture_count", 5);
	}
	
	/**
	 * 设置短信次数
	 * 
	 * @return
	 */
	public static void setSmsTime(String key, long time) {
		sp.edit().putLong(key, time).commit();
	}
	
	/**
	 * 获取短信次数
	 * 
	 * @return
	 */
	public static long getSmsTime(String key) {
		return sp.getLong(key, 0);
	}
}
