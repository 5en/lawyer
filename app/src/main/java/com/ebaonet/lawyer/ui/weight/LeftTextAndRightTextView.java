package com.ebaonet.lawyer.ui.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebaonet.lawyer.R;


/**
 * Created by tx on 2016/6/17.
 */
public class LeftTextAndRightTextView extends FrameLayout implements ISetData {
    private TextView leftText;
    private TextView rightText;
    private ImageView jiantou;

    public LeftTextAndRightTextView(Context context) {
        super(context);
    }

    public LeftTextAndRightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LeftTextAndRightTextView);

        addView(LayoutInflater.from(getContext()).inflate(R.layout.view_left_text_and_right_text,null));
        leftText = (TextView) findViewById(R.id.leftText);
        jiantou = (ImageView)findViewById(R.id.jiantou);
        rightText = (TextView) findViewById(R.id.rightText);
        leftText.setText(typedArray.getString(R.styleable.LeftTextAndRightTextView_leftText));
        rightText.setText(typedArray.getString(R.styleable.LeftTextAndRightTextView_rightText));
        leftText.setTextColor(typedArray.getColor(R.styleable.LeftTextAndRightTextView_leftTextColor, ContextCompat.getColor(context,R.color.black1)));
        rightText.setTextColor(typedArray.getColor(R.styleable.LeftTextAndRightTextView_rightTextColor, ContextCompat.getColor(context,R.color.TextColor3)));

         boolean isShowImage = typedArray.getBoolean(R.styleable.LeftTextAndRightTextView_isShowImage,true);
        if (isShowImage){
            jiantou.setVisibility(VISIBLE);
        }else {
            jiantou.setVisibility(GONE);
        }
        typedArray.recycle();
    }

    public LeftTextAndRightTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setData(String data){
        rightText.setText(data);
    }

}
