package cn.devifish.readme.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.devifish.readme.R
import cn.devifish.readme.entity.Book
import cn.devifish.readme.view.base.BaseRecyclerAdapter
import cn.devifish.readme.view.base.BaseViewHolder
import kotlinx.android.synthetic.main.book_item_stack.view.*

/**
 * Created by zhang on 2017/6/9.
 *
 */
class StackItemListRecyclerAdapter(list: MutableList<Book>): BaseRecyclerAdapter<Book, StackItemListRecyclerAdapter.StackItemListHolder>(list) {

    override fun onCreateView(group: ViewGroup, viewType: Int): StackItemListHolder {
        val view = LayoutInflater.from(group.context).inflate(R.layout.book_item_stack, group, false)
        return StackItemListHolder(view, listener)
    }

    override fun onBindView(holder: StackItemListHolder, position: Int) = holder.bind(getItem(position))


    class StackItemListHolder(itemView: View, listener: BaseViewHolder.OnItemClickListener?): BaseViewHolder<Book>(itemView, listener) {

        override fun bind(m: Book) {
            itemView.title.text = m.title
            itemView.author.text = m.author
        }

    }

}