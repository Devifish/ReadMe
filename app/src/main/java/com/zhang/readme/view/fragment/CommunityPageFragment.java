package com.zhang.readme.view.fragment;

import android.view.View;

import com.zhang.readme.R;
import com.zhang.readme.view.base.BaseMainPageFragment;

/**
 * Created by zhang on 2017/2/22.
 *
 * @author zhang
 */

public class CommunityPageFragment extends BaseMainPageFragment {

    private final static String mTitle = "社区";

    public CommunityPageFragment() {
        super.setPageTitle(mTitle);
        super.setContentView(R.layout.page_community_main);
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
