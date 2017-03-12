package com.zhang.readme.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhang.readme.ui.fragment.BookListPageFragment;
import com.zhang.readme.ui.fragment.BookClassPageFragment;
import com.zhang.readme.ui.fragment.CommunityPageFragment;
import com.zhang.readme.ui.fragment.base.MainPageLayoutBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2017/1/19.
 *
 * 控制Main Activity的 PageView 左右切换
 *
 * @author zhang
 */

public class MainViewPageAdapter extends FragmentPagerAdapter {

    private List<MainPageLayoutBase> pageLayouts;

    public MainViewPageAdapter(FragmentManager fm) {
        super(fm);

        pageLayouts = new ArrayList<>();
        pageLayouts.add(new BookListPageFragment());
        pageLayouts.add(new BookClassPageFragment());
        pageLayouts.add(new CommunityPageFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return pageLayouts.get(position);
    }

    @Override
    public int getCount() {
        return pageLayouts.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageLayouts.get(position).getTitle();
    }
}
