package com.ebaonet.lawyer.util;

import android.content.Context;
import android.content.Intent;

import com.ebaonet.lawyer.ui.presenter.activity.LoginActivity;
import com.ebaonet.lawyer.util.sharePreferences.PreferencesManger;

/**
 * Created by tx on 2016/10/11.
 */

public class LoginHelp {

    public static boolean isLogin(Context context){

        if (PreferencesManger.getPassword(context).equals("")&&
                PreferencesManger.getPassword(context).equals("")){
            return false;
        }else {
            return true;
        }
    }

    /**
     * @param code 应用id
     */
    public static void toLogin(Context context, String code) {
        Intent it = new Intent(context, LoginActivity.class);
        it.putExtra(LoginActivity.LOGIN_CLASS, code);
        context.startActivity(it);
    }
}
