package com.ebaonet.lawyer.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ebaonet.lawyer.R;
import com.github.rahatarmanahmed.cpv.CircularProgressView;


/**
 * Created by Administrator on 2015/11/9.
 */
public class LoadDialog {


    private Context mContext;
    private Dialog mDialog;
    private CircularProgressView mLoadingView;
    private TextView textView;
    private View mDialogContentView;


    public LoadDialog(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        mDialog = new Dialog(mContext, R.style.dialog);
        mDialogContentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_loading, null);


        mLoadingView = (CircularProgressView) mDialogContentView.findViewById(R.id.progress_view);
        textView = (TextView) mDialogContentView.findViewById(R.id.tv_loading);
        mDialog.setContentView(mDialogContentView);
    }

    public void setBackground(int color) {
        GradientDrawable gradientDrawable = (GradientDrawable) mDialogContentView.getBackground();
        gradientDrawable.setColor(color);
    }

    public void setLoadingText(CharSequence charSequence) {
        textView.setText(charSequence);
    }

    public void show() {
        mDialog.show();

    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public Dialog getDialog() {
        return mDialog;
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
    }

    public void setCanCancel(boolean cancel) {
        mDialog.setCancelable(cancel);
    }
}