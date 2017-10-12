package com.ksw.kswnote.addnote;

import android.content.Context;
import android.widget.SpinnerAdapter;

import com.ksw.kswnote.BR;
import com.ksw.kswnote.R;
import com.ksw.kswnote.base.KswAdapter;
import com.ksw.kswnote.been.NoteBook;

import java.util.ArrayList;

/**
 * 选择笔记本的设配器
 * Created by KwokSiuWang on 2017/10/12.
 */

public class NBSpinnerAdapter extends KswAdapter implements SpinnerAdapter {
    private ArrayList<NoteBook> mBooks;

    public Context mContext;

    public NBSpinnerAdapter(Context context, ArrayList<NoteBook> books) {
        this.mBooks = books;
        this.mContext = context;
    }

    @Override
    protected ArrayList getData() {
        return mBooks;
    }

    @Override
    protected Context getContext() {
        return mContext;
    }

    @Override
    protected int getLayout() {
        return R.layout.item_spanner_notebook;
    }

    @Override
    protected int getVariableId() {
        return BR.notebook;
    }
}
