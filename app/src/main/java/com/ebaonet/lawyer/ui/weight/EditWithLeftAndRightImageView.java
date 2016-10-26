package com.ebaonet.lawyer.ui.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ebaonet.lawyer.R;

/**
 * Created by tx on 2016/6/17.
 */
public class EditWithLeftAndRightImageView extends FrameLayout {


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
    //private ImageView right_image;

    public EditWithLeftAndRightImageView(Context context) {
        super(context);
    }

    public EditWithLeftAndRightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditWithLeftAndRightImageView);

//        addView(LayoutInflater.from(getContext()).inflate(R.layout.view_edit_with_left_and_right_image_view,null));
//        left_image = (ImageView) findViewById(R.id.left_image);
//        content = (EditText) findViewById(R.id.content);
       // right_image = (ImageView)findViewById(R.id.right_image);
        content.setHint(typedArray.getString(R.styleable.EditWithLeftAndRightImageView_hint2));
        content.setText(typedArray.getString(R.styleable.EditWithLeftAndRightImageView_content2));
        left_image.setImageResource(typedArray.getResourceId(R.styleable.EditWithLeftAndRightImageView_left_image2,R.mipmap.shezhi));
       // right_image.setImageResource(typedArray.getResourceId(R.styleable.EditWithLeftAndRightImageView_right_image2,R.mipmap.jiantou));
        //  buildItem();
        typedArray.recycle();
    }

    public EditWithLeftAndRightImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @param
     * */

    public String getContent(){
        return content.getText().toString();
    }

    public void setContent(String stringText){
        content.setText(stringText);
    }

    public boolean isEmpty(){
        return  "".equals(content.getText().toString());
    }
    //设置输入内容为数字

    public void setInputContentFormatNumber(){
        content.setInputType(EditorInfo.TYPE_CLASS_PHONE);
    }

    public void setContentSecret(){
        content.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    public void setLimitLenth(int lenth){
        content.setFilters(new InputFilter[]{new InputFilter.LengthFilter(lenth)});
    }

    public void setOnlyDigits(){
        content.setKeyListener(new DigitsKeyListener(false,true));
    }
    public void setOnlyDigitsAndLeter(){
        content.addTextChangedListener(new TextWatcher() {

            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }



            @Override

            public void beforeTextChanged(CharSequence s, int start, int count,

                                          int after) {

            }



            @Override

            public void afterTextChanged(Editable edt) {

                try {

                    String temp = edt.toString();

                    String tem = temp.substring(temp.length()-1, temp.length());

                    char[] temC = tem.toCharArray();

                    int mid = temC[0];

                    if(mid>=48&&mid<=57){//数字

                        return;

                    }

                    if(mid>=65&&mid<=90){//大写字母

                        return;

                    }

                    if(mid>97&&mid<=122){//小写字母

                        return;

                    }

                    edt.delete(temp.length()-1, temp.length());

                } catch (Exception e) {

// TODO: handle exception

                }

            }

        });
    }

    public EditText getContentEdit(){
        return  content;
    }


}
