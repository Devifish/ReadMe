package cn.devifish.readme.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.devifish.readme.view.fragment.BookListPageFragment;
import cn.devifish.readme.view.fragment.BookClassPageFragment;
import cn.devifish.readme.view.fragment.CommunityPageFragment;
import cn.devifish.readme.view.base.BaseMainPageFragment;

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

    private List<BaseMainPageFragment> mPageLayouts;

    public MainViewPageAdapter(FragmentManager fm) {
        super(fm);
        mPageLayouts = new ArrayList<>();
        mPageLayouts.add(new BookListPageFragment());
        mPageLayouts.add(new BookClassPageFragment());
        mPageLayouts.add(new CommunityPageFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mPageLayouts.get(position);
    }

    @Override
    public int getCount() {
        return mPageLayouts.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageLayouts.get(position).getPageTitle();
    }
}
