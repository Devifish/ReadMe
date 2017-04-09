package com.zhang.readme.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhang.readme.R;
import com.zhang.readme.entity.BookContext;

import java.util.List;

/**
 * Created by zhang on 2017/4/5.
 *
 * 小说内容及数据加载实现逻辑
 * @author zhang
 */

public class BookContextRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<BookContext> mBookContextList;

    public BookContextRecyclerViewAdapter(Context context, List<BookContext> list) {
        this.mContext = context;
        this.mBookContextList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.context_read, parent, false);
            return new BookContextViewHolder(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.load_context_read, parent, false);
            return new LoadContextViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BookContextViewHolder) {
            BookContextViewHolder bookContextViewHolder = (BookContextViewHolder) holder;
            bookContextViewHolder.setData(mBookContextList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mBookContextList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position != mBookContextList.size() ? 0 : 1;
    }

    private static class BookContextViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mText;

        private BookContextViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.read_title);
            mText = (TextView) itemView.findViewById(R.id.read_text);
        }

        private void setData(BookContext bookContext) {
            if (bookContext != null) {
                mTitle.setText(bookContext.getTitle());
                mText.setText(bookContext.getText());
            }
        }
    }

    private static class LoadContextViewHolder extends RecyclerView.ViewHolder {
        private LoadContextViewHolder(View itemView) {
            super(itemView);
        }
    }
}
