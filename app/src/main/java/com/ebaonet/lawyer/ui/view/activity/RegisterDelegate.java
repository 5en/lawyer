package com.ebaonet.lawyer.ui.view.activity;

import android.app.Activity;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.weight.AutoCompleteTextViewWithDelete;
import com.ebaonet.lawyer.ui.weight.TitleView;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/9/18.
 */
public class RegisterDelegate extends AppDelegate {
    private Button send_identity_code;
    private Button register_button;
    private AutoCompleteTextViewWithDelete name;
    private AutoCompleteTextViewWithDelete phone_number;
    private AutoCompleteTextViewWithDelete identity_message;
    private AutoCompleteTextViewWithDelete pass_word;
    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void created(){
        send_identity_code = findViewById(R.id.send_identity_code);
        TitleView mTitleView = findViewById(R.id.mTitleView);
        mTitleView.bindActivity((Activity) mPresenter);
        register_button = findViewById(R.id.register_button);
        name = findViewById(R.id.name);
        phone_number = findViewById(R.id.phone_number);
        identity_message = findViewById(R.id.identity_message);
        pass_word = findViewById(R.id.pass_word);

    }

    @Override
    public void bindEvent(){
        EventHelper.click(mPresenter,send_identity_code,register_button);
    }
    public Button getSend_identity_code() {
        return send_identity_code;
    }

    public Button getRegister_button() {
        return register_button;
    }


    public AutoCompleteTextViewWithDelete getName() {
        return name;
    }

    public AutoCompleteTextViewWithDelete getPhone_number() {
        return phone_number;
    }

    public AutoCompleteTextViewWithDelete getIdentity_message() {
        return identity_message;
    }

    public AutoCompleteTextViewWithDelete getPass_word() {
        return pass_word;
    }
}
