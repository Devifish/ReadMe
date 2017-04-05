package com.zhang.readme.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.readme.R;

/**
 * Created by zhang on 2017/3/30.
 *
 * Activity基类，对各操作进行分块
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVar();
        initView();
        initViewState();
    }

    protected abstract void initView();
    protected abstract void initViewState();
    protected abstract void initVar();

}
