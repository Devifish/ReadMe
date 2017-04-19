package com.zhang.readme.view.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by zhang on 2017/2/22.
 *
 * @author zhang
 */

public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    @NonNull
    private OnItemClickListener mListener;

    public BaseViewHolder(@NonNull View itemView, @NonNull OnItemClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mListener = listener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public abstract void bind(@NonNull M m);

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) mListener.onItemClick(view, position);
    }

    @Override
    public boolean onLongClick(View view) {
        int position = getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) mListener.onItemLongClick(view, position);
        return true;
    }

}
