package cn.devifish.readme.view.module.community

import android.view.View
import cn.devifish.readme.R
import cn.devifish.readme.view.base.MainPageFragment

/**
 * Created by zhang on 2017/6/9.
 *
 */
class CommunityFragment: MainPageFragment() {

    private val title = "社区"

    override fun bindLayout(): Int = R.layout.page_community_main
    override fun getTitle(): String = this.title

    override fun initVar() {
    }

    override fun initView(view: View?) {
    }

    override fun onRefresh() {
    }

    override fun onItemClick(view: View, position: Int) {
    }

    override fun onItemLongClick(view: View, position: Int) {
    }

}