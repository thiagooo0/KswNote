package com.ksw.kswnote.addnote;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import com.ksw.kswnote.R;
import com.ksw.kswnote.base.BaseFragment;
import com.ksw.kswnote.base.RevealFragment;
import com.ksw.kswnote.been.LocalNote;
import com.ksw.kswnote.databinding.FragmentAddNoteBinding;
import com.ksw.kswnote.mainpage.MainActivity;

/**
 * 添加笔记
 * Created by KwokSiuWang on 2017/9/28.
 */

public class AddNoteFragment extends BaseFragment {
    private FragmentAddNoteBinding binding;
    private LocalNote note;
//    private boolean isShowCircularReveal = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.getRoot().requestFocus();
//        circleShow();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void completeNote() {
        String text = binding.etNote.getText().toString();
        if (!TextUtils.isEmpty(text)) {
            ((MainActivity) getActivity()).updateFragment(FragmentType.MainPageFragment);
        } else {
            ((MainActivity) getActivity()).updateFragment(FragmentType.MainPageFragment);
        }
    }

//    private int circleX = 0;
//    private int circleY = 0;
//
//    public void setShowCircularReveal(boolean isShowCircularReveal, int circleX, int circleY){
//        this.isShowCircularReveal = isShowCircularReveal;
//        this.circleX = circleX;
//    }

//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public void circleShow() {
//        binding.getRoot().post(new Runnable() {
//            @Override
//            public void run() {
//                // 圆形动画的x坐标  位于View的中心
//                int cx = (binding.getRoot().getLeft() + binding.getRoot().getRight()) / 2;
//
//                //圆形动画的y坐标  位于View的中心
//                int cy = (binding.getRoot().getTop() + binding.getRoot().getBottom()) / 2;
//
//                //起始大小半径
//                float startX = 0f;
//
//                //结束大小半径 大小为图片对角线的一半
//                float startY = (float) Math.sqrt(cx * cx + cy * cy);
//                Animator animator = ViewAnimationUtils.createCircularReveal(binding.getRoot(), circleX, circleY, 0, binding.getRoot().getHeight());
//
//                //在动画开始的地方速率改变比较慢,然后开始加速
//                animator.setInterpolator(new AccelerateInterpolator());
//                animator.setDuration(500);
//                animator.start();
//            }
//        });
//
//    }

    @Override
    public FragmentType getType() {
        return FragmentType.AddNoteFragment;
    }
}
