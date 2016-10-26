package com.ebaonet.lawyer.ui.presenter.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.core.entity.NetBaseBean;
import com.ebaonet.lawyer.core.http.MyRequestClient;
import com.ebaonet.lawyer.core.http.biz.LoginBiz;
import com.ebaonet.lawyer.core.http.biz.PersonBiz;
import com.ebaonet.lawyer.ui.dialog.TwoSelectDialog;
import com.ebaonet.lawyer.ui.presenter.activity.BaseActivity;
import com.ebaonet.lawyer.ui.presenter.activity.HomeActivity;
import com.ebaonet.lawyer.ui.presenter.fragment.MineFragment;
import com.ebaonet.lawyer.ui.view.activity.MySettingDelegate;
import com.ebaonet.lawyer.ui.weight.ToggleButton;
import com.ebaonet.lawyer.util.ImageUtils;
import com.ebaonet.lawyer.util.sharePreferences.PreferencesManger;


import org.json.JSONException;

import java.io.IOException;

/**
 * Created by tx on 2016/10/13.
 */

public class MySettingActivity extends BaseActivity<MySettingDelegate>  implements View.OnClickListener{
    public static void actionActivity(Context context){
        Intent intent = new Intent(context,MySettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void created(Bundle bum){
        checkState();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.modify_password:
                ModifyPasswordActivity.actionActivity(MySettingActivity.this,1);
                break;
            case R.id.modify_phone:
                ModifyBindPhoneActivity.actionActivity(MySettingActivity.this);
                break;
            case R.id.toggle:
                setToggle();
                break;
            case R.id.clear_cache:
                Snackbar.make(mView.getToggle(),"确定清除数据吗",Snackbar.LENGTH_LONG).setAction("清除", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 清除
                        ImageUtils.clearAllCache(MySettingActivity.this);
                        mView.getCache_size().setText("0M");
                    }
                }).show();
                break;
            case R.id.login_out:
                TwoSelectDialog twoSelectDialog= showSelectDialog("退出登录","你确定退出登录吗",true,true);
                twoSelectDialog.setOnClickSelect(new TwoSelectDialog.OnDialogClickListener() {
                    @Override
                    public void onOneClick(View v) {
                    }
                    @Override
                    public void onTwoClick(View v) {
                        logout();




                    }
                });




                break;
        }
    }
    private void logout() {
        //todo 去除第三方临时信息。

        showRequestDialog("",false,false);
        PersonBiz.loginOut(this, "","",new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {
                closeRequestDialog();
                if(data.isSuccess()){
//                    PreferencesManger.setAccount(PersonalCenterActivity.this, "");
                    PreferencesManger.setPassword(MySettingActivity.this,"");
                    PreferencesManger.setPassword(MySettingActivity.this,"");
                    Intent intent = new Intent(MineFragment.IDENTITY);
                    intent.putExtra(MineFragment.IDENTITY,MineFragment.IDENTITY_UN_LOGIN);
                    sendBroadcast(intent);
                    HomeActivity.actionActivity(MySettingActivity.this,2);

                }else {
                    alert(data.getMessage());
                }
            }

            @Override
            public void onError(NetBaseBean data) {
                alert(data.getMessage());
            }
        });

    }

    private void setToggle(){

        mView.getToggle().setOnStateChangedListener(new ToggleButton.OnStateChangedListener() {
            @Override
            public void toggleToOn(View view) {
                SwitchMessagePushState(true);
            }

            @Override
            public void toggleToOff(View view) {
                SwitchMessagePushState(false);
            }
        });
    }

    private void checkState(){
        PersonBiz.checkMessageState(this, new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {
                closeRequestDialog();
                if(data.isSuccess()){
                    String push_switch  = data.getResultData().getString("push_switch");
                    if ("true".equals(push_switch)){
                        mView.getToggle().setOpened(true);
                    }else {
                        mView.getToggle().setOpened(false);
                    }
                }else {
                    alert(data.getMessage());
                }
            }

            @Override
            public void onError(NetBaseBean data) {

            }
        });
    }
    private void SwitchMessagePushState(final boolean isPush){
        showRequestDialog("",true,true);
        String switchFlag = isPush ? "1" : "0";
        PersonBiz.pushMessage(MySettingActivity.this, switchFlag, new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {
                closeRequestDialog();
                if (!data.isSuccess()){
                    alert(data.getMessage());
                    return;
                }
                mView.getToggle().setOpened(isPush);
            }

            @Override
            public void onError(NetBaseBean data) {

                alert(data.getMessage());
            }
        });

    }
}
