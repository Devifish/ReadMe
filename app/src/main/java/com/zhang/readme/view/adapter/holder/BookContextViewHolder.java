package com.zhang.readme.view.adapter.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.zhang.readme.R;
import com.zhang.readme.entity.BookContext;
import com.zhang.readme.view.base.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by zhang on 2017/4/22.
 *
 */

public class BookContextViewHolder extends BaseViewHolder<BookContext> {

    @BindView(R.id.title) TextView mTitle;
    @BindView(R.id.text) TextView mText;

    public BookContextViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(@NonNull BookContext bookContext) {
        mTitle.setText(bookContext.getTitle());
        mText.setText(bookContext.getText());
    }
}
