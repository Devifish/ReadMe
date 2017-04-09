package com.zhang.readme.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhang on 2017/4/5.
 *
 * Fragment基类
 */

public abstract class BaseFragment extends Fragment {

    protected int mLayoutId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(mLayoutId, container, false);
        initVar();
        initView(rootView);
        initViewState();
        return rootView;
    }

    protected void setContentView(int id) {
        this.mLayoutId = id;
    }

    protected abstract void initVar();
    protected abstract void initView(View view);
    protected abstract void initViewState();

}
