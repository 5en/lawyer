package com.ebaonet.lawyer.ui.presenter.activity;

import android.content.Context;
import android.content.Intent;

import com.ebaonet.lawyer.ui.view.activity.SuccessDelegate;

/**
 * 作者：yzb on 2016/10/13 09:31
 * 邮箱：280766447@qq.com
 */
public class SuccessActivity extends BaseActivity<SuccessDelegate>{
    public static void actionActivity(Context context){
        context.startActivity(new Intent(context,SuccessActivity.class));
    }
}
