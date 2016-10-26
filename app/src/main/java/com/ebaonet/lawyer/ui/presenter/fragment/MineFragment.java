package com.ebaonet.lawyer.ui.presenter.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.ui.popupWindow.SharePopWindow;
import com.ebaonet.lawyer.ui.presenter.activity.mine.ConsultingMeActivity;
import com.ebaonet.lawyer.ui.presenter.activity.mine.MyCollectionActivity;
import com.ebaonet.lawyer.ui.presenter.activity.mine.MyConsultationActivity;
import com.ebaonet.lawyer.ui.presenter.activity.mine.MyKnowledgeActivity;
import com.ebaonet.lawyer.ui.presenter.activity.mine.MySettingActivity;
import com.ebaonet.lawyer.ui.presenter.activity.mine.PersonalInfoActivity;
import com.ebaonet.lawyer.ui.view.frgament.MineFragmentDelegate;
import com.ebaonet.lawyer.util.sharePreferences.PreferencesManger;
import com.ebaonet.lawyer.util.sharePreferences.PreferencesUtils;

/**
 * Created by tx on 2016/9/20.
 */
public class MineFragment extends BaseFragment<MineFragmentDelegate> implements View.OnClickListener{

    public static final String  IDENTITY_CUSTOM_USER = "custom_user";
    public static final String  IDENTITY_LAWYER = "lawyer";
    public static final String  IDENTITY_UN_LOGIN = "un_login";
    public static final String  IDENTITY = "identity";
    private SharePopWindow sharePopWindow;


    // 实时更替“我的” fragment身份。 广播

    BroadcastReceiver identityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String identity = intent.getStringExtra(IDENTITY);
            switch (identity){
                case IDENTITY_CUSTOM_USER:
                    showCustomUser();
                    PreferencesManger.setIdentity(getContext(),IDENTITY_CUSTOM_USER);

                    break;
                case IDENTITY_LAWYER:
                    showLawyer();
                    PreferencesManger.setIdentity(getContext(),IDENTITY_LAWYER);
                    break;
                case IDENTITY_UN_LOGIN:
                    showNotLogin();
                    PreferencesManger.setIdentity(getContext(),IDENTITY_UN_LOGIN);
                    break;
            }
        }
    };

    @Override
    public void created(Bundle bundle){
        String identity = PreferencesManger.getIdentity(getContext());
        switch (identity){
            case IDENTITY_CUSTOM_USER:
                showCustomUser();
                break;
            case IDENTITY_LAWYER:
                showLawyer();
                break;
            case IDENTITY_UN_LOGIN:
                showNotLogin();
                break;


        }
        //todo 先暂时显示最多项。显示2条消息

        showLawyer();
        mView.getMy_question().showMessageAndSetCount("2");

        getActivity().registerReceiver(identityReceiver,new IntentFilter(IDENTITY));

    }
/**
 * 普通用户
 * */
    private void showCustomUser(){

        mView.getMy_been_questioned().setVisibility(View.GONE);
        mView.getMy_knowledge_lin().setVisibility(View.GONE);
    }
/**
 * 律师
 * */
    private void showLawyer(){

        mView.getMy_been_questioned().setVisibility(View.VISIBLE);
        mView.getMy_knowledge_lin().setVisibility(View.VISIBLE);
    }

    /**
     * 未登录
     * */
    private void showNotLogin(){

        mView.getMy_been_questioned().setVisibility(View.GONE);
        mView.getMy_knowledge_lin().setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_question:          //我的咨询

                MyConsultationActivity.actionActivity(getContext());

                break;
            case R.id.my_been_questioned:     //咨询我的
                ConsultingMeActivity.actionActivity(getContext());

                break;
            case R.id.collection:          //我的收藏

                MyCollectionActivity.actionActivity(getContext());
                break;
            case R.id.my_knowledge:             //我的知识

                MyKnowledgeActivity.actionActivity(getContext());
                break;
            case R.id.my_setting:                //我的设置

                MySettingActivity.actionActivity(getContext());
                break;
            case R.id.suggestion:                  //意见反馈
                break;
            case R.id.about:                        //关于律言
                break;
            case R.id.share:                  //分享

                if(sharePopWindow ==null){
                    sharePopWindow = new SharePopWindow(getContext(),itemClickListener);
                }
                if (!sharePopWindow.isShowing()){
                    sharePopWindow.showAtLocation(mView.findViewById(R.id.parent), Gravity.BOTTOM,0,0);
                }
                break;
            case R.id.my_knowledge_lin:         //普通用户没有“我的知识”
                break;
            case R.id.user_relative:          //用户信息
                 PersonalInfoActivity.actionActivity(getContext());
                break;

        }
    }

    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
