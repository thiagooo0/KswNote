package com.ksw.kswnote.base;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;

/**
 * 做揭示动画的fragment
 * Created by KwokSiuWang on 2017/9/29.
 */

public abstract class RevealFragment extends BaseFragment {
    //是否做揭示动画
    public boolean isShowCircularReveal = false;
    private int circleX = 0;
    private int circleY = 0;

    public void showCircularReveal(View view){
        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (Build.VERSION.SDK_INT > 21 ) {
                    circleShow(v);
                }
            }
        });
    }

    public void setShowCircularReveal(boolean isShowCircularReveal, int circleX, int circleY) {
        this.isShowCircularReveal = isShowCircularReveal;
        this.circleX = circleX;
        this.circleY = circleY;
    }

    public void setShowCircularReveal(boolean isShowCircularReveal) {
        this.isShowCircularReveal = isShowCircularReveal;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void circleShow(View view) {
        if (!isShowCircularReveal) {
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                Animator animator = ViewAnimationUtils.createCircularReveal(view
                        , circleX, circleY, 0, view.getHeight());

                //在动画开始的地方速率改变比较慢,然后开始加速
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(500);
                animator.start();

                //做完动画后下次就不再做了。
                isShowCircularReveal = false;
            }
        });

    }
}
