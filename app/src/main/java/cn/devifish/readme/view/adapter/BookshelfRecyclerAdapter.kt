package cn.devifish.readme.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.devifish.readme.R
import cn.devifish.readme.entity.Book
import cn.devifish.readme.view.base.BaseRecyclerAdapter
import cn.devifish.readme.view.base.BaseViewHolder
import kotlinx.android.synthetic.main.book_item_bookshelf.view.*

/**
 * Created by zhang on 2017/6/6.
 *
 */
class BookshelfRecyclerAdapter(): BaseRecyclerAdapter<Book, BookshelfRecyclerAdapter.BookshelfHolder>() {

    constructor(data: MutableList<Book>) : this() {
        super.data = data
    }

    override fun onCreateView(group: ViewGroup, viewType: Int): BookshelfHolder {
        val view = LayoutInflater.from(group.context).inflate(R.layout.book_item_bookshelf, group, false)
        return BookshelfHolder(view, listener)
    }

    override fun onBindView(holder: BookshelfHolder, position: Int) = holder.bind(getItem(position)!!)

    class BookshelfHolder(itemView: View, listener: BaseViewHolder.OnItemClickListener?) : BaseViewHolder<Book>(itemView, listener) {

        override fun bind(m: Book) {
            itemView.title.text = m.title
            itemView.author.text = m.author
        }
    }

}