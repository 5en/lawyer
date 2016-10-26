package com.ebaonet.lawyer.ui.presenter.activity.mine;

import android.content.Context;
import android.content.Intent;

import com.ebaonet.lawyer.ui.presenter.activity.BaseActivity;
import com.ebaonet.lawyer.ui.view.activity.LawyerAuthenticationDelegate;

/**
 * Created by tx on 2016/10/24.
 */

public class LawyerAuthentication extends BaseActivity<LawyerAuthenticationDelegate>{

    public static void actionActivity(Context context){

        Intent intent = new Intent(context,LawyerAuthentication.class);
        context.startActivity(intent);
    }
}
