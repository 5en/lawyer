package com.yanglaoClient.http.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by xu.tang on 2016/6/13.
 */
public class PreferenceCookieManager {
    private String TAG = PreferenceCookieManager.class.getSimpleName();
    private static PreferenceCookieManager sington;
    private String preference = "cookie";
    private static Context mContext;
    private String cookieValue;
    public static PreferenceCookieManager getInstance(){
        if (sington ==null){
            sington = new PreferenceCookieManager();
        }
        return sington;
    }

    public static void init(Context context){
        mContext = context;
    }

    private PreferenceCookieManager(){
        read();
    }
    private void read(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(
                preference, Context.MODE_PRIVATE);
         cookieValue = sharedPreferences.getString("cookieName", null);
        Log.e(TAG,cookieValue);
    }

    public String getCookieValue() {
        Log.e(TAG,cookieValue);
        return cookieValue;

    }

    public void setCookieValue(String cookieValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(
                preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cookieName", cookieValue);
        editor.commit();
        this.cookieValue = cookieValue;
    }
}
