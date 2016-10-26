package com.ebaonet.lawyer.core.http;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ebaonet.lawyer.configs.GlobalConfig;
import com.ebaonet.lawyer.core.entity.NetBaseBean;
import com.ebaonet.lawyer.ui.dialog.TwoSelectDialog;
import com.ebaonet.lawyer.ui.dialog.loginWithoutEffectSelectDialog;
import com.ebaonet.lawyer.ui.presenter.activity.LoginActivity;
import com.ebaonet.lawyer.util.LogUtil;
import com.ebaonet.lawyer.util.sharePreferences.PreferencesManger;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yanglaoClient.http.OkHttpUtils;
import com.yanglaoClient.http.callback.StringCallback;

import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 作者：yzb on 2016/6/30 14:40
 * 邮箱：280766447@qq.com
 */
public class MyRequestClient {

    public static final String BASE_URL = GlobalConfig.API_URL;
    private static Handler handler = new Handler();
    private static Toast mToast;
    private static TwoSelectDialog twoSelectDialog;
    private static loginWithoutEffectSelectDialog reLoginSelectDialog;
    private static Runnable run = new Runnable() {
        @Override
        public void run() {
            if (mToast != null) {
                mToast.cancel();
            }

        }
    };

    /**
     * post方式调用接口
     *
     * @param context
     * @param params
     * @param tag
     * @param callback
     */
    public static void post(final Context context, String api, Map params, final String tag, final Callback<NetBaseBean> callback) {
        OkHttpUtils
                .post()
                .url(BASE_URL + api)
                .params(params)
//                .addHeader("Cookie", getCookie(context))
                .build().connTimeOut(20000)
                .execute(new StringCallback() {

                    @Override
                    public String parseNetworkResponse(Response response) throws IOException {
//                        if (tag != null) {
//                            if (tag.equals("login")) {
//                                List<String> list = response.headers("Set-Cookie");
//                                String cookie = "";
//                                for (int i = 0; i < list.size(); i++) {
//                                    String item = list.get(i);
//                                    if (item.startsWith("ebao")) {
//                                        if (!item.startsWith("ebaormid=delete")) {
//                                            cookie = item;
//                                        }
//                                    }
//                                }
//                                PreferencesManger.setCookie(context, cookie);
//                                Log.e("header", cookie);
//                            }
//                        }
                        return super.parseNetworkResponse(response);
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        LogUtil.getLogger().e(e);
                        if (e instanceof SocketTimeoutException) {

                            toast(context, "网络超时", Toast.LENGTH_LONG);
                        }
                        NetBaseBean bean = new NetBaseBean();
                        try {
                            bean = NetBaseBean.getObjectFromJson(e.toString());

                        } catch (Exception e1) {
                            callback.onError(bean);
                        }
                        callback.onError(bean);
                    }

                    @Override
                    public void onResponse(String response) {
                        LogUtil.getLogger().e(response);
                        NetBaseBean bean = NetBaseBean.getObjectFromJson(response);
                        try {
                            try {
                                if (bean.getCode() == 22102) {
                                    Toast.makeText(context, bean.getMessage(), Toast.LENGTH_LONG).show();
//                                    exitApp(context);
                                }
//                                else if (bean.getCode() == 60007||bean.getCode() == 1000) {
//                                    Toast.makeText(context, bean.getMessage(), Toast.LENGTH_LONG).show();
//                                    exitApp(context);
//                                }
                                else {
                                    callback.callback(bean);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public static void post(final Context context, String api, Map<String ,String> params, final Callback<NetBaseBean> callback) {
        getUrlWithQueryString(false,BASE_URL+api,params);
        OkHttpUtils
                .post()
                .params(params)
//                .addHeader("Cookie", getCookie(context))
                .url(BASE_URL + api)
                .build().connTimeOut(20000).readTimeOut(25000).writeTimeOut(25000)

                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        LogUtil.getLogger().e(e);
                        if (e instanceof SocketTimeoutException) {
                            toast(context, "网络超时", Toast.LENGTH_LONG);
                        }
                        NetBaseBean bean = new NetBaseBean();
                        callback.onError(bean);
                    }

                    @Override
                    public void onResponse(String response) {
                        LogUtil.getLogger().e(response);
                        NetBaseBean bean = NetBaseBean.getObjectFromJson(response);
                        switch (bean.getCode()){
                            case 1000:
                                clearApp(context);
                                if (twoSelectDialog!=null&&twoSelectDialog.isShowing()){
                                    return;
                                }
                                if (reLoginSelectDialog!=null&&reLoginSelectDialog.isShowing()){
                                    return;
                                }

                                showSelectDialog(context, true, "登录失效", "您的登录已过期,请重新登录!", true, true, new TwoSelectDialog.OnDialogClickListener() {
                                    @Override
                                    public void onOneClick(View v) {
                                        //  reLoginSelectDialog.dismiss();
                                    }

                                    @Override
                                    public void onTwoClick(View v) {
                                        //reLoginSelectDialog.dismiss();
                                        LoginActivity.actionActivity(context);

                                    }
                                });

                                break;

                        }
                        try {
                            callback.callback(bean);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    public interface Callback<T> {
        void callback(T data) throws JSONException, IOException, ClassNotFoundException;

        void onError(T data);
    }

    private static void clearApp(final  Context context){
        PreferencesManger.setPassword(context, "");
    }
/*退出登录*/
    public static TwoSelectDialog showSelectDialog(Context context, boolean isShowSingleSlect, String title, String msg, boolean canCanceledOnTouch, boolean canCancel, TwoSelectDialog.OnDialogClickListener onDialogClickListener) {
        if (twoSelectDialog != null) {
            twoSelectDialog.dismiss();
            twoSelectDialog = null;
        }
        twoSelectDialog = new TwoSelectDialog(context);
        twoSelectDialog.setText(title, msg);
        twoSelectDialog.setButtonName("取消", "确认");
        if(isShowSingleSlect){
            twoSelectDialog.setTextView1Gone();

        }
        twoSelectDialog.setCanceledOnTouchOutside(canCanceledOnTouch);
        twoSelectDialog.setCanCancel(canCancel);
        twoSelectDialog.setOnClickSelect(onDialogClickListener);
        twoSelectDialog.show();
        return twoSelectDialog;
    }

    private static void toast(Context context, CharSequence charSequence, int duration) {
        handler.removeCallbacks(run);
        int tempDuration = 0;
        switch (duration) {
            case Toast.LENGTH_LONG:
                tempDuration = 1000;
                break;
            case Toast.LENGTH_SHORT:
                tempDuration = 3000;
                break;
            default:
                break;

        }
        if (mToast != null) {
            mToast.setText(charSequence);
        } else {
            mToast = Toast.makeText(context, charSequence, duration);

        }
        handler.postDelayed(run, tempDuration);
        mToast.show();
    }

    public static String getUrlWithQueryString(boolean shouldEncodeUrl, String url, Map<String, String> params) {
        if (url == null)
            return null;

        if (shouldEncodeUrl) {
            try {
                url = URLEncoder.encode(url, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // Should not really happen, added just for sake of validity
                Log.e("", "getUrlWithQueryString encoding URL", e);
            }
        }
        String newUrl = url + "?";
        if (params != null) {
            for (String key : params.keySet()) {
                String str = key + "=" + params.get(key) + "&";
                newUrl = newUrl + str;
            }
        }
        newUrl=newUrl.substring(0,newUrl.length()-1);
        Log.e("BaseUrl", newUrl);
        return url;
    }

    /**
     * 展示 登录失效返回的1000状态码， 取消或去登录页面
     * */

    public static loginWithoutEffectSelectDialog showReloginSlectDialog(Context context, boolean isShowSingleSlect, String title, String msg, boolean canCanceledOnTouch, boolean canCancel, loginWithoutEffectSelectDialog.OnDialogClickListener onDialogClickListener) {
        if (reLoginSelectDialog != null) {
            reLoginSelectDialog.dismiss();
            reLoginSelectDialog = null;
        }
        reLoginSelectDialog = new loginWithoutEffectSelectDialog(context);
        reLoginSelectDialog.setText(title, msg);
        reLoginSelectDialog.setButtonName("取消", "确认");
        if(isShowSingleSlect){
            reLoginSelectDialog.setTextView1Gone();

        }
        reLoginSelectDialog.setCanceledOnTouchOutside(canCanceledOnTouch);
        reLoginSelectDialog.setCanCancel(canCancel);
        reLoginSelectDialog.setOnClickSelect(onDialogClickListener);
        reLoginSelectDialog.show();
        return reLoginSelectDialog;
    }


}
