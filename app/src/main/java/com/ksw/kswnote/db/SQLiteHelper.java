package com.ksw.kswnote.db;

import android.animation.ValueAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ksw.kswnote.been.LocalNote;
import com.ksw.kswnote.been.Note;
import com.ksw.kswnote.been.NoteBook;
import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * my SQLite open helper
 * Created by KwokSiuWang on 2017/10/2.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "kswnote.db";
    private static final int version = 1;
    /**
     * 新建笔记本
     */
    public static final String CREATE_NOTE_BOOK = "create table notebook ("
            + "id integer primary key autoincrement, "
            + "name text)";

    /**
     * 新建笔记
     */
    public static final String CREATE_NOTE = "create table note ("
            + "id integer primary key autoincrement, "
            + "title text, "
            + "content text, "
            + "note_book_id integer,"
            + "FOREIGN KEY(note_book_id) REFERENCES notebook (id))";

    private NoteTable noteTable = new NoteTable();
    private NoteBookTable noteBookTable = new NoteBookTable();

    private BriteDatabase database;

    public SQLiteHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory, version);
        database = new SqlBrite.Builder().build()
                .wrapDatabaseHelper(this, Schedulers.io());
    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(noteTable.getCreateSQL());
        db.execSQL(noteBookTable.getCreateSQL());
        ContentValues values = new ContentValues();
        values.put("name", "default");
        db.insert("notebook", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Observable<ArrayList<NoteBook>> getAllNotes() {
        return noteBookTable.getAllNoteBooks(database);
    }

    public Observable<Long> saveNote(Note note) {
        return noteTable.saveNote(database, note);
    }
}
