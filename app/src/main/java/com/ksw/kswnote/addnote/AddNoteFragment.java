package com.ksw.kswnote.addnote;


import android.content.ContentValues;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.ksw.kswnote.R;
import com.ksw.kswnote.base.BaseFragment;
import com.ksw.kswnote.been.LocalNote;
import com.ksw.kswnote.been.LocalNoteBook;
import com.ksw.kswnote.been.NoteBook;
import com.ksw.kswnote.databinding.FragmentAddNoteBinding;
import com.ksw.kswnote.db.SQLiteHelper;
import com.ksw.kswnote.mainpage.MainActivity;
import com.squareup.sqlbrite2.BriteContentResolver;
import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;

import org.reactivestreams.Subscription;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.IntFunction;
import io.reactivex.internal.subscriptions.ArrayCompositeSubscription;
import io.reactivex.schedulers.Schedulers;

/**
 * 添加笔记
 * Created by KwokSiuWang on 2017/9/28.
 */

public class AddNoteFragment extends BaseFragment {
    private FragmentAddNoteBinding binding;
    /**
     * record the not
     */
    private LocalNote note;

    private NoteBook localNoteBook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false);
        note = new LocalNote();
        getNotebooks();
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

    private void getNotebooks() {
        SQLiteHelper helper = new SQLiteHelper(getContext(), null);
        helper.getAllNotes()
                .subscribe(noteBooks -> {
                    for (NoteBook book : noteBooks) {
                        Log.d("DATABASE", book.getTitle() + " : " + ((LocalNoteBook) book).getLocalID());
                    }
                    if (noteBooks.size() != 0) {
                        localNoteBook = noteBooks.get(0);
                    }
                });
    }

    public void completeNote() {
        String text = binding.etNote.getText().toString();
        if (!TextUtils.isEmpty(text)) {
            //如果不是空的话，那就存储本条笔记。
            note.setNoteBook(localNoteBook);
            note.setContent(text);
            new SQLiteHelper(getActivity(), null)
                    .saveNote(note)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        binding.etNote.setText("");
                        ((MainActivity) getActivity()).updateFragment(FragmentType.MainPageFragment);
                    });
        } else {
            ((MainActivity) getActivity()).updateFragment(FragmentType.MainPageFragment);
        }
//        SqlBrite brite = new SqlBrite.Builder().build();
//        BriteDatabase database = brite.wrapDatabaseHelper(new SQLiteHelper(getActivity(), null), Schedulers.io());
//        ContentValues contentValues = new ContentValues();
//        contentValues.put();
//        database.insert("note", null, )
//        Observable<SqlBrite.Query> notebooks = database.createQuery("notebook", "select * from notebook");
//
//        notebooks.observeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<SqlBrite.Query>() {
//                    @Override
//                    public void accept(SqlBrite.Query query) throws Exception {
//                        Cursor cursor = query.run();
//                        if (cursor != null) {
//                            while (cursor.moveToNext()) {
//                                Log.d("database", cursor.getString(cursor.getColumnIndex("name")));
//                            }
//                        } else {
//                            Log.d("database", "cursor is null");
//                        }
//                    }
//                });


    }

    @Override
    public FragmentType getType() {
        return FragmentType.AddNoteFragment;
    }
}
