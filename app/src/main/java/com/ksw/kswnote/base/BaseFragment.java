package com.ksw.kswnote.base;


import android.support.v4.app.Fragment;

/**
 * the base fragment
 * Created by KwokSiuWang on 2017/9/27.
 */

public abstract class BaseFragment extends Fragment {
    public enum FragmentType {AddNoteFragment, MainPageFragment}

    public abstract FragmentType getType();
}
