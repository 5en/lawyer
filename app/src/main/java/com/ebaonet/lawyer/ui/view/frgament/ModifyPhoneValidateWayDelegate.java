package com.ebaonet.lawyer.ui.view.frgament;

import android.widget.Button;

import com.ebaonet.lawyer.R;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/10/13.
 */

public class ModifyPhoneValidateWayDelegate extends AppDelegate {
    private Button password_validate;
    private Button message_validate;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_phone_validate;
    }
    @Override
    public void created(){
        message_validate = findViewById(R.id.message_validate);
        password_validate = findViewById(R.id.password_validate);

    }

    @Override
    public void bindEvent(){
        EventHelper.click(mPresenter,password_validate,message_validate);
    }

    public Button getPassword_validate() {
        return password_validate;
    }

    public Button getMessage_validate() {
        return message_validate;
    }
}
