package com.ebaonet.lawyer.core.http.biz;

import android.content.Context;

import com.ebaonet.lawyer.configs.GlobalConfig;
import com.ebaonet.lawyer.core.entity.NetBaseBean;
import com.ebaonet.lawyer.core.http.MyRequestClient;
import com.ebaonet.lawyer.util.Base64Code;

import java.util.HashMap;

/**
 * Created by tx on 2016/10/19.
 */

public class PersonBiz  {
    /*登录接口*/
    public static void login(Context context, String tel, String password, MyRequestClient.Callback<NetBaseBean> callback) {
        HashMap<String ,String> params = new HashMap();
        params.put("tel", tel);//用户名
        params.put("password", Base64Code.getCode(password));

        MyRequestClient.post(context, GlobalConfig.login, params, "login", callback);
    }
    /*登录接口*/
    public static void loginOut(Context context, String registration_id, String sys_type, MyRequestClient.Callback<NetBaseBean> callback) {
        HashMap<String ,String> params = new HashMap();
        params.put("registration_id", registration_id);//用户名
        params.put("sys_type", sys_type);//密码


        MyRequestClient.post(context, GlobalConfig.loginOut, params, callback);
    }


    /*注册接口*/
    public static void register(Context context, String user_name, String phone_no,String verify_code,
                                String password,String app_ver,MyRequestClient.Callback<NetBaseBean> callback) {
        HashMap<String ,String> params = new HashMap();
        params.put("user_name", user_name); //昵称
        params.put("phone_no", phone_no); //电话号
        params.put("verify_code",verify_code); //验证码
        params.put("password",password);  //密码
        params.put("app_ver",app_ver); //app版本

        MyRequestClient.post(context, GlobalConfig.register, params, callback);
    }


    /*忘记密码*/
    public static void forgetPassword(Context context,String phone_no,String verify_code,
                                String password,MyRequestClient.Callback<NetBaseBean> callback) {
        HashMap<String ,String> params = new HashMap();
        params.put("phone_no", phone_no); //电话号码
        params.put("verify_code",verify_code); //验证码
        params.put("password",password);  //密码

        MyRequestClient.post(context, GlobalConfig.forgetPassword, params, callback);
    }

    /*获取验证码
    * 1为注册,2为忘记密码
    * */
    public  static void getIdentity(Context context,String phone_no
                                   ,String type,MyRequestClient.Callback<NetBaseBean> callback) {
        HashMap<String ,String> params = new HashMap();
        params.put("phone_no", phone_no); //电话号码
        params.put("type",type); // 验证码类型，1为注册，2为忘记密码。
        MyRequestClient.post(context, GlobalConfig.sendMessage, params, callback);
    }

    /*咨询我的列表
    * */
    public  static void getConsultingMelist(Context context,String phone_no
            ,String type,MyRequestClient.Callback<NetBaseBean> callback) {
        HashMap<String ,String> params = new HashMap();
        params.put("start", phone_no); //电话号码
        params.put("end",type); // 验证码类型，1为注册，2为忘记密码。
        MyRequestClient.post(context, GlobalConfig.consultingMe, params, callback);
    }
    /*
    我的咨询列表
       * */
    public  static void getMyConsultinglist(Context context,String phone_no
            ,String type,MyRequestClient.Callback<NetBaseBean> callback) {
        HashMap<String ,String> params = new HashMap();
        params.put("start", phone_no); //电话号码
        params.put("end",type); // 验证码类型，1为注册，2为忘记密码。
        MyRequestClient.post(context, GlobalConfig.myConsulting, params, callback);
    }

    /**
     * 开关
     * */

    public static void pushMessage(Context context,String  switch_flag,MyRequestClient.Callback<NetBaseBean>callback){
        HashMap<String ,String> params = new HashMap();
        params.put("switch_flag", switch_flag); //开关消息推送
        MyRequestClient.post(context, GlobalConfig.pushMessage, params, callback);

    }
    /**
     * 开关
     * */

    public static void checkMessageState(Context context,MyRequestClient.Callback<NetBaseBean>callback){
        HashMap<String ,String> params = new HashMap();

        MyRequestClient.post(context, GlobalConfig.checkMessageState, params, callback);

    }

    /**
     * 查询个人信息
     * */
    public static void queryPersonalInfo(Context context,MyRequestClient.Callback<NetBaseBean>callback){
        HashMap<String ,String> params = new HashMap();

        MyRequestClient.post(context, GlobalConfig.queryPersonalInfo, params, callback);

    }
    /**
     * 查询我的收藏
     * */
    public static void queryMyCollection(Context context,MyRequestClient.Callback<NetBaseBean>callback){
        HashMap<String ,String> params = new HashMap();

        MyRequestClient.post(context, GlobalConfig.queryPersonalInfo, params, callback);

    }
    /**
     * 查询咨询详情 CONSULT_DETAIL
     * */
    public static void queryConsultingDetail(Context context,String consultId,MyRequestClient.Callback<NetBaseBean>callback){
        HashMap<String ,String> params = new HashMap();

        params.put("consultId",consultId);
        MyRequestClient.post(context, GlobalConfig.CONSULT_DETAIL, params, callback);

    }
}
