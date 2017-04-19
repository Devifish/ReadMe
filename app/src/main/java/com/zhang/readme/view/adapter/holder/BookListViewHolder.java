package com.zhang.readme.view.adapter.holder;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.readme.R;
import com.zhang.readme.view.base.BaseViewHolder;
import com.zhang.readme.entity.Book;

import butterknife.BindView;

/**
 * Created by zhang on 2017/4/15.
 *
 * @author zhang
 */

public class BookListViewHolder extends BaseViewHolder<Book> {

    @BindView(R.id.image) ImageView image;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.author) TextView author;

    public BookListViewHolder(@NonNull View itemView, @NonNull OnItemClickListener listener) {
        super(itemView, listener);
    }

    @Override
    public void bind(@NonNull Book book) {
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        image.setImageDrawable(Drawable.createFromPath(book.getImagePath()));
    }
}
