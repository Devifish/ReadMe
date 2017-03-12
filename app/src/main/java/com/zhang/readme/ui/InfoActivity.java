package com.zhang.readme.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zhang.readme.R;
import com.zhang.readme.tools.Config;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //设置关于Activity 版本号信息
        TextView version = (TextView) findViewById(R.id.info_version);
        version.setText(String.format(version.getText().toString(), Config.AppInfo.getVersionName(this)));


    }
}
