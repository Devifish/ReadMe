package com.zhang.readme.view;

import android.widget.TextView;

import com.zhang.readme.R;
import com.zhang.readme.util.Config;
import com.zhang.readme.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoActivity extends BaseActivity {

    @BindView(R.id.version) TextView mVersion;

    @Override
    protected int bindLayout() {return R.layout.activity_info;}

    @Override
    protected void initView() {
        mVersion.setText(String.format(mVersion.getText().toString(), Config.AppInfo.getVersionName(this)));
    }

    @Override
    protected void initVar() {

    }
}
