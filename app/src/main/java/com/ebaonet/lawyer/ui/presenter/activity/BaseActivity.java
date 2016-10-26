package com.ebaonet.lawyer.ui.presenter.activity;

/**
 * 作者：yzb on 2016/9/8 14:48
 * 邮箱：280766447@qq.com
 */

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import com.ebaonet.lawyer.ui.dialog.LoadDialog;
import com.ebaonet.lawyer.ui.dialog.TwoSelectDialog;
import com.yanglaoClient.mvp.presenter.FragmentActivityPresenterImpl;
import com.yanglaoClient.mvp.view.AppDelegate;



public abstract class BaseActivity<T extends AppDelegate> extends FragmentActivityPresenterImpl<T> {

    private Toast mToast;
    private LoadDialog mDialog;
    public TwoSelectDialog twoSelectDialog;

    @Override
    public void create(Bundle savedInstance) {
        //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
//        setTitleColor();
        super.create(savedInstance);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    public void alert(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public void showRequestDialog(String msg, boolean canCanceledOnTouch, boolean canCancel) {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
        mDialog = new LoadDialog(this);
        mDialog.setLoadingText(msg);
        mDialog.setCanceledOnTouchOutside(canCanceledOnTouch);
        mDialog.setCanCancel(canCancel);
        mDialog.show();
    }

    public void closeRequestDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public TwoSelectDialog showSelectDialog(String title, String msg, boolean canCanceledOnTouch, boolean canCancel) {
        if (twoSelectDialog != null) {
            twoSelectDialog.dismiss();
            twoSelectDialog = null;
        }
        twoSelectDialog = new TwoSelectDialog(this);
        twoSelectDialog.setText(title, msg);
        twoSelectDialog.setButtonName("取消", "确认");
        twoSelectDialog.setCanceledOnTouchOutside(canCanceledOnTouch);
        twoSelectDialog.setCanCancel(canCancel);
        twoSelectDialog.show();
        return twoSelectDialog;
    }

    public void closeSelectDialog() {
        if (twoSelectDialog != null) {
            twoSelectDialog.dismiss();
            twoSelectDialog = null;
        }
    }


    protected void onResume() {
        super.onResume();
//        JPushInterface.onResume(this);
    }

    protected void onPause() {
        super.onPause();
//        JPushInterface.onPause(this);
    }

}
