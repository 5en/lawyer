package com.ebaonet.lawyer.ui.presenter.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.configs.GlobalConfig;
import com.ebaonet.lawyer.core.entity.NetBaseBean;
import com.ebaonet.lawyer.core.entity.PersonalInfoEntity;
import com.ebaonet.lawyer.core.http.MyRequestClient;
import com.ebaonet.lawyer.core.http.biz.PersonBiz;
import com.ebaonet.lawyer.ui.dialog.SelectPhotoDialog;
import com.ebaonet.lawyer.ui.presenter.activity.BaseActivity;
import com.ebaonet.lawyer.ui.view.activity.PersonalInfoDelegate;
import com.ebaonet.lawyer.util.ImageUtils;
import com.ebaonet.lawyer.util.LoginHelp;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;
import java.net.URI;

/**
 * Created by tx on 2016/9/23.
 */
public class PersonalInfoActivity extends BaseActivity<PersonalInfoDelegate> implements View.OnClickListener {


    public static void actionActivity(Context context){
        if (LoginHelp.isLogin(context)){
            Intent intent = new Intent(context,PersonalInfoActivity.class);
            context.startActivity(intent);
        }else {
            LoginHelp.toLogin(context,"");
        }

    }

    @Override
    public void created(Bundle bundle){
        PersonBiz.queryPersonalInfo(PersonalInfoActivity.this, new MyRequestClient.Callback<NetBaseBean>() {
            @Override
            public void callback(NetBaseBean data) throws JSONException, IOException, ClassNotFoundException {

                if (data.isSuccess()){
                    String info = data.getResultData().getString("data");
                    Gson gson = new Gson();

                    PersonalInfoEntity personalInfoEntity = gson.fromJson(info,PersonalInfoEntity.class);
                    //赋予个人信息值
                    mView.getName().setData(personalInfoEntity.getUser_name());
                    mView.getArea().setData(personalInfoEntity.getOrg_name());
                    mView.getEmail_address().setData(personalInfoEntity.getEmail());
                    mView.getIndustry().setData(personalInfoEntity.getMobile());
                    mView.getLawyer_info().setData(personalInfoEntity.getIndustry());
                    mView.getProfession().setData(personalInfoEntity.getMajors());
                    mView.getTrade().setData(personalInfoEntity.getWork_year());
                    mView.getWork_years().setData(personalInfoEntity.getWork_year());
                    //todo 头像.
                    loadImage(GlobalConfig.API_URL+GlobalConfig.getImageUrl+personalInfoEntity.getAvatar_image_id());
                }
            }

            @Override
            public void onError(NetBaseBean data) {

            }
        });

    }
/*
    @param url
    */
    public void loadImage(String url){
        // leftImage.setImageBitmap(ImageUtils.getBitmapByBase64(url));
        if ("".equals(url)) {
            mView.getAvatar().setImageResource(R.mipmap.touxiang);
        } else {
//            mView.getAvatar().setImageBitmap(ImageUtils.getBitmapByBase64(url));
//            mView.getAvatar().setBorderColor(ContextCompat.getColor(PersonalInfoActivity.this,R.color.white));

            Uri uri = Uri.parse(url);
            mView.getAvatar().setImageURI(uri);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.avatar_relative:
                SelectPhotoDialog selectPhotoDialog =
               new SelectPhotoDialog(PersonalInfoActivity.this);
                selectPhotoDialog.setOnClickSelect(new SelectPhotoDialog.OnDialogClickListener() {
                    @Override
                    public void onOneClick(View v) {
                        doCamera();
                    }

                    @Override
                    public void onTwoClick(View v) {
                        doGallery();
                    }
                });

                selectPhotoDialog.show();
                break;
            case R.id.lawyer_info:
                LawyerAuthentication.actionActivity(PersonalInfoActivity.this);
                break;
        }
    }
}
