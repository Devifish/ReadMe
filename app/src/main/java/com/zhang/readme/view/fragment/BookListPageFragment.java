package com.zhang.readme.view.fragment;

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
import com.zhang.readme.view.DetailActivity;
import com.zhang.readme.view.adapter.BookListRecyclerViewAdapter;
import com.zhang.readme.view.base.BaseMainPageFragment;
import com.zhang.readme.view.adapter.holder.BookListViewHolder;

import butterknife.BindView;

/**
 * Created by zhang on 2017/2/22.
 *
 * @author zhang
 */

public class BookListPageFragment extends BaseMainPageFragment implements SwipeRefreshLayout.OnRefreshListener {

    private final static String mTitle = "书架";
    private BookListRecyclerViewAdapter mRecyclerViewAdapter;
    private BookListDao mBookDao;
    private BookList mBookList;

    @BindView(R.id.page_booklist_swiperefesh) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.page_booklist_recycler) RecyclerView mRecyclerView;

    public BookListPageFragment() {super.setPageTitle(mTitle);}

    @Override
    protected int bindLayout() {return R.layout.page_booklist_main;}

    @Override
    protected void initVar() {
        mBookDao = new BookListDao(this.getContext());
        mBookList = mBookDao.getBookList();
        mRecyclerViewAdapter = new BookListRecyclerViewAdapter(mBookList);
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light
        );
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewItemClickListener());
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> {
            mSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getContext(), "更新成功", Toast.LENGTH_SHORT).show();
        }, 2000);
    }

    /**
     * RecyclerView Item 点击事件（点击/长按）
     * 实现书架item 项目逻辑
     */
    private class RecyclerViewItemClickListener implements BookListViewHolder.OnItemClickListener {

        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("book_info", mBookList.get(position));
            startActivity(intent);
        }

        @Override
        public void onItemLongClick(View view, int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(mBookList.get(position).getTitle());
            builder.setItems(new String[]{"置顶", "缓存到本地", "移出书架"}, (dialog, which) -> {
                switch (which) {
                    case 0: //置顶
                        break;
                    case 1: //缓存到本地
                        break;
                    case 2: //移出书架
                        mRecyclerViewAdapter.removeItem(position);
                        break;
                    default: break;
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
