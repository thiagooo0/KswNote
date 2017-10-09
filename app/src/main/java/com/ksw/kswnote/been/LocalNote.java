package com.ksw.kswnote.been;

/**
 * 本地笔记
 * Created by KwokSiuWang on 2017/9/24.
 */

public class LocalNote implements Note {

    private String content;
    private String title;
    private long time;
    private NoteBook noteBook;

    public LocalNote() {

    }

    public LocalNote(String strNote) {
        this.content = strNote;
        time = System.currentTimeMillis();
    }

    @Override
    public String getNote() {
        return content;
    }

    @Override
    public NoteBook getNoteBook() {
        return noteBook;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }


    public void setNoteBook(NoteBook noteBook) {
        this.noteBook = noteBook;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
