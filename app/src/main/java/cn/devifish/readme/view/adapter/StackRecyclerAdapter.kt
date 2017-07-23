package cn.devifish.readme.view.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.devifish.readme.R
import cn.devifish.readme.entity.Stack
import cn.devifish.readme.view.base.BaseRecyclerAdapter
import cn.devifish.readme.view.base.BaseViewHolder
import kotlinx.android.synthetic.main.list_item_stack.view.*

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

    class StackHolder(itemView: View, listener: BaseViewHolder.OnItemClickListener?): BaseViewHolder<Stack>(itemView, listener) {

        override fun bind(m: Stack) {
            itemView.name.text = m.name
            itemView.list.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            itemView.list.adapter = StackItemListRecyclerAdapter(m.list.toMutableList())
        }
    }

}
