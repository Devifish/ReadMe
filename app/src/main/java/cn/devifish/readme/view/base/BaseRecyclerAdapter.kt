package cn.devifish.readme.view.base

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by zhang on 2017/4/15.

 * @author zhang
 */

abstract class BaseRecyclerAdapter<M, VH : BaseViewHolder<M>>(val list: MutableList<M>) : RecyclerView.Adapter<VH>() {

    var listener: BaseViewHolder.OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = onCreateView(parent, viewType)

    override fun onBindViewHolder(holder: VH, position: Int) = onBindView(holder, position)

    override fun getItemCount(): Int = list.size

    protected abstract fun onCreateView(group: ViewGroup, viewType: Int): VH
    protected abstract fun onBindView(holder: VH, position: Int)


    fun setOnItemClickListener(listener: BaseViewHolder.OnItemClickListener?) {
        this.listener = listener
    }

    fun getItem(position: Int): M {
        return this.list[position]
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeItem(item: M) {
        val position = list.indexOf(item)
        if (position != -1) removeItem(position)
    }

    fun addItem(item: M, position: Int = itemCount) {
        list.add(position, item)
        notifyItemInserted(position)
    }

}
