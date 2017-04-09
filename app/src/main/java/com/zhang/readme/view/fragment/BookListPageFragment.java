package com.zhang.readme.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.zhang.readme.R;
import com.zhang.readme.dao.BookListDao;
import com.zhang.readme.entity.BookList;
import com.zhang.readme.entity.Book;
import com.zhang.readme.view.DetailActivity;
import com.zhang.readme.view.adapter.BookListRecyclerViewAdapter;
import com.zhang.readme.view.base.BaseMainPageFragment;

/**
 * Created by zhang on 2017/2/22.
 *
 * @author zhang
 */

public class BookListPageFragment extends BaseMainPageFragment implements SwipeRefreshLayout.OnRefreshListener {

    private final static String mTitle = "书架";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private BookListRecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private BookListDao mBookDao;
    private BookList mBookList;

    public BookListPageFragment() {
        super.setPageTitle(mTitle);
        super.setContentView(R.layout.page_booklist_main);
    }

    @Override
    protected void initView(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.page_booklist_swiperefesh);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.page_booklist_recycler);
    }

    @Override
    protected void initViewState() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light
        );
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewItemClickListener());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    protected void initVar() {
        mBookDao = new BookListDao(this.getContext());
        mBookList = mBookDao.getBookList();
        mRecyclerViewAdapter = new BookListRecyclerViewAdapter(this.getContext(), mBookList);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getContext(), "更新成功", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }

    /**
     * RecyclerView Item 点击事件（点击/长按）
     * 实现书架item 项目逻辑
     */
    private class RecyclerViewItemClickListener implements BookListRecyclerViewAdapter.OnItemClickListener {

        @Override
        public void onClick(View v, Book book, int position) {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("book_info", book);
            startActivity(intent);
        }

        @Override
        public void onLongClick(View v, Book info, final int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(info.getTitle());
            builder.setItems(new String[]{"置顶", "缓存到本地", "移出书架"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0: //置顶
                            break;
                        case 1: //缓存到本地
                            break;
                        case 2: //移出书架
                            mBookList.remove(position);
                            mRecyclerViewAdapter.notifyItemRemoved(position);
                            break;
                        default: break;
                    }
                }
            });
            builder.show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBookDao.close();
    }
}
