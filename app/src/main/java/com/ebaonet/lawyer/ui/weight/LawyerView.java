package com.ebaonet.lawyer.ui.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ebaonet.lawyer.R;

/**
 * 作者：yzb on 2016/10/9 14:26
 * 邮箱：280766447@qq.com
 */
public class LawyerView extends FrameLayout {
    public LawyerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        intoViews();
    }

    public LawyerView(Context context) {
        super(context);
        intoViews();
    }

    public LawyerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intoViews();
    }

    private void intoViews() {
        addView(LinearLayout.inflate(getContext(), R.layout.view_lawyer, null));
    }
}
