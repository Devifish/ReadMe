package com.zhang.readme.ui.fragment.base;

import android.support.v4.app.Fragment;

/**
 * Created by zhang on 2017/2/22.
 *
 *
 */

public class MainPageLayoutBase extends Fragment {

    private String title = "无标题";

    public String getTitle() {return this.title;}

    public void setTitle(String title) {
        this.title = title;
    }
}
