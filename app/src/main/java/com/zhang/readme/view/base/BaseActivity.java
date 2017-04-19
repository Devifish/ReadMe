package com.zhang.readme.view.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by zhang on 2017/3/30.
 *
 * Activity基类
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        ButterKnife.bind(this);

        initVar();
        initView();
    }

    @LayoutRes
    protected abstract int bindLayout();
    protected abstract void initVar();
    protected abstract void initView();

}
