package com.zhang.readme.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.readme.R;
import com.zhang.readme.model.BookList;
import com.zhang.readme.model.Book;

/**
 * Created by zhang on 2017/2/28.
 *
 * 书架相关功能及数据加载实现逻辑
 * @author zhang
 */

public class BookListRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private BookList bookList;
    private OnRecyclerViewItemClickListener listener;

    public BookListRecyclerViewAdapter(Context context, BookList bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item_booklist, parent, false);
        return new BookListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Book book = bookList.get(position);
        final int index = position;
        ((BookListViewHolder)holder).setInfo(book);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v, book, index);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(v, book, index);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public interface OnRecyclerViewItemClickListener{
        void onClick(View v, Book book, int position);
        void onLongClick(View v, Book book, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    private static class BookListViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title;
        private TextView author;

        private BookListViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.book_image);
            title = (TextView) itemView.findViewById(R.id.book_title);
            author = (TextView) itemView.findViewById(R.id.book_author);
        }

        private void setInfo(Book book) {
            if (book != null) {
                title.setText(book.getTitle());
                author.setText(book.getAuthor());
                image.setImageDrawable(Drawable.createFromPath(book.getImagePath()));
            }
        }
    }
}
