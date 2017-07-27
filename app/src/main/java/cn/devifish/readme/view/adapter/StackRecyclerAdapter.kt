package cn.devifish.readme.view.adapter

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.devifish.readme.R
import cn.devifish.readme.entity.Book
import cn.devifish.readme.entity.Stack
import cn.devifish.readme.view.base.BaseRecyclerAdapter
import cn.devifish.readme.view.base.BaseViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.list_item_stack.view.*
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat.startActivity
import android.widget.ImageView
import cn.devifish.readme.view.module.bookdetail.BookDetailActivity


/**
 * Created by zhang on 2017/6/9.
 *
 */

class StackRecyclerAdapter(list: MutableList<Stack>) : BaseRecyclerAdapter<Stack, StackRecyclerAdapter.StackHolder>(list){

    override fun onCreateView(group: ViewGroup, viewType: Int): StackHolder {
        val view = LayoutInflater.from(group.context).inflate(R.layout.list_item_stack, group, false)
        return StackHolder(view, listener)
    }

    override fun onBindView(holder: StackHolder, position: Int) = holder.bind(getItem(position))

    class StackHolder(itemView: View, listener: BaseViewHolder.OnItemClickListener?): BaseViewHolder<Stack>(itemView, listener), BaseViewHolder.OnItemClickListener {

        private var stack: Stack? = null

        override fun bind(m: Stack) {
            stack = m
            itemView.name.text = m.name
            itemView.list.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            if (m.list == null) {
                m.data.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { bookDate ->
                            val books = bookDate.books!!.toMutableList()
                            itemView.list.adapter = StackItemListRecyclerAdapter(books).apply {
                                setOnItemClickListener(this@StackHolder)
                            }
                            m.list = books
                        }
            }else {
                itemView.list.adapter = StackItemListRecyclerAdapter(m.list as MutableList<Book>).apply {
                    setOnItemClickListener(this@StackHolder)
                }
            }
        }

        override fun onItemClick(view: View, position: Int) {
            val intent = Intent(itemView.context, BookDetailActivity::class.java)
            intent.putExtra("book", stack!!.list!!.get(position))
            itemView.context.startActivity(intent)
        }

        override fun onItemLongClick(view: View, position: Int) {
        }

    }

}
