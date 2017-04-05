package com.zhang.readme.view.fragment;

import android.view.View;

import com.zhang.readme.R;
import com.zhang.readme.view.base.MainPageFragmentBase;

/**
 * Created by zhang on 2017/2/22.
 *
 * @author zhang
 */

public class BookClassPageFragment extends MainPageFragmentBase {

    private final static String mTitle = "分类";

    public BookClassPageFragment() {
        super.setPageTitle(mTitle);
        super.setContentView(R.layout.page_class_main);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initViewState() {

    }

    @Override
    protected void initVar() {

    }
}
