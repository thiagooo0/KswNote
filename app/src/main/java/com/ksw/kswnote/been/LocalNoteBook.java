package com.ksw.kswnote.been;

import java.util.ArrayList;

/**
 * 本地的笔记本
 * Created by KwokSiuWang on 2017/9/29.
 */

public class LocalNoteBook implements NoteBook {
    private ArrayList<Note> notes = new ArrayList<>();

    @Override
    public ArrayList<Note> getNotes() {
        return notes;
    }
}
