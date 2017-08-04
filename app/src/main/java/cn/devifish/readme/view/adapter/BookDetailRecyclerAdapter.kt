package cn.devifish.readme.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.devifish.readme.R
import cn.devifish.readme.entity.Book
import cn.devifish.readme.view.base.BaseRecyclerAdapter
import cn.devifish.readme.view.base.BaseViewHolder
import java.util.*

/**
 * Created by zhang on 2017/8/3.
 * 书籍详情列表适配器
 */
class BookDetailRecyclerAdapter : BaseRecyclerAdapter<Any, BaseViewHolder<Any>>() {

    companion object {
        val TYPE_HEADER = 0
        val TYPE_NORMAL = 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return TYPE_HEADER
        else return TYPE_NORMAL
    }

    override fun onCreateView(group: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        val layoutInflater = LayoutInflater.from(group.context)
        when (viewType) {
            TYPE_HEADER -> {

            }
            TYPE_NORMAL -> {

            }
        }
        val view = layoutInflater.inflate(R.layout.book_info_header_book_detail, group, false)
        return BookDetailHolder(view)
    }

    override fun onBindView(holder: BaseViewHolder<Any>, position: Int) {
        when (getItemViewType(position)) {
            TYPE_HEADER -> {
                (holder as BookDetailHolder).bind(getItem(position)!!)
            }
            TYPE_NORMAL -> {

            }
        }
    }

    override fun getItemCount(): Int = if (super.getItemCount() < 1) 1 else super.getItemCount()

    inner class BookDetailHolder(itemView: View) : BaseViewHolder<Any>(itemView) {

        override fun bind(m: Any) {

        }

    }



}