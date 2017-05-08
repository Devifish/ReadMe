package com.zhang.readme.view;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.zhang.readme.R;
import com.zhang.readme.dao.BookmarkDao;
import com.zhang.readme.entity.BookContext;
import com.zhang.readme.entity.BookDetail;
import com.zhang.readme.entity.Bookmark;
import com.zhang.readme.provider.ChapterProvider;
import com.zhang.readme.util.ProviderUtil;
import com.zhang.readme.view.adapter.BookContextRecyclerViewAdapter;
import com.zhang.readme.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ReadActivity extends BaseActivity {

    private boolean mLoad = false;
    private BookDetail mBookDetail;
    private BookmarkDao mBookmarkDao;
    private List<BookContext> mBookContextList;
    private BookContextRecyclerViewAdapter mRecyclerViewAdapter;

    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected int bindLayout() {return R.layout.activity_read;}

    @Override
    protected void initVar() {
        mBookContextList = new ArrayList<>();
        mBookDetail = this.getIntent().getParcelableExtra("chapter_detail");
        mBookmarkDao = new BookmarkDao(this);
        new ChapterDataInit().execute(mBookDetail.getChapterList().get(mBookDetail.getBookmarkIndex()).getUrl());
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!mRecyclerView.canScrollVertically(1) && !mLoad) {
                    Log.i("ContextOnload", mBookContextList.size() +"--"+ mRecyclerViewAdapter.getItemCount());
                    int index = mBookDetail.getBookmarkIndex() + 1;
                    if (index < mBookDetail.getChapterList().size()) {
                        mBookDetail.setBookmarkIndex(index);
                        int book_id = mBookDetail.getBook().getId();
                        if (mBookmarkDao.existsByClass(book_id, BookmarkDao.BOOKMARK_AUTO)) {
                            mBookmarkDao.updateAutoBookmark(book_id, index);
                        }else {
                            Bookmark bookmark = new Bookmark();
                            bookmark.setBookId(mBookDetail.getBook().getId());
                            bookmark.setName(mBookDetail.getBook().getTitle());
                            bookmark.setBookIndex(index);
                            bookmark.setMarkClass("auto");
                            mBookmarkDao.insertBookmark(bookmark);
                            mBookDetail.setBookmark(mBookmarkDao.getAutoBookmark(book_id));
                        }
                        new ChapterDataInit().execute(mBookDetail.getChapterList().get(index).getUrl());
                    }else {

                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBookmarkDao.close();
    }

    @SuppressLint("StaticFieldLeak")
    private class ChapterDataInit extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            Log.i("text_path", strings[0]);
            mLoad = true;
            ChapterProvider provider = ProviderUtil.Builder(ProviderUtil.PROVIDER_8DUSHU).getChapterProvider(strings[0]);
            return provider.getBookChapterText();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mLoad = false;
            BookContext bookContext = new BookContext();
            int bookmark = mBookDetail.getBookmarkIndex();
            bookContext.setTitle(mBookDetail.getChapterList().get(bookmark).getName());
            bookContext.setText(s);
            mBookContextList.add(bookContext);

            if (mRecyclerViewAdapter == null) {
                mRecyclerViewAdapter = new BookContextRecyclerViewAdapter(ReadActivity.this, mBookContextList);
                mRecyclerView.setAdapter(mRecyclerViewAdapter);
            }else {
                mRecyclerViewAdapter.notifyItemChanged(mRecyclerViewAdapter.getItemCount()-1);
            }

            mRecyclerView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
