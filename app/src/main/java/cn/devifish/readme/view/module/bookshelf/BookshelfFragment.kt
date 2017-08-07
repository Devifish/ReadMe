package cn.devifish.readme.view.module.bookshelf

import android.support.v7.widget.GridLayoutManager
import android.view.View
import cn.devifish.readme.R
import cn.devifish.readme.view.adapter.BookshelfRecyclerAdapter
import cn.devifish.readme.view.base.MainPageFragment
import kotlinx.android.synthetic.main.page_bookshelf_main.view.*

/**
 * Created by zhang on 2017/6/3.
 * 书架Fragment
 */
class BookshelfFragment : MainPageFragment() {

    private val title = "书架"
    private val adapter: BookshelfRecyclerAdapter = BookshelfRecyclerAdapter()

    override fun bindLayout(): Int = R.layout.page_bookshelf_main
    override fun getTitle(): String = this.title

    override fun initVar() {}

    override fun initView(view: View) {
        view.refresh.setOnRefreshListener(this)
        view.refresh.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light)

        view.rv_bookshelf.layoutManager = GridLayoutManager(context, 3)
        view.rv_bookshelf.adapter = adapter
        adapter.setOnItemClickListener(this)
    }

    override fun onRefresh() {}

    override fun onItemClick(view: View, position: Int) {
    }

    override fun onItemLongClick(view: View, position: Int) {
    }

}