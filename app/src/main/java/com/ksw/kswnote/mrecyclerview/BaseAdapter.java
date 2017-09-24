package com.ksw.kswnote.mrecyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * 通用的adapter
 * Created by KwokSiuWang on 2017/9/24.
 */

public class BaseAdapter<T> extends XRecyclerView.Adapter<BaseViewHolder> {
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

    public BaseAdapter(List<T> mData, int layoutId, int brId) {
        this.mData = mData;
        this.layoutId = layoutId;
        this.brId = brId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        BaseViewHolder baseViewHolder = new BaseViewHolder(binding.getRoot());
        baseViewHolder.setBinding(binding);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.getBinding().setVariable(brId, mData.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
