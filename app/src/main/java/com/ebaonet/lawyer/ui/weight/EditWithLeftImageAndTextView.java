package com.ebaonet.lawyer.ui.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebaonet.lawyer.R;


/**
 * Created by tx on 2016/6/17.
 */
public class EditWithLeftImageAndTextView extends FrameLayout{

    /**
     * 左边图片
     * */
    private ImageView left_image;
    /**
     * edit内容
     * */
    private EditText content;
    /**
     * 右边图片
     * */
    private TextView left_text;

    public EditWithLeftImageAndTextView(Context context) {
        super(context);
    }

    public EditWithLeftImageAndTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditWithLeftImageAndTextView);

        addView(LayoutInflater.from(getContext()).inflate(R.layout.view_edit_with_left_image_and_text,null));
        left_image = (ImageView) findViewById(R.id.left_image);
        content = (EditText) findViewById(R.id.content);
        left_text = (TextView)findViewById(R.id.left_text);

        left_text.setText(typedArray.getString(R.styleable.EditWithLeftImageAndTextView_leftText2));
        content.setHint(typedArray.getString(R.styleable.EditWithLeftImageAndTextView_edit_hint));
        left_image.setImageResource(typedArray.getResourceId(R.styleable.EditWithLeftImageAndTextView_leftImage,R.mipmap.shezhi));
        int showImage = typedArray.getInt(R.styleable.EditWithLeftImageAndTextView_showLeftImage, View.GONE);
        left_image.setVisibility(showImage);

        //  buildItem();
        typedArray.recycle();
    }

    public EditWithLeftImageAndTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @param
     * */

    public String getContent (){
        return  content.getText().toString();
    }

    public void setHideWord(){
        content.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    public void setLimitLenth(int lenth){
        content.setFilters(new InputFilter[]{new InputFilter.LengthFilter(lenth)});
    }

    public void setOnlyDigits(){
        content.setKeyListener(new DigitsKeyListener(false,true));
    }
}
