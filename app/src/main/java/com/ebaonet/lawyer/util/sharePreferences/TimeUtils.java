package com.ebaonet.lawyer.util.sharePreferences;

import java.text.SimpleDateFormat;

/**
 * Created by tx on 2016/8/1.
 */
public class TimeUtils {
    public static String translateTime(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return  simpleDateFormat.format(time);
    }
}
