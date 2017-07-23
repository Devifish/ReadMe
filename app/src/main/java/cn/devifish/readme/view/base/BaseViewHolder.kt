package cn.devifish.readme.view.base

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by zhang on 2017/2/22.

 * @author zhang
 */

abstract class BaseViewHolder<M> constructor(itemView: View, listener: BaseViewHolder.OnItemClickListener? = null) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    private var mListener: OnItemClickListener? = null

    init {
        if (listener != null) {
            mListener = listener
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }
    }

    abstract fun bind(m: M)

    override fun onClick(view: View) {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) mListener!!.onItemClick(view, position)
    }

    override fun onLongClick(view: View): Boolean {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) mListener!!.onItemLongClick(view, position)
        return true
    }

}
