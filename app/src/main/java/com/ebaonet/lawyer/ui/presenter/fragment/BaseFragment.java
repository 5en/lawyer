package com.ebaonet.lawyer.ui.presenter.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.ebaonet.lawyer.ui.dialog.LoadDialog;
import com.yanglaoClient.mvp.presenter.FragmentPresenterImpl;
import com.yanglaoClient.mvp.view.AppDelegate;

/**
 * BaseFragment
 */
public abstract class BaseFragment<T extends AppDelegate> extends FragmentPresenterImpl<T> {
    private LoadDialog mDialog;

    public void showRequestDialog(String msg, boolean canCanceledOnTouch, boolean canCancel) {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
        mDialog = new LoadDialog(getActivity());
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

}
