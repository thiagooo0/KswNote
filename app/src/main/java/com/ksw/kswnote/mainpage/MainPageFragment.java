package com.ksw.kswnote.mainpage;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ksw.kswnote.BR;
import com.ksw.kswnote.R;
import com.ksw.kswnote.base.BaseFragment;
import com.ksw.kswnote.been.Note;
import com.ksw.kswnote.databinding.FragmentMainPageBinding;
import com.ksw.kswnote.db.SQLiteHelper;
import com.ksw.kswnote.mrecyclerview.BaseRecycleAdapter;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * 显示笔记列表
 * Created by KwokSiuWang on 2017/9/27.
 */

public class MainPageFragment extends BaseFragment {
    private final String TAG = MainPageFragment.class.getSimpleName();
    private FragmentMainPageBinding binding;
    private BaseRecycleAdapter adapter;

    private ObservableField<ArrayList<Note>> noteList = new ObservableField<>(new ArrayList<>());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, container, false);

        initRecycleView();
        updateData();
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MainPageFragment", "onResume");
    }

    private void initFloatingButton() {
    }

    private void initRecycleView() {
        Log.d("MainPageFragment", "initRecycleView");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        //设置Item增加、移除动画
//        binding.recyclerView.addHeaderView(headerView);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
//        binding.recyclerView.setLoadingMoreEnabled(true);
        binding.recyclerView.setRefreshProgressStyle(ProgressStyle.SysProgress);
//        binding.recyclerView.setLoadingMoreProgressStyle(ProgressStyle.LineScaleParty);
        binding.recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                binding.recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
            }
        });
        //一无所有的时候显示什么
        binding.recyclerView.setEmptyView(binding.emptyView.getRoot());


        adapter = new BaseRecycleAdapter(null, R.layout.item_note, BR.note);
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void updateData() {
        Log.d("MainPageFragment", "updateData");
//        ArrayList<Note> list = new ArrayList<Note>();
//        list.add(new LocalNote("还等吗，你爱戴钻戒，要他爱戴吗"));
//        list.add(new LocalNote("无人忘记仍能闪闪发光"));
//        list.add(new LocalNote("何必等他一吻去韬光"));
//        list.add(new LocalNote("从某年某天某地"));
//        list.add(new LocalNote("谁得到过愿放手"));
//        list.add(new LocalNote("如果失约在这生，无需相见在某年"));
//        list.add(new LocalNote("谈你谈我的新趣味，无法忘记当天的你"));
//        list.add(new LocalNote("完完全全共醉他生也愿意"));
//        adapter.updateDate(list);

        new SQLiteHelper(getActivity(), null)
                .getRecommendNote()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Note>>() {
                    @Override
                    public void accept(ArrayList<Note> notes) throws Exception {
                        Log.d(TAG, "get new recommend note");
                        if (adapter != null) {
                            adapter.updateDate(notes);
                        }
                    }
                });
    }


    @Override
    public FragmentType getType() {
        return FragmentType.MainPageFragment;
    }
}
