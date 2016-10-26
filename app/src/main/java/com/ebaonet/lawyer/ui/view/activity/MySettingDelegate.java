package com.ebaonet.lawyer.ui.view.activity;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.weight.ToggleButton;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/10/13.
 */

public class MySettingDelegate extends AppDelegate {
    private RelativeLayout modify_password;
    private RelativeLayout modify_phone;
    private ToggleButton toggle;
    private RelativeLayout clear_cache;
    private Button login_out;
    private TextView cache_size;
    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void created(){
        modify_password = findViewById(R.id.modify_password);
        modify_phone = findViewById(R.id.modify_phone);
        toggle =findViewById(R.id.toggle);
        clear_cache = findViewById(R.id.clear_cache);
        login_out = findViewById(R.id.login_out);
        cache_size = findViewById(R.id.cache_size);
    }
    @Override
    public void bindEvent(){
        EventHelper.click(mPresenter,modify_password,modify_phone,toggle,clear_cache,login_out);

    }

    public RelativeLayout getModify_password() {
        return modify_password;
    }

    public RelativeLayout getModify_phone() {
        return modify_phone;
    }

    public ToggleButton getToggle() {
        return toggle;
    }

    public RelativeLayout getClear_cache() {
        return clear_cache;
    }

    public TextView getCache_size() {
        return cache_size;
    }

    public Button getLogin_out() {
        return login_out;
    }
}
