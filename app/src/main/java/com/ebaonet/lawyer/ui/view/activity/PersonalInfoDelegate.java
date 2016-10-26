package com.ebaonet.lawyer.ui.view.activity;

import android.widget.RelativeLayout;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.weight.CircleImageView;
import com.ebaonet.lawyer.ui.weight.LeftTextAndRightTextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yanglaoClient.mvp.helper.EventHelper;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * Created by tx on 2016/9/23.
 */
public class PersonalInfoDelegate extends AppDelegate {
    private RelativeLayout avatar_relative;
    private SimpleDraweeView avatar;
    private LeftTextAndRightTextView name;
    private LeftTextAndRightTextView area;
    private LeftTextAndRightTextView industry;
    private LeftTextAndRightTextView email_address;
    private LeftTextAndRightTextView trade;
    private LeftTextAndRightTextView profession;
    private LeftTextAndRightTextView work_years;
    private LeftTextAndRightTextView lawyer_info;
    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_info;
    }
    @Override
    public void created(){
        name = findViewById(R.id.name);
        area = findViewById(R.id.area);
        industry = findViewById(R.id.industry);
        email_address =findViewById(R.id.email_address);
        trade = findViewById(R.id.trade);
        profession = findViewById(R.id.profession);
        work_years = findViewById(R.id.work_years);
        lawyer_info = findViewById(R.id.lawyer_info);

        avatar = findViewById(R.id.avatar);
        avatar_relative = findViewById(R.id.avatar_relative);
        avatar.setBackgroundResource(R.mipmap.touxiang);

    }
    @Override
    public void bindEvent(){
        EventHelper.click(mPresenter,avatar_relative,lawyer_info);
    }


    public LeftTextAndRightTextView getTrade() {
        return trade;
    }

    public LeftTextAndRightTextView getProfession() {
        return profession;
    }

    public LeftTextAndRightTextView getWork_years() {
        return work_years;
    }

    public LeftTextAndRightTextView getLawyer_info() {
        return lawyer_info;
    }

    public LeftTextAndRightTextView getName() {
        return name;
    }

    public LeftTextAndRightTextView getArea() {
        return area;
    }

    public LeftTextAndRightTextView getIndustry() {
        return industry;
    }

    public LeftTextAndRightTextView getEmail_address() {
        return email_address;
    }

    public SimpleDraweeView getAvatar() {
        return avatar;
    }

    public RelativeLayout getAvatar_relative() {
        return avatar_relative;
    }
}
