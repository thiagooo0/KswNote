package com.ksw.kswnote.been;

import java.util.ArrayList;

/**
 * 书
 * Created by KwokSiuWang on 2017/9/29.
 */

public interface NoteBook {
    ArrayList<Note> getNotes();

    String getTitle();

    int getLocalID();
}
