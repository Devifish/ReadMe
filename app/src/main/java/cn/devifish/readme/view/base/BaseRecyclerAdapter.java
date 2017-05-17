package cn.devifish.readme.view.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhang on 2017/4/15.
 *
 * @author zhang
 */

public abstract class BaseRecyclerAdapter<M, VH extends BaseViewHolder, L extends BaseViewHolder.OnItemClickListener> extends RecyclerView.Adapter<VH> {

    @NonNull private List<M> mList;
    @Nullable private L mListener;

    public BaseRecyclerAdapter(@NonNull List<M> list) {
        mList = list;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {return onCreateView(parent, viewType);}

    @Override
    public void onBindViewHolder(VH holder, int position) {onBindView(holder, position);}

    @Override
    public int getItemCount() {return mList.size();}

    protected abstract VH onCreateView(ViewGroup group, int viewType);
    protected abstract void onBindView(VH holder, int position);

    public void setOnItemClickListener(@Nullable L listener) {mListener = listener;}

    public L getItemClickListener() {return mListener;}

    public M getItem(int position) {return this.mList.get(position);}

    public void removeItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItem(M item) {
        int position = mList.indexOf(item);
        if (position != -1) removeItem(position);
    }

    public void addItem(M item, int position) {
        mList.add(position, item);
        notifyItemInserted(position);
    }

    public void addItem(M item) {addItem(item, getItemCount());}

}
