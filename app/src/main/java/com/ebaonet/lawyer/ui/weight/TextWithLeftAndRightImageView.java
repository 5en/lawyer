package com.ebaonet.lawyer.ui.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebaonet.lawyer.R;
import com.yanglaoClient.mvp.util.core.StringUtils;

/**
 * Created by tx on 2016/6/17.
 */
public class TextWithLeftAndRightImageView extends FrameLayout {

    /**
     * 当前类型
     */
    private int mStyle;
    /**
     * 显示标题文字内容
     */
    private String mDisplayName;

    private ImageView left_image;

    private TextView content;
    private ImageView right_image;
    private TextView mMessageCount;

    public TextWithLeftAndRightImageView(Context context) {
        super(context);
    }

    public TextWithLeftAndRightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextWithLeftAndRightImageView);

        addView(LayoutInflater.from(getContext()).inflate(R.layout.text_with_left_and_right_image_view, null));
        left_image = (ImageView) findViewById(R.id.left_image);
        mMessageCount = (TextView)findViewById(R.id.mMessageCount) ;
        content = (TextView) findViewById(R.id.content);
        right_image = (ImageView) findViewById(R.id.right_image);
        String contentText = typedArray.getString(R.styleable.TextWithLeftAndRightImageView_content);
        if (!StringUtils.isEmpty(contentText)) {
            content.setText(contentText);
        }
        int showLeftImage = typedArray.getInt(R.styleable.TextWithLeftAndRightImageView_show_left_image, View.GONE);
        left_image.setVisibility(showLeftImage);
        left_image.setImageResource(typedArray.getResourceId(R.styleable.TextWithLeftAndRightImageView_left_image1, R.mipmap.shezhi));
        right_image.setImageResource(typedArray.getResourceId(R.styleable.TextWithLeftAndRightImageView_right_image1, R.mipmap.jiantou));
        //  buildItem();
        typedArray.recycle();
    }

    public TextWithLeftAndRightImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ImageView getRight_image(){
        return right_image;
    }

    public void setContent(String text){
        content.setText(text);
    }

public void setRightImage(int id){
    right_image.setImageResource(id);
}
    /**
     * @param
     * */

    //todo loadimage .

    public void showMessageAndSetCount(String count){
        if ("0".equals(count)){
            mMessageCount.setVisibility(GONE);
        }else {
            mMessageCount.setVisibility(VISIBLE);
            mMessageCount.setText(count);
        }

    }
}
