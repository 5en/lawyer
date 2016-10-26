package com.ebaonet.lawyer.ui.presenter.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.ebaonet.lawyer.ui.view.activity.SplashDelegate;
import com.yanglaoClient.mvp.util.core.StringUtils;


/**
 * 作者：yzb on 2016/7/4 18:14
 * 邮箱：280766447@qq.com
 */
public class SplashActivity extends BaseActivity<SplashDelegate> {
    private Handler handler = new Handler();

    @Override
    public void created(Bundle savedInstance) {
        super.created(savedInstance);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        nextToDo();
    }


    private void nextToDo() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                        Intent it = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(it);
                        SplashActivity.this.finish();
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler = null;
    }
}
