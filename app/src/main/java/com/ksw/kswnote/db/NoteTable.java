package com.ksw.kswnote.db;

import android.content.ContentValues;
import android.util.Log;

import com.ksw.kswnote.been.Note;
import com.ksw.kswnote.been.NoteBook;
import com.squareup.sqlbrite2.BriteDatabase;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
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
                database.close();
                Log.d("notetable", "insert reuslt:" + l);
                e.onNext(l);
            }
        }).observeOn(Schedulers.io());
    }
}
