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
import com.ebaonet.lawyer.ui.view.activity.ForgetPasswordDelegate;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by tx on 2016/9/19.
 */
public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordDelegate> implements View.OnClickListener {
    TimeCount mTimeCount;
public static void actionActivity(Context context){
        Intent intent = new Intent(context,ForgetPasswordActivity.class);

        context.startActivity(intent);

    }

    @Override
    public void created(Bundle bundle){
        mTimeCount = new TimeCount(60000,1000);

    }

    private void forgetPassword(String phone_no,String verify_code,String password){

        PersonBiz.forgetPassword(ForgetPasswordActivity.this,phone_no,verify_code,password, new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {
                if(data.isSuccess()){
                    alert("修改成功");

                }else {
                    alert(data.getMessage());
                }
            }

            @Override
            public void onError(NetBaseBean data) {


            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure:
                forgetPassword(mView.getPhone_number().getText().toString(),mView.getIdentity_message().getText().toString(),mView.getNew_password().getText().toString());


                break;
            case R.id.send_identity_code:
                //1为注册,2为忘记密码
                mTimeCount.start();
                getIdentityCode(mView.getPhone_number().getText().toString(),"2");
                break;
        }
    }

    private void getIdentityCode(String phone_no,String type){
        PersonBiz.getIdentity(ForgetPasswordActivity.this,phone_no,type, new MyRequestClient.Callback<NetBaseBean>() {
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
