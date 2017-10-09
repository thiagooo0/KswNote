package com.ksw.kswnote.been;

/**
 * 本地笔记
 * Created by KwokSiuWang on 2017/9/24.
 */

public class LocalNote implements Note {

    private int id;
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
    public LocalNote(int id, String title, String strNote) {
        this.id = id;
        this.title = title;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
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
