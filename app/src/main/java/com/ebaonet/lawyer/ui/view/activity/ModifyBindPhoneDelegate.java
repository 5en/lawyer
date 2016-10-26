package com.ebaonet.lawyer.ui.view.activity;

import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.weight.TitleView;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/10/13.
 */

public class ModifyBindPhoneDelegate extends AppDelegate {
    private RelativeLayout bind_phone_title;
    private FrameLayout container;
    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone;
    }
    @Override
    public void created(){
        bind_phone_title = findViewById(R.id.bind_phone_title);
        container = findViewById(R.id.container);
    }

    @Override
    public void bindEvent(){
        EventHelper.click(mPresenter,bind_phone_title);
    }

    public RelativeLayout getTitle() {
        return bind_phone_title;
    }

    public FrameLayout getContainer() {
        return container;
    }
}
