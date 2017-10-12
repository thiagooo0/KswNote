package com.ksw.kswnote.mrecyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的adapter
 * Created by KwokSiuWang on 2017/9/24.
 */

public class BaseRecycleAdapter<T> extends XRecyclerView.Adapter<BaseRecycleViewHolder> {
    /**
     * 数据源
     */
    private List<T> mData;

    /**
     * 布局id
     */
    private int layoutId;

    /**
     * data binding id
     */
    private int brId;

    public BaseRecycleAdapter(@NonNull List<T> mData, int layoutId, int brId) {
        this.mData = mData;
        this.layoutId = layoutId;
        this.brId = brId;
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        BaseRecycleViewHolder baseViewHolder = new BaseRecycleViewHolder(binding.getRoot());
        baseViewHolder.setBinding(binding);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, int position) {
        holder.getBinding().setVariable(brId, mData.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void updateDate(ArrayList<T> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
}
