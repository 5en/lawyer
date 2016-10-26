package com.ebaonet.lawyer.util.sharePreferences;

import android.content.Context;


/**
 * 用于管理Preferences数据（用于存储数据量不大的数据）（要考虑被清空时应用的动作）
 * Created by dong on 2015/3/12.
 */
public class PreferencesManger {
    /**
     * 第一次安装
     *
     * @param context
     * @param account
     */
    public static void setFirst(Context context, String account) {
        PreferencesUtils.setStringPreferences(context, "first", account);
    }

    public static String getFirst(Context context) {
        return PreferencesUtils.getStringPreference(context, "first", "");
    }
    /**
     * 账号持久化存储
     *
     * @param context
     * @param account
     */
    public static void setAccount(Context context, String account) {
        PreferencesUtils.setStringPreferences(context, "account", account);
    }

    public static String getAccount(Context context) {
        return PreferencesUtils.getStringPreference(context, "account", "");
    }

    /**
     * 社保账号持久化存储
     *
     * @param context
     * @param code 社保账号
     */
    public static void setCode(Context context, String code) {
        PreferencesUtils.setStringPreferences(context, "code", code);
    }

    public static String getCode(Context context) {
        return PreferencesUtils.getStringPreference(context, "code", "");
    }

    /*存储律言登录身份*/

    public static void setIdentity(Context context,String code){
        PreferencesUtils.setStringPreferences(context, "identity", code);
    }

    public static String getIdentity(Context context) {
        return PreferencesUtils.getStringPreference(context, "identity","");
    }

//    /**
//     * cookie持久化存储
//     *
//     * @param context
//     * @param cookie
//     */
//    public static void setCookie(Context context, String cookie) {
//        PreferencesUtils.setStringPreferences(context, "cookie", cookie);
//    }
//
//    public static String getCookie(Context context) {
//        return PreferencesUtils.getStringPreference(context, "cookie", "");
//    }

    /**
     * 密码的持久化存储
     *
     * @param context
     * @param password
     */

    public static void setPassword(Context context, String password) {
        PreferencesUtils.setStringPreferences(context, "password", password);
    }

    public static String getPassword(Context context) {
        return PreferencesUtils.getStringPreference(context, "password", "");
    }

    public static boolean getMessagePush(Context context) {
        return PreferencesUtils.getBooleanPreference(context,"messagePush",true);
    }
    public static void setMessagePush(Context context,boolean isPush) {
         PreferencesUtils.setBooleanPreference(context,"messagePush",isPush);
    }

}
