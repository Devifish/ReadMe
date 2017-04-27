package com.zhang.readme.view.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    private OnItemClickListener mListener;

    public BaseViewHolder(@NonNull View itemView) {
        this(itemView, null, false);
    }

    public BaseViewHolder(@NonNull View itemView, @NonNull OnItemClickListener listener) {
        this(itemView, listener, true);
    }

    private BaseViewHolder(@NonNull View itemView, @Nullable OnItemClickListener listener, boolean clickable) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        if (clickable) {
            mListener = listener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
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
