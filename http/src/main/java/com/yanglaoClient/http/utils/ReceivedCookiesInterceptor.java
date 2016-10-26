package com.yanglaoClient.http.utils;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by xu.tang on 2016/6/13.
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        List<String> cookieList = originalResponse.headers("Set-Cookie");
        String cookie = "";
        if (cookieList !=null&&cookieList.size()!=0){
            for (String s:cookieList){
               cookie += s;
            }
            Log.e("cookie",cookie);
            PreferenceCookieManager.getInstance().setCookieValue(cookie);

        }
        return originalResponse;
    }
}
