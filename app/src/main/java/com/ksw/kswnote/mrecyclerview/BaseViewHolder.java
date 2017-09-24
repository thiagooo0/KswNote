package com.ksw.kswnote.mrecyclerview;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * 通用的viewHolder
 * Created by KwokSiuWang on 2017/9/24.
 */

public class BaseViewHolder extends XRecyclerView.ViewHolder {
    private ViewDataBinding binding;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    public void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }
}
