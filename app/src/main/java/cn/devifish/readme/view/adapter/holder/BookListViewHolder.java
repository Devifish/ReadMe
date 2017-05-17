package cn.devifish.readme.view.adapter.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import cn.devifish.readme.R;
import cn.devifish.readme.view.base.BaseViewHolder;
import cn.devifish.readme.entity.Book;

import butterknife.BindView;

/**
 * Created by zhang on 2017/4/15.
 *
 * @author zhang
 */

public class BookListViewHolder extends BaseViewHolder<Book> {

    private Context mContext;

    @BindView(R.id.image) ImageView mImage;
    @BindView(R.id.title) TextView mTitle;
    @BindView(R.id.author) TextView mAuthor;

    public BookListViewHolder(@NonNull View itemView, @NonNull OnItemClickListener listener) {
        super(itemView, listener);
        mContext = itemView.getContext();
    }

    @Override
    public void bind(@NonNull Book book) {
        mTitle.setText(book.getTitle());
        mAuthor.setText(book.getAuthor());
        Glide.with(mContext)
                .load(book.getImagePath())
                .crossFade(800)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImage);
    }
}
