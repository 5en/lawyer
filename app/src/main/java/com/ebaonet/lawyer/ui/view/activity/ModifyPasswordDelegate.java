package com.ebaonet.lawyer.ui.view.activity;

import android.app.Activity;
import android.widget.Button;


import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.weight.EditWithLeftImageAndTextView;
import com.ebaonet.lawyer.ui.weight.TitleView;
import com.ebaonet.lawyer.ui.weight.animate.AmplifyRecoverAnimatorHelper;
import com.ebaonet.lawyer.ui.weight.animate.IAnimatorHelper;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/6/22.
 */
public class ModifyPasswordDelegate extends AppDelegate {
    Button sure_modify;
    EditWithLeftImageAndTextView sure_new_password;
    EditWithLeftImageAndTextView new_password;
    EditWithLeftImageAndTextView old_password;

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_password;
    }

    @Override
    public void created(){
        TitleView titleView = findViewById(R.id.title);
        titleView.bindActivity((Activity) mPresenter);
        sure_modify = findViewById(R.id.sure_modify);
        sure_new_password = findViewById(R.id.sure_new_password);
        new_password = findViewById(R.id.new_password);
        old_password = findViewById(R.id.old_password);

        old_password.setHideWord();
        new_password.setHideWord();
        sure_new_password.setHideWord();


        IAnimatorHelper iAnimatorHelper = new AmplifyRecoverAnimatorHelper();
        iAnimatorHelper.setAnimate(sure_modify);
        new_password.setOnlyDigits();
        new_password.setLimitLenth(6);
        old_password.setOnlyDigits();
        old_password.setLimitLenth(6);
        sure_new_password.setLimitLenth(6);
        sure_new_password.setOnlyDigits();

    }
    @Override
    public void bindEvent(){
        EventHelper.click(mPresenter,sure_modify);

    }

    public Button getSure_modify() {
        return sure_modify;
    }

    public EditWithLeftImageAndTextView getSure_new_password() {
        return sure_new_password;
    }

    public EditWithLeftImageAndTextView getNew_password() {
        return new_password;
    }

    public EditWithLeftImageAndTextView getOld_password() {
        return old_password;
    }
}
