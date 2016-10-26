package com.ebaonet.lawyer.ui.view.activity;

import android.widget.ImageView;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.presenter.activity.CallActivity;
import com.ebaonet.lawyer.ui.weight.TitleView;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * 作者：yzb on 2016/10/8 16:07
 * 邮箱：280766447@qq.com
 */
public class CallAppDelegate extends AppDelegate {
    private ImageView mCall;
    private TitleView mTitleView;
    @Override
    public int getLayoutId() {
        return R.layout.activity_call;
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
        EventHelper.click(mPresenter,mCall,findViewById(R.id.toWrite));
    }

    @Override
    public void created() {
        super.created();
        mCall=findViewById(R.id.mCall);
        mTitleView=findViewById(R.id.mTitleView);
        mTitleView.bindActivity((CallActivity)mPresenter);
    }
}
