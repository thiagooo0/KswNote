package com.ksw.kswnote.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.ksw.kswnote.been.LocalNote;
import com.ksw.kswnote.been.LocalNoteBook;
import com.ksw.kswnote.been.Note;
import com.ksw.kswnote.been.NoteBook;
import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * the class to manager the table : note
 * Created by KwokSiuWang on 2017/10/2.
 */

class NoteTable {
    static final String strTableName = "note";
    static final String strTitle = "title";
    static final String strContent = "content";
    static final String strID = "id";
    static final String strNoteBookID = "note_book_id";

    String getCreateSQL() {
        return "create table " + strTableName + " ("
                + strID + " integer primary key autoincrement, "
                + strTitle + " text, "
                + strContent + " text, "
                + strNoteBookID + " integer,"
                + "FOREIGN KEY(" + strNoteBookID + ") REFERENCES " + NoteBookTable.strTableName + " (" + NoteBookTable.strID + "))";
    }

    Observable<Long> saveNote(BriteDatabase database, Note note) {

        return Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Long> e) throws Exception {
                //构建content value
                ContentValues values = new ContentValues();
                values.put(strTitle, note.getTitle());
                values.put(strContent, note.getNote());
                values.put(strNoteBookID, note.getNoteBook().getLocalID());
                long l = database.insert(strTableName, values);
//                database.close();
                Log.d("notetable", "insert reuslt:" + l);
                e.onNext(l);
            }
        }).observeOn(Schedulers.io());
    }

    Observable<ArrayList<Note>> getRecommendNote(BriteDatabase database) {
        return database.createQuery(strTableName, "select * from " + strTableName)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<SqlBrite.Query, ArrayList<Note>>() {
                    @Override
                    public ArrayList<Note> apply(@NonNull SqlBrite.Query query) throws Exception {
                        //把得到的书转化为列表
                        ArrayList<Note> list = new ArrayList<Note>();
                        Cursor cursor = query.run();
                        if (cursor != null) {
                            while (cursor.moveToNext()) {
                                list.add(new LocalNote(cursor.getInt(cursor.getColumnIndex(strID)),
                                        cursor.getString(cursor.getColumnIndex(strTitle)),
                                        cursor.getString(cursor.getColumnIndex(strContent))));
                                Log.d("NoteTable", "get Recommend note");
                            }
                        }
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
