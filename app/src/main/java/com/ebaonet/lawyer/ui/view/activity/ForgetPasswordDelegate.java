package com.ebaonet.lawyer.ui.view.activity;

import android.widget.Button;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.weight.AutoCompleteTextViewWithDelete;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/9/19.
 */
public class ForgetPasswordDelegate extends AppDelegate {
    private Button send_identity_code;
    private AutoCompleteTextViewWithDelete phone_number;
    private AutoCompleteTextViewWithDelete identity_message;
    private AutoCompleteTextViewWithDelete new_password;
    private AutoCompleteTextViewWithDelete password_again;

    private Button sure;
    @Override
    public void created(){
        send_identity_code = findViewById(R.id.send_identity_code);
        sure = findViewById(R.id.sure);
        phone_number = findViewById(R.id.phone_number);
        sure = findViewById(R.id.sure);
        password_again = findViewById(R.id.password_again);
        new_password = findViewById(R.id.new_password);
        identity_message = findViewById(R.id.identity_message);

    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    public void bindEvent(){
        EventHelper.click(mPresenter,sure,send_identity_code);
    }

    public Button getSend_identity_code() {
        return send_identity_code;
    }

    public AutoCompleteTextViewWithDelete getPhone_number() {
        return phone_number;
    }

    public AutoCompleteTextViewWithDelete getIdentity_message() {
        return identity_message;
    }

    public Button getSure() {
        return sure;
    }

    public AutoCompleteTextViewWithDelete getNew_password() {
        return new_password;
    }

    public AutoCompleteTextViewWithDelete getPassword_again() {
        return password_again;
    }
}
