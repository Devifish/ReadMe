package cn.devifish.readme.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import cn.devifish.readme.view.module.read.ReadFragment

/**
 * Created by zhang on 2017/8/7.
 *
 */
class ReadViewPageAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return ReadFragment.newInstance()
    }

    override fun getCount(): Int {
        return 5
    }
}