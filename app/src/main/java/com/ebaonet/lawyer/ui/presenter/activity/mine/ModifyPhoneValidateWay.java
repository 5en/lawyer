package com.ebaonet.lawyer.ui.presenter.activity.mine;

import android.content.Intent;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.presenter.fragment.BaseFragment;

import com.ebaonet.lawyer.ui.view.frgament.ModifyPhoneValidateWayDelegate;

/**
 * Created by tx on 2016/10/13.
 */

public class ModifyPhoneValidateWay extends BaseFragment<ModifyPhoneValidateWayDelegate> implements View.OnClickListener{


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.password_validate:{
                //todo 切换成验证码fragment 向activity发送广播通知
                Intent intent = new Intent(ModifyBindPhoneActivity.LOGIN_PASSWORD);
                getActivity().sendBroadcast(intent);
                break;
            }
            case R.id.message_validate:{
                //todo 切换成密码安全验证fragment 向所在的activity发送广播通知
                Intent intent = new Intent(ModifyBindPhoneActivity.MESSAGE_VALIDATE);
                getActivity().sendBroadcast(intent);
                break;
            }





        }
    }
}
