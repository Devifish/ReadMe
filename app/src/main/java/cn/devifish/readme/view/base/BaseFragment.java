package cn.devifish.readme.view.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by zhang on 2017/4/5.
 *
 * Fragment基类
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(bindLayout(), container, false);
        ButterKnife.bind(this, rootView);

        initVar();
        initView();
        return rootView;
    }

    @LayoutRes
    protected abstract int bindLayout();
    protected abstract void initVar();
    protected abstract void initView();

}
