package com.ksw.kswnote.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * 基本的adapter。通过data binding绑定数据
 * Created by KwokSiuWang on 2017/10/12.
 */

public abstract class KswAdapter extends BaseAdapter {

    protected abstract ArrayList getData();

    protected abstract Context getContext();

    protected abstract int getLayout();

    protected abstract int getVariableId();

    @Override
    public int getCount() {
        return getData().size();
    }

    @Override
    public Object getItem(int position) {
        return getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), getLayout(), parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ViewDataBinding) convertView.getTag();
        }
        binding.setVariable(getVariableId(), getItem(position));
        return convertView;
    }
}
