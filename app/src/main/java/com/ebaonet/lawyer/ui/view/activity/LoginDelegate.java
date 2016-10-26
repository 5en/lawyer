package com.ebaonet.lawyer.ui.view.activity;

import android.widget.Button;
import android.widget.TextView;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.weight.AutoCompleteTextViewWithDelete;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/9/18.
 */
public class LoginDelegate extends AppDelegate {
    private Button login;
    private Button register;
    private TextView no_server_password;
    private AutoCompleteTextViewWithDelete pass_word;
    private AutoCompleteTextViewWithDelete phone_number;
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void created(){
        login =findViewById(R.id.login);
        register = findViewById(R.id.register);
        phone_number = findViewById(R.id.phone_number);
        pass_word = findViewById(R.id.pass_word);
        no_server_password = findViewById(R.id.no_server_password);
    }
    @Override
    public void bindEvent(){
        EventHelper.click(mPresenter,register,no_server_password,login);
    }

    public AutoCompleteTextViewWithDelete getPass_word() {
        return pass_word;
    }

    public AutoCompleteTextViewWithDelete getPhone_number() {
        return phone_number;
    }

}
