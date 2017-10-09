package com.ksw.kswnote.db;

import android.database.Cursor;

import com.ksw.kswnote.been.LocalNoteBook;
import com.ksw.kswnote.been.NoteBook;
import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * the class to manager the table : note
 * Created by KwokSiuWang on 2017/10/2.
 */

class NoteBookTable {

    static final String strTableName = "notebook";
    static final String strName = "name";
    static final String strID = "id";

    String getCreateSQL() {
        return "create table " + strTableName + " ("
                + strID + " integer primary key autoincrement, "
                + strName + " text)";
    }

    /**
     * get all the book
     */
    Observable<ArrayList<NoteBook>> getAllNoteBooks(BriteDatabase database) {
        return database.createQuery(strTableName, "select * from " + strTableName)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<SqlBrite.Query, ArrayList<NoteBook>>() {
                    @Override
                    public ArrayList<NoteBook> apply(@NonNull SqlBrite.Query query) throws Exception {
                        //把得到的书转化为列表
                        ArrayList<NoteBook> list = new ArrayList<NoteBook>();
                        Cursor cursor = query.run();
                        if (cursor != null) {
                            while (cursor.moveToNext()) {
                                list.add(new LocalNoteBook(cursor.getString(cursor.getColumnIndex(strName)),
                                        cursor.getInt(cursor.getColumnIndex(strID))));
                            }
                        }
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
