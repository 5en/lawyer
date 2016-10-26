package com.ebaonet.lawyer.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.util.ScreenUtils;


/**
 * Created by dong on 2015/10/30.
 */
public class TwoSelectDialog {

    private Context context;
    private TextView textView1;
    private TextView textView2;
    private TextView tvTitle;
    private TextView tvMessage;
    private Dialog dialog;
    private OnDialogClickListener listener;

    public TwoSelectDialog(Context context) {
        this.context = context;
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        dialog = new Dialog(context, R.style.dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_two_select);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        int width = ScreenUtils.getScreenWidth(context);
        int height = ScreenUtils.getScreenHeight(context);
        lp.width = (int) (0.8 * width);

        textView1 = (TextView) dialog.findViewById(R.id.tv_play1);
        textView2 = (TextView) dialog.findViewById(R.id.tv_play2);
        tvTitle = (TextView) dialog.findViewById(R.id.tv_title);
        tvMessage = (TextView) dialog.findViewById(R.id.tv_message);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.onOneClick(v);
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //退出主界面
                listener.onTwoClick(v);
            }
        });


    }

    public boolean isShowing(){
        return dialog.isShowing();
    }
    public void setOnClickSelect(OnDialogClickListener listener) {
        this.listener = listener;
    }

    public interface OnDialogClickListener {
        public void onOneClick(View v);

        public void onTwoClick(View v);

    }


    public void setText(String title, String msg) {

        if (!msg.isEmpty()) {
            tvMessage.setVisibility(View.VISIBLE);
            tvMessage.setText(msg);
        }
        tvTitle.setText(title);
    }

    public void setButtonName(String left, String right) {
        textView1.setText(left);
        textView2.setText(right);
    }



    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setTextView2Gone() {
        textView2.setVisibility(View.GONE);
//        dialog.findViewById(R.id.line).setVisibility(View.GONE);
    }
    public void setTextView1Gone() {
        textView1.setVisibility(View.GONE);
//        dialog.findViewById(R.id.line).setVisibility(View.GONE);
    }
    public void setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
    }

    public void setCanCancel(boolean cancel) {
        dialog.setCancelable(cancel);
    }
}