package cn.devifish.readme.view.module.bookshelf

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import cn.devifish.readme.R
import cn.devifish.readme.entity.Book
import cn.devifish.readme.view.adapter.BookshelfRecyclerAdapter
import cn.devifish.readme.view.base.MainPageFragment
import kotlinx.android.synthetic.main.page_bookshelf_main.*

/**
 * Created by zhang on 2017/6/3.
 * 书架Fragment
 */
class BookshelfFragment : MainPageFragment() {

    private val title = "书架"
    private var adapter: BookshelfRecyclerAdapter? = null

    override fun bindLayout(): Int = R.layout.page_bookshelf_main
    override fun getTitle(): String = this.title

    override fun initVar() {
        adapter = BookshelfRecyclerAdapter()
    }

    override fun initView(view: View?) {
        super.initView(view)
        rv_bookshelf.layoutManager = GridLayoutManager(context, 3)
        rv_bookshelf.adapter = adapter
        adapter!!.setOnItemClickListener(this)
    }

    override fun onRefresh() {}

    override fun onItemClick(view: View, position: Int) {
    }

    override fun onItemLongClick(view: View, position: Int) {
    }

}