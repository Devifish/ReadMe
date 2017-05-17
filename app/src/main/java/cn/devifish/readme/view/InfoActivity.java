package cn.devifish.readme.view;

import android.widget.TextView;

import cn.devifish.readme.R;
import cn.devifish.readme.util.Config;
import cn.devifish.readme.view.base.BaseActivity;

import butterknife.BindView;

public class InfoActivity extends BaseActivity {

    @BindView(R.id.version) TextView mVersion;

    @Override
    protected int bindLayout() {return R.layout.activity_info;}

    @Override
    protected void initView() {
        mVersion.setText(getString(R.string.version, Config.AppInfo.getVersionName(this)));
    }

    @Override
    protected void initVar() {

    }
}
