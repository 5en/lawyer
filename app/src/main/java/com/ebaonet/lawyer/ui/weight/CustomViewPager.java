package com.ebaonet.lawyer.ui.weight;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 作者：yzb on 2016/6/21 15:42
 * 邮箱：280766447@qq.com
 */
public class CustomViewPager extends ViewPager {
    private boolean isCanScroll = false;
    private static final String TAG = "CustomViewPager";
    private boolean result = false;
    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CustomViewPager(Context context) {
        super(context);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if(result)
            return super.onInterceptTouchEvent(arg0);
        else
            return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if(result)
            return super.onTouchEvent(arg0);
        else
            return false;
    }
    public void setScanScroll(boolean isCanScroll){
        this.isCanScroll = isCanScroll;
    }
}

