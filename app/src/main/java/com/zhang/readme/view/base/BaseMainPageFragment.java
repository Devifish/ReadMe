package com.zhang.readme.view.base;

/**
 * Created by zhang on 2017/2/22.
 *
 * MainActivity ViewPageFragment基类
 */

public abstract class BaseMainPageFragment extends BaseFragment {

    private String mTitle = "无标题";

    public String getPageTitle() {
        return this.mTitle;
    }

    public void setPageTitle(String title) {
        this.mTitle = title;
    }
}
