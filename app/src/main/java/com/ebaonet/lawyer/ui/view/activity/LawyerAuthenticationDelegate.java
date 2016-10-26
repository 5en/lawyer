package com.ebaonet.lawyer.ui.view.activity;

import android.app.Activity;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.weight.LeftTextAndRightTextView;
import com.ebaonet.lawyer.ui.weight.TitleView;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/10/24.
 */

public class LawyerAuthenticationDelegate extends AppDelegate {
    private LeftTextAndRightTextView easy_introduce;
    private LeftTextAndRightTextView work_years;
    private LeftTextAndRightTextView profession;
    private LeftTextAndRightTextView unit;
    private LeftTextAndRightTextView belong_model;
    private LeftTextAndRightTextView lawyer_practice_certificate;
    private LeftTextAndRightTextView identity;
    private LeftTextAndRightTextView mail;
    private LeftTextAndRightTextView name;
    private TitleView title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication;
    }
    @Override
    public void created(){
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        identity = findViewById(R.id.identity);
        lawyer_practice_certificate = findViewById(R.id.lawyer_practice_certificate);
        belong_model = findViewById(R.id.belong_model);
        unit = findViewById(R.id.unit);
        profession = findViewById(R.id.profession);
        work_years = findViewById(R.id.work_years);
        easy_introduce = findViewById(R.id.easy_introduce);
        title = findViewById(R.id.title);
        title.bindActivity((Activity) mPresenter);
    }

    @Override
    public void bindEvent(){
        EventHelper.click(mPresenter,easy_introduce);
    }
}
