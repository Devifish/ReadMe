package cn.devifish.readme.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cn.devifish.readme.view.base.MainPageFragment
import cn.devifish.readme.view.module.bookshelf.BookshelfFragment
import cn.devifish.readme.view.module.community.CommunityFragment
import cn.devifish.readme.view.module.stack.StackFragment

/**
 * Created by zhang on 2017/6/4.
 * 主页ViewPage适配器
 */
class MainViewPageAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val list : Array<MainPageFragment> = arrayOf(BookshelfFragment(), StackFragment(), CommunityFragment());

    override fun getItem(position : Int): Fragment = list[position]

    override fun getCount(): Int = list.size

    override fun getPageTitle(position: Int): CharSequence = list[position].getTitle()

}