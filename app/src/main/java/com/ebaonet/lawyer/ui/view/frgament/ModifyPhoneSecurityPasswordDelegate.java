package com.ebaonet.lawyer.ui.view.frgament;

import android.widget.Button;

import com.ebaonet.lawyer.R;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/10/13.
 */

public class ModifyPhoneSecurityPasswordDelegate extends AppDelegate {
    Button validate;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_security_password;
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
