package com.ebaonet.lawyer.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ebaonet.lawyer.R;


/**
 * Created by dong on 2016/3/6.
 */
public class SelectCallDialog {
    private TextView call,cancel;
    private Dialog dialog;
    private OnDialogClickListener listener;

    public SelectCallDialog(Context context) {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        dialog = new Dialog(context, R.style.dialog);
        // dialog.setCancelable(true);

        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_call);
        cancel=(TextView) dialog.findViewById(R.id.cancel);
        call=(TextView)dialog.findViewById(R.id.call);
        // 设置弹出的动画效果

        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.dialog_from_bottom);
        WindowManager.LayoutParams lp = window.getAttributes();
        int width = getScreenWidth(context);
        int height = getScreenHeight(context);
        lp.width = (int) (width);
        lp.gravity = Gravity.BOTTOM;
        addListener();
    }

    private void addListener() {
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void setOnClickSelect(OnDialogClickListener listener) {
        this.listener = listener;
    }

    public interface OnDialogClickListener {
         void onClick(View v);

    }

    public void show() {
        dialog.show();
    }

    public int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

}
