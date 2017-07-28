package cn.devifish.readme.view.base

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by zhang on 2017/4/15.

 * @author zhang
 */

abstract class BaseRecyclerAdapter<M, VH : BaseViewHolder<M>>() : RecyclerView.Adapter<VH>() {

    var data: MutableList<M>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    constructor(list : MutableList<M>) : this() {
        this.data = list
    }

    var listener: BaseViewHolder.OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = onCreateView(parent, viewType)

    override fun onBindViewHolder(holder: VH, position: Int) = onBindView(holder, position)

    override fun getItemCount(): Int {
        if (data != null) {
            return data!!.size
        }
        return 0
    }

    protected abstract fun onCreateView(group: ViewGroup, viewType: Int): VH
    protected abstract fun onBindView(holder: VH, position: Int)

    fun setOnItemClickListener(listener: BaseViewHolder.OnItemClickListener?) {
        this.listener = listener
    }

    fun getItem(position: Int): M? {
        if (data != null) {
            return this.data!![position]
        }
        return null
    }

    fun removeItem(position: Int) {
        if (data != null) {
            data!!.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun removeItem(item: M) {
        if (data != null) {
            val position = data!!.indexOf(item)
            if (position != -1) removeItem(position)
        }
    }

    fun addItem(item: M, position: Int = itemCount) {
        if (data != null) {
            data!!.add(position, item)
            notifyItemInserted(position)
        }
    }

}
