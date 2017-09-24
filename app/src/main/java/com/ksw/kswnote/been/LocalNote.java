package com.ksw.kswnote.been;

/**
 * 本地笔记
 * Created by KwokSiuWang on 2017/9/24.
 */

public class LocalNote implements Note {

    private String strNote;
    private long time;

    public LocalNote() {

    }

    public LocalNote(String strNote) {
        this.strNote = strNote;
        time = System.currentTimeMillis();
    }

    @Override
    public String getNote() {
        return strNote;
    }

    public String getStrNote() {
        return strNote;
    }

    public void setStrNote(String strNote) {
        this.strNote = strNote;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
