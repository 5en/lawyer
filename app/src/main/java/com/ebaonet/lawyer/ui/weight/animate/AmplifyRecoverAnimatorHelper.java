package com.ebaonet.lawyer.ui.weight.animate;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;



/**
 * Created by tx on 2016/7/28.
 */
public class AmplifyRecoverAnimatorHelper implements IAnimatorHelper {
    private int width;
    private int height;

    public void setAnimate(final View view){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        width = view.getLayoutParams().width;
                        height = view.getLayoutParams().height;

                        muplifyViewAnimate(view, 0.1f);

                        break;
                    case MotionEvent.ACTION_UP:
                        // anima recover

                        float times = ((float) view.getLayoutParams().height)/(float) height -1;
                        Log.e("times",times+"");
                        recoverViewAnimate(view,times);
                        break;
                }
                return false;
            }
        });

    }
    /**
     * @param view 将要动画的view
     *             @param times 要扩大的倍数
     * */
    public  void muplifyViewAnimate(final View view, final float times){

        //todo anima maplify
        view.clearAnimation();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(200);
        valueAnimator.setDuration(200);
        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        final int height = layoutParams.height;
        final int width  = layoutParams.width;
        // valueAnimator.setInterpolator(myMaplifyInterpolator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.e("amplify",animation.getAnimatedFraction()+"");

                layoutParams.height = (int) (height *( 1+ animation.getAnimatedFraction()*times));
                layoutParams.width = (int)(width *(1+animation.getAnimatedFraction()*times));
                view.setLayoutParams(layoutParams);
                view.invalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * @param view 将要动画变化的view。
     *             @param times，由于是之前是扩大动画，这里的times是实时的现在/以前。 看demo,注意计算机里int相除得到的是只取整，必须转化成float再相除
     * */
    public  void recoverViewAnimate(final View view,final float times){

        //todo anima maplify

        view.clearAnimation();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(200);
        //valueAnimator.setInterpolator(myInterpolator);
        valueAnimator.setDuration(200);
        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        final int height = layoutParams.height;
        final int width  = layoutParams.width;
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.e("amplify",animation.getAnimatedFraction()+"");
                Log.e("times",times+"");
                layoutParams.height = (int) (height /(1+(animation.getAnimatedFraction() * times)));
                layoutParams.width = (int)(width /(1+(animation.getAnimatedFraction()* times)));
                view.setLayoutParams(layoutParams);
                view.invalidate();
            }
        });
        valueAnimator.start();
    }

}
