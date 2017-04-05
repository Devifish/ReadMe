package com.zhang.readme.view;

import android.widget.TextView;

import com.zhang.readme.R;
import com.zhang.readme.util.Config;
import com.zhang.readme.view.base.BaseActivity;

public class InfoActivity extends BaseActivity {

    TextView mVersion;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_info);

        mVersion = (TextView) findViewById(R.id.info_version);

    }

    @Override
    protected void initViewState() {
        mVersion.setText(String.format(mVersion.getText().toString(), Config.AppInfo.getVersionName(this)));
    }

    @Override
    protected void initVar() {

    }
}
