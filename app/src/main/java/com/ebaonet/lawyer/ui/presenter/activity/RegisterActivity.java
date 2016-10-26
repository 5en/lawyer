package com.ebaonet.lawyer.ui.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.core.entity.NetBaseBean;
import com.ebaonet.lawyer.core.http.MyRequestClient;
import com.ebaonet.lawyer.core.http.biz.PersonBiz;
import com.ebaonet.lawyer.ui.view.activity.RegisterDelegate;
import com.ebaonet.lawyer.util.sharePreferences.PreferencesManger;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by tx on 2016/9/18.
 */
public class RegisterActivity extends BaseActivity<RegisterDelegate> implements View.OnClickListener{
    TimeCount mTimeCount;
    public static void actionActivity(Context context){
        Intent intent = new Intent(context,RegisterActivity.class);
        context.startActivity(intent);

    }

    @Override
    public void created(Bundle bundle){

        mTimeCount = new TimeCount(60000,1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_identity_code:
                 mTimeCount.start();
                getIdentityCode(mView.getPhone_number().getText().toString(),"1");
                break;
            case R.id.register_button:
                showRequestDialog("",false,false);
                register();
                break;
        }
    }

    private void getIdentityCode(String phone_no,String type){
        PersonBiz.getIdentity(RegisterActivity.this,phone_no,type, new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {
                if(data.isSuccess()){

                }else {
                    alert(data.getMessage());
                }
            }

            @Override
            public void onError(NetBaseBean data) {


            }
        });

    }
    private void register(){
        PersonBiz.register(RegisterActivity.this, mView.getName().getText().toString(), mView.getPhone_number().getText().toString(),
                mView.getIdentity_message().getText().toString(), mView.getPass_word().getText().toString(), "", new MyRequestClient.Callback<NetBaseBean>() {
                    @Override
                    public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {
                        closeRequestDialog();
                        if(data.isSuccess()){
                            //todo 自动登录 ，跳转到首页
                            login(mView.getPhone_number().getText().toString(),mView.getPass_word().getText().toString());


                        }else {
                            alert(data.getMessage());
                        }
                    }

                    @Override
                    public void onError(NetBaseBean data) {

                    }
                });
    }

    private void login(final String phone, final String password){

        PersonBiz.login(RegisterActivity.this, phone, password, new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {
                closeRequestDialog();
                if (data.isSuccess()){
                    PreferencesManger.setAccount(RegisterActivity.this,phone);
                    PreferencesManger.setPassword(RegisterActivity.this,password);
                    HomeActivity.actionActivity(RegisterActivity.this,2 );
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

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
            mView.getSend_identity_code().setEnabled(true);
        }

        @Override
        public void onFinish() {//计时完毕时触发
            mView.getSend_identity_code().setText("发送验证码");
            mView.getSend_identity_code().setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            mView.getSend_identity_code().setText(millisUntilFinished / 1000 + "秒后再次获取");
            mView.getSend_identity_code().setEnabled(false);
        }

   }
}
