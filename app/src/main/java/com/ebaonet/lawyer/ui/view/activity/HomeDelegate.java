package com.ebaonet.lawyer.ui.view.activity;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.weight.CustomViewPager;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * 作者：yzb on 2016/9/8 15:14
 * 邮箱：280766447@qq.com
 */
public class HomeDelegate extends AppDelegate {
    private RadioGroup mMainBottomBar;
    private CustomViewPager mMainLayoutContent;
    private RadioButton mMainPage;
    private RadioButton mMainRadioKnowledge;
    private RadioButton mMainRadioMe;
    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }
    @Override
    public void created(){
        mMainBottomBar = findViewById(R.id.mMainBottomBar);
        mMainLayoutContent = findViewById(R.id.mMainLayoutContent);
        mMainPage = findViewById(R.id.mMainPage);
        mMainRadioKnowledge = findViewById(R.id.mMainRadioKnowledge);
        mMainRadioMe = findViewById(R.id.mMainRadioMe);
    }
    @Override
    public void bindEvent() {
        EventHelper.checkedChanged((RadioGroup.OnCheckedChangeListener) mPresenter, mMainBottomBar);
    }


    public RadioGroup getMainBottomBar() {
        return mMainBottomBar;
    }

    public CustomViewPager getViewPager() {
        return mMainLayoutContent;
    }
}
