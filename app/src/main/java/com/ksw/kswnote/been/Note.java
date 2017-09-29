package com.ksw.kswnote.been;

/**
 * 笔记
 * Created by KwokSiuWang on 2017/9/24.
 */

public interface Note {
    /**
     * 得到笔记的内容
     */
    public String getNote();

    /**
     * @return 属于哪个笔记本
     */
    public NoteBook getNoteBook();
}
