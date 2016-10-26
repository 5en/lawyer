package com.ebaonet.lawyer.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.util.ScreenUtils;


/**
 * Created by dong on 2016/3/6.
 */
public class SelectPhotoDialog {
    private Dialog dialog;
    private OnDialogClickListener listener;
    private ImageView iv_finish;
    private LinearLayout album_layout,camera_layout;

    public SelectPhotoDialog(Context context) {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        dialog = new Dialog(context, R.style.dialog);

        // dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_select_photo);
        iv_finish = (ImageView) dialog.findViewById(R.id.iv_finish);
        album_layout=(LinearLayout)dialog.findViewById(R.id.album_layout);
        camera_layout=(LinearLayout)dialog.findViewById(R.id.camera_layout);
        // 设置弹出的动画效果

        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.dialog_from_bottom);
        WindowManager.LayoutParams lp = window.getAttributes();
        int width = ScreenUtils.getScreenWidth(context);
        int height = ScreenUtils.getScreenHeight(context);
        lp.width = (int) (width);
        lp.gravity = Gravity.BOTTOM;
        addListener();
    }

    private void addListener() {
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        camera_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOneClick(v);
                dialog.dismiss();
            }
        });
        album_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTwoClick(v);
                dialog.dismiss();
            }
        });

    }

    public void setOnClickSelect(OnDialogClickListener listener) {
        this.listener = listener;
    }

    public interface OnDialogClickListener {
        public void onOneClick(View v);

        public void onTwoClick(View v);

    }

    public void show() {
        dialog.show();
    }
}
