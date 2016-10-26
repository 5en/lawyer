package com.ebaonet.lawyer.ui.view.frgament;

import android.widget.Button;

import com.ebaonet.lawyer.R;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/10/14.
 */

public class ModifyPhoneSecurityMessageDelegate extends AppDelegate {
    private Button validate;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_security_message;
    }
    @Override
    public void created(){
        validate = findViewById(R.id.validate);
    }
    @Override
    public void bindEvent(){
        EventHelper.click(mPresenter,validate);
    }
}
