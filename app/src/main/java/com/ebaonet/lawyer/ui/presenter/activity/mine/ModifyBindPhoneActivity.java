package com.ebaonet.lawyer.ui.presenter.activity.mine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.presenter.activity.BaseActivity;
import com.ebaonet.lawyer.ui.presenter.fragment.BinPhoneFragment;
import com.ebaonet.lawyer.ui.view.activity.ModifyBindPhoneDelegate;
import com.ebaonet.lawyer.ui.view.frgament.ModifyPhoneSecurityPasswordDelegate;

import static com.ebaonet.lawyer.ui.presenter.activity.mine.ModifyBindPhoneActivity.BindEventState.STATE_1;
import static com.ebaonet.lawyer.ui.presenter.activity.mine.ModifyBindPhoneActivity.BindEventState.STATE_2;
import static com.ebaonet.lawyer.ui.presenter.activity.mine.ModifyBindPhoneActivity.BindEventState.STATE_3;

/**
 * Created by tx on 2016/10/13.
 */

public class ModifyBindPhoneActivity extends BaseActivity<ModifyBindPhoneDelegate> implements View.OnClickListener {

    public static final String LOGIN_PASSWORD = "login_password";

    public static final String MESSAGE_VALIDATE = "message_validate";

   // public static final String SECURITY_VALIDATE = "security_validate";

    public static final String BIND_PHONE = "bind_phone";
    public static void actionActivity(Context context){
        Intent intent = new Intent(context,ModifyBindPhoneActivity.class);
        context.startActivity(intent);
    }
// 处理三步 。1 。选择验证方式 2. 安全验证 3. 换绑手机
public enum  BindEventState {
        STATE_1,STATE_2,STATE_3
    }

    private BindEventState fragmentState = STATE_1;
    //第一步fragment
    private ModifyPhoneValidateWay modifyPhoneValidateWay;

    private ModifyPhoneSecurityPasswordFragment modifyPhoneSecurityPasswordFragment;
    private ModifyPhoneSecurityMessageFragment modifyPhoneSecurityMessageFragment;
    private BinPhoneFragment binPhoneFragment;

    //第二步的的 密码fragment  验证码fragment

    // 安全验证 使用登录密码
    BroadcastReceiver LoginPasswordbroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            fragmentState = STATE_2;
            modifyPhoneSecurityPasswordFragment = new ModifyPhoneSecurityPasswordFragment();
            FragmentManager fragmentManager;
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container,modifyPhoneSecurityPasswordFragment);
            fragmentTransaction.commit();
        }
    };


    // 安全验证 使用短信验证
    BroadcastReceiver MessagebroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            fragmentState =STATE_2;
            modifyPhoneSecurityMessageFragment = new ModifyPhoneSecurityMessageFragment();
            FragmentManager fragmentManager;
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container,modifyPhoneSecurityMessageFragment);
            fragmentTransaction.commit();

        }
    };

    BroadcastReceiver bindPhoneReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            fragmentState = STATE_3;
            FragmentManager fragmentManager;
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            binPhoneFragment = new BinPhoneFragment();
            fragmentTransaction.add(R.id.container,binPhoneFragment);
            fragmentTransaction.commit();        }
    };


    @Override
    public void created(Bundle bundle){
        fragmentState =STATE_1;
        modifyPhoneValidateWay = new ModifyPhoneValidateWay();
        FragmentManager fragmentManager;
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.container,modifyPhoneValidateWay);
        fragmentTransaction.commit();


        registerReceiver(LoginPasswordbroadcastReceiver,new IntentFilter(LOGIN_PASSWORD));
        registerReceiver(MessagebroadcastReceiver,new IntentFilter(MESSAGE_VALIDATE));
        registerReceiver(bindPhoneReceiver,new IntentFilter(BIND_PHONE));

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bind_phone_title:{
                switch (fragmentState){
                    case STATE_1:
                        // finish
                        finish();
                    break;
                    case STATE_2:{

                        FragmentManager fragmentManager;
                        fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        if (modifyPhoneSecurityPasswordFragment!=null){

                            fragmentTransaction.remove(modifyPhoneSecurityPasswordFragment);
                            fragmentTransaction.commit();
                            modifyPhoneSecurityPasswordFragment = null;
                        }
                        if (modifyPhoneSecurityMessageFragment !=null){

                            fragmentTransaction.remove(modifyPhoneSecurityMessageFragment);
                            fragmentTransaction.commit();
                            modifyPhoneSecurityMessageFragment = null;

                        }
                        fragmentState =STATE_1;
                        //remove 2
                        break;
                    }
                    case STATE_3: {
                        FragmentManager fragmentManager;
                        fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentState = STATE_2;
                        if (binPhoneFragment != null) {
                            fragmentTransaction.remove(binPhoneFragment);
                            fragmentTransaction.commit();
                        }
                        // remove 3
                        break;
                    }
                }
                break;
            }

        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(MessagebroadcastReceiver);
        unregisterReceiver(LoginPasswordbroadcastReceiver);
        unregisterReceiver(bindPhoneReceiver);
    }

}
