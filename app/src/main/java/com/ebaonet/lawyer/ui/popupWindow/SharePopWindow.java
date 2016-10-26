package com.ebaonet.lawyer.ui.popupWindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.ebaonet.lawyer.R;
import com.ebaonet.lawyer.util.ScreenUtils;


/**
 * Created by tx on 2016/6/23.
 */
public class SharePopWindow extends PopupWindow {

    private Context mContext;
    private View mView;
    private View.OnClickListener mItemClickListener;

    public SharePopWindow(Context context, View.OnClickListener itemClickListener) {
        mView = LayoutInflater.from(context).inflate(R.layout.pop_share,null);
        mItemClickListener = itemClickListener;
//
         this.mContext = context;
//
        this.setContentView(mView);

        int width = ScreenUtils.getScreenWidth(context);
        int height = ScreenUtils.getScreenHeight(context);
        this.setHeight(height);
        this.setWidth(width);
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        this.setBackgroundDrawable(dw);


        initView();
    }


    private void initView() {
        Button cancel = (Button) mView.findViewById(R.id.cancel);
        RelativeLayout can_be_covered_parent = (RelativeLayout)mView.findViewById(R.id.can_be_covered_parent);
        cancel.setOnClickListener(dismissListener);
        can_be_covered_parent.setOnClickListener(dismissListener);
        LinearLayout wechat = (LinearLayout) mView.findViewById(R.id.wechat);
        LinearLayout circle = (LinearLayout)mView.findViewById(R.id.circle);
        LinearLayout qq = (LinearLayout)mView.findViewById(R.id.qq);
        LinearLayout weibo = (LinearLayout)mView.findViewById(R.id.weibo);
        LinearLayout email = (LinearLayout)mView.findViewById(R.id.email);
        LinearLayout message = (LinearLayout)mView.findViewById(R.id.message);

        wechat.setOnClickListener(mItemClickListener);
        circle.setOnClickListener(mItemClickListener);
        qq.setOnClickListener(mItemClickListener);
        weibo.setOnClickListener(mItemClickListener);
        email.setOnClickListener(mItemClickListener);
        message.setOnClickListener(mItemClickListener);

    }



   private View.OnClickListener dismissListener = new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           dismiss();
       }
   };


}
