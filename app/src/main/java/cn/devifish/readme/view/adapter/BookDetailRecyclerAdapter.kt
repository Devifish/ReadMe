package cn.devifish.readme.view.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.devifish.readme.R
import cn.devifish.readme.entity.Book
import cn.devifish.readme.provider.BookProvider
import cn.devifish.readme.service.BookService
import cn.devifish.readme.util.RxJavaUtil
import cn.devifish.readme.view.base.BaseRecyclerAdapter
import cn.devifish.readme.view.base.BaseViewHolder
import kotlinx.android.synthetic.main.header_book_detail.view.*
import java.text.SimpleDateFormat

/**
 * Created by zhang on 2017/8/3.
 * 书籍详情列表适配器
 */
class BookDetailRecyclerAdapter(val book: Book) : BaseRecyclerAdapter<Any, BaseViewHolder<Any>>() {

    private val bookProvider = BookProvider.getInstance().create(BookService::class.java)

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
        val view = layoutInflater.inflate(R.layout.header_book_detail, group, false)
        return BookDetailHolder(view)
    }

    override fun onBindView(holder: BaseViewHolder<Any>, position: Int) {
        when (getItemViewType(position)) {
            TYPE_HEADER -> (holder as BookDetailHolder).bind(book)
            TYPE_NORMAL -> {

            }
        }
    }

    override fun getItemCount(): Int = if (super.getItemCount() < 1) 1 else super.getItemCount()

    inner class BookDetailHolder(itemView: View) : BaseViewHolder<Any>(itemView) {

        @SuppressLint("SimpleDateFormat")
        override fun bind(m: Any) {
            val context = itemView.context
            RxJavaUtil.getObservable(bookProvider.getBookDetail((m as Book)._id!!))
                    .subscribe { bookDetail ->
                        itemView.serial_info.text = context.getString(if (bookDetail.isSerial) R.string.book_serial else R.string.book_end)
                        itemView.update.text = SimpleDateFormat("yyyy-MM-dd").format(bookDetail.updated)
                        itemView.chapter_count.text = context.getString(R.string.book_chapterCount, bookDetail.chaptersCount.toString())
                        itemView.book_intro.text = bookDetail.longIntro
                    }
        }

    }



}