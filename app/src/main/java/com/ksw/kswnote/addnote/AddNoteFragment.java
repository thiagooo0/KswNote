package com.ksw.kswnote.addnote;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;

import com.ksw.kswnote.R;
import com.ksw.kswnote.base.BaseFragment;
import com.ksw.kswnote.databinding.FragmentAddNoteBinding;

/**
 * 添加笔记
 * Created by KwokSiuWang on 2017/9/28.
 */

public class AddNoteFragment extends BaseFragment {
    private FragmentAddNoteBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false);
//        binding.getRoot().getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                circleShow();
//                return false;
//            }
//        });
        binding.getRoot().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (Build.VERSION.SDK_INT > 21) {
                    circleShow();
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
//        circleShow();


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void circleShow() {
        binding.getRoot().post(new Runnable() {
            @Override
            public void run() {
                // 圆形动画的x坐标  位于View的中心
                int cx = (binding.getRoot().getLeft() + binding.getRoot().getRight()) / 2;

                //圆形动画的y坐标  位于View的中心
                int cy = (binding.getRoot().getTop() + binding.getRoot().getBottom()) / 2;

                //起始大小半径
                float startX = 0f;

                //结束大小半径 大小为图片对角线的一半
                float startY = (float) Math.sqrt(cx * cx + cy * cy);
                Animator animator = ViewAnimationUtils.createCircularReveal(binding.getRoot(), cx, cy, 0, binding.getRoot().getHeight());

                //在动画开始的地方速率改变比较慢,然后开始加速
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(500);
                animator.start();
            }
        });

    }
}
