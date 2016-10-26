package com.ebaonet.lawyer.ui.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.core.entity.NetBaseBean;
import com.ebaonet.lawyer.core.http.MyRequestClient;
import com.ebaonet.lawyer.core.http.biz.PersonBiz;
import com.ebaonet.lawyer.ui.presenter.activity.BaseActivity;
import com.ebaonet.lawyer.ui.view.activity.LoginDelegate;
import com.ebaonet.lawyer.util.LoginHelp;
import com.ebaonet.lawyer.util.sharePreferences.PreferencesManger;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by tx on 2016/9/9.
 */
public class LoginActivity extends BaseActivity<LoginDelegate> implements View.OnClickListener {
    public static final String LOGIN_CLASS = "login_class";
    public static void actionActivity(Context context){
        if (LoginHelp.isLogin(context)){
            Intent intent = new Intent(context,LoginActivity.class);
            context.startActivity(intent);
        }else {
            LoginHelp.toLogin(context,"");
        }


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:             //注册页面

                RegisterActivity.actionActivity(LoginActivity.this);
                break;
            case R.id.no_server_password:  //忘记密码
                //todo
                ForgetPasswordActivity.actionActivity(LoginActivity.this);
                break;
            case R.id.login:
              //  showRequestDialog("",true,true);
                //todo  登陆
                login();
//                PreferencesManger.setAccount(LoginActivity.this,mView.getPhone_number().getText().toString());
//                PreferencesManger.setPassword(LoginActivity.this,mView.getPass_word().getText().toString());
                finish();
                break;
        }
    }

    private void login(){

        PersonBiz.login(LoginActivity.this, mView.getPhone_number().getText().toString(), mView.getPass_word().getText().toString(), new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {
                closeRequestDialog();
                if (data.isSuccess()){
                    PreferencesManger.setAccount(LoginActivity.this,mView.getPhone_number().getText().toString());
                    PreferencesManger.setPassword(LoginActivity.this,mView.getPass_word().getText().toString());
                    finish();
                }else {
                    alert(data.getMessage());
                }
            }

            @Override
            public void onError(NetBaseBean data) {

                closeRequestDialog();

            }
        });
    }
}
