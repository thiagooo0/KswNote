package com.ksw.kswnote.been;

import java.util.ArrayList;

/**
 * 本地的笔记本
 * Created by KwokSiuWang on 2017/9/29.
 */

public class LocalNoteBook implements NoteBook {
    private ArrayList<Note> notes = new ArrayList<>();

    private String title;
    private int localID;

    public LocalNoteBook(String title, int localID) {
        this.title = title;
        this.localID = localID;
    }

    public String getTitle() {
        return title;
    }

    public int getLocalID() {
        return localID;
    }

    @Override
    public ArrayList<Note> getNotes() {
        return notes;
    }
}
