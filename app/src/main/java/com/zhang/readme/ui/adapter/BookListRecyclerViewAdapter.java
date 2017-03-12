package com.zhang.readme.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.readme.R;
import com.zhang.readme.model.BookInfo;

import java.util.List;

/**
 * Created by zhang on 2017/2/28.
 *
 * 书架相关功能及数据加载实现逻辑
 * @author zhang
 */

public class BookListRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<BookInfo> list;
    private OnRecyclerViewItemClickListener listener;

    public BookListRecyclerViewAdapter(Context context, List<BookInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item_booklist, parent, false);
        return new BookListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final BookInfo bookInfo = list.get(position);
        ((BookListViewHolder)holder).setInfo(bookInfo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v, bookInfo);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(v, bookInfo);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnRecyclerViewItemClickListener{
        void onClick(View v, BookInfo info);
        boolean onLongClick(View v, BookInfo info);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    private class BookListViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title;
        private TextView author;

        private BookListViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.book_image);
            title = (TextView) itemView.findViewById(R.id.book_title);
            author = (TextView) itemView.findViewById(R.id.book_author);
        }

        private void setInfo(BookInfo info) {
            if (info != null) {
                title.setText(info.getTitle());
                author.setText(info.getAuthor());
                image.setImageDrawable(Drawable.createFromPath(info.getImagePath()));
            }
        }
    }
}
