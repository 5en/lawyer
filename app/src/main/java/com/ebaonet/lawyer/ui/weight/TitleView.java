package com.ebaonet.lawyer.ui.weight;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebaonet.lawyer.R;


/**
 * 标题栏
 *
 * @author zhang xf
 * @version 1.0
 * @date 2013-7-16 下午3:08:34
 * @copyright MobileSolu
 */
public class TitleView extends FrameLayout {

    /**
     * 当前类型
     */
    private int mStyle;
    /**
     * 显示标题文字内容
     */
    private String mDisplayName;
    /**
     * 当前绑定AC
     */
    private Activity mBindActivity;
    /**
     * 点击事件监听
     */
    private OnClickListener mOnClickListener;
    /**
     * 设置title right text
     */
    private String mRightText;
    /**
     * 设置title right image
     */
    TextView titlerighttext;
    ImageView titlerightimg;
    TextView titleleft;
    TextView titlecenter;


    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        this.mStyle = typedArray.getInt(R.styleable.TitleBar_display_style, Style.CENTER);
        this.mDisplayName = typedArray.getString(R.styleable.TitleBar_displayname);
        this.mRightText = typedArray.getString(R.styleable.TitleBar_right_display_name);
        buildTitleBar(typedArray);
        typedArray.recycle();
    }

    public TitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void buildTitleBar(TypedArray typedArray) {
        addView(LinearLayout.inflate(getContext(), R.layout.layout_component_titlebar, null));

        RelativeLayout normal = (RelativeLayout) findViewById(R.id.title_normal);
        titlecenter = (TextView) findViewById(R.id.title_center);
        titleleft = (TextView) findViewById(R.id.title_left);

        titlerightimg = (ImageView) findViewById(R.id.title_right_img);
        titlerighttext = (TextView) findViewById(R.id.title_right_text);
        if (mRightText != null) {
            titlerighttext.setText(mRightText);
        }
        int showLeftDrawable = typedArray.getResourceId(R.styleable.TitleBar_has_left_drawable, 0);
        if (showLeftDrawable == 1) {
            titleleft.setCompoundDrawables(null, null, null, null);
        }


        titlerightimg.setImageResource(typedArray.getResourceId(R.styleable.TitleBar_right_display_image, R.mipmap.quxiao));
        if (mStyle == Style.LEFTCENTER) {
            titlecenter.setVisibility(View.VISIBLE);
            titleleft.setVisibility(View.VISIBLE);
            titlerightimg.setVisibility(View.GONE);
            titlerighttext.setVisibility(View.GONE);
            normal.setVisibility(View.VISIBLE);
        } else if (mStyle == Style.RIGHTCENTERIMG) {
            titlecenter.setVisibility(View.VISIBLE);
            titleleft.setVisibility(View.GONE);
            titlerightimg.setVisibility(View.VISIBLE);
            titlerighttext.setVisibility(View.GONE);
            normal.setVisibility(View.VISIBLE);
        } else if (mStyle == Style.RIGHTCENTERTEXT) {
            titlecenter.setVisibility(View.VISIBLE);
            titleleft.setVisibility(View.GONE);
            titlerightimg.setVisibility(View.GONE);
            titlerighttext.setVisibility(View.VISIBLE);
            normal.setVisibility(View.VISIBLE);
        } else if (mStyle == Style.CENTER) {
            titlecenter.setVisibility(View.VISIBLE);
            titleleft.setVisibility(View.GONE);
            titlerightimg.setVisibility(View.GONE);
            titlerighttext.setVisibility(View.GONE);
            normal.setVisibility(View.VISIBLE);
        } else if (mStyle == Style.ALL_RIGHT_IMG) {
            titlecenter.setVisibility(View.VISIBLE);
            titleleft.setVisibility(View.VISIBLE);
            titlerightimg.setVisibility(View.VISIBLE);
            titlerighttext.setVisibility(View.GONE);
            normal.setVisibility(View.VISIBLE);
        } else if (mStyle == Style.ALL_RIGHT_TEXT) {
            titlecenter.setVisibility(View.VISIBLE);
            titleleft.setVisibility(View.VISIBLE);
            titlerightimg.setVisibility(View.GONE);
            titlerighttext.setVisibility(View.VISIBLE);
            normal.setVisibility(View.VISIBLE);
        } else if (mStyle == Style.LEFT) {
            normal.setVisibility(View.VISIBLE);
            titlecenter.setVisibility(View.GONE);
            titleleft.setVisibility(View.VISIBLE);
            titlerightimg.setVisibility(View.GONE);
            titlerighttext.setVisibility(View.GONE);
        }
        titleleft.setTag(mStyle);
        titlecenter.setText(mDisplayName);
        titleleft.setOnClickListener(mDefOnClickListener);
    }

    private void resetOnClickListener() {
        TextView titleleft = (TextView) findViewById(R.id.title_left);
        ImageView titlerightimg = (ImageView) findViewById(R.id.title_right_img);
        TextView titlerighttext = (TextView) findViewById(R.id.title_right_text);

        titleleft.setTag(mStyle);
        titleleft.setOnClickListener(mOnClickListener);
        titlerightimg.setTag(mStyle);
        titlerightimg.setOnClickListener(mOnClickListener);
        titlerighttext.setTag(mStyle);
        titlerighttext.setOnClickListener(mOnClickListener);
    }

    public void setDisplayName(String displayName, int type, OnClickListener onClickListener) {
        this.mDisplayName = displayName;
        this.mStyle = type;
        TextView titlecenter = (TextView) findViewById(R.id.title_center);
        TextView titleleft = (TextView) findViewById(R.id.title_left);
        ImageView titlerightimg = (ImageView) findViewById(R.id.title_right_img);
        TextView titlerighttext = (TextView) findViewById(R.id.title_right_text);


        RelativeLayout normal = (RelativeLayout) findViewById(R.id.title_normal);
        titlecenter.setText(mDisplayName);
        if (mStyle == Style.LEFTCENTER) {
            titlecenter.setVisibility(View.VISIBLE);
            titleleft.setVisibility(View.VISIBLE);
            titlerightimg.setVisibility(View.GONE);
            titlerighttext.setVisibility(View.GONE);
            normal.setVisibility(View.VISIBLE);
        } else if (mStyle == Style.RIGHTCENTERIMG) {
            titlecenter.setVisibility(View.VISIBLE);
            titleleft.setVisibility(View.GONE);
            titlerightimg.setVisibility(View.VISIBLE);
            titlerighttext.setVisibility(View.GONE);
            normal.setVisibility(View.VISIBLE);
        } else if (mStyle == Style.RIGHTCENTERTEXT) {
            titlecenter.setVisibility(View.VISIBLE);
            titleleft.setVisibility(View.GONE);
            titlerightimg.setVisibility(View.GONE);
            titlerighttext.setVisibility(View.VISIBLE);
            normal.setVisibility(View.VISIBLE);
        } else if (mStyle == Style.CENTER) {
            titlecenter.setVisibility(View.VISIBLE);
            titleleft.setVisibility(View.GONE);
            titlerightimg.setVisibility(View.GONE);
            titlerighttext.setVisibility(View.GONE);
            normal.setVisibility(View.VISIBLE);
        } else if (mStyle == Style.ALL_RIGHT_IMG) {
            titlecenter.setVisibility(View.VISIBLE);
            titleleft.setVisibility(View.VISIBLE);
            titlerightimg.setVisibility(View.VISIBLE);
            titlerighttext.setVisibility(View.GONE);
            normal.setVisibility(View.VISIBLE);
        } else if (mStyle == Style.ALL_RIGHT_TEXT) {
            titlecenter.setVisibility(View.VISIBLE);
            titleleft.setVisibility(View.VISIBLE);
            titlerightimg.setVisibility(View.GONE);
            titlerighttext.setVisibility(View.VISIBLE);
            normal.setVisibility(View.VISIBLE);
        } else if (mStyle == Style.LEFT) {
            titlecenter.setVisibility(View.GONE);
            titleleft.setVisibility(View.VISIBLE);
            titlerightimg.setVisibility(View.GONE);
            titlerighttext.setVisibility(View.VISIBLE);
            normal.setVisibility(View.VISIBLE);
        }
        this.mOnClickListener = onClickListener;
        resetOnClickListener();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
        resetOnClickListener();
    }

    public void bindActivity(Activity activity) {
        this.mBindActivity = activity;
    }

    public void setRightTvText(String rightText) {
        TextView titlerighttext = (TextView) findViewById(R.id.title_right_text);
        titlerighttext.setText(rightText);
        titlerighttext.setVisibility(VISIBLE);
    }

    private OnClickListener mDefOnClickListener = new OnClickListener() {
        public void onClick(View v) {
            if (v.getId() == R.id.title_left) {
                int style = (Integer) v.getTag();
                if (mBindActivity != null) {
                    mBindActivity.finish();
                }

                if (style == Style.LEFTCENTER && mBindActivity != null) {
                } else if (style == Style.LEFT && mBindActivity != null) {
                }
            }
        }
    };

    public static class Style {
        /**
         * leftcenter title
         */
        public static int LEFTCENTER = 0;
        /**
         * 三个全显示右img title
         */
        public static int ALL_RIGHT_IMG = 1;
        /**
         * 三个全显示右text title
         */
        public static int ALL_RIGHT_TEXT = 2;
        /**
         * rightcenterimg title
         */
        public static int RIGHTCENTERIMG = 3;
        /**
         * rightcentertext title
         */
        public static int RIGHTCENTERTEXT = 4;
        /**
         * center title
         */
        public static int CENTER = 5;
        /**
         * homepage title
         */
        public static int HOMEPAGE = 6;
        /**
         * left title
         */
        public static int LEFT = 7;

    }

    public void setTitleText(String text) {
        TextView titlecenter = (TextView) findViewById(R.id.title_center);
        titlecenter.setText(text);
    }


    public void setRtvOnClickListener(OnClickListener listener) {
        TextView titlerighttext = (TextView) findViewById(R.id.title_right_text);
        titlerighttext.setOnClickListener(listener);
    }

    public void setLeftOnclickLister(OnClickListener lister) {
        TextView title_left = (TextView) findViewById(R.id.title_left);
        title_left.setOnClickListener(lister);
    }

    public void setRightTextOnclickListener(OnClickListener listener) {
        if (titlerighttext != null) {
            titlerighttext.setOnClickListener(listener);
        }

    }

    public void setRightImage(int resource) {
        if (titlerightimg != null) {
            titlerightimg.setImageResource(resource);
        }
    }

    public void setRightImageOnclickListener(@NonNull OnClickListener li) {
        titlerightimg.setOnClickListener(li);
    }

    public void setRImageOnClickListener(OnClickListener listener) {
        ImageView title_right_img = (ImageView) findViewById(R.id.title_right_img);
        title_right_img.setOnClickListener(listener);
    }


    public void setRightTextVisible(boolean isVisible) {
        if (isVisible) {
            titlerighttext.setVisibility(VISIBLE);
        } else {
            titlerighttext.setVisibility(GONE);
        }
    }

    public void setLeftImageVisible(boolean isVisible, Drawable drawable) {
        if (isVisible) {
            titleleft.setCompoundDrawables(drawable, null, null, null);
        } else {
            titleleft.setCompoundDrawables(null, null, null, null);

        }
    }

    public void setContentVisible(boolean isVisible) {
        if (isVisible) {
            titlecenter.setVisibility(VISIBLE);
        } else {
            titlecenter.setVisibility(GONE);
        }
    }

    public void setLeftText(String text) {
        titleleft.setText(text);
    }


}
