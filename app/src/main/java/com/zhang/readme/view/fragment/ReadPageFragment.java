package com.zhang.readme.view.fragment;

import android.os.Bundle;

import com.zhang.readme.view.base.BaseFragment;

/**
 * Created by zhang on 2017/4/18.
 */

public class ReadPageFragment extends BaseFragment {



    public static ReadPageFragment newInstance() {
        Bundle args = new Bundle();

        ReadPageFragment fragment = new ReadPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return 0;
    }

    @Override
    protected void initVar() {

    }

    @Override
    protected void initView() {

    }
}
